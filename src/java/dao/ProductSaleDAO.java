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
import model.ProductSale;

/**
 *
 * @author ADMIN
 */
public class ProductSaleDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ProductSale> getProductSale() {
        List<ProductSale> listProductSale = new ArrayList<>();
        String query = "select p.ProductId, p.Name, p.ImageURL, ps.SalePrice, ps.Discount, ps.Quantity, r.RateStar \n"
                + "  from Product p\n"
                + "  join Product_Sale ps\n"
                + "  on p.ProductId = ps.ProductID\n"
                + "  join Restaurant r\n"
                + "  on p.RestaurantId = r.RestaurantId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                listProductSale.add(new ProductSale(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getInt(6),
                        rs.getInt(7)
                ));
            }
        } catch (Exception e) {
        }
        return listProductSale;
    }
    public static void main(String[] args) {
        ProductSaleDAO dao = new ProductSaleDAO();
        List<ProductSale> listProductSale = dao.getProductSale();
        for (ProductSale productSale : listProductSale) {
            System.out.println(productSale);
            
        }
    }
}
