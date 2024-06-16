/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

public class AccountDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> listAccount = new ArrayList<>();
        String sql = "select * from Account";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listAccount.add(new Account(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getBoolean(10),
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getInt(14)
                ));
            }
        } catch (Exception e) {
        }
        return listAccount;
    }

    public Account getAccountByAId(String accountId) {
        String sql = "select *\n"
                + "from Account\n"
                + "where AccountId = ?";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId1 = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                boolean gender = rs.getBoolean(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String imageAvatar = rs.getString(8);
                int loginWith = rs.getInt(9);
                boolean status = rs.getBoolean(10);
                Date lastDateLogin = rs.getDate(11);
                Date createDate = rs.getDate(12);
                Date updateDate = rs.getDate(13);
                int roleId = rs.getInt(14);

                Account s = new Account(accountId1, email, password, fullName, gender, phone, address, imageAvatar, loginWith, status, lastDateLogin, createDate, updateDate, roleId);
                return s;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void editAccount(int roleId, int status, int accountId) throws SQLException, ClassNotFoundException {
        try {
            String sql = "update [dbo].[Account]\n"
                    + "set RoleId = ?, [Status] = ?\n"
                    + "where [AccountId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, roleId);
            ps.setInt(2, status);
            ps.setInt(3, accountId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteAccount(String aid) throws SQLException, ClassNotFoundException {
        try {
            String sql = "delete from [dbo].[Account]\n"
                    + "where [AccountId] = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, aid);
            ps.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Account checkLogin(String email, String password) {
        String sql = "select * from Account where [Email] = ? and [Password] = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getBoolean(10),
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getInt(14));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Account registerAccount(String email, String password, String fullName, boolean gender, String phone, String address) {
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String imageAvatar = "anh_mac_dinh.jpg";
            String sql = "INSERT INTO Account (Email, Password, Name, Gender, Phone, Address, imageAvatar, LoginWith, Status, lastDateLogin, createDate, updateDate, roleId) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            try {
                conn = new DBContext().getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, fullName);
            ps.setBoolean(4, gender);
            ps.setString(5, phone);
            ps.setString(6, address);
            ps.setString(7, imageAvatar);
            ps.setInt(8, 0);
            ps.setBoolean(9, true);
            ps.setString(10, date);
            ps.setString(11, date);
            ps.setString(12, date);
            ps.setInt(13, 2);
            ps.executeUpdate();
            return new Account(0, email, password, date, gender, phone, address, imageAvatar, 0, gender, java.sql.Date.valueOf(date), java.sql.Date.valueOf(date), java.sql.Date.valueOf(date), 2);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkAccountExist(String email) {
        try {
            String sql = "select * from Account where Email = ?";
            try {
                conn = new DBContext().getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void UpdateLastDateLogin(String email) {
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String sql = "UPDATE Account SET LastDateLogin = ? WHERE Email = ?";
            try {
                conn = new DBContext().getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ChangePassword(String email, String password) throws SQLException {
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String sql = "UPDATE Account SET [Password] = ? , UpdateDate = ? where Email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, date);
            ps.setString(3, email);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccountByEmail(String email) throws SQLException {
        try {
            String sql = "select * from Account where Email = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getBoolean(10),
                        rs.getDate(11),
                        rs.getDate(12),
                        rs.getDate(13),
                        rs.getInt(14));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccountLoginGoogle(String email, int loginWith) throws SQLException {
        try {
            LocalDate curDate = LocalDate.now();
            String date = curDate.toString();
            String sql = "INSERT INTO [dbo].[Account] ([Email],[LoginWith],[Status],[LastDateLogin],[CreateDate],[UpdateDate],[RoleId]) VALUES (?,?,?,?,?,?,?)";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setInt(2, loginWith);
            ps.setBoolean(3, true);
            ps.setString(4, date);
            ps.setString(5, date);
            ps.setString(6, date);
            ps.setInt(7, 2);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws SQLException {
        AccountDAO ac = new AccountDAO();
        ac.getAccountByAId("1");
    }
}
