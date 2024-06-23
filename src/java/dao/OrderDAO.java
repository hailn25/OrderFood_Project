package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDTO;
import model.OrderDetailDTO;
import model.OrderDetailDTO_Huyvq;
import model.OrderDetailDTO_Huyvq_1;
import model.ViewDetail;

public class OrderDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(Cart cart, Account u) {
        LocalDate curDate = LocalDate.now();
        Date sqlDate = Date.valueOf(curDate);

        try {
            // Insert new order
            String insertOrderSQL = "INSERT INTO [Order] (AccountId, TotalMoney, CreateDate) VALUES (?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, u.getAccountId()); // accountId ở vị trí thứ nhất trong câu lệnh SQL
            ps.setDouble(2, cart.getTotalMoney()); // totalMoney ở vị trí thứ hai trong câu lệnh SQL
            ps.setDate(3, sqlDate); // createDate ở vị trí thứ ba trong câu lệnh SQL

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            String insertItemSQL = "INSERT INTO [OrderDetail] (OrderId, ProductId, Quantity, TotalMoney, UpdateDate) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(insertItemSQL);

            for (Item item : cart.getItems()) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getProduct().getProductId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getPrice() * item.getQuantity());
                ps.setDate(5, sqlDate);

                ps.addBatch();
            }

            ps.executeBatch();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetailDTO_Huyvq getOrderDetailById(int orderDetailId) throws SQLException, Exception {

        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE OrderDetail.OrderDetailId = ?";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, orderDetailId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId1 = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId1, name, quantity, totalMoney, orderStatusId, imageURL);
            return s;
        }
        return null;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getAllOrderByRestaurantId(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ?";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_1(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 1";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_2(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 2";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_3(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 3";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_4(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 4";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_5(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 5";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_6(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 6";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public ArrayList<OrderDetailDTO_Huyvq> getOrderStatusByRestaurantId_7(int restaurantId) throws SQLException, Exception {
        ArrayList<OrderDetailDTO_Huyvq> list = new ArrayList<>();
        String sql = "SELECT        OrderDetail.OrderDetailId, Product.Name, OrderDetail.Quantity, OrderDetail.TotalMoney, OrderStatus.OrderStatusId, Product.ImageURL\n"
                + "FROM            [Order] INNER JOIN\n"
                + "                         OrderDetail ON [Order].OrderId = OrderDetail.OrderId INNER JOIN\n"
                + "                         OrderStatus ON [Order].OrderStatusId = OrderStatus.OrderStatusId INNER JOIN\n"
                + "                         Product ON OrderDetail.ProductId = Product.ProductId\n"
                + "WHERE Product.RestaurantId = ? and OrderStatus.OrderStatusId = 7";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int orderDetailId = rs.getInt(1);
            String name = rs.getString(2);
            int quantity = rs.getInt(3);
            double totalMoney = rs.getDouble(4);
            int orderStatusId = rs.getInt(5);
            String imageURL = rs.getString(6);
            OrderDetailDTO_Huyvq s = new OrderDetailDTO_Huyvq(orderDetailId, name, quantity, totalMoney, orderStatusId, imageURL);
            list.add(s);
        }
        return list;
    }

    public void confirmOrderOfCustomer(String orderDetailId) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE o\n"
                + "SET o.OrderStatusId = 1\n"
                + "FROM [Order] o\n"
                + "INNER JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                + "WHERE od.OrderDetailId = ?;";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setString(1, orderDetailId);
        ps.executeUpdate();
    }

    public void cancelOrderOfCustomer(int orderDetailId) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE o\n"
                + "SET o.OrderStatusId = 7\n"
                + "FROM [Order] o\n"
                + "INNER JOIN OrderDetail od ON o.OrderId = od.OrderId\n"
                + "WHERE od.OrderDetailId = ?;";
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, orderDetailId);
        ps.executeUpdate();
    }

    public ArrayList<OrderDetailDTO_Huyvq_1> getOrderDetailByoid(int orderId) {
        ArrayList<OrderDetailDTO_Huyvq_1> listOrderDetails = new ArrayList<>();
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
                OrderDetailDTO_Huyvq_1 orderDetail = new OrderDetailDTO_Huyvq_1(
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

    public ViewDetail getViewDetailslByOid(int orderId) {
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

    public ArrayList<OrderDetailDTO> getOrderDetailByOid(int orderId) {
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

    public void updateOrderStatus(int orderId, int orderStatusId) {
        String sql = "UPDATE [dbo].[Order] SET OrderStatusId = ? WHERE OrderId = ?";
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

//        public static void main(String[] args) throws Exception {
//        OrderDAO db = new OrderDAO();
//        db.confirmOrderOfCustomer("10");
//    }
}
