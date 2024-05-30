package com.example.sellingmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

public class XemNhanVien extends AppCompatActivity {

    //khai báo biến giao diện
    ImageButton btn_timKiemNV;
    TextView timKiemNV,xemMaNV,xemHoTen,xemSDT,xemGioiTinh,xemCCCD,xemNgaySinh,xemQueQuan,xemBangCap;
    Button button_backXemNv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_nhan_vien);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//ánh xạ id
        btn_timKiemNV = findViewById(R.id.btn_timKiemNV);
        timKiemNV = findViewById(R.id.timKiemNV);
        xemMaNV = findViewById(R.id.xemMaNV);
        xemHoTen = findViewById(R.id.xemHoTen);
        xemSDT = findViewById(R.id.xemSDT);
        xemGioiTinh = findViewById(R.id.xemGioiTinh);
        xemCCCD = findViewById(R.id.xemCCCD);
        xemNgaySinh = findViewById(R.id.xemNgaySinh);
        xemQueQuan = findViewById(R.id.xemQueQuan);
        xemBangCap = findViewById(R.id.xemBangCap);
        button_backXemNv = findViewById(R.id.button_backXemNv);

        //hàm xử lý sự kiện khi click vào nút tìm kiếm
        btn_timKiemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kết nối csdl
                Connection connection= SQLServerHelper.connectionSQLSever();
                try {
                    if(connection!=null){
                        BreakIterator id;
                        //tạo câu lệnh xem thông tin trong bảng PhieuMuon
                        String sqlXem="select * from ThongTinNhanVien where MaNV = '"+timKiemNV.getText().toString()+"'";
                        //tạo đối tượng statement
                        Statement st=connection.createStatement();
                        //thực thi câu lệnh sql
                        ResultSet rs=st.executeQuery(sqlXem);
                        while (rs.next()){
                            //ánh xạ các thông tin vưà truy xuất từ câu lệnh sql lên id của các thành phần giao diện tương ứng
                            xemMaNV.setText(rs.getString(1));
                            xemHoTen.setText(rs.getString(2));
                            xemSDT.setText(rs.getString(3));
                            xemGioiTinh.setText(rs.getString(4));
                            xemCCCD.setText(rs.getString(5));
                            xemNgaySinh.setText(rs.getString(6));
                            xemQueQuan.setText(rs.getString(7));
                            xemBangCap.setText(rs.getString(8));

                        }
                    }
                } catch (SQLException exception) {
                    Log.e("Errol",exception.getMessage());
                }
            }
        });

        //hàm xử lý sự kiện khi click vào nút quay lại
        button_backXemNv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(XemNhanVien.this, TrangChuQuanLyNhanVien.class);
                startActivity(myintent);
            }
        });
    }
}