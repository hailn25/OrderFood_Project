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

    public double getRevenueOfWeb(int month, int year) {
        String sql = """
                     SELECT 
                                    MONTH(O.FinishDate) AS month,
                                 YEAR(O.FinishDate) AS year,
                                 SUM(O.TotalMoney) AS total_revenue
                             FROM 
                             [Order] O
                               WHERE 
                                 O.OrderStatusId = 3
                                      AND MONTH(O.FinishDate) = ?
                     \t\t\t\t AND YEAR(O.FinishDate) = ?
                                   GROUP BY 
                                     YEAR(O.FinishDate), MONTH(O.FinishDate)
                                  ORDER BY 
                                      YEAR(O.FinishDate), MONTH(O.FinishDate)""";

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
                double totalRevenue = rs.getDouble("total_revenue");
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

    public double getRevenueSliderOfRestaurant(int restaurantId, int year, int month) {
        try {
            String sql = """
                        select count(SliderId)
                        from Slider
                        where (SliderStatusId = 3 or SliderStatusId = 4) and UpdateBy = ?
                        and YEAR(UpdateDate) = ? and MONTH(UpdateDate) = ?
                         """;

            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, restaurantId);
            ps.setInt(2, year);
            ps.setInt(3, month);
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

    public static void main(String[] args) {
        RevenueDAO dao = new RevenueDAO();
        System.out.println(dao.getMonthBecomeRestaurant(9));
    }

    public int AccountValid1(int year) {
        String sql = """
                     SELECT 
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 1 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 1 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1) AND MONTH(UpdateDate) IN (2,3,4,5,6,7,8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 2 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 2 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2) AND MONTH(UpdateDate) IN (3,4,5,6,7,8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 3 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 3 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3) AND MONTH(UpdateDate) IN (4,5,6,7,8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                            SUM(
                                CASE 
                                    WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 4 AND DAY(UpdateDate) != 1 THEN 1
                                    WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 4 AND DAY(UpdateDate)  = 1 THEN 0
                        			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4) AND MONTH(UpdateDate) IN (5,6,7,8,9,10,11,12) THEN 1
                        			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4) AND YEAR(UpdateDate) = ? THEN 1
                                    ELSE 0
                                END
                            ) AS number_of_accounts
                        FROM 
                            Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 5 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 5 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5) AND MONTH(UpdateDate) IN (6,7,8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 6 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ?  AND MONTH(UpdateDate) = 6 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ?  AND MONTH(CreateDate) IN (1,2,3,4,5,6) AND MONTH(UpdateDate) IN (7,8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6) AND YEAR(UpdateDate) = ?  THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 7 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 7 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7) AND MONTH(UpdateDate) IN (8,9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 8 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 8 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8) AND MONTH(UpdateDate) IN (9,10,11,12) THEN 1
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7,8) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                                     SUM(
                                         CASE 
                                             WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 9 AND DAY(UpdateDate) != 1 THEN 1
                                             WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 9 AND DAY(UpdateDate)  = 1 THEN 0
                                 			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8,9) AND MONTH(UpdateDate) IN (10,11,12) THEN 1
                                 			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7,8,9) AND YEAR(UpdateDate) = ? THEN 1
                                             ELSE 0
                                         END
                                     ) AS number_of_accounts
                                 FROM 
                                     Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                                     SUM(
                                         CASE 
                                             WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 10 AND DAY(UpdateDate) != 1 THEN 1
                                             WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 10 AND DAY(UpdateDate)  = 1 THEN 0
                                 			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8,9,10) AND MONTH(UpdateDate) IN (11,12) THEN 1
                                 			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7,8,9,10) AND YEAR(UpdateDate) = ? THEN 1
                                             ELSE 0
                                         END
                                     ) AS number_of_accounts
                                 FROM 
                                     Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                                 SUM(
                                     CASE 
                                         WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 11 AND DAY(UpdateDate) != 1 THEN 1
                                         WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(UpdateDate) = 11 AND DAY(UpdateDate)  = 1 THEN 0
                             			WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8,9,10,11) AND MONTH(UpdateDate) IN (12) THEN 1
                             			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7,8,9,10,11) AND YEAR(UpdateDate) = ? THEN 1
                                         ELSE 0
                                     END
                                 ) AS number_of_accounts
                             FROM 
                                 Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            ps.setInt(4, year);
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
                             SUM(
                                 CASE 
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8,9,10,11,12) AND MONTH(UpdateDate) = 12 AND DAY(UpdateDate) != 1 THEN 1
                                     WHEN  Account.RoleId = 4 and Account.Status = 0 AND YEAR(UpdateDate) = ? AND MONTH(CreateDate) IN (1,2,3,4,5,6,7,8,9,10,11,12) AND MONTH(UpdateDate) = 12 AND DAY(UpdateDate)  = 1 THEN 0
                         			WHEN  Account.RoleId = 4 and Account.Status = 1 AND MONTH(UpdateDate) IN (1,2,3,4,5,6,7,8,9,10,11,12) AND YEAR(UpdateDate) = ? THEN 1
                                     ELSE 0
                                 END
                             ) AS number_of_accounts
                         FROM 
                             Account""";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, year);
            ps.setInt(2, year);
            ps.setInt(3, year);
            rs = ps.executeQuery();
            if (rs.next()) {
                int numberAccount12 = rs.getInt("number_of_accounts");
                return numberAccount12;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public int getMonthBecomeRestaurant(int accountId) {
        try {
            String sql = "select MONTH(CreateDate) as Month\n"
                    + "from Account\n"
                    + "where AccountId = 9";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RevenueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
}



