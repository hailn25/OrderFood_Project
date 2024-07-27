<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!--day la icon edit, delete-->
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"> <!--day la table cua bang manager-->
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <title>4FOODHD</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />


        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">

        <style>
            img{
                width: 150px;
                height: 150px;
            }
            .btn {
                display: inline-block;
                padding: 5px 10px;
                font-size: 16px;
                font-weight: bold;
                color: white;
                background-color: #28a745;
                border: none;
                border-radius: 5px;
                text-align: center;
                text-decoration: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }

            .btn-blue {
                background-color: #74C0FC;
                color: white;
            }

            .btn-green {
                background-color: #28a745;
                color: white;
            }

            .btn-green:hover {
                background-color: #218838;
                color: white;
            }

            .btn-blue:hover {
                background-color: #58A6FF;
                color: white;
            }

            .btn-red {
                background-color: #dc3545;
                color: white;
            }

            .btn-red:hover {
                background-color: #c82333;
                color: white;
            }
            .custom-star {
                color: #FFB524 !important;
            }
        </style>
        <style>
            .flash-sale {
                color: green; /* Màu xanh cho Đang FlashSale */
            }

            .rejected {
                color: red; /* Màu đỏ cho Bị từ chối */
            }

            .ended {
                color: gray; /* Màu xám cho Đã hết FlashSale */
            }

            .pending-1 {
                color: orange; /* Màu cam cho Chờ xác nhận */
            }

        </style>
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">

        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">

                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link " href="ManagerStaff.jsp">
                                    <i class="fas fa-home"></i> Trang chủ
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link" href="managerCategory">
                                    <i class="fas fa-bookmark"></i> Loại sản phẩm
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerBlog">
                                    <i class="far fa-file-alt"></i> Quản lý blog
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerService">
                                    <i class="fas fa-sliders-h"></i> Dịch vụ 
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerReport">
                                    <i class="far fa-comment-dots"></i> Quản lý báo cáo
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link active" href="managerProductFlashSale">
                                    <i class="far fa-clock"></i> Flash Sale
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerVoucher">
                                    <i class="fas fa-ticket-alt"></i> Quản lý mã giảm giá
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="loadListRequestVoucher">
                                    <i class="fas fa-tasks"></i> Yêu cầu thêm voucher
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <c:if test = "${sessionScope.account == null}"> 
                                <a class="nav-link d-block" href="Login.jsp">
                                    <b>Đăng nhập</b>
                                </a>

                            </c:if> 
                            <c:if test = "${sessionScope.account != null}"> 
                                <a class="nav-link d-block" href="logout">
                                    <b>Đăng xuất</b>
                                </a>
                            </c:if> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>



        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>Flash Sale</b></h2>
                        </div>
                        <div class="col-sm-3 ml-auto" style="max-width: 250px; margin-right: 10px">
                            <input id="date" name="date" type="date" required class="form-control validate" />
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên sản phẩm</th>
                            <th>Ảnh</th>
                            <th>Trạng thái</th>
                            <th>Giá đã giảm</th>
                            <th>Khung thời gian</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listF}" var="f">

                            <tr>

                                <td>${f.productId}</td>
                                <td> ${f.name}</td>
                                <td>
                                    <img src="img/${f.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td style="color: red;">
                                    <c:choose>
                                        <c:when test="${f.isFlashSale == 0}">
                                            <span class="pending-1">Chờ xác nhận</span>
                                        </c:when>
                                        <c:when test="${f.isFlashSale == 1}">
                                            <span class="flash-sale">Đang Flash Sale</span>
                                        </c:when>
                                        <c:when test="${f.isFlashSale == 2}">
                                            <span class="rejected">Bị từ chối</span>
                                        </c:when>
                                        <c:when test="${f.isFlashSale == 3}">
                                            <span class="ended">Đã hết FlashSale</span>
                                        </c:when>

                                    </c:choose>
                                </td>
                                <td><fmt:formatNumber value="${f.salePrice }" type="number" maxFractionDigits="0"/> VNĐ</td>

                                <td>
                                    <c:choose>
                                        <c:when test="${f.timeFrame == 1}">
                                            10h --> 13h
                                        </c:when>
                                        <c:when test="${f.timeFrame == 2}">
                                            13h --> 16h
                                        </c:when>
                                        <c:when test="${f.timeFrame == 3}">
                                            16h --> 19h
                                        </c:when>
                                        <c:when test="${f.timeFrame == 4}">
                                            19h --> 22h
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="viewDetailRequestFlashSale?vid=${f.productId}" class="btn btn-blue" title="Xem chi tiết"><i class="far fa-eye" ></i></a>
                                    <c:if test = "${f.isFlashSale == 0}"> 
                                        <a href="changeStatusFlashSale?changeStatus=${1}&pid=${f.productId}" class="btn btn-green" title="Xác nhận"><i class="fas fa-check"></i></a>
                                        <a href="changeStatusFlashSale?changeStatus=${2}&pid=${f.productId}" class="btn btn-red" title="Từ chối" onclick="confirmDelete(event)"><i class="fas fa-times"></i></a>
                                    </c:if> 
                                </td>

                            </tr>

                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>



        <!--cai nay cua ProGear-->
        <script src="js/manager_1.js" type="text/javascript"></script>

        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
        <script>
                                        new DataTable('#example');

                                        function confirmDelete(event) {
                                            event.preventDefault();
                                            var confirmAction = confirm("Bạn có muốn từ chối yêu cầu này không?");
                                            if (confirmAction) {
                                                window.location.href = event.target.closest('a').href;
                                            }
                                        }
        </script>
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
        </script>
        <script>
                    $(document).ready(function(){
            $('#date').on('change', function(){
            var dateValue = $(this).val();
                    $.ajax({
                    url: 'managerProductFlashSale',
                            type: 'POST',
                            data: { date: dateValue },
                            success: function(response) {
                            console.log('Date sent successfully');
                                    $('#example tbody').html($(response).find('#example tbody').html());
                            },
                            error: function(xhr, status, error) {
                            console.error('Error: ' + error);
                            }
                    });
            });
            });
        </script>
    </body>
</html>
