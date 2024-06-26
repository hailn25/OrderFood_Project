package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderAccount;
import model.OrderDTO;
import model.OrderDetailDTO;
import model.ViewDetail;

public class OrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertNewOrder(int orderStatusId, int accountId, double totalMoney, String name, String email, String phone, String address, String note) {
        LocalDate curDate = LocalDate.now();
        Date sqlDate = Date.valueOf(curDate);
        String sql = "INSERT INTO [Order] ( OrderStatusId ,AccountId, TotalMoney, Name, Email, Phone, Address, Note, CreateDate  ) VALUES \n"
                + "               (?, ?, ?, ?, ?,?, ?, ?, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderStatusId);
            ps.setInt(2, accountId);
            ps.setDouble(3, totalMoney);
            ps.setString(4, name);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setString(8, note);
            ps.setDate(9, sqlDate);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public ArrayList<OrderDTO> getAllOrder() {
        ArrayList<OrderDTO> listOrders = new ArrayList<>();
        try {
            String sql = "SELECT [Order].OrderId, [Order].Name, [Order].Phone, [Order].Address, [Order].Note, [Order].CreateDate, [Order].TotalMoney, OrderStatus.Status\n"
                    + "FROM [Order] INNER JOIN\n"
                    + "OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
                listOrders.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listOrders;
    }

    public OrderAccount getOrdertByAId(String aid) {
        String sql = "SELECT [Order].[AccountId], [Order].OrderId, [Order].Name, [Order].Phone, [Order].Address, [Order].Note, [Order].CreateDate, [Order].TotalMoney, OrderStatus.Status\n"
                + "FROM [Order] INNER JOIN\n"
                + "OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId\n"
                + "where [Order].AccountId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, aid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt(1);
                int orderId = rs.getInt(2);
                String name = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);
                String note = rs.getString(6);
                Date createDate = rs.getDate(7);
                double totalMoney = rs.getDouble(8);
                int status = rs.getInt(9);
                OrderAccount oa = new OrderAccount(accountId, orderId, name, phone, address, note, createDate, totalMoney, status);
                return oa;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<OrderDTO> getAllOrder(int OrderStatusId) {
        ArrayList<OrderDTO> listOrders = new ArrayList<>();
        try {
            String sql = "SELECT [Order].OrderId, [Order].Name, [Order].Phone, [Order].Address, [Order].Note, [Order].CreateDate, [Order].TotalMoney, OrderStatus.Status\n"
                    + "                                      FROM     [Order] INNER JOIN\n"
                    + "                                                    OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId\n"
                    + "                   							   where OrderStatus.OrderStatusId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, OrderStatusId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDouble(7),
                        rs.getString(8)
                );
                listOrders.add(order);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listOrders;
    }

    public int getOrderID() {
        String sql = "SELECT top 1 * FROM [dbo].[Order]  ORDER BY  [OrderId ]DESC";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getOrderDetailId() {
        String sql = "SELECT TOP 1 OrderDetailId\n"
                + "FROM OrderDetail\n"
                + "ORDER BY OrderDetailID DESC;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void insertNewOrderDetail(int orderId, int productId, int quantity, double totalMoney, String paymentBy, String paymentStatus) {
        LocalDate curDate = LocalDate.now();
        Date sqlDate = Date.valueOf(curDate);
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([OrderID]\n"
                + "           ,[ProductID]\n"
                + "           ,[Quantity]\n"
                + "           ,[TotalMoney]\n"
                + "           ,[PaymentBy]\n"
                + "           ,[PaymentStatus]\n"
                + "           ,[UpdateDate])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, totalMoney);
            ps.setString(5, paymentBy);
            ps.setString(6, paymentStatus);
            ps.setDate(7, sqlDate);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePaymentStatus(int oid, String paymentStatus) {
        String sql = "UPDATE [dbo].[OrderDetail]\n"
                + "SET [PaymentStatus] = ?\n"
                + "WHERE [OrderID] = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, paymentStatus);
            ps.setInt(2, oid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void getQuantity(int pid) {
        String sql = "select quantity from Product where ProductId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (Exception e) {
        }

    }

    public String getPayment(int orderDetailId) {
        String sql = "SELECT PaymentBy FROM OrderDetail WHERE OrderDetailId = ?";
        try (Connection conn = new DBContext().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderDetailId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("PaymentBy");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateQuantity(int pid, int quantity) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "SET [quantity] = [quantity] - ?\n"
                + "WHERE [ProductId] = ?;";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, pid);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void updateOrderStatus(int orderId, int orderStatusId) {
        String sql = "UPDATE [Order_FoodV5].[dbo].[Order] SET OrderStatusId = ? WHERE OrderId = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderStatusId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getOrderStatusById(int orderId) {
        String sql = "SELECT OrderStatus.OrderStatusId\n"
                + "FROM [Order] INNER JOIN\n"
                + "     OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId\n"
                + "WHERE [Order].OrderId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int status = 0;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            if (rs.next()) {
                status = rs.getInt("OrderStatusId");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return status;
    }

    public ArrayList<OrderDetailDTO> getOrderDetailByoid(int orderId) {
        ArrayList<OrderDetailDTO> listOrderDetails = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    OrderDetail.OrderDetailId, \n"
                    + "    OrderDetail.OrderId,\n"
                    + "	Product.Name ,\n"
                    + "    Product.Price, \n"
                    + "    OrderDetail.Quantity, \n"
                    + "    [Order].TotalMoney\n"
                    + "FROM  [Order]\n"
                    + "INNER JOIN \n"
                    + "    OrderDetail ON [Order].OrderId = OrderDetail.OrderId\n"
                    + "INNER JOIN \n"
                    + "    Product ON OrderDetail.ProductId = Product.ProductId\n"
                    + "WHERE \n"
                    + "    [Order].OrderId = ?; ";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetailDTO orderDetail = new OrderDetailDTO(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDouble(6)
                );
                listOrderDetails.add(orderDetail);

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return listOrderDetails;
    }

    public ViewDetail getViewDetailslByoid(int orderId) {
        ViewDetail viewDetail = new ViewDetail();
        try {
            String sql = "SELECT o.OrderId, o.Name, o.Email, o.Phone, o.Address, o.Note, o.CreateDate, od.PaymentBy, od.PaymentStatus\n"
                    + "FROM [Order] o\n"
                    + "INNER JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                    + "WHERE o.OrderId = ?;";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                viewDetail = new ViewDetail(
                        rs.getInt("OrderId"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Note"),
                        rs.getDate("CreateDate"),
                        rs.getString("PaymentBy"),
                        rs.getString("PaymentStatus")
                );
            }
        } catch (Exception ex) {

        }
        return viewDetail;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        System.out.println(orderDAO.getAllOrder());

    }
}
