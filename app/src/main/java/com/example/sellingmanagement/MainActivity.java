package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Khai báo các biến giao diện
    Button button_QLyNv,button_QLyChamCong,button_QlyTinhLuong,button_DangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id
        button_QLyNv = findViewById(R.id.button_QLyNv);
        button_QLyChamCong = findViewById(R.id.button_QLyChamCong);
        button_QlyTinhLuong = findViewById(R.id.button_QlyTinhLuong);
        button_DangXuat = findViewById(R.id.button_DangXuat);
        //hàm xử lý sự kiện khi click vào nút đăng xuất
        button_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, Login.class);
                startActivity(myintent);
                Toast.makeText(MainActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });

        //hàm xử lý sự kiện khi click vào nút quản lý nhân viên
        button_QLyNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, TrangChuQuanLyNhanVien.class);
                startActivity(myintent);
            }
        });
        //hàm xử lý sự kiện khi click vào nút quản lý chấm công
        button_QLyChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, TrangChuQuanLyChamCong.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút quản lý tính lương
        button_QlyTinhLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, TrangChuQuanLyTinhLuong.class);
                startActivity(myintent);
            }
        });
    }
}