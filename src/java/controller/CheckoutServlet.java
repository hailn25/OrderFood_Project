/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import model.Account;
import model.Cart;
import model.EmailHandler;
import model.Item;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        String currentURL = request.getRequestURI();
        session.setAttribute("redirectURL", currentURL);
        response.sendRedirect("Checkout.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        String payment = request.getParameter("payment");
        String total = request.getParameter("cost");
         request.setAttribute("note", note);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }

        Account account = null;
        Object a = session.getAttribute("account");
        if (a != null) {
            account = (Account) a;
            int id = account.getAccountId();
            OrderDAO dao = new OrderDAO();
            dao.insertNewOrder(1, id, Double.parseDouble(total), account.getName(), account.getEmail(), account.getPhone(), account.getAddress(), note);
            int oid = dao.getOrderID();
            int orderDetailId = dao.getOrderDetailId();
            if (payment.equals("cod")) {
                for (Item item : cart.getItems()) {
                    dao.insertNewOrderDetail(oid, item.getProduct().getProductId(), item.getQuantity(), item.getPrice() * item.getQuantity(), payment, "Thanh toán khi nhận hàng ");
                    dao.updateQuantity(item.getProduct().getProductId(), item.getQuantity());
                }
            }
            for (Item item : cart.getItems()) {
                dao.insertNewOrderDetail(orderDetailId, item.getProduct().getProductId(), item.getQuantity(), item.getPrice() * item.getQuantity(), payment, "Thanh toán thành công ");
                dao.updateQuantity(item.getProduct().getProductId(), item.getQuantity());
            }
            if (payment.equals("vnpay")) {
                session.setAttribute("fullname", name);
                session.setAttribute("address", address);
                session.setAttribute("phone", phone);
                session.setAttribute("email", email);
                session.setAttribute("note", note);
                session.setAttribute("amount", (long) Double.parseDouble(total));
                response.sendRedirect("paymentvnpay");
                return;
            } else {
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

                String subject = "4FooodHD!";
                String content = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<head>\n"
                        + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "    <title>Xác thực đơn hàng</title>\n"
                        + "    <style>\n"
                        + "        .container {\n"
                        + "            margin: 50px 200px;\n"
                        + "            background-color: #F3F3F3;\n"
                        + "            padding: 25px;\n"
                        + "        }\n"
                        + "    </style>\n"
                        + "</head>\n"
                        + "<body style=\" padding: 30px;\">\n"
                        + "    <div>\n"
                        + "        <h2 style=\"font-size: 25px;\">Cảm ơn " + account.getName() + " đã đặt hàng tại  <a href=\"http://localhost:9999/Order_Food/home\">4FoodHD</a></h2>\n"
                        + "        <p>Đơn hàng của bạn đã được đặt thành công!</p>\n"
                        + "        <h1 style=\"margin-top: 50px; font-size: 28px\">" + "Chi tiết đơn hàng của bạn." + "</h1>\n"
                        + "<table style=\"width:100%;border-spacing:inherit;border:1px solid #ddd\">\n"
                        + "            <tr style=\"background-color:#ce0707;font-weight:bold\">\n"
                        + "                <td style=\"padding:10px;border-right:1px solid #ddd;color:white\">\n"
                        + "                    THÔNG TIN THANH TOÁN\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;color:white\">\n"
                        + "                    ĐỊA CHỈ GIAO HÀNG\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "            <tr style=\"color:#ce0707\">\n"
                        + "                <td style=\"padding:10px;border-right:1px solid #ddd\">\n"
                        + "                    Tên khách hàng :\n"
                        + "                    " + account.getName() + "\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px\">\n"
                        + "                   Địa chỉ   :\n"
                        + "                 " + account.getAddress() + "\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "            <tr style=\"color:#ce0707\">\n"
                        + "                <td style=\"padding:10px;border-right:1px solid #ddd;\">\n"
                        + "                   Số điện thoại  :\n"
                        + "                 " + account.getPhone() + "\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;color:white\">\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "            <tr style=\"color:#ce0707\">\n"
                        + "                <td style=\"padding:10px;border-right:1px solid #ddd;\">\n"
                        + "                  Hình thức thanh toán :\n"
                        + "                 " + dao.getPayment(orderDetailId) + "\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;color:white\">\n"
                        + "                </td>\n"
                        + "            </tr>\n"
                        + "        </table>"
                        + "<table style=\"border-collapse:collapse;width:100%;color:#333; margin-top: 50px\" border=\"1\">\n"
                        + "            <tbody>\n"
                        + "            <tr style=\"background-color:#ce0707;font-weight:bold;color:white\">\n"
                        + "                <td style=\"padding:10px;width: 30%;\">\n"
                        + "                    Sản phẩm\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;width: 25%;\">\n"
                        + "                    Giá Tiền\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;width: 20%;\">\n"
                        + "                    Số lượng\n"
                        + "                </td>\n"
                        + "                <td style=\"padding:10px;width: 25%;\">\n"
                        + "                    Thành tiền\n"
                        + "                </td>\n"
                        + "            </tr>";
                for (Item item : cart.getItems()) {
                    content += "<tr style=\"\">\n"
                            + "                <td style=\"padding:4px;\">\n"
                            + item.getProduct().getName() + "\n"
                            + "                </td>\n"
                            + "                <td style=\"padding:4px;align-content: center;justify-content: center\">\n"
                            + formatter.format(item.getProduct().getPrice()) + "\n"
                            + "                </td>\n"
                            + "                <td style=\"padding:4px;align-content: center;justify-content: center\">\n"
                            + item.getQuantity() + "\n"
                            + "                </td>\n"
                            + "                <td class=\"price\" style=\"padding:4px;align-content: center;justify-content: center\">\n"
                            + formatter.format(item.getProduct().getPrice() * item.getQuantity()) + "\n"
                            + "                </td>\n"
                            + "            </tr>";
                }

                content += "<tr>\n"
                        + "                <td colspan=\"3\" style=\"padding:4px;text-align:right\"> Tổng thanh toán </td>\n"
                        + "                <td class=\"price\">" + formatter.format(Double.parseDouble(total)) + "</td>\n"
                        + "            </tr>\n"
                        + "            </tbody>\n"
                        + "        </table>"
                        + "        <p>Trân trọng,</p>\n"
                        + "        <h2>4FoodHD</h2>\n"
                        + "    </div>\n"
                        + "<script>\n"
                        + "// Định dạng giá tiền\n"
                        + "function formatPrice(price) {\n"
                        + "  const formatter = new Intl.NumberFormat('vi-VN', {\n"
                        + "    style: 'currency',\n"
                        + "    currency: 'VND'\n"
                        + "  });\n"
                        + "  return formatter.format(price);\n"
                        + "}\n"
                        + "// Lấy tất cả các phần tử có class là 'price'\n"
                        + "const priceElements = document.getElementsByClassName('price');\n"
                        + "// Định dạng lại giá tiền cho từng phần tử\n"
                        + "for (let i = 0; i < priceElements.length; i++) {\n"
                        + "  const priceElement = priceElements[i];\n"
                        + "  const price = Number(priceElement.textContent);\n"
                        + "  priceElement.textContent = formatPrice(price);\n"
                        + "}\n"
                        + "</script>"
                        + "</body>\n"
                        + "</html>";
                try {
                    EmailHandler.sendEmail(account.getEmail(), subject, content);
                } catch (AddressException ex) {
                    Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.removeAttribute("cart");
                session.setAttribute("size", 0);
                request.getRequestDispatcher("home").forward(request, response);
            }
        } else {
            response.sendRedirect("Login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}