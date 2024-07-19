/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FeedbackDAO;
import dao.ProductHomeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryListDetail;
import model.Feedback;
import model.ProductHome;

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
        String isSale = request.getParameter("isSale");

        // Fetch the product details
        ProductHomeDAO dao = new ProductHomeDAO();
        FeedbackDAO fb = new FeedbackDAO();
        ProductHome p = dao.getProductById(id);

        // Fetch the category ID from the product details
        int categoryId = p.getCategoryId();

        // Fetch products from the same category
        List<ProductHome> listSameCategoryProducts = dao.getProductByCategoryId(categoryId);
        List<ProductHome> listProductByIsSale = dao.getProductByIsSale();

        // Fetch other necessary details    
        List<CategoryListDetail> listCategoryListDetail = dao.getCategoryListDetail();
        List<ProductHome> listBestSellerProduct = dao.getAllBestSellerProduct();

        // Fetch feedback for the product
        List<Feedback> listFeedback = fb.getFeedbackByProductId(Integer.parseInt(id));

        // Set attributes for the request
        request.setAttribute("detail", p);
        request.setAttribute("listSameCategoryProducts", listSameCategoryProducts);
        request.setAttribute("listCategoryListDetail", listCategoryListDetail);
        request.setAttribute("listProductByIsSale", listProductByIsSale);
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

