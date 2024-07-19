<%-- 
    Document   : Testimonial
    Created on : May 25, 2024, 10:32:54 PM
    Author     : hailt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            .col-12 {
                display: flex;
                align-items: center;  /* Align items vertically center */
                justify-content: center; /* Align items horizontally center */
                height: 100%;
            }

            .img-fluid {
                max-height: 250px;
                max-width: 250px;
                object-fit: cover;
            }

        </style>
    </head>

    <body>

        <div class="container-fluid fixed-top">
            <div class="container topbar bg-primary d-none d-lg-block">
                <div class="d-flex justify-content-between">
                    <div class="top-info ps-2">
                        <small class="me-3"><i class="fas fa-map-marker-alt me-2 text-secondary"></i> <a href="#" class="text-white">DH FPT</a></small>
                        <!--<small class="me-3"><i class="fas fa-envelope me-2 text-secondary"></i><a href="#" class="text-white">Email@Example.com</a></small>-->
                    </div>
                    <div class="top-link pe-2">
                        <c:if test = "${sessionScope.account == null}"> 
                            <a href="Login.jsp" class="text-white"><small class="text-white ms-2">Đăng nhập</small></a>
                        </c:if> 
                        <c:if test="${sessionScope.account != null}">
                            <c:set var="username" value="${fn:substringBefore(sessionScope.account.email, '@')}" />
                            <small class="text-white ms-2">Hello, ${account.name}</small>
                            <span class="text-white ms-2">|</span>
                            <a href="logout" class="text-white"><small class="text-white ms-2">Đăng xuất</small></a>
                        </c:if> 
                    </div>
                </div>
            </div>
            <div class="container px-0">
                <nav class="navbar navbar-light bg-white navbar-expand-xl">
                    <a href="home" class="navbar-brand"><h1 class="text-primary display-6">4FOODHD</h1></a>
                    <button class="navbar-toggler py-2 px-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-white" id="navbarCollapse">
                        <div class="navbar-nav mx-auto">
                            <a href="home" class="nav-item nav-link ">Trang chủ</a>
                            <a href="shop" class="nav-item nav-link ">Lọc sản phẩm</a>
                           <a href="flsale" class="nav-item nav-link ">Flash Sale</a>
                            <a href="blog" class="nav-item nav-link active" >Blog</a>
                            <c:if test="${sessionScope.account != null}">
                                <a href="loadVoucherFreeship" class="nav-item nav-link">Voucher</a>
                            </c:if> 

                            <!--<a href="Contact.jsp" class="nav-item nav-link">Contact</a>-->
                        </div>
                        <div class="d-flex m-3 me-0">
                            <button class="btn-search btn border border-secondary btn-md-square rounded-circle bg-white me-4" data-bs-toggle="modal" data-bs-target="#searchModal">
                                <i class="fas fa-search text-primary"></i>
                            </button>

                            <c:set value="${sessionScope.size}" var="size"></c:set>
                                <a href="Cart.jsp" class="position-relative me-4 my-auto">
                                    <i class="fa fa-shopping-bag fa-2x"></i>
                                    <span class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1" style="top: -5px; left: 15px; height: 20px; min-width: 20px;">${size}</span>
                            </a>

                            <!-- Kiểm tra nếu người dùng đã đăng nhập -->
                            <c:if test="${not empty sessionScope.account}">
                                <a href="profile" class="my-auto">
                                    <i class="fas fa-user fa-2x"></i>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

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

        <div class="testimonial-header text-center">
            <h1 class="display-5 mb-5 text-dark">Chào mừng bạn đến với Blog!</h1>
        </div>

        <div class="container py-5">

            <div class="row g-5">
                <!-- Blog list Start -->
                <div class="col-lg-8">
                    <div class="blog-item mb-5">
                        <c:forEach var="b" items="${paginatedList}">
                            <c:if test="${b.status == true}">
                                <div class="row g-0 bg-light overflow-hidden" style="margin: 30px 0px;">
                                    <div class="col-12 col-sm-5 h-100">
                                        <img class="img-fluid h-100" src="img/${b.imageURL}" style="object-fit: cover;">
                                    </div>
                                    <div class="col-12 col-sm-7 h-100 d-flex flex-column justify-content-center">
                                        <div class="p-4">
                                            <div class="d-flex mb-3">
                                                <small class="me-3"><i class="bi bi-bookmarks me-2"></i>Người đăng: ${b.nameUpdateBy}</small>
                                                <small><i class="bi bi-calendar-date me-2"></i>${b.createDate}</small>
                                            </div>
                                            <h5 class="text-uppercase mb-3 title">${b.title}</h5>
                                            <p class="summary">${b.summary}</p>
                                            <a class="text-primary text-uppercase" href="blogDetail?blogId=${b.blogId}">Đọc thêm<i class="bi bi-chevron-right"></i></a>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                        <div class="col-12">
                            <div class="pagination d-flex justify-content-center mt-5">
                                <a href="blog?page=${1}" class="rounded">&laquo;</a>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <a href="blog?page=${i}" class="${currentPage == i ? 'active rounded' : 'rounded'}">${i}</a>
                                </c:forEach>
                                <a href="blog?page=${totalPages}" class="rounded" >&raquo;</a>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Blog list End -->

                <!-- Sidebar Start -->
                <div class="col-lg-4">
                    <!-- Search Form Start -->
                    <div class="mb-5">
                        <div class="input-group">
                            <input type="text" class="form-control p-3" placeholder="Từ khoá">
                            <button class="btn btn-primary px-4"><i class="bi bi-search"></i></button>
                        </div>
                    </div>
                    <!-- Search Form End -->

                    <!-- Recent Post Start -->
                    <div class="mb-5">
                        <h3 class="text-uppercase border-start border-5 border-primary ps-3 mb-4">Bài viết gần đây</h3>
                        <c:forEach var="b" items="${listBlogDTO}">
                            <c:if test="${b.status == true}">
                                <div class="d-flex overflow-hidden mb-3">
                                    <img class="img-fluid" src="img/${b.imageURL}" style="width: 100px; height: 100px; object-fit: cover;" alt="Không thể tải ảnh">
                                    <a href="blogDetail?blogId=${b.blogId}" class="h5 d-flex align-items-center bg-light px-3 mb-0">${b.title}
                                    </a>
                                </div>
                            </c:if>
                        </c:forEach>

                    </div>

                    <div class="mb-5">
                        <img src="img/anhblog3.png" alt="" class="img-fluid rounded">
                    </div>
                </div>
            </div>
        </div>


        <%@include file="Footer.jsp" %>


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
            document.addEventListener("DOMContentLoaded", function () {
                const summaries = document.querySelectorAll('.summary');
                summaries.forEach(summary => {
                    const maxLength = 150;
                    if (summary.textContent.length > maxLength) {
                        summary.textContent = summary.textContent.substring(0, maxLength) + '...';
                    }
                });
            });
        </script>
    </body>

</html>
