package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import model.Account;
import model.Cart;
import model.EmailHandler;
import model.Item;
import model.Product;

@WebServlet(name = "Checkout2Servlet", urlPatterns = {"/checkout2"})
public class Checkout2Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Checkout2Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Checkout2Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

        String productId = request.getParameter("productId");
        String quantityStr = request.getParameter("quantityCart");
        int quantity = 1;

        if (quantityStr != null && !quantityStr.isEmpty()) {
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                quantity = 1;
            }
        }

        try {
            if (productId != null) {
                int id = Integer.parseInt(productId);
                ProductDAO dao = new ProductDAO();
                Product p = dao.getProductByID(id);
                double price = p.getPrice();
                int maxquantity = dao.getQuantityProduct(id);
                session.setAttribute("maxquantity", maxquantity);
                Item t = new Item(p, quantity, price, 0);
                cart.addItem(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("Checkout_2.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("home");
            return;
        }

        Account account = (Account) session.getAttribute("account");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        String payment = request.getParameter("payment");
        String total = request.getParameter("cost");

        OrderDAO dao = new OrderDAO();
        int accountId = account.getAccountId();
        dao.insertNewOrder(1, accountId, Double.parseDouble(total), name, email, phone, address, note);
        int orderId = dao.getOrderID();

        String paymentStatus = payment.equals("cod") ? "Thanh toán khi nhận hàng" : "Thanh toán thành công";

        if (payment.equals("cod")) {
            for (Item item : cart.getItems()) {
                dao.insertNewOrderDetail(orderId, item.getProduct().getProductId(), item.getQuantity(), item.getPrice() * item.getQuantity(), payment, paymentStatus);
                dao.updateQuantity(item.getProduct().getProductId(), item.getQuantity());
            }
        }

        if (payment.equals("vnpay")) {
            session.setAttribute("fullname", name);
            session.setAttribute("address", address);
            session.setAttribute("phone", phone);
            session.setAttribute("email", email);
            session.setAttribute("note", note);
            session.setAttribute("amount", (long) Double.parseDouble(total));
            response.sendRedirect("paymentvnpay");
        } else {
            Double subtotal = Double.parseDouble(request.getParameter("subtotal"));
            Double voucherDiscount = Double.parseDouble(request.getParameter("voucherDiscount"));
            Double shippingDiscount = Double.parseDouble(request.getParameter("shippingDiscount"));
            Double totalAmount = Double.parseDouble(request.getParameter("total"));

            sendOrderConfirmationEmail(name, address, phone, email, cart, note, dao, subtotal, shippingDiscount, voucherDiscount, totalAmount);
            session.setAttribute("size", 0);
            request.getRequestDispatcher("Buysuccessfull.jsp").forward(request, response);
        }
    }

    private void sendOrderConfirmationEmail(String name, String address, String phone, String email, Cart cart, String note, OrderDAO dao, double subtotal, double shippingDiscount, double voucherDiscount, double totalAmount) {
        int orderDetailId = dao.getOrderDetailId();
        NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        formatter.setGroupingUsed(true);

        String subject = "4FoodHD - Xác nhận đơn hàng!";

        String content = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
                + "    <title>Xác thực đơn hàng</title>"
                + "    <style>"
                + "        .container { margin: 50px 200px; background-color: #F3F3F3; padding: 25px; }"
                + "    </style>"
                + "</head>"
                + "<body style=\" padding: 30px;\">"
                + "    <div>"
                + "        <h2 style=\"font-size: 25px;\">Cảm ơn " + name + " đã đặt hàng tại <a href=\"http://localhost:9999/Order_Food/home\">4FoodHD</a></h2>"
                + "        <p>Đơn hàng của bạn đã được đặt thành công!</p>"
                + "        <h1 style=\"margin-top: 50px; font-size: 28px\">Chi tiết đơn hàng của bạn</h1>"
                + "        <table style=\"width:100%;border-spacing:inherit;border:1px solid #ddd\">"
                + "            <tr style=\"background-color:#ce0707;font-weight:bold\">"
                + "                <td style=\"padding:10px;border-right:1px solid #ddd;color:white\">THÔNG TIN THANH TOÁN</td>"
                + "                <td style=\"padding:10px;color:white\">ĐỊA CHỈ GIAO HÀNG</td>"
                + "            </tr>"
                + "            <tr style=\"color:#ce0707\">"
                + "                <td style=\"padding:10px;border-right:1px solid #ddd\">Tên khách hàng : " + name + "</td>"
                + "                <td style=\"padding:10px\">Địa chỉ : " + address + "</td>"
                + "            </tr>"
                + "            <tr style=\"color:#ce0707\">"
                + "                <td style=\"padding:10px;border-right:1px solid #ddd;\">Số điện thoại : " + phone + "</td>"
                + "            </tr>"
                + "            <tr style=\"color:#ce0707\">"
                + "                <td style=\"padding:10px;border-right:1px solid #ddd;\">Hình thức thanh toán : " + dao.getPayment(orderDetailId).toUpperCase() + "</td>"
                + "            </tr>"
                + "        </table>"
                + "        <table style=\"border-collapse:collapse;width:100%;color:#333; margin-top: 50px\" border=\"1\">"
                + "            <tbody>"
                + "                <tr style=\"background-color:#ce0707;font-weight:bold;color:white\">"
                + "                    <td style=\"padding:10px;width: 30%;\">Sản phẩm</td>"
                + "                    <td style=\"padding:10px;width: 25%;\">Giá Tiền</td>"
                + "                    <td style=\"padding:10px;width: 20%;\">Số lượng</td>"
                + "                    <td style=\"padding:10px;width: 25%;\">Thành tiền</td>"
                + "                </tr>";
        for (Item item : cart.getItems()) {
            content += "<tr>"
                    + "    <td style=\"padding:4px;\">" + item.getProduct().getName() + "</td>"
                    + "    <td style=\"padding:4px;align-content: center;justify-content: center\">" + formatter.format(item.getProduct().getPrice()) + " VNĐ</td>"
                    + "    <td style=\"padding:4px;align-content: center;justify-content: center\">" + item.getQuantity() + "</td>"
                    + "    <td class=\"price\" style=\"padding:4px;align-content: center;justify-content: center\">" + formatter.format(item.getProduct().getPrice() * item.getQuantity()) + " VNĐ</td>"
                    + "</tr>";
        }
        content
                += "<tr>"
                + "    <td colspan=\"3\" style=\"padding:4px;text-align:right\"> Tổng tiền hàng </td>"
                + "    <td class=\"price\">" + formatter.format(subtotal) + " VNĐ</td>"
                + "</tr>"
                + "<tr>"
                + "    <td colspan=\"3\" style=\"padding:4px;text-align:right\">Phí vận chuyển</td>"
                + "    <td class=\"price\">30.000 VNĐ</td>"
                + "</tr>"
                + "<tr>"
                + "    <td colspan=\"3\" style=\"padding:4px;text-align:right\">Giảm giá phí vận chuyển</td>"
                + "    <td class=\"price\">-" + formatter.format(shippingDiscount) + " VNĐ</td>"
                + "</tr>"
                + "<tr>"
                + "    <td colspan=\"3\" style=\"padding:4px;text-align:right\">Giảm giá voucher</td>"
                + "    <td class=\"price\">-" + formatter.format(voucherDiscount) + " VNĐ</td>"
                + "</tr>"
                + "<tr>"
                + "    <td colspan=\"3\" style=\"padding:4px;text-align:right\"> Tổng đơn hàng </td>"
                + "    <td class=\"price\">" + formatter.format(totalAmount) + " VNĐ</td>"
                + "</tr>"
                + "            </tbody>"
                + "        </table>"
                + "    </div>"
                + "</body>"
                + "</html>";
        try {
            EmailHandler.sendEmail(email, subject, content);
        } catch (AddressException ex) {
            Logger.getLogger(Checkout2Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
