/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ListOrderDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.ListOrder;
import model.OrderDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OrderHistoryControl", urlPatterns = {"/orderHistory"})
public class OrderHistoryControl extends HttpServlet {

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
        int accountId = 0;

        // Lấy accountId từ session
        Account account = (Account) request.getSession().getAttribute("account");
        if (account != null) {
            accountId = account.getAccountId();
        }

        // Xử lý orderStatusId để nhận nhiều giá trị
        List<Integer> orderStatusIds = new ArrayList<>();
        if (request.getParameter("orderStatusId") != null) {
            String orderStatusIdsParam = request.getParameter("orderStatusId");
            String[] orderStatusIdsArray = orderStatusIdsParam.split(",");
            for (String id : orderStatusIdsArray) {
                orderStatusIds.add(Integer.parseInt(id.trim()));
            }
        }

        if (request.getParameter("cancelOrder") != null && request.getParameter("orderId") != null) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            ListOrderDAO listOrderDAO = new ListOrderDAO();

            boolean isUpdated = false;
            try {
                isUpdated = listOrderDAO.updateOrderStatus(accountId, orderId);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(OrderHistoryControl.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (isUpdated) {
                // Thông báo cập nhật thành công
                request.setAttribute("message", "Order has been canceled successfully.");
            } else {
                // Thông báo cập nhật thất bại
                request.setAttribute("message", "Failed to cancel the order.");
            }
        }

        // Lấy danh sách đơn hàng
        ListOrderDAO listOrderDAO = new ListOrderDAO();
        List<ListOrder> listOrders = listOrderDAO.getListOrderByIds(orderStatusIds, accountId);
        request.setAttribute("listOrders", listOrders);

        // Chuyển tiếp tới trang hiển thị đơn hàng
        request.getRequestDispatcher("ShowOrder.jsp").forward(request, response);
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


