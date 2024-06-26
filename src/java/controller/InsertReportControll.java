/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="InsertReportControll", urlPatterns={"/insertReport"})
public class InsertReportControll extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertReportControll</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertReportControll at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        String imageURL = request.getParameter("imageURL");
        String accountId = request.getParameter("accountId");
        String restaurantId = request.getParameter("restaurantId");
        String status = request.getParameter("status");
        String createDate = request.getParameter("createDate");

        try {
            FeedbackDAO dao = new FeedbackDAO();
            dao.insertReport(description, imageURL, accountId, restaurantId, status, createDate);

            // Set success message in request attribute
            request.setAttribute("successMessage", "Phản hồi thành công!");
            request.getRequestDispatcher("Report.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle exceptions
            ex.printStackTrace();
            // Set error message in request attribute
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
        }

        // Forward to the same page (Feedback.jsp)
        request.getRequestDispatcher("Report.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
