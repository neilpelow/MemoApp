package com.example.neilpelow.memoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddMemoActivity extends AppCompatActivity {

  Button MyAddButton;
  Button MyCancelButton;
  Button myLocationButton;
  Button myCameraButton;
  EditText MyInputText;
  TextView locationTextView;
  ImageView imageView;
  Memos newMemo;
  String imageString;


  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
  private Uri fileUri;

  int value;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_memo);
    Log.w("AddMemoActivity", "Launching");


    Log.w("Location", "location");

    MyAddButton = (Button) findViewById(R.id.addbutton);
    MyCancelButton = (Button) findViewById(R.id.cancelbutton);
    myLocationButton = (Button) findViewById(R.id.myLocationButton);
    myCameraButton = (Button) findViewById(R.id.myCameraButton);
    MyInputText = (EditText) findViewById(R.id.memoInput);
    locationTextView = (TextView) findViewById(R.id.locationTextView);
    imageView = (ImageView) findViewById(R.id.memo_imageView);

    //if MainActivity has sent data

    Intent intent = getIntent();

    if (intent.getStringExtra("action").equals("update")) {
      newMemo = intent.getParcelableExtra("memoID");
      MyInputText.setText(newMemo.get_memobody());
      locationTextView.setText(newMemo.getAddress());
      //if (imageString != null) {
      imageView.setImageBitmap(decodeBase64(newMemo.getImagePath()));
      //}
      //reset buttons to reflect change in memo state; update and delete.
      MyAddButton.setText("Update");
      MyCancelButton.setText("Delete");
    } else {

      LocationLoc locationLoc = new LocationLoc(this);
      String location = locationLoc.getLocationAddress();
      locationTextView.setText(location);
      value = intent.getIntExtra("newID", 0);
      MyAddButton.setText("Add");
      MyCancelButton.setText("Cancel");
    }

    MyAddButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //Create new memo. Add to list view.
        if (MyInputText.getText().length() == 0) {
          Toast.makeText(AddMemoActivity.this, "Please enter data", Toast.LENGTH_SHORT).show();
        } else if(imageString == null) {
          if(MyAddButton.getText().equals("Update")) {
            returnData();
          } else {
            Toast.makeText(AddMemoActivity.this, "Please add a photo", Toast.LENGTH_SHORT).show();
          }
        }
        else {

          if (MyAddButton.getText().equals("Update")) {
            returnData();
          } else {

            Memos sendMemo = new Memos();
            sendMemo.set_memobody(MyInputText.getText().toString());
            sendMemo.setAddress(locationTextView.getText().toString());
            sendMemo.setImagePath(imageString);
            sendMemo.set_id(value);
            Intent intentAdd = new Intent();
            intentAdd.putExtra("memo", sendMemo);
            intentAdd.putExtra("action", "add");
            setResult(RESULT_OK, intentAdd);
            finish();
          }
        }
      }
    });


    MyCancelButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //DO NOT create new memo. Return to list view.
        if (MyInputText == null) {
          finish();
        }
        //When button reads delete
        if (MyCancelButton.getText().equals("Delete")) {
          Intent intent = new Intent();
          intent.putExtra("memo", newMemo);
          intent.putExtra("action", "delete");
          setResult(RESULT_OK, intent);
          finish();
        }
        if (MyCancelButton.getText().equals("Cancel")) {
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

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
      }
    });

  }//end onCreate

  public void returnData() {
    String stringForInput = MyInputText.getText().toString();
    Log.d("SF", stringForInput);
    newMemo.set_memobody(stringForInput);

    Intent intent = new Intent();
    intent.putExtra("memo", newMemo);
    intent.putExtra("action", "update");

    //myDBHandler.addMemo(newMemo);

    setResult(RESULT_OK, intent);
    finish();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

      Bundle extras = data.getExtras();
      Bitmap bitmap = (Bitmap) extras.get("data");
      imageView.setImageBitmap(bitmap);

      imageString = encocdeTO64(bitmap);

    }
  }

  private Bitmap decodeBase64(String imageString) {
      byte[] decodeByte = Base64.decode(imageString, 0);
      return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
  }


  private String encocdeTO64(Bitmap bitmap) {

    String bitmapString = null;

    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
      byte[] bytes = byteArrayOutputStream.toByteArray();

      bitmapString = Base64.encodeToString(bytes, Base64.DEFAULT);
      byteArrayOutputStream.close();

    }catch (IOException e) {
      e.printStackTrace();
    }

    return  bitmapString;
  }
}//end AddMemoActivity