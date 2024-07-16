<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>List of Vouchers</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
    <link rel="stylesheet" href="css/fontawesome.min.css">
    <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.min_1.css">
    <link rel="stylesheet" href="css/templatemo-style.css">
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
    <div class="header">
        <nav>
            <a href="home">Home</a>
            <a href="profile">Profile</a>
            <a href="logout">Logout</a>
        </nav>
    </div>
    <h1>List of Vouchers</h1>
    <table>
        <thead>
            <tr>
                <th>Account ID</th>
                <th>Voucher Name</th>
                <th>Quantity</th>
                <th>Release Date</th>
                <th>Finish Date</th>
                <th>Discount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="voucher" items="${listVoucher}">
                <tr>
                    <td>${voucher.accountId}</td>                 
                    <td>${voucher.voucherName}</td>
                    <td>${voucher.quantity}</td>
                    <td>${voucher.releaseDate}</td>
                    <td>${voucher.finishDate}</td>
                    <td>${voucher.discount}</td>           
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
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
