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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ListOrder;
import model.OrderDTO;
import model.ShowOrder;

/**
 *
 * @author ADMIN
 */
public class ListOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ListOrder> getListOrderById(int orderStatusId, int accountId) {
        List<ListOrder> listOrderById = new ArrayList<>();
        try {

            String query = "SELECT\n"
                    + "    o.AccountId,\n"
                    + "    p.Name,\n"
                    + "    p.Price,\n"
                    + "    p.ImageURL,\n"
                    + "    r.Name,\n"
                    + "    o.Name,\n"
                    + "    o.Phone,\n"
                    + "    o.Address,\n"
                    + "    o.Note,\n"
                    + "    od.Quantity,\n"
                    + "    od.TotalMoney,\n"
                    + "	os.OrderStatusId,\n"
                    + "	os.Status\n"
                    + "FROM [dbo].[Order] o\n"
                    + "JOIN [dbo].[OrderDetail] od ON o.OrderId = od.OrderId\n"
                    + "JOIN [dbo].[Product] p ON od.ProductId = p.ProductId\n"
                    + "JOIN [dbo].[OrderStatus] os ON o.OrderStatusId = os.OrderStatusId\n"
                    + "JOIN [dbo].[Restaurant] r ON p.RestaurantId = r.RestaurantId\n"
                    + "WHERE o.OrderStatusId = ? AND o.AccountId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderStatusId);
            ps.setInt(2, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderById.add(new ListOrder(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getDouble(11),
                        rs.getInt(12),
                        rs.getString(13)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderById;
    }

    public List<OrderDTO> getListOrderById_V1(int orderStatusId, int accountId) {
        List<OrderDTO> listOrderById_V1 = new ArrayList<>();
        try {

            String query = "SELECT [Order].OrderId, [Order].Name, [Order].Phone, [Order].Address, [Order].Note, [Order].CreateDate, [Order].TotalMoney, OrderStatus.Status\n"
                    + "FROM [Order] INNER JOIN\n"
                    + "OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId\n"
                    + "where OrderStatus.OrderStatusId = ? AND [Order].accountId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, orderStatusId);
            ps.setInt(2, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listOrderById_V1.add(new OrderDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDouble(7),
                        rs.getString(8)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderById_V1;
    }

    public static void main(String[] args) {
        ListOrderDAO dao = new ListOrderDAO();
        System.out.println(dao.getListOrderById(1, 6));
    }
}
