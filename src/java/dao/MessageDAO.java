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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MessageUser;
import model.RestaurantName;

/**
 *
 * @author quoch
 */
public class MessageDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<RestaurantName> getListRestaurantName(int userId) {
        ArrayList<RestaurantName> listRestaurant = new ArrayList<>();
        try {

            String sql = "SELECT r.Name, a.ImageAvatar AS RestaurantImage, a.AccountId\n"
                    + "FROM Restaurant r\n"
                    + "INNER JOIN Conversation c ON r.AccountId = c.RestaurantId\n"
                    + "INNER JOIN Account a ON r.AccountId = a.AccountId\n"
                    + "WHERE c.UserId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
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
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
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
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
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
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, senderId);
            ps.setInt(2, receiverId);
            rs = ps.executeQuery();
            while (rs.next()) {
                conversationId = rs.getInt(1);
            }
            String sql1 = "INSERT INTO Message (ConversationId, SenderId, ReceiverId, MessageContent)\n"
                    + "VALUES (?, ?, ?, ?);";
            Connection con1 = new DBContext().getConnection();
            PreparedStatement ps1 = con1.prepareStatement(sql1);
            ps1.setInt(1, conversationId);
            ps1.setInt(2, senderId);
            ps1.setInt(3, receiverId);
            ps1.setString(4, messageContent);

            ps1.executeUpdate();

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
                    + "WHERE r.Name like '%"+restaurantName+"%'";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
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

    public static void main(String[] args) {
        MessageDAO m = new MessageDAO();

        System.out.println(m.getListRestaurantName(6));
        for (MessageUser ms : m.getMessageUser(6, 8)) {
            System.out.println(ms.toString());
        }
        for (RestaurantName r : m.searchMessageUser("bánh")) {
            System.out.println(r.toString());
        }
    }
}
