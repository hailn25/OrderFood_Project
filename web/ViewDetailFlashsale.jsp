<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chi tiết sản phẩm FlashSale</h2>
                            </div>
                        </div>
                        <form enctype="multipart/form-data" class="tm-edit-product-form">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">

                                    <div class="form-group mb-3">
                                        <label for="name">Tên sản phẩm</label>
                                        <input id="name" name="name" type="text" required value="${detail.productName}" class="form-control read-only" readonly/>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="date">Ngày</label>
                                            <input readonly="" id="date" name="date" type="text" required value="<fmt:formatDate value='${detail.date}' pattern='dd-MM-yyyy' />" class="form-control validate read-only" />
                                        </div>

                                        <c:choose>
                                            <c:when test="${detail.timeFrame == 1}">
                                                <c:set var="formattedTimeFrame" value="10h --> 13h" />
                                            </c:when>
                                            <c:when test="${detail.timeFrame == 2}">
                                                <c:set var="formattedTimeFrame" value="13h --> 16h" />
                                            </c:when>
                                            <c:when test="${detail.timeFrame == 3}">
                                                <c:set var="formattedTimeFrame" value="16h --> 19h" />
                                            </c:when>
                                            <c:when test="${detail.timeFrame == 4}">
                                                <c:set var="formattedTimeFrame" value="19h --> 22h" />
                                            </c:when>
                                            <c:otherwise>
                                                <c:set var="formattedTimeFrame" value="Unknown" />
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="timeFrame">Khung giờ</label>
                                            <input readonly="" id="timeFrame" name="timeFrame" type="text" required value="${formattedTimeFrame}" class="form-control validate read-only" />
                                        </div>

                                    </div>
                                    <div class="row">
                                        <c:set var="formattedDiscount" value="${detail.discount * 100}" />

                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="discount">Giá gốc</label>
                                            <input readonly="" id="discount" name="discount" type="text" required value="<fmt:formatNumber value='${price}' type='number' minFractionDigits='0' maxFractionDigits='0'/> VNĐ" class="form-control validate read-only" />
                                        </div>


                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="salePrice">Giá sau khi giảm</label>
                                            <input readonly="" id="salePrice" name="salePrice" type="text" required value="<fmt:formatNumber value='${detail.salePrice}' type='number' minFractionDigits='0' maxFractionDigits='0'/> VNĐ" class="form-control validate read-only" />
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="stock">Số lượng kho</label>
                                            <input readonly="" id="stock" name="stock" type="text" required value="${stock}" class="form-control validate read-only" />
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="quantity">Số lượng FlashSale</label>
                                            <input readonly="" id="quantity" name="quantity" type="text" required value="${detail.quantity}" class="form-control validate read-only" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <!-- Khung chứa hình ảnh sản phẩm -->
                                    <div class="tm-product-img-edit mx-auto">
                                        <img style="width: 350px; height: 350px" src="img/${detail.imageURL}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto" style="color: white">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <a href="managerFlashSaleProduct" class="btn btn-primary btn-block text-uppercase">Trở lại</a>
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



