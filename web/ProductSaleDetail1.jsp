<%-- 
    Document   : ProductSaleDetail
    Created on : Jun 28, 2024, 8:09:52 AM
    Author     : hailt
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
            <div class="container">
                <img src="img/flashsale.jpg" class="img-fluid rounded" style="width: 100%; height: 20%; margin-top: 170px; margin-bottom: 10px" alt="Image">
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
                                            <img style="height: 450px; width: 500px" src="img/${fsdetail.imageURL}" class="img-fluid rounded" alt="Image">
                                    </a>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <h4 class="fw-bold mb-3">${fsdetail.name}</h4>
                                <h5 style="display: flex; align-items: center;font-family: sans-serif;" id="price-${detail.id}">${fsdetail.price}</h5>
                                <div class="d-flex mb-4" id="star-rating">
                                    <!-- Các ngôi sao sẽ được thêm động bởi JavaScript -->
                                </div>
                                <p class="mb-4">${fsdetail.description}</p>
                                <p class="mb-4">Quantity: ${fsdetail.quantity}</p>
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
                                    <input type="hidden" name="productId" value="${fsdetail.productId}">
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
                                <h4 class="mb-4">Sản phẩm nổi bật</h4>
                                <div id="productList">
                                    <h5>Đang chờ đến thời gian Flash Sale</h5>
                                </div>                              
                            </div>
                        </div>
                    </div>
                    <!-- Related products -->

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
    </body>
</html>