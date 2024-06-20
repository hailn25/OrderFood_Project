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
                <div class="col-lg-8">
                    <!-- Blog Detail Start -->
                    <div class="mb-5">
                        <img class="img-fluid w-100 rounded mb-5" src="img/${blog.imageURL}" alt="Không thể tải ảnh">
                        <h1 class="text-uppercase mb-4">${blog.title}</h1>
                        <div style="display: flex;">
                            <p style="font-size: 22px; margin-right: 10px;"><i class="fas fa-user" style="margin-right: 10px"></i>${blog.nameUpdateBy}</p>
                            <p style="font-size: 22px; margin-right: 10px;"><i class="fas fa-calendar-alt" style="margin-right: 10px"></i>${blog.createDate}</p>
                        </div>
                        <p>${blog.content}</p>
                    </div>
                    <!-- Blog Detail End -->

                </div>


                <div class="col-lg-4">

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
    </body>

</html>
