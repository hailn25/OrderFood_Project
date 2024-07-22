/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Cart;
import model.Product;
import model.Voucher;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ShowVoucherServlet", urlPatterns = {"/showVoucher"})
public class ShowVoucherServlet extends HttpServlet {

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
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account");
    session.setMaxInactiveInterval(180);

    if (account == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
        cart = new Cart();
    }

    int productId = 0;
    if (request.getParameter("productId") != null) {
        productId = Integer.parseInt(request.getParameter("productId"));
    }

    int aid = account.getAccountId();
    ProductDAO dao = new ProductDAO();
    List<Integer> listProductId = cart.getAllProductIdOfCart();
    List<Integer> listRestaurantId = dao.getRestaurantId(listProductId);
    VoucherDAO voucherDAO = new VoucherDAO();
    ArrayList<Voucher> listFree = voucherDAO.getAllVoucherWithQuantityByAccountIdFree(aid);
    ArrayList<Voucher> listR = voucherDAO.getAllVoucherWithQuantityByAccountIdR(aid, listRestaurantId);

    request.setAttribute("productId", productId);
    request.setAttribute("listF", listFree);
    request.setAttribute("listR", listR);
    request.getRequestDispatcher("UseVoucher.jsp").forward(request, response);
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