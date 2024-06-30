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
@WebServlet(name = "RevenueRestaurant", urlPatterns = {"/revenueRestaurant"})
public class RevenueRestaurant extends HttpServlet {

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

        double thang1 = dao.getTotalMoneyByMonth(restaurantId, 1, year) * 1000;
        double thang2 = dao.getTotalMoneyByMonth(restaurantId, 2, year) * 1000;
        double thang3 = dao.getTotalMoneyByMonth(restaurantId, 3, year) * 1000;
        double thang4 = dao.getTotalMoneyByMonth(restaurantId, 4, year) * 1000;
        double thang5 = dao.getTotalMoneyByMonth(restaurantId, 5, year) * 1000;
        double thang6 = dao.getTotalMoneyByMonth(restaurantId, 6, year) * 1000;
        double thang7 = dao.getTotalMoneyByMonth(restaurantId, 7, year) * 1000;
        double thang8 = dao.getTotalMoneyByMonth(restaurantId, 8, year) * 1000;
        double thang9 = dao.getTotalMoneyByMonth(restaurantId, 9, year) * 1000;
        double thang10 = dao.getTotalMoneyByMonth(restaurantId, 10, year) * 1000;
        double thang11 = dao.getTotalMoneyByMonth(restaurantId, 11, year) * 1000;
        double thang12 = dao.getTotalMoneyByMonth(restaurantId, 12, year) * 1000;

        request.setAttribute("thang1", thang1);
        request.setAttribute("thang2", thang2);
        request.setAttribute("thang3", thang3);
        request.setAttribute("thang4", thang4);
        request.setAttribute("thang5", thang5);
        request.setAttribute("thang6", thang6);
        request.setAttribute("thang7", thang7);
        request.setAttribute("thang8", thang8);
        request.setAttribute("thang9", thang9);
        request.setAttribute("thang10", thang10);
        request.setAttribute("thang11", thang11);
        request.setAttribute("thang12", thang12);

        request.getRequestDispatcher("ManagerDashboardRestaurant.jsp").forward(request, response);
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
