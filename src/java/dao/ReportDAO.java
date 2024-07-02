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
            String sql = "SELECT Report.ReportId, Report.Description, Report.ImageURL, Report.CreateDate, Report.AccountId, Report.RestaurantId, ReportStatus.StatusName\n"
                    + "FROM     Report INNER JOIN\n"
                    + "                  ReportStatus ON Report.ReportStatusId = ReportStatus.ReportStatusId";
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

    public static void main(String[] args) {
        ReportDAO dao = new ReportDAO();
//        for (Report r : dao.getAllReport()) {
//            System.out.println(r.toString());
//        }
//        for (ReportDTO r : dao.getAllReportDTO()) {
//            System.out.println(r.toString());
//        }
    }
}
