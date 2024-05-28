/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FunctionShopDAO;
import dao.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author quoch
 */
@WebServlet(name = "ShopControl", urlPatterns = {"/shop"})
public class ShopControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ShopDAO dao = new ShopDAO();
        FunctionShopDAO daofunction = new FunctionShopDAO();

        ArrayList<ProductDTO> listProductDTO = dao.getAllProductDTO();
        request.setAttribute("listProductDTO", listProductDTO);

        ArrayList<Category> listAllCategory = dao.getAllCategory();
        request.setAttribute("listAllCategory", listAllCategory);

        ArrayList<CategoryDTO> listTotalQuantityByCategory = dao.getTotalQuantityByCategory();
        request.setAttribute("listTotalQuantityByCategory", listTotalQuantityByCategory);

        ArrayList<RestaurantDTO> listRestaurantDTO = dao.getAllRestaurantDTO();
        request.setAttribute("listRestaurantDTO", listRestaurantDTO);

        String categoryName = request.getParameter("categoryName");
        if (categoryName != null) {
            listProductDTO = daofunction.getAllProductDTOByCategoryName(categoryName);
            request.setAttribute("listProductDTO", listProductDTO);
        }

        String productName = request.getParameter("productName");
        String maxPriceStr = request.getParameter("rangeInput");

        if (productName != null || maxPriceStr != null) {
            try {
                int maxPrice = maxPriceStr != null ? Integer.parseInt(maxPriceStr) : 0;
                listProductDTO = daofunction.searchProductByAttribute(productName, maxPrice);
                request.setAttribute("listProductDTO", listProductDTO);
            } catch (NumberFormatException ex) {
                // Xử lý ngoại lệ: ghi log hoặc thông báo lỗi cho người dùng
            }
        }

        request.getRequestDispatcher("Shop.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
