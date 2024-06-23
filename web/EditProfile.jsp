<%-- 
    Document   : EditProfile
    Created on : Jun 19, 2024, 10:19:58 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
            body {
                margin-top: 20px;
                background: #f8f8f8;
            }
            #avatarPreview img,
            #imagePreview img {
                max-width: 200px; /* Chiều rộng tối đa của ảnh */
                height: auto;     /* Duy trì tỉ lệ khung hình */
                display: block;   /* Đảm bảo ảnh là một khối độc lập */
                margin: 0 auto;   /* Canh giữa ảnh */
            }
            .custom-file .btn {
                width: 100px; /* Chiều rộng cụ thể của nút "Chọn ảnh" */
                margin: 0 auto; /* Canh giữa nút */
                display: block; /* Đảm bảo nút là một khối độc lập */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row flex-lg-nowrap">
                <div class="col-12 col-lg-auto mb-3" style="width: 200px;">
                    <div class="card p-3">
                        <div class="e-navlist e-navlist--active-bg">
                            <ul class="nav">
                                <li class="nav-item"><a class="nav-link px-3 active" href="home"><i class="fa fa-fw fa-bar-chart mr-1"></i><span>Home</span></a></li>
                                <li class="nav-item"><a class="nav-link px-3" href="profile"><i class="fa fa-fw fa-cog mr-1"></i><span>Profile</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <ul class="nav nav-tabs">
                                        <li class="nav-item"><a href="" class="active nav-link">Settings</a></li>
                                    </ul>
                                    <div class="tab-content pt-3">
                                        <div class="tab-pane active">
                                            <form action="editProfile" method="post" enctype="multipart/form-data">
                                                <div class="form-group">
                                                    <input type="hidden" name="accountId" value="${account.accountId}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="email">Email</label>
                                                    <input type="text" class="form-control" name="email" value="${account.email}" readonly required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="fullName">Họ Và Tên</label>
                                                    <input type="text" class="form-control" name="name" value="${account.name}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="phone">Số điện thoại</label>
                                                    <input type="text" class="form-control" name="phone" id="phone" value="${account.phone}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="address">Địa chỉ</label>
                                                    <input type="text" class="form-control" name="address" value="${account.address}">
                                                </div>

                                                <div class="form-group">
                                                    <label for="gender">Giới tính</label>
                                                    <select class="form-control" name="gender">
                                                        <option value="Nam"${account.gender ? 'Nam' : 'Nữ'}>Nam</option>
                                                        <option value="Nữ"${account.gender ? 'Nam' : 'Nữ'}>Nữ</option>
                                                    </select>
                                                </div>
                                                <input id="OldImage" name="OldImage" type="hidden" value="${account.imageAvatar}" class="form-control validate" />
                                                <div id="avatarPreview">
                                                    <h3>Xem trước ảnh đại diện mới</h3>
                                                    <img id="currentImage" src="img/${account.imageAvatar}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto">
                                                </div>
                                                <div class="custom-file mt-3 mb-3">
                                                    <input id="fileInput" name="image" type="file" style="display:none;" onchange="previewImage(event);" />
                                                    <input type="button" class="btn btn-primary btn-block mx-auto text-uppercase" value="Chọn ảnh" onclick="document.getElementById('fileInput').click();" />
                                                    <div id="imagePreview" class="mt-3"></div>
                                                </div>

                                                <div class="form-group">
                                                    <input type="submit" class="btn btn-primary" value="Lưu">
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var form = document.querySelector('form[action="editProfile"]');

                form.addEventListener("submit", function (event) {
                    var name = form.elements["name"].value.trim();
                    var phone = form.elements["phone"].value.trim();
                    var address = form.elements["address"].value.trim();
                    var gender = form.elements["gender"].value;

                    // Validate Họ và Tên
                    if (!name) {
                        alert("Vui lòng nhập Họ và Tên.");
                        event.preventDefault();
                        return false;
                    }

                    // Validate Số điện thoại
                    var phoneRegex = /^(0[2-9][0-9]{8,9})$/;
                    if (!phoneRegex.test(phone)) {
                        alert("Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại Việt Nam đúng định dạng.");
                        event.preventDefault();
                        return false;
                    }

                    // Validate Địa chỉ
                    if (!address) {
                        alert("Vui lòng nhập Địa chỉ.");
                        event.preventDefault();
                        return false;
                    }

                    // Validate Giới tính
                    if (gender !== "Nam" && gender !== "Nữ") {
                        alert("Vui lòng chọn Giới tính.");
                        event.preventDefault();
                        return false;
                    }

                    return true; // Form sẽ submit nếu hợp lệ
                });
            });
        </script>

        <script>
            function previewImage(event) {
                var input = event.target;
                var reader = new FileReader();
                reader.onload = function () {
                    var dataURL = reader.result;
                    var output = document.getElementById('currentImage');
                    output.src = dataURL;
                    output.style.display = 'block'; // Hiển thị ảnh mới
                };
                if (input.files && input.files[0]) {
                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </body>
</html>
