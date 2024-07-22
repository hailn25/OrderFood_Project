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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DeliveryIssue;

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

    public void insertShipperMessage(int oderId, int shipperId, String issueDescription) throws SQLException {
        LocalDate curDate = LocalDate.now();
        java.sql.Date sqlDate = java.sql.Date.valueOf(curDate);
        try {
            String sql = "  INSERT INTO DeliveryIssue ( OrderId, ShipperId, IssueDescription, IssueDate)\n"
                    + "VALUES ( ?, ?, ?, ?);";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, oderId);
            ps.setInt(2, shipperId);
            ps.setString(3, issueDescription);
            ps.setDate(4, sqlDate);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShipperDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getShipperId(int accountId) {
        String sql = "  Select Shipper.ShipperId \n"
                + "  from Shipper\n"
                + "  where Shipper.AccountId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public ArrayList<DeliveryIssue> getAllOrderCancel(int shipperId) {
        ArrayList<DeliveryIssue> listDeliveryIssue = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT [Order].OrderId, [Order].Name, [Order].Phone, [Order].Address, [Order].CreateDate, [Order].TotalMoney, OrderStatus.Status, DeliveryIssue.IssueDescription, DeliveryIssue.IssueDate\n"
                    + "FROM DeliveryIssue INNER JOIN\n"
                    + "[Order] ON DeliveryIssue.OrderId = [Order].OrderId INNER JOIN\n"
                    + "OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId\n"
                    + "WHERE DeliveryIssue.ShipperId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, shipperId);
            rs = ps.executeQuery();

            while (rs.next()) {
                DeliveryIssue deliveryIssue = new DeliveryIssue(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9)
                );
                listDeliveryIssue.add(deliveryIssue);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDeliveryIssue;
    }

    public static void main(String[] args) {

        try {
            ShipperDAO dao = new ShipperDAO();
            dao.insertShipper("Huy", "0981123123", "6");
            System.err.println(dao.getShipperId(13));
        } catch (SQLException ex) {
            Logger.getLogger(ShipperDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}