package com.example.neilpelow.memoapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] memos = {"New Entry +", "Starbucks", "Boardwalk", "Café"};
        ListAdapter memoAdapter;
        memoAdapter = new CustomAdapter(this, memos);
        ListView memoListView = (ListView) findViewById(R.id.MemoList);
        memoListView.setAdapter(memoAdapter);

        memoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    // TODO:add image capute intent
                    Log.d("test", "This works!");

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); //create the file to save the image
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                    // start the image capture Intent
                    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }

            }
        });


    }


}
