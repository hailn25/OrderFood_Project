/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utils.Validation;

/**
 *
 * @author Vu Huy
 */
@WebServlet(name = "EditCategoryControl", urlPatterns = {"/editCategory"})
public class EditCategoryControl extends HttpServlet {

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String id = request.getParameter("id");
            int lengthName = Validation.removeAllBlank(name).length();

            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int roleId = a.getRoleId();

            if (roleId == 1 || roleId == 5) {
                if (lengthName > 0) {
                    name = Validation.removeUnnecessaryBlank(name);
                    CategoryDAO dao1 = new CategoryDAO();
                    dao1.editCategory(name, id);
                    response.sendRedirect("managerCategory");
                } else {
                    request.setAttribute("error", "Nhập không hợp lệ!");
                    request.getRequestDispatcher("loadCategory?cid=" + id).forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tài khoản đang dùng không hợp lệ");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditCategoryControl.class.getName()).log(Level.SEVERE, null, ex);
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
