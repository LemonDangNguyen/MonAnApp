package com.example.loginbtl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class DetailActivitythemMonAn extends AppCompatActivity {

    private static final String DATABASE_NAME = "MonAn.db";
    private static final int DATABASE_VERSION = 1;
    int REQUEST_CODE_IMG = 123;

    ImageView imageView;
    TextView updateten, updatecongThuc;
    Button updatehinhAnh, updateMonAnDS, xoaMonAn;
    MonAnAdapter monAnAdapter;
    private ArrayList<MonAn> listMonAn = new ArrayList<>();
    private ListView listView;


    DataMonAn dataMonAn = new DataMonAn(DetailActivitythemMonAn.this, DATABASE_NAME,null, DATABASE_VERSION);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activitythem_mon_an);

        imageView = findViewById(R.id.update_anhMonAn);

        updateten = findViewById(R.id.update_tenMonAn);
        updatecongThuc = findViewById(R.id.update_congThucNau);

        updatehinhAnh = findViewById(R.id.btn_updateHinhAnh);
        updateMonAnDS = findViewById(R.id.btnCapNhatMonAn);
        xoaMonAn = findViewById(R.id.btnXoaMonAn);



        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }

        MonAn monAn = (MonAn) bundle.get("abc");
        byte[] anh = monAn.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        imageView.setImageBitmap(bitmap);
        updateten.setText(monAn.getTenMonAn());
        updatecongThuc.setText(monAn.getCongThuc());




        updatehinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK); // may anh thi la Intern(Mediastore.action img capture);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_IMG);
            }
        });




        xoaMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMonAn.delete(monAn.getID());
                listMonAn.remove(monAn);

            }
        });

        updateMonAnDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(updateten.getText().toString())){
                    updateten.setError("Nhap Ten Mon An");
                    return;
                } else if(TextUtils.isEmpty(updatecongThuc.getText().toString())){
                    updatecongThuc.setError("Nhap Cong Thuc");
                    return;
                } else if(imageView != null){
                    uploadToSQLite(imageView);
                    return;
                }else {
                    Toast.makeText(DetailActivitythemMonAn.this, "Nhap anh", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_IMG && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void uploadToSQLite(ImageView anhMonAn) {
        dataMonAn.insert(
                imageViewToByte(anhMonAn),// hanh
                updateten.getText().toString().trim(), //ten mon an
                updatecongThuc.getText().toString().trim()//cth
        );
        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
        back();
    }
    private void back() {
        Intent back = new Intent(DetailActivitythemMonAn.this, MainActivityMonAn.class);
        startActivity(back);
    }
    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}