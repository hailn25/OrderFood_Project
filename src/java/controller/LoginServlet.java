/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.FeedbackDAO;
import dao.ProductHomeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.CategoryListDetail;
import model.Feedback;
import model.ListProduct;
import model.ProductHome;
import util.EncodePassword;

/**
 *
 * @author
 */
public class LoginServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            //String hashedPassword = EncodePassword.toSHA1(password);
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                request.setAttribute("err", "Vui lòng nhập cả email và mật khẩu");
                request.setAttribute("email", email);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
                return;
            }
            AccountDAO acc = new AccountDAO();
            Account a = acc.checkLogin(email, password);

            if (a == null) {
                request.setAttribute("err", "Bạn đã nhập sai password hoặc email");
                request.setAttribute("email", email);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if (a.isStatus() == false) {
                request.setAttribute("err", "Tài khoản của bạn đã bị chặn");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                if (a.getRoleId() == 2) {
                    acc.UpdateLastDateLogin(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("account", a);
                    request.getRequestDispatcher("home").forward(request, response);
                } else if (a.getRoleId() == 1) {
                    acc.UpdateLastDateLogin(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("account", a);
                    request.getRequestDispatcher("ManagerDashboard.jsp").forward(request, response);
                } else if (a.getRoleId() == 3) {
                    acc.UpdateLastDateLogin(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("account", a);
                    request.getRequestDispatcher("Shipper.jsp").forward(request, response);
                } else if (a.getRoleId() == 4) {
                    acc.UpdateLastDateLogin(email);
                    HttpSession session = request.getSession();
                    session.setAttribute("account", a);
                    request.getRequestDispatcher("managerProduct").forward(request, response);
                }
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
