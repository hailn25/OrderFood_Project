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
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="orderDetailId">Mã Chi Tiết Đơn Hàng</label>
                                                        <div class="form-control">123</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="orderId">Mã Đơn Hàng</label>
                                                        <div class="form-control">4213</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="productId">Mã Sản Phẩm</label>
                                                        <div class="form-control">2</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="quantity">Số Lượng</label>
                                                        <div class="form-control">2000</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="totalMoney">Tổng Tiền</label>
                                                        <div class="form-control">12.00</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="paymentBy">Thanh Toán Bằng</label>
                                                        <div class="form-control">VNpay</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="paymentStatus">Trạng Thái Thanh Toán</label>
                                                        <div class="form-control">Thành công</div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="updateDate">Ngày Cập Nhật</label>
                                                        <div class="form-control">13/01/2003</div>
                                                    </div>
                                                </div>
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
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>

