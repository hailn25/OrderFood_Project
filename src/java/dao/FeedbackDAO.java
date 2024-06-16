/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
}
