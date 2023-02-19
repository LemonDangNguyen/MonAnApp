package com.example.loginbtl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DataMonAn extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MonAn.db";
    private static final int DATABASE_VERSION = 1;

    public DataMonAn(@Nullable Context context, @Nullable String name,
                     @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        String tblMonAn = "Create table " + "MonAn" + "("
                + "ID integer primary key autoincrement, " +
                "hinhAnh blob,"
                +"tenMonAn text,"
                +"congThuc text)";
                db.execSQL(tblMonAn);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists " + "MonAn");
        onCreate(db);
    }


    public boolean update(MonAn monAn){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hinhAnh", monAn.getHinhAnh());
        values.put("tenMonAn", monAn.getTenMonAn());
        values.put("congThuc", monAn.getCongThuc());
        long row = db.update("MonAn", values, "ID=?", new String[]{monAn.getID() + ""});
        return (row>0);
    }

    public boolean delete(int ID){
        SQLiteDatabase db = getWritableDatabase();
        int row = db.delete("MonAn","ID=?", new String[]{ID + ""});
        return (row>0);
    }

    public boolean insert(byte[] hinhAnh, String tenMon, String congThuc) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hinhAnh", hinhAnh);
        values.put("tenMonAn", tenMon);
        values.put("congThuc", congThuc);
        long row = db.insert("MonAn", null, values);
        return (row>0);
    }

}
