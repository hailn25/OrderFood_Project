<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Insert Voucher Account Details</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                width: 600px;
                max-width: 100%;
            }
            h2 {
                text-align: center;
                color: #333;
            }
            label {
                display: block;
                margin-bottom: 10px;
                color: #555;
            }
            input[type="text"], input[type="number"], textarea, input[type="date"] {
                width: calc(100% - 16px); /* To account for padding */
                padding: 8px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 14px;
            }
            .form-footer {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-top: 20px;
            }
            button[type="submit"], button[type="button"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                width: 48%; /* Adjust width as needed */
            }
            button[type="submit"]:hover, button[type="button"]:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <form action="voucher" method="post">
            <h2>Insert Voucher Account Details</h2>
            <input type="hidden" id="accountId" name="accountId" value="${sessionScope.account.accountId}">
            <div>
                <label for="voucherName">Voucher Name:</label>
                <input type="text" id="voucherName" name="voucherName" required>
            </div>
            <div>
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" required></textarea>
            </div>
            <div>
                <label for="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>
            </div>
            <div>
                <label for="releaseDate">Release Date:</label>
                <input type="date" id="releaseDate" name="releaseDate" required>
            </div>
            <div>
                <label for="finishDate">Finish Date:</label>
                <input type="date" id="finishDate" name="finishDate" required>
            </div>
            <div>
                <label for="status">Status:</label>
                <input type="text" id="status" name="status" required>
            </div>
            <div class="form-footer">
                <button type="button" onclick="history.back()">Quay lại</button>
                <button type="submit">Submit</button>                
            </div>
        </form>
        <script>
            document.getElementById('releaseDate').valueAsDate = new Date();
        </script>
    </body>
</html>
