package controller;

import dao.CategoryDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import utils.Validation;

@WebServlet(name = "EditCategoryControl", urlPatterns = {"/editCategory"})
public class EditCategoryControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String name = request.getParameter("name");
            String id = request.getParameter("id");
            int lengthName = Validation.removeAllBlank(name).length();

            // Regular expression for invalid characters
            String invalidCharsRegex = "[!@#$%^&?_\\-+{}|\\\\:<>?/*,.]";
            
            // Check if name contains invalid characters
            boolean nameHasInvalidChars = name.matches(".*" + invalidCharsRegex + ".*");
            
            // Check if name is purely numeric
            boolean nameIsNumeric = name.matches("\\d+");

            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            int roleId = a.getRoleId();

            if (roleId == 1 || roleId == 5) {
                if (lengthName > 0 && !nameHasInvalidChars && !nameIsNumeric) {
                    name = Validation.removeUnnecessaryBlank(name);
                    CategoryDAO dao1 = new CategoryDAO();
                    dao1.editCategory(name, id);
                    response.sendRedirect("managerCategory");
                } else {
                    String errorMessage = "Nhập không hợp lệ!";
                    if (nameHasInvalidChars) {
                        errorMessage = "Tên danh mục chứa kí tự không hợp lệ!";
                    } else if (nameIsNumeric) {
                        errorMessage = "Tên danh mục không được chỉ chứa số!";
                    }
                    request.setAttribute("error", errorMessage);
                    request.getRequestDispatcher("loadCategory?cid=" + id).forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tài khoản đang dùng không hợp lệ");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditCategoryControl.class.getName()).log(Level.SEVERE, null, ex);
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
