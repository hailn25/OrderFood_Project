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

        String productName = request.getParameter("productName");
        String minPriceSTR = request.getParameter("rangeInput");

        if (productName != null) {
            int lengthProductName = Validation.removeAllBlank(productName).length();
            if (lengthProductName > 0) {
                try {
                    productName = Validation.removeUnnecessaryBlank(productName);
                    int minPrice = Integer.parseInt(minPriceSTR);
                    listProductDTO = daofunction.searchProductByAttribute(productName, minPrice);
                    request.setAttribute("productName", productName);
                    request.setAttribute("listProductDTO", listProductDTO);
                } catch (NumberFormatException ex) {

                }
            } else {
                request.setAttribute("error", "Tên tìm kiếm không hợp lệ!");
            }
        }

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
