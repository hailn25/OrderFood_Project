<%-- 
    Document   : OrderTracking
    Created on : Jun 19, 2024, 10:52:51 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Tracking</title>
        <style>
            body {
                font-family: Arial, sans-serif;
            }
            .container {
                width: 50%;
                margin: 0 auto;
                border: 1px solid #ccc;
                padding: 20px;
                box-shadow: 0px 0px 10px 0px #000;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input, .form-group textarea, .form-group select {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
                background-color: #f9f9f9;
            }
            .form-group textarea {
                resize: vertical;
            }
            .form-group input[readonly], .form-group textarea[readonly], .form-group select[disabled] {
                background-color: #e9e9e9;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Order Tracking</h2>
            <div class="form-group">
                <label for="fullName">Họ và tên</label>
                <input type="text" id="fullName" name="fullName" value="${orderAccount.name}" readonly>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Số điện thoại</label>
                <input type="tel" id="phoneNumber" name="phoneNumber" value="0123456789" readonly>
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ</label>
                <textarea id="address" name="address" rows="3" readonly>123 Đường ABC, Phường XYZ, Quận 1, TP.HCM</textarea>
            </div>
            <div class="form-group">
                <label for="notes">Ghi chú</label>
                <textarea id="notes" name="notes" rows="3" readonly>Giao hàng trong giờ hành chính</textarea>
            </div>
            <div class="form-group">
                <label for="orderDate">Ngày đặt</label>
                <input type="date" id="orderDate" name="orderDate" value="2023-06-20" readonly>
            </div>
            <div class="form-group">
                <label for="totalAmount">Tổng tiền</label>
                <input type="number" id="totalAmount" name="totalAmount" value="1000000" readonly>
            </div>
            <div class="form-group">
                <label for="orderStatus">Trạng thái đơn</label>
                <select id="orderStatus" name="orderStatus" disabled>
                    <option value="waitingForShipperConfirmation">Đang chờ xác nhận của shipper</option>
                    <option value="delivering">Đang giao hàng</option>
                    <option value="delivered">Giao hàng thành công</option>
                    <option value="cancelled">Đơn hàng bị huỷ</option>
                    <option value="shipperDeclined">Shipper không nhận đơn</option>
                    <option value="waitingForRestaurantConfirmation">Đang chờ xác nhận của nhà hàng</option>
                </select>
            </div>
        </div>
    </body>
</html>
