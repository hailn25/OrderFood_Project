package controller;

import dao.AccountDAO;
import dao.ProductDAO;
import dao.RestaurantDAO;
import dao.RevenueDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hailt
 */
@WebServlet(name = "RevenueAdmin", urlPatterns = {"/revenueAdmin"})
public class RevenueAdmin extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        RevenueDAO dao = new RevenueDAO();
        AccountDAO dao1 = new AccountDAO();
        RestaurantDAO dao2 = new RestaurantDAO();
        ProductDAO dao3 = new ProductDAO();
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int currentMonth = date.getMonthValue();
        int quantityOfAccount = dao1.getQuantityOfAccount();
        int quantityOfRestaurant = dao2.getQuantityOfRestaurant();
        int quantityOfProduct = dao3.getQuantityOfProduct();
        
        double t1 = (currentMonth >= 1) ? dao.getRevenueOfWeb(1, year) + (dao.AccountValid1(year) * 1000000) + dao.getRevenueSliderOfWeb(1, year) : 0;
        double t2 = (currentMonth >= 2) ? dao.getRevenueOfWeb(2, year) + (dao.AccountValid2(year) * 1000000) + dao.getRevenueSliderOfWeb(2, year) : 0;
        double t3 = (currentMonth >= 3) ? dao.getRevenueOfWeb(3, year) + (dao.AccountValid3(year) * 1000000) + dao.getRevenueSliderOfWeb(3, year) : 0;
        double t4 = (currentMonth >= 4) ? dao.getRevenueOfWeb(4, year) + (dao.AccountValid4(year) * 1000000) + dao.getRevenueSliderOfWeb(4, year) : 0;
        double t5 = (currentMonth >= 5) ? dao.getRevenueOfWeb(5, year) + (dao.AccountValid5(year) * 1000000) + dao.getRevenueSliderOfWeb(5, year) : 0;
        double t6 = (currentMonth >= 6) ? dao.getRevenueOfWeb(6, year) + (dao.AccountValid6(year) * 1000000) + dao.getRevenueSliderOfWeb(6, year) : 0;
        double t7 = (currentMonth >= 7) ? dao.getRevenueOfWeb(7, year) + (dao.AccountValid7(year) * 1000000) + dao.getRevenueSliderOfWeb(7, year) : 0;
        double t8 = (currentMonth >= 8) ? dao.getRevenueOfWeb(8, year) + (dao.AccountValid8(year) * 1000000) + dao.getRevenueSliderOfWeb(8, year) : 0;
        double t9 = (currentMonth >= 9) ? dao.getRevenueOfWeb(9, year) + (dao.AccountValid9(year) * 1000000) + dao.getRevenueSliderOfWeb(9, year) : 0;
        double t10 = (currentMonth >= 10) ? dao.getRevenueOfWeb(10, year) + (dao.AccountValid10(year) * 1000000) + dao.getRevenueSliderOfWeb(10, year) : 0;
        double t11 = (currentMonth >= 11) ? dao.getRevenueOfWeb(11, year) + (dao.AccountValid11(year) * 1000000) + dao.getRevenueSliderOfWeb(11, year) : 0;
        double t12 = (currentMonth >= 12) ? dao.getRevenueOfWeb(12, year) + (dao.AccountValid12(year) * 1000000) + dao.getRevenueSliderOfWeb(12, year) : 0;

        double totalRevenue = t1 + t2 + t3 + t4 + t5 + t6 + t7 + t8 + t9 + t10 + t11 + t12;

        DecimalFormat df = new DecimalFormat("#,###");

        request.setAttribute("t1", t1);
        request.setAttribute("t2", t2);
        request.setAttribute("t3", t3);
        request.setAttribute("t4", t4);
        request.setAttribute("t5", t5);
        request.setAttribute("t6", t6);
        request.setAttribute("t7", t7);
        request.setAttribute("t8", t8);
        request.setAttribute("t9", t9);
        request.setAttribute("t10", t10);
        request.setAttribute("t11", t11);
        request.setAttribute("t12", t12);
        request.setAttribute("totalRevenue", df.format(totalRevenue));
        
        request.setAttribute("totalAccounts", quantityOfAccount);
        request.setAttribute("totalStores", quantityOfRestaurant);
        request.setAttribute("totalProducts", quantityOfProduct);

        request.getRequestDispatcher("ManagerDashboardAdmin.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RevenueAdmin.class.getName()).log(Level.SEVERE, null, ex);
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
