package com.example.loginbtl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText tenDN, matKhau;
    Button dangnhap, dangkylai, quenmk;
    DBHelper DB;
    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dangkylai = findViewById(R.id.btnquaylaidky);
        tenDN = findViewById(R.id.username1);
        matKhau = findViewById(R.id.password1);
        dangnhap =findViewById(R.id.btndangnhap1);
        quenmk  = findViewById(R.id.quenMK);
        DB = new DBHelper(this);

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = tenDN.getText().toString();
                String pass = matKhau.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
                    Toast.makeText(Login.this, "Nhập đầy đủ", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkk = DB.check2(user, pass);
                    if (checkk==true){
                        Toast.makeText(Login.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, MainActivityMonAn.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login.this, "Sai Tên Đăng Nhập Hoặc Mật Khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dangkylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        quenmk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                View view = LayoutInflater.from(Login.this).inflate(R.layout.notifix, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();
//
            }
        });

    }
}


















