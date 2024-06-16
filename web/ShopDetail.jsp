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
                                <h5 class="fw-bold mb-3">${detail.price}</h5>
                                <div class="d-flex mb-4" id="star-rating">
                                    <!-- Các ngôi sao sẽ được thêm động bởi JavaScript -->
                                </div>
                                <p class="mb-4">${detail.decription}</p>
                                <p class="mb-4">Quantity: ${detail.quantity}</p>
                                <div class="input-group quantity mb-5" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-minus rounded-circle bg-light border" >
                                            <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="text" class="form-control form-control-sm text-center border-0" value="1">
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-plus rounded-circle bg-light border">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                                <form action="addtocart" method="post">
                                    <input type="hidden" name="productId" value="${detail.id}">
                                    <button type="submit" class="btn border border-secondary rounded-pill px-3 text-primary">
                                        <i class="fa fa-shopping-bag me-2 text-primary"></i>Thêm vào giỏ hàng
                                    </button>
                                </form>
                            </div>
                            <div class="col-lg-12">
                                <nav>
                                    <div class="nav nav-tabs mb-3">
                                        <button class="nav-link active border-white border-bottom-0" type="button" role="tab"
                                                id="nav-about-tab" data-bs-toggle="tab" data-bs-target="#nav-about"
                                                aria-controls="nav-about" aria-selected="true">Description</button>
                                        <button class="nav-link border-white border-bottom-0" type="button" role="tab"
                                                id="nav-mission-tab" data-bs-toggle="tab" data-bs-target="#nav-mission"
                                                aria-controls="nav-mission" aria-selected="false">Reviews</button>
                                    </div>
                                </nav>
                                <div class="tab-content mb-5">
                                    <div class="tab-pane active" id="nav-about" role="tabpanel" aria-labelledby="nav-about-tab">
                                        <p>${detail.decription}</p>
                                    </div>
                                    <div class="tab-pane" id="nav-mission" role="tabpanel" aria-labelledby="nav-mission-tab">
                                        <c:forEach var="review" items="${reviews}">
                                            <div class="d-flex">
                                                <img src="img/${review.imageAvatar}" class="img-fluid rounded-circle p-3" style="width: 100px; height: 100px;" alt="">
                                                <div class="">
                                                    <p class="mb-2" style="font-size: 14px;">${review.date}</p>
                                                    <div class="d-flex justify-content-between">
                                                        <h5>${review.nameAccount}</h5>
                                                        <div class="d-flex mb-3">
                                                            <c:forEach begin="1" end="5" varStatus="status">
                                                                <c:choose>
                                                                    <c:when test="${status.index <= (review.rateStar)}">
                                                                        <i class="fa fa-star text-secondary"></i>
                                                                    </c:when>
                                                                    <c:when test="${(review.rateStar - status.index) > -0.5 && (review.rateStar - status.index) < 0}">
                                                                        <i class="fa fa-star-half-alt text-secondary"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="fa fa-star"></i>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <p>${review.feedback}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                            <form action="#">
                                <h4 class="mb-5 fw-bold">Leave a Reply</h4>
                                <div class="row g-4">
                                    <div class="col-lg-6">
                                        <div class="border-bottom rounded">
                                            <input type="text" class="form-control border-0 me-4" placeholder="Yur Name *">
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="border-bottom rounded">
                                            <input type="email" class="form-control border-0" placeholder="Your Email *">
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="border-bottom rounded my-4">
                                            <textarea name="" id="" class="form-control border-0" cols="30" rows="8" placeholder="Your Review *" spellcheck="false"></textarea>
                                        </div>
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="d-flex justify-content-between py-3 mb-5">
                                            <div class="d-flex align-items-center">
                                                <p class="mb-0 me-3">Please rate:</p>
                                                <div class="d-flex align-items-center" style="font-size: 12px;">
                                                    <i class="fa fa-star text-muted"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                    <i class="fa fa-star"></i>
                                                </div>
                                            </div>
                                            <a href="#" class="btn border border-secondary text-primary rounded-pill px-4 py-3"> Post Comment</a>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-4 col-xl-3">
                        <div class="row g-4 fruite">
                            <div class="col-lg-12">
                                <h4 class="mb-4">Sản phẩm nổi bật</h4>
                                <c:forEach var="listProductSale" items="${listProductSale}">
                                    <div class="d-flex align-items-center justify-content-start mb-4">
                                        <div class="rounded" style="width: 100px; height: 100px;">
                                            <img src="img/${listProductSale.image}" class="img-fluid rounded" alt="${listProductSale.name}">
                                        </div>
                                        <div class="ms-3">
                                            <h6 class="mb-2">${listProductSale.name}</h6>
                                            <div class="d-flex mb-2">
                                                <c:forEach begin="1" end="5" varStatus="status">
                                                    <i class="fa fa-star ${status.index <= listProductSale.rateStar ? 'text-secondary' : ''}"></i>
                                                </c:forEach>
                                            </div>
                                            <div class="d-flex mb-2">
                                                <h5 class="fw-bold me-2">${listProductSale.salePrice} $</h5>
                                                <h5 class="text-danger text-decoration-line-through">${listProductSale.discount} $</h5>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="d-flex justify-content-center my-4">
                                    <a href="#" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">Xem thêm </a>
                                </div>
                            </div>

                            <div class="col-lg-12">
                                <div class="position-relative">
                                    <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="">
                                    <div class="position-absolute" style="top: 50%; right: 10px; transform: translateY(-50%);">
                                        <h3 class="text-secondary fw-bold">Fresh <br> Fruits <br> Banner</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Related products -->
                <h1 class="fw-bold mb-0">Sản Phẩm Liên Quan</h1>
                <div class="vesitable">
                    <div class="owl-carousel vegetable-carousel justify-content-center">
                        <c:forEach var="relatedProduct" items="${listSameCategoryProducts}">
                            <div class="border border-primary rounded position-relative vesitable-item">
                                <div class="vesitable-img">
                                    <img src="img/${relatedProduct.image}" class="img-fluid w-100 rounded-top" alt="${relatedProduct.name}">
                                </div>
                                <div class="text-white bg-primary px-3 py-1 rounded position-absolute" style="top: 10px; right: 10px;">${relatedProduct.categoryName}</div>
                                <div class="p-4 pb-0 rounded-bottom">
                                    <h4>${relatedProduct.name}</h4>
                                    <p class="description">${relatedProduct.decription}</p>
                                    <div class="d-flex justify-content-between flex-lg-wrap">
                                        <p class="text-dark fs-5 fw-bold">${relatedProduct.price}</p>
                                        <a href="#" class="btn border border-secondary rounded-pill px-3 py-1 mb-4 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                                    </div>
                                </div>
                            </div>
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
                // Lấy giá trị sao từ thuộc tính JSP
                var rating = ${detail.rateStar};
                var starContainer = document.getElementById('star-rating');

                for (var i = 1; i <= 5; i++) {
                    var star = document.createElement('i');
                    star.className = 'fa fa-star';
                    if (i <= Math.floor(rating)) {
                        star.classList.add('text-primary'); // Đổi màu sao được đánh giá
                    } else {
                        star.classList.add('text-secondary'); // Đổi màu sao không được đánh giá
                    }
                    if (i === Math.ceil(rating) && rating % 1 !== 0) {
                        star.className = 'fa fa-star-half'; // Nửa sao
                        star.classList.add('text-primary');
                    }
                    starContainer.appendChild(star);
                }
        </script>
    </body>

</html>
