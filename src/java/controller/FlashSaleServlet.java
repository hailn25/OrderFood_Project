/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductSaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductSaleDTO;

/**
 *
 * @author hailt
 */
@WebServlet(name = "FlashSaleServlet", urlPatterns = {"/flsale"})
public class FlashSaleServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String timeFrame = request.getParameter("timeFrame");
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        String date = today.toString();
        LocalTime timeFrame1 = LocalTime.of(13, 00);
        LocalTime timeFrame2 = LocalTime.of(16, 00);
        LocalTime timeFrame3 = LocalTime.of(19, 00);
        LocalTime timeFrame4 = LocalTime.of(22, 00);
        ProductSaleDAO psdao = new ProductSaleDAO();
        List<ProductSaleDTO> list = new ArrayList<>();
        if (timeFrame == null) {
            if (now.isBefore(timeFrame1)) {
                list = psdao.getProductIsFlashSale(date, 1);
            }
            if (now.isBefore(timeFrame2) && now.isAfter(timeFrame1)) {
                list = psdao.getProductIsFlashSale(date, 2);
            }
            if (now.isBefore(timeFrame3) && now.isAfter(timeFrame2)) {
                list = psdao.getProductIsFlashSale(date, 3);
            }
            if (now.isBefore(timeFrame4) && now.isAfter(timeFrame3)) {
                list = psdao.getProductIsFlashSale(date, 4);
            }
        }
        if (timeFrame != null) {
            if (timeFrame.equals("1")) {
                list = psdao.getProductIsFlashSale(date, 1);
            }
            if (timeFrame.equals("2")) {
                list = psdao.getProductIsFlashSale(date, 2);
            }
            if (timeFrame.equals("3")) {
                list = psdao.getProductIsFlashSale(date, 3);
            }
            if (timeFrame.equals("4")) {
                list = psdao.getProductIsFlashSale(date, 4);
            }
        }
        request.setAttribute("listPS", list);
        request.getRequestDispatcher("FlashSale.jsp").forward(request, response);

    }
    public static void main(String[] args) throws SQLException {
             ProductSaleDAO psdao = new ProductSaleDAO();
             System.out.println(psdao.getProductIsFlashSale("2024-07-04 ", 2));
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
            Logger.getLogger(FlashSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FlashSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
