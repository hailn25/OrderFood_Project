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

import java.sql.Timestamp;
import java.util.AbstractList;
import java.sql.Date;
import java.util.List;
import model.FlashSaleDTO;
import model.ProductFlashSaleDTO;
import model.ProductSaleDTO;
import model.ProductSaleDTO1;
import model.ProductSaleDetailDTO;

/**
 *
 * @author hailt
 */
public class ProductSaleDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProductSaleDTO> getProductIsFlashSale(String date, int timeFrame) throws SQLException {
        List<ProductSaleDTO> listSale = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        try {
            String sql = "";
            conn = new DBContext().getConnection();
            if (conn != null) {
                if (timeFrame == 1) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 1 \n"
                            + "                                AND startTime <= CAST('" + date + " 10:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 13:00:00' AS DATETIME) OR endTime IS NULL);";

                }
                if (timeFrame == 2) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 2 \n"
                            + "                                AND startTime <= CAST('" + date + " 13:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 16:00:00' AS DATETIME) OR endTime IS NULL);";
                }
                if (timeFrame == 3) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 3 \n"
                            + "                                AND startTime <= CAST('" + date + " 16:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 19:00:00' AS DATETIME) OR endTime IS NULL);";
                }
                if (timeFrame == 4) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 4 \n"
                            + "                                AND startTime <= CAST('" + date + " 19:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 22:00:00' AS DATETIME) OR endTime IS NULL);";
                }
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSale.add(new ProductSaleDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getString(9), rs.getInt(10)));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSale;
    }

    public ProductSaleDetailDTO getProductSaleDetailById(int productId) throws SQLException {
        try {
            String sql = "select ps.ProductID, p.Name,p.Description,p.ImageURL , ps.Quantity, ps.Discount, ps.SalePrice,p.Price\n"
                    + "from Product p\n"
                    + "join Product_Sale ps\n"
                    + "on ps.ProductID = p.ProductId\n"
                    + "where ps.ProductID = ?";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductSaleDetailDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductSaleDTO1> ListProductFlashSale(String date) throws SQLException {
        List<ProductSaleDTO1> list = new ArrayList<>();
        try {
            String sql = "SELECT ps.ProductID,\n"
                    + "       p.Name,\n"
                    + "       ps.IsFlashSale,\n"
                    + "       p.ImageURL,\n"
                    + "       ps.Quantity,\n"
                    + "       ps.Discount,\n"
                    + "       ps.SalePrice,\n"
                    + "       p.Price,\n"
                    + "       ps.TimeFrame,\n"
                    + "       ps.StartTime,\n"
                    + "       ps.EndTime\n"
                    + "FROM Product p\n"
                    + "JOIN Product_Sale ps ON ps.ProductID = p.ProductID\n"
                    + "WHERE CAST(ps.StartTime AS DATE) = ?";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ProductSaleDTO1(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9)));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void ChangeStatusFlashSale(int isFlashSale, int productId) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[Product_Sale] SET [IsFlashSale] = ? WHERE ProductID = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, isFlashSale);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<ProductSaleDTO> getProductIsFlashSaleDiscount(String date, int timeFrame, double discount) throws SQLException {
        List<ProductSaleDTO> listDiscount = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        try {
            String sql = "";
            conn = new DBContext().getConnection();
            if (conn != null) {
                if (timeFrame == 1) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 1 \n"
                            + "                                AND Discount = ?\n"
                            + "                                AND startTime <= CAST('" + date + " 10:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 13:00:00' AS DATETIME) OR endTime IS NULL);";

                }
                if (timeFrame == 2) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 2 \n"
                            + "                                AND Discount = ?\n"
                            + "                                AND startTime <= CAST('" + date + " 13:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 16:00:00' AS DATETIME) OR endTime IS NULL);";
                }
                if (timeFrame == 3) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 3 \n"
                            + "                                AND Discount = ?\n"
                            + "                                AND startTime <= CAST('" + date + " 16:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 19:00:00' AS DATETIME) OR endTime IS NULL);";
                }
                if (timeFrame == 4) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL, p.Price, ps.TimeFrame,a.ImageAvatar,r.RestaurantId\n"
                            + "                            FROM Product_Sale ps\n"
                            + "                            JOIN Product p\n"
                            + "                           ON p.ProductId = ps.ProductID\n"
                            + "						   JOIN Restaurant r\n"
                            + "						   ON r.RestaurantId = p.RestaurantId\n"
                            + "						   JOIN Account a\n"
                            + "						   ON a.AccountId = r.AccountId\n"
                            + "                            WHERE IsFlashSale = 1 \n"
                            + "                                AND TimeFrame = 4 \n"
                            + "                                AND Discount = ?\n"
                            + "                                AND startTime <= CAST('" + date + " 19:00:00' AS DATETIME)\n"
                            + "                            AND (endTime >= CAST('" + date + " 22:00:00' AS DATETIME) OR endTime IS NULL);";
                }
            }
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, discount);
            rs = ps.executeQuery();
            while (rs.next()) {
                listDiscount.add(new ProductSaleDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getString(9), rs.getInt(10)));

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listDiscount;
    }

    public ArrayList<ProductFlashSaleDTO> getFlashSaleProductByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<ProductFlashSaleDTO> list = new ArrayList<>();
        String sql = """
                     SELECT        Product_Sale.*, Product.Name ProductName, Product.ImageURL 
                     FROM            Product INNER JOIN
                                              Product_Sale ON Product.ProductId = Product_Sale.ProductID INNER JOIN
                                              Restaurant ON Product.RestaurantId = Restaurant.RestaurantId
                     WHERE Restaurant.RestaurantId = ?  and Product_Sale.Quantity > 0 and (Product_Sale.IsFlashSale = 0 or Product_Sale.IsFlashSale = 1
                     or Product_Sale.IsFlashSale = 2 or Product_Sale.IsFlashSale = 3)""";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("ProductId");
            String productName = rs.getString("ProductName");
            int isFlashSale = rs.getInt("IsFlashSale");
            String imageURL = rs.getString("ImageURL");
            int quantity = rs.getInt("Quantity");
            double discount = rs.getDouble("Discount");
            int timeFrame = rs.getInt("TimeFrame");
            Date date = rs.getDate("StartTime");
            double salePrice = rs.getDouble("SalePrice");
            ProductFlashSaleDTO s = new ProductFlashSaleDTO(productId, productName, isFlashSale, salePrice,
                    imageURL, quantity, discount, timeFrame, date);
            list.add(s);
        }
        return list;
    }

    public void insertFlashSaleProduct(int productId, String startTime, String endTime,
            double salePrice, double discount, int isFlashSale, int quantity,
            int timeFrame, int restaurantId, Date createDate) throws SQLException {
        try {
            String sql = "INSERT INTO [dbo].[Product_Sale]  \n"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setString(2, startTime);
            ps.setString(3, endTime);
            ps.setDouble(4, salePrice);
            ps.setDouble(5, discount);
            ps.setInt(6, isFlashSale);
            ps.setInt(7, quantity);
            ps.setInt(8, timeFrame);
            ps.setInt(9, restaurantId);
            ps.setDate(10, createDate);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStockBeforeFlashSale(int stock, int quantity, int productId) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "SET [Quantity] = ? -  ?\n"
                    + "WHERE [ProductId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, quantity);
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateStockAfterFlashSale(int stock, int quantity, int productId) throws SQLException {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "SET [Quantity] = ? +  ?\n"
                    + "WHERE [ProductId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, quantity);
            ps.setInt(3, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteFlashSaleProduct(int productId) throws SQLException {
        try {
            String sql = """
                         update [dbo].[Product_Sale]
                         set [IsFlashSale] = 4
                         where ProductID = ?""";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public FlashSaleDTO ViewProductFlashSaleDetail(int id) throws SQLException {
        List<FlashSaleDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT ps.ProductID,p.Name,ps.IsFlashSale,p.ImageURL,ps.Quantity,ps.Discount, ps.SalePrice,p.Price,ps.TimeFrame,ps.StartTime,p.Description\n"
                    + "FROM Product p\n"
                    + "\n"
                    + "JOIN Product_Sale ps\n"
                    + "ON ps.ProductID = p.ProductID\n"
                    + "where ps.ProductId = ?";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new FlashSaleDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9), rs.getDate(10), rs.getString(11));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void ChangeStatusIsFlashSale1() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 1 AND CAST(p.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale2() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 2 AND CAST(p.StartTime AS DATE) <= CAST(GETDATE() AS DATE)";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale3() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 3 AND CAST(p.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale4() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 4 AND CAST(p.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale1() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 1 AND CAST(ps.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale2() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 2 AND CAST(ps.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale3() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 3 AND CAST(ps.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale4() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 4 AND CAST(ps.StartTime AS DATE) <= CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale11() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 1 AND CAST(p.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale22() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 2 AND CAST(p.StartTime AS DATE) < CAST(GETDATE() AS DATE)";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale33() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 3 AND CAST(p.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusIsFlashSale44() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.IsFlashSale = 3\n"
                    + "FROM [dbo].[Product_Sale] p\n"
                    + "WHERE p.TimeFrame = 4 AND CAST(p.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale11() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 1 AND CAST(ps.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale22() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 2 AND CAST(ps.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale33() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 3 AND CAST(ps.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeStatusProductAfterFlashSale44() throws SQLException {
        try {
            String sql = "UPDATE p\n"
                    + "SET p.[Status] = 1\n"
                    + "FROM [dbo].[Product] p\n"
                    + "JOIN [dbo].[Product_Sale] ps ON p.ProductId = ps.ProductID\n"
                    + "WHERE ps.IsFlashSale = 3 AND ps.TimeFrame = 4 AND CAST(ps.StartTime AS DATE) < CAST(GETDATE() AS DATE);";
            conn = new DBContext().getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ProductFlashSaleDTO getDetailFlashSaleProductByRestaurantId(int restaurantId, int pId) throws SQLException, Exception {
        String sql = """
                     SELECT        Product_Sale.*, Product.Name ProductName, Product.ImageURL
                     FROM            Product INNER JOIN
                                     Product_Sale ON Product.ProductId = Product_Sale.ProductID INNER JOIN
                                     Restaurant ON Product.RestaurantId = Restaurant.RestaurantId
                     WHERE Restaurant.RestaurantId = ? and Product.ProductId = ?  and Product_Sale.Quantity > 0 and (Product_Sale.IsFlashSale = 0 or Product_Sale.IsFlashSale = 1
                                          or Product_Sale.IsFlashSale = 2 or Product_Sale.IsFlashSale = 3)""";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        ps.setInt(2, pId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int productId = rs.getInt("ProductId");
            String productName = rs.getString("ProductName");
            int isFlashSale = rs.getInt("IsFlashSale");
            String imageURL = rs.getString("ImageURL");
            int quantity = rs.getInt("Quantity");
            double discount = rs.getDouble("Discount");
            int timeFrame = rs.getInt("TimeFrame");
            Date date = rs.getDate("StartTime");
            double salePrice = rs.getDouble("SalePrice");

            return new ProductFlashSaleDTO(productId, productName, isFlashSale, salePrice,
                    imageURL, quantity, discount, timeFrame, date);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ProductSaleDAO dao = new ProductSaleDAO();
        dao.ChangeStatusProductAfterFlashSale1();

    }

}
