package Datamanagement;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NhanVien {
    String MaNv;

    public NhanVien(){
        MaNv = "";
    };
    public NhanVien(String maNv) {
        MaNv = maNv;
    }

    public static NhanVien getmaNvlist(String maNv) throws SQLException {
        Connection connection = SQLServerHelper.connectionSQLSever();
        NhanVien nhanVien = new NhanVien();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from ThongTinNhanVien where MaNV = '" + maNv +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            nhanVien = new NhanVien(
                    rs.getString(1).trim());
                    // Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return nhanVien;
    }
    public String getUser() {
        return MaNv;
    }

    public void setUser(String maNv) {
        MaNv = maNv;
    }


}
