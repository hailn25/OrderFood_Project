/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RevenueDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hailt
 */
@WebServlet(name = "RevenueRestaurant", urlPatterns = {"/RevenueRestaurant"})
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RevenueRestaurant</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RevenueRestaurant at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
       RevenueDAO dao = new RevenueDAO();
//    double thang1 = dao.getTotalMoneyByMonth(1);
//    double thang2 = dao.getTotalMoneyByMonth(2);
//    double thang3 = dao.getTotalMoneyByMonth(3);
//    double thang4 = dao.getTotalMoneyByMonth(4);
//    double thang5 = dao.getTotalMoneyByMonth(5);
//    double thang6 = dao.getTotalMoneyByMonth(6);
//    double thang7 = dao.getTotalMoneyByMonth(7);
//    double thang8 = dao.getTotalMoneyByMonth(8);
//    double thang9 = dao.getTotalMoneyByMonth(9);
//    double thang10 = dao.getTotalMoneyByMonth(10);
//    double thang11 = dao.getTotalMoneyByMonth(11);
//    double thang12 = dao.getTotalMoneyByMonth(12);

double thang1 = 10;
double thang2 = 10;
double thang3 = 10;
double thang4 = 10;
double thang5 = 10;
double thang6 = 10;
double thang7 = 10;
double thang8 = 10;
double thang9 = 10;
double thang10 = 10;
double thang11 = 10;
double thang12 = 10;


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

    request.getRequestDispatcher("ManagerDashboard.jsp").forward(request, response);
        

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
