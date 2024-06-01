package controller;

import dao.ProductDAO;
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
import jakarta.servlet.http.Part;

@WebServlet(name = "AddControl", urlPatterns = {"/addProduct"})
@MultipartConfig
public class AddProductControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String quantity = request.getParameter("quantity");
            String description = request.getParameter("description");
            String category = request.getParameter("category");

//            // Xử lý file upload
//            Part filePart = request.getPart("image"); // Lấy file từ request
//            if (filePart != null && filePart.getSize() > 0) {
//                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//
//                // Đường dẫn thư mục lưu ảnh
//                String uploadPath = getServletContext().getRealPath("") + File.separator + "img";
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) uploadDir.mkdir();
//
//                // Lưu file
//                filePart.write(uploadPath + File.separator + fileName);
//
//                // Đường dẫn ảnh sau khi upload
//                String img = "img/" + fileName;
//
//                // Thay bằng lấy từ session nếu cần
//                int restaurantId = 3;
//
//                ProductDAO dao = new ProductDAO();
//                dao.insertProduct(name, price, description, img, category, restaurantId, quantity);
//            } else {
//                // Xử lý khi không có file được upload
//                // Bạn có thể thêm thông báo lỗi hoặc xử lý khác tùy theo yêu cầu của bạn
//                response.getWriter().println("No file uploaded or file is empty");
//            }
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
            int restaurantId = 3;

            ProductDAO dao = new ProductDAO();
            dao.insertProduct(name, price, description, img, category, restaurantId, quantity);

            response.sendRedirect("managerProduct");
        } catch (SQLException ex) {
            Logger.getLogger(AddProductControl.class.getName()).log(Level.SEVERE, null, ex);
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
