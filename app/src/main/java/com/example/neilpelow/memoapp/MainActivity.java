package com.example.neilpelow.memoapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MEMO_RETURN = 1;
    CustomAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Memos newMemo = new Memos();
        newMemo.set_memoname("Add Memo");
        memoAdapter = new CustomAdapter(this);
        memoAdapter.add(newMemo);

        final ListView memoListView = (ListView) findViewById(R.id.MemoList);
        memoListView.setAdapter(memoAdapter);

        memoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // TODO:add image capture intent
                    Log.d("test", "This works!");

                    Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
                    //user picked a memo
                    if (position > 0) {
                        Memos memos = memoAdapter.getItem(position);
                        intent.putExtra("memoID", memos);
                        startActivityForResult(intent, MEMO_RETURN);
                    } else {
                        //new memo
                        intent.putExtra("newID", memoAdapter.getCount());
                        startActivityForResult(intent, MEMO_RETURN);
                    }

                }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //this is coming back from memo adding
        if(requestCode == MEMO_RETURN && resultCode == Activity.RESULT_OK) {

            String value = data.getStringExtra("action");
            Memos memos = data.getParcelableExtra("memo");

            if(value.equals("add")) {
                memoAdapter.add(memos);
                memoAdapter.notifyDataSetChanged();
            } else {

                for(int i = 1; i < memoAdapter.getCount(); i++) {

                    Memos getMemo = memoAdapter.getItem(i);

                    if(getMemo.get_id() == memos.get_id()) {
                        getMemo.set_memoname(memos.get_memoname());
                        Toast.makeText(this, memos.get_memoname(),Toast.LENGTH_SHORT).show();
                        memoAdapter.notifyDataSetChanged();
                    }
                }

            }


        }
    }
}
