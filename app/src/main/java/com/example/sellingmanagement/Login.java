package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.SQLException;

import Datamanagement.Logins;

public class Login extends AppCompatActivity {
    //Khai báo các biến giao diện
    TextView btnDkDn;
    EditText login_user,login_password;
    Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dangNhap), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Init();
        onClickDangNhap();
        //hàm xử lý sự kiện khi click vào ô đăng ký
        btnDkDn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(Login.this,SignUp.class);
                startActivity(myintent);
            }
        });

    }

    //hàm ánh xạ id giao diện lên các biến giao diện
    private void Init() {
        btnDkDn = findViewById(R.id.btnDkDn);
        login_user = findViewById(R.id.login_user);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
    }

    //hàm xử lý sự kiện khi click vào nút đăg nhập
    private void onClickDangNhap(){
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //khai báo biến và gán dữ liệu trùng vs dữ liệu nhâp
                String user = login_user.getText().toString();
                String passwords = login_password.getText().toString();
                //khai báo class logins
                Logins logins = new Logins();
                try {
                    logins = Logins.getuserlist(user,passwords);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }if(user.length()==0 || passwords.length() ==0){//câu lệnh if kiểm tra nếu ô tài khoản và mật khẩu bỏ trống
                    Toast.makeText(Login.this, "Vui lòng nhập tài khoản hoặc mật khâu", Toast.LENGTH_SHORT).show();
                }
                else if(logins.getUser().equals(user) && logins.getPass().equals(passwords)){//câu lệnh if kiểm tra nếu ô tài khoản và mật khẩu trùng với csdl
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }else{//câu lệnh else kiểm tra nếu ô tài khoản và mật khẩu sai với csdl
                    Toast.makeText(Login.this, "Tài khoản mật khẩu của bạn không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}