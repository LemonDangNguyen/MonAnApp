package com.example.loginbtl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivityMonAn extends AppCompatActivity {
    private static final String DATABASE_NAME = "MonAn.db";
    private static final int DATABASE_VERSION = 1;

    private Button bthThemMonMoi;
    private ListView listViewMonAn;
    private ArrayAdapter<String> adapter;
    private ArrayList<MonAn> listMonAn = new ArrayList<>();

    MonAnAdapter monAnAdapter;


    DataMonAn dataMonAn = new DataMonAn(MainActivityMonAn.this, DATABASE_NAME,
            null, DATABASE_VERSION);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mon_an);

        bthThemMonMoi = findViewById(R.id.btn_themMonAnMoi);
        listViewMonAn = findViewById(R.id.lstViewMonAn);


        monAnAdapter  = new MonAnAdapter(getListMonAn(),this);
        listViewMonAn.setAdapter(monAnAdapter);
        listViewMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MonAn monAn = (MonAn) listViewMonAn.getItemAtPosition(position);
                monAn.setID(position);
                Intent intentDetail = new Intent(MainActivityMonAn.this, DetailActivitythemMonAn.class);
//                Bundle bundleDetail = new Bundle();
//                bundleDetail.putSerializable("abc",  monAn);
//                intentDetail.putExtras(bundleDetail);
                startActivity(intentDetail);

            }
        });

        bthThemMonMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMonAn.this, MainActivitythemMonAn.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<MonAn> getListMonAn() {
        ArrayList<MonAn> ds = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dataMonAn.getReadableDatabase();
        Cursor cs = sqLiteDatabase.rawQuery("Select * from MonAn", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {

           int ma = cs.getInt(0);
            byte[] anh = cs.getBlob(1);
            String ten = cs.getString(2);
            String cth = cs.getString(3);

            MonAn monAn = new MonAn(ma, anh, ten, cth);
            ds.add(monAn);
            cs.moveToNext();
        }
        cs.close();
        sqLiteDatabase.close();
        return ds;
    }
//        private ArrayList<MonAn> getListMonAn() {


}