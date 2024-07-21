/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.OrderDetailDTO;
import model.OrderDetailProfile;
import model.ViewDetail;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ListOrderProduct", urlPatterns = {"/listOrderProduct"})
public class ListOrderProduct extends HttpServlet {

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
        OrderDAO orderDAO = new OrderDAO();
        String action = request.getParameter("action");
        int orderId = Integer.parseInt(request.getParameter("oid"));

        if ("view".equals(action)) {
            orderDAO.getOrderDetailByOrderId(orderId);
        }


        ArrayList<OrderDetailProfile> listOrderDetailsByAccountId = orderDAO.getOrderDetailByOrderId(orderId);
        request.setAttribute("listOrderDetailsByAccountId", listOrderDetailsByAccountId);

        // Chuyển tiếp đến JSP hiển thị chi tiết đơn hàng
        request.getRequestDispatcher("OrderDetail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
