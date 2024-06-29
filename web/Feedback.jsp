<%-- 
    Document   : Feedback
    Created on : Jun 19, 2024, 5:08:55 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Feedback</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            body {
                background-color: #f8f9fa; /* màu nền xám nhẹ */
            }
            .card {
                border: none; /* bỏ viền card */
                border-radius: 10px; /* bo góc card */
                box-shadow: 0 0 10px rgba(0,0,0,0.1); /* đổ bóng nhẹ */
            }
            .card-header {
                background-color: #007bff; /* màu header */
                border-radius: 10px 10px 0 0; /* bo góc header */
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="card">
                        <div class="card-header text-white">
                            <h4 class="mb-0">Phản hồi</h4>
                        </div>
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

                            <form action="insertFeedback" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="rateStar">Rate Star:</label>
                                    <input type="text" class="form-control" id="rateStar" name="rateStar">
                                </div>

                                <div class="form-group">
                                    <label for="feedback">Feedback:</label>
                                    <textarea class="form-control" id="feedback" name="feedback" rows="4"></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="accountName">AccountId</label>
                                    <input type="text" class="form-control" id="accountName" name="accountName" value="${account.accountId}" readonly required>
                                </div>

                                <div class="form-group">
                                    <label for="productId">Product ID:</label>
                                    <input type="text" class="form-control" id="productId" name="productId">
                                </div>

                                <div class="form-group">
                                    <label for="date">Date:</label>
                                    <input type="text" class="form-control" id="displayDate" readonly>
                                    <input type="hidden" id="date" name="date">
                                </div>

                                <div class="custom-file mt-3 mb-3">
                                    <input id="imageURL" name="imageURL" type="file" class="custom-file-input" onchange="previewImage(event)" required>
                                    <label class="custom-file-label" for="fileInput">Chọn ảnh</label>
                                </div>

                                <div class="form-group">
                                    <img id="imagePreview" src="#" style="display: none; max-height: 300px;">
                                </div>

                                <a href="OrderHistory.jsp" class="btn btn-secondary mr-2">Quay lại</a>
                                <button type="submit" class="btn btn-primary">Submit Feedback</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <script>
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

                                        // Set the current date in the hidden date input field
                                        window.onload = function () {
                                            var dateInput = document.getElementById('date');
                                            var displayDateInput = document.getElementById('displayDate');
                                            var currentDate = new Date().toISOString().split('T')[0];
                                            dateInput.value = currentDate;
                                            displayDateInput.value = currentDate;
                                        }
        </script>
    </body>
</html>
