

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

    </head>

    <body>

        <%@include file="Header.jsp" %>

        <div class="container mt-5">
            <div class="row header">
                <div class="col-md-6 header-left" style="background-image: url('img/anhnhahang.jpg'); margin-top: 150px;">
                    <div class="header-left-detail">
                        <img src="img/admin1.png" alt="Không thể tải ảnh"/>
                        <h5>Nestlé Chính hãng</h5>
                    </div>
                    <div class="header-left-detail-chat">
                        <i class="far fa-comments"></i>
                        <a href="#">Chat</a>
                    </div>
                </div>

                <div class="col-md-6 header-right" style="margin-top: 150px;">
                    <div class="header-right-detail">
                        <i class="fas fa-store"></i>
                        <p>Sản phẩm: <span>1,3k</span></p>
                    </div>
                    <div class="header-right-detail">
                        <i class="fas fa-star"></i>
                        <p>Đánh Giá: <span>4.9 (540k Đánh Giá)</span></p>
                    </div>
                    <div class="header-right-detail">
                        <i class="fas fa-user-check"></i>
                        <p>Tham Gia: <span>6 Năm Trước</span></p>
                    </div>
                </div>
            </div>

            <div class="row justify-content-center">
                <div class="col-lg-12">
                    <div class="row g-4 justify-content-center">
                        <c:forEach items="${listProductDTO}" var="p">
                            <c:if test="${p.quantity >= 1}">
                                <div class="col-6 col-md-4 col-lg-2-4">
                                    <div class="rounded position-relative fruite-item-restaurant" style="height: 400px;">
                                        <div class="fruite-img-restaurant">
                                            <a href="detail?pid=${p.producId}">
                                                <img src="img/${p.imageURL}" class="img-fluid w-100 rounded-top" alt="Không thể tải ảnh" style="height: 200px; object-fit: cover;">
                                            </a>
                                        </div>
                                        <c:if test="${p.isSale == true}">
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Đang giảm giá</div>
                                        </c:if>
                                        <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                            <div style="height: 60px; overflow: hidden;">
                                                <a href="detail?pid=${p.producId}" style="color: black; font-weight: bold; font-size: 18px;">${p.name}</a>
                                            </div>
                                            <div style="display: flex; justify-content: space-between;">
                                                <h6 style="display: flex; align-items: center; font-family: sans-serif;" id="price-${p.producId}">${p.price}</h6>
                                                <div style="display: flex;">
                                                    <form action="addtocart" method="post">
                                                        <input type="hidden" name="productId" value="${p.producId}">
                                                        <button type="submit" class="text-primary" style="margin-right: 5px; border: 2px solid black; border-radius: 8px; height: 40px; width: 40px;" title="Thêm vào giỏ hàng">
                                                            <i class="fa fa-shopping-bag" title="Thêm vào giỏ hàng"></i>
                                                        </button>
                                                    </form>
                                                    <div class="d-flex justify-content-between flex-lg-wrap" style="margin-right: 10px;">
                                                        <img src="img/${p.restaurantImage}" style="height: 40px; width: 40px; border: 2px solid black; border-radius: 8px;">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                        <div class="col-12">
                            <div class="pagination d-flex justify-content-center mt-5">
                                <a href="restaurant?page=${1}" class="rounded">&laquo;</a>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                    <a href="restaurant?page=${i}" class="${currentPage == i ? 'active rounded' : 'rounded'}">${i}</a>
                                </c:forEach>
                                <a href="restaurant?page=${totalPages}" class="rounded">&raquo;</a>
                            </div>
                        </div>
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
            document.addEventListener('DOMContentLoaded', function () {
                const prices = document.querySelectorAll('[id^="price-"]');

                prices.forEach(priceElement => {
                    const priceId = priceElement.id.split('-')[1];
                    const priceValue = parseFloat(priceElement.textContent.replace(/[^0-9.-]+/g, "")); // Chuyển đổi giá trị thành số

                    const formattedPrice = (priceValue * 1000).toLocaleString('vi-VN');

                    // Cập nhật nội dung của thẻ h6
                    priceElement.textContent = formattedPrice + " VNĐ";
                });
            });
        </script>
    </body>

</html>
