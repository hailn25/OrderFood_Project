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

    public List<ListOrder> getListOrderById(String aid) {
        List<ListOrder> listOrderById = new ArrayList<>();
        String query = "select o.OrderId, o.OrderStatusId, o.AccountId, s.[Name], o.TotalMoney, o.[Name], o.Email, o.Phone, o.[Address], o.Note, o.CreateDate, o.FinishDate \n"
                + "from [Order] o\n"
                + "join Account a\n"
                + "on o.AccountId = a.AccountId\n"
                + "join Shipper s\n"
                + "on o.ShipperId = s.ShipperId\n"
                + "where o.AccountId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderById.add(new ListOrder(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getDate(11),
                        rs.getDate(12)
                ));
            }
        } catch (Exception e) {
        }
        return listOrderById;
    }
}
