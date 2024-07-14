<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <!-- Modal Search End -->

            <!-- Single Page Header start -->
            <div class="container-fluid page-header py-5">
                <h1 class="text-center text-white display-6">Cart</h1>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-white">Cart</li>
                </ol>
            </div>
            <!-- Single Page Header End -->

            <!-- Cart Page Start -->
            <div class="container-fluid py-5">
                <div class="container py-5">

                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
<<<<<<< HEAD (a9a0b53) - code_haile
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Handle</th>
=======
                                    <th scope="col">Sản phẩm</th>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Giá</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                    <th scope="col">Hủy</th>
>>>>>>> origin/hungnv13 (c6b0c90) - hungnv13
                                </tr>
                            </thead>
                                        </div>
                                        </div>
                        </tbody>
                    </table>
                </div>
<<<<<<< HEAD (a9a0b53) - code_haile

                        <c:set var="o" value="${sessionScope.cart}"/>
                        <c:forEach var="i" items="${o.items}"  >


                            <tr>
                                <th scope="row">
                                    <div class="d-flex align-items-center">
                                        <img src="img/${i.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                    </div>
                                </th>
                                <td>
                                    <p class="mb-0 mt-4">${i.product.name}</p>
                                </td>
                                <td>
                                    <p class="mb-0 mt-4">
                                        <fmt:formatNumber value="${i.price}" maxFractionDigits="3"/>
                                    </p>

                                </td>
                                <td>
                                    <c:if test="${not empty requestScope.mes}">
                                        <div class="alert alert-warning" role="alert">
                                            ${requestScope.mes}
=======
                            <tbody>
                            <c:set var="o" value="${sessionScope.cart}"/>
                            <c:forEach var="i" items="${o.items}">
                                <tr>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <img src="img/${i.product.imageURL}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
>>>>>>> origin/hungnv13 (c6b0c90) - hungnv13
                </div>

                <div class="row g-4 justify-content-end">
                                </div>
                                <form action="checkout" method="get">
                                    <button class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4" type="submit">Mua Hàng</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <jsp:include page="Footer.jsp"></jsp:include>
        <script>
            function confirmDelete(event, formId) {
                event.preventDefault(); // Ngăn chặn việc gửi form ngay lập tức
                if (confirm('Bạn có muốn xoá sản phẩm khỏi giỏ hàng không?')) {
                    document.getElementById(formId).submit(); // Gửi form nếu người dùng xác nhận
                }
            }
        </script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const quantityInputs = document.querySelectorAll('.quantity-input');

                function updateQuantityAndPrice(quantityInput, newQuantity, maxQuantity) {
                    if (newQuantity < 1) {
                        alert('Số lượng không hợp lệ');
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

                function updatePrice(quantityInput, quantity) {
                    const pricePerItem = parseFloat(quantityInput.dataset.price);
                    const totalPriceElement = quantityInput.closest('tr').querySelector('.total-price');
                    const totalPrice = quantity * pricePerItem * 1000;
                    totalPriceElement.innerText = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(totalPrice);
                }

                function updateTotal() {
                    let total = 0;
                    const rows = document.querySelectorAll('tbody > tr');
                    rows.forEach(function (row) {
                        const priceElement = row.querySelector('.total-price');
                        total += parseFloat(priceElement.innerText.replace(/[^\d.-]/g, ''));
                    });
                    const subtotalElement = document.querySelector('.subtotal');
                    subtotalElement.innerText = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(total);
                    const totalElement = document.querySelector('.cart-total');
                    totalElement.innerText = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(total);
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

        </script>
        <script>
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
<<<<<<< HEAD (a9a0b53) - code_haile
                                    </c:if>
                                    <div class="input-group  mt-4" style="width: 100px; border: none">
                                        <div class="input-group-btn" style="border: none;">
                                            <button><a href="process?num=-1&id=${i.product.productId}" style="border: none">-</a></button>
=======
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">${i.product.name}</p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">
                                            <fmt:formatNumber value="${i.price*1000}" currencySymbol="VND" maxFractionDigits="0"/>
                                        </p>
                                    </td>
                                    <td>
                                        <div class="input-group quantity mt-4" style="width: 100px;">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                    <i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                            <input type="text" class="form-control form-control-sm text-center border-0 quantity-input" value="${i.quantity}" data-product-id="${i.product.productId}" data-price="${i.price}" data-max-quantity="10">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
>>>>>>> origin/hungnv13 (c6b0c90) - hungnv13
