/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RevenueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hailt
 */
@WebServlet(name = "RevenueOfWeb", urlPatterns = {"/RevenueOfWeb"})
public class RevenueOfWeb extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RevenueDAO dao = new RevenueDAO();
        double t1 = dao.getRevenueOfWeb(1) + dao.AccountValidFee(1);
        double t2 = dao.getRevenueOfWeb(2) + dao.AccountValidFee(2);
        double t3 = dao.getRevenueOfWeb(3) + dao.AccountValidFee(3);
        double t4 = dao.getRevenueOfWeb(4) + dao.AccountValidFee(4);
        double t5 = dao.getRevenueOfWeb(5) + dao.AccountValidFee(5);
        double t6 = dao.getRevenueOfWeb(6) + dao.AccountValidFee(6);
        double t7 = dao.getRevenueOfWeb(7) + dao.AccountValidFee(7);
        double t8 = dao.getRevenueOfWeb(8) + dao.AccountValidFee(8);
        double t9 = dao.getRevenueOfWeb(9) + dao.AccountValidFee(9);
        double t10 = dao.getRevenueOfWeb(10) + dao.AccountValidFee(10);
        double t11 = dao.getRevenueOfWeb(11) + dao.AccountValidFee(11);
        double t12 = dao.getRevenueOfWeb(12) + dao.AccountValidFee(12);

        request.setAttribute("t1", t1);
        request.setAttribute("t2", t2);
        request.setAttribute("t3", t3);
        request.setAttribute("t4", t4);
        request.setAttribute("t5", t5);
        request.setAttribute("t6", t6);
        request.setAttribute("t7", t7);
        request.setAttribute("t8", t8);
        request.setAttribute("t9", t9);
        request.setAttribute("t10", t10);
        request.setAttribute("t11", t11);
        request.setAttribute("t12", t12);
        request.getRequestDispatcher("ManagerDashboard.jsp").forward(request, response);

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
        processRequest(request, response);
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
        processRequest(request, response);
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
