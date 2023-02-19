package com.example.loginbtl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "dangnhap.db";
    public DBHelper( Context context) {
        super(context, "dangnhap.db", null, 1   );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(tenDN TEXT primary key, matKhau TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    @SuppressLint("SuspiciousIndentation")
    public boolean insertData(String tenDN, String  matKhau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDN", tenDN);
        values.put("matKhau", matKhau);

        long result = db.insert("users", null, values);
        if(result == -1)  return false;
        else
        return true;
    }

    public boolean check(String tenDN){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where tenDN = ?", new String[] {tenDN});
        if(cursor.getCount()>0) return true;
        else return false;
    }
    public boolean check2(String tenDN, String matKhau){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where tenDN=? and matKhau=?", new String[] {tenDN, matKhau});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
