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
                <a class="navbar-brand" href="HomeOfRestaurant.jsp">
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
                                <a class="nav-link active" href="HomeOfRestaurant.jsp">
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
                                <a class="nav-link" href="managerOpenProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm đang bán
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" id="dropdownMenuLink" onclick="toggleDropdown(event)">
                                    <i class="far fa-file-alt"></i>
                                    <span> Quản lý đơn hàng <i class="fas fa-angle-down"></i> </span>
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
            <div class="row">
                <div class="col">
                    <p class=" mt-5 mb-5" style="color: black">Xin chào, <b>${sessionScope.account.name}</b></p>
                </div>
            </div>

            <!-- Nội dung giả để tăng chiều cao của trang -->
            <div class="content-placeholder">
                <p style="height: 2000px;"></p>
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
            function toggleDropdown(event) {
                event.preventDefault();
                event.stopPropagation();
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
        </script>

    </body>
</html>
