package Datamanagement;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Logins {
    String TenDangNhap;
    String MatKhau;
    public Logins(){
        TenDangNhap = "";
        MatKhau = "";
    };
    public Logins(String user, String pass) {
        TenDangNhap = user;
        this.MatKhau = pass;
    }

    public static Logins getuserlist(String user,String passWords) throws SQLException {
        Connection connection = SQLServerHelper.connectionSQLSever();
        Logins logins = new Logins();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from ThongTinDangNhap where TenDangNhap = '" + user + "' and MatKhau = '" + passWords +"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            logins = new Logins(
                    rs.getString(1).trim(),
                    rs.getString(2).trim());// Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return logins;
    }
    public String getUser() {
        return TenDangNhap;
    }

    public void setUser(String user) {
        TenDangNhap = user;
    }

    public String getPass() {
        return MatKhau;
    }

    public void setPass(String pass) {
        this.MatKhau = pass;
    }
}
