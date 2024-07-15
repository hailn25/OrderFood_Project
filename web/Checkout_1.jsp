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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
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
                            <form action="checkout1" method="post" id="checkoutForm" onsubmit="return validateForm()">
                                <div id="errorMessage" style="color: red;"></div>
                                <div class="form-item" style="margin-bottom: 30px">
                                    <label class="form-label my-3">Họ và Tên <sup>*</sup></label>
                                    <input type="text" class="form-control" name="name" value="" id="name">
                                </div>
                                <div class="form-item" style="margin-bottom: 30px">
                                    Giới tính <input type="radio" name="gender" value="Male" checked /> Nam
                                    <input type="radio" name="gender" value="Female" /> Nữ
                                </div>

                                <div class="form-item">
                                    <label class="form-label my-3">Email <sup>*</sup></label>
                                    <input type="text" class="form-control" name="email" value="" id="email">
                                </div>
                                <div class="form-item">
                                    <label class="form-label my-3">Địa chỉ <sup>*</sup></label>
                                    <input type="text" class="form-control" name="address" value="" id="address">
                                </div>
                                <div class="form-item" style="margin-bottom: 30px">
                                    <label class="form-label my-3">Số điện thoại <sup>*</sup></label>
                                    <input type="tel" class="form-control" name="phone" value="" id="phone">
                                </div>
                                <textarea name="note" class="form-control" spellcheck="false" id="note" cols="30" rows="11" placeholder="Ghi chú (thêm)"></textarea>
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
                                            <div class="py-3 border-bottom border-top" id="subtotal">
                                                <c:set var="subtotal" value="0" />
                                                <c:forEach var="i" items="${o.items}">
                                                    <c:set var="subtotal" value="${subtotal + (i.quantity * i.price)}" />
                                                </c:forEach>
                                                <fmt:formatNumber value="${subtotal}" maxFractionDigits="2" />
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
                                                <input type="radio" class="form-check-input bg-primary border-0" id="Shipping-1" name="shipping" value="0" onclick="updateTotal()">
                                                <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                            </div>
                                            <div class="form-check text-start">
                                                <input type="radio" class="form-check-input bg-primary border-0" id="Shipping-2" name="shipping" value="15" onclick="updateTotal()">
                                                <label class="form-check-label" for="Shipping-2">Flat rate: 15.000</label>
                                            </div>
                                            <div class="form-check text-start">
                                                <input type="radio" class="form-check-input bg-primary border-0" id="Shipping-3" name="shipping" value="20" onclick="updateTotal()">
                                                <label class="form-check-label" for="Shipping-3">Local Pickup: 20.000</label>
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
                                            <div class="py-3 border-bottom border-top" id="total">
                                                <fmt:formatNumber value="${subtotal}" maxFractionDigits="2" />
                                            </div>
                                            <!-- Hidden input to store total value -->
                                            <input type="hidden" name="total" id="totalValue" value="${subtotal}">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                            <div class="col-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="Payment-1" name="payment" value="cod" onclick="updatePaymentInfo('COD')" checked>
                                    <label class="form-check-label" for="Payment-1">Thanh toán khi nhận hàng</label>
                                </div>
                            </div>
                        </div>
                        <div class="row g-4 text-center align-items-center justify-content-center border-bottom py-3">
                            <div class="col-12">
                                <div class="form-check text-start my-3">
                                    <input type="radio" class="form-check-input bg-primary border-0" id="Payment-2" name="payment" value="vnpay" onclick="updatePaymentInfo('VNPay')">
                                    <label class="form-check-label" for="Payment-2">Chuyển khoản</label>
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
        <!-- Checkout Page End -->

        <jsp:include page="Footer.jsp"></jsp:include>

            <script>
                window.onload = function () {
                    var msg = "${msg}";
                    if (msg) {
                        document.getElementById("errorMessage").innerHTML = msg;
                    }
                };
                function validateForm() {
                    var errorMessage = document.getElementById("errorMessage");
                    var name = document.getElementById("name").value.trim();
                    var gender = document.querySelector('input[name="gender"]:checked');
                    var address = document.getElementById("address").value.trim();
                    var email = document.getElementById("email").value.trim();
                    var phone = document.getElementById("phone").value.trim();
                    var note = document.getElementById("note").value.trim();
                    var payment = document.querySelector('input[name="payment"]:checked');
                    var shipping = document.querySelector('input[name="shipping"]:checked');
                    var namePattern = /^[a-zA-Z]/;
                    var emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/; // Ensure the email pattern is defined

                    // Name validation
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

                    // Email validation
                    if (!email.match(emailPattern)) {
                        errorMessage.textContent = "Vui lòng nhập đúng định dạng email.";
                        return false;
                    } else if (email.length < 5 || email.length > 60) {
                        errorMessage.textContent = "Email phải có độ dài từ 5 - 60 kí tự.";
                        return false;
                    }

                    // Phone validation
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

                    // Address validation
                    if (address === "") {
                        errorMessage.textContent = "Vui lòng nhập địa chỉ.";
                        return false;
                    } else if (address.length < 5 || address.length > 64) {
                        errorMessage.textContent = "Địa chỉ phải có độ dài từ 5 - 64 kí tự.";
                        return false;
                    }

                    // Gender validation
                    if (!gender) {
                        errorMessage.textContent = "Vui lòng chọn giới tính.";
                        return false;
                    }

                    // Shipping method validation
                    if (!shipping) {
                        errorMessage.textContent = "Vui lòng chọn phương thức giao hàng.";
                        return false;
                    }

                    // Payment method validation
                    if (!payment) {
                        errorMessage.textContent = "Vui lòng chọn phương thức thanh toán.";
                        return false;
                    }

                    // Note validation
                    if (note.length > 60) {
                        errorMessage.textContent = "Ghi chú không được vượt quá 60 kí tự.";
                        return false;
                    }

                    return true;
                }

// Assuming you have a form with an onsubmit attribute
                document.getElementById("myForm").onsubmit = function () {
                    return validateForm();
                };


                function updateTotal() {
                    let subtotal = parseFloat(document.getElementById('subtotal').innerText.replace(/[^0-9.-]+/g, ""));
                    let shipping = parseFloat(document.querySelector('input[name="shipping"]:checked').value);
                    let total = subtotal + shipping;
                    document.getElementById('total').innerText = total.toLocaleString('en-US', {style: 'currency', currency: 'USD'});
                    document.getElementById('totalValue').value = total;
                }

                function updatePaymentInfo(paymentMethod) {
                    var paymentInfo = document.getElementById("paymentInfo");
                    var vnpayButton = document.getElementById("vnpay-button");
                    var codButton = document.getElementById("cod-button");
                    if (paymentMethod === 'COD') {
                        paymentInfo.innerHTML = "Nhận hàng thanh toán";
                        vnpayButton.style.display = "none";
                        codButton.style.display = "block";
                    } else if (paymentMethod === 'VNPay') {
                        paymentInfo.innerHTML = "Quý khách chuyển khoản trước";
                        vnpayButton.style.display = "block";
                        codButton.style.display = "none";
                    }
                }
        </script>


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
