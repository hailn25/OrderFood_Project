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
                                <a class="nav-link active" href="managerOpenProduct">
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
                            <!--                            <li class="nav-item">
                                                            <a class="nav-link active" href="managerOrderOfCustomer">
                                                                <i class="far fa-file-alt"></i> Quản lý đơn hàng
                                                            </a>
                                                        </li>-->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="far fa-file-alt"></i>
                                    <span> Quản lý đơn hàng <i class="fas fa-angle-down"></i> </span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="managerOrderOfCustomer_6">Đơn hàng đang chờ xác nhận của nhà hàng</a>
                                    <a class="dropdown-item" href="managerOrderOfCustomer_1">Đơn hàng đang chờ xác nhận của Shipper</a>
                                    <a class="dropdown-item" href="managerOrderOfCustomer_2">Đơn hàng đang giao</a>
                                    <a class="dropdown-item" href="managerOrderOfCustomer_3">Đơn hàng đã giao thành công</a>
                                    <a class="dropdown-item" href="managerOrderOfCustomer_4">Đơn hàng đã bị huỷ</a>
                                    <a class="dropdown-item" href="managerOrderOfCustomer_5">Đơn hàng đã bị shipper huỷ</a>
                                </div>
                            </li>

                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="#Profile.jsp">
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
                            <h2>Quản lý sản phẩm<b> đang bán</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="managerAddOpenProduct"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm sản phẩm mới</span></a>
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
                            <th>Ảnh</th>
                            <th>Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listP}" var="o">
                            <tr>
                                <td>${o.productId}</td>
                                <td>${o.name}</td>
                                <td>${o.quantity}</td>
                                <td><fmt:formatNumber value="${o.price}" type="number" minFractionDigits="0" maxFractionDigits="0" /> đ</td>
                                <td>
                                    <img src="img/${o.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td>
                                    <a href="loadOpenProduct?pid=${o.productId}&cid=${o.categoryId}&status=${o.status}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Chỉnh sửa sản phẩm">&#xE254;</i></a>
                                    <a href="closeProduct?pid=${o.productId}" onclick="confirmDelete(event)" class="delete" data-toggle="modal"><i class="material-symbols-outlined" data-toggle="tooltip" title="Ẩn sản phẩm" style="color: red">&#xe8f4;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="managerAddOpenProduct" class="btn btn-primary btn-block text-uppercase mb-3">Thêm sản phẩm mới</a>
        </div>

        <!--cai nay cua ProGear-->
        <script src="js/manager_1.js" type="text/javascript"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
        
      
        
        <script src="js/bootstrap.min.js"></script>
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

        
    </body>
</html>
