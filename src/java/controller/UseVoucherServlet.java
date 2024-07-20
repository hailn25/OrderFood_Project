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
        session.setMaxInactiveInterval(180);
        if (account == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        int productId = 0;
        if (request.getParameter("productId") != null) {
            productId = Integer.parseInt(request.getParameter("productId"));
        }
        int aid = account.getAccountId();
        ProductDAO dao = new ProductDAO();
        List<Integer> listProductId = cart.getAllProductIdOfCart();
        List<Integer> listRestaurantId = dao.getRestaurantId(listProductId);
        VoucherDAO voucherDAO = new VoucherDAO();
        ArrayList<Voucher> listFree = voucherDAO.getAllVoucherWithQuantityByAccountIdFree(aid);
        ArrayList<Voucher> listR = voucherDAO.getAllVoucherWithQuantityByAccountIdR(aid, listRestaurantId);
        request.setAttribute("listF", listFree);
        request.setAttribute("listR", listR);

        String voucherFreeIdStr = request.getParameter("voucherFree");
        String voucherRIdStr = request.getParameter("voucherR");

        if (voucherFreeIdStr != null && !voucherFreeIdStr.isEmpty()) {
            int voucherFreeId = Integer.parseInt(voucherFreeIdStr);
            request.setAttribute("listFree", voucherDAO.getDiscountByVoucherId(voucherFreeId));
        }

        if (voucherRIdStr != null && !voucherRIdStr.isEmpty()) {
            int voucherRId = Integer.parseInt(voucherRIdStr);
            request.setAttribute("listVoucherR", voucherDAO.getDiscountByVoucherId(voucherRId));
        }
        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        session.setAttribute("size", list.size());
        request.getRequestDispatcher("Checkout_2.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
