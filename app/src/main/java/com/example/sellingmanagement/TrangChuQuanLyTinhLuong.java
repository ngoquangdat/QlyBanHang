package com.example.sellingmanagement;

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

import Datamanagement.ChamCong;

public class TrangChuQuanLyTinhLuong extends AppCompatActivity {

    //khai báo biến giao diện
    ImageButton btn_timKiemLuong;
    EditText timKiemLuong,maLuong,maChamCongLuong,maNVluong,hoTenLuong,soNgayCongLuong,soTangCaLuong,luongCoBan,luongTangCa,phuCap,tamUng,bhxh,tongLuong,hinhThucGuiLuong;
    Button button_themLuong,button_suaLuong,button_xoaLuong,button_xemLuong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_trang_chu_quan_ly_tinh_luong);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //ánh xạ id
        btn_timKiemLuong = findViewById(R.id.btn_timKiemLuong);
        timKiemLuong = findViewById(R.id.timKiemLuong);
        maLuong = findViewById(R.id.maLuong);
        maChamCongLuong = findViewById(R.id.maChamCongLuong);
        maNVluong = findViewById(R.id.maNVluong);
        hoTenLuong = findViewById(R.id.hoTenLuong);
        soNgayCongLuong = findViewById(R.id.soNgayCongLuong);
        soTangCaLuong = findViewById(R.id.soTangCaLuong);
        luongCoBan = findViewById(R.id.luongCoBan);
        luongTangCa = findViewById(R.id.luongTangCa);
        phuCap = findViewById(R.id.phuCap);
        tamUng = findViewById(R.id.tamUng);
        bhxh = findViewById(R.id.bhxh);
        tongLuong = findViewById(R.id.tongLuong);
        hinhThucGuiLuong = findViewById(R.id.hinhThucGuiLuong);
        button_themLuong = findViewById(R.id.button_themLuong);
        button_suaLuong = findViewById(R.id.button_suaLuong);
        button_xoaLuong = findViewById(R.id.button_xoaLuong);
        button_xemLuong = findViewById(R.id.button_xemLuong);

        //hàm xử lý sự kiện khi click vào nút tim kiếm
        btn_timKiemLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection = SQLServerHelper.connectionSQLSever();
                String maChamCong = timKiemLuong.getText().toString();
                String maNV = maNVluong.getText().toString();
                String hoVaTen = hoTenLuong.getText().toString();
                String tongNgayCong = soNgayCongLuong.getText().toString();
                ChamCong chamCong = new ChamCong();
                try {
                    chamCong = ChamCong.getmaChamConglist(maChamCong,maNV,hoVaTen,tongNgayCong);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    if (connection != null) {
                        BreakIterator id;
                        //tạo câu lệnh xem thông tin trong bảng PhieuMuon
                        String sqlXem = "select * from ThongTinChamCong where MaChamCong = '" + timKiemLuong.getText().toString() + "'";
                        //tạo đối tượng statement
                        Statement st = connection.createStatement();
                        //thực thi câu lệnh sql
                        ResultSet rs = st.executeQuery(sqlXem);
                        if (rs.next() && chamCong.getMaChamCong().equals(maChamCong)||chamCong.getHoVaTen().equals(hoVaTen)||chamCong.getMaNV().equals(maNV)) {
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            maChamCongLuong.setText(rs.getString(1));
                            maNVluong.setText(rs.getString(2));
                            hoTenLuong.setText(rs.getString(3));
                            soNgayCongLuong.setText(rs.getString(7));
                        }else{
                            Toast.makeText(TrangChuQuanLyTinhLuong.this, "Bạn chưa chấm công nhân viên này", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch(SQLException exception){
                    Log.e("Errol", exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nút thêm
        button_themLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyTinhLuong.this, "Thêm bảng lương thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlThem="insert into ThongTinTinhLuong values ('"+maLuong.getText().toString()+"','"+maChamCongLuong.getText().toString()+"','"+maNVluong.getText().toString()+"','"+hoTenLuong.getText().toString()+"','"+soNgayCongLuong.getText().toString()+"','"+soTangCaLuong.getText().toString()+"','"+phuCap.getText().toString()+"','"+tamUng.getText().toString()+"','"+bhxh.getText().toString()+"','"+hinhThucGuiLuong.getText().toString()+"','"+luongCoBan.getText().toString()+"','"+luongTangCa.getText().toString()+"')";
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


        //hàm xử lý sự kiện khi click vào nút xem lương
        button_xemLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLServerHelper.connectionSQLSever();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh xem thông tin trong bảng PhieuMuon
                        String sqlXem="select * from ThongTinTinhLuong where MaTinhLuong = '"+maLuong.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //thực thi câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXem);
                        while (rs.next()){
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            maLuong.setText(rs.getString(1));
                            maChamCongLuong.setText(rs.getString(2));
                            maNVluong.setText(rs.getString(3));
                            hoTenLuong.setText(rs.getString(4));
                            soNgayCongLuong.setText(rs.getString(5));
                            soTangCaLuong.setText(rs.getString(6));
                            phuCap.setText(rs.getString(7));
                            tamUng.setText(rs.getString(8));
                            bhxh.setText(rs.getString(9));
                            hinhThucGuiLuong.setText(rs.getString(10));
                            luongCoBan.setText(rs.getString(11));
                            luongTangCa.setText(rs.getString(12));
                            tongLuong.setText(rs.getString(13));
                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });
        //hàm xử lý sự kiện khi click vào nút sửa
        button_suaLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyTinhLuong.this, "Sửa bảng lương nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlSua="update ThongTinTinhLuong set SoNgayCong='"+soNgayCongLuong.getText().toString()+"',SoNgayTangCa='"+soTangCaLuong.getText().toString()+"',PhuCap='"+phuCap.getText().toString()+"',TamUng='"+tamUng.getText().toString()+"',BHXH='"+bhxh.getText().toString()+"',HinhThucGui='"+hinhThucGuiLuong.getText().toString()+"',LuongCoBan='"+luongCoBan.getText().toString()+"',LuongTangCa='"+luongTangCa.getText().toString()+"'where MaTinhLuong ='"+maLuong.getText().toString()+"'";
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
        button_xoaLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection=SQLServerHelper.connectionSQLSever();
                Toast.makeText(TrangChuQuanLyTinhLuong.this, "Xóa chấm công nhân viên thành công", Toast.LENGTH_SHORT).show();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        String sqlXoa="delete ThongTinTinhLuong where MaTinhLuong = '"+maLuong.getText().toString()+"'";
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