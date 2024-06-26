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
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

        <jsp:include page="Header.jsp"></jsp:include>

            <!-- Navbar End -->

            <!-- Modal Search Start -->
            <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Search by keyword</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body d-flex align-items-center">
                            <div class="input-group w-75 mx-auto d-flex">
                                <input type="search" class="form-control p-3" placeholder="keywords" aria-describedby="search-icon-1">
                                <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Search End -->

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
                    <h1 class="mb-4">Thông tin khách hàng</h1>
                    <div class="row g-5">
                        <div class="col-md-12 col-lg-6 col-xl-7">
                            <form action="checkout" method="post" onsubmit="return validateForm()">
                                <div id="errorMessage" style="color: red;"></div>

                                <div class="form-item">
                                    <label class="form-label my-3">Họ và Tên <sup>*</sup></label>

                                    <input type="text" class="form-control" name="name" value="${account.name}">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Địa chỉ <sup>*</sup></label>
                                <input type="text" class="form-control" name="address" value="${account.address}">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Email <sup>*</sup></label>
                                <input type="email" class="form-control" name="email" value="${account.email}">
                            </div>
                            <div class="form-item">
                                <label class="form-label my-3">Số điện thoại <sup>*</sup></label>
                                <input type="tel" class="form-control" name="phone" value="${account.phone}">
                            </div>
                            <div class="form-check my-3">
                                <input class="form-check-input" type="checkbox" id="Address-1" name="Address" value="Address" onchange="redirectToCheckout()">
                                <label class="form-check-label" for="Address-1">Ship to a different address?</label>
                            </div>


                            <textarea name="note" class="form-control" spellcheck="false" cols="30" rows="11" placeholder="Ghi chú (thêm)"></textarea>
                    </div>
                    <div class="col-md-12 col-lg-6 col-xl-5">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Products</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="o" value="${sessionScope.cart}" />
                                    <c:forEach var="i" items="${o.items}">
                                        <tr>
                                            <th scope="row">
                                                <div class="d-flex align-items-center mt-2">
                                                    <img src="img/${i.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="Không thể tải ảnh">
                                                </div>
                                            </th>
                                            <td class="py-5">${i.product.name}</td>
                                            <td class="py-5">
                                                <fmt:formatNumber value="${i.price}" maxFractionDigits="1" />
                                            </td>
                                            <td class="py-5">${i.quantity}</td>
                                            <td class="py-5">
                                                <fmt:formatNumber value="${i.quantity * i.price}" maxFractionDigits="2" />
                                                <input type="hidden"value="${i.quantity * i.price}" />
                                            </td>


                                        </tr>
                                    </c:forEach>

                                    <tr>
                                        <th scope="row"></th>
                                        <td class="py-5"></td>
                                        <td class="py-5"></td>
                                        <td class="py-5">
                                            <p class="mb-0 text-dark py-3">Subtotal</p>
                                        </td>
                                        <td class="py-5">
                                            <div class="py-3 border-bottom border-top">
                                                <c:set var="subtotal" value="0" />
                                                <c:forEach var="i" items="${o.items}">
                                                    <c:set var="subtotal" value="${subtotal + (i.quantity * i.price)}" />
                                                </c:forEach>
                                                <fmt:formatNumber value="${subtotal}" maxFractionDigits="2" />
                                                <input type="hidden" name="cost" value="<fmt:formatNumber value="${subtotal}" maxFractionDigits="2" />" />
                                            </div>

                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row"></th>
                                        <td class="py-5">
                                            <p class="mb-0 text-dark py-4">Shipping</p>
                                        </td>
                                        <td colspan="3" class="py-5">
                                            <div class="form-check text-start">
                                                <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-1" name="Shipping-1" value="Shipping">
                                                <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                            </div>
                                            <div class="form-check text-start">
                                                <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-2" name="Shipping-1" value="Shipping">
                                                <label class="form-check-label" for="Shipping-2">Flat rate: $15.00</label>
                                            </div>
                                            <div class="form-check text-start">
                                                <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-3" name="Shipping-1" value="Shipping">
                                                <label class="form-check-label" for="Shipping-3">Local Pickup: $8.00</label>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th scope="row"></th>
                                        <td class="py-5">
                                            <p class="mb-0 text-dark text-uppercase py-3">TOTAL</p>
                                        </td>
                                        <td class="py-5"></td>
                                        <td class="py-5"></td>
                                        <td class="py-5">
                                            <div class="py-3 border-bottom border-top">
                                                <p class="mb-0 text-dark">$135.00</p>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                            <div class="col-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="cod" name="payment" value="cod" onclick="updatePaymentInfo('COD')" checked>
                                    <label class="form-check-label" for="cod">Ship COD</label>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="vnpay" name="payment" value="vnpay" onclick="updatePaymentInfo('VNPay')">
                                    <label class="form-check-label" for="vnpay">Thanh toán bằng VNPay</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-4 text-center align-items-center justify-content-center pt-4">
                            <input type="hidden" name="cost" value="${subtotal}" />
                            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="submit">Mua Hàng</button>
                        </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Checkout Page End -->

    <script>
        function validateForm() {
            var email = document.getElementsByName("email")[0];
            var fullname = document.getElementsByName("name")[0];
            var phonenumber = document.getElementsByName("phone")[0];
            var address = document.getElementsByName("address")[0];

            if (!fullname.value.trim()) {
                document.getElementById("errorMessage").innerHTML = "Vui lòng điền tên của bạn";
                return false;
            }

            var emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
            if (!email.value.match(emailPattern)) {
                document.getElementById("errorMessage").innerHTML = "Vui lòng nhập đúng định dạng email";
                return false;
            }

            if (phonenumber.value.length != 10) {
                document.getElementById("errorMessage").innerHTML = "Số điện thoại phải có 10 số";
                return false;
            }

            if (!address.value.trim()) {
                document.getElementById("errorMessage").innerHTML = "Vui lòng điền đúng địa chỉ";
                return false;
            }

            return true;
        }

        function updatePaymentInfo(paymentMethod) {
            var paymentInfo = document.getElementById("paymentInfo");
            var vnpayButton = document.getElementById("vnpay-button");
            var codButton = document.getElementById("cod-button");
            if (paymentMethod == 'COD') {
                paymentInfo.innerHTML = "Nhận hàng thanh toán";
                vnpayButton.style.display = "none";
                codButton.style.display = "block";
            } else if (paymentMethod == 'VNPay') {
                paymentInfo.innerHTML = "Quý khách chuyển khoản trước";
                vnpayButton.style.display = "block";
                codButton.style.display = "none";
            }
        }
    </script>
    <script>
        function redirectToCheckout() {
            var checkbox = document.getElementById("Address-1");
            if (checkbox.checked) {
                window.location.href = "Checkout_1.jsp";
            }
        }
    </script>

    <jsp:include page="Footer.jsp"></jsp:include>

    <!-- Back to Top -->
    <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i class="fa fa-arrow-up"></i></a>

    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>
