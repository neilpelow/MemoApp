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
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;

    EditText memoInput;
    TextView textView2;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //------------------------------------Photo input---------------------------------------------------------//
        String[] memos = {"New Entry +"};
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



        //-----------------------------------------------Database input/output------------------------------------------//
        memoInput = (EditText) findViewById(R.id.memoInput);
        textView2 = (TextView) findViewById(R.id.textView2);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    //Add a memo to the database
    public void addButtonClicked(View view) {
        Memos memo = new Memos(memoInput.getText().toString());
        dbHandler.addMemo(memo);
        printDatabase();
    }

    //Delete memo
    public void deleteButtonClicked(View view) {
        String inputText = memoInput.getText().toString();
        dbHandler.deleteMemo(inputText);
        printDatabase();

    }

    public void printDatabase(){
        String dbString = dbHandler.databasetoString();
        textView2.setText(dbString);
        memoInput.setText("");
    }


}
