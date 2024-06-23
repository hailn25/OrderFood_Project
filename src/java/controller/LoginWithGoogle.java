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
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;


/**
 *
 * @author hailt
 */
@WebServlet(name = "LoginWithGoogle", urlPatterns = {"/logingoogle"})
public class LoginWithGoogle extends HttpServlet {

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
            if (a != null && a.isStatus() == false) {
                request.setAttribute("err", "Tài khoản của bạn đã bị chặn");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else if (a != null && a.getLoginWith() == 1) {
                session.setAttribute("account", a);
                dao.UpdateLastDateLogin(toEmail);
                request.getRequestDispatcher("home").forward(request, response);
            } else {
                if (!dao.checkAccountExist(toEmail)) {
                try {
                    dao.insertAccountLoginGoogle(toEmail, 1);
                    Account a1 = dao.getAccountByEmail(toEmail);
                    session.setAttribute("account", a1);
                    request.getRequestDispatcher("home").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                session.setAttribute("account", a);
                request.getRequestDispatcher("home").forward(request, response);
            }
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
     *
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
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);
        } catch (AddressException ex) {
            Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
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
