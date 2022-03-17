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
import model.Expense;
import model.Revenue;
import model.Room;

/**
 *
 * @author Bach
 */
public class BalanceDBContext extends DBContext{
    public Revenue getRevenue(int month, int year){
        try {
            String sql = "select SUM(DaThanhToan) as TongThu\n" +
"from HoaDon hd join\n" +
"ThuePhong tp\n" +
"on hd.IDThuePhong = tp.ID\n" +
"join SuDung sd\n" +
"on hd.IDSuDung = sd.IDSuDung\n" +
"Where ThangSuDung = ? and NamSuDung = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, year);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Revenue r = new Revenue();
                r.setRevenue(rs.getInt("TongThu"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BalanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<Expense> getExpenses(int month,int year){
        ArrayList<Expense> expenses = new ArrayList<>();
        String sql = "SELECT [IDChi]\n" +
"      ,[TenChiTieu]\n" +
"      ,[SoTien]\n" +
"      ,[Thang]\n" +
"      ,[Nam]\n" +
"  FROM [ThueTro].[dbo].[Chi]\n"+
"WHERE Thang = ? and Nam= ?";
        
         try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, month);
            stm.setInt(2, year);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Expense e = new Expense();
                e.setId(rs.getInt("IDChi"));
                e.setName(rs.getString("TenChiTieu"));
                e.setPrice(rs.getInt("SoTien"));
                e.setMonth(rs.getInt("Thang"));
                e.setYear(rs.getInt("Nam"));
                expenses.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BalanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return expenses;
    }
    public void insertExpense(Expense e) {
        String sql_insert_expense = "INSERT INTO [dbo].[Chi]\n" +
"           ([TenChiTieu]\n" +
"           ,[SoTien]\n" +
"           ,[Thang]\n" +
"           ,[Nam])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql_insert_expense);
            stm.setString(1, e.getName());
            stm.setInt(2, e.getPrice());
            stm.setInt(3, e.getMonth());
            stm.setInt(4, e.getYear());
            stm.executeUpdate();
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

    }
}
