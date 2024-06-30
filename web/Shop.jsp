

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


        <!-- Modal Search Start -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen">
                <div class="modal-content rounded-0">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Tìm kiếm</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body d-flex align-items-center">
                        <div class="input-group w-75 mx-auto d-flex">
                            <input type="search" class="form-control p-3" placeholder="Từ khoá tìm kiếm" aria-describedby="search-icon-1">
                            <span id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Search End -->

        <!-- Fruits Shop Start-->
        <div class="container-fluid fruite py-5" style="margin-top: 100px">
            <div class="container py-5">
                <h1 class="mb-4">Tìm kiếm</h1>

                <div class="row g-4">
                    <div class="col-lg-12">
                        <div class="row g-4">
                            <div class="col-xl-3">
                                <c:if test="${not empty error}">
                                    <div id="error-message" class="alert alert-danger mt-3">${error}</div>
                                </c:if>
                                <form action="shop" method="post">
                                    <div class="input-group w-100 mx-auto d-flex">
                                        <input type="search" class="form-control p-3" placeholder="Tên đồ ăn, đồ uống,..." aria-describedby="search-icon-1" name="productName" required="" value="${productName}">
                                        <button id="search-icon-1" class="input-group-text p-3"><i class="fa fa-search"></i></button>
                                    </div>
                                </form>
                                <form id="rangeForm" action="shop" method="POST" style="display: none;">
                                    <input type="hidden" id="hiddenRangeInput" name="rangeValue">
                                </form>
                                <div class="mb-3" style="margin: 10px">
                                    <h4 class="mb-2">Giá sản phẩm</h4>
                                    <input type="range" class="form-range w-100" id="rangeInput" name="rangeInput" min="0" max="1000" value="${not empty minPrice ? minPrice : 0}" oninput="updateAmount()" onchange="submitForm()">
                                    <output id="amount" name="amount" min-value="0" max-value="1000" for="rangeInput">
                                        <c:choose>
                                            <c:when test="${not empty minPrice}">
                                                <span id="formattedMinPrice"></span> - 1.000.000 VND
                                            </c:when>
                                            <c:otherwise>
                                                0 VND - 1.000.000 VND
                                            </c:otherwise>
                                        </c:choose>
                                    </output>
                                </div>

                            </div>
                            <div class="col-6"></div>
                            <!--                            <div class="col-xl-3">
                                                            <div class="bg-light ps-3 py-3 rounded d-flex justify-content-between mb-4">
                                                                <label for="fruits">Sắp xếp:</label>
                                                                <select id="fruits" name="fruitlist" class="border-0 form-select-sm bg-light me-3" form="fruitform">
                                                                    <option value="volvo">Không có</option>
                                                                    <option value="volvo">Theo ngày cập nhật</option>
                                                                </select>
                                                            </div>
                                                        </div>-->
                        </div>
                        <div class="row g-4">
                            <div class="col-lg-3">
                                <div class="row g-4">
                                    <div class="col-lg-12">
                                        <div class="mb-3">
                                            <h4>Phân Loại</h4>
                                            <ul class="list-unstyled fruite-categorie">
                                                <c:forEach items="${listTotalQuantityByCategory}" var="c">
                                                    <li>
                                                        <div class="d-flex justify-content-between fruite-name">
                                                            <a href="shop?categoryName=${c.name}"><i class="fas fa-utensils" style="margin-right: 5px;"></i>${c.name}</a>
                                                            <span>(${c.totalQuantity})</span>
                                                        </div>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="col-lg-12">
                                        <h4 class="mb-3">Nhà hàng nổi bật</h4>
                                        <c:forEach items="${listRestaurantDTO}" var="r" >
                                            <div class="d-flex align-items-center justify-content-start" style="margin: 10px;">
                                                <div class="rounded me-4" style="width: 100px; height: 100px;">
                                                    <a href="shop?restaurantId=${r.restaurantId}">
                                                        <img src="img/${r.imageAvatar}" class="img-fluid rounded" alt="Không thể tải ảnh" style="height: 80px; width: 80px; border: 2px solid black; border-radius: 8px">
                                                    </a>
                                                </div>
                                                <div>
                                                    <a href="shop?restaurantId=${r.restaurantId}" style="font-weight: bold;">${r.name}</a>
                                                    <div class="d-flex mb-2">
                                                        <c:forEach begin="1" end="5" var="i">
                                                            <c:choose>
                                                                <c:when test="${i <= r.rateStar}">
                                                                    <i class="fa fa-star text-secondary"></i>
                                                                </c:when>
                                                                <c:when test="${i - 0.5 == r.rateStar}">
                                                                    <i class="fa fa-star-half-alt text-secondary"></i>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <i class="far fa-star"  style="color: rgb(255, 181, 36);"></i>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </div>
                                                    <div class="d-flex mb-2">
                                                        <h5 class="fw-bold me-2">${r.address}</h5>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>

                                        <!--                                        <div class="d-flex justify-content-center my-4">
                                                                                    <a href="#" class="btn border border-secondary px-4 py-3 rounded-pill text-primary w-100">View More</a>
                                                                                </div>-->
                                    </div>
                                    <div class="col-lg-12">
                                        <div class="position-relative">
                                            <img src="img/banner-fruits.jpg" class="img-fluid w-100 rounded" alt="Không thể tải ảnh">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-9">
                                <c:choose>
                                    <c:when test="${not empty listProductDTO}">
                                        <div class="row g-4 justify-content-center">
                                            <c:forEach items="${listProductDTO}" var="p">
                                                <c:if test="${p.quantity >= 1}">
                                                    <div class="col-md-6 col-lg-6 col-xl-4">
                                                        <div class="rounded position-relative fruite-item">
                                                            <div class="fruite-img">
                                                                <a href="detail?pid=${p.producId}">
                                                                    <img src="img/${p.imageURL}" class="img-fluid w-100 rounded-top" alt="Không thể tải ảnh" style="height: 300px; object-fit: cover;">
                                                                </a>
                                                            </div>
                                                            <c:if test="${p.isSale == true}">
                                                                <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">Đang giảm giá</div>
                                                            </c:if>
                                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom" >
                                                                <div style="height: 80px;">
                                                                    <a href="detail?pid=${p.producId}" style="color: black; font-weight: bold; font-size: 20px;">${p.name}</a>
                                                                </div>

                                                                <div style="display: flex; justify-content: space-between;">
                                                                    <h6 style="display: flex; align-items: center;font-family: sans-serif;" id="price-${p.producId}">${p.price}</h6>   
                                                                    <div style="display: flex;">
                                                                        <form action="addtocart" method="post" >
                                                                            <input type="hidden" name="productId" value="${p.producId}">
                                                                            <button type="submit" class="text-primary " style="margin-right: 5px; border: 2px solid black; border-radius: 8px; height: 40px; width: 40px;" title="Thêm vào giỏ hàng">
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
                                                    <a href="shop?page=${1}" class="rounded">&laquo;</a>
                                                    <c:forEach var="i" begin="1" end="${totalPages}">
                                                        <a href="shop?page=${i}" class="${currentPage == i ? 'active rounded' : 'rounded'}">${i}</a>
                                                    </c:forEach>
                                                    <a href="shop?page=${totalPages}" class="rounded" >&raquo;</a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="display: flex; justify-content: center;">
                                            <img src="img/cantfind.png" width="100px" height="100px" alt="Không tìm thấy ảnh"/>
                                            <h3 style="display: flex; align-items: center;">Không tìm thấy sản phẩm</h3>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Fruits Shop End-->


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
                                        function updateAmount() {
                                            var rangeInput = document.getElementById('rangeInput');
                                            var amount = document.getElementById('amount');
                                            var value = rangeInput.value;

                                            // Định dạng giá trị với dấu chấm phân tách hàng nghìn
                                            var formattedValue = (value * 1000).toLocaleString('vi-VN') + ' VND - 1.000.000 VND';

                                            amount.value = formattedValue;
                                            amount.innerText = formattedValue;
                                        }

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

                                        document.addEventListener('DOMContentLoaded', function () {
                                            // Định dạng giá trị của minPrice khi trang được tải
                                            var minPrice = ${not empty minPrice ? minPrice : 0};
                                            var formattedMinPrice = (minPrice * 1000).toLocaleString('vi-VN');
                                            document.getElementById('formattedMinPrice').innerText = formattedMinPrice + ' VND';

                                            // Định dạng giá trị của thanh trượt khi trang được tải
                                            var rangeInput = document.getElementById('rangeInput');
                                            var amount = document.getElementById('amount');
                                            var value = rangeInput.value;
                                            var formattedValue = (value * 1000).toLocaleString('vi-VN') + ' VND - 1.000.000 VND';
                                            amount.value = formattedValue;
                                            amount.innerText = formattedValue;
                                        });

                                        function submitForm() {
                                            var rangeInput = document.getElementById('rangeInput').value;
                                            var hiddenRangeInput = document.getElementById('hiddenRangeInput');
                                            hiddenRangeInput.value = rangeInput;
                                            document.getElementById('rangeForm').submit();
                                        }
        </script>
    </body>

</html>
