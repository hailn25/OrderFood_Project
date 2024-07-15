<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .custom-header {
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
                        <h1 class="navbar-brand mb-0 h4">Đổi mật khẩu</h1>
                        <div class="d-flex align-items-center">
                            <span class="mr-3">Xin chào, ${account.name}</span>
                            <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
                        </div>
                    </header>
                </c:when>
                <c:when test="${sessionScope.account.roleId == 4}">
                    <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4" style="background-color: #567086;">
                        <h1 class="navbar-brand mb-0 h4">Đổi mật khẩu</h1>
                        <div class="d-flex align-items-center">
                            <span class="mr-3">Xin chào, ${account.name}</span>
                            <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
                        </div>
                    </header>
                </c:when>
                <c:when test="${sessionScope.account.roleId == 3}">
                    <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4" style="background-color: #567086;">
                        <h1 class="navbar-brand mb-0 h4">Đổi mật khẩu</h1>
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
                                                <span>Voucher</span>
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
                                                <span>Setting banner</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="voucher?&accountId=${sessionScope.account.accountId}">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Voucher</span>
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
                                            <form id="changePasswordForm" action="editPassword" method="post"> <!-- Đổi method thành POST -->
                                                <div class="form-group">
                                                    <input type="hidden" name="accountId" value="${account.accountId}">
                                                </div>
                                                <div class="form-group">
                                                    <label for="currentPassword"></label>
                                                    <input type="password" class="form-control" name="currentPassword" placeholder="Nhập mật khẩu cũ" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="newPassword"></label>
                                                    <input type="password" class="form-control" name="newPassword" placeholder="Nhập mật khẩu mới" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="confirmPassword"></label>
                                                    <input type="password" class="form-control" name="confirmPassword" placeholder="Nhập lại mật khẩu mới" required>
                                                </div>
                                                <input type="submit" class="btn btn-primary" value="Lưu">

                                                <!-- Thông báo lỗi -->
                                                <c:if test="${not empty error}">
                                                    <div class="alert alert-danger mt-3" role="alert">
                                                        ${error}
                                                    </div>
                                                </c:if>
                                                <!-- Thông báo thành công -->
                                                <c:if test="${not empty success}">
                                                    <div class="alert alert-success mt-3" role="alert">
                                                        ${success}
                                                    </div>
                                                </c:if>
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
                var form = document.getElementById('changePasswordForm');

                form.addEventListener("submit", function (event) {
                    var currentPassword = form.elements["currentPassword"].value.trim();
                    var newPassword = form.elements["newPassword"].value.trim();
                    var confirmPassword = form.elements["confirmPassword"].value.trim();

                    // Validate Mật khẩu cũ
                    if (!currentPassword) {
                        alert("Vui lòng nhập Mật khẩu cũ.");
                        event.preventDefault();
                        return false;
                    }

                    // Validate Mật khẩu mới
                    if (newPassword.length < 6) {
                        alert("Mật khẩu mới phải có ít nhất 6 ký tự.");
                        event.preventDefault();
                        return false;
                    }

                    // Validate Mật khẩu mới và Nhập lại mật khẩu mới
                    if (newPassword !== confirmPassword) {
                        alert("Mật khẩu mới và Nhập lại mật khẩu mới không khớp.");
                        event.preventDefault();
                        return false;
                    }

                    return true; // Form sẽ submit nếu hợp lệ
                });
            });
        </script>

    </body>
</html>
