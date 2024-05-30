package com.example.sellingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.BreakIterator;

public class TrangChuQuanLyNhanVien extends AppCompatActivity {
    //Khai báo các biến giao diện
    EditText maNVQlyNV,hoTenQlyNV,sdtQlyNV,gioiTinhQlyNV,soCCCDQlyNV,ngaySinhQlyNV,queQuanQlyNV,bangCapQlyNV;
    Button button_ThemNV,button_SuaNV,button_XoaNV,button_XemNV,button_backQlyNv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_quan_ly_nhan_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ÁNH XẠ ID
        maNVQlyNV = findViewById(R.id.maNVQlyNV);
        hoTenQlyNV = findViewById(R.id.hoTenQlyNV);
        sdtQlyNV = findViewById(R.id.sdtQlyNV);
        gioiTinhQlyNV = findViewById(R.id.gioiTinhQlyNV);
        soCCCDQlyNV = findViewById(R.id.soCCCDQlyNV);
        ngaySinhQlyNV = findViewById(R.id.ngaySinhQlyNV);
        queQuanQlyNV = findViewById(R.id.queQuanQlyNV);
        bangCapQlyNV = findViewById(R.id.bangCapQlyNV);
        button_ThemNV = findViewById(R.id.button_ThemNV);
        button_SuaNV = findViewById(R.id.button_SuaNV);
        button_XoaNV = findViewById(R.id.button_XoaNV);
        button_XemNV = findViewById(R.id.button_XemNV);
        button_backQlyNv = findViewById(R.id.button_backQlyNv);

        //hàm xử lý sự kiện khi click vào nút quay lại
        button_backQlyNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TrangChuQuanLyNhanVien.this, MainActivity.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút thêm
        button_ThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLServerHelper.connectionSQLSever();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlThem="insert into ThongTinNhanVien values ('"+maNVQlyNV.getText().toString()+"','"+hoTenQlyNV.getText().toString()+"','"+sdtQlyNV.getText().toString()+"','"+gioiTinhQlyNV.getText().toString()+"','"+soCCCDQlyNV.getText().toString()+"','"+ngaySinhQlyNV.getText().toString()+"','"+queQuanQlyNV.getText().toString()+"','"+bangCapQlyNV.getText().toString()+"')";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlThem);
                        Toast.makeText(TrangChuQuanLyNhanVien.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
//hàm xử lý sự kiện khi click vào nút sửa
        button_SuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyNhanVien.this, "Sửa nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlSua="update ThongTinNhanVien set HoVaTen = '"+hoTenQlyNV.getText().toString()+"',SDT='"+sdtQlyNV.getText().toString()+"',GioiTinh='"+gioiTinhQlyNV.getText().toString()+"',CCCD='"+soCCCDQlyNV.getText().toString()+"',NgaySinh='"+ngaySinhQlyNV.getText().toString()+"',QueQuan='"+queQuanQlyNV.getText().toString()+"',BANGCAP='"+bangCapQlyNV.getText().toString()+"'where MaNV ='"+maNVQlyNV.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlSua);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nút xóa
        button_XoaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyNhanVien.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlXoa="delete ThongTinNhanVien where MaNV = '"+maNVQlyNV.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXoa);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nút xem
        button_XemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TrangChuQuanLyNhanVien.this, XemNhanVien.class);
                startActivity(myintent);
            }
        });
    }
}