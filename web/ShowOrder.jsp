<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Order Details - Dashboard Admin Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min_1.css">
        <link rel="stylesheet" href="css/templatemo-style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">


        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .header {
                background-color: #81C408;
                padding: 20px;
                color: white;
                display: flex;
                justify-content: space-between;
                align-items: center;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .header h1 {
                margin: 0;
                font-size: 24px;
            }

            .header nav {
                display: flex;
                gap: 20px;
            }

            .header a {
                color: white;
                text-decoration: none;
                font-size: 18px;
                transition: color 0.3s ease;
            }

            .header a:hover {
                color: #007bff;
            }

            .button-container {
                display: flex;
                justify-content: center;
                margin: 20px 0;
            }

            .button-link {
                display: inline-block;
                padding: 10px 20px;
                margin: 0 10px;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                background-color: gray;
                transition: background-color 0.3s ease;
            }

            .button-link:hover {
                background-color: darkgray;
            }

            .button-link.active {
                background-color: #3dd5f3;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
                box-shadow: 0 2px 3px rgba(0,0,0,0.1);
            }

            th, td {
                padding: 12px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #343a40;
                color: white;
            }

            tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }

            tbody tr:hover {
                background-color: #ddd;
            }

            h1 {
                text-align: center;
                margin: 20px 0;
                color: #343a40;
            }
        </style>
    </head>
    <body>
        <div class="header">
            <div class="button-container">
                <a href="orderHistory?orderStatusId=1&accountId=${sessionScope.account.accountId}" 
                   class="button-link ${param.orderStatusId == '1' ? 'active' : ''}">
                    <i class="fa fa-hourglass-start"></i> Chờ xác nhận
                </a>
                <a href="orderHistory?orderStatusId=2&accountId=${sessionScope.account.accountId}" 
                   class="button-link ${param.orderStatusId == '2' ? 'active' : ''}">
                    <i class="fa fa-truck"></i> Đang giao hàng
                </a>
                <a href="orderHistory?orderStatusId=3&accountId=${sessionScope.account.accountId}" 
                   class="button-link ${param.orderStatusId == '3' ? 'active' : ''}">
                    <i class="fa fa-check-circle"></i> Đã giao
                </a>
                <a href="orderHistory?orderStatusId=4,8&accountId=${sessionScope.account.accountId}" 
                   class="button-link ${param.orderStatusId == '4,8' ? 'active' : ''}">
                    <i class="fa fa-times-circle"></i> Đã huỷ
                </a>
            </div>

            <nav>
                <a href="home">Home</a>
                <a href="profile">Profile</a>
                <a href="logout">Logout</a>
            </nav>
        </div>
        <table>
            <thead>
                <tr>
                    <th>Họ và tên</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Ngày đặt</th>
                    <th>Trạng thái</th>
                    <th></th>
                    <th></th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${listOrders}">
                    <tr>               
                        <td>${order.accountName}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td> 
                        <td><fmt:formatDate value="${order.createDate}" pattern="dd/MM/yyyy" /></td>
                        <td>${order.status}</td>
                        <td><a href="listOrderProduct?action=view&oid=${order.orderId}">Xem</a></td>
                        <td>
                            <c:choose>
                                <c:when test="${order.orderStatusId == 1}">
                                    <form action="orderHistory" method="post" onsubmit="return confirmCancel(this)">
                                        <input type="hidden" name="cancelOrder" value="true">
                                        <input type="hidden" name="orderId" value="${order.orderId}">
                                        <button type="submit" class="cancel-button">Huỷ</button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
                                        function confirmCancel(form) {
                                            var reason = prompt("Vui lòng ghi lý do huỷ đơn hàng:");
                                            if (reason != null && reason.trim() != "") {
                                                var reasonInput = document.createElement("input");
                                                reasonInput.type = "hidden";
                                                reasonInput.name = "cancelReason";
                                                reasonInput.value = reason;
                                                form.appendChild(reasonInput);
                                                return true;
                                            }
                                            return false;
                                        }

                                        function formatVND(value) {
                                            return new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(value * 1000);
                                        }

                                        function formatAll() {
                                            document.querySelectorAll('.totalMoney').forEach(function (element) {
                                                let value = parseFloat(element.textContent);
                                                element.textContent = formatVND(value);
                                            });
                                            document.querySelectorAll('.price').forEach(function (element) {
                                                let value = parseFloat(element.textContent);
                                                element.textContent = formatVND(value);
                                            });
                                        }

                                        window.onload = formatAll;
        </script>
    </body>
</html>

