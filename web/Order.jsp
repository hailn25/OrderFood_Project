<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
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
                background-color: #007bff;
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
        <div class="button-container">
            <a href="orderHistory?orderStatusId=1&accountId=${sessionScope.account.accountId}" 
               class="button-link <c:if test='${param.orderStatusId == "1"}'>active</c:if>'">
                   Chờ xác nhận
               </a>
               <a href="orderHistory?orderStatusId=2&accountId=${sessionScope.account.accountId}" 
               class="button-link <c:if test='${param.orderStatusId == "2"}'>active</c:if>'">
                   Đang giao hàng
               </a>
               <a href="orderHistory?orderStatusId=3&accountId=${sessionScope.account.accountId}" 
               class="button-link <c:if test='${param.orderStatusId == "3"}'>active</c:if>'">
                   Đã giao
               </a>
               <a href="orderHistory?orderStatusId=4&accountId=${sessionScope.account.accountId}" 
               class="button-link <c:if test='${param.orderStatusId == "4"}'>active</c:if>'">
                   Đã huỷ
               </a>
               <a href="profile" 
               class="button-link <c:if test='${param.orderStatusId == "4"}'>active</c:if>'">
                   Profile
               </a>
            </div>

        <c:choose>
            <c:when test="${param.orderStatusId == '1'}">
                <h1>Pending Orders</h1>
            </c:when>
            <c:when test="${param.orderStatusId == '2'}">
                <h1>Orders in Delivery</h1>
            </c:when>
            <c:when test="${param.orderStatusId == '3'}">
                <h1>Delivered Orders</h1>
            </c:when>
            <c:when test="${param.orderStatusId == '4'}">
                <h1>Cancelled Orders</h1>
            </c:when>
            <c:otherwise>
                <h1>Order Details</h1>
            </c:otherwise>
        </c:choose>

        <table>
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Restaurant</th>
                    <th>Account Name</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Note</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Money</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${listOrders}">
                    <tr>
                        <td>${order.productName}</td>
                        <td><img src="img/${order.imageURL}" alt="${order.productName}" style="max-width: 100px;"/></td>
                        <td>${order.restaurant}</td>
                        <td>${order.accountName}</td>
                        <td>${order.phone}</td>
                        <td>${order.address}</td>
                        <td>${order.note}</td>
                        <td>${order.price}</td>
                        <td>${order.quantity}</td>
                        <td>${order.totalMoney}</td>
                        <td>${order.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
