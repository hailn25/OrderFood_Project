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

    public List<Feedback> getFeedback() {
        List<Feedback> listFeedback = new ArrayList<>();
        String query = "select f.FeedbackId, f.RateStar, f.Feedback, f.ImageURL, a.Name, a.ImageAvatar, p.Name, f.Date \n"
                + "from Feedback f\n"
                + "join Account a\n"
                + "on a.AccountId = f.AccountId\n"
                + "join Product p\n"
                + "on f.ProductId = p.ProductId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listFeedback.add(new Feedback(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8)
                ));
            }
        } catch (Exception e) {
        }
        return listFeedback;
    }
}
