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

            <!-- Single Page Header End -->
            <!-- Checkout Page Start -->
            <div class="container-fluid py-5" style="margin-top: 150px">
                <div class="container py-5">
                    <div class="row">
                        <!-- Customer Information -->
                        <div class="col-md-6 mb-4">
                            <h2>Thông tin khách hàng</h2>
                            <form name="checkoutForm" id="checkoutForm" action="checkout" method="post" onsubmit="return validateForm()">
                                <div id="errorMessage" style="color: red;"></div>
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <label class="form-label my-3">Họ và Tên <sup>*</sup></label>
                                        <input type="text" id="name" class="form-control" name="name" value="${account.name}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Địa chỉ <sup>*</sup></label>
                                    <input type="text" id="address" class="form-control" name="address" value="${account.address}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Email <sup>*</sup></label>
                                    <input type="email" id="email" class="form-control" name="email" value="${account.email}">
                                </div>
                                <div class="col-md-12">
                                    <label class="form-label my-3">Số điện thoại <sup>*</sup></label>
                                    <input type="tel" id="phone" class="form-control" name="phone" value="${account.phone}">
                                </div>

                                <div class="col-12">
                                    <textarea id="note" name="note" class="form-control" spellcheck="false" cols="30" rows="5" placeholder="Ghi chú"></textarea>
                                </div>
                            </div>


                    </div>
                    <!-- Order Summary -->
                    <div class="col-md-6">
                        <h2>Giỏ hàng</h2>
                        <div class="table-responsive">
                            <table class="table">
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
                                            <td><fmt:formatNumber value="${item.price}" maxFractionDigits="1" /></td>
                                            <td>${item.quantity}</td>
                                            <td><fmt:formatNumber value="${item.quantity * item.price}" maxFractionDigits="2" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <c:set var="subtotal" value="0" />
                        <c:forEach var="item" items="${sessionScope.cart.items}">
                            <c:set var="subtotal" value="${subtotal + (item.quantity * item.product.price)}" />
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
                                <span class="text-muted-foreground"><fmt:formatNumber value="${subtotal}" currencySymbol="VND" maxFractionDigits="0" /> VNĐ</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Phí vận chuyển</span>
                                <span class="text-muted-foreground"><fmt:formatNumber value="${shippingFee}" currencySymbol="VND" maxFractionDigits="0" /> VNĐ</span>
                            </div>
                            <c:if test="${shippingDiscount != 0}">
                                <div class="d-flex justify-content-between mb-2">
                                    <span class="text-muted-foreground">Giảm giá phí vận chuyển</span>
                                    <span class="text-muted-foreground">- <fmt:formatNumber value="${shippingDiscount}" currencySymbol="VND" maxFractionDigits="0" /> VNĐ</span>
                                </div>
                            </c:if>


                            <c:if test="${voucherDiscountRate != 0}">
                                <div class="d-flex justify-content-between mb-2">
                                    <span class="text-muted-foreground">Giảm giá voucher</span>
                                    <span class="text-muted-foreground">- <fmt:formatNumber value="${voucherDiscount}" currencySymbol="VND" maxFractionDigits="0" /> VNĐ</span>
                                </div>
                            </c:if>
                            <hr>
                            <div class="d-flex justify-content-between mb-2">
                                <span class="text-muted-foreground">Tổng đơn hàng</span>
                                <span class="text-muted-foreground"><fmt:formatNumber value="${total}" currencySymbol="VND" maxFractionDigits="0" /> VNĐ</span>
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
                        <div style="text-align: center;">
                            <button class="btn btn-primary mt-3" style="width: 150px; display: inline-block;" type="submit">Đặt hàng</button>
                            <button class="btn btn-danger mt-3 ms-3" style="width: 150px; display: inline-block;" type="button" onclick="cancelOrder()">Hủy đơn</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Checkout Page End -->

        <jsp:include page="Footer.jsp"></jsp:include>
<script>
    function cancelOrder() {
        if (confirm("Bạn có chắc chắn muốn hủy đơn hàng?")) {
            resetCart();
            window.location.href = "home";
        }
    }

    function resetCart() {
        fetch('resetCart', {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                console.log('Cart reset successfully.');
            } else {
                console.error('Failed to reset cart.');
            }
        }).catch(error => {
            console.error('Error resetting cart:', error);
        });
    }
</script>

        <script>
                function validateForm() {
                    var errorMessage = document.getElementById("errorMessage");
                    var name = document.getElementById("name").value.trim();
                    var address = document.getElementById("address").value.trim();
                    var email = document.getElementById("email").value.trim();
                    var phone = document.getElementById("phone").value.trim();
                    var note = document.getElementById("note").value.trim();
                    var payment = document.querySelector('input[name="payment"]:checked');
                    var namePattern = /^[a-zA-Z]/;
                    var emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

                    // Reset error message
                    errorMessage.textContent = "";

                    if (name === "") {
                        errorMessage.textContent = "Vui lòng nhập họ và tên.";
                        return false;
                    } else if (!namePattern.test(name)) {
                        errorMessage.textContent = "Họ và tên không được bắt đầu bằng số hoặc kí tự đặc biệt.";
                        return false;
                    } else if (name.length < 5 || name.length > 64) {
                        errorMessage.textContent = "Họ và tên phải có độ dài từ 5 - 64 kí tự.";
                        return false;
                    }

                    if (email === "") {
                        errorMessage.textContent = "Vui lòng nhập email.";
                        return false;
                    } else if (!emailPattern.test(email)) {
                        errorMessage.textContent = "Vui lòng nhập đúng định dạng email.";
                        return false;
                    } else if (email.length < 5 || email.length > 60) {
                        errorMessage.textContent = "Email phải có độ dài từ 5 - 60 kí tự.";
                        return false;
                    }

                    if (phone === "") {
                        errorMessage.textContent = "Vui lòng nhập số điện thoại.";
                        return false;
                    } else if (phone.length !== 10) {
                        errorMessage.textContent = "Số điện thoại phải có 10 số.";
                        return false;
                    } else if (isNaN(phone)) {
                        errorMessage.textContent = "Số điện thoại phải là số.";
                        return false;
                    }

                    if (address === "") {
                        errorMessage.textContent = "Vui lòng nhập địa chỉ.";
                        return false;
                    } else if (address.length < 5 || address.length > 64) {
                        errorMessage.textContent = "Địa chỉ phải có độ dài từ 5 - 64 kí tự.";
                        return false;
                    }

                    if (!payment) {
                        errorMessage.textContent = "Vui lòng chọn phương thức thanh toán.";
                        return false;
                    }

                    if (note.length > 60) {
                        errorMessage.textContent = "Ghi chú không được vượt quá 60 kí tự.";
                        return false;
                    }

                    return true;
                }

        </script>

        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/wow/wow.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/isotope/isotope.pkgd.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>

        <!-- Template Javascript -->

    </body>
</html>
