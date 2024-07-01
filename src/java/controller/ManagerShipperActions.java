/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.OrderDTO;
import model.OrderDetailDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ManagerShipperActions", urlPatterns = {"/managerActions"})
public class ManagerShipperActions extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        OrderDAO orderDAO = new OrderDAO();
        String action = request.getParameter("action");
        int orderId = Integer.parseInt(request.getParameter("oid"));

        switch (action) {
            case "accept":
                orderDAO.updateOrderStatus(orderId, 2);
               int acc =   account.getAccountId();
               orderDAO.insertShipper(1, orderId);
                break;
            case "refuse":
                orderDAO.updateOrderStatus(orderId, 5);
                break;
            case "finish":
                orderDAO.updateOrderStatus(orderId, 3);
                orderDAO.insertDateFinish(orderId);
                request.getRequestDispatcher("managerShipperSuccess").forward(request, response);
                return;
            default:
               
                break;
        }

        ArrayList<OrderDTO> listOrder = orderDAO.getAllOrder(1);
        request.setAttribute("list", listOrder);
        request.getRequestDispatcher("managerShipper").forward(request, response);

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
