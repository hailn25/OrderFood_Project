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
public class ShipperDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertShipper(String name, String phone, String accountId) throws SQLException {
        try {
            String sql = "MERGE INTO [dbo].[Shipper] AS target\n"
                    + "USING (SELECT 1 AS dummy) AS source\n"
                    + "ON target.[AccountId] = ?\n"
                    + "\n"
                    + "WHEN MATCHED THEN\n"
                    + "    UPDATE SET\n"
                    + "        target.[Name] = ?,\n"
                    + "        target.[Phone] = ?\n"
                    + "\n"
                    + "WHEN NOT MATCHED BY TARGET THEN\n"
                    + "    INSERT ([Name], [Phone], [AccountId])\n"
                    + "    VALUES (?, ?, ?);";

            conn = new DBContext().getConnection(); // Assuming DBContext handles connection properly
            ps = conn.prepareStatement(sql);

            // Parameters for both UPDATE and INSERT parts of the MERGE statement
            ps.setString(1, accountId);
            ps.setString(2, name);
            ps.setString(3, phone);
            

            // Parameters specific to INSERT part of the MERGE statement
            ps.setString(4, name);
            ps.setString(5, phone);
            ps.setString(6, accountId);
            

            ps.executeUpdate();

            // Close PreparedStatement and Connection properly
            ps.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShipperDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        try {
            ShipperDAO dao = new ShipperDAO();
            dao.insertShipper("Huy", "0981123123", "6");
        } catch (SQLException ex) {
            Logger.getLogger(ShipperDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
