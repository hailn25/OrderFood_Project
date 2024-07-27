<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>4FOODHD</title>
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

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->

        <!-- Navbar start -->
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

            <!-- Single Page Header End -->

            <!-- Cart Page Start -->
            <div class="container-fluid py-5" style="margin-top: 150px">
                <div class="container py-5">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                    <th scope="col">Hủy</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:set var="o" value="${sessionScope.cart}"/>
                            <c:forEach var="i" items="${o.items}">
                                <tr>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <img src="img/${i.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                        </div>
                                    </td>
                            <input type="hidden" name="productId" value="${i.product.productId}" />
                            <td>
                                <p class="mb-0 mt-4">${i.product.name}</p>
                            </td>
                            <td>
                                <p class="mb-0 mt-4">
                                    <fmt:formatNumber value="${i.price}" maxFractionDigits="0" currencySymbol="VND"/> VNĐ
                                </p>
                            </td>
                            <td>
                                <div class="input-group quantity mt-4" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                            <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="text" class="form-control form-control-sm text-center border-0 quantity-input" value="${i.quantity}" data-product-id="${i.product.productId}" data-price="${i.price}" data-max-quantity=${maxquantity}>
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <p class="mb-0 mt-4 total-price">
                                    <fmt:formatNumber value="${i.quantity * i.price}" maxFractionDigits="0" currencySymbol="VND"/> VNĐ
                                </p>
                            </td>
                            <td>
                                <form id="deleteForm${i.product.productId}" action="process" method="post">
                                    <input type="hidden" name="id" value="${i.product.productId}"/>
                                    <button class="btn btn-md rounded-circle bg-light border mt-4" onclick="confirmDelete(event, 'deleteForm${i.product.productId}')">
                                        <i class="fa fa-times text-danger"></i>
                                    </button>
                                </form>
                            </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <c:choose>
                    <c:when test="${not empty sessionScope.cart and not empty sessionScope.cart.items}">
                        <div class="container mt-5">





                        </div>
                        <div class="row g-4 justify-content-between">
                            <!-- Voucher Section -->
                            <div class="col-md-5">
                                <form action="showVoucher" method="get">
                                    <div class="mt-5">
                                        <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
                                        <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="submit">Sử dụng Voucher</button>
                                    </div>
                                </form>
                            </div>
                            <!-- Cart Total Section -->
                            <div class="col-md-5">
                                <div class="bg-light rounded">
                                    <div class="p-4">

                                        <c:set var="subtotal" value="0" />
                                        <c:forEach var="item" items="${sessionScope.cart.items}">
                                            <c:set var="subtotal" value="${subtotal + (item.quantity * item.product.price)}" />
                                        </c:forEach>

                                        <c:set var="shippingFee" value="30000" />
                                        <c:set var="shippingDiscountRate" value="${listFree / 100}" />
                                        <c:set var="voucherDiscountRate" value="${listVoucherR / 100}" />
                                        <c:set var="shippingDiscount" value="${shippingDiscountRate * shippingFee}" />
                                        <c:set var="voucherDiscount" value="${voucherDiscountRate * subtotal}" />
                                        <c:set var="total" value="${subtotal + shippingFee - shippingDiscount - voucherDiscount}" />




                                        <div class="border p-4 mt-4" style="margin-bottom: 50px">
                                            <h2 class="mb-4">Tổng thanh toán</h2>
                                            <div class="d-flex justify-content-between mb-2">
                                                <span class="text-muted-foreground">Tổng tiền hàng</span>
                                                <span class="text-muted-foreground"><fmt:formatNumber value="${subtotal}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                            </div>
                                            <div class="d-flex justify-content-between mb-2">
                                                <span class="text-muted-foreground">Phí vận chuyển</span>
                                                <span class="text-muted-foreground"><fmt:formatNumber value="${shippingFee}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                            </div>
                                            <c:set var="shippingDiscount" value="${shippingDiscountRate * shippingFee}" />
                                            <c:set var="voucherDiscountRate" value="${listVoucherR / 100}" />


                                            <c:if test="${shippingDiscount != 0}">
                                                <div class="d-flex justify-content-between mb-2">
                                                    <span class="text-muted-foreground">Giảm giá phí vận chuyển</span>
                                                    <span class="text-muted-foreground">- <fmt:formatNumber value="${shippingDiscount}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                                </div>
                                            </c:if>


                                            <c:if test="${voucherDiscountRate != 0}">
                                                <div class="d-flex justify-content-between mb-2">
                                                    <span class="text-muted-foreground">Giảm giá voucher</span>
                                                    <span class="text-muted-foreground">- <fmt:formatNumber value="${voucherDiscount}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                                </div>
                                            </c:if>

                                            <hr>
                                            <div class="d-flex justify-content-between mb-2">
                                                <span class="text-muted-foreground">Tổng đơn hàng</span>
                                                <span class="text-muted-foreground"><fmt:formatNumber value="${total}" currencySymbol="VND" maxFractionDigits="0" /></span>
                                                <input type="hidden" name="cost" value="${total}" />


                                            </div>
                                        </div>
                                        <form action="checkout" method="get">
                                            <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="submit">Mua Hàng</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div style="display: flex; justify-content: center;">
                            <img src="img/cart.png" width="300px" height="250px" alt="Không tìm thấy ảnh"/>

                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>



        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const quantityInputs = document.querySelectorAll('.quantity-input');
                function formatCurrency(amount) {
                    let formatted = amount.toString().replace(/\D/g, '');
                    formatted = formatted.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
                    return formatted + ' VNĐ';
                }


                function calculateShippingDiscount(shippingFee, shippingDiscountRate) {
                    return shippingDiscountRate * shippingFee;
                }

                function calculateVoucherDiscount(subtotal, voucherDiscountRate) {
                    return voucherDiscountRate * subtotal;
                }

                function updateQuantityAndPrice(quantityInput, newQuantity, maxQuantity) {
                    if (newQuantity < 1) {
                        confirmDelete(event, 'deleteForm' + quantityInput.dataset.productId);
                        return;
                    }
                    if (newQuantity > maxQuantity) {
                        alert('Số lượng vượt quá số lượng tối đa có sẵn');
                        return;
                    }

                    quantityInput.value = newQuantity;
                    updatePrice(quantityInput, newQuantity);
                    updateTotal();

                    const productId = quantityInput.dataset.productId;
                    const url = 'process?productId=' + productId + '&quantity=' + newQuantity;

                    fetch(url)
                            .then(response => {
                                if (!response.ok) {
                                    throw new Error('Network response was not ok');
                                }
                                return response.text();
                            })
                            .then(data => {
                                // Handle any response data if necessary
                            })
                            .catch(error => {
                                console.error('Có vấn đề xảy ra trong quá trình fetch:', error);
                            });
                }


                function updateTotal() {
                    let subtotal = 0;
                    const rows = document.querySelectorAll('tbody > tr');
                    rows.forEach(function (row) {
                        const priceElement = row.querySelector('.total-price');
                        subtotal += parseFloat(priceElement.innerText.replace(/\D/g, ''));
                    });

                    // Ensure shipping fee and discounts are correctly set
                    const shippingFee = 30000;
                    const shippingDiscountRate = parseFloat(document.querySelector('.shipping-discount-rate')?.dataset.value) / 100 || 0;
                    const voucherDiscountRate = parseFloat(document.querySelector('.voucher-discount-rate')?.dataset.value) / 100 || 0;

                    const shippingDiscount = calculateShippingDiscount(shippingFee, shippingDiscountRate);
                    const voucherDiscount = calculateVoucherDiscount(subtotal, voucherDiscountRate);
                    const total = subtotal + shippingFee - shippingDiscount - voucherDiscount;

                    document.querySelector('.subtotal').innerText = formatCurrency(subtotal);
                    document.querySelector('.shipping-fee').innerText = formatCurrency(shippingFee);
                    document.querySelector('.shipping-discount').innerText = shippingDiscount ? '- ' + formatCurrency(shippingDiscount) : '';
                    document.querySelector('.voucher-discount').innerText = voucherDiscount ? '- ' + formatCurrency(voucherDiscount) : '';
                    document.querySelector('.cart-total').innerText = formatCurrency(total);

                    document.querySelector('input[name="cost"]').value = total;
                }


                quantityInputs.forEach(function (quantityInput) {
                    const maxQuantity = parseInt(quantityInput.dataset.maxQuantity);
                    const btnPlus = quantityInput.closest('.quantity').querySelector('.btn-plus');
                    const btnMinus = quantityInput.closest('.quantity').querySelector('.btn-minus');

                    btnPlus.addEventListener('click', function () {
                        updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) + 1, maxQuantity);
                    });

                    btnMinus.addEventListener('click', function () {
                        updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) - 1, maxQuantity);
                    });

                    quantityInput.addEventListener('input', function () {
                        let newValue = parseInt(quantityInput.value);
                        if (isNaN(newValue) || newValue < 1) {
                            newValue = 1;
                        }
                        updateQuantityAndPrice(quantityInput, newValue, maxQuantity);
                    });

                    quantityInput.addEventListener('keyup', function (event) {
                        if (event.keyCode === 13) {
                            let newValue = parseInt(quantityInput.value);
                            if (isNaN(newValue) || newValue < 1) {
                                newValue = 1;
                            }
                            updateQuantityAndPrice(quantityInput, newValue, maxQuantity);
                        }
                    });

                    updatePrice(quantityInput, parseInt(quantityInput.value));
                });
            });

            function confirmDelete(event, formId) {
                event.preventDefault(); // Ngăn chặn việc gửi form ngay lập tức
                if (confirm('Bạn có muốn xoá sản phẩm khỏi giỏ hàng không?')) {
                    document.getElementById(formId).submit(); // Gửi form nếu người dùng xác nhận
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
    </body>

</html>