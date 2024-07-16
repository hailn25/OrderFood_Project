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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CustomerName;
import model.MessageRestaurant1;

/**
 *
 * @author hailt
 */
public class MessageDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<CustomerName> getListCustomerName(int restaurantId) throws SQLException {
        try {
            List<CustomerName> list = new ArrayList<>();
            String sql = "SELECT DISTINCT \n"
                    + "    a.Name,\n"
                    + "    a.ImageAvatar,\n"
                    + "	c.UserId\n"
                    + "FROM \n"
                    + "    Conversation c\n"
                    + "INNER JOIN \n"
                    + "    Account a ON c.UserId = a.AccountId\n"
                    + "WHERE \n"
                    + "    c.RestaurantId = ?";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new CustomerName(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
            return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getRestaurantIdByAccountId(int accountId) throws SQLException {
        try {

            String sql = "SELECT r.RestaurantId\n"
                    + "FROM [dbo].[Account] a\n"
                    + "JOIN [dbo].[Restaurant] r\n"
                    + "ON a.AccountId = r.AccountId\n"
                    + "where a.AccountId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<MessageRestaurant1> getListMessageOfRestaurant(int senderId, int receiveId) throws SQLException {
        List<MessageRestaurant1> list = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "m.MessageContent,\n"
                    + "m.Timestamp,\n"
                    + "sender.ImageAvatar AS SenderImageAvatar,\n"
                    + "receiver.ImageAvatar AS ReceiverImageAvatar,\n"
                    + "m.SenderId,\n"
                    + "m.ReceiverId\n"
                    + "FROM Message m\n"
                    + "INNER JOIN \n"
                    + "Conversation c ON m.ConversationId = c.ConversationId \n"
                    + "INNER JOIN \n"
                    + "Account sender ON m.SenderId = sender.AccountId \n"
                    + "INNER JOIN \n"
                    + " Account receiver ON m.ReceiverId = receiver.AccountId \n"
                    + "    WHERE\n"
                    + " (m.SenderId = ? AND m.ReceiverId = ?) OR (m.SenderId = ? AND m.ReceiverId = ?)\n"
                    + "       ORDER BY \n"
                    + "  m.Timestamp ASC";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiveId);
            ps.setInt(4, senderId);
            ps.setInt(3, receiveId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MessageRestaurant1(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getMessageId(int senderId, int receiverId) throws SQLException {
        try {
            String sql = "  select m.ConversationId\n"
                    + "  from  [dbo].[Message] m\n"
                    + "  where m.SenderId = ? and m.ReceiverId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                return id;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void insertMessageRestaurant(int conversationId, int senderId, int receiverId, String message) throws SQLException {
        try {
            String sql = "INSERT INTO [dbo].[Message] \n"
                    + "    ( [ConversationId], [SenderId], [ReceiverId], [MessageContent])\n"
                    + "VALUES \n"
                    + "    ( ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, conversationId);
            ps.setInt(2, senderId);
            ps.setInt(3, receiverId);
            ps.setString(4, message);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<CustomerName> listSeacrhRestaurantDAO(String text) throws SQLException {
        List<CustomerName> list = new ArrayList<>();
        try {
            String sql = " \n"
                    + " SELECT DISTINCT \n"
                    + "    a.Name,\n"
                    + "    a.ImageAvatar,\n"
                    + "	c.UserId\n"
                    + "FROM \n"
                    + "     Account a \n"
                    + "	 join Conversation c\n"
                    + "	 on a.AccountId = c.UserId\n"
                    + "where a.Name Like ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new CustomerName(rs.getString(1), rs.getString(2),rs.getInt(3)));
               
            }
             return list;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        MessageDAO m = new MessageDAO();
        System.out.println(m.listSeacrhRestaurantDAO("U"));
    }

}
