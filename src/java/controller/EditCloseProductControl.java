package controller;

import dao.ProductDAO;
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
import java.sql.Date;
import java.time.LocalDate;
import utils.Validation;

@WebServlet(name = "EditCloseProductControl", urlPatterns = {"/editCloseProduct"})
@MultipartConfig
public class EditCloseProductControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            // Lấy các tham số từ form
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            String status = request.getParameter("status");
            LocalDate updateDate = LocalDate.now();

            // Xử lý file upload
            Part filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();

            // Kiểm tra nếu có tệp mới được tải lên
            String img = null;
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
                filePart.write(uploadPath);
                img = fileName;
            } else {
                img = request.getParameter("OldImage");
            }

            int lengthName = Validation.removeAllBlank(name).length();
            int lengthPrice = Validation.removeAllBlank(price).length();
            int lengthQuantity = Validation.removeAllBlank(quantity).length();
            int lengthDescription = Validation.removeAllBlank(description).length();
            if (lengthName > 0 && lengthPrice > 0 && lengthQuantity > 0 && lengthDescription > 0) {

                name = Validation.removeUnnecessaryBlank(name);
                price = Validation.removeAllBlank(price);
                quantity = Validation.removeAllBlank(quantity);
                description = Validation.removeUnnecessaryBlank(description);

                ProductDAO dao = new ProductDAO();
                dao.editProduct(name, price, description, img, category, quantity, status, Date.valueOf(updateDate), id);

                response.sendRedirect("managerCloseProduct");
            } else {
                request.setAttribute("error", "Nhập không hợp lệ!");
                request.getRequestDispatcher("loadCloseProduct?pid=" + id + "&cid=" + category + "&status=" + status).forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditOpenProductControl.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
