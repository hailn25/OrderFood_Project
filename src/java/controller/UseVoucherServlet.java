package controller;

import dao.ProductDAO;
import dao.VoucherDAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Cart;
import model.Item;
import model.Voucher;

@WebServlet(name = "UseVoucherServlet", urlPatterns = {"/useVoucher"})
public class UseVoucherServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        VoucherDAO voucherDAO = new VoucherDAO();
        String voucherRIdStr = request.getParameter("voucherR");
        String voucherFreeStr = request.getParameter("voucherFree");

        double shippingFee = 30000;
        double shippingDiscount = 0;
        double subtotal = 0;
        double voucherDiscount = 0;

        // Apply restaurant-specific voucher
        if (voucherRIdStr != null && !voucherRIdStr.isEmpty()) {
            int voucherRId = Integer.parseInt(voucherRIdStr);
            Voucher voucherR = voucherDAO.getDiscountByVoucherRId(voucherRId);
            int restaurantId = voucherR.getRestaurantId();
            List<Item> items = cart.getItems();
            for (Item item : items) {
                double price = item.getProduct().getPrice();
                if (item.getProduct().getRestaurantId() == restaurantId) {
                    double discount = price * voucherR.getDiscount() / 100;
                    item.setDiscountedPrice(price - discount);
                    voucherDiscount += discount * item.getQuantity();
                } else {
                    item.setDiscountedPrice(price);
                }
                subtotal += item.getDiscountedPrice() * item.getQuantity();
            }

            voucherDAO.updateQuantity(voucherRId);

        } else {
            List<Item> items = cart.getItems();
            for (Item item : items) {
                item.setDiscountedPrice(item.getProduct().getPrice());
                subtotal += item.getDiscountedPrice() * item.getQuantity();
            }
        }

        // Apply free shipping voucher
        if (voucherFreeStr != null && !voucherFreeStr.isEmpty()) {
            int voucherFreeId = Integer.parseInt(voucherFreeStr);
            int shippingDiscountRate = voucherDAO.getDiscountByVoucherId(voucherFreeId);
            shippingDiscount = shippingFee * (shippingDiscountRate / 100.0); // Chia bằng 100.0 để có phép chia chính xác
            voucherDAO.updateQuantity(voucherFreeId);
        }

        double total = subtotal + shippingFee - shippingDiscount - voucherDiscount;
        request.setAttribute("subtotal", subtotal);
        request.setAttribute("shippingFee", shippingFee);
        request.setAttribute("shippingDiscount", shippingDiscount);
        request.setAttribute("voucherDiscount", voucherDiscount);
        request.setAttribute("total", total);

        session.setAttribute("cart", cart);
        request.getRequestDispatcher("Checkout_2.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
