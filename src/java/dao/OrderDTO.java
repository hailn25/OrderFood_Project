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

public class OrderDTO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insertNewOrder(int accountId, double totalMoney, String name, String email, String phone, String address, String note) {
        LocalDate curDate = LocalDate.now();
        Date sqlDate = Date.valueOf(curDate);
        String sql = "INSERT INTO [Order] ( AccountId,  TotalMoney, Name, Email, Phone, Address, Note, CreateDate ) VALUES \n"
                + "(?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setDouble(2, totalMoney);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, note);
            ps.setDate(8, sqlDate);
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

    public void insertNewOrderDetail(int orderId, int productId, int quantity, double totalMoney, String paymentBy, boolean paymentStatus) {
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
            ps.setBoolean(6, paymentStatus);
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

    public  void updateQuantity(int pid, int quantity) {
    String sql = "UPDATE [dbo].[Product]\n" +
                 "SET [quantity] = [quantity] - ?\n" +
                 "WHERE [ProductId] = ?;";
    try {
        conn = new DBContext().getConnection();
        ps = conn.prepareStatement(sql);
        ps.setInt(1,quantity);  
        ps.setInt(2, pid);
          ps.executeUpdate(); 
    }catch (Exception e ){
        
    }
       

}
}
