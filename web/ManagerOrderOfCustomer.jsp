<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!--cai nay cua ProGear-->
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
            .btn {
                display: inline-block;
                padding: 5px 10px;
                font-size: 16px;
                font-weight: bold;
                color: white;
                background-color: #28a745;
                border: none;
                border-radius: 5px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-blue {
                background-color: #74C0FC;
                color: white;
            }

            .btn-green {
                background-color: #28a745;
                color: white;
            }

            .btn-green:hover {
                background-color: #218838;
                color: white;
            }

            .btn-blue:hover {
                background-color: #58A6FF;
                color: white;
            }

            .btn-red {
                background-color: #dc3545;
                color: white;
            }

            .btn-red:hover {
                background-color: #c82333;
                color: white;
            }

        </style>
        <style>
            .status-waiting-for-shipper {
                color: orange;
            }

            .status-delivering {
                color: blue;
            }

            .status-delivered-successfully {
                color: green;
            }

            .status-customer-rejected {
                color: red;
            }

            .status-shipper-rejected {
                color: darkred;
            }
.status-waiting-for-restaurant {
                color: purple;
            }

            .status-restaurant-rejected {
                color: brown;
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



    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">
        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="revenueRestaurant">
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
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link" href="revenueRestaurant">
                                    <i class="fas fa-tachometer-alt"></i> Thống kê
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="managerCategory">
                                    <i class="far fa-file-alt"></i> Loại sản phẩm
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
<li class="nav-item">
                                <a class="nav-link " href="managerOpenProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm đang bán
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerCloseProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm đang ẩn
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle active" id="dropdownMenuLink" onclick="toggleDropdown(event)">
                                        <i class="far fa-file-alt" onclick="toggleDropdown(event)"></i>
                                        <span onclick="toggleDropdown(event)"> Quản lý đơn hàng <i class="fas fa-angle-down"></i> </span>
                                    </a>
                                    <div class="dropdown-menu" id="dropdownMenu">
                                        <a class="dropdown-item" href="managerOrderOfCustomer_0">Tất cả đơn hàng của nhà hàng</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_6">Đơn hàng đang chờ xác nhận của nhà hàng</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_1">Đơn hàng đang chờ xác nhận của shipper</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_2">Đơn hàng đang giao</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_3">Đơn hàng giao thành công</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_4">Đơn hàng bị khách hàng huỷ</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_5">Đơn hàng bị shipper huỷ</a>
                                        <a class="dropdown-item" href="managerOrderOfCustomer_7">Đơn hàng do nhà hàng huỷ</a>
                                    </div>
                                </li>

                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="Profile.jsp">
                                    <i class="far fa-user"></i> Tài khoản
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
                            <h2>Quản lý <b> đơn hàng</b></h2>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Đơn giá</th>
                            <th>Trạng thái</th>
                            <th>Ảnh</th>
                            <th>Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listO}" var="o">
                            <tr>
                                <td>${o.orderDetailId}</td>
                                <td>${o.name}</td>
                                <td>${o.quantity}</td>
                                <td><fmt:formatNumber value="${o.totalMoney}" type="number" minFractionDigits="0" maxFractionDigits="0" /> đ</td>
                                <td class="<c:choose>
                                        <c:when test="${o.orderStatusId == 1}">status-waiting-for-shipper</c:when>
                                        <c:when test="${o.orderStatusId == 2}">status-delivering</c:when>
                                        <c:when test="${o.orderStatusId == 3}">status-delivered-successfully</c:when>
                                        <c:when test="${o.orderStatusId == 4}">status-customer-rejected</c:when>
                                        <c:when test="${o.orderStatusId == 5}">status-shipper-rejected</c:when>
                                        <c:when test="${o.orderStatusId == 6}">status-waiting-for-restaurant</c:when>
                                        <c:when test="${o.orderStatusId == 7}">status-restaurant-rejected</c:when>
                                        <c:otherwise>status-unknown</c:otherwise>
                                    </c:choose>">
                                    <c:choose>
<c:when test="${o.orderStatusId == 1}">
                                            Đang chờ xác nhận của shipper
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 2}">
                                            Đang giao hàng
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 3}">
                                            Giao hàng thành công
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 4}">
                                            Người mua không nhận hàng
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 5}">
                                            Shipper không nhận đơn
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 6}">
                                            Đang chờ xác nhận của nhà hàng
                                        </c:when>
                                        <c:when test="${o.orderStatusId == 7}">
                                            Nhà hàng không nhận đơn
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <img src="img/${o.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td>
                                    <a href="viewOrderByRestaurant?oid=${o.orderDetailId}" class="btn btn-blue" title="Xem chi tiết"><i class="far fa-eye" ></i></a>
                                        <c:if test="${o.orderStatusId == 6}">
                                        <a href="confirmOrder?oid=${o.orderDetailId}" class="btn btn-green" title="Xác nhận"><i class="fas fa-check"></i></a>
                                        <a href="cancelOrder?oid=${o.orderDetailId}" onclick="confirmCancel(event)" class="btn btn-red" title="Từ chối"><i class="fas fa-times"></i></a>
                                        </c:if>
                                </td>
                            </tr>
                        </c:forEach>


                    </tbody>
                </table>
            </div>
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
                var confirmAction = confirm("Bạn có chắc chắn muốn ẩn sản phẩm này không?");
                if (confirmAction) {
                    window.location.href = event.target.closest('a').href;
                }
            }
        </script>
        <script>
            function toggleDropdown(event) {
                event.preventDefault();
                var dropdownMenu = document.getElementById("dropdownMenu");
                dropdownMenu.classList.toggle("show");
            }

// Đóng dropdown menu nếu click ngoài nó
            window.onclick = function (event) {
                if (!event.target.matches('.dropdown-toggle')) {
                    var dropdowns = document.getElementsByClassName("dropdown-menu");
                    for (var i = 0; i < dropdowns.length; i++) {
                        var openDropdown = dropdowns[i];
                        if (openDropdown.classList.contains('show')) {
                            openDropdown.classList.remove('show');
                        }
                    }
                }
            }
            function toggleDropdown(event) {
                event.preventDefault();
                event.stopPropagation();
                var dropdownMenu = document.getElementById("dropdownMenu");
                dropdownMenu.classList.toggle("show");
            }
            function confirmCancel(event) {
                event.preventDefault();
                var confirmAction = confirm("Bạn có chắc chắn huỷ đơn hàng này không?");
                if (confirmAction) {
                    window.location.href = event.target.closest('a').href;
                }
            }
            function confirmAccecpt(event) {
                event.preventDefault();
                var confirmAction = confirm("Bạn có chắc chắn muốn hiển thị sản phẩm này không?");
                if (confirmAction) {
                    window.location.href = event.target.closest('a').href;
                }
            }
        </script>



    </body>
</html>