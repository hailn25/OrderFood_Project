<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!--cai nay cua ProGear-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!--day la icon edit, delete-->
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"> <!--day la table cua bang manager-->
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <title>4FOODHD</title>
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

            .btn-green {
                background-color: #28a745;
                color: white;
            }

            .btn-green:hover {
                background-color: #218838;
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
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">

        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="ManagerStaff.jsp">
                    <c:if test="${not empty sessionScope.account.name}">
                        <h1 class="tm-site-title mb-0">Staff: <br><b>${sessionScope.account.name}</b></h1>
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

                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManagerStaff.jsp">
                                    <i class="fas fa-home"></i> Trang chủ
                                    <span class="sr-only">(current)</span>
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
                                <a class="nav-link active" href="managerService">
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
                            <h2>Yêu cầu quảng cáo từ <b>Nhà hàng</b></h2>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Ảnh</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listSlider}" var="s">
                            <c:if test="${s.statusName.equals('Đang chờ xác nhận')}">
                                <tr>
                                    <td>${s.sliderId}</td>
                                    <td class="title" data-title>${s.sliderTitle}</td>
                                    <td>
                                        <img src="img/${s.imageURL}" alt="Không thể tải ảnh">
                                    </td>
                                    <td>${s.statusName}</td>
                                    <td>
                                        <a href="changeStatusSlider?changeStatus=${3}&sliderId=${s.sliderId}" class="btn btn-green" title="Xác nhận"><i class="fas fa-check"></i></a>
                                        <a href="changeStatusSlider?changeStatus=${2}&sliderId=${s.sliderId}" class="btn btn-red" title="Từ chối" onclick="confirmDelete(event)"><i class="fas fa-times"></i></a>
                                    </td>
                                </tr>
                            </c:if>
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

                                            function confirmDelete(event) {
                                                event.preventDefault();
                                                var confirmAction = confirm("Bạn có muốn từ chối yêu cầu này không?");
                                                if (confirmAction) {
                                                    window.location.href = event.target.closest('a').href;
                                                }
                                            }
        </script>
    </body>
</html>