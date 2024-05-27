/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOFunctionShop;
import dao.DAOShop;
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
        DAOShop dao = new DAOShop();
        DAOFunctionShop daofunction = new DAOFunctionShop();
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        listProductDTO = dao.getAllProductDTO();
        request.setAttribute("listProductDTO", listProductDTO);

        ArrayList<Category> listAllCategory = dao.getAllCategory();
        request.setAttribute("listAllCategory", listAllCategory);

        ArrayList<CategoryDTO> listTotalQuantityByCategory = dao.getTotalQuantityByCategory();
        request.setAttribute("listTotalQuantityByCategory", listTotalQuantityByCategory);

        String categoryName = request.getParameter("categoryName");
        if (categoryName != null) {
            listProductDTO = daofunction.getAllProductDTOByCategoryName(categoryName);
            request.setAttribute("listProductDTO", listProductDTO);
        }

        request.getRequestDispatcher("Shop.jsp").forward(request, response);
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
        DAOShop dao = new DAOShop();
        DAOFunctionShop daofunction = new DAOFunctionShop();
        ArrayList<ProductDTO> listProductDTO = new ArrayList<>();
        listProductDTO = dao.getAllProductDTO();
        request.setAttribute("listProductDTO", listProductDTO);

        ArrayList<Category> listAllCategory = dao.getAllCategory();
        request.setAttribute("listAllCategory", listAllCategory);

        ArrayList<CategoryDTO> listTotalQuantityByCategory = dao.getTotalQuantityByCategory();
        request.setAttribute("listTotalQuantityByCategory", listTotalQuantityByCategory);
        String productName = request.getParameter("productName");
        try {
            int maxPrice = Integer.parseInt(request.getParameter("rangeInput"));

            if (productName != null && maxPrice == 0) {
                listProductDTO = daofunction.searchProductByAttribute(productName, 0);
                request.setAttribute("listProductDTO", listProductDTO);
                request.getRequestDispatcher("Shop.jsp").forward(request, response);
            } else {
                listProductDTO = daofunction.searchProductByAttribute(productName, maxPrice);
                request.setAttribute("listProductDTO", listProductDTO);
                request.getRequestDispatcher("Shop.jsp").forward(request, response);
            }
            request.getRequestDispatcher("Shop.jsp").forward(request, response);

        } catch (NumberFormatException ex) {
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
