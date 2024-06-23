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

//    public static void main(String[] args) {
//        RestaurantDAO dao = new RestaurantDAO();
//        System.out.println(dao.getRestaurantIdByAccountId(8));
//    }
}
