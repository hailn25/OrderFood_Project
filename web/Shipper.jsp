<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css">
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">
    </head>
    <body id="reportsPage" style="background-color: #F6F6F6">
        <div class="" id="home">
            <nav class="navbar navbar-expand-xl">
                <div class="container h-100">
                    <a class="navbar-brand" href="Shipper.jsp">
                        <h1 class="tm-site-title mb-0">Shipper</h1>
                    </a>
                    <button class="navbar-toggler ml-auto mr-0" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="fas fa-bars tm-nav-icon"></i>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mx-auto h-100">
                            <c:if test="${sessionScope.account.roleId == 3}">
                                <li class="nav-item">
                                    <a class="nav-link active" href="managerShipper">
                                        <i class="fas fa-clipboard-check"></i>Đơn hàng đợi xử lý
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 3}">
                                <li class="nav-item">
                                    <a class="nav-link" href="managerShipperSuccess">
                                        <i class="fas fa-shipping-fast"></i> Đơn hàng đã xác nhận
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 3}">
                                <li class="nav-item">
                                    <a class="nav-link" href="managerShipperFinish">
                                        <i class="fas fa-thumbs-up"></i>  Đơn hàng đã giao thành công 
                                    </a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.account.roleId == 3}">
                                <li class="nav-item">
                                    <a class="nav-link " href="managerShipperCancel">
                                        <i class="fas fa-trash-alt"></i> Đơn hàng bị hủy
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <c:if test="${sessionScope.account == null}">
                                    <a class="nav-link d-block" href="Login.jsp">
                                        <b>Đăng nhập</b>
                                    </a>
                                </c:if>
                                <c:if test="${sessionScope.account != null}">
                                    <a class="nav-link d-block" href="logout">
                                        <b>Đăng xuất</b>
                                    </a>
                                </c:if>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>đơn hàng </b></h2>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Stt</th>
                            <th>Họ và tên</th>
                            <th>Số điện thoại</th>
                            <th>Địa chỉ</th>
                            <th>Ghi chú</th>
                            <th>Ngày đặt</th>
                            <th>Tổng tiền </th>
                            <th>Trạng thái đơn hàng </th>
                            <th>Hoạt Động </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="o">
                            <tr>
                                <td>${o.orderId}</td>
                                <td>${o.name}</td>
                                <td>${o.phone}</td>
                                <td>${o.address}</td>
                                <td>${o.note}</td>
                                <td>${o.createDate}</td>
                                <td>${o.totalMoney}</td>
                                <td>
                                    ${o.status}
                                </td>

                                <td>
                                    <a href="managerActions?action=accept&oid=${o.orderId}" class="accept" data-toggle="modal"><i class="material-symbols-outlined" data-toggle="tooltip" title="Xác nhận đơn hàng">&#xe5ca;</i></a>

                                    <a href="managerActions?action=refuse&oid=${o.orderId}" class="refuse" data-toggle="modal"><i class="material-symbols-outlined" data-toggle="tooltip" title="Không chấp nhận " style="color: red;">&#xe5cd;</i></a>

                                    <a href="viewOrderByShipper?action=view&oid=${o.orderId}" class="view" data-toggle="modal"><i class="material-symbols-outlined" data-toggle="tooltip" title="Xem chi tiết đơn hàng ">&#xe8f4;</i></a>

                                </td>

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <script src="js/manager_1.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
        <script>
            new DataTable('#example');
        </script>
    </body>
</html>
