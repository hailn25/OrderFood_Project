package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Cart;

/**
 *
 * @author
 */
public class LoginServlet extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("err", "Vui lòng nhập cả email và mật khẩu");
            request.setAttribute("email", email);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }

        AccountDAO acc = new AccountDAO();
        Account a = acc.checkLogin(email, password);

        if (a == null) {
            request.setAttribute("err", "Bạn đã nhập sai password hoặc email");
            request.setAttribute("email", email);
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else if (!a.isStatus()) {
            request.setAttribute("err", "Tài khoản của bạn đã bị cấm");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            acc.UpdateLastDateLogin(email);
            HttpSession session = request.getSession();
            session.setAttribute("account", a);

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            switch (a.getRoleId()) {
                case 1:
                    request.getRequestDispatcher("revenueAdmin").forward(request, response);
                    break;
                case 2:
                    request.getRequestDispatcher("home").forward(request, response);
                    break;
                case 3:
                    request.getRequestDispatcher("Shipper.jsp").forward(request, response);
                    break;
                case 4:
                    request.getRequestDispatcher("revenueRestaurant").forward(request, response);
                    break;
                case 5:
                    request.getRequestDispatcher("ManagerStaff.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("home.jsp");
                    break;
            }
        }
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
