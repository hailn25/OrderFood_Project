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
                            <form action="process_report.php" method="POST">
                                <div class="form-group">
                                    <label for="description">Mô tả</label>
                                    <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
                                </div>

                                <div class="form-group">
                                    <label for="createDate">Ngày Tạo</label>
                                    <input type="date" class="form-control" id="createDate" name="createDate" required>
                                </div>

                                <div class="form-group">
                                    <label for="accountName">Account Name</label>
                                    <input type="text" class="form-control" id="accountName" name="accountName" required>
                                </div>

                                <div class="form-group">
                                    <label for="restaurantName">Restaurant Name</label>
                                    <input type="text" class="form-control" id="restaurantName" name="restaurantName" required>
                                </div>

                                <div class="form-group">
                                    <label for="imageFile">Chọn ảnh</label>
                                    <input type="file" class="form-control-file" id="imageFile" name="imageFile" accept="image/*" onchange="previewImage(event)">
                                    <img id="preview" src="#" alt="Ảnh xem trước" style="max-width: 200px; max-height: 200px; display: none; margin-top: 5px">
                                </div>

                                <div class="form-group text-center">
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
                                        function previewImage(event) {
                                            var input = event.target;
                                            var reader = new FileReader();

                                            reader.onload = function () {
                                                var imgElement = document.getElementById('preview');
                                                imgElement.src = reader.result;
                                                imgElement.style.display = 'block'; // Hiển thị ảnh khi đã load
                                            };

                                            reader.readAsDataURL(input.files[0]); // Đọc file như là một URL dữ liệu
                                        }

                                       
        </script>
    </body>
</html>

