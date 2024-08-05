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
        <title>4FOODHD</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            #imagePreview {
                display: none;
                height: 200px;
                width: 150px;
            }
        </style>
    </head>
    <body>
        <!-- Header Section -->
        <header class="text-white py-3" style="background-color: #81C408">
            <div class="container">
                <h1 class="mb-0" style="color: white; font-weight: bold">Tố Cáo Của Hàng</h1>
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

                            <form id="reportForm" action="insertReport" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">
                                <div class="form-group">
                                    <label>Nhà Hàng</label>
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
                                    <label for="createDate">Ngày</label>
                                    <input type="text" class="form-control" id="displayDate" readonly>
                                    <input type="hidden" id="createDate" name="createDate">
                                </div>

                                <div class="custom-file mt-3 mb-3">
                                    <input id="imageURL" name="imageURL" type="file" class="custom-file-input" onchange="previewImage(event)" required>
                                    <label class="custom-file-label" for="imageURL">Chọn ảnh</label>
                                </div>
                                <img id="imagePreview"/>

                                <div class="form-group text-center">
                                    <a href="restaurant?restaurantId=${restaurantId}&page=${1}" class="btn btn-secondary mr-2">Quay lại</a>
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
                                        function validateImage(fileInput) {
                                            var filePath = fileInput.value;
                                            var allowedExtensions = /(\.jpg|\.jpeg|\.png)$/i;
                                            if (!allowedExtensions.exec(filePath)) {
                                                alert('Chỉ được upload các tệp có định dạng .jpg, .jpeg hoặc .png.');
                                                fileInput.value = '';
                                                document.getElementById('imagePreview').style.display = 'none';
                                                return false;
                                            }
                                            return true;
                                        }

                                        function previewImage(event) {
                                            var fileInput = event.target;
                                            if (validateImage(fileInput)) {
                                                var reader = new FileReader();
                                                reader.onload = function () {
                                                    var output = document.getElementById('imagePreview');
                                                    output.src = reader.result;
                                                    output.style.display = 'block';
                                                };
                                                reader.readAsDataURL(fileInput.files[0]);
                                            }
                                        }



                                        window.onload = function () {
                                            var dateInput = document.getElementById('createDate');
                                            var displayDateInput = document.getElementById('displayDate');
                                            var currentDate = new Date();

                                            // Định dạng ngày thành dd/MM/yyyy
                                            var day = String(currentDate.getDate()).padStart(2, '0');
                                            var month = String(currentDate.getMonth() + 1).padStart(2, '0');
                                            var year = currentDate.getFullYear();

                                            var formattedDate = day + '-' + month + '-' + year;
                                            var isoDate = currentDate.toISOString().split('T')[0];

                                            dateInput.value = isoDate; // Định dạng ISO để gửi lên server
                                            displayDateInput.value = formattedDate; // Hiển thị định dạng dd/MM/yyyy
                                        }
        </script>
        <script>
            function validateForm() {
                var description = document.getElementById("description").value.trim();
                var allNumbers = /^\d+$/;
                var allSpecialChars = /^[!@#$%^&*(),.?":{}|<>]+$/;
                var hasAlphanumeric = /[a-zA-Z0-9]/;

                if (!description) {
                    alert("Mô tả không được để trống hoặc chứa toàn dấu cách.");
                    return false;
                }

                if (allNumbers.test(description)) {
                    alert("Mô tả không được chứa toàn số.");
                    return false;
                }

                if (allSpecialChars.test(description) || !hasAlphanumeric.test(description)) {
                    alert("Mô tả không được chứa toàn ký hiệu đặc biệt.");
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>



