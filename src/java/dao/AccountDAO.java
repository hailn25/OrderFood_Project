/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Vu Huy
 */
public class AccountDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        String sql = "select *\n"
                + "from Account";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int accountId = rs.getInt(1);
                String email = rs.getString(2);
                String password = rs.getString(3);
                String fullName = rs.getString(4);
                boolean gender = rs.getBoolean(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String imageAvatar = rs.getString(8);
                boolean status = rs.getBoolean(9);
                Date lastDateLogin = rs.getDate(10);
                Date createDate = rs.getDate(11);
                Date updateDate = rs.getDate(12);
                int roleId = rs.getInt(13);

                Account s = new Account(accountId, email, password, fullName, gender, phone, address, imageAvatar, status, lastDateLogin, createDate, updateDate, roleId);

                list.add(s);
            }
        } catch (Exception e) {
        }
        return list;
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
                boolean status = rs.getBoolean(9);
                Date lastDateLogin = rs.getDate(10);
                Date createDate = rs.getDate(11);
                Date updateDate = rs.getDate(12);
                int roleId = rs.getInt(13);

                Account s = new Account(accountId1, email, password, fullName, gender, phone, address, imageAvatar, status, lastDateLogin, createDate, updateDate, roleId);
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
}
