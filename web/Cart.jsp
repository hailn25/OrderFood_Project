<%-- 
    Document   : Cart
    Created on : May 26, 2024, 2:54:53 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                                    <th scope="col">Products</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Price</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Handle</th>
                                </tr>
                            </thead>

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
                                        </div>
                                    </c:if>
                                    <div class="input-group  mt-4" style="width: 100px; border: none">
                                        <div class="input-group-btn" style="border: none;">
                                            <button><a href="process?num=-1&id=${i.product.productId}" style="border: none">-</a></button>
                                        </div>
                                        <input type="text" style="width: 40px" readonly value="${i.quantity}"/>
                                        <div class="input-group-btn">
                                            <button><a href="process?num=1&id=${i.product.productId}">+</a></button>
                                        </div>
                                    </div>
                                </td>

                                <td>

                                    <p class="mb-0 mt-4">
                                        <fmt:formatNumber value="${i.quantity*i.price}" maxFractionDigits="3"/>
                                    </p>
                                </td>
                                <td>
                                    <!--                                    <form action="process" method="post">
                                    
                                                                            <input type="hidden" name="id" value="${i.product.productId}"/>
                                                                            <input style="cursor: pointer; margin-top: 20px"  type="submit" value="Delete"/>
                                    
                                    
                                                                        </form>-->
                                    <form id="deleteForm${i.product.productId}" action="process" method="post">
                                        <input type="hidden" name="id" value="${i.product.productId}"/>
                                        <input style="cursor: pointer; margin-top: 20px" type="button" value="Delete" onclick="confirmDelete(event, 'deleteForm${i.product.productId}')"/>
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
                    <div class="col-8"></div>
                    <div class="row g-4 justify-content-end">
                        <div class="col-8"></div>
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
                                        <fmt:formatNumber value="${subtotal}" maxFractionDigits="2"/>
                                    </div>
                                    <div class="mb-4">
                                        <p class="mb-0 text-dark py-4">Shipping</p>
                                        <div class="form-check text-start">
                                            <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-1" name="Shipping" value="Free">
                                            <label class="form-check-label" for="Shipping-1">Free Shipping</label>
                                        </div>
                                        <div class="form-check text-start">
                                            <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-2" name="Shipping" value="FlatRate">
                                            <label class="form-check-label" for="Shipping-2">Flat rate: $15.00</label>
                                        </div>
                                        <div class="form-check text-start">
                                            <input type="checkbox" class="form-check-input bg-primary border-0" id="Shipping-3" name="Shipping" value="LocalPickup">
                                            <label class="form-check-label" for="Shipping-3">Local Pickup: $8.00</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
                                    <h5 class="mb-0 ps-4 me-4">Total</h5>
                                    <fmt:formatNumber value="${subtotal}" maxFractionDigits="2"/>
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

        <!-- Cart Page End -->

        <jsp:include page="Footer.jsp"></jsp:include>
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
        <script src="js/main.js"></script>
    </body>

</html>
