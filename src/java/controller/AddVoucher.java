package controller;

import dao.VoucherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import utils.Validation;

@WebServlet(name = "AddVoucher", urlPatterns = {"/addVoucher"})
@MultipartConfig
public class AddVoucher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        VoucherDAO voucherDAO = new VoucherDAO();
        String voucherName = request.getParameter("voucherName");
        String description = request.getParameter("description");
        String releaseDate = request.getParameter("releaseDate");
        String finishDate = request.getParameter("finishDate");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int status = Integer.parseInt(request.getParameter("status"));

        Date createDate = new Date();
        Date updateDate = new Date();
        String error = "Thông tin không hợp lệ";

        int lengthVoucherName = Validation.removeAllBlank(voucherName).length();
        int lengthDescription = Validation.removeAllBlank(description).length();
        
        if (lengthVoucherName == 0 || lengthDescription == 0 || request.getParameter("status") == null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("AddVoucher.jsp").forward(request, response);
        } else {
            voucherDAO.addVoucher(voucherName, description, quantity, createDate, updateDate, status);
            response.sendRedirect("managerVoucher");
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
