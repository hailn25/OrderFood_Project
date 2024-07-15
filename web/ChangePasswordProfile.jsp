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
            .custom-header {
                background-color: #81C408; /* Màu nền header */
                color: #fff; /* Màu chữ của header */
                padding: 10px; /* Khoảng cách giữa nội dung và viền header */
                width: 100%; /* Chiều rộng header bằng 100% */
                box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Đổ bóng cho header */
            }

            .navbar-brand {
                font-size: 1.5rem; /* Cỡ chữ của tiêu đề */
            }
            body {
                margin-top: 20px;
                background: #f8f8f8
            }

            .avatar {
                display: flex;
                justify-content: center; /* căn giữa theo chiều ngang */
                align-items: center; /* căn giữa theo chiều dọc */
                width: 190px; /* Chiều rộng cố định của avatar */
                height: 190px;
                overflow: hidden; /* Ảnh sẽ bị cắt bớt nếu vượt quá kích thước của avatar */
            }

            .avatar-img {
                max-width: 100%; /* hình ảnh không vượt quá kích thước của ô avatar */
                max-height: 100%; /* hình ảnh không vượt quá kích thước của ô avatar */
                display: block; /* đảm bảo hình ảnh không bị căn giữa dọc */
                margin: auto; /* căn giữa hình ảnh */
            }

            .nav {
                display: flex;
                flex-wrap: wrap;
                gap: 10px; /* Khoảng cách giữa các mục */
                list-style-type: none; /* Loại bỏ các dấu đầu dòng */
                padding: 0; /* Xóa padding mặc định của danh sách */
            }

            .nav-item {
                flex: 1 1 100%; /* Mỗi mục chiếm hết chiều rộng của cột */
                max-width: 100%; /* Đảm bảo mỗi mục không vượt quá chiều rộng tối đa */
            }

            .nav-link {
                display: block;
            }

            .username {
                display: block;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4">
                <h1 class="navbar-brand mb-0 h4">Đổi mật khẩu</h1>
                <div class="d-flex align-items-center">
                    <span class="mr-3">Xin chào, ${account.name}</span>
                    <a href="logout" class="btn btn-outline-danger btn-sm">Đăng xuất</a>
                </div>
            </header>
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
                                            <a class="nav-link px-3" href="Voucher.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Voucher</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="OrderHistory.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Lịch sử đơn hàng</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="OrderTracking.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Theo dõi đơn hàng</span>
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
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="SettingBanner.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Setting banner</span>
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
                                                    <label for="currentPassword">Mật khẩu cũ</label>
                                                    <input type="password" class="form-control" name="currentPassword" placeholder="Nhập mật khẩu cũ" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="newPassword">Mật khẩu mới</label>
                                                    <input type="password" class="form-control" name="newPassword" placeholder="Nhập mật khẩu mới" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="confirmPassword">Nhập lại mật khẩu mới</label>
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
