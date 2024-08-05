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
        <title>4FOODHD</title>
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
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .custom-header {
                background-color: #81C408;
                color: #fff;
                padding: 10px;
                width: 100%;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .navbar-brand {
                font-size: 1.5rem;
                margin: 0;
            }

            .avatar {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 190px;
                height: 190px;
                overflow: hidden;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin: 0 auto 20px;
            }

            .avatar-img {
                max-width: 100%;
                max-height: 100%;
            }

            .nav {
                display: flex;
                flex-direction: column;
                gap: 10px;
                list-style-type: none;
                padding: 0;
                margin: 0;
            }

            .nav-item {
                width: 100%;
            }

            .nav-link {
                display: block;
                padding: 10px;
                border-radius: 5px;
                background-color: #f8f9fa;
                color: #333;
                text-decoration: none;
                transition: background-color 0.3s;
            }

            .nav-link:hover {
                background-color: #e2e6ea;
            }

            .form-group {
                margin-bottom: 1.5rem;
            }

            .form-control {
                border: none;
                border-bottom: 2px solid #ddd;
                padding: 0.5rem;
                font-size: 1rem;
                background-color: transparent;
            }

            .form-control:focus {
                outline: none;
                border-bottom: 2px solid #81C408;
            }

            .btn-primary {
                background-color: #81C408;
                border: none;
                transition: background-color 0.3s;
            }

            .btn-primary:hover {
                background-color: #6ba306;
            }

            .username {
                display: block;
                text-align: center;
                margin-bottom: 10px;
                font-size: 1.2rem;
                color: #555;
            }

            .content-section {
                padding: 20px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <div class="container">
            <c:choose>
                <c:when test="${sessionScope.account.roleId == 2}">
                    <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4" style="background-color: #81C408;">
                        <h1 class="navbar-brand mb-0 h4">Thông tin cá nhân</h1>
                        <div class="d-flex align-items-center">
                            <span class="mr-3">Xin chào, ${account.name}</span>
                            <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
                        </div>
                    </header>
                </c:when>
                <c:when test="${sessionScope.account.roleId == 4}">
                    <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4" style="background-color: #567086;">
                        <h1 class="navbar-brand mb-0 h4">Thông tin cá nhân</h1>
                        <div class="d-flex align-items-center">
                            <span class="mr-3">Xin chào, ${account.name}</span>
                            <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
                        </div>
                    </header>
                </c:when>
                <c:when test="${sessionScope.account.roleId == 3}">
                    <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4" style="background-color: #567086;">
                        <h1 class="navbar-brand mb-0 h4">Thông tin cá nhân</h1>
                        <div class="d-flex align-items-center">
                            <span class="mr-3">Xin chào, ${account.name}</span>
                            <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
                        </div>
                    </header>
                </c:when>
            </c:choose>
            <div class="row flex-lg-nowrap">
                <div class="col-12 col-lg-auto mb-3" style="width: 250px;">
                    <div class="card p-3">
                        <div class="e-navlist e-navlist--active-bg">
                            <div class="user-profile">
                                <div class="avatar">
                                    <img id="avatarImage" src="img/${account.imageAvatar}" alt="Avatar" class="avatar-img">
                                </div>
                            </div>
                            <c:choose>
                                <c:when test="${sessionScope.account.roleId == 2}">
                                    <ul class="nav">
                                        <li class="nav-item">
                                            <a class="nav-link px-3 active" href="home">
                                                <i class="fa fa-fw fa-bar-chart mr-1"></i>
                                                <span>Trang chủ</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="ChangePasswordProfile.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Đổi mật khẩu</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="#">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Mã giảm giá</span>
                                            </a>
                                        </li>                                 
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="orderHistory?orderStatusId=1&accountId=${sessionScope.account.accountId}">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Đơn hàng</span>
                                            </a>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:when test="${sessionScope.account.roleId == 4}">
                                    <ul class="nav">
                                        <li class="nav-item">
                                            <a class="nav-link px-3 active" href="revenueRestaurant">
                                                <i class="fa fa-fw fa-bar-chart mr-1"></i>
                                                <span>Trang chủ</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="ChangePasswordProfile.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Đổi mật khẩu</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="SettingBanner.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Thiết lập quảng cáo</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="voucher?&accountId=${sessionScope.account.accountId}">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Thiết lập mã giảm giá</span>
                                            </a>
                                        </li>
                                    </ul>
                                </c:when>
                                <c:when test="${sessionScope.account.roleId == 3}">
                                    <ul class="nav">
                                        <li class="nav-item">
                                            <a class="nav-link px-3 active" href="revenueRestaurant">
                                                <i class="fa fa-fw fa-bar-chart mr-1"></i>
                                                <span>Trang chủ</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="ChangePasswordProfile.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Đổi mật khẩu</span>
                                            </a>
                                        </li>                                   
                                    </ul>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <div class="card">
                                <div class="card-body">
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
                                                    <img id="currentImage" class="img-fluid d-block mx-auto">
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


