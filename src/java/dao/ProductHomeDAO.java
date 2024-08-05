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
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
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
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public ProductHome getProductById(String id) {
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<ProductHome> getAllOSTProduct() {
        List<ProductHome> listBestSellerProduct = new ArrayList<>();
        String query = "WITH RankedProducts AS (\n"
                + "    SELECT \n"
                + "        p.ProductId,\n"
                + "        p.Name,\n"
                + "        p.Price,\n"
                + "        p.Description,\n"
                + "        p.ImageURL,\n"
                + "        c.CategoryId,\n"
                + "        c.Name AS CategoryName,\n"
                + "        r.RestaurantId,\n"
                + "        a.Status AS AccountStatus,\n"
                + "        r.Name AS RestaurantName,\n"
                + "        a.ImageAvatar,\n"
                + "        p.IsSale,\n"
                + "        p.Quantity,\n"
                + "        p.CreateDate,\n"
                + "        p.UpdateDate,\n"
                + "        p.Status AS ProductStatus,\n"
                + "        r.RateStar,\n"
                + "        ROW_NUMBER() OVER (PARTITION BY r.RestaurantId ORDER BY p.ProductId) AS rn\n"
                + "    FROM \n"
                + "        Account a\n"
                + "    INNER JOIN \n"
                + "        Restaurant r ON a.AccountId = r.AccountId\n"
                + "    INNER JOIN \n"
                + "        Product p ON r.RestaurantId = p.RestaurantId\n"
                + "    INNER JOIN \n"
                + "        Category c ON p.CategoryId = c.CategoryId\n"
                + ")\n"
                + "SELECT \n"
                + "    ProductId,\n"
                + "    Name,\n"
                + "    Price,\n"
                + "    Description,\n"
                + "    ImageURL,\n"
                + "    CategoryId,\n"
                + "    CategoryName,\n"
                + "    RestaurantId,\n"
                + "    AccountStatus,\n"
                + "    RestaurantName,\n"
                + "    ImageAvatar,\n"
                + "    IsSale,\n"
                + "    Quantity,\n"
                + "    CreateDate,\n"
                + "    UpdateDate,\n"
                + "    ProductStatus,\n"
                + "    RateStar\n"
                + "FROM \n"
                + "    RankedProducts\n"
                + "WHERE \n"
                + "    rn <= 3\n"
                + "ORDER BY \n"
                + "    RestaurantId, rn;";
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return listBestSellerProduct;
    }

    public List<ListProduct> getListProductP() {
        List<ListProduct> listProductP = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
    
    public List<ProductHome> getProductHome() {
        List<ProductHome> listProduct = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return listProduct;
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
        String query = "select top 9 p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ProductHome> getProductByCategoryId(int id) {
        List<ProductHome> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
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
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<ProductHome> getProductByIsSale() {
        List<ProductHome> listProductByIsSale = new ArrayList<>();
        String query = "select p.ProductId, p.Name,p.Price, p.Description, p.ImageURL, c.CategoryId, c.Name, r.RestaurantId, a.Status, r.Name, a.ImageAvatar , p.IsSale, p.Quantity, p.CreateDate, p.UpdateDate, p.Status, r.RateStar\n"
                + "from Account a\n"
                + "INNER JOIN Restaurant r\n"
                + "on a.AccountId = r.AccountId\n"
                + "INNER JOIN Product p\n"
                + "on r.RestaurantId = p.RestaurantId\n"
                + "INNER JOIN Category c\n"
                + "on p.CategoryId = c.CategoryId\n"
                + "where p.IsSale = 1";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProductByIsSale.add(new ProductHome(rs.getInt(1),
                         rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getBoolean(12),
                        rs.getInt(13),
                        rs.getDate(14),
                        rs.getDate(15),
                        rs.getInt(16),
                        rs.getDouble(17)
                ));
            }
        } catch (Exception e) {
        }
        return listProductByIsSale;
    }

    public static void main(String[] args) {
        ProductHomeDAO dao = new ProductHomeDAO();
        System.out.println(dao.getProductBySearchName("Bánh"));
    }

}



