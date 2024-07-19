/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductSaleDAO;
import dao.RestaurantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Product;

/**
 *
 * @author Vu Huy
 */
@MultipartConfig

@WebServlet(name = "AddFlashSaleProductControl", urlPatterns = {"/addFlashSaleProduct"})
public class AddFlashSaleProductControl extends HttpServlet {

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

            // xử lý add FlashSale
            int productId = 0;
            if (request.getParameter("productId") != null) {
                productId = Integer.parseInt(request.getParameter("productId"));
            }
//            String productName = request.getParameter("productName");
            String date = request.getParameter("date");
            String startTime = null, endTime = null;
            int timeFrame = 0;
            if (request.getParameter("timeFrame") != null) {
                timeFrame = Integer.parseInt(request.getParameter("timeFrame"));
            }
            if (timeFrame == 1) {
                startTime = date + " 10:00:00";
                endTime = date + " 13:00:00";
            }
            if (timeFrame == 2) {
                startTime = date + " 13:00:00";
                endTime = date + " 16:00:00";
            }
            if (timeFrame == 3) {
                startTime = date + " 16:00:00";
                endTime = date + " 19:00:00";
            }
            if (timeFrame == 4) {
                startTime = date + " 19:00:00";
                endTime = date + " 22:00:00";
            }
            double discount = 0;
            int quantity = 0;
            if (request.getParameter("discount") != null) {
                discount = Double.parseDouble(request.getParameter("discount")) / 100;
            }
            int stock = 0;
            if (request.getParameter("stock") != null) {
                stock = Integer.parseInt(request.getParameter("stock"));
            }
            if (request.getParameter("quantity") != null) {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }

            // xử lý bắt ngoại lệ
            LocalDate today = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate selectedDate = LocalDate.parse(date, dateFormatter);

            ProductDAO productDao = new ProductDAO();
            Product product = productDao.getProductByID(productId);

            if (!selectedDate.isAfter(today)) {
                request.setAttribute("productId", product.getProductId());
                request.setAttribute("productName", product.getName());
                request.setAttribute("stock", product.getQuantity());
                request.setAttribute("imageURL", product.getImageURL());
                request.setAttribute("errorDate", "Ngày FlashSale phải là sau ngày hôm nay.");
                request.getRequestDispatcher("AddFlashSaleProduct.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int accountId = a.getAccountId();
            RestaurantDAO dao2 = new RestaurantDAO();
            int restaurantId = dao2.getRestaurantIdByAccountId(accountId);

            ProductDAO dao = new ProductDAO();
            double price = dao.getPriceByProductId(productId);
            double salePrice = price * (1 - discount);
            LocalDate createDate = LocalDate.now();
            ProductSaleDAO dao1 = new ProductSaleDAO();
            dao1.insertFlashSaleProduct(productId, startTime, endTime, salePrice, discount, 0, quantity, timeFrame, restaurantId, java.sql.Date.valueOf(createDate));
            dao1.updateStockBeforeFlashSale(stock, quantity, productId);

            request.getRequestDispatcher("managerFlashSaleProduct").forward(request, response);
//            request.setAttribute("productId", productId);
//            request.getRequestDispatcher("AddFlashSaleProduct.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(AddFlashSaleProductControl.class.getName()).log(Level.SEVERE, null, ex);
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


