/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.MessageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.MessageUser;
import model.RestaurantName;

/**
 *
 * @author quoch
 */
@WebServlet(name = "MessageUserControl", urlPatterns = {"/messageUser"})
public class MessageUserControl extends HttpServlet {

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
        MessageDAO messsageDAO = new MessageDAO();
        int userId = 0;
        int restaurantStr = 0;
        if(request.getParameter("userId") != null && request.getParameter("restaurantId") != null){
             userId = Integer.parseInt(request.getParameter("userId"));
             restaurantStr = Integer.parseInt(request.getParameter("restaurantId"));
        }
        
        request.setAttribute("error", "Chào mừng bạn đến với tin nhắn!");
        request.setAttribute("userId", userId);
        int restaurantId = messsageDAO.getAccountIdByRestaurantId(restaurantStr);
        request.setAttribute("restaurantId", restaurantId);

        ArrayList<RestaurantName> listRestaurantName = messsageDAO.getListRestaurantName(userId);
        request.setAttribute("listRestaurantName", listRestaurantName);
        ArrayList<MessageUser> listMessageUser = messsageDAO.getMessageUser(userId, restaurantId);
        request.setAttribute("listMessageUser", listMessageUser);

        request.getRequestDispatcher("MessageUser.jsp").forward(request, response);
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


