package com.example.neilpelow.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "memofiles.db";
  private static final String TABLE_MEMOS = "memos";
  private static final String COLUMN_ID = "_id";
  private static final String COLUMN_MEMOBODY = "memobody";
  //private static final String COLUNM_ADDRESS = "address";

  SQLiteDatabase sqLiteDatabase;

  public MyDBHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  public void open() {
    sqLiteDatabase = this.getWritableDatabase();
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    sqLiteDatabase = db;
    String query = "CREATE TABLE " + TABLE_MEMOS + "(" +
        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_MEMOBODY + " TEXT" + //text for now
        ");";
    db.execSQL(query);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS" + TABLE_MEMOS);
  }

  public void addMemo(Memos memos) {
    ContentValues values = new ContentValues();
    if (memos == null) {
      Log.d("SF", "memo is fucking null");
    }
    values.put(COLUMN_MEMOBODY, memos.get_memobody());
    SQLiteDatabase db = getWritableDatabase();
    db.insert(TABLE_MEMOS, null, values);
    db.close();
  }

  public void updateMemo(Memos memos) {
    open();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN_MEMOBODY, memos.get_memobody());

    sqLiteDatabase.update(TABLE_MEMOS, contentValues, COLUMN_ID + " = " + memos.get_id(), null);

  }

  public void deleteMemo(Memos memos) {
    open();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLUMN_MEMOBODY, memos.get_memobody());

    sqLiteDatabase.delete(TABLE_MEMOS, COLUMN_ID + " = " + memos.get_id(), null);
    Log.d("NP", "Delete method runs");
  }

  public List<Memos> getAllMemos() {
    Log.d("NP", "method runs");
    open();
    String query = "SELECT * FROM "+TABLE_MEMOS;
    Cursor cursor = sqLiteDatabase.rawQuery(query, null);
    List<Memos> memolist = new ArrayList<>();
    if (cursor.getCount() != 0) {
      cursor.moveToFirst();
      while (cursor.moveToNext()) {
        Memos memos = new Memos();
        memos.set_id(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
        memos.set_memobody(cursor.getString(cursor.getColumnIndex(COLUMN_MEMOBODY)));
        memolist.add(memos);
      }
    }
    return memolist;
  }
}//end MyDBHandler