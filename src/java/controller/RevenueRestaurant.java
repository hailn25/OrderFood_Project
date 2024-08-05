/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.OrderDAO;
import dao.ProductDAO;
import dao.RestaurantDAO;
import dao.RevenueDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author huyvu
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
            throws ServletException, IOException, SQLException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");

            int accountId = a.getAccountId();
            RestaurantDAO dao2 = new RestaurantDAO();
            int restaurantId = dao2.getRestaurantIdByAccountId(accountId);

            RevenueDAO dao = new RevenueDAO();
            ProductDAO dao1 = new ProductDAO();
            OrderDAO dao3 = new OrderDAO();
            AccountDAO dao4 = new AccountDAO();

            LocalDate date = LocalDate.now();
            int year = date.getYear();
            int currentMonth = date.getMonthValue();
            int monthBecomeRestaurant = dao.getMonthBecomeRestaurant(accountId);

            double thang1 = (currentMonth >= 1 && monthBecomeRestaurant <= 1) ? dao.getTotalMoneyByMonth(restaurantId, 1, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 1) - 1000000 : 0;
            double thang2 = (currentMonth >= 2 && monthBecomeRestaurant <= 2) ? dao.getTotalMoneyByMonth(restaurantId, 2, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 2) - 1000000 : 0;
            double thang3 = (currentMonth >= 3 && monthBecomeRestaurant <= 3) ? dao.getTotalMoneyByMonth(restaurantId, 3, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 3) - 1000000 : 0;
            double thang4 = (currentMonth >= 4 && monthBecomeRestaurant <= 4) ? dao.getTotalMoneyByMonth(restaurantId, 4, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 4) - 1000000 : 0;
            double thang5 = (currentMonth >= 5 && monthBecomeRestaurant <= 5) ? dao.getTotalMoneyByMonth(restaurantId, 5, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 5) - 1000000 : 0;
            double thang6 = (currentMonth >= 6 && monthBecomeRestaurant <= 6) ? dao.getTotalMoneyByMonth(restaurantId, 6, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 6) - 1000000 : 0;
            double thang7 = (currentMonth >= 7 && monthBecomeRestaurant <= 7) ? dao.getTotalMoneyByMonth(restaurantId, 7, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 7) - 1000000 : 0;
            double thang8 = (currentMonth >= 8 && monthBecomeRestaurant <= 8) ? dao.getTotalMoneyByMonth(restaurantId, 8, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 8) - 1000000 : 0;
            double thang9 = (currentMonth >= 9 && monthBecomeRestaurant <= 9) ? dao.getTotalMoneyByMonth(restaurantId, 9, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 9) - 1000000 : 0;
            double thang10 = (currentMonth >= 10 && monthBecomeRestaurant <= 10) ? dao.getTotalMoneyByMonth(restaurantId, 10, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 10) - 1000000 : 0;
            double thang11 = (currentMonth >= 11 && monthBecomeRestaurant <= 11) ? dao.getTotalMoneyByMonth(restaurantId, 11, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 11) - 1000000 : 0;
            double thang12 = (currentMonth >= 12 && monthBecomeRestaurant <= 12) ? dao.getTotalMoneyByMonth(restaurantId, 12, year) - dao.getRevenueSliderOfRestaurant(restaurantId, year, 12) - 1000000 : 0;

            double totalRevenue = thang1 + thang2 + thang3 + thang4 + thang5 + thang6 + thang7 + thang8 + thang9 + thang10 + thang11 + thang12;
            DecimalFormat df = new DecimalFormat("#,###");

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
            request.setAttribute("totalRevenue", df.format(totalRevenue));
            request.setAttribute("totalProducts", dao1.getQuantityOfProductByRestaurantId(restaurantId));
            request.setAttribute("totalPendingOrders", dao3.getQuantityOrderPendingByRestaurantId(restaurantId));
            request.setAttribute("totalCompletedOrders", dao3.getQuantityOrderSuccessByRestaurantId(restaurantId));

            request.getRequestDispatcher("ManagerDashboardRestaurant.jsp").forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(RevenueRestaurant.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RevenueRestaurant.class.getName()).log(Level.SEVERE, null, ex);
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
