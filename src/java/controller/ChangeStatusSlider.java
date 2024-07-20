/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.RestaurantDAO;
import dao.SliderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author quoch
 */
@WebServlet(name = "ChangeStatusSlider", urlPatterns = {"/changeStatusSlider"})
public class ChangeStatusSlider extends HttpServlet {

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
        SliderDAO sliderDAO = new SliderDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        int changeStatus = Integer.parseInt(request.getParameter("changeStatus"));
        int sliderId = Integer.parseInt(request.getParameter("sliderId"));
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        int restaurantId = restaurantDAO.getRestaurantIdByAccountId(Integer.parseInt(request.getParameter("accountId")));
        String updateDate = request.getParameter("updateDate");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        String formattedDate = "2024-01-01";
        try {
            date = formatter.parse(updateDate);

            // Sử dụng Calendar để cộng thêm một tháng và đặt ngày là 01
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);

            // Lấy ngày cập nhật
            Date updatedDate = calendar.getTime();
            formattedDate = formatter.format(updatedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        if (changeStatus == 3) {
            sliderDAO.changeStatusSlider(sliderId, changeStatus, restaurantId, formattedDate);
        } else if(changeStatus == 2) {
            sliderDAO.changeStatusSlider(sliderId, changeStatus, restaurantId, formattedDate);
        } else {
            sliderDAO.changeStatusSlider(sliderId, changeStatus, accountId, updateDate);
        }

        request.getRequestDispatcher("managerService").forward(request, response);
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
