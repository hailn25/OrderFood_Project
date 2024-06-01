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

@WebServlet(name = "EditProductControl", urlPatterns = {"/editProduct"})
@MultipartConfig
public class EditProductControl extends HttpServlet {

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
            String stock = request.getParameter("quantity");

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

            // Gọi DAO để cập nhật sản phẩm
            ProductDAO dao = new ProductDAO();
            dao.editProduct(name, price, description, img, category, stock, id);

            // Chuyển hướng sau khi cập nhật thành công
            response.sendRedirect("managerProduct");
        } catch (SQLException ex) {
            Logger.getLogger(EditProductControl.class.getName()).log(Level.SEVERE, null, ex);
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
