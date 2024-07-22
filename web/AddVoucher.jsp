<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>4FOODHD</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <!-- jQuery UI Datepicker -->
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css">
        <!-- Bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min_1.css">
        <!-- Custom Styles -->
        <link rel="stylesheet" href="css/templatemo-style.css">
    </head>
    <body style="background-color: #F6F6F6">


        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Thêm mã giảm giá mới</h2>
                            </div>
                        </div>

                        <form action="addVoucher" method="post" enctype="multipart/form-data">
                            <div class="row tm-edit-product-row">
                                <div class="col-md-6">
                                    <c:if test="${not empty error}">
                                        <div id="error-message" class="alert alert-danger mt-3">${error}</div>
                                    </c:if>
                                    <div class="form-group mb-3">
                                        <label for="voucherName">Mã giảm giá</label>
                                        <input id="voucherName" name="voucherName" type="text" required class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Nội dung</label>
                                        <input id="description" name="description" type="text" required class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Giảm giá </label>
                                        <input id="discount" name="discount" type="number" required class="form-control validate" />
                                    </div>

                                    <div class="form-group mb-3">
                                        <label for="releaseDate">Ngày phát hành</label>
                                        <input id="releaseDate" name="releaseDate" type="date" required class="form-control validate" />

                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group mb-3">
                                        <label for="quantity">Số lượng</label>
                                        <input id="quantity" name="quantity" type="number" required class="form-control validate" />
                                    </div>
                                  
                                     <div class="form-group mb-3">
                                        <label for="status">Loại Voucher</label><br>
                                        <input type="radio" id="status1" name="voucherCategoryId" value="1">
                                        <label for="status1">FreeShip</label><br>
                                        <input type="radio" id="status0" name="voucherCategoryId" value="0">
                                        <label for="status0">Voucher Nhà Hàng </label>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="finishDate">Ngày kết thúc</label>
                                        <input id="finishDate" name="finishDate" type="date" required class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="status">Trạng thái</label><br>
                                        <input type="radio" id="status1" name="status" value="1">
                                        <label for="status1">Đang hiển thị</label><br>
                                        <input type="radio" id="status0" name="status" value="0">
                                        <label for="status0">Ẩn</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <input type="button" class="btn btn-primary btn-block text-uppercase" value="Huỷ bỏ" onclick="window.history.back();" />
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
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>