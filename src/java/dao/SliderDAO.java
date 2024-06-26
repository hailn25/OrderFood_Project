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

    public void insertSlider(String sliderTitle, String imageAvatar, int arrange, int sliderStatusId, int updateBy, String createDate, String updateDate, String backLink) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Slider ([SliderTitle], [ImageURL], [Arrange], [SliderStatusId], [UpdateBy], [CreateDate], [UpdateDate], [Backlink])\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, sliderTitle);
            ps.setString(2, imageAvatar);
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.setInt(5, 2);
            ps.setString(6, createDate);
            ps.setString(7, updateDate);
            ps.setString(8, backLink);
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
        dao.insertSlider("ahihi", "dsds.img", 1, 1, 2, "2024-06-20", "2024-06-25", "link.com");
    }
}
