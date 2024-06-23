/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.ListOrder;

/**
 *
 * @author ADMIN
 */
public class ListOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ListOrder> getListOrderById(String accountId) {
        List<ListOrder> listOrderById = new ArrayList<>();
        String query = "SELECT o.AccountId, a.Name, a.Email, a.Phone, a.Address, od.PaymentBy, od.PaymentStatus, o.CreateDate, o.Note, p.Name, p.Price, od.Quantity, o.TotalMoney\n"
                + "FROM [Order] o\n"
                + "JOIN Account a ON o.AccountId = a.AccountId\n"
                + "JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                + "JOIN Product p ON od.ProductId = p.ProductId\n"
                + "where a.AccountId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderById.add(new ListOrder(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getDouble(11),
                        rs.getInt(12),
                        rs.getDouble(13)
                ));
            }
        } catch (Exception e) {
        }
        return listOrderById;
    }
}
