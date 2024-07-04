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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voucher;

/**
 *
 * @author quoch
 */
public class VoucherDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Voucher> getAllVoucher() {
        ArrayList<Voucher> listVoucher = new ArrayList<>();
        try {
            String sql = "select * from Voucher";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listVoucher.add(new Voucher(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listVoucher;
    }

    public void addVoucher(String voucherName, String description, int quantity, Date releaseDate, Date finishDate, int status) {
        try {
            String sql = "INSERT INTO [dbo].[Voucher] (\n"
                    + "    [VoucherName], [Description], [Quantity], [ReleaseDate], [FinishDate], [Status]\n"
                    + ") VALUES (?, ?, ?, ?, ?, ?);";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, voucherName);
            ps.setString(2, description);
            ps.setInt(3, quantity);
            ps.setDate(4, new java.sql.Date(releaseDate.getTime()));
            ps.setDate(5, new java.sql.Date(finishDate.getTime()));
            ps.setInt(6, status);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editVoucher(int voucherId, String voucherName, String description, int quantity, Date releaseDate, Date finishDate, int status) {
    Connection con = null;
    PreparedStatement ps = null;
    try {
        String sql = "UPDATE [dbo].[Voucher]\n"
                + "SET [VoucherName] = ?, \n"
                + "    [Description] = ?, \n"
                + "    [Quantity] = ?, \n"
                + "    [ReleaseDate] = ?, \n"
                + "    [FinishDate] = ?, \n"
                + "    [Status] = ?\n"
                + "WHERE [VoucherId] = ?;";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, voucherName);
        ps.setString(2, description);
        ps.setInt(3, quantity);
        ps.setDate(4, new java.sql.Date(releaseDate.getTime()));
        ps.setDate(5, new java.sql.Date(finishDate.getTime()));
        ps.setInt(6, status);
        ps.setInt(7, voucherId);

        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        // Đảm bảo đóng PreparedStatement và Connection
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}

   

    public void deleteVoucher(int voucherId) {
        try {
            String sql = "delete from [dbo].[Voucher] \n"
                    + "where [VoucherId] = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Voucher getVoucherById(int voucherId) {
        Voucher voucher = new Voucher();
        try {
            String sql = "select * from Voucher Where VoucherId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            rs = ps.executeQuery();

            while (rs.next()) {
                voucher = new Voucher(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }


}
