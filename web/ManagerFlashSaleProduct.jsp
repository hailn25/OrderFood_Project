<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!--cai nay cua ProGear-->
        <title>4FOODHD</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!--day la icon edit, delete-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"> <!--day la table cua bang manager-->
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">
        <style>
            img{
                width: 150px;
                height: 150px;
            }
            /* Ẩn dropdown menu mặc định */
            .dropdown-menu {
                display: none;
                position: absolute;
                background-color: white;
                box-shadow: 0 8px 16px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-menu .dropdown-item {
                padding: 8px 16px;
                display: block;
                color: black;
                text-decoration: none;
            }

            .dropdown-menu .dropdown-item:hover {
                background-color: #ddd;
            }

            /* Hiển thị dropdown menu khi active */
            .show {
                display: block;
            }

        </style>

        <style>
            .flash-sale {
                color: green; /* Màu xanh cho Đang FlashSale */
            }

            .rejected {
                color: red; /* Màu đỏ cho Bị từ chối */
            }

            .ended {
                color: gray; /* Màu xám cho Đã hết FlashSale */
            }

            .pending-1 {
                color: orange; /* Màu cam cho Chờ xác nhận */
            }

        </style>
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">
        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <!--                <a class="navbar-brand" href="HomeOfRestaurant.jsp">
                <c:if test="${not empty sessionScope.account.name}">
                    <h1 class="tm-site-title mb-0">Nhà hàng: <br><b>${sessionScope.account.name}</b></h1>
                </c:if>
        </a>
        <button
            class="navbar-toggler ml-auto mr-0"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
            >
            <i class="fas fa-bars tm-nav-icon"></i>
        </button>-->

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link" href="HomeOfRestaurant.jsp">
                                    <i class="fas fa-home"></i> Trang chủ
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link" href="revenueRestaurant">
                                    <i class="fas fa-tachometer-alt"></i> Thống kê
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link" href="managerCategory">
                                    <i class="far fa-file-alt"></i> Loại sản phẩm
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerOpenProduct" id="btn-viewListProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm đang bán
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link active" href="managerFlashSaleProduct" id="btn-viewListProduct">
                                    <i class="fas fa-bolt"></i> Sản phẩm đang FlashSale
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <!--                            <li class="nav-item">
                                                            <a class="nav-link" href="managerCloseProduct">
                                                                <i class="fas fa-shopping-cart"></i> Sản phẩm đang ẩn
                                                            </a>
                                                        </li>-->
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerOrderOfCustomer_0">
                                    <i class="far fa-file-alt"></i> Quản lý đơn hàng
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="Profile.jsp">
                                    <i class="far fa-user"></i> Tài khoản
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="messageRestaurant?accountId=${sessionScope.account.accountId}">
                                    <i class="far fa-comments"></i> Tin nhắn
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <c:if test = "${sessionScope.account == null}"> 
                                <a class="nav-link d-block" href="Login.jsp">
                                    <b>Đăng nhập</b>
                                </a>
                            </c:if> 
                            <c:if test = "${sessionScope.account != null}"> 
                                <a class="nav-link d-block" href="logout">
                                    <b>Đăng xuất</b>
                                </a>
                            </c:if> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý sản phẩm<b> FlashSale</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="loadListProductToSelect"  id="btn-addNewProduct" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm sản phẩm FlashSale</span></a>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giảm</th>
                            <th>Giá sau khi giảm</th>
                            <th>Ngày</th>
                            <!--<th>Khung giờ</th>-->
                            <th>Trạng thái</th>
                            <th>Ảnh</th>
                            <th>Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody>


                        <c:forEach items="${listP}" var="o">
                            <tr>
                                <td>${o.productName}</td>
                                <td>${o.quantity}</td>
                                <td><fmt:formatNumber value="${o.discount * 100}" type="number" maxFractionDigits="0"/>%</td>
                                <td><fmt:formatNumber value="${o.salePrice}" type="number" maxFractionDigits="0"/> VNĐ</td>
                                <td><fmt:formatDate value="${o.date}" pattern="dd-MM-yyyy"/></td>
                                
                                <td>
                                    <c:choose>
                                        <c:when test="${o.isFlashSale == 0}">
                                            <span class="pending-1">Chờ xác nhận</span>
                                        </c:when>
                                        <c:when test="${o.isFlashSale == 1}">
                                            <span class="flash-sale">Đang FlashSale</span>
                                        </c:when>
                                        <c:when test="${o.isFlashSale == 2}">
                                            <span class="rejected">Bị từ chối</span>
                                        </c:when>
                                        <c:when test="${o.isFlashSale == 3}">
                                            <span class="ended">Đã hết FlashSale</span>
                                        </c:when>

                                    </c:choose>
                                </td>

                                <td>
                                    <img src="img/${o.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td>
                                    <a href="viewDetailFlashSaleProduct?pid=${o.productId}" class="" title="Xem chi tiết"><i class="material-symbols-outlined far fa-eye" ></i></a>
                                    <a href="deleteFlashSaleProduct?pid=${o.productId}&quantity=${o.quantity}" onclick="confirmDelete(event)" class="delete" data-toggle="modal"><i class="material-symbols-outlined" data-toggle="tooltip" title="Xoá sản phẩm" style="color: red">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="loadListProductToSelect" class="btn btn-primary btn-block text-uppercase mb-3">Thêm sản phẩm FlashSale</a>
        </div>

        <!--cai nay cua ProGear-->
        <script src="js/manager_1.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>



        <script>
                                        new DataTable('#example');
        </script>
        <script>
            function formatPrice(price) {
                // Chuyển đổi số tiền thành dạng chuỗi và thêm dấu phân cách phần nghìn
                return price.toLocaleString('vi-VN') + ' đ';
            }
            function confirmDelete(event) {
                event.preventDefault();
                var confirmAction = confirm("Bạn có chắc chắn muốn xoá sản phẩm này không?");
                if (confirmAction) {
                    window.location.href = event.target.closest('a').href;
                }
            }
        </script>

        <script>
            function confirmTurnOffSale(event) {
                event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết

                var confirmAction = confirm("Bạn có chắc chắn muốn tắt sale sản phẩm này không?");

                if (confirmAction) {
                    window.location.href = event.target.closest('a').href;
                }
            }

            function confirmTurnOnSale(event) {
                event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết

                var userConfirmed = confirm("Bạn có chắc muốn sale sản phẩm này không?");

                if (userConfirmed) {
                    window.location.href = event.currentTarget.href;
                }
            }

// Gắn hàm confirmDelete vào sự kiện onclick của tất cả các liên kết có lớp .delete và tiêu đề "Khoá tài khoản"
            document.querySelectorAll('.delete[data-toggle="tooltip"][title="Tắt sale sản phẩm"]').forEach(function (element) {
                element.onclick = function (event) {
                    confirmDelete(event);
                };
            });

// Gắn hàm confirmOpen vào sự kiện onclick của tất cả các liên kết có lớp .delete và tiêu đề "Mở khoá tài khoản"
            document.querySelectorAll('.delete[data-toggle="tooltip"][title="Bật sale sản phẩm"]').forEach(function (element) {
                element.onclick = function (event) {
                    confirmOpen(event);
                };
            });
        </script>


    </body>
</html>



