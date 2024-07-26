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
                    + "FROM [dbo].[Category];";
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

    public ArrayList<CategoryDTO> getProductQuantityByCategory() {
        ArrayList<CategoryDTO> listCategoryDTO = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    c.Name AS CategoryName,\n"
                    + "    COUNT(p.ProductId) AS ProductCount\n"
                    + "FROM \n"
                    + "    Category c\n"
                    + "JOIN \n"
                    + "    Product p ON c.CategoryId = p.CategoryId\n"
                    + "JOIN \n"
                    + "    Restaurant r ON p.RestaurantId = r.RestaurantId\n"
                    + "JOIN \n"
                    + "    Account a ON r.AccountId = a.AccountId\n"
                    + "WHERE \n"
                    + "    p.Status = 1 \n"
                    + "    AND p.Quantity >= 1\n"
                    + "	AND a.Status = 1\n"
                    + "GROUP BY\n"
                    + "    c.Name";
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
            String sql = "SELECT \n"
                    + "    Product.ProductId, \n"
                    + "    Product.Name, \n"
                    + "    Product.Price, \n"
                    + "    Product.Description, \n"
                    + "    Product.ImageURL, \n"
                    + "    Product.CategoryId, \n"
                    + "    Account.ImageAvatar, \n"
                    + "    Product.IsSale, \n"
                    + "    Product.Quantity, \n"
                    + "    Product.CreateDate, \n"
                    + "    Product.UpdateDate, \n"
                    + "    Product.Status, \n"
                    + "    Product.RestaurantId\n"
                    + "FROM \n"
                    + "    Account \n"
                    + "INNER JOIN \n"
                    + "    Restaurant ON Account.AccountId = Restaurant.AccountId \n"
                    + "INNER JOIN \n"
                    + "    Product ON Restaurant.RestaurantId = Product.RestaurantId\n"
                    + "WHERE Account.[Status] = 1";
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
                        rs.getInt(12),
                        rs.getInt(13)));
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
            String sql = """
                         SELECT Top 4 Restaurant.RestaurantId, Restaurant.Name, Restaurant.Address, Restaurant.RateStar, Account.ImageAvatar, Account.status
                                                                           FROM     Account INNER JOIN
                                                                           Restaurant ON Account.AccountId = Restaurant.AccountId
                                                                           ORDER BY Restaurant.RateStar DESC""";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listRestaurant.add(new RestaurantDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)));
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
//        for (CategoryDTO c : dao.getProductQuantityByCategory()) {
//            System.out.println(c.toString());
//        }
//        for (ProductDTO pt : dao.getAllProductDTO()) {
//            System.out.println(pt.toString());
//        }
//        for (RestaurantDTO rs : dao.getAllRestaurantDTO()) {
//            System.out.println(rs.toString());
//        }
    }
}
