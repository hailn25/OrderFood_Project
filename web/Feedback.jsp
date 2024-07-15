<%-- 
    Document   : Profile
    Created on : Jun 19, 2024, 11:32:34 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Thông tin cá nhân</title>
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
                        <h1 class="navbar-brand mb-0 h4">Đánh giá</h1>
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
                                            <a class="nav-link px-3 active" href="managerShipper">
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
                                            <div class="star-rating">
                                                <span class="fa fa-star" data-rating="1"></span>
                                                <span class="fa fa-star" data-rating="2"></span>
                                                <span class="fa fa-star" data-rating="3"></span>
                                                <span class="fa fa-star" data-rating="4"></span>
                                                <span class="fa fa-star" data-rating="5"></span>
                                            </div>
                                            <input type="hidden" id="rateStar" name="rateStar">
                                        </div>
                                       
                                        <div class="form-group">
                                            <label for="feedback">Feedback:</label>
                                            <input type="text" class="form-control" id="feedback" name="feedback">
                                        </div>

                                        <div class="form-group">
                                            <label for="accountId">Account ID:</label>
                                            <input type="text" class="form-control" id="accountId" name="accountId" value="${sessionScope.account.accountId}" readonly>
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
                                            <input id="imageURL" name="imageURL" type="file" class="custom-file-input" onchange="previewImage(event)">
                                            <label class="custom-file-label" for="fileInput">Chọn ảnh</label>
                                        </div>

                                        <div class="form-group">
                                            <img id="imagePreview" src="#" style="display: none; max-height: 300px;">
                                        </div>

                                        <a href="orderHistory?orderStatusId=1&accountId=${accountId}" class="btn btn-secondary mr-2">Quay lại</a>
                                        <button type="submit" class="btn btn-primary">Submit Feedback</button>
                                    </form>
                                </div>
                            </div>
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

                                                    $(document).ready(function () {
                                                        var $stars = $('.star-rating .fa-star');
                                                        var $rateStar = $('#rateStar');

                                                        $stars.on('click', function () {
                                                            var rating = $(this).data('rating');
                                                            $rateStar.val(rating);
                                                            $stars.removeClass('checked');
                                                            for (var i = 0; i < rating; i++) {
                                                                $stars.eq(i).addClass('checked');
                                                            }
                                                        });
                                                    });
        </script>
    </body>
</html>
