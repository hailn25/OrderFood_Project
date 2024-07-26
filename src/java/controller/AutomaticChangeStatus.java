/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductSaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailt
 */
public class AutomaticChangeStatus extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        LocalTime now = LocalTime.now();
        LocalTime timeFrame1 = LocalTime.of(13, 00);
        LocalTime timeFrame2 = LocalTime.of(16, 00);
        LocalTime timeFrame3 = LocalTime.of(19, 00);
        LocalTime timeFrame4 = LocalTime.of(22, 00);
        ProductSaleDAO dao = new ProductSaleDAO();
        if(now.isBefore(timeFrame1)){
            dao.ChangeStatusIsFlashSale11();
            dao.ChangeStatusIsFlashSale22();
            dao.ChangeStatusIsFlashSale33();
            dao.ChangeStatusIsFlashSale44();
            dao.ChangeStatusProductAfterFlashSale11();
            dao.ChangeStatusProductAfterFlashSale22();
            dao.ChangeStatusProductAfterFlashSale33();
            dao.ChangeStatusProductAfterFlashSale44();
        }
        else if (now.isAfter(timeFrame1) && now.isBefore(timeFrame2)) {
            dao.ChangeStatusIsFlashSale1();
            dao.ChangeStatusIsFlashSale22();
            dao.ChangeStatusIsFlashSale33();
            dao.ChangeStatusIsFlashSale44();
            dao.ChangeStatusProductAfterFlashSale1();
            dao.ChangeStatusProductAfterFlashSale22();
            dao.ChangeStatusProductAfterFlashSale33();
            dao.ChangeStatusProductAfterFlashSale44();
        } else if (now.isAfter(timeFrame2) && now.isBefore(timeFrame3)) {
            dao.ChangeStatusIsFlashSale1();
            dao.ChangeStatusIsFlashSale2();
            dao.ChangeStatusIsFlashSale33();
            dao.ChangeStatusIsFlashSale44();
            dao.ChangeStatusProductAfterFlashSale1();
            dao.ChangeStatusProductAfterFlashSale2();
            dao.ChangeStatusProductAfterFlashSale33();
            dao.ChangeStatusProductAfterFlashSale44();
        } else if (now.isAfter(timeFrame3) || now.isAfter(timeFrame4)) {
            dao.ChangeStatusIsFlashSale1();
            dao.ChangeStatusIsFlashSale2();
            dao.ChangeStatusIsFlashSale3();
            dao.ChangeStatusIsFlashSale44();
            dao.ChangeStatusProductAfterFlashSale1();
            dao.ChangeStatusProductAfterFlashSale2();
            dao.ChangeStatusProductAfterFlashSale3();
            dao.ChangeStatusProductAfterFlashSale44();
        } else if (now.isAfter(timeFrame4)) {
            dao.ChangeStatusIsFlashSale1();
            dao.ChangeStatusProductAfterFlashSale1();
            dao.ChangeStatusIsFlashSale2();
            dao.ChangeStatusProductAfterFlashSale2();
            dao.ChangeStatusIsFlashSale3();
            dao.ChangeStatusProductAfterFlashSale3();
            dao.ChangeStatusIsFlashSale4();
            dao.ChangeStatusProductAfterFlashSale4();
        }
        response.sendRedirect("flsale");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AutomaticChangeStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AutomaticChangeStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
