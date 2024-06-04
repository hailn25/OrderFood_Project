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
import model.*;

/**
 *
 * @author quoch
 */
public class ShopDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Product> getAllProduct() {
        ArrayList<Product> listProduct = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM [orderfoodperfect].[dbo].[Product]";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listProduct.add(new Product(rs.getInt(1),
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
                        rs.getBoolean(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProduct;
    }

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM [orderfoodperfect].[dbo].[Category]";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listCategory.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getDate(3),
                        rs.getDate(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategory;
    }

    public ArrayList<CategoryDTO> getTotalQuantityByCategory() {
        ArrayList<CategoryDTO> listCategoryDTO = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    c.name,\n"
                    + "    SUM(p.Quantity)\n"
                    + "FROM \n"
                    + "    Category c\n"
                    + "JOIN \n"
                    + "    Product p ON c.CategoryId = p.CategoryId\n"
                    + "GROUP BY \n"
                    + "    c.[Name];";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listCategoryDTO.add(new CategoryDTO(rs.getString(1),
                        rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCategoryDTO;
    }

    public ArrayList<ProductDTO> getAllProductDTO() {
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductId, Product.Name, Product.Price, Product.Description, Product.ImageURL, Product.CategoryId, Account.ImageAvatar, Product.IsSale, Product.Quantity, Product.CreateDate, Product.UpdateDate, Product.Status\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Restaurant ON Account.AccountId = Restaurant.AccountId INNER JOIN\n"
                    + "                  Product ON Restaurant.RestaurantId = Product.RestaurantId";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listProductDTO.add(new ProductDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductDTO;
    }

    public ArrayList<RestaurantDTO> getAllRestaurantDTO() {
        ArrayList<RestaurantDTO> listRestaurant = new ArrayList<>();
        try {
            String sql = "SELECT Restaurant.Name, Restaurant.Address, Restaurant.RateStar, Account.ImageAvatar\n"
                    + "FROM     Account INNER JOIN\n"
                    + "                  Restaurant ON Account.AccountId = Restaurant.AccountId\n"
                    + "ORDER BY Restaurant.RateStar DESC";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listRestaurant.add(new RestaurantDTO(rs.getString(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRestaurant;
    }

    public static void main(String[] args) {
        ShopDAO dao = new ShopDAO();

//        for (Product p : dao.getAllProduct()) {
//            System.out.println(p.toString());
//        }
//        for (Category c : dao.getAllCategory()) {
//            System.out.println(c.toString());
//        }
//        for (CategoryDTO c : dao.getTotalQuantityByCategory()) {
//              System.out.println(c.toString());
//        }
//        for (ProductDTO pt : dao.getAllProductDTO()) {
//            System.out.println(pt.toString());
//        }
        for (RestaurantDTO rs : dao.getAllRestaurantDTO()) {
            System.out.println(rs.toString());
        }
    }
}
