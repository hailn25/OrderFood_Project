<%-- 
    Document   : FlashSale
    Created on : Jun 26, 2024, 9:21:09 AM
    Author     : hailt
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
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

            @keyframes appear{
                from{
                    opactity: 0;
                    scale: 0.5;
                }
                to {
                    opacity: 1;
                    scale: 1;
                }
            }

            #Block{
                animation: appear linear;
                animation-timeline: view();
                animation-range: entry 0% cover 40%;
            }


            .status{
                margin: auto 10px;
                color: #fff;
                background: #CB1C21;
                border-radius: 5px;
                padding: 0 8px;
                line-height: 24px;
                font-size: 16px;
                font-weight: 400;
                white-space: nowrap;
            }
            .col:hover {
                cursor: pointer;
            }
            .selected{
                background: #ff7604;
            }
            .hideP {
                display: none;
            }
            .showP {
                display: block;
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
                            <a href="shop" class="nav-item nav-link">Lọc sản phẩm</a>
                            <a href="flsale" class="nav-item nav-link active">Flash Sale</a>
                            <a href="blog" class="nav-item nav-link">Blog</a>
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

        <div class="container">
            <img src="img/flashsale.jpg" class="img-fluid rounded" style="width: 100%; height: 20%; margin-top: 170px; margin-bottom: 10px" alt="Image">
        </div>

        <header>
            <div class="container">
                <div class="row " style="background: #81C408">
                    <!-- Khung thời gian 1 -->
                    <div class="col" id="timeSlot1" onclick="window.location = 'flsale?timeFrame=1'" >
                        <span style="color: white;font-size: 30px" >10:00-13:00</span><br>
                        <span class="status" id="status1"></span>
                    </div>
                    <!-- Khung thời gian 2 -->
                    <div class="col" id="timeSlot2" onclick="window.location = 'flsale?timeFrame=2'" >
                        <span style="color: white;font-size: 30px" >13:00-16:00</span><br>
                        <span class="status"  id="status2"></span>
                    </div>
                    <!-- Khung thời gian 3 -->
                    <div class="col" id="timeSlot3" onclick="window.location = 'flsale?timeFrame=3'" >
                        <span style="color: white;font-size: 30px" >16:00-19:00</span><br>
                        <span class="status"  id="status3"></span>
                    </div>
                    <div class="col" id="timeSlot4" onclick="window.location = 'flsale?timeFrame=4'" >
                        <span style="color: white;font-size: 30px" >19:00-22:00</span><br>
                        <span class="status"  id="status4"></span>
                    </div>
                    <div class="col-md-3">
                        <span style="color: #cc0000;font-size: 29px;margin-left: 40px" id="mess" ></span><br>
                        <span id="time" style="color: #fff;font-size: 20px;margin-left: 60px"></span>
                    </div>
                </div>
            </div>
        </header>
        <div class="container-fluid fruite py-5">
            <div class="container py-5">                  
                <div class="tab-content">
                    <div id="tab-1" class="tab-pane fade show p-0 active">
                        <div class="row g-4">
                            <div class="col-lg-12">
                                <div class="row g-4" id="product-container">
                                    <c:forEach items="${listPS}" var="p">
                                        <div class="col-md-6 col-lg-4 col-xl-3" id="Block">
                                            <div class="rounded position-relative fruite-item">
                                                <div class="fruite-img">
                                                    <a href="psdetail?pid=${p.productId}&ptimeframe=${p.timeFrame}" style="color: black;">
                                                        <img src="img/${p.imageURL}" class="img-fluid w-100 rounded-top" alt="Không thể tải ảnh" style="height: 280px;">
                                                    </a>
                                                </div>
                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px; color: red;font-family: cursive">Flash Sale</div>
                                                <div class="discount-tag position-absolute text-white bg-danger px-2 py-1 rounded" style="top: 10px; right: 10px;">
                                                    -<fmt:formatNumber value="${p.discount * 100}" type="number" maxFractionDigits="0"/>% 
                                                </div>
                                                <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                    <h4>
                                                        <a href="psdetail?pid=${p.productId}&ptimeframe=${p.timeFrame}" style="color: black;">${p.name}</a>
                                                    </h4>
                                                    <h5 class="text-danger text-decoration-line-through" style="display: flex; align-items: center; font-family: sans-serif; font-size: 15px;color: orange" id="price-${p.productId}">
                                                        <c:out value="${p.price}"/>
                                                    </h5>
                                                    <div class="d-flex justify-content-between align-items-center mt-auto">
                                                        <p style="display: flex; align-items: center;font-family: sans-serif;"id="price-${p.productId}">${p.salePrice}</p>
                                                        <form id="${p.productId}" onsubmit="addToCart(${p.productId}); return false;">
                                                            <input type="hidden" name="productId" value="${p.productId}">
                                                            <button type="submit" class="text-primary " style="margin-right: 0px; border: 2px solid black; border-radius: 8px; height: 40px; width: 40px;" title="Thêm vào giỏ hàng">
                                                                <i class="fa fa-shopping-bag" title="Thêm vào giỏ hàng"></i>
                                                            </button>
                                                        </form>

                                                        <div class="d-flex justify-content-between flex-lg-wrap" style="margin-right: 5px; ">
                                                            <a href="restaurant?restaurantId=${p.restaurantId}&page=${1}">
                                                                <img src="img/${p.restaurantImage}" style="height: 40px; width: 40px; border: 2px solid black; border-radius: 8px;">
                                                            </a>
                                                        </div>
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

        <jsp:include page="Footer.jsp"></jsp:include>




        <script type="text/javascript">
            // Biến toàn cục để lưu khung thời gian được chọn
            var selectedTimeSlot = null;

            // Hàm để xử lý sự kiện nhấp chuột vào một khung thời gian
            function getParamFromURL(paramName) {
                var url = new URL(window.location.href);
                return url.searchParams.get(paramName);
            }
            selectedTimeSlot = getParamFromURL("timeFrame");

            // Hàm để cập nhật trạng thái và đếm ngược
            function updateStatusAndCountdown() {
                var now = new Date();
                var hour = now.getHours();
                var countdown = new Date(now);

                var timeSlots = document.querySelectorAll('.col');
                for (var i = 0; i < timeSlots.length; i++) {
                    timeSlots[i].classList.remove('selected');
                }

                var selectedTimeSlot = getParamFromURL("timeFrame");

                var timeSlot, status;

                if (hour < 10) {
                    timeSlot = document.getElementById('timeSlot1');
                    status = 'Sắp diễn ra';
                    document.getElementById("status1").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status2").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status3").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status4").innerHTML = 'Sắp diễn ra';
                    document.getElementById("mess").innerHTML = 'Diễn ra sau';
                    countdown.setHours(10, 0, 0, 0); // Set the countdown time to 10:00
                } else if (hour < 13) {
                    timeSlot = document.getElementById('timeSlot1');
                    document.getElementById("status1").innerHTML = 'Đang diễn ra';
                    document.getElementById("status2").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status3").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status4").innerHTML = 'Sắp diễn ra';
                    document.getElementById("mess").innerHTML = 'Kết thúc trong';
                    status = 'Đang diễn ra';
                    countdown.setHours(13, 0, 0, 0); // Set the countdown time to 13:00
                } else if (hour < 16) {
                    timeSlot = document.getElementById('timeSlot2');
                    status = 'Sắp diễn ra';
                    document.getElementById("status1").innerHTML = 'Đã kết thúc';
                    document.getElementById("status2").innerHTML = 'Đang diễn ra';
                    document.getElementById("status3").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status4").innerHTML = 'Sắp diễn ra';
                    document.getElementById("mess").innerHTML = 'Kết thúc trong';
                    document.getElementById('timeSlot1').style.pointerEvents = 'none';
                    countdown.setHours(16, 0, 0, 0); // Set the countdown time to 16:00
                } else if (hour < 19) {
                    timeSlot = document.getElementById('timeSlot3');
                    document.getElementById("status1").innerHTML = 'Đã kết thúc';
                    document.getElementById("status2").innerHTML = 'Đã kết thúc';
                    document.getElementById("status3").innerHTML = 'Đang diễn ra';
                    document.getElementById("status4").innerHTML = 'Sắp diễn ra';
                    document.getElementById("mess").innerHTML = 'Kết thúc trong';
                    document.getElementById('timeSlot1').style.pointerEvents = 'none';
                    document.getElementById('timeSlot2').style.pointerEvents = 'none';
                    status = 'Đang diễn ra';
                    countdown.setHours(19, 0, 0, 0); // Set the countdown time to 19:00
                } else if (hour < 22) {
                    timeSlot = document.getElementById('timeSlot4');
                    document.getElementById("status1").innerHTML = 'Đã kết thúc';
                    document.getElementById("status2").innerHTML = 'Đã kết thúc';
                    document.getElementById("status3").innerHTML = 'Đã kết thúc';
                    document.getElementById("status4").innerHTML = 'Đang diễn ra';
                    document.getElementById("mess").innerHTML = 'Kết thúc trong';
                    document.getElementById('timeSlot1').style.pointerEvents = 'none';
                    document.getElementById('timeSlot2').style.pointerEvents = 'none';
                    document.getElementById('timeSlot3').style.pointerEvents = 'none';
                    status = 'Đang diễn ra';
                    countdown.setHours(22, 0, 0, 0); // Set the countdown time to 22:00
                } else {
                    timeSlot = document.getElementById('timeSlot1');
                    status = 'Sắp diễn ra';
                    document.getElementById("status1").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status2").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status3").innerHTML = 'Sắp diễn ra';
                    document.getElementById("status4").innerHTML = 'Sắp diễn ra';
                    document.getElementById("mess").innerHTML = 'Diễn ra sau';
                    document.getElementById('timeSlot1').style.pointerEvents = 'none';
                    document.getElementById('timeSlot2').style.pointerEvents = 'none';
                    document.getElementById('timeSlot3').style.pointerEvents = 'none';
                    document.getElementById('timeSlot4').style.pointerEvents = 'none';
                    countdown.setHours(10, 0, 0, 0); // Set the countdown time to 10:00 next day
                    countdown.setDate(countdown.getDate() + 1);
                }

                if (selectedTimeSlot !== null) {
                    document.getElementById('timeSlot' + selectedTimeSlot).classList.add('selected');
                    timeSlot = document.getElementById('timeSlot' + selectedTimeSlot);
                    if (selectedTimeSlot === '1') {
                        countdown.setHours(13, 0, 0, 0);
                    } else if (selectedTimeSlot === '2') {
                        countdown.setHours(16, 0, 0, 0);
                    } else if (selectedTimeSlot === '3') {
                        countdown.setHours(19, 0, 0, 0);
                    } else if (selectedTimeSlot === '4') {
                        countdown.setHours(22, 0, 0, 0);
                    }
                } else {
                    timeSlot.classList.add('selected');
                }

                var itemsSale = document.querySelectorAll('.showP');
                var hidden = document.querySelectorAll('.hideP');

                if (status === 'Sắp diễn ra') {
                    if (selectedTimeSlot === '1') {
                        countdown.setHours(13, 0, 0, 0);
                    } else if (selectedTimeSlot === '2') {
                        countdown.setHours(16, 0, 0, 0);
                    } else if (selectedTimeSlot === '3') {
                        countdown.setHours(19, 0, 0, 0);
                    } else if (selectedTimeSlot === '4') {
                        countdown.setHours(22, 0, 0, 0);
                    }
                    for (var i = 0; i < itemsSale.length; i++) {
                        itemsSale[i].style.display = 'none';
                    }
                } else {
                    for (var i = 0; i < hidden.length; i++) {
                        hidden[i].style.display = 'none';
                    }
                }

                var countdownElement = document.getElementById('time');
                var countdownTime = countdown - now;

                var hours = Math.floor((countdownTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((countdownTime % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((countdownTime % (1000 * 60)) / 1000);

                hours = String(hours).padStart(2, '0');
                minutes = String(minutes).padStart(2, '0');
                seconds = String(seconds).padStart(2, '0');

                countdownElement.textContent = hours + ": " + minutes + ": " + seconds;
            }

            setInterval(updateStatusAndCountdown, 1000);

            document.addEventListener('DOMContentLoaded', function () {
                const prices = document.querySelectorAll('[id^="price-"]');

                prices.forEach(priceElement => {
                    const priceId = priceElement.id.split('-')[1]; // Extract the productId from the element's id
                    const discount50Button = document.getElementById('discount-50-' + priceId);
                    const discount25Button = document.getElementById('discount-25-' + priceId);
                    const originalPrice = parseFloat(priceElement.dataset.originalPrice);

                    discount50Button.addEventListener('click', function () {
                        const discountedPrice = originalPrice * 0.5;
                        priceElement.innerText = discountedPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                    });

                    discount25Button.addEventListener('click', function () {
                        const discountedPrice = originalPrice * 0.75;
                        priceElement.innerText = discountedPrice.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'});
                    });
                });
            });


            document.addEventListener('DOMContentLoaded', function () {
                const prices = document.querySelectorAll('[id^="price-"]');

                prices.forEach(priceElement => {
                    const priceId = priceElement.id.split('-')[1]; // Lấy ID sản phẩm
                    const priceValue = parseFloat(priceElement.textContent.replace(/[^0-9.-]+/g, "")); // Chuyển đổi giá trị thành số

                    // Định dạng giá thành VND
                    const formattedPrice = (priceValue * 1000).toLocaleString('vi-VN');

                    // Cập nhật nội dung của thẻ h6
                    priceElement.textContent = formattedPrice + " VNĐ";
                });
            });

        </script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
