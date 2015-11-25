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

public class MainActivity extends AppCompatActivity {

  private static final int MEMO_RETURN = 1;
  CustomAdapter memoAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

//    Button locationButton;
    Memos newMemo = new Memos();
    newMemo.set_memobody("Add Memo");
    memoAdapter = new CustomAdapter(this);
    memoAdapter.add(newMemo);

//    locationButton = (Button) findViewById(R.id.locationButton);
//
//    //
//    locationButton.setOnClickListener(new View.OnClickListener() {
//      @Override
//      public void onClick(View v) {
//        //Change to location and address view.
//        Intent myintent = new Intent(MainActivity.this, LocationLoc.class);
//        startActivity(myintent);
//      }
//    });

    final ListView memoListView = (ListView) findViewById(R.id.MemoList);
    memoListView.setAdapter(memoAdapter);


    memoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // TODO:add image capture intent
        Log.d("test", "This works!");

        //Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
        //Memos memos = new Memos();

        if (position > 0) {
          Memos memos = memoAdapter.getItem(position);
          //intent.putExtra("memoID", memos);
          //startActivityForResult(intent, MEMO_RETURN);
        } else {
          addNewMemo();
        }

      }

    });

  }

  private void addNewMemo() {
    Intent intent = new Intent(this, AddMemoActivity.class);
    Memos memo = new Memos();
    intent.putExtra("memo", memo);
    startActivity(intent);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    //this is returned from memo adding
    if (requestCode == MEMO_RETURN && resultCode == Activity.RESULT_OK) {

      String value = data.getStringExtra("action");
      Memos memos = data.getParcelableExtra("memo");

      if (value.equals("add")) {
        memoAdapter.add(memos);
        memoAdapter.notifyDataSetChanged();
      }  else  if (value.equals("update")) {

        for (int i = 1; i < memoAdapter.getCount(); i++) {

          Memos getMemo = memoAdapter.getItem(i);

          if (getMemo.get_id() == memos.get_id()) {
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
            }
          }
      }
    }
  }
}