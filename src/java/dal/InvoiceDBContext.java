/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Invoice;
import model.Tenant;

/**
 *
 * @author Bach
 */
public class InvoiceDBContext extends DBContext{
    public Invoice getInvoice(int month,int year, String roomID){
        String sql = "DECLARE @SoDienCu int, @SoNuocCu int,@IDSuDung int, @IDSuDungCu int\n" +
"					SET @IDSuDung = (Select sd.IDSuDung from ThuePhong tp join HoaDon hd on tp.ID = hd.IDThuePhong join SuDung sd on hd.IDSuDung = sd.IDSuDung WHERE ThangSuDung = ? and NamSuDung = ? and IDPhong = ?)\n" +
"					SET @IDSuDungCu = (Select sd.IDSuDung from ThuePhong tp join HoaDon hd on tp.ID = hd.IDThuePhong join SuDung sd on hd.IDSuDung = sd.IDSuDung WHERE ThangSuDung = ? and NamSuDung = ? and IDPhong = ?)\n" +
"                    SET @SoDienCu = (SELECT [SoDien] FROM SuDung\n" +
"                    WHERE IDSuDung = @IDSuDungCu)\n" +
"                    SET @SoNuocCu = (SELECT [SoNuoc] FROM SuDung \n" +
"                    WHERE IDSuDung = @IDSuDungCu)\n" +
"                    SELECT p.IDPhong, ThangSuDung,NamSuDung,SoDien,@SoDienCu as SoDienCu,Dien,((SoDien-@SoDienCu)*Dien) as TienDien,SoXe,PhiGuiXe, (SoXe*PhiGuiXe) as TienXe,SoNuoc,@SoNuocCu as SoNuocCu,Nuoc, ((SoNuoc-@SoNuocCu)*Nuoc) as TienNuoc,TH_Cap,SoTV, (SoTV*TH_Cap) as TienTHCap,DonGia,VeSinh,Internet,PhiDichVu, ((SoDien-@SoDienCu)*Dien+(SoXe*PhiGuiXe)+(SoNuoc-@SoNuocCu)*Nuoc+(SoTV*TH_Cap)+DonGia+VeSinh+PhiDichVu+Internet) as Total, DaThanhToan \n" +
"                    FROM SuDung sd JOIN DichVu dv\n" +
"                    ON sd.IDDichVu = dv.IDDichVu\n" +
"                    JOIN HoaDon hd\n" +
"                    ON sd.IDSuDung = hd.IDSuDung\n" +
"                    JOIN ThuePhong tp\n" +
"                    ON tp.ID = hd.IDThuePhong\n" +
"                    JOIN Phong p\n" +
"                    ON tp.IDPhong = p.IDPhong \n" +
"                    JOIN LoaiPhong lp\n" +
"                    ON p.IDLoaiPhong = lp.IDLoaiPhong\n" +
"                    WHERE hd.IDSuDung = @IDSuDung" ;

        PreparedStatement stm = null;
        
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(3, roomID);
            stm.setString(6,roomID);
            if(month == 1){
                stm.setInt(1,month);
                stm.setInt(2,year);
                stm.setInt(4,12);
                stm.setInt(5,year-1);
            }else{
                stm.setInt(1,month);
                stm.setInt(2,year);
                stm.setInt(4,month-1);
                stm.setInt(5,year);
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Invoice i = new Invoice();
                i.setRoomID(rs.getString("IDPhong"));
                i.setMonth(rs.getInt("ThangSuDung"));
                i.setYear(rs.getInt("NamSuDung"));
                i.setElectric_num(rs.getInt("SoDien"));
                i.setOld_electric_num(rs.getInt("SoDienCu"));
                i.setElectric_price(rs.getInt("Dien"));
                i.setBike_num(rs.getInt("SoXe"));
                i.setBike_price(rs.getInt("PhiGuiXe"));
                i.setWater_num(rs.getInt("SoNuoc"));
                i.setOld_water_num(rs.getInt("SoNuocCu"));
                i.setWater_price(rs.getInt("Nuoc"));
                i.setTv_num(rs.getInt("SoTV"));
                i.setCable_tv_price(rs.getInt("TH_Cap"));
                i.setRoomPrice(rs.getInt("DonGia"));
                i.setTotal(rs.getInt("Total"));
                i.setService_price(rs.getInt("PhiDichVu"));
                i.setInternet_price(rs.getInt("Internet"));
                i.setCleaning_price(rs.getInt("VeSinh"));
                i.setPaid(rs.getInt("DaThanhToan"));
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
    public void insertInvoice(Invoice i){
        String insert_used_sql="INSERT INTO [dbo].[SuDung]\n" +
"           ([IDDichVu]\n" +
"           ,[SoDien]\n" +
"           ,[SoNuoc]\n" +
"           ,[SoXe]\n" +
"           ,[ThangSuDung]\n" +
"           ,[NamSuDung]\n" +
"           ,[SoTV])\n" +
"     VALUES\n" +
"           (?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?\n" +
"           ,?)";
        String getIDSuDung= "SELECT MAX(IDSuDung) as 'MAXID' FROM SuDung";
        
        String insert_invoice_sql=" DECLARE @ID INT\n" +
                        "SET @ID = (SELECT TOP 1 ID FROM ThuePhong\n" +
                        "WHERE IDPhong = ?\n" +
                        "ORDER BY ID)"+
                        "INSERT INTO [dbo].[HoaDon]\n" +
                        "           ([IDThuePhong]\n" +
                        "           ,[IDSuDung]\n" +
                        "           ,[TongTien]\n" +
                        "           ,[DaThanhToan])\n" +
                        "     VALUES\n" +
                        "           (@ID\n" +
                        "           ,?\n" +
                        "           ,?\n" +
                        "           ,?)";
        
        
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_insert_used = connection.prepareStatement(insert_used_sql);
            stm_insert_used.setInt(1, 1);
            stm_insert_used.setInt(2, i.getElectric_num());
            stm_insert_used.setInt(3, i.getWater_num());
            stm_insert_used.setInt(4,i.getBike_num());
            stm_insert_used.setInt(5, i.getMonth());
            stm_insert_used.setInt(6, i.getYear());
            stm_insert_used.setInt(7, i.getTv_num());
            stm_insert_used.executeUpdate();
            
            PreparedStatement stm_getIDSuDung = connection.prepareStatement(getIDSuDung);
            ResultSet rs = stm_getIDSuDung.executeQuery();
            int IDSuDung=0;
            if(rs.next()){
                IDSuDung = rs.getInt("MAXID");
            }
            

            PreparedStatement stm_insert_invoice = connection.prepareStatement(insert_invoice_sql);
            stm_insert_invoice.setString(1, i.getRoomID());
            stm_insert_invoice.setInt(2, IDSuDung);
            stm_insert_invoice.setInt(3, i.getTotal());
            stm_insert_invoice.setInt(4, 0);
            stm_insert_invoice.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
    public void deleteInvoice(Invoice i){
        String sql = "DECLARE @IDHD int,@IDSD int\n"+
                    "Select @IDHD = hd.IDHoaDon,@IDSD = sd.IDSuDung\n" +
                    "from HoaDon hd \n" +
                    "join ThuePhong tp \n" +
                    "on hd.IDThuePhong = tp.ID\n" +
                    "join SuDung sd\n" +
                    "on hd.IDSuDung = sd.IDSuDung\n" +
                    "where IDPhong = ? and ThangSuDung = ? and NamSuDung=?\n" +
                    "Delete from HoaDon where IDHoaDon = @IDHD\n" +
                    "Delete from SuDung where IDSuDung = @IDSD";
                try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, i.getRoomID());
            stm.setInt(2, i.getMonth());
            stm.setInt(3, i.getYear());
            stm.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
    public void updateInvoice(Invoice i){
        String sql="Declare @IDSuDung int\n" +
"SET @IDSuDung = (Select sd.IDSuDung \n" +
"from SuDung sd \n" +
"join HoaDon hd\n" +
"on sd.IDSuDung = hd.IDSuDung\n" +
"join ThuePhong tp\n" +
"on hd.IDThuePhong = tp.ID\n" +
"WHERE ThangSuDung = ? and NamSuDung=? and IDPhong = ?)\n" +
"UPDATE [dbo].[SuDung]\n" +
"   SET \n" +
"       [SoDien] = ?\n" +
"      ,[SoNuoc] = ?\n" +
"      ,[SoXe] = ?\n" +
"      ,[SoTV] = ?\n" +
" WHERE IDSuDung = @IDSuDung";
        try{
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i.getMonth());
            stm.setInt(2, i.getYear());
            stm.setString(3, i.getRoomID());
            stm.setInt(4, i.getElectric_num());
            stm.setInt(5,i.getWater_num());
            stm.setInt(6,i.getBike_num());
            stm.setInt(7,i.getTv_num());
            stm.execute();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
    public void updatePaid(Invoice i){
        String sql = "DECLARE @IDHD int,@IDSD int\n"+
                    "Select @IDHD = hd.IDHoaDon,@IDSD = sd.IDSuDung\n" +
                    "from HoaDon hd \n" +
                    "join ThuePhong tp \n" +
                    "on hd.IDThuePhong = tp.ID\n" +
                    "join SuDung sd\n" +
                    "on hd.IDSuDung = sd.IDSuDung\n" +
                    "where IDPhong = ? and ThangSuDung = ? and NamSuDung=?\n" +
                    "UPDATE [dbo].[HoaDon]\n" +
                    "SET \n" +
                    "[DaThanhToan] = ?\n" +
                    " WHERE IDHoaDon = @IDHD";
                try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, i.getRoomID());
            stm.setInt(2, i.getMonth());
            stm.setInt(3, i.getYear());
            stm.setInt(4, i.getPaid());
            stm.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
}
