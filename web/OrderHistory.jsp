<%-- 
    Document   : OrderHistory
    Created on : Jun 19, 2024, 10:51:49 AM
    Author     : ADMIN
--%>

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
                                                <div class="order-item">
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="ProductName">Tên sản phẩm</label>
                                                                <div class="form-control">${listOrder.productName}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="price">Giá</label>
                                                                <div class="form-control">${listOrder.price}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="quantity">Số lượng</label>
                                                                <div class="form-control">${listOrder.quantity}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="totalMoney">Tổng</label>
                                                                <div class="form-control">${listOrder.totalMoney}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row additional-details d-none">
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="accountName">Họ và tên</label>
                                                                <div class="form-control">${listOrder.accountName}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="email">Email</label>
                                                                <div class="form-control">${listOrder.email}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="phone">Số điện thoại</label>
                                                                <div class="form-control">${listOrder.phone}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="address">Địa chỉ</label>
                                                                <div class="form-control">${listOrder.address}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="paymentBy">Hình thức thanh toán</label>
                                                                <div class="form-control">${listOrder.paymentBy}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="paymentStatus">Trạng thái thanh toán</label>
                                                                <div class="form-control">${listOrder.paymentStatus}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="createDate">Ngày mua</label>
                                                                <div class="form-control">${listOrder.createDate}</div>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="note">Ghi chú</label>
                                                                <div class="form-control">${listOrder.note}</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <button class="btn btn-link show-more">Xem thêm</button>
                                                </div>
                                            <div class="additional-details mt-3">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <button type="button" class="btn btn-info btn-block" onclick="window.location.href = 'Feedback.jsp';">Phản hồi</button>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <button type="button" class="btn btn-danger btn-block" onclick="window.location.href = 'Report.jsp';">Tố cáo</button>
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

