package com.example.sellingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import Datamanagement.Logins;
import Datamanagement.NhanVien;

public class TrangChuQuanLyChamCong extends AppCompatActivity {
    //khai báo biến giao diện
    ImageButton btn_timKiemChamCong;
    EditText timKiemChamCong,maChamCong,maNvChamCong,hoTenNvChamCong,chucVuNvChamCong,soNgayNghi,soNgayDiLam;
    Button button_themChamCong,button_suaChamCong,button_XoaChamCong,button_backChamCong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_quan_ly_cham_cong);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ánh xạ id
        btn_timKiemChamCong = findViewById(R.id.btn_timKiemChamCong);
        timKiemChamCong = findViewById(R.id.timKiemChamCong);
        maChamCong = findViewById(R.id.maChamCong);
        maNvChamCong = findViewById(R.id.maNvChamCong);
        hoTenNvChamCong = findViewById(R.id.hoTenNvChamCong);
        chucVuNvChamCong = findViewById(R.id.chucVuNvChamCong);
        soNgayNghi = findViewById(R.id.soNgayNghi);
        soNgayDiLam = findViewById(R.id.soNgayDiLam);
        button_themChamCong = findViewById(R.id.button_themChamCong);
        button_suaChamCong = findViewById(R.id.button_suaChamCong);
        button_XoaChamCong = findViewById(R.id.button_XoaChamCong);
        button_backChamCong = findViewById(R.id.button_backChamCong);

        //hàm xử lý khi click vào ô tim kiếm
        btn_timKiemChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection = SQLServerHelper.connectionSQLSever();
                String maNv = timKiemChamCong.getText().toString();
                NhanVien nhanVien = new NhanVien();
                try {
                    nhanVien = NhanVien.getmaNvlist(maNv);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (connection != null) {
                        BreakIterator id;
                        //tạo câu lệnh xem thông tin trong bảng PhieuMuon
                        String sqlXem = "select * from ThongTinNhanVien where MaNV = '" + timKiemChamCong.getText().toString() + "'";
                        //tạo đối tượng statement
                        Statement st = connection.createStatement();
                        //thực thi câu lệnh sql
                        ResultSet rs = st.executeQuery(sqlXem);
                        if (rs.next() && nhanVien.getUser().equals(maNv)) {
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            maNvChamCong.setText(rs.getString(1));
                            hoTenNvChamCong.setText(rs.getString(2));
                        }else{
                            Toast.makeText(TrangChuQuanLyChamCong.this, "Bạn chưa thêm nhân viên này", Toast.LENGTH_SHORT).show();
                        }
                    }
                    } catch(SQLException exception){
                        Log.e("Errol", exception.getMessage());
                    }
                }
        });
//Hàm xử lý sự kiện khi click vào quay lại
        button_backChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(TrangChuQuanLyChamCong.this, MainActivity.class);
                startActivity(myintent);
            }
        });

        //hàm xử lý sự kiện khi click vào nút thêm
        button_themChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyChamCong.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlThem="insert into ThongTinChamCong values ('"+maChamCong.getText().toString()+"','"+maNvChamCong.getText().toString()+"','"+hoTenNvChamCong.getText().toString()+"','"+chucVuNvChamCong.getText().toString()+"','"+soNgayNghi.getText().toString()+"','"+soNgayDiLam.getText().toString()+"')";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //chạy câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlThem);
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
//hàm xử lý sự kiện khi click vào nút sửa
        button_suaChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyChamCong.this, "Sửa chấm công nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlSua="update ThongTinChamCong set HoVaTen = '"+hoTenNvChamCong.getText().toString()+"',ChucVu='"+chucVuNvChamCong.getText().toString()+"',SoNgayNghi='"+soNgayNghi.getText().toString()+"',SoNgayLam='"+soNgayDiLam.getText().toString()+"'where MaChamCong ='"+maChamCong.getText().toString()+"'";
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
        button_XoaChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyChamCong.this, "Xóa chấm công nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlXoa="delete ThongTinChamCong where MaChamCong = '"+maChamCong.getText().toString()+"'";
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
    }
}