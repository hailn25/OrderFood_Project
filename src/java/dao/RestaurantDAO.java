/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vu Huy
 */
public class RestaurantDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int getRestaurantIdByAccountId(int accountId) {
        int restaurantId = 0;
        try {
            String sql = "SELECT        RestaurantId\n"
                    + "FROM            Restaurant\n"
                    + "WHERE AccountId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                restaurantId = rs.getInt(1);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantId;
    }

    public void insertRestaurant(String name, String email, String phone, String address, String accountId) throws SQLException {
        try {
            String sql = "MERGE [dbo].[Restaurant] AS target\n"
                    + "USING (SELECT 1 AS dummy) AS source\n"
                    + "ON target.[AccountId] = ?\n"
                    + "\n"
                    + "WHEN MATCHED THEN \n"
                    + "    UPDATE SET \n"
                    + "        target.[Name] = ?, \n"
                    + "        target.[Email] = ?, \n"
                    + "        target.[Phone] = ?, \n"
                    + "        target.[Address] = ?\n"
                    + "\n"
                    + "WHEN NOT MATCHED BY TARGET THEN\n"
                    + "    INSERT ([Name], [Email], [Phone], [Address], [AccountId])\n"
                    + "    VALUES (?, ?, ?, ?, ?);";

            conn = new DBContext().getConnection(); // Assuming DBContext handles connection properly
            ps = conn.prepareStatement(sql);

            // Parameters for both UPDATE and INSERT parts of the MERGE statement
            ps.setString(1, accountId);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, address);

            // Parameters specific to INSERT part of the MERGE statement
            ps.setString(6, name);
            ps.setString(7, email);
            ps.setString(8, phone);
            ps.setString(9, address);
            ps.setString(10, accountId);

            ps.executeUpdate();

            // Close PreparedStatement and Connection properly
            ps.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        
        try {
            RestaurantDAO dao = new RestaurantDAO();
            dao.insertRestaurant("của  Huy", "vuhuy@gmail.com", "0981222222", "Hà Nội", "7");
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
