/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductHomeDAO;
import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.ListProduct;
import model.ProductHome;
import model.SliderDTO;

/**
 *
 * @author ADMIN
 */
public class SearchController extends HttpServlet {

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
        String txtSearch = request.getParameter("txt");
        request.setCharacterEncoding("UTF-8");
        ProductHomeDAO dao = new ProductHomeDAO();
        SliderDAO sliderDAO = new SliderDAO();
        List<ProductHome> list = dao.getProductBySearchName(txtSearch);
        List<ProductHome> listBestSellerProduct = dao.getAllOSTProduct();
        List<ProductHome> listProduct = dao.getProductHome();
        ArrayList<SliderDTO> listSlider = sliderDAO.getAllSliderDTO();
        ArrayList<SliderDTO> listSliderDot = new ArrayList<>();

        for (SliderDTO s : listSlider) {
            if (s.getStatusName().equals("Xác nhận")) {
                listSliderDot.add(s);
            }
        }

        request.setAttribute("listP", list);
        request.setAttribute("listV", listProduct);
        request.setAttribute("listB", listBestSellerProduct);
        request.setAttribute("listSlider", listSlider);
        request.setAttribute("listSliderDot", listSliderDot);
        request.getRequestDispatcher("Home.jsp").forward(request, response);
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
