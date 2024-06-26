package controller;

import dao.ProductDAO;
import dao.RestaurantDAO;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.time.LocalDate;
import model.Account;
import utils.Validation;

@WebServlet(name = "AddOpenControl", urlPatterns = {"/addOpenProduct"})
@MultipartConfig
public class AddOpenProductControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String status = request.getParameter("status");
            LocalDate updateDate = LocalDate.now();
            LocalDate createDate = LocalDate.now();

// Xử lý file upload
            Part filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();

// Kiểm tra nếu có tệp mới được tải lên
            String img = null;
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
                filePart.write(uploadPath);
                img = fileName;
            }

// Kiểm tra các trường nhập liệu
            int lengthName = Validation.removeAllBlank(name).length();
            int lengthPrice = Validation.removeAllBlank(price).length();
            int lengthQuantity = Validation.removeAllBlank(quantity).length();
            int lengthDescription = Validation.removeAllBlank(description).length();

            if (img == null) {
                request.setAttribute("error", "Chưa chọn ảnh!");
                request.getRequestDispatcher("managerAddOpenProduct").forward(request, response);
            } else if (lengthName > 0 && lengthPrice > 0 && lengthQuantity > 0 && lengthDescription > 0) {
                name = Validation.removeUnnecessaryBlank(name);
                price = Validation.removeAllBlank(price);
                quantity = Validation.removeAllBlank(quantity);
                description = Validation.removeUnnecessaryBlank(description);

                // Lấy accountId từ session
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("account");
                int accountId = a.getAccountId();

                // Lấy restaurantId dựa trên accountId
                RestaurantDAO dao2 = new RestaurantDAO();
                int restaurantId = dao2.getRestaurantIdByAccountId(accountId);

                // Thêm sản phẩm mới vào cơ sở dữ liệu
                ProductDAO dao = new ProductDAO();
                dao.insertProduct(name, price, description, img, category, restaurantId, quantity,  Date.valueOf(updateDate), Date.valueOf(createDate), status);
                request.getRequestDispatcher("managerOpenProduct").forward(request, response);
            } else {
                request.setAttribute("error", "Nhập không hợp lệ!");
                request.getRequestDispatcher("managerAddOpenProduct").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddOpenProductControl.class.getName()).log(Level.SEVERE, null, ex);
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
