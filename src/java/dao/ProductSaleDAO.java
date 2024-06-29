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
import java.util.List;
import model.ProductSaleDTO;
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
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL\n"
                            + "FROM Product_Sale ps\n"
                            + "JOIN Product p\n"
                            + "ON p.ProductId = ps.ProductID\n"
                            + "WHERE IsFlashSale = 1 \n"
                            + "    AND TimeFrame != 2 \n"
                            + "    AND startTime <= CAST('" + date + " 08:00:00' AS DATETIME)\n"
                            + "    AND (endTime >= CAST('" + date + " 14:00:00' AS DATETIME) OR endTime IS NULL);";

                }
                if (timeFrame == 2) {
                    sql = "SELECT ps.ProductID, p.Name,ps.Quantity,ps.SalePrice,ps.Discount ,p.ImageURL\n"
                            + "FROM Product_Sale ps\n"
                            + "JOIN Product p\n"
                            + "ON p.ProductId = ps.ProductID\n"
                            + "WHERE IsFlashSale = 1 \n"
                            + "    AND TimeFrame != 1 \n"
                            + "    AND startTime <= CAST('" + date + " 18:00:00' AS DATETIME)\n"
                            + "    AND (endTime >= CAST('" + date + " 22:00:00' AS DATETIME) OR endTime IS NULL);";
                }
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listSale.add(new ProductSaleDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getString(6)));

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
                return new ProductSaleDetailDTO(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductSaleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        ProductSaleDAO dao = new ProductSaleDAO();
        System.out.println(dao.getProductSaleDetailById(1));

    }

}
