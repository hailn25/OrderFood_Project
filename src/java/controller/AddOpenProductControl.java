package controller;

import dao.ProductDAO;
import dao.RestaurantDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
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
import model.Account;

@WebServlet(name = "AddControl", urlPatterns = {"/addOpenProduct"})
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
                img = request.getParameter("currentImage");
            }

// Thay bằng lấy từ session nếu cần
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int accountId = a.getAccountId();
            RestaurantDAO dao2 = new RestaurantDAO();
            int restaurantId = dao2.getRestaurantIdByAccountId(accountId);

            ProductDAO dao = new ProductDAO();
            dao.insertProduct(name, price, description, img, category, restaurantId, quantity, status);

            response.sendRedirect("managerOpenProduct");
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