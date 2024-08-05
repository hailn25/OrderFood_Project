
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>4FOODHD</title>
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
                background-color: #567086;
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
                        <h1 class="navbar-brand mb-0 h4">Thiết lập mã giảm giá</h1>
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
                                            <a class="nav-link px-3 active" href="HomeOfRestaurant.jsp">
                                                <i class="fa fa-fw fa-bar-chart mr-1"></i>
                                                <span>Trang chủ</span>
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link px-3" href="profile">
                                                <i class="fa fa-fw fa-cog mr-1"></i>
                                                <span>Thông tin tài khoản</span>
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

                <!-- Main content section -->
                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <div class="card">
                                <div id="personalInfo" class="content-section">
                                    <div class="card-body">
                                        <!-- Hiển thị thông báo thành công -->
                                        <c:if test="${not empty success}">
                                            <div class="alert alert-success" role="alert">
                                                ${success}
                                            </div>
                                        </c:if>

                                        <!-- Hiển thị thông báo lỗi -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>

                                        <form action="voucher" method="post" onsubmit="return validateForm()">
                                            <div class="form-group">
                                                <label for="voucherName">Tên mã giảm giá</label>
                                                <input type="text" id="voucherName" name="voucherName" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="description">Chi tiết</label>
                                                <textarea id="description" name="description" rows="4" class="form-control" required></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="quantity">Số lượng</label>
                                                <input type="number" id="quantity" name="quantity" class="form-control" required min="1">
                                            </div>
                                            <div class="form-group">
                                                <label for="releaseDate">Ngày phát hành</label>
                                                <input type="date" id="releaseDate" name="releaseDate" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="finishDate">Ngày kết thúc</label>
                                                <input type="date" id="finishDate" name="finishDate" class="form-control" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="status" style="display: none;">Status:</label>
                                                <input type="hidden" id="status" name="status" value="0" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="discount">Giảm giá</label>
                                                <div class="input-group">
                                                    <input type="number" id="discount" name="discount" class="form-control" required min="1" max="100" step="0.01">
                                                    <div class="input-group-append">
                                                        <span class="input-group-text">%</span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="voucherCategoryId" style="display: none;">VoucherCategoryId:</label>
                                                <input type="hidden" id="voucherCategoryId" name="voucherCategoryId" value="2" class="form-control hidden" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="restauranId" class="hidden" style="display: none;">restauranId</label>
                                                <input type="hidden" id="restauranId" name="restauranId" value="${sessionScope.account.accountId}" class="form-control hidden" required>
                                            </div>
                                            <div class="form-group text-center">
                                                <button type="submit" class="btn btn-primary">Gửi</button>
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
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                // Set releaseDate to tomorrow
                var releaseDateInput = document.getElementById('releaseDate');
                var tomorrow = new Date();
                tomorrow.setDate(tomorrow.getDate() + 1);
                var yyyy = tomorrow.getFullYear();
                var mm = String(tomorrow.getMonth() + 1).padStart(2, '0');
                var dd = String(tomorrow.getDate()).padStart(2, '0');
                var formattedDate = yyyy + '-' + mm + '-' + dd;
                releaseDateInput.value = formattedDate;
            });
        </script>
        <script>
            function validateForm() {
                const voucherName = document.getElementById("voucherName").value.trim();
                const description = document.getElementById("description").value.trim();
                const quantity = document.getElementById("quantity").value;
                const releaseDate = document.getElementById("releaseDate").value;
                const finishDate = document.getElementById("finishDate").value;
                const discount = document.getElementById("discount").value;

                // Kiểm tra Tên mã giảm giá
                if (voucherName === "") {
                    alert("Tên mã giảm giá không được để trống.");
                    return false;
                }

                // Kiểm tra Chi tiết
                if (description === "") {
                    alert("Chi tiết không được để trống.");
                    return false;
                }

                // Kiểm tra Số lượng
                if (quantity <= 0) {
                    alert("Số lượng phải lớn hơn 0.");
                    return false;
                }

                // Kiểm tra Ngày phát hành
                const today = new Date().toISOString().split("T")[0];
                if (releaseDate < today) {
                    alert("Ngày phát hành không được đặt trong quá khứ.");
                    return false;
                }

                // Kiểm tra Ngày kết thúc
                if (finishDate <= releaseDate) {
                    alert("Ngày kết thúc phải sau ngày phát hành.");
                    return false;
                }

                // Kiểm tra Giảm giá
                if (discount <= 0 || discount > 100) {
                    alert("Giảm giá phải nằm trong khoảng từ 1 đến 100.");
                    return false;
                }

                return true;
            }
        </script>
        <script>
            function validateForm() {
                const voucherName = document.getElementById('voucherName').value.trim();
                const description = document.getElementById('description').value.trim();
                const quantity = document.getElementById('quantity').value.trim();
                const releaseDate = document.getElementById('releaseDate').value.trim();
                const finishDate = document.getElementById('finishDate').value.trim();
                const discount = document.getElementById('discount').value.trim();

                const specialCharPattern = /^[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+$/;

                if (voucherName === "" || specialCharPattern.test(voucherName)) {
                    alert("Tên mã giảm giá không được để trống, toàn dấu cách hoặc toàn ký hiệu đặc biệt.");
                    return false;
                }

                if (description === "" || specialCharPattern.test(description)) {
                    alert("Chi tiết không được để trống, toàn dấu cách hoặc toàn ký hiệu đặc biệt.");
                    return false;
                }

                if (quantity === "" || isNaN(quantity) || quantity <= 0) {
                    alert("Số lượng phải là một số dương và không được để trống.");
                    return false;
                }

                if (releaseDate === "" || new Date(releaseDate) < new Date()) {
                    alert("Ngày phát hành không được để trống và không được chọn ngày trong quá khứ.");
                    return false;
                }

                if (finishDate === "" || new Date(finishDate) < new Date(releaseDate)) {
                    alert("Ngày kết thúc không được để trống và phải sau ngày phát hành.");
                    return false;
                }

                if (discount === "" || isNaN(discount) || discount < 1 || discount > 100) {
                    alert("Giảm giá phải nằm trong khoảng từ 1 đến 100 và không được để trống.");
                    return false;
                }

                return true;
            }
        </script>
    </body>
</html>
