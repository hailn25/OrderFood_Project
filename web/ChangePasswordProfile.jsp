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
                background: #f8f8f8
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
                                            <form id="changePasswordForm" action="editPassword" method="post"> <!-- Đổi method thành POST -->
                                                <h5 class="card-title mb-4">Đổi mật khẩu</h5>
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
