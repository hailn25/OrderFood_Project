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
import model.OrderDetailDTO_Huyvq;
import model.OrderDetailDTO_Huyvq_1;

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


//        public static void main(String[] args) throws Exception {
//        OrderDAO db = new OrderDAO();
//        db.confirmOrderOfCustomer("10");
//    }
        
}
