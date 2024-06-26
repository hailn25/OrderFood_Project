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

    public ListOrder getListOrderById(String accountId) {
        String sql = "SELECT o.AccountId, a.Name, a.Email, a.Phone, a.Address, od.PaymentBy, od.PaymentStatus, o.CreateDate, o.Note, p.Name, p.Price, od.Quantity, o.TotalMoney\n"
                + "FROM [Order] o\n"
                + "JOIN Account a ON o.AccountId = a.AccountId\n"
                + "JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                + "JOIN Product p ON od.ProductId = p.ProductId\n"
                + "where a.AccountId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int aid = rs.getInt(1);
                String accountName = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String paymentBy = rs.getString(6);
                String paymantStatus = rs.getString(7);
                Date createDate = rs.getDate(8);
                String note = rs.getString(9);
                String ProductName = rs.getString(9);
                double price = rs.getDouble(11);
                int quantity = rs.getInt(12);
                double totalMoney = rs.getDouble(13);
                ListOrder lo = new ListOrder(aid, accountName, email, phone, address, paymentBy, paymantStatus, createDate, note, ProductName, price, quantity, totalMoney);
                return lo;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ListOrderDAO dao = new ListOrderDAO();
        dao.getListOrderById("6");
    }
}
