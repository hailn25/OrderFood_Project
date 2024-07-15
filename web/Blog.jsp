<%-- 
    Document   : Testimonial
    Created on : May 25, 2024, 10:32:54 PM
    Author     : hailt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

        <%@include file="Header.jsp" %>

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
