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
        List<Product> list = new ArrayList<>();
        String query = "select p.ProductId, p.Name, p.Price, p.Description, p.ImageURL, p.CategoryId, p.RestaurantId, p.Quantity, p.Status, r.Address \n"
                + "from Product p\n"
                + "join Restaurant r\n"
                + "on p.RestaurantId = r.RestaurantId";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getProductById(String id) {
        String query = "select * from Product\n"
                + "where productId = ?";
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
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getBoolean(9),
                        rs.getString(10)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
            System.out.println(dao.getProductById("1"));
        }
}
