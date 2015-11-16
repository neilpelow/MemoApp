package com.example.neilpelow.memoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMemoActivity extends AppCompatActivity {

    Button MyAddButton;
    Button MyCancelButton;
    EditText MyInputText;
    Memos newMemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);


        MyAddButton = (Button) findViewById(R.id.addbutton);
        MyCancelButton = (Button) findViewById(R.id.cancelbutton);
        MyInputText = (EditText) findViewById(R.id.memoInput);

        //if MainActivity has sent data

        Bundle bundle = getIntent().getExtras();

        if(bundle.getParcelable("memoID") != null) {
            newMemo = bundle.getParcelable("memoID");
            MyInputText.setText(newMemo.get_memoname());
            MyAddButton.setText("Update");
        }

        final int value = bundle.getInt("newID");
        Toast.makeText(AddMemoActivity.this, "ID: "+value, Toast.LENGTH_SHORT).show();

        MyAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new memo. Add to list view.
                if (MyInputText.getText().length() == 0) {
                    Toast.makeText(AddMemoActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
                } else {

                    if (MyAddButton.getText().equals("Add")) {
                        Memos sendMemo = new Memos();
                        sendMemo.set_memoname(MyInputText.getText().toString());
                        sendMemo.set_id(value);
                        Intent intent1 = new Intent();
                        intent1.putExtra("memo", sendMemo);
                        intent1.putExtra("action", "add");
                        setResult(RESULT_OK, intent1);
                        finish();
                    } else {
                        returnData();
                    }
                }
            }
        });

        MyCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Do NOT create new memo. Return to list view.
                finish();
            }
        });
    }

    public void returnData() {
        newMemo.set_memoname(MyInputText.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("memo", newMemo);
        intent.putExtra("action", "update");
        setResult(RESULT_OK, intent);
        finish();
    }
}
