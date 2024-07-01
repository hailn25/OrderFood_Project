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
import model.ProductDTO;

/**
 *
 * @author quoch
 */
public class FunctionShopDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<ProductDTO> getAllProductDTOByCategoryName(String categoryName) {
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductId, Product.Name, Product.Price, Product.Description, Product.ImageURL, Product.CategoryId, Account.ImageAvatar, Product.IsSale, Product.Quantity, Product.CreateDate, Product.UpdateDate, Product.Status, Product.RestaurantId\n"
                    + "FROM     Product INNER JOIN\n"
                    + "                  Category ON Product.CategoryId = Category.CategoryId INNER JOIN\n"
                    + "                  Restaurant ON Product.RestaurantId = Restaurant.RestaurantId INNER JOIN\n"
                    + "                  Account ON Restaurant.AccountId = Account.AccountId\n"
                    + "WHERE Category.Name = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoryName);
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
                        rs.getBoolean(12),
                        rs.getInt(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductDTO;
    }

    public ArrayList<ProductDTO> searchProductByName(String productName) {
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductId, Product.Name, Product.Price, Product.Description, Product.ImageURL, Product.CategoryId, Account.ImageAvatar, Product.IsSale, Product.Quantity, Product.CreateDate, Product.UpdateDate, Product.Status, Product.RestaurantId\n"
                    + "FROM     Product INNER JOIN\n"
                    + "Category ON Product.CategoryId = Category.CategoryId INNER JOIN\n"
                    + "Restaurant ON Product.RestaurantId = Restaurant.RestaurantId INNER JOIN\n"
                    + "Account ON Restaurant.AccountId = Account.AccountId\n"
                    + "WHERE Product.Name like N'%" + productName + "%'";
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
                        rs.getBoolean(12),
                        rs.getInt(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductDTO;
    }

    public ArrayList<ProductDTO> getListProductByRestaurantId(int restaurantId) {
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductId, Product.Name, Product.Price, Product.Description, Product.ImageURL, Product.CategoryId, Account.ImageAvatar, Product.IsSale, Product.Quantity, Product.CreateDate, Product.UpdateDate, Product.Status, Product.RestaurantId\n"
                    + "FROM     Account INNER JOIN\n"
                    + "Restaurant ON Account.AccountId = Restaurant.AccountId INNER JOIN\n"
                    + "Product ON Restaurant.RestaurantId = Product.RestaurantId\n"
                    + "WHERE Restaurant.RestaurantId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, restaurantId);
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
                        rs.getBoolean(12),
                        rs.getInt(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductDTO;
    }

    public ArrayList<ProductDTO> searchByPrice(int minPrice) {
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        try {
            String sql = "SELECT Product.ProductId, Product.Name, Product.Price, Product.Description, Product.ImageURL, Product.CategoryId, Account.ImageAvatar, Product.IsSale, Product.Quantity, Product.CreateDate, Product.UpdateDate, Product.Status, Product.RestaurantId\n"
                    + "FROM    Product INNER JOIN\n"
                    + "		Category ON Product.CategoryId = Category.CategoryId INNER JOIN\n"
                    + "		Restaurant ON Product.RestaurantId = Restaurant.RestaurantId INNER JOIN\n"
                    + "		Account ON Restaurant.AccountId = Account.AccountId\n"
                    + "WHERE Product.Price between ? and  (SELECT MAX(Product.Price) FROM Product)";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, minPrice);
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
                        rs.getBoolean(12),
                        rs.getInt(13)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listProductDTO;
    }

    public static void main(String[] args) {
        FunctionShopDAO dao = new FunctionShopDAO();

//        for (ProductDTO pt : dao.getAllProductDTOByCategoryName("Bánh kem")) {
//            System.out.println(pt.toString());
//        }
//        for (ProductDTO pt : dao.searchByPrice(50)) {
//            System.out.println(pt.toString());
//        }
//        for (ProductDTO pt : dao.searchProductByAttribute("Cơm ", 0)) {
//            System.out.println(pt.toString());
//        }
        for (ProductDTO p : dao.getListProductByRestaurantId(1)) {
            System.out.println(p.toString());
        }
    }
}
