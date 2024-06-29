package controller;

import dao.SliderDAO; // Import your DAO class
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@MultipartConfig
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
        String createDate = request.getParameter("createDate");
        String updateDate = request.getParameter("updateDate");
        String backLink = request.getParameter("backLink");

        Part filePart = request.getPart("imageAvatar");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();

        String imagePath = fileName; // Define your upload directory

        try {
            // Save the file to the server
            Files.copy(fileContent, Paths.get(getServletContext().getRealPath("/") + imagePath));

            SliderDAO dao = new SliderDAO();
            dao.insertSlider(sliderTitle, imagePath, 1, 1, 2, createDate, updateDate, backLink);

            // If insertion is successful, set a success message
            request.setAttribute("message", "Insert successful.");
            // Forward to the same page (or another page for success message display)
            request.getRequestDispatcher("SettingBanner.jsp").forward(request, response);
        } catch (SQLException ex) {
            // If there's an SQL exception, set an error message
            request.setAttribute("error", "Error inserting slider: " + ex.getMessage());
            // Forward to the same page (or another page for error message display)
            request.getRequestDispatcher("SettingBanner.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
