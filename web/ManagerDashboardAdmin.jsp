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

    <body id="reportsPage" style="background-color: #F6F6F6">
        <div class="" id="home">

            <nav class="navbar navbar-expand-xl">
                <div class="container h-100">
                    <a class="navbar-brand" href="revenueAdmin">
                        <c:if test="${not empty sessionScope.account.name}">
                            <h1 class="tm-site-title mb-0">Admin</h1>
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
                            <c:if test="${sessionScope.account.roleId == 1}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="revenueAdmin">
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
                                    <a class="nav-link" href="managerOpenProduct">
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

                            <c:if test="${sessionScope.account.roleId == 1}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="managerAccount">
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



            <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <html>
                <head>
                    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
                    <script type="text/javascript">
                        google.charts.load('current', {'packages': ['corechart']});
                        google.charts.setOnLoadCallback(drawChart);

                        function drawChart() {
                            var data = google.visualization.arrayToDataTable([
                                ['Tháng', 'Doanh thu'],
                                ['Tháng 1', ${t1}],
                                ['Tháng 2', ${t2}],
                                ['Tháng 3', ${t3}],
                                ['Tháng 4', ${t4}],
                                ['Tháng 5', ${t5}],
                                ['Tháng 6', ${t6}],
                                ['Tháng 7', ${t7}],
                                ['Tháng 8', ${t8}],
                                ['Tháng 9', ${t9}],
                                ['Tháng 10', ${t10}],
                                ['Tháng 11', ${t11}],
                                ['Tháng 12', ${t12}]
                            ]);

                            var options = {
                                title: 'Doanh thu của nhà hàng trong năm 2024',
                                curveType: 'function',
                                legend: {position: 'bottom'}
                            };

                            var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

                            chart.draw(data, options);
                        }
                    </script>
                </head>
                <div class="col-12 tm-block-col">
                    <div class=" tm-block-taller">
                        <div id="curve_chart" style="width: 1300px; height: 500px; margin: 50px auto"></div>
                    </div>
                </div>

            </html>
        </head>
        
    </div>

    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- https://jquery.com/download/ -->
    <script src="js/moment.min.js"></script>
    <!-- https://momentjs.com/ -->
    <script src="js/Chart.min.js"></script>
    <!-- http://www.chartjs.org/docs/latest/ -->
    <script src="js/bootstrap.min.js"></script>
    <!-- https://getbootstrap.com/ -->
    <script src="js/tooplate-scripts.js"></script>

</body>

</html>
