package com.example.loginbtl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivitythemMonAn extends AppCompatActivity {
    private static final String DATABASE_NAME = "MonAn.db";
    private static final int DATABASE_VERSION = 1;

    private Button btnThemHinhAnh, btnThemVaoDS, btnHuy;
    private EditText edtTenMonAn, edtCheBien;
    private ImageView imageView;
    private ListView listView;
    private ArrayList<MonAn> listMonAn = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    final int REQUEST_CODE_IMG = 123;
    DataMonAn dataMonAn = new DataMonAn(MainActivitythemMonAn.this,DATABASE_NAME,
            null, DATABASE_VERSION );

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_IMG:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_IMG);
                }else {
                        Toast.makeText(this, "Ban Khong Cho phep", Toast.LENGTH_SHORT).show();
                    }
                /*if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_Camera);
                }else {
                    Toast.makeText(this, "Ban Khong CHo phep", Toast.LENGTH_SHORT).show();
                }
                break;

                 */
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitythem_mon_an);

        edtTenMonAn     = findViewById(R.id.tenMonAn);
        edtCheBien      = findViewById(R.id.congThucNau);

        btnThemHinhAnh  = findViewById(R.id.btn_themHinhAnh);
        btnThemVaoDS    = findViewById(R.id.btnThemMonAnvaoDS);
        btnHuy          = findViewById(R.id.btnHuy);

        imageView       = findViewById(R.id.anhMonAn);


   

        btnThemHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK); // may anh thi la Intern(Mediastore.action img capture);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_IMG);
                ActivityCompat.requestPermissions(MainActivitythemMonAn.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_IMG);

//                //MayAnh
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, REQUEST_CODE_IMG);
            }
        });


        btnThemVaoDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtTenMonAn.getText().toString())){
                    edtTenMonAn.setError("Nhap Ten Mon An");
                    return;
                } else if(TextUtils.isEmpty(edtCheBien.getText().toString())){
                    edtCheBien.setError("Nhap Cong Thuc");
                    return;
                } else if(imageView != null){
                    uploadToSQLite(imageView);
                    return;
                }else {
                    Toast.makeText(MainActivitythemMonAn.this, "Nhap anh", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }



    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    private void uploadToSQLite(ImageView anhMonAn) {
        dataMonAn.insert(
                imageViewToByte(anhMonAn),// hanh
                edtTenMonAn.getText().toString().trim(), //ten mon an
                edtCheBien.getText().toString().trim()//cth
        );
        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
        back();
    }
    private void back() {
        Intent back = new Intent(MainActivitythemMonAn.this, MainActivityMonAn.class);
        startActivity(back);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        //MayAnh
//        if(requestCode == REQUEST_CODE_IMG && resultCode == RESULT_OK && data != null){
//            Bitmap bitmap =(Bitmap) data.getExtras().get("data");
//            imageView.setImageBitmap(bitmap);
//        }
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



}

