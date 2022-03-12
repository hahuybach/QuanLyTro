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
import model.Room;

/**
 *
 * @author Bach
 */
public class RoomDBContext extends DBContext {

    public Room getRoom(String rid) {
        try {
            String sql = "select IDPhong, IDLoaiPhong, TrangThai  from Phong where IDPhong = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, rid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Room r = new Room();
                r.setRoomID(rs.getString("IDPhong"));
                r.setRoomTypeID(rs.getInt("IDLoaiPhong"));
                r.setStatus(rs.getBoolean("TrangThai"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "SELECT IDPhong, p.IDLoaiPhong, TrangThai, lp.DonGia  \n"
                    + "FROM PHONG p JOIN LoaiPhong lp\n"
                    + "ON p.IDLoaiPhong = lp.IDLoaiPhong  ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRoomID(rs.getString("IDPhong"));
                r.setRoomTypeID(rs.getInt("IDLoaiPhong"));
                r.setStatus(rs.getBoolean("TrangThai"));
                r.setPrice(rs.getInt("DonGia"));
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    public void changeStatus(String roomID) {
        String sql = "UPDATE [dbo].[Phong]\n"
                + "SET [TrangThai] = ([TrangThai]-1)*(-1)\n"
                + "WHERE [IDPhong] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, roomID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
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

    public void insertRoom(String roomID, int price) {
        String sql_insert_roomType = "BEGIN\n"
                + "   IF NOT EXISTS (SELECT * FROM LoaiPhong \n"
                + "                   WHERE DonGia = ?)\n"
                + "   BEGIN\n"
                + "       INSERT INTO LoaiPhong(DonGia)\n"
                + "	   VALUES (?)\n"
                + "   END\n"
                + "END";
        String sql_get_roomTypeID = "select IDLoaiPhong from LoaiPhong\n"
                + "where DonGia=?";
        String sql_insert_room = "INSERT INTO [dbo].[Phong]\n"
                + "           ([IDPhong]\n"
                + "           ,[IDLoaiPhong]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,'true')";

        try {
            connection.setAutoCommit(false);
            //Add tenant
            PreparedStatement stm_insert_roomType = connection.prepareStatement(sql_insert_roomType);
            stm_insert_roomType.setInt(1, price);
            stm_insert_roomType.setInt(2, price);
            stm_insert_roomType.executeUpdate();

            PreparedStatement stm_get_roomTypeID = connection.prepareStatement(sql_get_roomTypeID);
            stm_get_roomTypeID.setInt(1, price);
            ResultSet rs = stm_get_roomTypeID.executeQuery();
            int roomTypeID = 0;
            if (rs.next()) {
                roomTypeID = rs.getInt("IDLoaiPhong");
            }
            PreparedStatement stm_insert_room = connection.prepareStatement(sql_insert_room);
            stm_insert_room.setString(1, roomID);
            stm_insert_room.setInt(2, roomTypeID);
            stm_insert_room.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }

    public void deleteRoom(String roomID) {
        String sql = "DELETE FROM [dbo].[Phong]\n"
                + "      WHERE IDPhong = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, roomID);
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
    public Room getRoomAndPrice(String roomID) {
        String sql = "SELECT IDPhong, p.IDLoaiPhong, TrangThai, lp.DonGia \n" +
"                       FROM PHONG p JOIN LoaiPhong lp\n" +
"                       ON p.IDLoaiPhong = lp.IDLoaiPhong  \n" +
"                       WHERE IDPhong = ? ";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, roomID);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Room r = new Room();
                r.setRoomID(rs.getString("IDPhong"));
                r.setRoomTypeID(rs.getInt("IDLoaiPhong"));
                r.setStatus(rs.getBoolean("TrangThai"));
                r.setPrice(rs.getInt("DonGia"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
        return null;
    }
    public void updateRoom(String roomID, int price, boolean status){
        String sql_insert_roomType = "BEGIN\n"
                + "   IF NOT EXISTS (SELECT * FROM LoaiPhong \n"
                + "                   WHERE DonGia = ?)\n"
                + "   BEGIN\n"
                + "       INSERT INTO LoaiPhong(DonGia)\n"
                + "	   VALUES (?)\n"
                + "   END\n"
                + "END";
        String sql_get_roomTypeID = "select IDLoaiPhong from LoaiPhong\n"
                + "where DonGia=?";
        String sql_update_room = "UPDATE [dbo].[Phong]\n" +
                                "   SET " +
                                "      [IDLoaiPhong] = ?\n" +
                                "      ,[TrangThai] = ?\n" +
                                " WHERE IDPhong = ? ";
        try {
            connection.setAutoCommit(false);
            //Add tenant
            PreparedStatement stm_insert_roomType = connection.prepareStatement(sql_insert_roomType);
            stm_insert_roomType.setInt(1, price);
            stm_insert_roomType.setInt(2, price);
            stm_insert_roomType.executeUpdate();

            PreparedStatement stm_get_roomTypeID = connection.prepareStatement(sql_get_roomTypeID);
            stm_get_roomTypeID.setInt(1, price);
            ResultSet rs = stm_get_roomTypeID.executeQuery();
            int roomTypeID = 0;
            if (rs.next()) {
                roomTypeID = rs.getInt("IDLoaiPhong");
            }
            PreparedStatement stm_update_room = connection.prepareStatement(sql_update_room);
            stm_update_room.setInt(1, roomTypeID);
            stm_update_room.setBoolean(2, status);
            stm_update_room.setString(3, roomID);
            stm_update_room.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(TenantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
        }
    }
}
