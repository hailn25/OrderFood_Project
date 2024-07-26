package controller;

import dao.VoucherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Account;
import utils.Validation;

@WebServlet(name = "AddVoucher", urlPatterns = {"/addVoucher"})
@MultipartConfig
public class AddVoucher extends HttpServlet {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        VoucherDAO voucherDAO = new VoucherDAO();

        String voucherName = request.getParameter("voucherName");
        String description = request.getParameter("description");
        String releaseDateStr = request.getParameter("releaseDate");
        String finishDateStr = request.getParameter("finishDate");
        String quantityStr = request.getParameter("quantity");
        String statusStr = request.getParameter("status");
        String discountStr = request.getParameter("discount");
        String voucherCategoryIdStr = request.getParameter("voucherCategoryId");

        String error = null;
        boolean hasError = false;

        // Giữ dữ liệu đầu vào để hiển thị lại khi có lỗi
        request.setAttribute("voucherName", voucherName);
        request.setAttribute("description", description);
        request.setAttribute("releaseDate", releaseDateStr);
        request.setAttribute("finishDate", finishDateStr);
        request.setAttribute("quantity", quantityStr);
        request.setAttribute("status", statusStr);
        request.setAttribute("discount", discountStr);
        request.setAttribute("voucherCategoryId", voucherCategoryIdStr);

        try {
            // Validate inputs
            if (Validation.removeAllBlank(voucherName).isEmpty()) {
                error = "Mã giảm giá không được bỏ trống.";
                hasError = true;
            } else if (Validation.removeAllBlank(description).isEmpty()) {
                error = "Nội dung không được bỏ trống.";
                hasError = true;
            } else if (statusStr == null) {
                error = "Loại voucher không được bỏ trống.";
                hasError = true;
            } else if (releaseDateStr == null || releaseDateStr.isEmpty()) {
                error = "Ngày phát hành không được bỏ trống.";
                hasError = true;
            } else if (finishDateStr == null || finishDateStr.isEmpty()) {
                error = "Ngày kết thúc không được bỏ trống.";
                hasError = true;
            } else if (quantityStr == null || quantityStr.isEmpty()) {
                error = "Số lượng không được bỏ trống.";
                hasError = true;
            } else if (discountStr == null || discountStr.isEmpty()) {
                error = "Giảm giá không được bỏ trống.";
                hasError = true;
            } else if (voucherCategoryIdStr == null || voucherCategoryIdStr.isEmpty()) {
                error = "Loại mã giảm giá không được bỏ trống.";
                hasError = true;
            } else {
                // Parse input values
                int quantity = Integer.parseInt(quantityStr);
                int status = Integer.parseInt(statusStr);
                float discount = Float.parseFloat(discountStr);
                int voucherCategoryId = Integer.parseInt(voucherCategoryIdStr);

                Date releaseDate = new Date(DATE_FORMAT.parse(releaseDateStr).getTime());
                Date finishDate = new Date(DATE_FORMAT.parse(finishDateStr).getTime());
                Date currentDate = new Date(Calendar.getInstance().getTimeInMillis());

                // Validate date ranges
                if (releaseDate.before(currentDate)) {
                    error = "Ngày phát hành phải trong tương lai.";
                    hasError = true;
                } else if (finishDate.before(currentDate)) {
                    error = "Ngày kết thúc phải trong tương lai.";
                    hasError = true;
                } else if (finishDate.before(releaseDate)) {
                    error = "Ngày kết thúc phải lớn hơn ngày phát hành.";
                    hasError = true;
                } else if (quantity <= 0) {
                    error = "Số lượng phải lớn hơn 0.";
                    hasError = true;
                } else if (discount >= 100) {
                    error = "Giảm giá phải nhỏ hơn 100.";
                    hasError = true;
                } else {
                    // Add voucher to database
                    voucherDAO.addVoucher(voucherName, description, quantity, currentDate, currentDate, status, discount, voucherCategoryId);
                    response.sendRedirect("managerVoucher");
                    return; // Exit method after successful redirect
                }
            }
        } catch (NumberFormatException | ParseException e) {
            error = "Thông tin không hợp lệ: " + e.getMessage();
            hasError = true;
        }

        // Nếu có lỗi, thiết lập thông báo lỗi và chuyển hướng trở lại trang form
        if (hasError) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("AddVoucher.jsp").forward(request, response);
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