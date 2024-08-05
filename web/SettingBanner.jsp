<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>4FOODHD</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
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

            .image-preview {
                max-width: 100%;
                height: 200px;
                width: 200px;
                display: block;
                margin: 0 auto;
            }

            .image-preview {
                max-width: 100%;
                height: auto; /* Thay đổi để ảnh không bị cắt */
                display: block;
                margin: 0 auto;
            }
        </style>
        <script>
            function validateForm() {
                let isValid = true;

                // Validate sliderTitle
                const sliderTitle = document.getElementById('sliderTitle');
                const sliderTitleError = document.getElementById('sliderTitleError');
                if (sliderTitle.value.trim() === '') {
                    sliderTitleError.textContent = 'Chi tiết không được để trống.';
                    isValid = false;
                } else {
                    sliderTitleError.textContent = '';
                }

                // Validate imageAvatar
                const imageAvatar = document.getElementById('imageAvatar');
                const imageAvatarError = document.getElementById('imageAvatarError');
                if (imageAvatar.files.length === 0) {
                    imageAvatarError.textContent = 'Ảnh không được để trống.';
                    isValid = false;
                } else {
                    imageAvatarError.textContent = '';
                }

                return isValid;
            }

            function previewImages(event) {
                const input = event.target;
                const previewsContainer = document.getElementById('imagePreviews');
                previewsContainer.innerHTML = ''; // Xóa các ảnh xem trước trước đó

                if (input.files) {
                    Array.from(input.files).forEach(file => {
                        const reader = new FileReader();
                        reader.onload = function (e) {
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.className = 'image-preview';
                            previewsContainer.appendChild(img);
                        }
                        reader.readAsDataURL(file);
                    });
                }
            }

            function validateForm() {
                let isValid = true;
                const sliderTitle = document.getElementById('sliderTitle');
                const sliderTitleError = document.getElementById('sliderTitleError');
                const sliderTitleValue = sliderTitle.value.trim();
                if (sliderTitleValue === '') {
                    sliderTitleError.textContent = 'Chi tiết không được để trống.';
                    isValid = false;
                } else if (/^\s*$/.test(sliderTitleValue)) {
                    sliderTitleError.textContent = 'Chi tiết không được chỉ chứa dấu cách.';
                    isValid = false;
                } else if (/^[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+$/.test(sliderTitleValue)) {
                    sliderTitleError.textContent = 'Chi tiết không được chỉ chứa ký hiệu đặc biệt.';
                    isValid = false;
                } else {
                    sliderTitleError.textContent = '';
                }

                return isValid;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <header class="custom-header d-flex justify-content-between align-items-center py-3 mb-4">
                <h1 class="navbar-brand mb-0 h4">Thiết lập quảng cáo</h1>
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
                                <div id="personalInfo" class="content-section">
                                    <div class="card">
                                        <div class="card-body">
                                            <%-- Check for success message --%>
                                            <% if (request.getAttribute("message") != null) { %>
                                            <div class="alert alert-success" role="alert">
                                                <%= request.getAttribute("message") %>
                                            </div>
                                            <% } %>

                                            <%-- Check for error message --%>
                                            <% if (request.getAttribute("error") != null) { %>
                                            <div class="alert alert-danger" role="alert">
                                                <%= request.getAttribute("error") %>
                                            </div>
                                            <% } %>

                                            <form action="settingBanner" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                                <div class="form-group">
                                                    <label for="sliderTitle">Chi tiết</label>
                                                    <input type="text" class="form-control" id="sliderTitle" name="sliderTitle" required>
                                                    <div class="text-danger" id="sliderTitleError"></div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="backLink" style="display: none;">Backlink</label>
                                                    <input type="hidden" class="form-control" id="backLink" value="NULL" name="backLink" required>
                                                    <div class="text-danger" id="backLinkError"></div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="createDate" style="display: none">Create Date</label>
                                                    <input type="hidden" class="form-control" id="displayDate" readonly>
                                                    <input type="hidden" id="createDate" name="createDate">
                                                    <div class="text-danger" id="createDateError"></div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="updateDate" style="display: none">Update Date</label>
                                                    <input type="hidden" class="form-control" id="updateDate" name="updateDate" readonly>
                                                    <div class="text-danger" id="updateDateError"></div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="imageAvatar">Ảnh</label>
                                                    <input type="file" class="form-control-file" id="imageAvatar" name="imageAvatar" onchange="previewImages(event)" multiple>
                                                    <div class="text-danger" id="imageAvatarError"></div>
                                                </div>

                                                <div class="form-group">
                                                    <div id="imagePreviews"></div>
                                                </div>

                                                <input type="submit" class="btn btn-primary" value="Gửi">
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
            document.addEventListener('DOMContentLoaded', function () {
                var today = new Date().toISOString().split('T')[0];
                document.getElementById('createDate').value = today;
                document.getElementById('updateDate').value = today;
            });
        </script>
    </body>
</html>


