<%-- 
    Document   : Report
    Created on : Jun 19, 2024, 5:09:07 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Tố Cáo</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header bg-danger text-white">Tố cáo</div>
                        <div class="card-body">
                            <%-- Kiểm tra và hiển thị thông báo thành công nếu có --%>
                            <% if (request.getAttribute("successMessage") != null) { %>
                            <div class="alert alert-success" role="alert">
                                <%= request.getAttribute("successMessage") %>
                            </div>
                            <% } %>

                            <%-- Kiểm tra và hiển thị thông báo lỗi nếu có --%>
                            <% if (request.getAttribute("errorMessage") != null) { %>
                            <div class="alert alert-danger" role="alert">
                                <%= request.getAttribute("errorMessage") %>
                            </div>
                            <% } %>

                            <form action="insertReport" method="POST">
                                <div class="form-group">
                                    <label for="description">Mô tả</label>
                                    <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="imageURL">imageURL</label>
                                    <input type="text" class="form-control" id="imageURL" name="imageURL" required>
                                </div>

                                <div class="form-group">
                                    <label for="accountId">AccountId</label>
                                    <input type="text" class="form-control" id="accountId" name="accountId" value="${account.accountId}" readonly required>
                                </div>

                                <div class="form-group">
                                    <label for="restaurantId">restaurantId</label>
                                    <input type="text" class="form-control" id="restaurantId" name="restaurantId" required>
                                </div>

                                <div class="form-group">
                                    <label for="status">Trạng thái</label>
                                    <input type="text" class="form-control" id="status" name="status" required>
                                </div>

                                <div class="form-group">
                                    <label for="createDate">Ngày tạo</label>
                                    <input type="text" class="form-control" id="createDate" name="createDate" required>
                                </div>

                                <div class="form-group text-center">
                                    <a href="OrderHistory.jsp" class="btn btn-secondary mr-2">Quay lại</a>
                                    <button type="submit" class="btn btn-danger">Gửi tố cáo</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            document.getElementById('reportForm').addEventListener('submit', function (event) {
                // Kiểm tra các trường nhập liệu
                var description = document.getElementById('description').value.trim();
                var imageURL = document.getElementById('imageURL').value.trim();
                var restaurantId = document.getElementById('restaurantId').value.trim();
                var status = document.getElementById('status').value.trim();
                var createDate = document.getElementById('createDate').value.trim();

                var errorMessage = "";

                // Kiểm tra từng trường có bị bỏ trống không
                if (description === "") {
                    errorMessage += "Mô tả không được để trống.\n";
                }
                if (imageURL === "") {
                    errorMessage += "imageURL không được để trống.\n";
                }
                if (restaurantId === "") {
                    errorMessage += "Restaurant ID không được để trống.\n";
                }
                if (status === "") {
                    errorMessage += "Trạng thái không được để trống.\n";
                }
                if (createDate === "") {
                    errorMessage += "Ngày tạo không được để trống.\n";
                }

                // Nếu có lỗi, ngăn không submit form và hiển thị thông báo lỗi
                if (errorMessage !== "") {
                    alert(errorMessage);
                    event.preventDefault(); // Ngăn không submit form
                }
            });
        </script>
    </body>
</html>

