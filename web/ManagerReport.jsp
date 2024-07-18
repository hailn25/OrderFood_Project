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

            .btn-blue {
                background-color: #74C0FC;
                color: white;
            }

            .btn-blue:hover {
                background-color: #58A6FF;
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

            .btn-red {
                background-color: #dc3545;
                color: white;
            }

            .btn-red:hover {
                background-color: #c82333;
                color: white;
            }

            .status-confirmed {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 35px;
                width: 80px;
                border-radius: 8px;
                background-color: #ADFF2F;
                color: white;
            }
            .status-rejected {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 35px;
                width: 80px;
                border-radius: 8px;
                background-color: #FF5557;
                color: white;
            }
            .status-other {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 35px;
                width: 150px;
                border-radius: 8px;
                background-color: #F6DB67;
                color: white;
            }
        </style>
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">

        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="ManagerStaff.jsp">
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
                                <a class="nav-link" href="managerService">
                                    <i class="fas fa-sliders-h"></i> Dịch vụ 
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link active" href="managerReport">
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
                            <h2>Đơn tố cáo nhà hàng từ <b>Khách Hàng</b></h2>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Mô tả</th>
                            <th>Ảnh</th>
                            <th>Ngày</th>
                            <th>Trạng thái</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listReport}" var="r">
                            <tr>
                                <td>${r.reportId}</td>
                                <td>${r.description}</td>
                                <td>
                                    <img src="img/${r.imageURL}" style="object-fit: cover;" alt="Không thể tải ảnh">
                                </td>
                                <td>${r.createDate}</td>
                                <td>
                                    <p class="${r.statusName == 'Xác nhận' ? 'status-confirmed' : r.statusName == 'Từ chối' ? 'status-rejected' : 'status-other'}">
                                        ${r.statusName}
                                    </p>
                                </td>
                                <td>
                                    <a href="loadReport?reportId=${r.reportId}" class="btn btn-blue" title="Xem chi tiết"><i class="far fa-eye" ></i></a>
                                        <c:if test="${r.statusName == 'Đang chờ xác nhận'}">
                                        <a href="changeStatusReport?changeStatus=${3}&reportId=${r.reportId}" class="btn btn-green" title="Xác nhận"  onclick="confirmDelete(event)"><i class="fas fa-check"></i></a>
                                        <a href="changeStatusReport?changeStatus=${2}&reportId=${r.reportId}" class="btn btn-red" title="Từ chối"><i class="fas fa-times"></i></a>
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

                                            function confirmDelete(event) {
                                                event.preventDefault();
                                                var confirmAction = confirm("Bạn có muốn gửi yêu cầu cấm tài khoản không?");
                                                if (confirmAction) {
                                                    window.location.href = event.target.closest('a').href;
                                                }
                                            }
        </script>
    </body>
</html>

