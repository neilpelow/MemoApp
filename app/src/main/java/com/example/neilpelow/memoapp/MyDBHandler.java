package com.example.neilpelow.memoapp;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import android.content.Context;
import android.content.ContentValues;

/**
 * Created by neilpelow on 13/11/15.
 */
public class MyDBHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "memofiles.db";
    private static final String TABLE_MEMOS = "memos";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEMONAME = "memoname";

    public MyDBHandler (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE" + TABLE_MEMOS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_MEMONAME + " TEXT " + //text for now
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + );
    }

    public void addMemo(Memo memo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEMONAME, memo.get_memoname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_MEMOS, null, values);
        db.close();
    }

    public void deleteMemo(String memoname) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_MEMOS + " WHERE " + COLUMN_MEMONAME + "=\"" + memoname + "\";" );
    }

}
