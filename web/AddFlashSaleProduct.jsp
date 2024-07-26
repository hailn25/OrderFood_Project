<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
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
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Thêm sản phẩm FlashSale</h2>
                            </div>
                        </div>
                        <form action="addFlashSaleProduct" method="post" enctype="multipart/form-data" class="tm-edit-product-form">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <c:if test="${not empty errorDate}">
                                        <div id="error-message" class="alert alert-danger mt-3">${errorDate}</div>
                                    </c:if>
                                    <div>
                                        <input id="productId" name="productId" type="hidden" value="${productId}" class="form-control validate" />
                                        <input id="OldImage" name="OldImage" type="hidden" value="${detail.imageURL}" class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label>Tên sản phẩm</label>
                                        <input id="productName" name="productName" type="text" required value="${productName}" class="form-control read-only" />
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="date">Ngày</label>
                                        <input id="date" name="date" type="date" required class="form-control validate" min="" />
                                    </div>
                                    <script>
                                        // Lấy ngày hôm nay
                                        var today = new Date();
                                        // Tăng ngày hiện tại lên một ngày để có ngày mai
                                        var tomorrow = new Date(today);
                                        tomorrow.setDate(tomorrow.getDate() + 1);
                                        // Chuyển ngày mai thành định dạng YYYY-MM-DD
                                        var tomorrowStr = tomorrow.toISOString().split('T')[0];
                                        // Thiết lập thuộc tính min của input với id là date
                                        document.getElementById('date').setAttribute('min', tomorrowStr);
                                    </script>
                                    <div class="form-group mb-3">
                                        <label for="timeFrame">Khung giờ</label>
                                        <select style="color: white" class="custom-select tm-select-accounts" name="timeFrame" required>
                                            <option value="1">10h đến 13h</option>
                                            <option value="2">13h đến 16h</option>
                                            <option value="3">16h đến 19h</option>
                                            <option value="4">19h đến 22h</option>
                                        </select>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="discount">% Giảm giá</label>
                                        <div class="input-group">
                                            <input id="discount" name="discount" type="number" min="1" max="99" required class="form-control validate" title="Nhập giá trị từ 1 đến 99" />
                                            <div class="input-group-append">
                                                <span class="input-group-text">%</span>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="form-group mb-3">
                                        <label>Số lượng trong kho</label>
                                        <input id="stock" name="stock" type="text" required value="${stock}" class="form-control read-only" />
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="quantity">Số lượng muốn FlashSale</label>
                                        <input id="quantity" name="quantity" type="number" min="1" max="${stock}" required class="form-control validate" title="Nhập giá trị từ 1 đến ${stock}" />
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <!-- Khung chứa hình ảnh sản phẩm -->
                                    <div class="tm-product-img-edit mx-auto">
                                        <img style="width: 350px; height: 350px" id="currentImage" src="img/${imageURL}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto" style="color: white">
                                    </div>
                                    <!-- Khung chứa nút chọn ảnh -->
                                    <div class="custom-file mt-3 mb-3">
                                        <input id="fileInput" name="image" type="file" style="display:none;" onchange="previewImage(event);" />
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
                                    <a href="managerFlashSaleProduct" class="btn btn-primary btn-block text-uppercase">Huỷ bỏ</a>
                                </div>
                                <div class="col-6">
                                    <input type="submit" class="btn btn-primary btn-block text-uppercase" value="Thêm ngay" />
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



