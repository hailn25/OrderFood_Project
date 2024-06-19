/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RestaurantDAO;
import dao.RevenueDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import model.Account;

/**
 *
 * @author hailt
 */
@WebServlet(name = "RevenueAdmin", urlPatterns = {"/revenueAdmin"})
public class RevenueAdmin extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        int accountId = a.getAccountId();
        RestaurantDAO dao2 = new RestaurantDAO();
        int restaurantId = dao2.getRestaurantIdByAccountId(accountId);
        int year = LocalDate.now().getYear();

        RevenueDAO dao = new RevenueDAO();
        double t1 = dao.getRevenueOfWeb(1, year) + dao.AccountValidFee(1, year);
        double t2 = dao.getRevenueOfWeb(2, year) + dao.AccountValidFee(2, year);
        double t3 = dao.getRevenueOfWeb(3, year) + dao.AccountValidFee(3, year);
        double t4 = dao.getRevenueOfWeb(4, year) + dao.AccountValidFee(4, year);
        double t5 = dao.getRevenueOfWeb(5, year) + dao.AccountValidFee(5, year);
        double t6 = dao.getRevenueOfWeb(6, year) + dao.AccountValidFee(6, year);
        double t7 = dao.getRevenueOfWeb(7, year) + dao.AccountValidFee(7, year);
        double t8 = dao.getRevenueOfWeb(8, year) + dao.AccountValidFee(8, year);
        double t9 = dao.getRevenueOfWeb(9, year) + dao.AccountValidFee(9, year);
        double t10 = dao.getRevenueOfWeb(10, year) + dao.AccountValidFee(10, year);
        double t11 = dao.getRevenueOfWeb(11, year) + dao.AccountValidFee(11, year);
        double t12 = dao.getRevenueOfWeb(12, year) + dao.AccountValidFee(12, year);

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
        request.getRequestDispatcher("ManagerDashboardAdmin.jsp").forward(request, response);
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
