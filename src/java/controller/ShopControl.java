/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.FunctionShopDAO;
import dao.ShopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.Validation;

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

        ArrayList<CategoryDTO> listTotalQuantityByCategory = dao.getProductQuantityByCategory();
        request.setAttribute("listTotalQuantityByCategory", listTotalQuantityByCategory);

        ArrayList<RestaurantDTO> listRestaurantDTO = dao.getAllRestaurantDTO();
        request.setAttribute("listRestaurantDTO", listRestaurantDTO);

        String categoryName = request.getParameter("categoryName");
        if (categoryName != null) {
            listProductDTO = daofunction.getAllProductDTOByCategoryName(categoryName);
            request.setAttribute("listProductDTO", listProductDTO);
        }

        if (request.getParameter("restaurantId") != null) {
            try {
                int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
                listProductDTO = daofunction.getListProductByRestaurantId(restaurantId);
                request.setAttribute("listProductDTO", listProductDTO);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        int itemsPerPage = 10;
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int totalItems = listProductDTO.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalItems);

        listProductDTO = new ArrayList<>(listProductDTO.subList(start, end));

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listProductDTO", listProductDTO);
        
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
        ShopDAO dao = new ShopDAO();
        FunctionShopDAO daofunction = new FunctionShopDAO();

        ArrayList<ProductDTO> listProductDTO = dao.getAllProductDTO();
        request.setAttribute("listProductDTO", listProductDTO);

        ArrayList<Category> listAllCategory = dao.getAllCategory();
        request.setAttribute("listAllCategory", listAllCategory);

        ArrayList<CategoryDTO> listTotalQuantityByCategory = dao.getProductQuantityByCategory();
        request.setAttribute("listTotalQuantityByCategory", listTotalQuantityByCategory);

        ArrayList<RestaurantDTO> listRestaurantDTO = dao.getAllRestaurantDTO();
        request.setAttribute("listRestaurantDTO", listRestaurantDTO);

        String productName = request.getParameter("productName");

        if (productName != null) {
            int lengthProductName = Validation.removeAllBlank(productName).length();
            if (lengthProductName > 0) {
                productName = Validation.removeUnnecessaryBlank(productName);
                listProductDTO = daofunction.searchProductByName(productName);
                request.setAttribute("productName", productName);
                request.setAttribute("listProductDTO", listProductDTO);
            } else {
                request.setAttribute("error", "Tên tìm kiếm không hợp lệ!");
            }
        }

        if (request.getParameter("rangeValue") != null) {
            try {
                int minPrice = Integer.parseInt(request.getParameter("rangeValue"));
                listProductDTO = daofunction.searchByPrice(minPrice);
                request.setAttribute("minPrice", minPrice);
                request.setAttribute("listProductDTO", listProductDTO);

            } catch (NumberFormatException e) {
                
            }
        }
        
        int itemsPerPage = 10;
        int currentPage = 1;

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int totalItems = listProductDTO.size();
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        int start = (currentPage - 1) * itemsPerPage;
        int end = Math.min(start + itemsPerPage, totalItems);

        listProductDTO = new ArrayList<>(listProductDTO.subList(start, end));

        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listProductDTO", listProductDTO);
        request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
