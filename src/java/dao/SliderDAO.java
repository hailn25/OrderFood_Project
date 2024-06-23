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
import model.Slider;
import model.SliderDTO;

/**
 *
 * @author quoch
 */
public class SliderDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Slider> getAllSlider() {
        ArrayList<Slider> listSlider = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from [dbo].[Slider]";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listSlider.add(new Slider(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSlider;
    }

    public ArrayList<SliderDTO> getAllSliderDTO() {
        ArrayList<SliderDTO> listSlider = new ArrayList<>();
        try {
            String sql = "SELECT Slider.SliderId, Slider.SliderTitle, Slider.ImageURL, Slider.Arrange, SliderStatus.StatusName, Slider.UpdateBy, Slider.CreateDate, Slider.UpdateDate, Slider.Backlink\n"
                    + "FROM     Slider INNER JOIN\n"
                    + "                  SliderStatus ON Slider.SliderStatusId = SliderStatus.SliderStatusId";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listSlider.add(new SliderDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSlider;
    }

    public void changeStatusSlider(int sliderId, int status) {
        try {
            String sql = "UPDATE [dbo].[Slider]\n"
                    + "SET [SliderStatusId] = ?\n"
                    + "WHERE [SliderId] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, sliderId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertSlider(String sliderTitle, String imageAvatar, int arrange, int sliderStatusId, int updateBy, String createDate, String updateDate, String backLink) throws SQLException {
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

    public static void main(String[] args) {
        SliderDAO dao = new SliderDAO();
        for (SliderDTO s : dao.getAllSliderDTO()) {
            System.out.println(s.toString());
        }
    }
}
