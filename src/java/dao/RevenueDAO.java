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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailt
 */
public class RevenueDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public double getTotalMoneyByMonth(int restaurantId, int month, int year) {
        String sql = "SELECT \n"
                + "    MONTH(o.FinishDate) AS Month,\n"
                + "    YEAR(o.FinishDate) AS Year,\n"
                + "    SUM(od.TotalMoney) AS TotalMoney\n"
                + "FROM \n"
                + "    [Order] o\n"
                + "JOIN \n"
                + "    OrderDetail od ON o.OrderId = od.OrderId\n"
                + "JOIN \n"
                + "    Product p ON od.ProductId = p.ProductId\n"
                + "WHERE \n"
                + "    o.OrderStatusId = 3\n"
                + "    AND p.RestaurantId = ?\n"
                + "	AND MONTH(o.FinishDate) = ?\n"
                + "	AND YEAR(o.FinishDate) = ?\n"
                + "GROUP BY \n"
                + "    MONTH(o.FinishDate), \n"
                + "    YEAR(o.FinishDate)";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            ps.setInt(2, month);
            ps.setInt(3, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                double totalMoney = rs.getDouble("TotalMoney");
                return totalMoney;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        RevenueDAO dao = new RevenueDAO();
//        System.out.println(dao.getRevenueSliderOfWeb());
//    }

    public double getRevenueOfWeb(int month, int year) {
        String sql = "SELECT \n"
                + "               MONTH(O.FinishDate) AS month,\n"
                + "            YEAR(O.FinishDate) AS year,\n"
                + "            SUM(O.TotalMoney) AS total_revenue\n"
                + "        FROM \n"
                + "        [Order] O\n"
                + "          WHERE \n"
                + "            O.OrderStatusId = 3\n"
                + "                 AND MONTH(O.FinishDate) = ?\n"
                + "				 AND YEAR(O.FinishDate) = ?\n"
                + "              GROUP BY \n"
                + "                YEAR(O.FinishDate), MONTH(O.FinishDate)\n"
                + "             ORDER BY \n"
                + "                 YEAR(O.FinishDate), MONTH(O.FinishDate)";

        // Initialize resources
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            rs = ps.executeQuery();

            if (rs.next()) {
                double totalRevenue = rs.getDouble("total_revenue") * 1000;
                double finalRevenue = totalRevenue * 0.05;
                return Math.ceil(finalRevenue);
            }
        } catch (Exception e) {
            // Handle exceptions and possibly log them
            e.printStackTrace();
        } finally {
            // Ensure resources are closed properly
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public double getRevenueSliderOfWeb() {
        try {
            String sql = """
                                     select count(SliderId)
                                     from Slider
                                     where SliderStatusId = 3""";

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1) * 500000;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int AccountValid1(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 1""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount1 = rs.getInt("number_of_accounts");
                return numberAccount1;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid2(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 2""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount2 = rs.getInt("number_of_accounts");
                return numberAccount2;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid3(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 3;""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount3 = rs.getInt("number_of_accounts");
                return numberAccount3;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid4(int year) {
        String sql = """
                     SELECT 
                         COUNT(AccountId) AS number_of_accounts
                     FROM 
                         Account
                     WHERE 
                         Account.RoleId = 4
                         AND (
                             (Account.Status = 1 AND (
                                 (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                                 OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                             ))
                             OR 
                             (
                                 Account.Status = 0 AND (
                                     (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                                     OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                                 )
                             )
                         )
                         AND MONTH(LastDateLogin) >= 4""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount4 = rs.getInt("number_of_accounts");
                return numberAccount4;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid5(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 5""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount5 = rs.getInt("number_of_accounts");
                return numberAccount5;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid6(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 6""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount6 = rs.getInt("number_of_accounts");
                return numberAccount6;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid7(int year) {
        String sql = """
                     SELECT 
                         COUNT(AccountId) AS number_of_accounts
                     FROM 
                         Account
                     WHERE 
                         Account.RoleId = 4
                         AND (
                             (Account.Status = 1 AND (
                                 (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                                 OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                             ))
                             OR 
                             (
                                 Account.Status = 0 AND (
                                     (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                                     OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                                 )
                             )
                         )
                         AND MONTH(LastDateLogin) >= 7""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount7 = rs.getInt("number_of_accounts");
                return numberAccount7;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid8(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 8""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);
            ps.setInt(15, year);
            ps.setInt(16, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount8 = rs.getInt("number_of_accounts");
                return numberAccount8;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid9(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 9""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);
            ps.setInt(15, year);
            ps.setInt(16, year);
            ps.setInt(17, year);
            ps.setInt(18, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount9 = rs.getInt("number_of_accounts");
                return numberAccount9;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid10(int year) {
        String sql = """
                     SELECT 
                             COUNT(AccountId) AS number_of_accounts
                         FROM 
                             Account
                         WHERE 
                             Account.RoleId = 4
                             AND (
                                 (Account.Status = 1 AND (
                                     (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                                     OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                         			OR (MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                                 ))
                                 OR 
                                 (
                                     Account.Status = 0 AND (
                                         (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                                         OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                         				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                                     )
                                 )
                             )
                             AND MONTH(LastDateLogin) >= 10""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);
            ps.setInt(15, year);
            ps.setInt(16, year);
            ps.setInt(17, year);
            ps.setInt(18, year);
            ps.setInt(19, year);
            ps.setInt(20, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount10 = rs.getInt("number_of_accounts");
                return numberAccount10;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid11(int year) {
        String sql = """
                     SELECT 
                         COUNT(AccountId) AS number_of_accounts
                     FROM 
                         Account
                     WHERE 
                         Account.RoleId = 4
                         AND (
                             (Account.Status = 1 AND (
                                 (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 11 AND YEAR(UpdateDate) = ?)
                                 OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                             ))
                             OR 
                             (
                                 Account.Status = 0 AND (
                                     (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 11 AND YEAR(UpdateDate) = ?)
                                     OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                                 )
                             )
                         )
                         AND MONTH(LastDateLogin) >= 11""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);
            ps.setInt(15, year);
            ps.setInt(16, year);
            ps.setInt(17, year);
            ps.setInt(18, year);
            ps.setInt(19, year);
            ps.setInt(20, year);
            ps.setInt(21, year);
            ps.setInt(22, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount11 = rs.getInt("number_of_accounts");
                return numberAccount11;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int AccountValid12(int year) {
        String sql = """
                     SELECT 
                         COUNT(AccountId) AS number_of_accounts
                     FROM 
                         Account
                     WHERE 
                         Account.RoleId = 4
                         AND (
                             (Account.Status = 1 AND (
                                 (DAY(UpdateDate) BETWEEN 1 AND 15 AND MONTH(UpdateDate) = 12 AND YEAR(UpdateDate) = ?)
                                 OR (MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                     			OR (MONTH(UpdateDate) = 11 AND YEAR(UpdateDate) = ?)
                             ))
                             OR 
                             (
                                 Account.Status = 0 AND (
                                     (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 12 AND YEAR(UpdateDate) = ?)
                                     OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 1 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 2 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 3 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 4 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 5 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 6 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 7 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 8 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 9 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 10 AND YEAR(UpdateDate) = ?)
                     				OR (DAY(UpdateDate) >= 2 AND MONTH(UpdateDate) = 11 AND YEAR(UpdateDate) = ?)
                                 )
                             )
                         )
                         AND MONTH(LastDateLogin) >= 12""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
            ps.setInt(5, year);
            ps.setInt(6, year);
            ps.setInt(7, year);
            ps.setInt(8, year);
            ps.setInt(9, year);
            ps.setInt(10, year);
            ps.setInt(11, year);
            ps.setInt(12, year);
            ps.setInt(13, year);
            ps.setInt(14, year);
            ps.setInt(15, year);
            ps.setInt(16, year);
            ps.setInt(17, year);
            ps.setInt(18, year);
            ps.setInt(19, year);
            ps.setInt(20, year);
            ps.setInt(21, year);
            ps.setInt(22, year);
            ps.setInt(23, year);
            ps.setInt(24, year);

            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount12 = rs.getInt("number_of_accounts");
                return numberAccount12;
            }
        } catch (Exception e) {

        }
        return 0;
    }

}
