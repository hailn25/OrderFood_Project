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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;
import model.ReportDTO;
import model.ReportDTO_1;

/**
 *
 * @author quoch
 */
public class ReportDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public ArrayList<Report> getAllReport() {
        ArrayList<Report> listReport = new ArrayList<>();
        try {
            String sql = "select *\n"
                    + "from [dbo].[Report]";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listReport.add(new Report(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReport;
    }

    public ReportDTO_1 getReportedAboutRestaurant(int restaurantId) throws ClassNotFoundException, SQLException {
        String sql = """
                     SELECT        Restaurant.Name, Report.Description, Report.ImageURL, Report.CreateDate
                                          FROM            Report INNER JOIN
                                                                   Restaurant ON Report.RestaurantId = Restaurant.RestaurantId
                                          WHERE dbo.Restaurant.RestaurantId = ? and ReportStatusId = 3""";
        con = new DBContext().getConnection();
        ps = con.prepareStatement(sql);
        ps.setInt(1, restaurantId);
        rs = ps.executeQuery();
        while (rs.next()) {
            return new ReportDTO_1(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4));
        }
        return null;
    }

    public void deleteReport(String RestaurantId) throws SQLException, ClassNotFoundException {
        try {
            String sql = "DELETE FROM [dbo].[Report]\n"
                    + "WHERE RestaurantId = ?;";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, RestaurantId);
            ps.executeQuery();
        } catch (Exception ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void changeStatusReport(int reportId, int status) {
        try {
            String sql = "UPDATE [dbo].[Report]\n"
                    + "SET [ReportStatusId]  = ?\n"
                    + "WHERE  [ReportId]= ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, reportId);

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<ReportDTO> getAllReportDTO() {
        ArrayList<ReportDTO> listReport = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    Report.ReportId, \n"
                    + "    Report.Description, \n"
                    + "    Report.ImageURL, \n"
                    + "    Report.CreateDate, \n"
                    + "    Report.AccountId, \n"
                    + "    Report.RestaurantId, \n"
                    + "    ReportStatus.StatusName\n"
                    + "FROM \n"
                    + "    Report\n"
                    + "INNER JOIN \n"
                    + "    ReportStatus ON Report.ReportStatusId = ReportStatus.ReportStatusId\n"
                    + "WHERE ReportStatus.ReportStatusId = 1 OR ReportStatus.ReportStatusId = 2 OR ReportStatus.ReportStatusId = 3";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                listReport.add(new ReportDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReport;
    }

    public ReportDTO getReportByReportId(int reportId) {
        ReportDTO report = new ReportDTO();
        try {
            String sql = "SELECT Report.ReportId, Report.Description, Report.ImageURL, Report.CreateDate, Account.Name, Restaurant.Name, ReportStatus.StatusName\n"
                    + "FROM     Report INNER JOIN\n"
                    + "ReportStatus ON Report.ReportStatusId = ReportStatus.ReportStatusId\n"
                    + "INNER JOIN\n"
                    + "Account ON Report.AccountId = Account.AccountId\n"
                    + "INNER JOIN\n"
                    + "Restaurant ON Report.RestaurantId = Restaurant.RestaurantId\n"
                    + "WHERE Report.ReportId = ?";
            con = new DBContext().getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, reportId);
            rs = ps.executeQuery();

            while (rs.next()) {
                report = new ReportDTO(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return report;
    }

    public static void main(String[] args) {
        ReportDAO dao = new ReportDAO();
//        for (Report r : dao.getAllReport()) {
//            System.out.println(r.toString());
//        }
//        for (ReportDTO r : dao.getAllReportDTO()) {
//            System.out.println(r.toString());
//        }
        System.out.println(dao.getReportByReportId(1));
    }
}
