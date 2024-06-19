/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dal.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hailt
 */
public class RevenueDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public double getTotalMoneyByMonth(int month) {
        String sql = "SELECT SUM(TotalMoney) as TotalMoney from OrderDetail where MONTH(UpdateDate) = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
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

    public double getRevenueOfWeb(int month) {
        String sql = "SELECT \n"
                + "    MONTH(O.FinishDate) AS month,\n"
                + "    YEAR(O.FinishDate) AS year,\n"
                + "    SUM(O.TotalMoney) AS total_revenue\n"
                + "FROM \n"
                + "    [Order] O\n"
                + "WHERE \n"
                + "    O.OrderStatusId = 3\n"
                + "    AND MONTH(O.FinishDate) = ?\n"
                + "GROUP BY \n"
                + "    YEAR(O.FinishDate), MONTH(O.FinishDate)\n"
                + "ORDER BY \n"
                + "    YEAR(O.FinishDate), MONTH(O.FinishDate);";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            if (rs.next()) {
                double totalRevenue = rs.getDouble("total_revenue");
                double finalRevenue = totalRevenue * 0.05;
                return finalRevenue;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public double AccountValidFee(int month) {
        String sql = "SELECT \n"
                + "    COUNT(AccountId) AS number_of_accounts,\n"
                + "    COUNT(CASE WHEN DAY(CreateDate) BETWEEN 1 AND 15 THEN 1 ELSE NULL END) AS accounts_to_charge_fee\n"
                + "FROM \n"
                + "    Account\n"
                + "WHERE \n"
                + "    Account.RoleId = 4\n"
                + "    AND Account.Status = 1\n"
                + "    AND DAY(CreateDate) BETWEEN 1 AND 15\n"
                + "	AND MONTH(Account.CreateDate) = ?";
        try {
             conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            if(rs.next()){
                int totalAccount = rs.getInt("number_of_accounts");
                double accountFee = totalAccount * 1000000;
                return accountFee;
            }
        } catch (Exception e) {
                
        }
        return 0;
    }

    public static void main(String[] args) {
        RevenueDAO dao = new RevenueDAO();
        System.out.println(dao.getTotalMoneyByMonth(6));
    }
}
