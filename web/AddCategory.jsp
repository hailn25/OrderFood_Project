<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Edit Product - Dashboard Admin Template</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
        <!-- http://api.jqueryui.com/datepicker/ -->
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->
    </head>

    <body style="background-color: #F6F6F6">

        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="Dashboard.jsp">
                    <h1 class="tm-site-title mb-0">Admin</h1>
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
                        <li class="nav-item">
                            <a class="nav-link" href="Dashboard.jsp">
                                <i class="fas fa-tachometer-alt"></i> Thống kê
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="managerCategory">
                                <i class="far fa-file-alt"></i> Loại sản phẩm
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="managerProduct">
                                <i class="fas fa-shopping-cart"></i> Sản phẩm
                            </a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" href="managerAccount">
                                <i class="far fa-user"></i> Tài khoản
                            </a>
                        </li>

                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link d-block" href="Login.jsp">
                                <b>Đăng xuất</b>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>


        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-6">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Thêm loại sản phẩm mới</h2>
                            </div>
                        </div>
                        <form action="addCategory" method="post">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-12 col-lg-12 col-md-12">
                                    <div class="form-group mb-3">
                                        <label for="name">Tên loại sản phảm</label>
                                        <input id="name" name="name" type="text" required" class="form-control validate" />
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 ">
                                    <input type="button" class="btn btn-primary btn-block text-uppercase" value="Huỷ bỏ" onclick="window.history.back();" />
                                </div>
                                <div class="col-xl-6 col-lg-6 ">
                                    <input type="submit" class="btn btn-primary btn-block text-uppercase" value="Tạo ngay" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>


     

        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
        <!--        <script>
            $(function () {
                $("#expire_date").datepicker({
                    defaultDate: "10/22/2020"
                });
            });
                </script>-->
    </body>
</html>
