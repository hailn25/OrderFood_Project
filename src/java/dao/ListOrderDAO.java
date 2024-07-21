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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public List<ListOrder> getListOrderByIds(List<Integer> orderStatusIds, int accountId) {
        Map<Integer, ListOrder> orderMap = new HashMap<>();
        if (orderStatusIds == null || orderStatusIds.isEmpty()) {
            return new ArrayList<>(orderMap.values());
        }
        try {
            StringBuilder query = new StringBuilder("SELECT\n"
                    + "    o.AccountId,\n"
                    + "    o.OrderId,\n"
                    + "    p.ProductId,\n"
                    + "    p.Name,\n"
                    + "    p.Price,\n"
                    + "    p.ImageURL,\n"
                    + "    r.Name AS RestaurantName,\n"
                    + "    o.Name AS OrderName,\n"
                    + "    o.Phone,\n"
                    + "    o.Address,\n"
                    + "    o.Note,\n"
                    + "    od.Quantity,\n"
                    + "    od.TotalMoney,\n"
                    + "    os.OrderStatusId,\n"
                    + "    os.Status,\n"
                    + "    o.CreateDate\n"
                    + "FROM [dbo].[Order] o\n"
                    + "JOIN [dbo].[OrderDetail] od ON o.OrderId = od.OrderId\n"
                    + "JOIN [dbo].[Product] p ON od.ProductId = p.ProductId\n"
                    + "JOIN [dbo].[OrderStatus] os ON o.OrderStatusId = os.OrderStatusId\n"
                    + "JOIN [dbo].[Restaurant] r ON p.RestaurantId = r.RestaurantId\n"
                    + "WHERE o.OrderStatusId IN (");
            for (int i = 0; i < orderStatusIds.size(); i++) {
                query.append("?");
                if (i < orderStatusIds.size() - 1) {
                    query.append(",");
                }
            }
            query.append(") AND o.AccountId = ?");

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query.toString());
            for (int i = 0; i < orderStatusIds.size(); i++) {
                ps.setInt(i + 1, orderStatusIds.get(i));
            }
            ps.setInt(orderStatusIds.size() + 1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("OrderId");
                ListOrder listOrder;
                if (orderMap.containsKey(orderId)) {
                    listOrder = orderMap.get(orderId);
                } else {
                    listOrder = new ListOrder(rs.getInt("AccountId"),
                            orderId,
                            rs.getInt("ProductId"),
                            rs.getString("Name"),
                            rs.getDouble("Price"),
                            rs.getString("ImageURL"),
                            rs.getString("RestaurantName"),
                            rs.getString("OrderName"),
                            rs.getString("Phone"),
                            rs.getString("Address"),
                            rs.getString("Note"),
                            rs.getInt("Quantity"),
                            rs.getDouble("TotalMoney"),
                            rs.getInt("OrderStatusId"),
                            rs.getString("Status"),
                            rs.getDate("CreateDate")
                    );
                    orderMap.put(orderId, listOrder);
                }
                // Update the quantity and total money for the existing order
                listOrder.setQuantity(listOrder.getQuantity() + rs.getInt("Quantity"));
                listOrder.setTotalMoney(listOrder.getTotalMoney() + rs.getDouble("TotalMoney"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListOrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(orderMap.values());
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

    public boolean updateOrderStatus(int accountId, int orderId) throws ClassNotFoundException {
        try {
            // Câu lệnh SQL cập nhật
            String query = "update [Order]\n"
                    + "set OrderStatusId = 8\n"
                    + "where AccountId = ? and OrderId = ?";

            // Kết nối tới cơ sở dữ liệu
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            // Thiết lập các tham số cho câu lệnh
            ps.setInt(1, accountId);
            ps.setInt(2, orderId);

            // Thực thi câu lệnh cập nhật
            int rowsUpdated = ps.executeUpdate();

            // Kiểm tra xem có bản ghi nào được cập nhật không
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng các tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    

    public static void main(String[] args) throws ClassNotFoundException {
        ListOrderDAO dao = new ListOrderDAO();
        System.out.println(dao);
    }
}


