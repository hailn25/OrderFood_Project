<%-- 
    Document   : Checkout
    Created on : May 26, 2024, 3:03:05 PM
    Author     : ADMIN
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Fruitables - Vegetable Website Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Google Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">
        <!-- Icon Fonts -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <!-- Libraries Stylesheets -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <!-- Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <!-- Single Page Header start -->
            <div class="container-fluid page-header py-5">
                <h1 class="text-center text-white display-6">Checkout</h1>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-white">Checkout</li>
                </ol>
            </div>
            <!-- Single Page Header End -->
            <!-- Checkout Page Start -->
            <div class="container-fluid py-5">
                <div class="container py-5">
                    <div class="row">
                        <!-- Customer Information -->
                        <div class="col-md-6 mb-4">
                            <h2>Thông tin khách hàng</h2>
                            <form name="checkoutForm" action="checkout" method="post" onsubmit="return validateForm()">
                                <div id="errorMessage" style="color: red;"></div>
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <label class="form-label my-3">Họ và Tên <sup>*</sup></label>
                                        <input type="text" class="form-control" name="name" value="${account.name}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Địa chỉ <sup>*</sup></label>
                                    <input type="text" class="form-control" name="address" value="${account.address}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Email <sup>*</sup></label>
                                    <input type="email" class="form-control" name="email" value="${account.email}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Số điện thoại <sup>*</sup></label>
                                    <input type="tel" class="form-control" name="phone" value="${account.phone}">
                                </div>
                                <div class="col-md-12">
                                    <div class="form-check my-3">
                                        <input class="form-check-input" type="checkbox" id="Address" name="Address" value="Address" onchange="redirectToCheckout()">
                                        <label class="form-check-label" for="Address">Giao hàng đến địa chỉ khác?</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <textarea name="note" class="form-control" spellcheck="false" cols="30" rows="5" placeholder="Ghi chú"></textarea>
                                </div>
                            </div>
                            <button class="btn btn-primary w-100 mt-3" type="submit">Đặt hàng</button>

                    </div>
                    <!-- Order Summary -->
                    <div class="col-md-5">
                        <h2>Giỏ hàng</h2>
                        <div class="table-responsive">
                            <table class="table" ">
                                <thead>
                                    <tr>
                                        <th scope="col">Sản phẩm</th>
                                        <th scope="col">Tên</th>
                                        <th scope="col">Giá</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Tổng</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:set var="cartItems" value="${sessionScope.cart.items}" />
                                    <c:forEach var="item" items="${cartItems}">
                                        <tr>
                                            <td><img src="img/${item.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="Không thể tải ảnh"></td>
                                            <td>${item.product.name}</td>
                                            <td><fmt:formatNumber value="${item.price * 1000}" maxFractionDigits="1" /></td>
                                            <td>${item.quantity}</td>
                                            <td><fmt:formatNumber value="${item.quantity * item.price * 1000}" maxFractionDigits="2" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:set var="subtotal" value="0" />
                        <c:forEach var="item" items="${sessionScope.cart.items}">
                            <c:set var="subtotal" value="${subtotal + (item.quantity * item.product.price * 1000)}" />
                        </c:forEach>

                        <c:set var="shippingFee" value="30000" />
                        <c:set var="shippingDiscountRate" value="${listFree / 100}" />
                        <c:set var="shippingDiscount" value="${shippingDiscountRate * shippingFee}" />

                        <c:set var="voucherDiscountRate" value="${listVoucherR / 100}" />
                        <c:set var="voucherDiscount" value="${voucherDiscountRate * subtotal}" />

                        <c:set var="total" value="${subtotal + shippingFee - shippingDiscount - voucherDiscount}" />

                        <div class="border p-4 mt-4">
                            <h2 class="mb-4">Tổng thanh toán</h2>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Tổng tiền hàng</span>
                                <span class="text-muted-foreground"><fmt:formatNumber value="${subtotal}" currencySymbol="VND" maxFractionDigits="0" /></span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Phí vận chuyển</span>
                                <span class="text-muted-foreground"><fmt:formatNumber value="${shippingFee}" currencySymbol="VND" maxFractionDigits="0" /></span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Giảm giá phí vận chuyển</span>
                                <span class="text-muted-foreground">- <fmt:formatNumber value="${shippingDiscount}" currencySymbol="VND" maxFractionDigits="0" /></span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Giảm giá voucher</span>
                                <span class="text-muted-foreground">- <fmt:formatNumber value="${voucherDiscount}" currencySymbol="VND" maxFractionDigits="0" /></span>
                            </div>
                            <hr>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Tổng đơn hàng</span>
                                <span class="text-muted-foreground"><fmt:formatNumber value="${total}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                <input type="hidden" name="cost" value="${total}" />


                            </div>
                        </div>


                        <div class="row mt-4">
                            <div class="col-md-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="cod" name="payment" value="cod" onclick="updatePaymentInfo('COD')" checked>
                                    <label class="form-check-label" for="cod">Thanh toán khi nhận hàng</label>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="vnpay" name="payment" value="vnpay" onclick="updatePaymentInfo('VNPay')">
                                    <label class="form-check-label" for="vnpay">VNPay</label>
                                </div>
                            </div>

                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Checkout Page End -->
        <jsp:include page="Footer.jsp"></jsp:include>
        <!-- External JavaScripts -->
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        <script src="js/script.js"></script>
        <!-- Custom JavaScripts -->

    </body>
</html>
