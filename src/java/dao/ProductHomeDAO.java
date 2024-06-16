/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.CategoryListDetail;
import model.ListProduct;
import model.ProductHome;

/**
 *
 * @author ADMIN
 */
public class ProductHomeDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProductHome> getAllProduct() {
        List<ProductHome> listProduct = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
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
                listProduct.add(new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
                ));
            }
        } catch (Exception e) {
        }
        return listProduct;
    }

    public List<CategoryListDetail> getAllCategory() {
        List<CategoryListDetail> listAllCategory = new ArrayList<>();
        String query = "select c.CategoryId, c.Name \n"
                + "from Category c";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAllCategory.add(new CategoryListDetail(rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
        }
        return listAllCategory;
    }

    public List<ProductHome> getProductByCID(String cid) {
        List<ProductHome> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
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
                list.add(new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ProductHome getProductById(String id) {
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
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
                return new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<ProductHome> getAllBestSellerProduct() {
        List<ProductHome> listBestSellerProduct = new ArrayList<>();
        String query = "select top 9 p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "order by r.RateStar desc";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listBestSellerProduct.add(new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
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

    public List<CategoryListDetail> getCategoryListDetail() {
        List<CategoryListDetail> listCategoryListDetail = new ArrayList<>();
        String query = "select c.CategoryId, c.Name \n"
                + "from Category c";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listCategoryListDetail.add(new CategoryListDetail(rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
        }
        return listCategoryListDetail;
    }

    public List<ProductHome> getProductBySearchName(String txtSearch) {
        List<ProductHome> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "where p.[name] like ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<ProductHome> getProductByCategoryId(int id) {
        List<ProductHome> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, r.Name, p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId\n"
                + "join Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "where c.CategoryId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductHome(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getInt(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getBoolean(14),
                        rs.getDouble(15)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static void main(String[] args) {
        ProductHomeDAO dao = new ProductHomeDAO();

        System.out.println(dao.getProductByCategoryId(1));
    }
}
