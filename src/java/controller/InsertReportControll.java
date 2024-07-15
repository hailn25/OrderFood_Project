
import dao.FeedbackDAO;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class InsertReportControll extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        String accountId = request.getParameter("accountId");
        String restaurantId = request.getParameter("restaurantId");
        String status = request.getParameter("status");
        String createDate = request.getParameter("createDate");

        // Get the file part from the request
        Part filePart = request.getPart("imageURL");
        String fileName = extractFileName(filePart);
        String savePath = getServletContext().getRealPath("") + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String filePath = savePath + File.separator + fileName;
        filePart.write(filePath);

        String imageURL = fileName;

        try {
            FeedbackDAO dao = new FeedbackDAO();
            dao.insertReport(description, imageURL, accountId, restaurantId, status, createDate);

            request.setAttribute("successMessage", "Phản hồi thành công!");
            request.getRequestDispatcher("Report.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi: " + ex.getMessage());
            request.getRequestDispatcher("Report.jsp").forward(request, response);
        }
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
