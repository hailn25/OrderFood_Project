<%-- 
    Document   : Home
    Created on : May 23, 2024, 7:52:26 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <style>
            .truncate-description {
                display: -webkit-box;
                -webkit-line-clamp: 3; /* Number of lines to show */
                -webkit-box-orient: vertical;
                overflow: hidden;
                text-overflow: ellipsis;
            }
            .fruite-item {
                display: flex;
                flex-direction: column;
                height: 100%;
            }

            .fruite-img img {
                object-fit: cover;
                height: 280px;
            }

            .fruite-item h4 {
                min-height: 60px;
            }

            .fruite-item .p-4 {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .btn-add-to-cart {
                width: 100%;
            }


            .vesitable-item {
                display: flex;
                flex-direction: column;
                height: 100%;
            }

            .vesitable-img img {
                height: 280px;
                object-fit: cover;
            }

            .truncate-description {
                overflow: hidden;
                text-overflow: ellipsis;
                display: -webkit-box;
                -webkit-line-clamp: 3; /* number of lines to show */
                -webkit-box-orient: vertical;
            }

            .vesitable-item {
                display: flex;
                flex-direction: column;
                height: 100%;
                justify-content: space-between;
            }

            .p-4 {
                flex-grow: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
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


            <!-- Hero Start -->
            <div class="container-fluid py-5 mb-5 hero-header">
                <div class="container py-5">
                    <div class="row g-5 align-items-center">
                        <div class="col-md-12 col-lg-7">
                            <!--                            <h4 class="mb-3 text-secondary">100% Organic Foods</h4>
                                                        <h1 class="mb-5 display-3 text-primary">Organic Veggies & Fruits Foods</h1>-->
                            <!--                            <div class="position-relative mx-auto">
                                                            <input class="form-control border-2 border-secondary w-75 py-3 px-4 rounded-pill" type="text" placeholder="Search">
                                                            <button type="submit" class="btn btn-primary border-2 border-secondary py-3 px-4 position-absolute rounded-pill text-white h-100" style="top: 0; right: 25%;">Submit Now</button>
                                                        </div>-->
                        </div>
                        <div class="col-md-12 col-lg-5">
                            <div id="carouselId" class="carousel slide position-relative" data-bs-ride="carousel">
                                <div class="carousel-inner" role="listbox">
                                    <div class="carousel-item active rounded">
                                        <img src="img/hero-img-1.png" class="img-fluid w-100 h-100 bg-secondary rounded" alt="First slide">
                                        <a href="#" class="btn px-4 py-2 text-white rounded">Fruites</a>
                                    </div>
                                    <div class="carousel-item rounded">
                                        <img src="img/hero-img-2.jpg" class="img-fluid w-100 h-100 rounded" alt="Second slide">
                                        <a href="#" class="btn px-4 py-2 text-white rounded">Vesitables</a>
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Hero End -->


            <!--             Featurs Section Start 
                        <div class="container-fluid featurs py-5">
                            <div class="container py-5">
                                <div class="row g-4">
                                    <div class="col-md-6 col-lg-3">
                                        <div class="featurs-item text-center rounded bg-light p-4">
                                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                                <i class="fas fa-car-side fa-3x text-white"></i>
                                            </div>
                                            <div class="featurs-content text-center">
                                                <h5>Free Shipping</h5>
                                                <p class="mb-0">Free on order over $300</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-3">
                                        <div class="featurs-item text-center rounded bg-light p-4">
                                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                                <i class="fas fa-user-shield fa-3x text-white"></i>
                                            </div>
                                            <div class="featurs-content text-center">
                                                <h5>Security Payment</h5>
                                                <p class="mb-0">100% security payment</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-3">
                                        <div class="featurs-item text-center rounded bg-light p-4">
                                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                                <i class="fas fa-exchange-alt fa-3x text-white"></i>
                                            </div>
                                            <div class="featurs-content text-center">
                                                <h5>30 Day Return</h5>
                                                <p class="mb-0">30 day money guarantee</p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-lg-3">
                                        <div class="featurs-item text-center rounded bg-light p-4">
                                            <div class="featurs-icon btn-square rounded-circle bg-secondary mb-5 mx-auto">
                                                <i class="fa fa-phone-alt fa-3x text-white"></i>
                                            </div>
                                            <div class="featurs-content text-center">
                                                <h5>24/7 Support</h5>
                                                <p class="mb-0">Support every time fast</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                         Featurs Section End -->


            <!-- Fruits Shop Start-->
            <div class="container-fluid fruite py-5">
                <div class="container py-5">
                    <div class="tab-class text-center">
                        <div class="row g-4">
                            <div class="col-lg-4 text-start">
                                <h1>Danh mục sản phẩm</h1>
                            </div>
                            <div class="col-lg-8 text-end">
                                <ul class="nav nav-pills d-inline-flex text-center mb-5">
                                <c:forEach items="${listC}" var="c">
                                    <li class="nav-item">
                                        <div class="d-flex m-2 py-2 bg-light rounded-pill active">
                                            <a class="text-dark" style="width: 130px;" href="category?cid=${c.id}">${c.name}</a>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane fade show p-0 active">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="row g-4">
                                    <c:forEach items="${listP}" var="p">
                                        <div class="col-md-6 col-lg-4 col-xl-3">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <img src="img/${p.image}" class="img-fluid w-100 rounded-top" alt="">
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${p.categoryName}</div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>
                                                        <a href="detail?pid=${p.id}" style="color: black;">${p.name}</a>
                                                    </h4>
                                                    <p>${p.restaurantName}</p>
                                                    <div class="d-flex justify-content-between align-items-center mt-auto">
                                                        <p class="text-dark fs-5 fw-bold mb-0">${p.price}</p>
                                                        <form action="addtocart" method="post" class="btn-add-to-cart">
                                                            <input type="hidden" name="productId" value="${p.id}">
                                                            <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary w-100">
                                                                <i class="fa fa-shopping-bag me-2 text-primary"></i>Thêm vào giỏ hàng</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>      
        </div>
        <!-- Fruits Shop End-->


        <!--         Featurs Start 
                <div class="container-fluid service py-5">
                    <div class="container py-5">
                        <div class="row g-4 justify-content-center">
                            <div class="col-md-6 col-lg-4">
                                <a href="#">
                                    <div class="service-item bg-secondary rounded border border-secondary">
                                        <img src="img/featur-1.jpg" class="img-fluid rounded-top w-100" alt="">
                                        <div class="px-4 rounded-bottom">
                                            <div class="service-content bg-primary text-center p-4 rounded">
                                                <h5 class="text-white">Fresh Apples</h5>
                                                <h3 class="mb-0">20% OFF</h3>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-4">
                                <a href="#">
                                    <div class="service-item bg-dark rounded border border-dark">
                                        <img src="img/featur-2.jpg" class="img-fluid rounded-top w-100" alt="">
                                        <div class="px-4 rounded-bottom">
                                            <div class="service-content bg-light text-center p-4 rounded">
                                                <h5 class="text-primary">Tasty Fruits</h5>
                                                <h3 class="mb-0">Free delivery</h3>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-4">
                                <a href="#">
                                    <div class="service-item bg-primary rounded border border-primary">
                                        <img src="img/featur-3.jpg" class="img-fluid rounded-top w-100" alt="">
                                        <div class="px-4 rounded-bottom">
                                            <div class="service-content bg-secondary text-center p-4 rounded">
                                                <h5 class="text-white">Exotic Vegitable</h5>
                                                <h3 class="mb-0">Discount 30$</h3>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                 Featurs End -->


        <!-- Vesitable Shop Start-->
        <div class="container-fluid vesitable py-5">
            <div class="container py-5">
                <h1 class="mb-0">Sản phẩm</h1>
                <div class="owl-carousel vegetable-carousel justify-content-center">
                    <c:forEach var="v" items="${listV}">
                        <div class="border border-primary rounded position-relative vesitable-item">
                            <div class="vesitable-img">
                                <img src="img/${v.image}" class="img-fluid w-100 rounded-top" alt="${v.name}">
                            </div>
                            <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">${v.cateName}</div>
                            <div class="p-4 d-flex flex-column justify-content-between rounded-bottom flex-grow-1">
                                <div>
                                    <h4><a href="detail?pid=${v.id}" style="color: black;" class="product-name">${v.name}</a></h4>
                                    <p class="truncate-description">${v.decription}</p>
                                </div>
                                <div class="d-flex justify-content-between align-items-center mt-3">
                                    <p class="text-dark fs-5 fw-bold mb-0">${v.price}</p>
                                    <form action="addtocart" method="post">
                                        <input type="hidden" name="productId" value="${v.id}">
                                        <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">
                                            <i class="fa fa-shopping-bag me-2 text-primary"></i>Thêm vào giỏ hàng
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>


        <!-- Vesitable Shop End -->

        <!--
                 Banner Section Start
                <div class="container-fluid banner bg-secondary my-5">
                    <div class="container py-5">
                        <div class="row g-4 align-items-center">
                            <div class="col-lg-6">
                                <div class="py-4">
                                    <h1 class="display-3 text-white">Fresh Exotic Fruits</h1>
                                    <p class="fw-normal display-3 text-dark mb-4">in Our Store</p>
                                    <p class="mb-4 text-dark">The generated Lorem Ipsum is therefore always free from repetition injected humour, or non-characteristic words etc.</p>
                                    <a href="#" class="banner-btn btn border-2 border-white rounded-pill text-dark py-3 px-5">BUY</a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="position-relative">
                                    <img src="img/baner-1.png" class="img-fluid w-100 rounded" alt="">
                                    <div class="d-flex align-items-center justify-content-center bg-white rounded-circle position-absolute" style="width: 140px; height: 140px; top: 0; left: 0;">
                                        <h1 style="font-size: 100px;">1</h1>
                                        <div class="d-flex flex-column">
                                            <span class="h2 mb-0">50$</span>
                                            <span class="h4 text-muted mb-0">kg</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 Banner Section End -->


        <!-- Bestsaler Product Start -->
        <div class="container-fluid py-5">
            <div class="container py-5">
                <div class="text-center mx-auto mb-5" style="max-width: 700px;">
                    <h1 class="display-4">Bestseller Products</h1>
                </div>
                <div class="row g-4">
                    <c:forEach items="${listB}" var="b">
                        <div class="col-lg-6 col-xl-4">
                            <div class="p-4 rounded bg-light">
                                <div class="row align-items-center">
                                    <div class="col-6">
                                        <img src="img/${b.image}" class="img-fluid rounded-circle w-100" alt="${b.name}">
                                    </div>
                                    <div class="col-6">
                                        <a href="detail?pid=${b.id}" class="h5">${b.name}</a>
                                        <div class="d-flex my-3">
                                            <c:forEach begin="1" end="${Math.floor(b.rateStar)}" var="star">
                                                <i class="fas fa-star text-primary"></i>
                                            </c:forEach>
                                            <c:if test="${b.rateStar % 1 != 0}">
                                                <i class="fas fa-star-half-alt text-primary"></i>
                                            </c:if>
                                            <c:forEach begin="1" end="${Math.floor(5 - b.rateStar)}" var="star">
                                                <i class="fas fa-star"></i>
                                            </c:forEach>
                                        </div>
                                        <h4 class="mb-3">${b.price}</h4>
                                        <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary">
                                            <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

        <!-- Bestsaler Product End -->


        <!--         Fact Start 
                <div class="container-fluid py-5">
                    <div class="container">
                        <div class="bg-light p-5 rounded">
                            <div class="row g-4 justify-content-center">
                                <div class="col-md-6 col-lg-6 col-xl-3">
                                    <div class="counter bg-white rounded p-5">
                                        <i class="fa fa-users text-secondary"></i>
                                        <h4>satisfied customers</h4>
                                        <h1>1963</h1>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-6 col-xl-3">
                                    <div class="counter bg-white rounded p-5">
                                        <i class="fa fa-users text-secondary"></i>
                                        <h4>quality of service</h4>
                                        <h1>99%</h1>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-6 col-xl-3">
                                    <div class="counter bg-white rounded p-5">
                                        <i class="fa fa-users text-secondary"></i>
                                        <h4>quality certificates</h4>
                                        <h1>33</h1>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-6 col-xl-3">
                                    <div class="counter bg-white rounded p-5">
                                        <i class="fa fa-users text-secondary"></i>
                                        <h4>Available Products</h4>
                                        <h1>789</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 Fact Start -->


        <!-- Tastimonial Start -->
        <div class="container-fluid testimonial py-5">
            <div class="container py-5">
                <div class="testimonial-header text-center">
                    <h4 class="text-primary">Phản hồi</h4>
                    <h1 class="display-5 mb-5 text-dark">Họ nói gì về chúng tôi!</h1>
                </div>
                <div class="owl-carousel testimonial-carousel">
                    <c:forEach items="${listFeedback}" var="listFeedback">
                        <div class="testimonial-item img-border-radius bg-light rounded p-4">
                            <div class="position-relative">
                                <i class="fa fa-quote-right fa-2x text-secondary position-absolute" style="bottom: 30px; right: 0;"></i>
                                <div class="mb-4 pb-4 border-bottom border-secondary">
                                    <p class="mb-0">${listFeedback.feedback}</p>
                                </div>
                                <div class="d-flex align-items-center flex-nowrap">
                                    <div class="bg-secondary rounded">
                                        <img src="img/${listFeedback.avatar}" class="img-fluid rounded" style="width: 100px; height: 100px;" alt="">
                                    </div>
                                    <div class="ms-4 d-block">
                                        <h4 class="text-dark">${listFeedback.name}</h4>
                                        <p class="m-0 pb-3">${listFeedback.nameProduct}</p>
                                        <div class="d-flex pe-5">
                                            <c:forEach begin="1" end="${Math.floor(listFeedback.rateStar)}" var="star">
                                                <i class="fas fa-star text-primary"></i>
                                            </c:forEach>
                                            <c:if test="${listFeedback.rateStar % 1 != 0}">
                                                <i class="fas fa-star-half-alt text-primary"></i>
                                            </c:if>
                                            <c:forEach begin="1" end="${Math.floor(5 - listFeedback.rateStar)}" var="star">
                                                <i class="fas fa-star"></i>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
        <!-- Tastimonial End -->


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