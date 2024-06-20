<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Order Details - Dashboard Admin Template</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min_1.css">
        <link rel="stylesheet" href="css/templatemo-style.css">
    </head>
    <body style="background-color: #F6F6F6">
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chi tiết đơn hàng</h2>
                            </div>
                        </div>
                        <div class="row tm-edit-product-row">

                            <div class="col-md-6">

                                <div class="form-group mb-3">
                                    <label for="name">Họ và tên</label>
                                    <input id="name" name="name" type="text" required value="${listV.name}" class="form-control validate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="phone">Số điện thoại</label>
                                    <input id="phone" name="phone" type="text" required value="${listV.phone}" class="form-control validate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="paymentMethod">Hình thức thanh toán</label>
                                    <input id="paymentMethod" name="paymentMethod" type="text" required value="${listV.paymentBy}" class="form-control validate">
                                </div>
                                <div class=" form-group mb-3">
                                    <label for="purchaseDate">Ngày mua</label>
                                    <input id="purchaseDate" name="purchaseDate" type="text" required value="${listV.createDate}" class="form-control validate">
                                </div>

                            </div>
                            <div class="col-md-6">

                                <div class="form-group  mb-3">
                                    <label for="email">Email</label>
                                    <input id="email" name="email" type="text" required value="${listV.email}" class="form-control validate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="address">Địa chỉ</label>
                                    <input id="address" name="address" type="text" required value="${listV.address}" class="form-control validate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="paymentStatus">Trạng thái thanh toán</label>
                                    <input id="paymentStatus" name="paymentStatus" type="text" required value="${listV.paymentStatus}" class="form-control validate">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="description">Ghi chú</label>
                                    <textarea class="form-control tm-small" rows="1" name="description" required>${listV.note}</textarea>
                                </div>

                            </div>

                        </div>
                        <div class="col-12">
                            <table class="table table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Tổng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrderDetail}" var="order">
                                        <tr>
                                            <td>${order.productName}</td>
                                            <td>${order.price}</td>
                                            <td>${order.quantity}</td>
                                            <td>${order.price * order.quantity}</td> 
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="text-center">
                            <h1 style="color: white; margin-top: 20px;">Tổng đơn hàng: ${listOrderDetail[0].totalMoney}</h1>
                        </div>
                        <div class="text-center">
                            <a href="managerShipper" class="btn btn-primary">Thoát</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
