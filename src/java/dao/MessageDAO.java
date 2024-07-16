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
import model.MessageUser;
import model.RestaurantName;

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
    public ArrayList<RestaurantName> getListRestaurantName(int userId) {
        ArrayList<RestaurantName> listRestaurant = new ArrayList<>();
        try {

            String sql = "SELECT r.Name, a.ImageAvatar AS RestaurantImage, a.AccountId\n"
                    + "FROM Restaurant r\n"
                    + "INNER JOIN Conversation c ON r.AccountId = c.RestaurantId\n"
                    + "INNER JOIN Account a ON r.AccountId = a.AccountId\n"
                    + "WHERE c.UserId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                listRestaurant.add(new RestaurantName(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRestaurant;
    }

    public int getAccountIdByRestaurantId(int restaurantId) {
        int accountId = 0;
        try {
            String sql = "SELECT AccountId\n"
                    + "FROM Restaurant\n"
                    + "WHERE RestaurantId = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            rs = ps.executeQuery();
            while (rs.next()) {
                accountId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountId;
    }

    public ArrayList<MessageUser> getMessageUser(int senderId, int receiverId) {
        ArrayList<MessageUser> listMessage = new ArrayList<>();
        try {

            String sql = "	SELECT \n"
                    + "    m.MessageContent,\n"
                    + "    m.Timestamp,\n"
                    + "    sender.ImageAvatar AS SenderImageAvatar,\n"
                    + "    receiver.ImageAvatar AS ReceiverImageAvatar,\n"
                    + "    m.SenderId,\n"
                    + "    m.ReceiverId\n"
                    + "FROM \n"
                    + "    Message m\n"
                    + "INNER JOIN \n"
                    + "    Conversation c ON m.ConversationId = c.ConversationId \n"
                    + "INNER JOIN \n"
                    + "    Account sender ON m.SenderId = sender.AccountId \n"
                    + "INNER JOIN \n"
                    + "    Account receiver ON m.ReceiverId = receiver.AccountId \n"
                    + "WHERE \n"
                    + "    (m.SenderId = ? AND m.ReceiverId = ?) OR (m.SenderId = ? AND m.ReceiverId = ?)\n"
                    + "ORDER BY \n"
                    + "    m.Timestamp ASC;";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            ps.setInt(3, receiverId);
            ps.setInt(4, senderId);
            rs = ps.executeQuery();

            while (rs.next()) {
                listMessage.add(new MessageUser(rs.getString(1),
                        rs.getTimestamp(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listMessage;
    }

    public void insertMessageUser(int senderId, int receiverId, String messageContent) {
        try {
            int conversationId = 0;
            String sql = "SELECT [ConversationId]\n"
                    + "FROM [dbo].[Conversation]\n"
                    + "WHERE  [UserId] = ? and [RestaurantId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                conversationId = rs.getInt(1);
            }
            if (conversationId != 0) {
                String sql1 = "INSERT INTO Message (ConversationId, SenderId, ReceiverId, MessageContent)\n"
                        + "VALUES (?, ?, ?, ?);";
                Connection con1 = new DBContext().getConnection();
                PreparedStatement ps1 = con1.prepareStatement(sql1);
                ps1.setInt(1, conversationId);
                ps1.setInt(2, senderId);
                ps1.setInt(3, receiverId);
                ps1.setString(4, messageContent);

                ps1.executeUpdate();
            } else {
                String sql2 = "INSERT INTO Conversation (UserId, RestaurantId, StatusMessage)\n"
                        + "VALUES (?, ?, 0);";
                Connection con2 = new DBContext().getConnection();
                PreparedStatement ps2 = con2.prepareStatement(sql2);
                ps2.setInt(1, senderId);
                ps2.setInt(2, receiverId);

                ps2.executeUpdate();

//                int conversationId1 = 0;
//                String sql3 = "SELECT [ConversationId]\n"
//                        + "FROM [dbo].[Conversation]\n"
//                        + "WHERE  [UserId] = ? and [RestaurantId] = ?";
//                Connection con3 = new DBContext().getConnection();
//                PreparedStatement ps3 = con3.prepareStatement(sql3);
//                ps3.setInt(1, senderId);
//                ps3.setInt(2, receiverId);
//                ResultSet rs3 = ps3.executeQuery();
//                while (rs3.next()) {
//                    conversationId1 = rs3.getInt(1);
//                }
//                String sql4 = "INSERT INTO Message (ConversationId, SenderId, ReceiverId, MessageContent)\n"
//                        + "VALUES (?, ?, ?, ?);";
//                Connection con4 = new DBContext().getConnection();
//                PreparedStatement ps4 = con4.prepareStatement(sql4);
//                ps4.setInt(1, conversationId);
//                ps4.setInt(2, senderId);
//                ps4.setInt(3, receiverId);
//                ps4.setString(4, messageContent);
//
//                ps4.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<RestaurantName> searchMessageUser(String restaurantName) {
        ArrayList<RestaurantName> listRestaurant = new ArrayList<>();
        try {

            String sql = "SELECT DISTINCT \n"
                    + "r.Name, a.ImageAvatar AS RestaurantImage, a.AccountId\n"
                    + "FROM Restaurant r\n"
                    + "INNER JOIN Conversation c ON r.AccountId = c.RestaurantId\n"
                    + "INNER JOIN Account a ON r.AccountId = a.AccountId\n"
                    + "WHERE r.Name like '%" + restaurantName + "%'";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listRestaurant.add(new RestaurantName(rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MessageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRestaurant;
    }




    public static void main(String[] args) throws SQLException {
        MessageDAO m = new MessageDAO();
        System.out.println(m.listSeacrhRestaurantDAO("U"));
    }

}
