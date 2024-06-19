package controller;

import dao.SliderDAO; // Import your DAO class
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SettingBannerControll extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Manage Banners</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Manage Banners</h1>");
            out.println("<p><a href=\"settingBanner.jsp\">Add New Banner</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String sliderTitle = request.getParameter("sliderTitle");
        String imageAvatar = request.getParameter("imageAvatar");
        String createDate = request.getParameter("createDate");
        String updateDate = request.getParameter("updateDate");
        String backLink = request.getParameter("backLink");
        
        try {
            SliderDAO dao = new SliderDAO();
            dao.insertSlider(sliderTitle, imageAvatar, createDate, updateDate, backLink);
            
            // Redirect to a success page or back to the management page
            response.sendRedirect("SettingBanner.jsp");
        } catch (SQLException | ClassNotFoundException ex) {
            // Handle exceptions
            ex.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
