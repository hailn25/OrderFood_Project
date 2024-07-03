<%-- 
    Document   : OrderHistory
    Created on : Jun 19, 2024, 10:51:49 AM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order History</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <title>Order Details</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .card-body {
                padding: 20px;
            }
            .more-details {
                display: none;
            }
        </style>
        <style>
            .additional-details.d-none {
                display: none;
            }
        </style>
        <style>
            .card-body {
                padding: 20px;
            }
            .more-details {
                display: none;
            }
            .order-row {
                display: flex;
                overflow-x: auto;
            }
            .order-card {
                min-width: 300px;
                margin-right: 15px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row flex-lg-nowrap">
                <div class="col-12 col-lg-auto mb-3" style="width: 200px;">
                    <div class="card p-3">
                        <div class="e-navlist e-navlist--active-bg">
                            <ul class="nav">
                                <li class="nav-item"><a class="nav-link px-3 active" href="home"><i class="fa fa-fw fa-bar-chart mr-1"></i><span>Home</span></a></li>
                                <li class="nav-item"><a class="nav-link px-3" href="profile"><i class="fa fa-fw fa-cog mr-1"></i><span>Profile</span></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <div class="row">
                        <div class="col mb-3">
                            <div class="card">
                                <div id="orderDetailsInfo" class="content-section">
                                    <div class="card">
                                        <div class="card-body">
                                            <h6 class="mb-3 text-primary">Lịch sử Đơn Hàng</h6>
                                            <div class="order-list">
                                                <c:forEach items="${listOrders}" var="l">
                                                    <div class="card mb-3 order-card">
                                                        <div class="order-info">
                                                            <div class="card-body">
                                                                <h5 class="card-title">Đơn Hàng</h5>
                                                                <div class="order-details">
                                                                    <div class="order-attribute">
                                                                        <strong>Tên sản phẩm:</strong> ${l.nameP}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Giá:</strong> ${l.price}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Số lượng:</strong> ${l.quantity}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Tổng:</strong> ${l.totalMoney}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Họ và tên:</strong> ${l.name}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Email:</strong> ${l.email}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Số điện thoại:</strong> ${l.phonel}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Địa chỉ:</strong> ${l.address}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Hình thức thanh toán:</strong> ${l.paymentBy}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Trạng thái thanh toán:</strong> ${l.paymentStatus}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Ngày mua:</strong> ${l.createDate}
                                                                    </div>
                                                                    <div class="order-attribute">
                                                                        <strong>Ghi chú:</strong> ${l.note}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <button type="button" class="btn btn-info feedback-button" onclick="window.location.href = 'Feedback.jsp';">Phản hồi</button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const showMoreButtons = document.querySelectorAll('.show-more');
                showMoreButtons.forEach(button => {
                    button.addEventListener('click', function () {
                        const additionalDetails = this.previousElementSibling;
                        additionalDetails.classList.toggle('d-none');
                        this.textContent = additionalDetails.classList.contains('d-none') ? 'Xem thêm' : 'Thu gọn';
                    });
                });
            });
        </script>
    </body>
</html>