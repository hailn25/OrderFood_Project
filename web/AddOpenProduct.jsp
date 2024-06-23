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
            https://templatemo.com/tm-524-product-admin
        -->
    </head>

    <body style="background-color: #F6F6F6">
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Thêm sản phẩm mới</h2>
                            </div>
                        </div>
                        <form action="addOpenProduct" method="post" enctype="multipart/form-data">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <c:if test="${not empty error}">
                                        <div id="error-message" class="alert alert-danger mt-3">${error}</div>
                                    </c:if>
                                    <div class="form-group mb-3">
                                        <label for="name">Tên sản phẩm</label>
                                        <input id="name" name="name" type="text" required class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Mô tả</label>
                                        <textarea class="form-control validate tm-small" rows="5" name="description" required></textarea>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="category">Loại sản phẩm</label>
                                        <select style="color: white" class="custom-select tm-select-accounts" name="category" required>
                                            <c:forEach items="${listC}" var="o">
                                                <option value="${o.categoryId}">${o.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="category">Trạng thái</label>
                                        <select style="color: white" class="custom-select tm-select-accounts" name="status" required>
                                            <option value="1">Bật</option>
                                            <option value="0">Tắt</option>
                                        </select>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="price">Đơn giá</label>
                                            <input id="price" name="price" type="text" required class="form-control validate" />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="quantity">Số lượng</label>
                                            <input id="quantity" name="quantity" type="text" required class="form-control validate" />
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <div class="tm-product-img-preview mx-auto">
                                        <img id="preview" src="" class="img-fluid d-block mx-auto" style="display:none;"/>
                                    </div>
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
                                            var output = document.getElementById('preview');
                                            output.src = dataURL;
                                            output.style.display = 'block'; // Hiển thị ảnh
                                        };
                                        reader.readAsDataURL(input.files[0]);
                                    }
                                </script>


                                <div class="col-6">
                                    <!--<input type="button" class="btn btn-primary btn-block text-uppercase" value="Huỷ bỏ" onclick="window.history.back();" />-->
                                    <a href="managerOpenProduct" class="btn btn-primary btn-block text-uppercase">Huỷ bỏ</a>
                                </div>
                                <div class="col-6">
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
    </body>
</html>
