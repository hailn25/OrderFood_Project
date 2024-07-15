/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class EditPasswordControll extends HttpServlet {

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

        // Lấy thông tin từ request
        String aid = request.getParameter("accountId");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Kiểm tra mật khẩu cũ
        AccountDAO dao = new AccountDAO();
        boolean isCurrentPasswordCorrect = dao.checkPassword(aid, currentPassword);

        if (!isCurrentPasswordCorrect) {
            // Nếu mật khẩu cũ không đúng, thông báo lỗi cho người dùng
            request.setAttribute("error", "Mật khẩu cũ không chính xác.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePasswordProfile.jsp");
            dispatcher.forward(request, response);
        } else {
            // Kiểm tra mật khẩu mới và nhập lại mật khẩu mới có khớp nhau không
            if (!newPassword.equals(confirmPassword)) {
                // Nếu mật khẩu mới và nhập lại mật khẩu mới không khớp, thông báo lỗi cho người dùng
                request.setAttribute("error", "Mật khẩu mới và Nhập lại mật khẩu mới không khớp.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePasswordProfile.jsp");
                dispatcher.forward(request, response);
            } else {
                // Nếu mật khẩu mới và nhập lại mật khẩu mới khớp, tiến hành cập nhật mật khẩu mới vào cơ sở dữ liệu
                dao.updatePassword(newPassword, aid);

                // Set thông báo thành công để hiển thị trên trang
                request.setAttribute("success", "Đổi mật khẩu thành công.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("ChangePasswordProfile.jsp");
                dispatcher.forward(request, response);
            }
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
