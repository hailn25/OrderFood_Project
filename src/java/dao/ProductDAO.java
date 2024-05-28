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
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Product> getProductByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product]\n"
                + "WHERE [RestaurantId] = ?";
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

    public void editProduct(String name, String price, String description,
            String img, String categoryId, String quantity,
            String productId) throws SQLException {
        try {
            String sql = "update [dbo].[Product]\n"
                    + "set [Name] = ?, [Price] = ?, [Description] = ?, [ImageURL] = ?, [CategoryId] = ?, [Quantity] = ?\n"
                    + "where [ProductId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, description);
            ps.setString(4, img);
            ps.setString(5, categoryId);
            ps.setString(6, quantity);
            ps.setString(7, productId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertProduct(String name, String price, String description, String img, String category, int RestaurantId, String quantity) throws SQLException {
        try {
            String sql = "insert into [dbo].[Product] ([Name], [Price], [Description], [ImageURL], [CategoryId], [RestaurantId], [Quantity])\n"
                    + "values (?,?,?,?,?,?,?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, description);
            ps.setString(4, img);
            ps.setString(5, category);
            ps.setInt(6, RestaurantId);
            ps.setString(7, quantity);

            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();
        String sql = "select * from Product";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProduct.add(new Product(
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
                ));
            }
        } catch (Exception e) {
        }
        return listProduct;
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

    public static void main(String[] args) {
        ProductDAO db = new ProductDAO();
        System.out.println(db.getProductByID(1));
    }
}
