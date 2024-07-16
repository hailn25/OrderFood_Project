/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import dao.ProductDAO;
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
import model.Feedback;
import model.Product;
import model.ProductSaleDTO;
import model.ProductSaleDetailDTO;

/**
 *
 * @author hailt
 */
@WebServlet(name = "ProductSaleDetailServlet", urlPatterns = {"/psdetail"})
public class ProductSaleDetailServlet extends HttpServlet {

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
        String pid = request.getParameter("pid");
        String time = request.getParameter("ptimeframe");
        int timeFrame = Integer.parseInt(time);
        int id = Integer.parseInt(pid);
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        String date = today.toString();
        LocalTime timeFrame1 = LocalTime.of(10, 00);
        LocalTime timeFrame2 = LocalTime.of(13, 00);
        LocalTime timeFrame3 = LocalTime.of(13, 00);
        LocalTime timeFrame4 = LocalTime.of(16, 00);
        LocalTime timeFrame5 = LocalTime.of(16, 00);
        LocalTime timeFrame6 = LocalTime.of(19, 00);
        LocalTime timeFrame7 = LocalTime.of(19, 00);
        LocalTime timeFrame8 = LocalTime.of(22, 00);
        ProductSaleDAO dao = new ProductSaleDAO();
        ProductDAO da = new ProductDAO();
        FeedbackDAO fb = new FeedbackDAO();
        List<Feedback> listFeedback = fb.getFeedbackByProductId(Integer.parseInt(pid));

        List<ProductSaleDTO> listRelate = new ArrayList<>();
        if (now.isAfter(timeFrame1) && now.isBefore(timeFrame2)) {
            listRelate = dao.getProductIsFlashSale(date, 1);
        } else if (now.isAfter(timeFrame3) && now.isBefore(timeFrame4)) {
            listRelate = dao.getProductIsFlashSale(date, 2);
        } else if (now.isAfter(timeFrame5) && now.isBefore(timeFrame6)) {
            listRelate = dao.getProductIsFlashSale(date, 3);
        } else if (now.isAfter(timeFrame7) && now.isBefore(timeFrame8)) {
            listRelate = dao.getProductIsFlashSale(date, 4);
        }
        request.setAttribute("listRelate", listRelate);
        request.setAttribute("reviews", listFeedback);
        if ((now.isAfter(timeFrame1) && now.isBefore(timeFrame2)) && timeFrame == 1) {
            ProductSaleDetailDTO ps = dao.getProductSaleDetailById(id);
            request.setAttribute("fsdetail", ps);
            request.getRequestDispatcher("ProductSaleDetail.jsp").forward(request, response);
        } else if ((now.isAfter(timeFrame3) && now.isBefore(timeFrame4)) && timeFrame == 2) {
            ProductSaleDetailDTO ps = dao.getProductSaleDetailById(id);
            request.setAttribute("fsdetail", ps);
            request.getRequestDispatcher("ProductSaleDetail.jsp").forward(request, response);
        } else if ((now.isAfter(timeFrame5) && now.isBefore(timeFrame6)) && timeFrame == 3) {
            ProductSaleDetailDTO ps = dao.getProductSaleDetailById(id);
            request.setAttribute("fsdetail", ps);
            request.getRequestDispatcher("ProductSaleDetail.jsp").forward(request, response);
        } else if ((now.isAfter(timeFrame7) && now.isBefore(timeFrame8)) && timeFrame == 4) {
            ProductSaleDetailDTO ps = dao.getProductSaleDetailById(id);
            request.setAttribute("fsdetail", ps);
            request.getRequestDispatcher("ProductSaleDetail.jsp").forward(request, response);
        } else {
            Product p = da.getProductByID(id);
            request.setAttribute("fsdetail", p);
            request.getRequestDispatcher("ProductSaleDetail1.jsp").forward(request, response);
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
        try {
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ProductSaleDetailServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductSaleDetailServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
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
