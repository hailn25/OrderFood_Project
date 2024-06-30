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
import model.OrderDetailDTO_Huyvq;
import model.Product;
import model.Role;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Product> getOpenProductByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product]\n"
                + "WHERE [RestaurantId] = ? and [Status] = 1";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String description = rs.getString(4);
            String imageURL = rs.getString(5);
            int categoryId = rs.getInt(6);
            int restaurantId1 = rs.getInt(7);
            boolean isSale = rs.getBoolean(8);
            int quantity = rs.getInt(9);
            Date createDate = rs.getDate(10);
            Date updateDate = rs.getDate(11);
            boolean status = rs.getBoolean(12);
            Product s = new Product(productId, name, price, description, imageURL,
                    categoryId, restaurantId1, isSale, quantity, createDate,
                    updateDate, status);
            list.add(s);
        }
        return list;
    }

    public ArrayList<Product> getCloseProductByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product]\n"
                + "WHERE [RestaurantId] = ? and [Status] = 0"
                + "";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt(1);
            String name = rs.getString(2);
            double price = rs.getDouble(3);
            String description = rs.getString(4);
            String imageURL = rs.getString(5);
            int categoryId = rs.getInt(6);
            int restaurantId1 = rs.getInt(7);
            boolean isSale = rs.getBoolean(8);
            int quantity = rs.getInt(9);
            Date createDate = rs.getDate(10);
            Date updateDate = rs.getDate(11);
            boolean status = rs.getBoolean(12);
            Product s = new Product(productId, name, price, description, imageURL,
                    categoryId, restaurantId1, isSale, quantity, createDate,
                    updateDate, status);
            list.add(s);
        }
        return list;
    }

    public void deleteProduct(int pid) throws SQLException {
        try {
            String sql = "delete from [dbo].[Product]\n"
                    + "where [ProductId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pid);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void openProduct(Date updateDate, int productId) {
        try {
            String sql = "update [dbo].[Product]\n"
                    + "set [Status] = 1, [UpdateDate] = ?\n"
                    + "where ProductId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, updateDate);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeProduct(Date updateDate, int productId) {
        try {
            String sql = "update [dbo].[Product]\n"
                    + "set [Status] = 0, [UpdateDate] = ?\n"
                    + "where ProductId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, updateDate);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editProduct(String name, String price, String description,
            String img, String categoryId, String isSale, String quantity, String status,
            Date updateDate, String productId) throws SQLException {

        try {
            String sql = "update [dbo].[Product]\n"
                    + "set [Name] = ?, [Price] = ?, [Description] = ?, [ImageURL] = ?, [CategoryId] = ?, [IsSale] = ?, [Quantity] = ?, [Status] = ?, [UpdateDate] = ?\n"
                    + "where [ProductId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, description);
            ps.setString(4, img);
            ps.setString(5, categoryId);
            ps.setString(6, isSale);
            ps.setString(7, quantity);
            ps.setString(8, status);
            ps.setDate(9, updateDate);
            ps.setString(10, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void updatePriceSale_on(String productId) {
        try {
            String sql = "update [dbo].[Product]\n"
                    + "set  [Price] = [Price] * 0.9\n"
                    + "where [ProductId] = ? and [IsSale] = 1";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePriceSale_off(String productId) {
        try {
            String sql = "update [dbo].[Product]\n"
                    + "set  [Price] = [Price] * (1/0.9)\n"
                    + "where [ProductId] = ? and [IsSale] = 0";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCurrentIsSale(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT [IsSale]\n"
                + "FROM [dbo].[Product] \n"
                + "WHERE [ProductId] = ?";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, productId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {            
            return rs.getString(1);
        }
        return null;
    }
    
        public static void main(String[] args) {
        try {
            ProductDAO dao = new dao.ProductDAO();
            System.out.println(dao.getCurrentIsSale("4"));;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertProduct(String name, String price, String description, String img, String category, int RestaurantId, String isSale, String quantity, Date createDate, Date updateDate, String status) throws SQLException {
        try {
            String sql = "insert into [dbo].[Product] ([Name], [Price], [Description], [ImageURL], [CategoryId], [RestaurantId], [IsSale], [Quantity], [CreateDate], [UpdateDate],[Status])\n"
                    + "values (?,?,?,?,?,?,?,?,?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, description);
            ps.setString(4, img);
            ps.setString(5, category);
            ps.setInt(6, RestaurantId);
            ps.setString(7, isSale);
            ps.setString(8, quantity);
            ps.setDate(9, createDate);
            ps.setDate(10, updateDate);
            ps.setString(11, status);

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProductByID(int id) {
        try {
            String query = "select * from Product where ProductId = ?";
            conn = new DBContext().getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                return new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public int getQuantityProduct(int pid) {
        try {

            String query = "SELECT Quantity\n"
                    + "FROM    Product  where ProductId = ?";
            conn = new DBContext().getConnection();

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
        }
        return 0;
    }

}
