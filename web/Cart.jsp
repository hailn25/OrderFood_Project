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
                    <li class="breadcrumb-item"><a href="#">Pages</li>
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
                                    <td>
                                        <p class="mb-0 mt-4">${i.product.name}</p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">
                                            <fmt:formatNumber value="${i.price}" maxFractionDigits="2"/>
                                        </p>
                                    </td>
                                    <td>
                                        <div class="input-group quantity mt-4" style="width: 100px;">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                    <i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                            <input type="text" class="form-control form-control-sm text-center border-0 quantity-input" value="${i.quantity}" data-product-id="${i.product.productId}" data-price="${i.price}">
                                            <div class="input-group-btn">
                                                <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4 total-price">
                                            <fmt:formatNumber value="${i.quantity * i.price}" maxFractionDigits="2"/>
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

                <div class="mt-5">
                    <input type="text" class="border-0 border-bottom rounded me-5 py-3 mb-4" placeholder="Coupon Code">
                    <button class="btn border-secondary rounded-pill px-4 py-3 text-primary" type="button">Apply Coupon</button>
                </div>

                <div class="row g-4 justify-content-end">
                    <div class="col-sm-8 col-md-7 col-lg-6 col-xl-4">
                        <div class="bg-light rounded">
                            <div class="p-4">
                                <h1 class="display-6 mb-4">Cart <span class="fw-normal">Total</span></h1>
                                <c:set var="subtotal" value="0"/>
                                <c:forEach var="i" items="${o.items}">
                                    <c:set var="subtotal" value="${subtotal + (i.quantity * i.price)}"/>
                                </c:forEach>

                                <div class="d-flex justify-content-between mb-4">
                                    <h5 class="mb-0 me-4">Subtotal:</h5>
                                    <span class="subtotal"><fmt:formatNumber value="${subtotal}" maxFractionDigits="2"/></span>
                                </div>
                                <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                    <h5 class="mb-0 ps-4 me-4">Total</h5>
                                    <span class="cart-total"><fmt:formatNumber value="${subtotal}" maxFractionDigits="2"/></span>
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
    document.addEventListener('DOMContentLoaded', function () {
        const quantityInputs = document.querySelectorAll('.quantity-input');

        function updateQuantityAndPrice(quantityInput, newQuantity) {
            if (newQuantity < 1) {
                console.error('Số lượng không hợp lệ');
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
                    // Bạn không cần cần thiết phải trả về JSON từ server nếu không có dữ liệu JSON để xử lý
                    // return response.json();
                })
                .then(data => {
                    // Xử lý dữ liệu nếu cần thiết
                })
                .catch(error => {
                    console.error('Có vấn đề xảy ra trong quá trình fetch:', error);
                });
        }

        function updatePrice(quantityInput, quantity) {
            const pricePerItem = parseFloat(quantityInput.dataset.price);
            const totalPriceElement = quantityInput.closest('tr').querySelector('.total-price');
            const totalPrice = quantity * pricePerItem;
            totalPriceElement.innerText = totalPrice.toFixed(2);
        }

        function updateTotal() {
            let total = 0;
            const rows = document.querySelectorAll('tbody > tr');
            rows.forEach(function (row) {
                const priceElement = row.querySelector('.total-price');
                total += parseFloat(priceElement.innerText);
            });
            const subtotalElement = document.querySelector('.subtotal');
            subtotalElement.innerText = total.toFixed(2);
            const totalElement = document.querySelector('.cart-total');
            totalElement.innerText = total.toFixed(2);
        }

        quantityInputs.forEach(function (quantityInput) {
            const btnPlus = quantityInput.closest('.quantity').querySelector('.btn-plus');
            const btnMinus = quantityInput.closest('.quantity').querySelector('.btn-minus');
            
            btnPlus.addEventListener('click', function () {
                updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) + 1);
            });

            btnMinus.addEventListener('click', function () {
                updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) - 1);
            });

            quantityInput.addEventListener('input', function () {
                let newValue = parseInt(quantityInput.value);
                if (isNaN(newValue) || newValue < 1) {
                    newValue = 1;
                }
                updateQuantityAndPrice(quantityInput, newValue);
            });

            quantityInput.addEventListener('keyup', function (event) {
                if (event.keyCode === 13) {
                    let newValue = parseInt(quantityInput.value);
                    if (isNaN(newValue) || newValue < 1) {
                        newValue = 1;
                    }
                    updateQuantityAndPrice(quantityInput, newValue);
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
