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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RestaurantDTO;
import model.RestaurantReportedDTO;

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

    public String getRestaurantNameByRestaurantId(int restaurantId) {
        String restaurantName = "";
        try {
            String sql = "SELECT [Name]\n"
                    + "FROM Restaurant\n"
                    + "where RestaurantId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            rs = ps.executeQuery();
            while (rs.next()) {
                restaurantName = rs.getString(1);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantName;
    }

    public int getQuantityOfRestaurant() throws ClassNotFoundException {
        try {
            String sql = "select COUNT(RestaurantId)\n"
                    + "from [dbo].[Restaurant]";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<RestaurantDTO> getRestaurantDTOByRestaurantId(int restaurantId) {
        ArrayList<RestaurantDTO> listRestaurant = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    r.RestaurantId,r.[Name] AS RestaurantName,r.[Address] AS RestaurantAddress,r.RateStar,a.ImageAvatar,a.CreateDate AS AccountCreateDate,a.Email,a.Phone,COUNT(p.ProductId) AS QuantityOfProduct\n"
                    + "FROM \n"
                    + "    Restaurant r\n"
                    + "JOIN \n"
                    + "    Account a ON r.AccountId = a.AccountId\n"
                    + "LEFT JOIN \n"
                    + "    Product p ON r.RestaurantId = p.RestaurantId\n"
                    + "WHERE r.RestaurantId = ?\n"
                    + "GROUP BY \n"
                    + "    r.RestaurantId,r.[Name],r.[Address],r.RateStar,a.ImageAvatar,a.CreateDate,a.Email,a.Phone";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            rs = ps.executeQuery();

            while (rs.next()) {
                listRestaurant.add(new RestaurantDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRestaurant;
    }

    public ArrayList<RestaurantReportedDTO> getListRestaurantReported() {
        ArrayList<RestaurantReportedDTO> list = new ArrayList<>();
        String sql = """
                     SELECT Restaurant.RestaurantId, Restaurant.Name, Account.ImageAvatar, Restaurant.RateStar, Account.Status
                     FROM Account INNER JOIN
                     Report ON Account.AccountId = Report.AccountId INNER JOIN
                     Restaurant ON Account.AccountId = Restaurant.AccountId AND Report.RestaurantId = Restaurant.RestaurantId
                     WHERE dbo.Report.ReportStatusId = 3 and Account.Status = 1""";
        try {

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RestaurantReportedDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getBoolean(5)
                ));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurantDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
        RestaurantDAO dao = new RestaurantDAO();
//        System.out.println(dao.getRestaurantIdByAccountId(8));
//        for (RestaurantDTO r : dao.getRestaurantDTOByRestaurantId(1)) {
//            System.out.println(r.toString());
//        }
//        System.out.println(dao.getRestaurantIdByAccountId(8));
System.out.println(dao.getRestaurantNameByRestaurantId(1));
    }
}
