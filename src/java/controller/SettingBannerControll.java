package controller;

import dao.SliderDAO; // Import your DAO class
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.PrintWriter;
import java.util.Collection;
import model.Account;

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
            out.println("<title>Servlet VoucherControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoucherControl at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int updateBy = account.getAccountId();

        // Define the directory to upload files
        String uploadDir = getServletContext().getRealPath("/") + "uploads";
        Path uploadPath = Paths.get(uploadDir);

        // Create the upload directory if it doesn't exist
        Files.createDirectories(uploadPath);

        try {
            // Get all parts of the request
            Collection<Part> parts = request.getParts();

            // Iterate through all parts and process file uploads
            for (Part part : parts) {
                if (part.getContentType() != null && part.getContentType().startsWith("image")) {
                    String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String uniqueFileName = originalFileName;
                    Path imagePath = uploadPath.resolve(uniqueFileName);
                    InputStream fileContent = part.getInputStream();

                    // Save the file to the server
                    Files.copy(fileContent, imagePath);

                    // Insert slider details into the database
                    SliderDAO dao = new SliderDAO();
                    dao.insertSlider(sliderTitle, uniqueFileName, 1, 1, updateBy, createDate, updateDate, backLink);
                }
            }

            // If insertion is successful, set a success message
            request.setAttribute("message", "Gửi thành công.");
        } catch (SQLException ex) {
            // Set an error message for SQL exceptions
            request.setAttribute("error", "Gửi lỗi: " + ex.getMessage());
        } catch (IOException ex) {
            // Set an error message for IO exceptions
            request.setAttribute("error", "Lỗi lưu ảnh: " + ex.getMessage());
        } finally {
            // Forward to the same page for message display
            request.getRequestDispatcher("SettingBanner.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
