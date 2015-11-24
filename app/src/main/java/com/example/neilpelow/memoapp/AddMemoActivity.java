package com.example.neilpelow.memoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMemoActivity extends AppCompatActivity {

  Button MyAddButton;
  Button MyCancelButton;
  Button myLocationButton;
  Button myCameraButton;
  EditText MyInputText;
  Memos newMemo;

  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
  private Uri fileUri;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_memo);
    Log.w("AddMemoActivity", "Launching");


    MyAddButton = (Button) findViewById(R.id.addbutton);
    MyCancelButton = (Button) findViewById(R.id.cancelbutton);
    myLocationButton = (Button) findViewById(R.id.myLocationButton);
    myCameraButton = (Button) findViewById(R.id.myCameraButton);
    MyInputText = (EditText) findViewById(R.id.memoInput);

    //if MainActivity has sent data

    Bundle bundle = getIntent().getExtras();

    if (bundle.getParcelable("memoID") != null) {
      newMemo = bundle.getParcelable("memoID");
      MyInputText.setText(newMemo.get_memobody());
      //reset buttons to reflect change in memo state; update and delete.
      MyAddButton.setText("Update");
      MyCancelButton.setText("Delete");
    }

    Log.w("AddMemoActivity", "Launching 2");

    final int value = bundle.getInt("newID");
    Toast.makeText(AddMemoActivity.this, "ID: " + value, Toast.LENGTH_SHORT).show();

    MyAddButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Create new memo. Add to list view.
        if (MyInputText.getText().length() == 0) {
          Toast.makeText(AddMemoActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
        } else {

          if (MyAddButton.getText().equals("Add")) {
            Memos sendMemo = new Memos();
            sendMemo.set_memobody(MyInputText.getText().toString());
            sendMemo.set_id(value);
            Intent intentAdd = new Intent();
            intentAdd.putExtra("memo", sendMemo);
            intentAdd.putExtra("action", "add");
            setResult(RESULT_OK, intentAdd);
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
        if (MyInputText == null) {
          finish();
        }
        //if a memo already exists button will change to Delete
        if (MyCancelButton.getText().equals("Delete")) {

          Intent intent = new Intent();
          intent.putExtra("memo", newMemo);
          intent.putExtra("action", "delete");
          setResult(RESULT_OK, intent);
          finish();


          Toast.makeText(AddMemoActivity.this, "Delete button", Toast.LENGTH_SHORT).show();
        } else {
          finish();
        }
      }
    });

    myLocationButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.w("AddMemoActivity", "Launching 3");
        //Change to location and address view.
        Intent locationIntent = new Intent(AddMemoActivity.this, LocationLoc.class);
        startActivity(locationIntent);
      }
    });

    myCameraButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
      }
    });

  }//end onCreate

  public void returnData() {
    newMemo.set_memobody(MyInputText.getText().toString());
    Intent intent = new Intent();
    intent.putExtra("memo", newMemo);
    intent.putExtra("action", "update");
    setResult(RESULT_OK, intent);
    finish();
  }
}//end AddMemoActivity