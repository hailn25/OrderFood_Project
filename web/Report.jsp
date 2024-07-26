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
        <!-- Header Section -->
        <header class="text-white py-3" style="background-color: #81C408">
            <div class="container">
                <h1 class="mb-0" style="color: red">Tố Cáo Của Hàng</h1>
                <p class="lead mb-0">Điền vào mẫu dưới đây để gửi tố cáo</p>
            </div>
        </header>

        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-8">
                    <div class="card">
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

                            <form id="reportForm" action="insertReport" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label>Restaurant Name</label>
                                    <input type="text" class="form-control" value="${restaurantName}" readonly>
                                </div>
                                
                                <div class="form-group">
                                    <label for="description">Mô tả</label>
                                    <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="accountId" style="display: none">AccountId</label>
                                    <input type="hidden" class="form-control" id="accountId" name="accountId" value="${sessionScope.account.accountId}" readonly required>
                                </div>

                                <div class="form-group">
                                    <label for="restaurantId" style="display: none">RestaurantId</label>
                                    <input type="hidden" class="form-control" id="restaurantId" name="restaurantId" value="${restaurantId}" readonly>
                                </div>

                                <div class="form-group">
                                    <label for="status" style="display: none">Trạng thái</label>
                                    <input type="hidden" class="form-control" id="status" value="1" name="status" readonly>
                                </div>

                                <div class="form-group">
                                    <label for="createDate">Date:</label>
                                    <input type="text" class="form-control" id="displayDate" readonly>
                                    <input type="hidden" id="createDate" name="createDate">
                                </div>

                                <div class="custom-file mt-3 mb-3">
                                    <input id="imageURL" name="imageURL" type="file" class="custom-file-input" onchange="previewImage(event)" required>
                                    <label class="custom-file-label" for="fileInput">Chọn ảnh</label>
                                </div>

                                <div class="form-group">
                                    <img id="imagePreview" src="#" style="display: none; max-height: 300px;">
                                </div>

                                <div class="form-group text-center">
                                    <a onclick="window.history.back()" class="btn btn-secondary mr-2">Quay lại</a>
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
                                                errorMessage += "ImageURL không được để trống.\n";
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

                                        function previewImage(event) {
                                            var input = event.target;
                                            var reader = new FileReader();
                                            reader.onload = function () {
                                                var dataURL = reader.result;
                                                var output = document.getElementById('imagePreview');
                                                output.src = dataURL;
                                                output.style.display = 'block'; // Hiển thị ảnh mới
                                            };
                                            if (input.files && input.files[0]) {
                                                reader.readAsDataURL(input.files[0]);
                                            }
                                        }

                                        window.onload = function () {
                                            var dateInput = document.getElementById('createDate');
                                            var displayDateInput = document.getElementById('displayDate');
                                            var currentDate = new Date().toISOString().split('T')[0];
                                            dateInput.value = currentDate;
                                            displayDateInput.value = currentDate;
                                        }
        </script>
    </body>
</html>
