<%-- 
    Document   : ShopDetail
    Created on : May 25, 2024, 10:31:14 PM
    Author     : hailt
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link href="css/detail.css" rel="stylesheet">
        <style>
            .vesitable-item {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                width: 100%;
                min-width: 300px;
                max-width: 300px;
                height: 100%;
                min-height: 450px;
                max-height: 450px;
                margin: 10px;
            }

            .vesitable-img img {
                width: 150px;
                height: 220px;
                object-fit: cover;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
            }

            .vesitable .owl-carousel .owl-item {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%;
            }

            .vesitable .owl-carousel .owl-item.active {
                display: flex;
            }

            .text-white.bg-primary {
                top: 10px;
                right: 10px;
                padding: 5px 10px;
                font-size: 14px;
                line-height: 1.2;
            }

            .p-4.pb-0.rounded-bottom {
                padding-bottom: 15px !important;
                flex: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .d-flex.justify-content-between.flex-lg-wrap {
                margin-top: auto;
            }

            .p-4.pb-0.rounded-bottom h4 {
                font-size: 1.25rem;
                margin-bottom: 10px;
            }

            .p-4.pb-0.rounded-bottom p.description {
                font-size: 1rem;
                margin-bottom: 15px;
            }

            .p-4.pb-0.rounded-bottom p.fs-5 {
                font-size: 1.5rem;
                font-weight: 700;
            }

            .p-4.pb-0.rounded-bottom a.btn {
                font-size: 1rem;
                padding: 5px 10px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="Header.jsp"></jsp:include>
            <!-- Modal Search Start -->
            <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-fullscreen">
                    <div class="modal-content rounded-0">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Tìm kiếm</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body d-flex align-items-center">
                            <form id="searchForm" action="search" method="get" class="w-75 mx-auto d-flex">
                                <input type="search" id="searchInput" class="form-control p-3" placeholder="keywords" name="txt" aria-describedby="search-icon-1">
                                <button type="submit" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Modal Search End -->


            <!-- Single Page Header start -->
            <div class="container-fluid page-header py-5">
                <h1 class="text-center text-white display-6">Chi tiết sản phẩm </h1>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="home" style="color: white">Home</a></li>
                    <!--                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                                        <li class="breadcrumb-item active text-white">Shop Detail</li>-->
                </ol>
            </div>
            <!-- Single Page Header End -->


            <!-- Single Product Start -->
            <div class="container-fluid py-5 mt-5">
                <div class="container py-5">
                    <div class="row g-4 mb-5">
                        <div class="col-lg-8 col-xl-9">
                            <div class="row g-4">
                                <div class="col-lg-6">
                                    <div class="border rounded">
                                        <a href="#">
                                            <img src="img/${detail.image}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="fw-bold mb-3">${detail.name}</h4>
                                <p class="mb-3">Category: ${detail.categoryName}</p>
                                <h5 style="display: flex; align-items: center;font-family: sans-serif;" id="price-${detail.id}">${detail.price}</h5>
                                <div class="d-flex mb-4">
                                    <c:forEach begin="1" end="5" var="i">
                                        <c:choose>
                                            <c:when test="${i <= detail.rateStar}">
                                                <i class="fa fa-star text-secondary"></i>
                                            </c:when>
                                            <c:when test="${i - 0.5 == detail.rateStar}">
                                                <i class="fa fa-star-half-alt text-secondary"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="far fa-star"  style="color: rgb(255, 181, 36);"></i>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </div>
                                <p class="mb-4">${detail.decription}</p>
                                <p class="mb-4">Quantity: ${detail.quantity}</p>
                                <div style="margin-bottom: 30px">
                                    <div class="input-group quantity mt-4" style="width: 100px;">
                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-minus rounded-circle bg-light border">
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>
                                        <input type="text" class="form-control form-control-sm text-center border-0 quantity-input" value="1" data-product-id="${detail.id}" data-price="${detail.price}" data-max-quantity="${detail.quantity}">
                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div style="display: flex; margin: 0 30; ">
                                    <form id="addToCartForm" action="addtocart" method="post" style="margin-right: 30px">
                                        <input type="hidden" name="productId" value="${detail.id}">
                                        <input type="hidden" name="quantity" id="addToCartQuantity" value="1">
                                        <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">
                                            <i class="fa fa-shopping-bag me-2 text-primary"></i>Thêm vào giỏ hàng
                                        </button>
                                    </form>
                                    <form id="addToCheckout" action="checkout2" method="get">
                                        <input type="hidden" name="productId" value="${detail.id}">
                                        <input type="hidden" name="quantityCart" id="checkoutQuantity" value="1">
                                        <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">

                                            <i class="fas fa-cart-arrow-down me-2 text-primary"></i>Mua ngay

                                        </button>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <nav>
                                    <div class="nav nav-tabs mb-3">
                                        <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                                id="nav-mission-tab" onclick="toggleReviews()"
                                                aria-controls="nav-mission" aria-selected="false">Reviews</button>
                                    </div>
                                </nav>
                                <div class="tab-content mb-5">
                                    <div class="tab-pane" id="reviews-section" style="display: none;" role="tabpanel" aria-labelledby="nav-mission-tab">
                                        <c:forEach var="review" items="${reviews}">
                                            <div class="d-flex">
                                                <img src="img/${review.imageAvatar}" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                                <div class="">
                                                    <p class="mb-2" style="font-size: 14px;">${review.date}</p>
                                                    <div class="d-flex justify-content-between">
                                                        <h5>${review.nameAccount}</h5>
                                                        <div class="d-flex mb-3">
                                                            <c:forEach begin="1" end="5" var="i">
                                                                <c:choose>
                                                                    <c:when test="${i <= review.rateStar}">
                                                                        <i class="fa fa-star text-secondary"></i>
                                                                    </c:when>
                                                                    <c:when test="${i - 0.5 == review.rateStar}">
                                                                        <i class="fa fa-star-half-alt text-secondary"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="far fa-star"  style="color: rgb(255, 181, 36);"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <p>${review.feedback}</p>
                                                    <c:if test="${not empty review.imageURL}">
                                                        <img src="img/${review.imageURL}" style="width: 100px; height: 100px;">
                                                    </c:if>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>                           
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-3">
                        <div class="row g-4 fruite">
                            <div class="col-lg-12">
                                <h4 class="mb-4">Sản phẩm giảm giá</h4>
                                <div id="productList">
                                    <c:forEach var="listProductByIsSale" items="${listProductByIsSale}" varStatus="status">
                                        <div class="product-item d-flex align-items-center justify-content-start mb-4 ${status.index >= 2 ? 'd-none more-item' : ''}" id="Block-${status.index}">
                                            <div class="rounded" style="width: 100px; height: 100px;">
                                                <img src="img/${listProductByIsSale.image}" class="img-fluid rounded" alt="${listProductByIsSale.name}">
                                            </div>
                                            <div class="ms-3">
                                                <h6 class="mb-2">${listProductByIsSale.name}</h6>
                                                <div class="d-flex mb-2">
                                                    <h5 style="display: flex; align-items: center; font-family: sans-serif;" id="price-${listProductByIsSale.id}">${listProductByIsSale.price}</h5>
                                                    <h5 class="text-danger text-decoration-line-through" style="display: flex; align-items: center; font-family: sans-serif;" id="price-${listProductByIsSale.id}">
                                                        <c:out value="${listProductByIsSale.price * 1.11111111}"/>
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <!-- Buttons to toggle view -->
                                <div id="toggleButtons" class="mt-3">
                                    <button class="btn btn-primary" onclick="showMore()">Xem thêm</button>
                                    <button class="btn btn-secondary d-none" onclick="showLess()">Thu gọn</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Related products -->
                    <h1 class="fw-bold mb-0">Sản Phẩm Liên Quan</h1>
                    <div class="vesitable">
                        <div class="owl-carousel vegetable-carousel justify-content-center">
                            <c:forEach var="relatedProduct" items="${listSameCategoryProducts}">
                                <c:if test="${relatedProduct.quantity > 0 && relatedProduct.status > 0 && relatedProduct.statusAccount != 0}">
                                    <div class="border border-primary rounded position-relative vesitable-item">
                                        <div class="vesitable-img">
                                            <img src="img/${relatedProduct.image}" class="img-fluid w-100 rounded-top" alt="${relatedProduct.name}">
                                        </div>
                                        <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">${relatedProduct.categoryName}</div>
                                        <div class="p-4 pb-0 rounded-bottom">
                                            <h4>${relatedProduct.name}</h4>
                                            <p class="description">${relatedProduct.decription}</p>
                                            <div style="display: flex; justify-content: space-between;">
                                                <h6 style="display: flex; align-items: center;font-family: sans-serif;" id="price-${relatedProduct.id}">${relatedProduct.price}</h6>   
                                                <div style="display: flex;">
                                                    <form action="addtocart" method="post" >
                                                        <input type="hidden" name="productId" value="${relatedProduct.id}">
                                                        <button type="submit" class="text-primary " style="margin-right: 5px; border: 2px solid black; border-radius: 8px; height: 40px; width: 40px;" title="Thêm vào giỏ hàng">
                                                            <i class="fa fa-shopping-bag" title="Thêm vào giỏ hàng"></i>
                                                        </button>
                                                    </form>
                                                    <div class="d-flex justify-content-between flex-lg-wrap" style="margin-right: 10px;">
                                                        <a href="restaurant?restaurantId=${relatedProduct.restaurantId}&page=${1}">
                                                            <img src="img/${relatedProduct.imageRestaurant}" style="height: 40px; width: 40px; border: 2px solid black; border-radius: 8px;">
                                                        </a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single Product End -->


            <!-- Footer Start -->

            <jsp:include page="Footer.jsp"></jsp:include>
            <!-- Footer End -->
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
            <script>
                                        function toggleReviews() {
                                            var reviewsSection = document.getElementById('reviews-section');
                                            if (reviewsSection.style.display === 'none') {
                                                reviewsSection.style.display = 'block';
                                            } else {
                                                reviewsSection.style.display = 'none';
                                            }
                                        }
            </script>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    const prices = document.querySelectorAll('[id^="price-"]');

                    prices.forEach(priceElement => {
                        const priceId = priceElement.id.split('-')[1]; // Lấy ID sản phẩm
                        const priceValue = parseFloat(priceElement.textContent.replace(/[^0-9.-]+/g, "")); // Chuyển đổi giá trị thành số

                        // Định dạng giá thành VND
                        const formattedPrice = (priceValue).toLocaleString('vi-VN');

                        // Cập nhật nội dung của thẻ h6
                        priceElement.textContent = formattedPrice + " VNĐ";
                    });
                });
            </script>
            <script>
                function toggleProducts() {
                    const moreItems = document.querySelectorAll('.more-item');
                    const loadMoreBtn = document.getElementById('load-more-btn');
                    const showLessBtn = document.getElementById('show-less-btn');

                    moreItems.forEach(item => {
                        item.classList.toggle('d-none');
                    });

                    loadMoreBtn.classList.toggle('d-none');
                    showLessBtn.classList.toggle('d-none');
                }
            </script>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    const quantityInput = document.querySelector('.quantity-input');
                    const addToCheckoutForm = document.getElementById('addToCheckout');
                    const checkoutQuantityInput = document.getElementById('checkoutQuantity');

                    quantityInput.addEventListener('input', function () {
                        let newValue = parseInt(quantityInput.value);
                        if (isNaN(newValue) || newValue < 1) {
                            newValue = 1;
                        }
                        quantityInput.value = newValue;
                        checkoutQuantityInput.value = newValue;
                    });

                    addToCheckoutForm.addEventListener('submit', function () {
                        checkoutQuantityInput.value = quantityInput.value;
                    });
                });
            </script>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    const quantityInputs = document.querySelectorAll('.quantity-input');
                    const addToCartForm = document.getElementById('addToCartForm');
                    const addToCartQuantityInput = document.getElementById('addToCartQuantity');

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

                        // Function to update quantity and price
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
                            addToCartQuantityInput.value = newQuantity;
                            updatePrice(quantityInput, newQuantity);
                        }

                        function updatePrice(quantityInput, quantity) {
                            const pricePerItem = parseFloat(quantityInput.dataset.price);
                            // Example of updating total price display based on quantity
                            const totalPriceElement = quantityInput.closest('.row').querySelector('.price-total');
                            const totalPrice = quantity * pricePerItem * 1000;
                            totalPriceElement.innerText = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(totalPrice);
                        }

                        updatePrice(quantityInput, parseInt(quantityInput.value));
                    });

                    // Example of submitting form
                    addToCartForm.addEventListener('submit', function (event) {
                        // You can add additional validation or actions before submitting the form
                        // event.preventDefault(); // Uncomment to prevent default form submission for testing
                        // Example of fetching data if needed
                        const formData = new FormData(addToCartForm);
                        fetch(addToCartForm.action, {
                            method: 'POST',
                            body: formData
                        })
                                .then(response => {
                                    if (!response.ok) {
                                        throw new Error('Network response was not ok');
                                    }
                                    return response.text();
                                })
                                .then(data => {
                                    // Handle response data if necessary
                                })
                                .catch(error => {
                                    console.error('Có vấn đề xảy ra trong quá trình fetch:', error);
                                });
                    });
                });
            </script>
            <script>
                function showMore() {
                    document.querySelectorAll('.more-item').forEach(item => item.classList.remove('d-none'));
                    document.getElementById('toggleButtons').innerHTML = `
            <button class="btn btn-secondary" onclick="showLess()">Thu gọn</button>
        `;
                }

                function showLess() {
                    document.querySelectorAll('.more-item').forEach(item => item.classList.add('d-none'));
                    document.getElementById('toggleButtons').innerHTML = `
            <button class="btn btn-primary" onclick="showMore()">Xem thêm</button>
        `;
                }
            </script>
    </body>
</html>