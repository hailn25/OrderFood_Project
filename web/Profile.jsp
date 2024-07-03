<%-- 
    Document   : Profile
    Created on : Jun 19, 2024, 11:32:34 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin cá nhân</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
            .custom-header {
                background-color: #81C408; /* Màu nền header */
                color: #fff; /* Màu chữ của header */
                padding: 10px; /* Khoảng cách giữa nội dung và viền header */
                width: 100%; /* Chiều rộng header bằng 100% */
                box-shadow: 0 2px 4px rgba(0,0,0,0.1); /* Đổ bóng cho header */
                border-top-left-radius: 50px; /* Bo tròn góc trái trên */
                border-bottom-left-radius: 20px;
                border-top-right-radius: 20px;
                border-bottom-right-radius: 50px; /* Bo tròn góc phải dưới */

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

            .username{
                display: block;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4">
                <h1 class="navbar-brand mb-0 h4">Thông tin cá nhân</h1>
                <div class="d-flex align-items-center">
                    <span class="mr-3">Xin chào, ${account.name}</span>
                    <a href="logout" class="btn btn-outline-danger btn-sm">Out</a>
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
                                            <a class="nav-link px-3" href="orderHistory?accountId=${sessionScope.account.accountId}">
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
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="Voucher.jsp">
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
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="SettingBanner.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Setting banner</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="Voucher.jsp">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Voucher</span>
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
                                <div id="personalInfo" class="content-section">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="fullName">Họ và Tên</label>
                                                        <div class="form-control">${account.name}</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="eMail">Email</label>
                                                        <div class="form-control">${account.email}</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="phone">Số Điện Thoại</label>
                                                        <div class="form-control">${account.phone}</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="gender">Giới Tính</label>
                                                        <div class="form-control">${account.gender ? 'Nam' : 'Nữ'}</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label for="address">Địa Chỉ</label>
                                                        <div class="form-control">${account.address}</div>
                                                    </div>
                                                </div>
                                            </div>
                                            <a href="EditProfile.jsp" class="btn btn-primary mt-3">Sửa</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
