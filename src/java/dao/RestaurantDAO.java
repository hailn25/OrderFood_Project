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

    public static void main(String[] args) {
        RestaurantDAO dao = new RestaurantDAO();
//        System.out.println(dao.getRestaurantIdByAccountId(8));
//        for (RestaurantDTO r : dao.getRestaurantDTOByRestaurantId(1)) {
//            System.out.println(r.toString());
//        }
    }
}
