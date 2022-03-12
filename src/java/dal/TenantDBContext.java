/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tenant;

/**
 *
 * @author Bach
 */
public class TenantDBContext extends DBContext {

    public ArrayList<Tenant> getTenants() {
        ArrayList<Tenant> tenants = new ArrayList<>();
        try {
            String sql = "select IDKhachHang, HoVaTen, CMND_CanCuoc, DienThoai, QueQuan, HKTT, GioiTinh, NamSinh\n"
                    + "from KhachHang";
            PreparedStatement stm = connection.prepareStatement(sql);
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
                tenants.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tenants;
    }

    public Tenant getTenantByIdenNumber(String IdenNumber) {
        try {
            String sql = "select IDKhachHang, HoVaTen, CMND_CanCuoc, DienThoai, QueQuan, HKTT, GioiTinh, NamSinh\n"
                    + "from KhachHang where IdenNumber = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, IdenNumber);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Tenant t = new Tenant();
                t.setId(rs.getInt("IDKhachHang"));
                t.setFullName(rs.getString("HoVaTen"));
                t.setIdentificationNumber(rs.getString("CMND_CanCuoc"));
                t.setPhoneNumber(rs.getString("DienThoai"));
                t.setPoB(rs.getString("QueQuan"));
                t.setPermanentResidence(rs.getString("HKTT"));
                t.setGender(rs.getBoolean("GioiTinh"));
                t.setDoB(rs.getDate("NamSinh"));
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Tenant getTenantByID(int id) {
        String sql = "select IDKhachHang, HoVaTen, CMND_CanCuoc, DienThoai, QueQuan, HKTT, GioiTinh, NamSinh\n"
                    + "from KhachHang where IDKhachHang = ?";
        PreparedStatement stm = null;
        try {
            
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Tenant t = new Tenant();
                t.setId(rs.getInt("IDKhachHang"));
                t.setFullName(rs.getString("HoVaTen"));
                t.setIdentificationNumber(rs.getString("CMND_CanCuoc"));
                t.setPhoneNumber(rs.getString("DienThoai"));
                t.setPoB(rs.getString("QueQuan"));
                t.setPermanentResidence(rs.getString("HKTT"));
                t.setGender(rs.getBoolean("GioiTinh"));
                t.setDoB(rs.getDate("NamSinh"));
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
        return null;
    }

    public int getMaxID(){
        String sql = "select MAX(IDKhachHang) as 'MAXID'\n" +
                    "from KhachHang";

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("MAXID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
        return 0;
    }
    public void deleteTenant(Tenant t) {
        String sql = "DELETE FROM [dbo].[KhachHang]\n"
                + "WHERE IDKhachHang = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, t.getId());
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
    public void delete(Tenant t){
        String sql_delete_tenant = "DELETE FROM [dbo].[KhachHang]\n"
                + "WHERE IDKhachHang = ?";
        String sql_delete_rent = "DELETE FROM [dbo].[ThuePhong]\n" +
                "WHERE IDKhachHang = ?";
        try {
            connection.setAutoCommit(false);
            //Add tenant
            PreparedStatement stm_delete_tenant = connection.prepareStatement(sql_delete_tenant);
            stm_delete_tenant.setInt(1, t.getId());
            stm_delete_tenant.executeUpdate();

            PreparedStatement stm_delete_rent = connection.prepareStatement(sql_delete_rent);
            stm_delete_rent.setInt(1, t.getId());
            stm_delete_rent.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }

    public void insertTenant(Tenant t) {
        String sql_insert_tenant = "INSERT INTO [dbo].[KhachHang]\n"
                + "           ([HoVaTen]\n"
                + "           ,[CMND_CanCuoc]\n"
                + "           ,[DienThoai]\n"
                + "           ,[QueQuan]\n"
                + "           ,[HKTT]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[NamSinh])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql_insert_tenant);
            stm.setString(1, t.getFullName());
            stm.setString(2, t.getIdentificationNumber());
            stm.setString(3, t.getPhoneNumber());
            stm.setString(4, t.getPoB());
            stm.setString(5, t.getPermanentResidence());
            stm.setBoolean(6, t.getGender());
            stm.setDate(7, t.getDoB());
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

    public void insertRent(int tid, String rid, Date startDate, Date endDate) {
        String sql_insert_rent = "INSERT INTO [dbo].[ThuePhong]\n"
                + "           ([IDKhachHang]\n"
                + "           ,[IDPhong]\n"
                + "           ,[NgayThue]\n"
                + "           ,[NgayTra])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql_insert_rent);
            stm.setInt(1, tid);
            stm.setString(2, rid);
            stm.setDate(3, startDate);
            stm.setDate(4, endDate);
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
    
    public void insert(Tenant t, String rid, Date startDate, Date endDate)
    {
        String sql_insert_tenant = "INSERT INTO [dbo].[KhachHang]\n"
                + "           ([HoVaTen]\n"
                + "           ,[CMND_CanCuoc]\n"
                + "           ,[DienThoai]\n"
                + "           ,[QueQuan]\n"
                + "           ,[HKTT]\n"
                + "           ,[GioiTinh]\n"
                + "           ,[NamSinh])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        String sql_getMaxID = "select MAX(IDKhachHang) as 'MAXID'\n" +
                    "from KhachHang";
        String sql_insert_rent = "INSERT INTO [dbo].[ThuePhong]\n"
                + "           ([IDKhachHang]\n"
                + "           ,[IDPhong]\n"
                + "           ,[NgayThue]\n"
                + "           ,[NgayTra])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            connection.setAutoCommit(false);
            //Add tenant
            PreparedStatement stm_insert_tenant = connection.prepareStatement(sql_insert_tenant);
            stm_insert_tenant.setString(1, t.getFullName());
            stm_insert_tenant.setString(2, t.getIdentificationNumber());
            stm_insert_tenant.setString(3, t.getPhoneNumber());
            stm_insert_tenant.setString(4, t.getPoB());
            stm_insert_tenant.setString(5, t.getPermanentResidence());
            stm_insert_tenant.setBoolean(6, t.getGender());
            stm_insert_tenant.setDate(7, t.getDoB());
            stm_insert_tenant.executeUpdate();
            //get ID of the tenant
            PreparedStatement stm_getMaxID = connection.prepareStatement(sql_getMaxID);
            ResultSet rs = stm_getMaxID.executeQuery();
            int tid=0;
            if (rs.next()) {
                tid = rs.getInt("MAXID");
            }
            PreparedStatement stm_insert_rent = connection.prepareStatement(sql_insert_rent);
            stm_insert_rent.setInt(1, tid);
            stm_insert_rent.setString(2, rid);
            stm_insert_rent.setDate(3, startDate);
            stm_insert_rent.setDate(4, endDate);
            stm_insert_rent.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
    public void updateTenant(Tenant t , int IDKhachHang) {
        String sql = "UPDATE [dbo].[KhachHang]\n" +
                "   SET [HoVaTen] = ?\n" +
                "      ,[CMND_CanCuoc] = ?\n" +
                "      ,[DienThoai] = ?\n" +
                "      ,[QueQuan] = ?\n" +
                "      ,[HKTT] = ?\n" +
                "      ,[GioiTinh] = ?\n" +
                "      ,[NamSinh] = ?\n" +
                " WHERE IDKhachHang = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, t.getFullName());
            stm.setString(2, t.getIdentificationNumber());
            stm.setString(3, t.getPhoneNumber());
            stm.setString(4, t.getPoB());
            stm.setString(5, t.getPermanentResidence());
            stm.setBoolean(6, t.getGender());
            stm.setDate(7, t.getDoB());
            stm.setInt(8, IDKhachHang);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(stm != null)
                try {
                    stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(connection != null)
                try {
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void update(Tenant t,String rid, Date startDate, Date endDate){
        String sql_update_tenant = "UPDATE [dbo].[KhachHang]\n" +
                                    "   SET [HoVaTen] = ?\n" +
                                    "      ,[CMND_CanCuoc] = ?\n" +
                                    "      ,[DienThoai] = ?\n" +
                                    "      ,[QueQuan] = ?\n" +
                                    "      ,[HKTT] = ?\n" +
                                    "      ,[GioiTinh] = ?\n" +
                                    "      ,[NamSinh] = ?\n" +
                                    " WHERE IDKhachHang = ?";
        String sql_update_rent = "UPDATE [dbo].[ThuePhong]\n" +
                                "   SET [IDKhachHang] = ?\n" +
                                "      ,[IDPhong] = ?\n" +
                                "      ,[NgayThue] = ?\n" +
                                "      ,[NgayTra] = ?\n" +
                                " WHERE IDKhachHang = ?";
        try {
            connection.setAutoCommit(false);
            //Add tenant
            PreparedStatement stm_update_tenant = connection.prepareStatement(sql_update_tenant);
            stm_update_tenant.setString(1, t.getFullName());
            stm_update_tenant.setString(2, t.getIdentificationNumber());
            stm_update_tenant.setString(3, t.getPhoneNumber());
            stm_update_tenant.setString(4, t.getPoB());
            stm_update_tenant.setString(5, t.getPermanentResidence());
            stm_update_tenant.setBoolean(6, t.getGender());
            stm_update_tenant.setDate(7, t.getDoB());
            stm_update_tenant.setInt(8,t.getId());
            stm_update_tenant.executeUpdate();
            //get ID of the tenant

            PreparedStatement stm_update_rent = connection.prepareStatement(sql_update_rent);
            stm_update_rent.setInt(1, t.getId());
            stm_update_rent.setString(2, rid);
            stm_update_rent.setDate(3, startDate);
            stm_update_rent.setDate(4, endDate);
            stm_update_rent.setInt(5,t.getId());
            stm_update_rent.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
}
