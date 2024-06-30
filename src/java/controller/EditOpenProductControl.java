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

@WebServlet(name = "EditOpenProductControl", urlPatterns = {"/editOpenProduct"})
@MultipartConfig
public class EditOpenProductControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            // Lấy các tham số từ form
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String priceStr = request.getParameter("price");
            String quantityStr = request.getParameter("quantity");
            String status = request.getParameter("status");
            String isSale = request.getParameter("isSale");
            LocalDate updateDate = LocalDate.now();

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

            // Kiểm tra nếu có tệp mới được tải lên
            String img = null;
            if (fileName != null && !fileName.isEmpty()) {
                if (fileExtension.equals("jpg") || fileExtension.equals("png") || fileExtension.equals("webp")) {
                    String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
                    filePart.write(uploadPath);
                    img = fileName;
                } else {
                    request.setAttribute("error", "Chỉ chấp nhận các tệp JPG, PNG, hoặc WebP!");
                    request.getRequestDispatcher("loadOpenProduct?pid=" + id + "&cid=" + category + "&status=" + status).forward(request, response);
                    return;
                }
            } else {
                img = request.getParameter("OldImage");
            }

            if (lengthName >= 2 && lengthName <= 30 && lengthDescription >= 4 && lengthDescription <= 500 && isNumeric) {

                name = Validation.removeUnnecessaryBlank(name);
                priceStr = Validation.removeAllBlank(priceStr);
                quantityStr = Validation.removeAllBlank(quantityStr);
                description = Validation.removeUnnecessaryBlank(description);

                ProductDAO dao = new ProductDAO();

                // Lấy giá trị isSale hiện tại của sản phẩm
                String currentIsSale = dao.getCurrentIsSale(id);

                // Cập nhật sản phẩm
                dao.editProduct(name, priceStr, description, img, category, isSale, quantityStr, status, Date.valueOf(updateDate), id);

                // Kiểm tra điều kiện và cập nhật giá bán nếu cần
                if (!isSale.equals(currentIsSale)) {
                    if (isSale.equals("1")) {
                        dao.updatePriceSale_on(id);
                    } else if (isSale.equals("0")) {
                        dao.updatePriceSale_off(id);
                    }
                }

                response.sendRedirect("managerOpenProduct");
            } else {
                request.setAttribute("error", "Nhập không hợp lệ!");
                request.getRequestDispatcher("loadOpenProduct?pid=" + id + "&cid=" + category + "&status=" + status).forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditOpenProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditOpenProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditOpenProductControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
