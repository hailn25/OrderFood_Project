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
                    + "from Sliders";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listSlider.add(new Slider(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
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

    public void changeStatusSlider(int sliderId, boolean status) {
        try {
            String sql = "UPDATE [dbo].[Sliders]\n"
                    + "SET [Status] = ?\n"
                    + "WHERE [SliderId] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, sliderId);
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        SliderDAO dao = new SliderDAO();
//        for (Slider s : dao.getAllSlider()) {
//            System.out.println(s.toString());
//        }
    }
}
