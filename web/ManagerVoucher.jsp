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
                                    <a class="nav-link " href="managerBlog">
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
                                    <a class="nav-link active" href="managerVoucher">
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
        </div>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>mã giảm giá </b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="AddVoucher.jsp"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm bài viết mới</span></a>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Stt</th>
                            <th>Mã giảm giá</th>
                            <th>Nội dung</th>
                            <th>Số lượng</th>
                            <th>Ngày phát hành</th>
                            <th>Ngày  kết thúc</th>
                            <th>Trạng thái  </th>
                            <th>Tác vụ </th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${list}" var="v">
                            <tr>

                                <td>${v.voucherId}</td>
                                <td>${v.voucherName}</td>
                                <td>${v.description}</td>
                                <td>${v.quantity}</td>
                                <td>${v.releaseDate}</td>
                                <td>${v.finishDate}</td>
                                <td style="color: ${v.status == 1 ? 'green' : 'red'};">
                                    ${v.status == 1 ? "Đang hiển thị" : "Đang ẩn"}
                                </td>


                                <td>
                                    <a href="editVoucher?vid=${v.voucherId}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Chỉnh sửa">&#xE254;</i></a>

                                    <a href="deleteVoucher?vid=${v.voucherId}" class="delete" data-toggle="modal" onclick="confirmDelete(event)"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
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
