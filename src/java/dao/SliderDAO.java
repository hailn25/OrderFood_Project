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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class SliderDAO {

    private Connection conn;
    private PreparedStatement ps;

    public void insertSlider(String sliderTitle, String imageAvatar, String createDate, String updateDate, String backLink) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO Sliders ([SliderTitle], [ImageURL], [Arrange], [Status], [UpdateBy], [CreateDate], [UpdateDate], [Backlink])\n"
                    + "VALUES (?, ?, 1, 0, 2, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sliderTitle);
            ps.setString(2, imageAvatar);
            ps.setString(3, createDate);
            ps.setString(4, updateDate);
            ps.setString(5, backLink);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SliderDAO dao = new SliderDAO();
        dao.insertSlider("áddasd", "haiavt.png", "2024-06-18", "2024-06-19", "facebook.com");
    }
}
