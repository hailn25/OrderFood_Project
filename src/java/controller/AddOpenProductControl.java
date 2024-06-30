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
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String status = request.getParameter("status");
            String isSale = request.getParameter("sale");
            LocalDate updateDate = LocalDate.now();
            LocalDate createDate = LocalDate.now();

            // Kiểm tra các trường nhập liệu
            int lengthName = Validation.removeAllBlank(name).length();
            int lengthDescription = Validation.removeAllBlank(description).length();

            double price = 0;
            int quantity = 0;
            boolean isNumeric = true;

            // Kiểm tra xem giá trị của price và quantity có phải là số không và lớn hơn 0
            try {
                price = Double.parseDouble(priceStr);
                quantity = Integer.parseInt(quantityStr);
                if (price <= 0 || quantity <= 0) {
                    isNumeric = false;
                }
            } catch (NumberFormatException e) {
                isNumeric = false;
            }

            // Xử lý file upload
            Part filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            // Kiểm tra nếu có tệp mới được tải lên và có đuôi phù hợp
            String img = null;
            if (fileName != null && !fileName.isEmpty()) {
                if (fileExtension.equals("jpg") || fileExtension.equals("png") || fileExtension.equals("webp")) {
                    String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
                    filePart.write(uploadPath);
                    img = fileName;
                } else {
                    request.setAttribute("error", "Chỉ chấp nhận các tệp JPG, PNG, hoặc WebP!");
                    request.getRequestDispatcher("managerAddOpenProduct").forward(request, response);
                    return;
                }
            }

            if (img == null) {
                request.setAttribute("error", "Chưa chọn ảnh!");
                request.getRequestDispatcher("managerAddOpenProduct").forward(request, response);
            } else if (lengthName >= 2 && lengthName <= 30 && lengthDescription >= 4 && lengthDescription <= 500 && isNumeric) {
                name = Validation.removeUnnecessaryBlank(name);
                priceStr = Validation.removeAllBlank(priceStr);
                quantityStr = Validation.removeAllBlank(quantityStr);
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
                dao.insertProduct(name, priceStr, description, img, category, restaurantId, isSale, quantityStr,  Date.valueOf(updateDate), Date.valueOf(createDate), status);
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
