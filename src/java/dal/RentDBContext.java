/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rent;
import model.Tenant;

/**
 *
 * @author Bach
 */
public class RentDBContext extends DBContext {

    public Rent getRent(int IdKhachHang) {
        try {
            String sql = "SELECT ID\n"
                    + "      ,IDKhachHang\n"
                    + "      ,IDPhong\n"
                    + "      ,NgayThue\n"
                    + "      ,NgayTra\n"
                    + "FROM [dbo].[ThuePhong]\n"
                    + "WHERE IDKhachHang = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, IdKhachHang);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Rent r = new Rent();
                r.setId(rs.getInt("ID"));
                r.setTenantId(rs.getInt("IDKhachHang"));
                r.setRoomId(rs.getString("IDPhong"));
                r.setStartDate(rs.getDate("NgayThue"));
                r.setEndDate(rs.getDate("NgayTra"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Tenant> getTenantByRoomID(String rid) {
        ArrayList<Tenant> tenants = new ArrayList<>();
        String sql = "SELECT kh.IDKhachHang, HoVaTen, CMND_CanCuoc, DienThoai, QueQuan, HKTT, GioiTinh, NamSinh, NgayThue, NgayTra\n"
                + "FROM KhachHang kh JOIN ThuePhong tp\n"
                + "ON kh.IDKhachHang = tp.IDKhachHang\n"
                + "WHERE tp.IDPhong = ?";
        PreparedStatement stm = null;
        try {

            stm = connection.prepareStatement(sql);
            stm.setString(1, rid);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tenant t = new Tenant();
                t.setId(rs.getInt("IDKhachHang"));
                t.setFullName(rs.getString("HoVaTen"));
                t.setIdentificationNumber(rs.getString("CMND_CanCuoc"));
                t.setPhoneNumber(rs.getString("DienThoai"));
                t.setPoB(rs.getString("QueQuan"));
                t.setPermanentResidence(rs.getString("HKTT"));
                t.setGender(rs.getBoolean("GioiTinh"));
                t.setDoB(rs.getDate("NamSinh"));
                t.setStartDate(rs.getDate("NgayThue"));
                t.setEndDate(rs.getDate("NgayThue"));
                tenants.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return tenants;
    }
}
