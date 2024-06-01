package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import model.Account;
import model.Cart;
import model.Item;
import model.Order;

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


    
}
