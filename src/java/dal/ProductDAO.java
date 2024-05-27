/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.ListProduct;
import model.Product;

/**
 *
 * @author ADMIN
 */
public class ProductDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Product> getAllProduct() {
        List<Product> listProduct = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.Name, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProduct.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12),
                        rs.getDouble(13)
                ));
            }
        } catch (Exception e) {
        }
        return listProduct;
    }

    public List<Category> getAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        String query = "select c.CategoryId, c.Name\n"
                + "from Category c";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategory.add(new Category(rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
        }
        return listCategory;
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.Name, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "where c.CategoryId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12),
                        rs.getDouble(13)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductById(String id) {
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.Name, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "where p.ProductId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12),
                        rs.getDouble(13)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getAllBestSellerProduct() {
        List<Product> listBestSellerProduct = new ArrayList<>();
        String query = "select top 9 p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.Name, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listBestSellerProduct.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8),
                        rs.getInt(9),
                        rs.getDate(10),
                        rs.getDate(11),
                        rs.getBoolean(12),
                        rs.getDouble(13)
                ));
            }
        } catch (Exception e) {
        }
        return listBestSellerProduct;
    }

    public List<ListProduct> getListProductP() {
        List<ListProduct> listProductP = new ArrayList<>();
        String query = "select p.ProductId, p.Name, p.Description, p.Price, p.ImageURL, c.Name, p.RestaurantId, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status\n"
                + "from Product p\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProductP.add(new ListProduct(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
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
        return listProductP;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        System.out.println(dao.getListProductP());
    }
}
