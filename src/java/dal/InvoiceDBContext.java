/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import model.Invoice;

/**
 *
 * @author Bach
 */
public class InvoiceDBContext extends DBContext{
    public Invoice getInvoice(int month,int year, int idPhong){
        String sql = "DECLARE @SoDienCu int, @SoNuocCu int\n" +
"SET @SoDienCu = (SELECT [SoDien] FROM SuDung\n" +
"WHERE ThangSuDung=? and NamSuDung =?)\n" +
"SET @SoNuocCu = (SELECT [SoNuoc] FROM SuDung \n" +
"WHERE ThangSuDung=? and NamSuDung =?) \n" +
"SELECT ThangSuDung,NamSuDung,SoDien,@SoDienCu as SoDienCu,Dien,((SoDien-@SoDienCu)*Dien) as TienDien,SoXe,PhiGuiXe, (SoXe*PhiGuiXe) as TienXe,SoNuoc,@SoNuocCu as SoNuocCu, ((SoNuoc-@SoNuocCu)*Nuoc) as TienNuoc, (SoTV*TH_Cap) as TienTHCap\n" +
"FROM SuDung sd JOIN DichVu dv\n" +
"ON sd.IDDichVu = dv.IDDichVu\n" +
"JOIN HoaDon hd\n" +
"ON sd.IDSuDung = hd.IDSuDung\n" +
"JOIN ThuePhong tp\n" +
"ON tp.ID = hd.IDThuePhong\n" +
"WHERE ThangSuDung = ? and NamSuDung = ?\n" +
"and IDPhong= ?";
       return null; 
    }
}
