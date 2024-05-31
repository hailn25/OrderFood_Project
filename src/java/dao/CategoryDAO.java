/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Product;

/**
 *
 * @author Vu Huy
 */
public class CategoryDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Category> getAllCategory() throws SQLException, Exception {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Category]";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            int categoryId = rs.getInt(1);
            String name = rs.getString(2);
            Date createDate = rs.getDate(3);
            Date updateDate = rs.getDate(4);
            Category s = new Category(categoryId, name, createDate, updateDate);
            list.add(s);
        }
        return list;
    }

    public void insertCategory(String name) throws SQLException {
        try {
            String sql = "insert into  [dbo].[Category]([Name])\n"
                    + "values (?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Category getCategoryByCId(int id) throws SQLException {

        try {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Category]\n"
                    + "WHERE [CategoryId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String name = rs.getString(2);
                Date createDate = rs.getDate(3);
                Date updateDate = rs.getDate(4);
                Category s = new Category(categoryId, name, createDate, updateDate);
                return s;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void editCategory(String name, String CId) throws SQLException {
        try {
            String sql = "update [dbo].[Category]\n"
                    + "set [Name] = ?\n"
                    + "where [CategoryId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, CId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteCategory(int cid) throws SQLException {
        try {
            String sql = "delete from [dbo].[Category]\n"
                    + "where [CategoryId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            CategoryDAO dao = new CategoryDAO();
            System.out.println(dao.getCategoryByCId(3));
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
