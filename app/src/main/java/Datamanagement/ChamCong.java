package Datamanagement;

import com.example.sellingmanagement.SQLServerManagement.SQLServerHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChamCong {
    String MaChamCong;
    String MaNV;
    String HoVaTen;

    String TongNgayCong;

    public ChamCong(){

        HoVaTen = "";
        MaNV = "";
        MaChamCong = "";
        TongNgayCong ="";
    };
    public ChamCong(String maNV,String hoVaTen,String maChamCong,String tongNgayCong) {
        HoVaTen = hoVaTen;
        MaNV = maNV;
        MaChamCong = maChamCong;
        TongNgayCong = tongNgayCong;
    }

    public static ChamCong getmaChamConglist(String maChamCong,String maNV,String hoVaTen,String tongNgayCong) throws SQLException {
        Connection connection = SQLServerHelper.connectionSQLSever();
        ChamCong chamCong = new ChamCong();
        Statement statement = connection.createStatement();// Tạo đối tượng Statement.
        String sql = "select * from ThongTinChamCong where MaChamCong = '" + maChamCong +"' and MaNV = '" + maNV + "' and HoVaTen = '" + hoVaTen +"' and TongNgayCong = '" + tongNgayCong+"'";
        // Thực thi câu lệnh SQL trả về đối tượng ResultSet. // Mọi kết quả trả về sẽ được lưu trong ResultSet
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            chamCong = new ChamCong(
                    rs.getString(1).trim(),
                    rs.getString(2).trim(),
                    rs.getString(3).trim(),
                    rs.getString(7).trim());
            // Đọc dữ liệu từ ResultSet)
        }
        statement.close();
        connection.close();// Đóng kết nối
        return chamCong;
    }
    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaChamCong() {
        return MaChamCong;
    }

    public void setMaChamCong(String maChamCong) {
        MaChamCong = maChamCong;
    }

    public String getTongNgayCong() {
        return TongNgayCong;
    }

    public void setTongNgayCong(String tongNgayCong) {
        TongNgayCong = tongNgayCong;
    }

}
