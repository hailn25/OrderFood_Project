/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import model.Account;
import model.EmailDTO;
import model.EmailHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.EncodePassword;

/**
 *
 * @author hailt
 */
@WebServlet(name="LoginWithGoogle", urlPatterns={"/logingoogle"})
public class LoginWithGoogle extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, AddressException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            EmailDTO email = getEmailInfo(accessToken);
            String toEmail = email.getEmail();
            AccountDAO dao = new AccountDAO();
            HttpSession session = request.getSession();
            Account a = dao.getAccountByEmail(toEmail);
            if(a != null && a.isStatus() == false){
                request.setAttribute("err", "Tài khoản của bạn đã bị chặn");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }else if (a!= null && a.getLoginWith() == 1){
                session.setAttribute("account", a);
                dao.UpdateLastDateLogin(toEmail);
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                String verify = EmailHandler.generateCodeVerify();
                String codeVerify = EncodePassword.toSHA1(verify);
                String subject = "Email Varification!";
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
                EmailHandler.sendEmail(toEmail, subject, content);
                Cookie c = new Cookie("codeVerify", codeVerify);
                c.setMaxAge(5 * 60);
                response.addCookie(c);
                session.setAttribute("email", email);
                session.setAttribute("authenticationfor", "logingoogle");
                request.getRequestDispatcher("Verify.jsp").forward(request, response);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post("https://accounts.google.com/o/oauth2/token")
                .bodyForm(Form.form().add("client_id", "1020017167169-f4va8u548gpbnn9ntap6cggkc4mrmnv4.apps.googleusercontent.com")
                        .add("client_secret", "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")
                        .add("redirect_uri", "http://localhost:8080/Order_Food/logingoogle")
                        .add("code", code)
                        .add("grant_type", "authorization_code").build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        System.out.println(" Json  " + jobj);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }
     public static EmailDTO getEmailInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        EmailDTO email = new Gson().fromJson(response, EmailDTO.class);

        return email;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (AddressException ex) {
            Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String codeEnter = request.getParameter("verify");
        String codeVerify = EncodePassword.toSHA1(codeEnter);
        Cookie[] arrCookie = request.getCookies();
        String code = "";
        if(arrCookie != null){
            for (Cookie cookie : arrCookie) {
                if(cookie.getName().equals("codeVerify")){
                    code += cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        HttpSession session = request.getSession();
       EmailDTO emailDTO = (EmailDTO) session.getAttribute("email");
        String email = emailDTO.getEmail();
        if(!codeVerify.equals(code)){
            request.setAttribute("err", "Code nhập không đúng");
            request.getRequestDispatcher("Verify.jsp").forward(request, response);
        } else {
            try {
                AccountDAO dao = new AccountDAO();
                if(!dao.checkAccountExist(email)){
                    try {
                        dao.insertAccountLoginGoogle(email, 1);
                        Account a = dao.getAccountByEmail(email);
                        session.setAttribute("account", a);
                        request.getRequestDispatcher("home").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                
                Account a = dao.getAccountByEmail(email);
                session.setAttribute("account", a);
                request.getRequestDispatcher("home").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
            }
               
                
            }
        }
    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
