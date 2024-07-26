/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Validation;

/**
 *
 * @author Vu Huy
 */
@WebServlet(name = "SearchListManagerToFlashSaleControl", urlPatterns = {"/searchListManagerToFlashSale"})
public class SearchListManagerToFlashSaleControl extends HttpServlet {

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
            ProductDAO dao = new ProductDAO();
            String productName = request.getParameter("productName");

            if (productName != null) {
                int lengthProductName = Validation.removeAllBlank(productName).length();
                if (lengthProductName > 0) {
                    productName = Validation.removeUnnecessaryBlank(productName);
                    int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                    request.setAttribute("listP", dao.getSearchProductToFlashsaleByRestaurantId(restaurantId, productName));
                    request.setAttribute("restaurantId", restaurantId);
                } else {
                    request.setAttribute("error", "Tên tìm kiếm không hợp lệ!");
                }
            }
            request.getRequestDispatcher("ListProductToSelect.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchListManagerToFlashSaleControl.class.getName()).log(Level.SEVERE, null, ex);
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
