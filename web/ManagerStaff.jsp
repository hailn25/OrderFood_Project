<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>4FOODHD</title>
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
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">
        <div class="" id="home">

            <nav class="navbar navbar-expand-xl">
                <div class="container h-100">


                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mx-auto h-100">
                            <c:if test="${sessionScope.account.roleId == 5}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="ManagerStaff.jsp">
                                        <i class="fas fa-home"></i> Trang chủ
                                        <span class="sr-only">(current)</span>
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 5}">
                                <li class="nav-item">
                                    <a class="nav-link" href="managerCategory">
                                        <i class="fas fa-bookmark"></i> Loại sản phẩm
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="managerBlog">
                                        <i class="far fa-file-alt"></i> Quản lý blog
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="managerService">
                                        <i class="fas fa-sliders-h"></i> Dịch vụ 
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="managerReport">
                                        <i class="far fa-comment-dots"></i> Quản lý báo cáo
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link " href="managerProductFlashSale">
                                        <i class="far fa-clock"></i> Flash Sale
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="managerVoucher">
                                        <i class="fas fa-ticket-alt"></i> Quản lý mã giảm giá
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 5}">                          
                                <li class="nav-item">
                                    <a class="nav-link" href="loadListRequestVoucher">
                                        <i class="fas fa-tasks"></i> Yêu cầu thêm voucher
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
                        <p class=" mt-5 mb-5" style="color: black">Welcome back, <b>Staff</b></p>
                    </div>
                </div>

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
            <script>
                new DataTable('#example');

                Chart.defaults.global.defaultFontColor = 'white';
                let ctxLine,
                        ctxBar,
                        ctxPie,
                        optionsLine,
                        optionsBar,
                        optionsPie,
                        configLine,
                        configBar,
                        configPie,
                        lineChart;
                barChart, pieChart;
                // DOM is ready
                $(function () {
                    drawLineChart(); // Line Chart
                    drawBarChart(); // Bar Chart
                    drawPieChart(); // Pie Chart

                    $(window).resize(function () {
                        updateLineChart();
                        updateBarChart();
                    });
                });
            </script>
    </body>

</html>



