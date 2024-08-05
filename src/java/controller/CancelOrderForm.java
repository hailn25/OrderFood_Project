package controller;

import dao.OrderDAO;
import dao.ShipperDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.OrderDetail;

/**
 * Servlet implementation class CancelOrderForm
 */
@WebServlet(name = "CancelOrderForm", urlPatterns = {"/cancelOrderForm"})
public class CancelOrderForm extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderForm() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("oid"));
        request.setAttribute("orderId", orderId);
        request.getRequestDispatcher("FormCancel.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int accountId = account.getAccountId();
        ShipperDAO shipper = new ShipperDAO();
        int shipperId = shipper.getShipperId(accountId);

        int orderId = Integer.parseInt(request.getParameter("oid"));
        String reason = request.getParameter("reason");

        try {
            shipper.insertShipperMessage(orderId, shipperId, reason);
            OrderDAO od = new OrderDAO();
            List<OrderDetail> listOrderDetail = od.getOrderDetailsByOrderId(orderId);
            List<Integer> quantities = od.getAllQuantities(listOrderDetail);
            List<Integer> productIds = od.getAllProductIds(listOrderDetail);
            for (int i = 0; i < listOrderDetail.size(); i++) {
              
                od.updateQuantityCancelOrder(productIds.get(i), quantities.get(i));
                od.updateOrderStatus(listOrderDetail.get(i).getOderId(), 5);
            }

            session.setAttribute("successMessage", "Phản hồi thành công!");
            request.getRequestDispatcher("ViewOrderSuccess.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CancelOrderForm.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi hủy đơn hàng.");
            request.getRequestDispatcher("FormCancel.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#getServletInfo()
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}


