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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class FeedbackDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Feedback> getFeedbackByProductId(int productId) {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "select p.ProductId, p.Name, p.Price, p.Description, p.ImageURL, p.CategoryId, p.RestaurantId, f.FeedbackId, f.Feedback, f.Date, f.RateStar, a.ImageAvatar, a.Name\n"
                + "from Feedback f\n"
                + "join Product p\n"
                + "on f.ProductId = p.ProductId\n"
                + "join Account a\n"
                + "on f.AccountId = a.AccountId\n"
                + "where p.ProductId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getDouble(11),
                        rs.getString(12),
                        rs.getString(13)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listFeedback;
    }

    public void insertFeedback(String rateStar, String feedbackText, String imageURL, String accountId, String productId, String date) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Feedback ([RateStar], [Feedback], [ImageURL], [AccountId], [ProductId], [Date])\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, rateStar);
            ps.setString(2, feedbackText);
            ps.setString(3, imageURL);
            ps.setString(4, accountId);
            ps.setString(5, productId);
            ps.setString(6, date);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void insertReport(String description, String imageURL, String createDate, int accountId, int restaurantId, int status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Report ([Description], [ImageURL], [AccountId], [RestaurantId],[CreateDate], [Status])\n"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, imageURL);
            ps.setInt(3, 6);
            ps.setInt(4, 1);
            ps.setString(5, createDate);
            ps.setInt(6, 1);
            ps.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex; // Re-throw the exception to handle it elsewhere if needed
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FeedbackDAO dao = new FeedbackDAO();
        dao.insertReport("non", "ahihi.img", "2024-06-20", 0, 0, 0);
    }
}
