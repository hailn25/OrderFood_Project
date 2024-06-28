/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import utils.Validation;

/**
 *
 * @author quoch
 */
@WebServlet(name = "AddBlogControl", urlPatterns = {"/addBlog"})
@MultipartConfig
public class AddBlogControl extends HttpServlet {

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
        BlogDAO blogDAO = new BlogDAO();
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String summary = request.getParameter("summary");
        Date createDate = new Date();
        Date today = new Date();

        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();

        String img = null;
        String error = "Thông tin không hợp lệ";

        int lengthTitle = Validation.removeAllBlank(title).length();
        int lengthContent = Validation.removeAllBlank(content).length();
        int lengthSummary = Validation.removeAllBlank(summary).length();
        if (lengthTitle < 5 || lengthTitle > 60 || lengthContent < 100 || lengthContent > 500 || 
                                lengthSummary < 30 || lengthSummary > 50 || request.getParameter("status") == null 
                                                        || request.getParameter("datesubmit") == null) {
            request.setAttribute("error", error);
            request.getRequestDispatcher("AddBlog.jsp").forward(request, response);
        } else {
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String dateSubmitStr = request.getParameter("datesubmit");
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = getServletContext().getRealPath("/") + "img" + File.separator + fileName;
                filePart.write(uploadPath);
                img = fileName;
                try {
                    SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateSubmit = spf.parse(dateSubmitStr);
                    if (dateSubmit.after(today)) {
                        blogDAO.addBlog(title, content, img, summary, 2, status, createDate, dateSubmit);
                        response.sendRedirect("managerBlog");
                    } else {
                        error = "Ngày đăng phải sau ngày hiện tại";
                        request.setAttribute("error", error);
                        request.getRequestDispatcher("AddBlog.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                }
            } else {
                error = "Chưa chọn ảnh";
                request.setAttribute("error", error);
                request.getRequestDispatcher("AddBlog.jsp").forward(request, response);
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
