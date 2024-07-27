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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ListVoucher;
import model.Voucher;
import model.VoucherOfUser;

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
                        rs.getInt(7),
                        rs.getFloat(8),
                        rs.getInt(9),
                        rs.getInt(10)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listVoucher;
    }

    public ArrayList<Voucher> getAllVoucherWithQuantityByAccountIdFree(int accountId) {
        ArrayList<Voucher> listVoucher = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = """
                         SELECT Voucher.VoucherId, Voucher.VoucherName, Voucher.Description, Voucher.Quantity, Voucher.ReleaseDate, Voucher.FinishDate, Voucher.Status, Voucher.Discount, Voucher.VoucherCategoryId, Voucher.RestaurantId
                         FROM Voucher
                         LEFT JOIN AccountVoucher ON Voucher.VoucherId = AccountVoucher.VoucherId 
                         WHERE Voucher.VoucherCategoryId = 1 AND AccountVoucher.AccountId =?
                         GROUP BY Voucher.VoucherId, Voucher.VoucherName, Voucher.Description, Voucher.Quantity, Voucher.ReleaseDate, Voucher.FinishDate, Voucher.Status, Voucher.Discount, Voucher.VoucherCategoryId, Voucher.RestaurantId""";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Voucher voucher = new Voucher(
                        rs.getInt("VoucherId"),
                        rs.getString("VoucherName"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDate("ReleaseDate"),
                        rs.getDate("FinishDate"),
                        rs.getInt("Status"),
                        rs.getFloat("Discount"),
                        rs.getInt("VoucherCategoryId"),
                        rs.getInt("RestaurantId")
                );
                listVoucher.add(voucher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các resource
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listVoucher;
    }

    public ArrayList<Voucher> getAllVoucherWithQuantityByAccountIdR(int accountId, List<Integer> list) {
        ArrayList<Voucher> listVoucher = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return listVoucher;
        }

        // Build the string for the IN clause
        StringBuilder listStr = new StringBuilder("(");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                listStr.append(", ");
            }
            listStr.append("?");
        }
        listStr.append(")");

        // Update the query to include placeholders for the IN clause
        String query = "SELECT Voucher.VoucherId, Voucher.VoucherName, Voucher.Description, Voucher.Quantity, "
                + "Voucher.ReleaseDate, Voucher.FinishDate, Voucher.Status, Voucher.Discount, "
                + "Voucher.VoucherCategoryId, Voucher.RestaurantId "
                + "FROM Voucher "
                + "LEFT JOIN AccountVoucher ON Voucher.VoucherId = AccountVoucher.VoucherId "
                + "WHERE Voucher.VoucherCategoryId = 2 "
                + "AND Voucher.RestaurantId IN " + listStr.toString() + " "
                + "AND AccountVoucher.AccountId = ? "
                + "GROUP BY Voucher.VoucherId, Voucher.VoucherName, Voucher.Description, Voucher.Quantity, "
                + "Voucher.ReleaseDate, Voucher.FinishDate, Voucher.Status, Voucher.Discount, "
                + "Voucher.VoucherCategoryId, Voucher.RestaurantId";

        try (Connection con = new DBContext().getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            // Set the IN clause parameters
            for (int i = 0; i < list.size(); i++) {
                ps.setInt(i + 1, list.get(i));
            }
            // Set the AccountId parameter
            ps.setInt(list.size() + 1, accountId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int voucherId = rs.getInt("VoucherId");
                    String voucherName = rs.getString("VoucherName");
                    String description = rs.getString("Description");
                    int quantity = rs.getInt("Quantity");
                    Date releaseDate = rs.getDate("ReleaseDate");
                    Date finishDate = rs.getDate("FinishDate");
                    int status = rs.getInt("Status");
                    float discount = rs.getFloat("Discount");
                    int voucherCategoryId = rs.getInt("VoucherCategoryId");
                    int rId = rs.getInt("RestaurantId");

                    Voucher voucher = new Voucher(voucherId, voucherName, description, quantity, releaseDate,
                            finishDate, status, discount, voucherCategoryId, rId);
                    listVoucher.add(voucher);
                }
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, "Error fetching vouchers", ex);
        }

        return listVoucher;
    }

    public void addVoucher(String voucherName, String description, int quantity, Date releaseDate, Date finishDate, int status, float discount, int voucherCategoryId) {
        try {
            String sql = "INSERT INTO [dbo].[Voucher] (\n"
                    + "    [VoucherName], [Description], [Quantity], [ReleaseDate], [FinishDate], [Status],[Discount],[VoucherCategoryId]\n"
                    + ") VALUES (?, ?, ?, ?, ?, ?,?,?);";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, voucherName);
            ps.setString(2, description);
            ps.setInt(3, quantity);
            ps.setDate(4, new java.sql.Date(releaseDate.getTime()));
            ps.setDate(5, new java.sql.Date(finishDate.getTime()));
            ps.setInt(6, status);
            ps.setFloat(7, discount);
            ps.setInt(8, voucherCategoryId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editVoucher(int voucherId, String voucherName, String description, int quantity, Date releaseDate, Date finishDate, int status, float discount, int voucherCategoryId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE [dbo].[Voucher]\n"
                    + "SET [VoucherName] = ?, \n"
                    + "    [Description] = ?, \n"
                    + "    [Quantity] = ?, \n"
                    + "    [ReleaseDate] = ?, \n"
                    + "    [FinishDate] = ?, \n"
                    + "    [Status] = ?, \n"
                    + "    [Discount] = ?, \n"
                    + "    [VoucherCategoryId] = ? \n"
                    + "WHERE [VoucherId] = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, voucherName);
            ps.setString(2, description);
            ps.setInt(3, quantity);
            ps.setDate(4, new java.sql.Date(releaseDate.getTime()));
            ps.setDate(5, new java.sql.Date(finishDate.getTime()));
            ps.setInt(6, status);
            ps.setFloat(7, discount);
            ps.setInt(8, voucherCategoryId);
            ps.setInt(9, voucherId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

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
                        rs.getInt(7),
                        rs.getFloat(8),
                        rs.getInt(9),
                        rs.getInt(10));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return voucher;
    }

    public int getDiscountByVoucherId(int voucherId) {
        String sql = "SELECT [Discount]\n"
                + "FROM [dbo].[Voucher]\n"
                + "WHERE [VoucherId] = ?;";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Discount");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return 0;
    }

    public void insertVoucherAccountDetails(String voucherName, String description, int quantity, String releaseDate, String finishDate, String status, String discount, String voucherCategoryId, String restauranId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);  // Start transaction

            // Insert into Voucher table
            String sql = "INSERT INTO Voucher ([VoucherName], [Description], [Quantity], [ReleaseDate], [FinishDate], [Status], [Discount], [VoucherCategoryId], [RestaurantId])"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, voucherName);
            ps.setString(2, description);
            ps.setInt(3, quantity);
            ps.setString(4, releaseDate);
            ps.setString(5, finishDate);
            ps.setString(6, status);
            ps.setString(7, discount);
            ps.setString(8, voucherCategoryId);
            ps.setString(9, restauranId);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int voucherId = 0;
            if (rs.next()) {
                voucherId = rs.getInt(1);
            }
            conn.commit();
        } catch (Exception ex) {
            if (conn != null) {
                conn.rollback();
            }
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getQuantity(int accountId, int voucherId) {
        String sql = "SELECT COUNT(*) AS VoucherCount\n"
                + "FROM AccountVoucher\n"
                + "WHERE AccountId = ? AND VoucherId = ?;";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, voucherId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("VoucherCount");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Đóng tất cả các resource
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public void updateQuantity(int accountId, int voucherId) {
        String sql = "";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, voucherId);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<ListVoucher> getVoucherByAccountId(int accountId) {
        List<ListVoucher> listVoucher = new ArrayList<>();
        String query = "select av.AccountId ,v.VoucherId, v.VoucherName,v.Description, v.Discount, v.FinishDate, v.ReleaseDate, v.Quantity, v.RestaurantId, v.Status, v.VoucherCategoryId\n"
                + "from Voucher v\n"
                + "join AccountVoucher av\n"
                + "on v.VoucherId = av.VoucherId\n"
                + "where av.AccountId = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new ListVoucher(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getFloat(9),
                        rs.getInt(10),
                        rs.getInt(11)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listVoucher;
    }

    public ArrayList<VoucherOfUser> getAllVoucherFreeshipOfUser(int accountId) {
        ArrayList<VoucherOfUser> listVoucher = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = """
                     SELECT distinct v.*, 
                                                           CASE WHEN av.AccountId IS NOT NULL THEN 1 ELSE 0 END AS hasVoucher,
                                                           a.ImageAvatar AS Image,
                                                           r.Name as RestaurantName
                                                           FROM Voucher v
                                                           LEFT JOIN AccountVoucher av ON v.VoucherId = av.VoucherId and av.AccountId = ?
                                                           LEFT JOIN Restaurant r ON v.RestaurantId = r.RestaurantId
                                                           LEFT JOIN Account a ON r.AccountId = a.AccountId
                                                           WHERE v.VoucherCategoryId = 1
                                                           AND v.Status = 1
                                                           AND v.Quantity > 0 
                                                           AND v.FinishDate >= CAST(GETDATE() AS DATE);
                     """;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new VoucherOfUser(
                        rs.getInt("VoucherId"),
                        rs.getString("VoucherName"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDate("ReleaseDate"),
                        rs.getDate("FinishDate"),
                        rs.getInt("Status"),
                        rs.getFloat("Discount"),
                        rs.getInt("VoucherCategoryId"),
                        rs.getInt("hasVoucher") == 1,
                        rs.getInt("RestaurantId"),
                        rs.getString("Image"),
                        rs.getString("RestaurantName")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listVoucher;
    }

    public ArrayList<VoucherOfUser> getAllVoucherRestaurantOfUser(int accountId) {
        ArrayList<VoucherOfUser> listVoucher = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = """
                     SELECT distinct v.*, 
                            CASE WHEN av.AccountId IS NOT NULL THEN 1 ELSE 0 END AS hasVoucher,
                            a.ImageAvatar AS Image,
                            r.Name as RestaurantName
                     FROM Voucher v
                     LEFT JOIN AccountVoucher av ON v.VoucherId = av.VoucherId and av.AccountId = ?
                     LEFT JOIN Restaurant r ON v.RestaurantId = r.RestaurantId
                     LEFT JOIN Account a ON r.AccountId = a.AccountId
                     WHERE v.VoucherCategoryId = 2
                       AND v.Status = 1
                       AND v.Quantity > 0 
                       AND v.FinishDate >= CAST(GETDATE() AS DATE);
                     """;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new VoucherOfUser(
                        rs.getInt("VoucherId"),
                        rs.getString("VoucherName"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDate("ReleaseDate"),
                        rs.getDate("FinishDate"),
                        rs.getInt("Status"),
                        rs.getFloat("Discount"),
                        rs.getInt("VoucherCategoryId"),
                        rs.getInt("hasVoucher") == 1,
                        rs.getInt("RestaurantId"),
                        rs.getString("Image"),
                        rs.getString("RestaurantName")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listVoucher;
    }

    public void updateQuantityAndGetVoucher(int accountId, int voucherId) {

        try {
            String sql = """
                                     INSERT INTO [dbo].[AccountVoucher] (AccountId, VoucherId)
                                     VALUES (?, ?)
                                     UPDATE [dbo].[Voucher]
                                            SET [Quantity] = [Quantity] - 1
                                       WHERE [VoucherId] = ?""";

            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, accountId);
            ps.setInt(2, voucherId);
            ps.setInt(3, voucherId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<VoucherOfUser> getListVoucherRequest() {
        ArrayList<VoucherOfUser> listVoucher = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            String sql = """
                     SELECT distinct v.*, 
                              CASE WHEN av.AccountId IS NOT NULL THEN 1 ELSE 0 END AS hasVoucher,
                              a.ImageAvatar AS Image,
                       	   r.Name as RestaurantName
                       FROM Voucher v
                       LEFT JOIN AccountVoucher av ON v.VoucherId = av.VoucherId 
                       LEFT JOIN Restaurant r ON v.RestaurantId = r.RestaurantId
                       LEFT JOIN Account a ON r.AccountId = a.AccountId
                       WHERE v.VoucherCategoryId = 2
                         AND v.Status = 0
                         AND v.Quantity > 0 
                         AND v.FinishDate >= CAST(GETDATE() AS DATE);
                     """;
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                listVoucher.add(new VoucherOfUser(
                        rs.getInt("VoucherId"),
                        rs.getString("VoucherName"),
                        rs.getString("Description"),
                        rs.getInt("Quantity"),
                        rs.getDate("ReleaseDate"),
                        rs.getDate("FinishDate"),
                        rs.getInt("Status"),
                        rs.getFloat("Discount"),
                        rs.getInt("VoucherCategoryId"),
                        rs.getInt("hasVoucher") == 1,
                        rs.getInt("RestaurantId"),
                        rs.getString("Image"),
                        rs.getString("RestaurantName")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listVoucher;
    }

    public VoucherOfUser getVoucherRequestDetail(int voucherId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT distinct v.*, \n"
                + "       CASE WHEN av.AccountId IS NOT NULL THEN 1 ELSE 0 END AS hasVoucher,\n"
                + "       a.ImageAvatar AS Image,\n"
                + "	   r.Name as RestaurantName\n"
                + "FROM Voucher v\n"
                + "LEFT JOIN AccountVoucher av ON v.VoucherId = av.VoucherId \n"
                + "LEFT JOIN Restaurant r ON v.RestaurantId = r.RestaurantId\n"
                + "LEFT JOIN Account a ON r.AccountId = a.AccountId\n"
                + "WHERE v.VoucherCategoryId = 2\n"
                + "  AND v.Status = 0\n"
                + "  AND v.Quantity > 0 \n"
                + "  AND v.VoucherId = ?\n"
                + "  AND v.FinishDate >= CAST(GETDATE() AS DATE);";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, voucherId);
        rs = ps.executeQuery();
        while (rs.next()) {

            return new VoucherOfUser(
                    rs.getInt("VoucherId"),
                    rs.getString("VoucherName"),
                    rs.getString("Description"),
                    rs.getInt("Quantity"),
                    rs.getDate("ReleaseDate"),
                    rs.getDate("FinishDate"),
                    rs.getInt("Status"),
                    rs.getFloat("Discount"),
                    rs.getInt("VoucherCategoryId"),
                    rs.getInt("hasVoucher") == 1,
                    rs.getInt("RestaurantId"),
                    rs.getString("Image"),
                    rs.getString("RestaurantName")
            );
        }
        return null;
    }

    public void acceptRequestVoucher(int voucherId) {
        try {
            String sql = "update [dbo].[Voucher]\n"
                    + "set [Status] = 1\n"
                    + "where VoucherId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cancelRequestVoucher(int voucherId) {
        try {
            String sql = "delete [dbo].[Voucher]\n"
                    + "where VoucherId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Voucher getDiscountByVoucherRId(int voucherId) {
        String sql = "SELECT [VoucherId], [Discount], [RestaurantId] FROM [dbo].[Voucher] WHERE [VoucherId] = ?;";
        Voucher voucher = null;
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            rs = ps.executeQuery();
            if (rs.next()) {
                voucher = new Voucher();
                voucher.setVoucherId(rs.getInt("VoucherId"));
                voucher.setDiscount(rs.getInt("Discount"));
                voucher.setRestaurantId(rs.getInt("RestaurantId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return voucher;
    }

    public void updateQuantity(int voucherId) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            String sql = "Delete [AccountVoucher] \n"
                    + "where VoucherId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, voucherId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng tài nguyên
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        VoucherDAO v = new VoucherDAO();

    }

}



