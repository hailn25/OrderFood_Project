/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.HttpSession;
import javax.mail.internet.AddressException;
import model.Account;
import model.EmailHandler;
import util.EncodePassword;

/**
 *
 * @author hailt
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        String codeEnter = request.getParameter("verify");
        HttpSession session = request.getSession();
        if (codeEnter == null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            String pass = EncodePassword.toSHA1(password);
            String phone = request.getParameter("phonenumber");
            String address = request.getParameter("address");
            AccountDAO acc = new AccountDAO();

            if (acc.checkAccountExist(email)) {
                request.setAttribute("msg", "Tài khoản email đã tồn tại.");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                try {

                    String verify = EmailHandler.generateCodeVerify();
                    String codeVerify = EncodePassword.toSHA1(verify);
                    String subject = "Email Varification";
                    String content = "<!DOCTYPE html>\n"
                            + "<html>\n"
                            + "<head>\n"
                            + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                            + "    <title>Xác thực email</title>\n"
                            + "    <style>\n"
                            + "        .container {\n"
                            + "            margin: 50px 200px;\n"
                            + "            background-color: #F3F3F3;\n"
                            + "            padding: 25px;\n"
                            + "        }\n"
                            + "    </style>\n"
                            + "</head>\n"
                            + "<body style=\"background-color: #b8daff; padding: 20px;\">\n"
                            + "    <div class=\"container\">\n"
                            + "        <h2 style=\"font-size: 30px;\">Xin Chào!!</h2>\n"
                            + "        <p>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi. Mã xác thực của bạn là:</p>\n"
                            + "        <h1 style=\"margin-left: 150px; font-size: 38px; color: red;\">" + verify + "</h1>\n"
                            + "        <p>Vui lòng nhập mã này vào trang xác thực trên website của chúng tôi để hoàn tất quá trình đăng ký.</p>\n"
                            + "        <p style=\"font-size: 15px;\"><a href=\"http://localhost:8080/Order_Foodv1/Verify.jsp\">Quay lại website của chúng tôi</a></p>\n"
                            + "        <p>Nếu bạn không yêu cầu mã này, vui lòng bỏ qua email này hoặc liên hệ với bộ phận hỗ trợ của chúng tôi.</p>\n"
                            + "        <p>Trân trọng,</p>\n"
                            + "        <h2>4FoodHD</h2>\n"
                            + "    </div>\n"
                            + "</body>\n"
                            + "</html>";
                    EmailHandler.sendEmail(email, subject, content);
                    Cookie c = new Cookie("codeVerify", codeVerify);
                    c.setMaxAge(60 * 5);
                    response.addCookie(c);
                    session.setAttribute("authenticationfor", "register");
                    Account account = new Account(email, pass, fullName, (gender.equals("Male") ? true : false), phone, address);
                    session.setAttribute("email", email);
                    session.setAttribute("accregister", account);
                    request.getRequestDispatcher("Verify.jsp").forward(request, response);
                } catch (AddressException ex) {
                    Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            String codeVerify = EncodePassword.toSHA1(codeEnter);
            Cookie[] arrCookie = request.getCookies();
            String code = "";
            if (arrCookie != null) {
                for (Cookie cookie : arrCookie) {
                    if (cookie.getName().equals("codeVerify")) {
                        code += cookie.getValue();
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }

                }
            }
          
            if (!codeVerify.equals(code)) {
                request.setAttribute("err", "Code nhập không đúng");
                request.getRequestDispatcher("Verify.jsp").forward(request, response);
            } else {
                AccountDAO adao = new AccountDAO();
                Account account = (Account) session.getAttribute("accregister");
                adao.registerAccount(account.getEmail(), account.getPassword(), account.getName(), account.isGender(), account.getPhone(), account.getAddress());
                request.getRequestDispatcher("Login.jsp").forward(request, response);

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
