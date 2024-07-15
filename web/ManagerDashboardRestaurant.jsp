<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Product Admin - Dashboard HTML Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="css/bootstrap.min_1.css">
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    </head>

    <!--đây là css của các ô vuông trên biểu đồ-->
    <style>
        .info-square {
            width: 250px;
            height: auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 15px;
            padding: 15px;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            position: relative;
            transition: transform 0.3s ease;
        }

        .info-square:hover {
            transform: translateY(-5px);
        }

        .info-square h2 {
            margin: 20px 0 0;
            font-size: 1.5em;
            word-wrap: break-word;
            width: 100%;
        }

        .info-square p {
            margin: 5px 0 0;
            color: #888;
            font-size: 0.9em;
        }

        .info-square .icon {
            font-size: 3em;
            margin-bottom: 15px;
        }

        .info-square .icon-revenue {
            color: #4CAF50;
        }

        .info-square .icon-products {
            color: #9C27B0;
        }

        .info-square .icon-orders-pending {
            color: #FF9800;
        }

        .info-square .icon-orders-success {
            color: #2196F3;
        }

        .info-container {
            padding: 30px 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-wrap: wrap;
        }
        .info-square h2.default-text {
            color: black; /* Use inherit to set it to the default color of its parent */
        }
    </style>

    <style>
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

    <body id="reportsPage" style="background-color: #F6F6F6">
        <div class="" id="home">

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
                                    <a class="nav-link" href="HomeOfRestaurant.jsp">
                                        <i class="fas fa-home"></i> Trang chủ
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 4}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="revenueRestaurant">
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
                                <!--                                <li class="nav-item">
                                                                    <a class="nav-link" href="managerCloseProduct">
                                                                        <i class="fas fa-shopping-cart"></i> Sản phẩm đang ẩn
                                                                    </a>
                                                                </li>-->
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 4}">
                                <li class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" id="dropdownMenuLink" onclick="toggleDropdown(event)">
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



            <script type="text/javascript">
                google.charts.load('current', {'packages': ['corechart']});
                google.charts.setOnLoadCallback(drawVisualization);

                function drawVisualization() {
                    var data = google.visualization.arrayToDataTable([
                        ['Tháng', 'Doanh thu'],
                        ['Tháng 1', ${requestScope.thang1}],
                        ['Tháng 2', ${requestScope.thang2}],
                        ['Tháng 3', ${requestScope.thang3}],
                        ['Tháng 4', ${requestScope.thang4}],
                        ['Tháng 5', ${requestScope.thang5}],
                        ['Tháng 6', ${requestScope.thang6}],
                        ['Tháng 7', ${requestScope.thang7}],
                        ['Tháng 8', ${requestScope.thang8}],
                        ['Tháng 9', ${requestScope.thang9}],
                        ['Tháng 10', ${requestScope.thang10}],
                        ['Tháng 11', ${requestScope.thang11}],
                        ['Tháng 12', ${requestScope.thang12}]
                    ]);

                    var formatter = new google.visualization.NumberFormat({
                        suffix: ' VNĐ',
                        fractionDigits: 0
                    });

                    formatter.format(data, 1); // Định dạng cột thứ 2 (index 1) là số và thêm ' VNĐ' vào sau giá trị

                    var currentYear = new Date().getFullYear();

                    var options = {
                        title: 'Doanh thu của nhà hàng trong năm ' + currentYear,
                        vAxis: {
                            title: 'Doanh thu (VNĐ)',
                            format: '#,### VNĐ'  // Định dạng số với "VNĐ" sau
                        },
                        hAxis: {title: 'Tháng'},
                        seriesType: 'bars'
                    };

                    var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
                    chart.draw(data, options);
                }




            </script>

            <!-- Info squares -->
            <div class="info-container">
                <div class="info-square">
                    <i class="fas fa-dollar-sign icon icon-revenue"></i>
                    <div>
                        <h2 class="revenue-text">${totalRevenue} VNĐ</h2>
                        <p>Doanh thu</p>
                    </div>
                </div>
                <a href="managerOpenProduct">
                    <div class="info-square">
                        <i class="fas fa-box icon icon-products"></i>
                        <div>
                            <h2 class="default-text">${totalProducts}</h2>
                            <p>Sản phẩm đang bán</p>
                        </div>
                    </div>
                </a>
                <a href="managerOrderOfCustomer_6">
                    <div class="info-square">
                        <i class="fas fa-clock icon icon-orders-pending"></i>
                        <div>
                            <h2 class="default-text">${totalPendingOrders}</h2>
                            <p>Đơn hàng đang chờ xác nhận</p>
                        </div>
                    </div>
                </a>
                <a href="managerOrderOfCustomer_3">
                    <div class="info-square">
                        <i class="fas fa-check-circle icon icon-orders-success"></i>
                        <div>
                            <h2 class="default-text">${totalCompletedOrders}</h2>
                            <p>Đơn hàng đã giao thành công</p>
                        </div>
                    </div>
                </a>
            </div>

            <div class="col-12 tm-block-col">
                <div class=" tm-block-taller">
                    <div id="chart_div" style="width: 1300px; height: 500px; margin: 50px auto;"></div>
                </div>
            </div>

        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="js/moment.min.js"></script>
        <!-- https://momentjs.com/ -->
        <script src="js/Chart.min.js"></script>
        <!-- http://www.chartjs.org/docs/latest/ -->
        <!-- https://getbootstrap.com/ -->
        <script src="js/tooplate-scripts.js"></script>

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
        </script>


    </body>

</html>
