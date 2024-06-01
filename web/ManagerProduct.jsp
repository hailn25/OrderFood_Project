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
                <a class="navbar-brand" href="ManagerDashboard.jsp">
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

                        <c:if test="${sessionScope.account.roleId == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManagerDashboard.jsp">
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
                                <a class="nav-link active" href="managerProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm
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

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>sản phẩm</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="managerAddProduct"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm sản phẩm mới</span></a>
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
                                <!--<td>${o.price}</td>-->
                                <td><p><fmt:formatNumber value="${o.price}" type="currency" currencyCode="VND" /></p></td>
                                <td>
                                    <img src="img/${o.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td>
                                    <a href="loadProduct?pid=${o.productId}&cid=${o.categoryId}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                    <a href="deleteProduct?pid=${o.productId}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <a href="managerAddProduct" class="btn btn-primary btn-block text-uppercase mb-3">Thêm sản phẩm mới</a>
        </div>

        <!-- Edit Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Thêm sản phẩm mới</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">					
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Quantity</label>
                                <input name="stock" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" required></textarea>
                            </div>
                            <div class="form-group">
                                <label>Category</label>
                                <select name="category" class="form-select" aria-label="Default select example">
                                    <c:forEach items="${listC}" var="o">
                                        <option value="${o.categoryId}">${o.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <!--cai nay cua ProGear-->
        <script src="js/manager_1.js" type="text/javascript"></script>

        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
        <script>
            new DataTable('#example');
        </script>
    </body>
</html>