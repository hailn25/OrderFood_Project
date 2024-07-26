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
            String sql = "SELECT \n"
                    + "    Slider.SliderId, \n"
                    + "    Slider.SliderTitle, \n"
                    + "    Slider.ImageURL, \n"
                    + "    Slider.Arrange, \n"
                    + "    SliderStatus.StatusName, \n"
                    + "    Slider.UpdateBy, \n"
                    + "    Slider.CreateDate, \n"
                    + "    Slider.UpdateDate, \n"
                    + "    Slider.Backlink, \n"
                    + "    Account.Status\n"
                    + "FROM \n"
                    + "    Account\n"
                    + "INNER JOIN \n"
                    + "    Slider ON Account.AccountId = Slider.UpdateBy\n"
                    + "INNER JOIN \n"
                    + "    SliderStatus ON Slider.SliderStatusId = SliderStatus.SliderStatusId\n"
                    + "WHERE\n" +
            "	SliderStatus.StatusName like N'Xác nhận' OR SliderStatus.StatusName like N'Từ chối' OR SliderStatus.StatusName like N'Đang chờ xác nhận' ";
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
                        rs.getString(9),
                        rs.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSlider;
    }

    public void changeStatusSlider(int sliderId, int status, int restaurantId, String updateDate) {
        try {
            String sql = "UPDATE [dbo].[Slider]\n"
                    + "SET \n"
                    + "    [SliderStatusId] = ?, [UpdateBy] = ?, [UpdateDate] = ?\n"
                    + "WHERE \n"
                    + "    [SliderId] = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, restaurantId);
            ps.setString(3, updateDate);
            ps.setInt(4, sliderId);

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
            ps.setInt(5, updateBy);
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

    public SliderDTO getSliderBySliderId(int sliderId) {
        SliderDTO slider = new SliderDTO();
        try {
            String sql = "SELECT Slider.SliderId, Slider.SliderTitle, Slider.ImageURL, Slider.Arrange, SliderStatus.StatusName, Slider.UpdateBy, Slider.CreateDate, Slider.UpdateDate, Slider.Backlink, Account.Status\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Slider ON Account.AccountId = Slider.UpdateBy INNER JOIN\n"
                    + "                  SliderStatus ON Slider.SliderStatusId = SliderStatus.SliderStatusId\n"
                    + "WHERE \n"
                    + "    Slider.SliderId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sliderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                slider = new SliderDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getDate(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getInt(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return slider;
    }

    public String getRestaurantNameBySliderId(int sliderId) {
        String restaurantName = "";
        try {
            String sql = "SELECT Restaurant.Name\n"
                    + "FROM  Restaurant\n"
                    + "WHERE Restaurant.RestaurantId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, sliderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                restaurantName = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return restaurantName;
    }

    public int checkBanAccountByRestaurantId(int restaurantId) {
        int flag = -1;
        try {
            String sql = "SELECT Account.Status\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Restaurant ON Account.AccountId = Restaurant.AccountId\n"
                    + "WHERE Restaurant.RestaurantId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            rs = ps.executeQuery();

            while (rs.next()) {
                flag = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public static void main(String[] args) {
        SliderDAO dao = new SliderDAO();
        for (SliderDTO s : dao.getAllSliderDTO()) {
            System.out.println(s.toString());
        }
        System.out.println(dao.getRestaurantNameBySliderId(1));
        System.out.println(dao.checkBanAccountByRestaurantId(3));

//        dao.changeStatusSlider(5, 3, 1, "2024-07-30");
    }
}



