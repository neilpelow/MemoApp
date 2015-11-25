package com.example.neilpelow.memoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewMemoActivity extends AppCompatActivity {

  private static int RESULT_LOAD_IMAGE = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_view_memo);

//    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//    startActivityForResult(i, RESULT_LOAD_IMAGE);
}

//  @Override
//  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//      Uri selectedImage = data.getData();
//      String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//      Cursor cursor = getContentResolver().query(selectedImage,
//          filePathColumn, null, null, null);
//      cursor.moveToFirst();
//
//      int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//      String picturePath = cursor.getString(columnIndex);
//      cursor.close();
//
//      ImageView imageView = (ImageView) findViewById(R.id.imageView);
//      imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//    }
//  }
}