<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Giao Hàng</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: greenyellow;
        }
        h1 {
            text-align: center;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        .section {
            margin-bottom: 20px;
        }
        .button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
        .row {
            display: flex;
        }
        .col-lg-3 {
            flex: 1;
            max-width: 25%;
            padding: 20px;
            border-right: 1px solid #ddd;
        }
        .col-lg-9 {
            flex: 3;
            padding: 20px;
        }
        .card {
            margin-bottom: 20px;
        }
        .card-header {
            padding: 10px;
            background-color: #007bff;
            color: white;
            text-transform: uppercase;
        }
        .shop__sidebar__accordion a {
            display: block;
            padding: 10px;
            text-decoration: none;
            color: #333;
        }
        .shop__sidebar__accordion a:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>

    <h1>Quản Lý Giao Hàng</h1>

    <div class="row">
        <div class="col-lg-3">
            <div class="shop__sidebar">
                <div class="shop__sidebar__accordion">
                    <div class="accordion" id="accordionExample">
                        <div class="card">
                            <div class="card-header">
                                <i class="fa fa-list"></i> Quản lý đơn hàng 
                            </div>
                            <div>
                                <a href="Shipper.jsp?status=pending">Đơn hàng đang chờ xử lý</a>
                                <a href="Shipper.jsp?status=confirmed">Đơn hàng đã xác nhận</a>
                                <a href="Shipper.jsp?status=delivered">Đơn hàng đã giao</a>
                                <a href="Shipper.jsp?status=cancelled">Đơn hàng bị hủy</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-9">
            <div class="section">
                <h2>Thông Tin Đơn Hàng</h2>
              <div class="col-lg-9">
    <div class="section">
        <h2>Chi Tiết Đơn Hàng Đang Xử Lý</h2>
        <c:forEach var="order" items="${orders}">
            <c:if test="${order.status == 'pending'}">
                <div class="order-info">
                    <p>Mã Đơn Hàng: ${order.id}</p>
                    <p>Ngày Đặt Hàng: ${order.orderDate}</p>
                    <p>Thời Gian Giao Dự Kiến: ${order.deliveryDate}</p>
                    <p>Họ và Tên: ${order.customerName}</p>
                    <p>Địa Chỉ: ${order.address}</p>
                    <p>Số Điện Thoại: ${order.phone}</p>
                    <p>Email: ${order.email}</p>

                    <h3>Thông Tin Sản Phẩm</h3>
                    <table>
                        <tr>
                            <th>STT</th>
                            <th>Tên Sản Phẩm</th>
                            <th>Số Lượng</th>
                            <th>Đơn Giá</th>
                            <th>Thành Tiền</th>
                        </tr>
                        <c:forEach var="item" items="${order.items}">
                            <tr>
                                <td>${item.index}</td>
                                <td>${item.productName}</td>
                                <td>${item.quantity}</td>
                                <td>${item.price}</td>
                                <td>${item.total}</td>
                            </tr>
                        </c:forEach>
                    </table>
                    <p><strong>Tổng Cộng: ${order.totalPrice} VND</strong></p>
                </div>
                <hr>
            </c:if>
        </c:forEach>
    </div>
</div>


            <div class="section">
                <h2>Ghi Chú và Phản Hồi</h2>
                <form action="UpdateOrderStatus" method="post">
                    <input type="hidden" name="orderId" value="${order.id}">
                    <textarea name="notes" rows="4" cols="50" placeholder="Nhập ghi chú hoặc phản hồi từ khách hàng..."></textarea>
                    <h3>Tình Trạng Giao Hàng</h3>
                    <select name="status">
                        <option value="confirmed">Xác Nhận Đơn</option>
                        <option value="delivered">Đã Giao Hàng</option>
                        <option value="cancelled">Hủy Đơn</option>
                    </select>
                    <br><br>
                    <button type="submit" class="button">Cập Nhật Trạng Thái</button>
                </form>
            </div>
        </div>
    </div>

</body>
</html>
