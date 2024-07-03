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
import model.ShowOrder;

/**
 *
 * @author ADMIN
 */
public class ListOrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<ListOrder> getListOrderById(int accountId) {
        List<ListOrder> listOrderById = new ArrayList<>();
        try {

            String query = "SELECT o.AccountId, a.Name, a.Email, a.Phone, a.Address, od.PaymentBy, od.PaymentStatus, o.CreateDate, o.Note, p.Name, p.Price, od.Quantity, o.TotalMoney\n"
                    + "FROM [Order] o\n"
                    + "JOIN Account a ON o.AccountId = a.AccountId\n"
                    + "JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                    + "JOIN Product p ON od.ProductId = p.ProductId\n"
                    + "WHERE a.AccountId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
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
                        rs.getDouble(13)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listOrderById;
    }

    public List<ShowOrder> getShowOrderByaId(int accountId) {
        List<ShowOrder> listShowOrderByaId = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            String query = "select o.AccountId, o.OrderId, o.Name, o.Phone, o.Address, o.Note, o.CreateDate, od.TotalMoney, os.Status\n"
                    + "from [Order] o\n"
                    + "join OrderDetail od\n"
                    + "on o.OrderId = od.OrderId\n"
                    + "join OrderStatus os\n"
                    + "on o.OrderStatusId = os.OrderStatusId\n"
                    + "where o.AccountId = ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();

            while (rs.next()) {
                ShowOrder order = new ShowOrder();
                order.setAccountId(rs.getInt("AccountId"));
                order.setOrderId(rs.getInt("OrderId"));
                order.setName(rs.getString("Name"));
                order.setAddress(rs.getString("Address"));
                order.setNote(rs.getString("Note"));
                order.setCreateDate(rs.getDate("CreateDate"));
                order.setTotalMoney(rs.getDouble("TotalMoney"));
                order.setStatus(rs.getString("Status"));
                listShowOrderByaId.add(order);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, "Error retrieving orders by account ID", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, "Database driver class not found", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListOrderDAO.class.getName()).log(Level.WARNING, "Error closing resources", ex);
            }
        }

        return listShowOrderByaId;
    }

    public static void main(String[] args) {
        ListOrderDAO dao = new ListOrderDAO();
        System.out.println(dao.getShowOrderByaId(6));
    }
}
