package com.example.neilpelow.memoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final int MEMO_RETURN = 1;
  CustomAdapter memoAdapter;
  MyDBHandler myDBHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    myDBHandler = new MyDBHandler(MainActivity.this);
    myDBHandler.open();

    Memos newMemo = new Memos();
    newMemo.set_memobody("Add Memo");
    memoAdapter = new CustomAdapter(this);
    memoAdapter.add(newMemo);

    final ListView memoListView = (ListView) findViewById(R.id.MemoList);
    memoListView.setAdapter(memoAdapter);
    populateListView();

    //populate Location Text

    memoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // TODO:add image capture intent
        Log.d("test", "This works!");
        if (position > 0) {
          Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
          Memos memos = memoAdapter.getItem(position);
          intent.putExtra("action", "update");
          intent.putExtra("memoID", memos);
          startActivityForResult(intent, MEMO_RETURN);
        } else {
          addNewMemo();
        }
      }

    });
  }

  private void populateListView() {
    Log.d("NP", "Gets to here anyway...");

    List<Memos> memosList = myDBHandler.getAllMemos();

    for (int i = 0; i < memosList.size(); i++) {
      memoAdapter.add(memosList.get(i));
    }

    //Display memos in list view.

  }

  private void addNewMemo() {
    Intent intent = new Intent(this, AddMemoActivity.class);
    intent.putExtra("newID", memoAdapter.getCount() - 1);
    intent.putExtra("action", "add");
    startActivityForResult(intent, MEMO_RETURN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    //this is returned from memo adding
    if (requestCode == MEMO_RETURN && resultCode == Activity.RESULT_OK) {

      String value = data.getStringExtra("action");
      Memos memos = data.getParcelableExtra("memo");

      if (value.equals("add")) {
        Log.w("action", "add");
        memoAdapter.add(memos);
        memoAdapter.notifyDataSetChanged();
        myDBHandler.addMemo(memos);
      } else if (value.equals("update")) {
        Log.w("action", "update");
        for (int i = 1; i < memoAdapter.getCount(); i++) {

          Memos getMemo = memoAdapter.getItem(i);

          if (getMemo.get_id() == memos.get_id()) {
            myDBHandler.updateMemo(memos);
            getMemo.set_memobody(memos.get_memobody());
            //Shows that the update has fired.
            Toast.makeText(this, memos.get_memobody(), Toast.LENGTH_SHORT).show();
            memoAdapter.notifyDataSetChanged();
          }
        }
      } else if (value.equals("delete")) {
        for (int i = 1; i < memoAdapter.getCount(); i++) {
          Memos getMemo = memoAdapter.getItem(i);
          if (getMemo.get_id() == memos.get_id()) {
            memoAdapter.remove(memoAdapter.getItem(i));
            memoAdapter.notifyDataSetChanged();
            myDBHandler.deleteMemo(memos);
            Log.d("action","delete");
          }//end inner if
        }//end outer delete if
      }//end delete else if
    }//end if result okay if
  }//end onActivityResult
}//end main