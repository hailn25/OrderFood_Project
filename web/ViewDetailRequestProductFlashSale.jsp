<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>4FOODHD</title>
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
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chi tiết yêu cầu sản phẩm Flash Sale</h2>
                            </div>
                        </div>
                        <form enctype="multipart/form-data" class="tm-edit-product-form">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">

                                    <div class="form-group mb-3">
                                        <label for="restaurantName">Tên sản phẩm</label>
                                        <input id="restaurantName" name="restaurantName" type="text" required value="${detail.name}" class="form-control read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="voucherName">Khung thời gian</label>
                                        <c:set var="timeFrameDisplay">
                                            <c:choose>
                                                <c:when test="${detail.timeFrame == 1}">
                                                    10h --> 13h
                                                </c:when>
                                                <c:when test="${detail.timeFrame == 2}">
                                                    13h --> 16h
                                                </c:when>
                                                <c:when test="${detail.timeFrame == 3}">
                                                    16h --> 19h
                                                </c:when>
                                                <c:when test="${detail.timeFrame == 4}">
                                                    19h --> 22h
                                                </c:when>
                                            </c:choose>
                                        </c:set>

                                        <input id="voucherName" name="voucherName" type="text" required value="${timeFrameDisplay}" class="form-control read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="description">Mô tả</label>
                                        <textarea class="form-control validate tm-small read-only" rows="5" name="description" required>${detail.description}</textarea>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="quantity">Số lượng</label>
                                            <input id="quantity" name="quantity" type="text" required value="${detail.quantity}" class="form-control read-only" readonly/>
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="discount">Giảm giá</label>
                                            <c:set var="formattedDiscount">
                                                <fmt:formatNumber value="${detail.discount * 100} " type="number" />
                                            </c:set>
                                            <input id="discount" name="discount" type="text" required value="${formattedDiscount}%" class="form-control read-only" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="releaseDate">Ngày Flash Sale</label>
                                        <fmt:formatDate value="${detail.date}" pattern="dd/MM/yyyy" var="formattedReleaseDate" />
                                        <input readonly="" id="releaseDate" name="releaseDate" type="text" required value="${formattedReleaseDate}" class="form-control validate read-only" />
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <!-- Khung chứa hình ảnh sản phẩm -->
                                    <div class="tm-product-img-edit mx-auto">
                                        <img src="img/${detail.imageURL}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto" style="color: white">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <a href="managerProductFlashSale" class="btn btn-primary btn-block text-uppercase">Trở lại</a>
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
