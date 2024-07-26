package controller;

import dao.FeedbackDAO;
import dao.RestaurantDAO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;

@WebServlet(name = "InsertReportControll", urlPatterns = {"/insertReport"})
@MultipartConfig
public class InsertReportControll extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";
    private static final Logger logger = Logger.getLogger(InsertReportControll.class.getName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertReportControll</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertReportControll at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String restaurantIdParam = request.getParameter("restaurantId");
        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            int restaurantId = Integer.parseInt(restaurantIdParam);
            RestaurantDAO dao = new RestaurantDAO();
            String restaurantName = dao.getRestaurantNameByRestaurantId(restaurantId);
            request.setAttribute("restaurantName", restaurantName);
            request.setAttribute("restaurantId", restaurantId);
        }
        request.getRequestDispatcher("Report.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        String description = request.getParameter("description");
        String createDate = request.getParameter("createDate");
        String accountId = request.getParameter("accountId");
        Part filePart = request.getPart("imageURL");
        String restaurantIdParam = request.getParameter("restaurantId");
        String status = request.getParameter("status");

        logger.log(Level.INFO, "POST - restaurantId: {0}", restaurantIdParam);
        if (restaurantIdParam != null && !restaurantIdParam.isEmpty()) {
            int restaurantId = Integer.parseInt(restaurantIdParam);
            String restaurantName = restaurantDAO.getRestaurantNameByRestaurantId(restaurantId);
            request.setAttribute("restaurantName", restaurantName);
            
            // Save file to the server
            String fileName = extractFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String savePath = applicationPath + File.separator + SAVE_DIR;
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            String filePath = savePath + File.separator + fileName;
            filePart.write(filePath);
            String imageURL = fileName;

            try {
                feedbackDAO.insertReport(description, imageURL, createDate, accountId, String.valueOf(restaurantId), status);
                request.setAttribute("successMessage", "Report has been submitted successfully!");
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                request.setAttribute("errorMessage", "Error occurred: " + ex.getMessage());
            }
        } else {
            request.setAttribute("errorMessage", "Restaurant information is missing.");
        }
        request.getRequestDispatcher("Report.jsp").forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}


