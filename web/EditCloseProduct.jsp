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

        <!--        <nav class="navbar navbar-expand-xl">
                    <div class="container h-100">
                        <a class="navbar-brand" href="Dashboard.jsp">
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
                <a class="nav-link" href="Dashboard.jsp">
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
</nav>-->


        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chỉnh sửa sản phẩm</h2>
                            </div>
                        </div>
                        <form action="editCloseProduct" method="post" enctype="multipart/form-data" class="tm-edit-product-form">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <c:if test="${not empty error}">
                                        <div id="error-message" class="alert alert-danger mt-3">${error}</div>
                                    </c:if>
                                    <div>
                                        <input id="id" name="id" type="hidden" value="${detail.productId}" class="form-control validate" />
                                        <input id="OldImage" name="OldImage" type="hidden" value="${detail.imageURL}" class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Tên sản phẩm</label>
                                        <input id="name" name="name" type="text" required value="${detail.name}" class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Mô tả</label>
                                        <textarea class="form-control validate tm-small" rows="5" name="description" required>${detail.description}</textarea>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="category">Loại sản phẩm</label>
                                        <select class="custom-select tm-select-accounts" name="category" required>
                                            <c:forEach items="${listC}" var="o">
                                                <option value="${o.categoryId}" ${o.categoryId == cid ? "selected" : ""}>${o.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="category">Trạng thái</label>
                                        <select class="custom-select tm-select-accounts" name="status" required>
                                            <!--                                            <option value="1">Bật</option>
                                                                                        <option value="0">Tắt</option>-->
                                            <option value="true" ${status == true ? "selected" : ""}>Bán hàng</option>
                                            <option value="false" ${status == false  ? "selected" : ""}>Ẩn</option>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="price">Đơn giá</label>
                                            <input id="price" name="price" type="text" required value="${detail.price}" class="form-control validate" />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="quantity">Số lượng</label>
                                            <input id="quantity" name="quantity" type="text" required value="${detail.quantity}" class="form-control validate" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <!-- Khung chứa hình ảnh sản phẩm -->
                                    <div class="tm-product-img-edit mx-auto">
                                        <img id="currentImage" src="img/${detail.imageURL}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto" style="color: white">
                                    </div>
                                    <!-- Khung chứa nút chọn ảnh -->
                                    <div class="custom-file mt-3 mb-3">
                                        <input id="fileInput" name="image" type="file" style="display:none;" onchange="previewImage(event);" />
                                        <input type="button" class="btn btn-primary btn-block mx-auto text-uppercase" value="Chọn ảnh" onclick="document.getElementById('fileInput').click();" />
                                    </div>
                                </div>

                                <script>
                                    function previewImage(event) {
                                        var input = event.target;
                                        var reader = new FileReader();
                                        reader.onload = function () {
                                            var dataURL = reader.result;
                                            var output = document.getElementById('currentImage');
                                            output.src = dataURL;
                                            output.style.display = 'block'; // Hiển thị ảnh mới
                                        };
                                        if (input.files && input.files[0]) {
                                            reader.readAsDataURL(input.files[0]);
                                        }
                                    }
                                </script>


                                <div class="col-6">
                                    <a href="managerCloseProduct" class="btn btn-primary btn-block text-uppercase">Huỷ bỏ</a>

                                </div>
                                <div class="col-6">
                                    <input type="submit" class="btn btn-primary btn-block text-uppercase" value="Cập nhật ngay" />
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
