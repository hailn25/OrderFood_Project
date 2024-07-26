package controller;

import dao.VoucherDAO;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Voucher;
import utils.Validation;

@WebServlet(name = "EditVoucher", urlPatterns = {"/editVoucher"})
@MultipartConfig
public class EditVoucher extends HttpServlet {

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
        try {
            int voucherId = Integer.parseInt(request.getParameter("vid"));
            Voucher voucher = voucherDAO.getVoucherById(voucherId);
            request.setAttribute("voucher", voucher);
            request.getRequestDispatcher("EditVoucher.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID mã giảm giá không hợp lệ");
            request.getRequestDispatcher("EditVoucher.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        
        try {
            VoucherDAO voucherDAO = new VoucherDAO();
            int voucherId = Integer.parseInt(request.getParameter("id"));
            String voucherName = request.getParameter("voucherName");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            int voucherCategoryId = Integer.parseInt(request.getParameter("voucherCategoryId"));
            Date releaseDate = dateFormat.parse(request.getParameter("releaseDate"));
            Date finishDate = dateFormat.parse(request.getParameter("finishDate"));
            float discount = Float.parseFloat(request.getParameter("discount"));
            int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
            int status = Integer.parseInt(request.getParameter("status"));
            Date today = new Date();
            if (releaseDate.before(today) || finishDate.before(today) || finishDate.before(releaseDate)) {
                String error = "Ngày phát hành và ngày kết thúc phải lớn hơn ngày hôm nay và ngày kết thúc phải lớn hơn ngày phát hành.";
                request.setAttribute("error", error);
                request.setAttribute("voucher", new Voucher(voucherId, voucherName, description, quantity, releaseDate, finishDate, status, discount, voucherCategoryId, restaurantId));
                request.getRequestDispatcher("EditVoucher.jsp").forward(request, response);
                return;
            }
            String error = "Thông tin không hợp lệ";
            int lengthVoucherName = Validation.removeAllBlank(voucherName).length();
            int lengthDescription = Validation.removeAllBlank(description).length();

            if (lengthVoucherName == 0 || lengthDescription == 0 || request.getParameter("status") == null || quantity <= 0 || discount > 100) {
                if (lengthVoucherName == 0) {
                    error = "Tên mã giảm giá không được để trống";
                } else if (lengthDescription == 0) {
                    error = "Nội dung không được để trống";
                } else if (quantity <= 0) {
                    error = "Số lượng phải lớn hơn 0";
                } else if (discount > 100) {
                    error = "Giảm giá không được vượt quá 100%";
                } else if (request.getParameter("status") == null) {
                    error = "Vui lòng chọn trạng thái";
                }

                request.setAttribute("error", error);
                request.setAttribute("voucher", new Voucher(voucherId, voucherName, description, quantity, releaseDate, finishDate, status, discount, voucherCategoryId, restaurantId));
                request.getRequestDispatcher("EditVoucher.jsp").forward(request, response);
            } else {
                voucherDAO.editVoucher(voucherId, voucherName, description, quantity, releaseDate, finishDate, status, discount, voucherCategoryId);
                response.sendRedirect("managerVoucher");
            }
        } catch (ParseException ex) {
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}