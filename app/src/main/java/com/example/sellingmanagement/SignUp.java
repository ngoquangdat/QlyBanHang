package com.example.sellingmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class SignUp extends AppCompatActivity {

    //khai báo các biến giao diện
    EditText login_user,login_password,login_ComfirmPassword;
    Button login_button,btnDangNhapNgay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ánh xạ id
        login_user = findViewById(R.id.login_user);
        login_password = findViewById(R.id.login_password);
        login_ComfirmPassword = findViewById(R.id.login_ComfirmPassword);
        login_button = findViewById(R.id.login_button);
        btnDangNhapNgay = findViewById(R.id.btnDangNhapNgay);
//    Hàm xử lý sự kiện khi click vào nút đăng ký
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= SQLServerHelper.connectionSQLSever();//kết nối csdl
                try {
                    if(connection!=null){
                        if(login_user.length()==0 || login_password.length()==0 || login_ComfirmPassword.length()==0){
                            Toast.makeText(SignUp.this, "Vui lòng nhập đầy đủ thông tin đăng ký", Toast.LENGTH_SHORT).show();
                        }else {
                            BreakIterator id;
                            //tạo câu lệnh sql thêm thông tin
                            String sqlDk = "insert into ThongTinDangNhap values('" + login_user.getText().toString() + "','" + login_password.getText().toString() + "','" + login_ComfirmPassword.getText().toString() + "')";
                            //tạo đối tượng Statement
                            Statement st = connection.createStatement();
                            //thực thi câu lệnh sql
                            ResultSet rs = st.executeQuery(sqlDk);
                            //hiển thị ra màn hình thông báo
                            Toast.makeText(SignUp.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
////    Hàm xử lý sự kiện khi click vào nút đăng nhập ngay
        btnDangNhapNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(SignUp.this, Login.class);
                startActivity(myintent);
            }
        });
    }
}