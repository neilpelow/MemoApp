package com.example.neilpelow.memoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] memos = {"New Entry +", "Starbucks", "Boardwalk", "Café"};
        ListAdapter memoAdapter;
        memoAdapter = new CustomAdapter(this, memos);
        ListView memoListView = (ListView) findViewById(R.id.MemoList);
        memoListView.setAdapter(memoAdapter);


    }
}
