package com.example.loginbtl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tenDN, matKhau, matKhau2;
    Button dangnhap, dangky;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tenDN = findViewById(R.id.username);
        matKhau = findViewById(R.id.password);
        matKhau2 = findViewById(R.id.repassword);
        dangnhap = findViewById(R.id.btndangnhap);
        dangky = findViewById(R.id.btndangky);
        DB = new DBHelper(this);


        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendn = tenDN.getText().toString();
                String mk = matKhau.getText().toString();
                String mk2 = matKhau2.getText().toString();

                if (TextUtils.isEmpty(tendn) || TextUtils.isEmpty(mk) || TextUtils.isEmpty(mk2))
                    Toast.makeText(MainActivity.this, "Bạn vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                else {
                    if (mk.equals(mk2)) {
                        Boolean checknguoidung = DB.check(tendn);
                        if (checknguoidung == false) {
                            Boolean insert = DB.insertData(tendn, mk);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Lỗi Đăng Ký", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Tài Khoản Đã Tồn Tại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Sai Mật Khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}