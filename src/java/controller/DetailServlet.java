/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import dao.ProductDAO;
import dao.ProductHomeDAO;
import dao.ProductSaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryListDetail;
import model.Feedback;
import model.Product;
import model.ProductHome;
import model.ProductSale;

/**
 *
 * @author ADMIN
 */
public class DetailServlet extends HttpServlet {

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
        String id = request.getParameter("pid");
        String fId = request.getParameter("ProductID");

        // Fetch the product details
        ProductHomeDAO dao = new ProductHomeDAO();
        FeedbackDAO fb = new FeedbackDAO();
        ProductHome p = dao.getProductById(id);

        // Fetch the category ID from the product details
        int categoryId = p.getCategoryId();

        // Fetch products from the same category
        List<ProductHome> listSameCategoryProducts = dao.getProductByCategoryId(categoryId);

        // Fetch other necessary details
        ProductSaleDAO dao1 = new ProductSaleDAO();
        List<ProductSale> listProductSale = dao1.getProductSale();
        List<CategoryListDetail> listCategoryListDetail = dao.getCategoryListDetail();
        List<ProductHome> listBestSellerProduct = dao.getAllBestSellerProduct();

        // Fetch feedback for the product
        List<Feedback> listFeedback = fb.getFeedbackByProductId(Integer.parseInt(id));

        // Set attributes for the request
        request.setAttribute("detail", p);
        request.setAttribute("listSameCategoryProducts", listSameCategoryProducts);
        request.setAttribute("listProductSale", listProductSale);
        request.setAttribute("listCategoryListDetail", listCategoryListDetail);
        request.setAttribute("listBSL", listBestSellerProduct);
        request.setAttribute("reviews", listFeedback); // Add this line to set feedback

        // Forward the request to the JSP page
        request.getRequestDispatcher("ShopDetail.jsp").forward(request, response);
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
