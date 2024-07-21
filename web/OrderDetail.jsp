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
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto" style="background-color: #81C408;">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chi tiết đơn hàng</h2>
                            </div>
                        </div>        
                        <div class="col-12">
                            <table class="table table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>Ảnh</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá</th>
                                        <th>Số lượng</th>
                                        <th>Tổng</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOrderDetailsByAccountId}" var="orderList">
                                        <tr>
                                            <td><img src="img/${orderList.imageURL}" alt="${orderList.productName}" width="100"></td>
                                            <td>${orderList.productName}</td>
                                            <td id="price-${orderList.orderId}">${orderList.price}</td>
                                            <td>${orderList.quantity}</td>
                                            <td id="price-${orderList.orderId}">${orderList.price * orderList.quantity}</td>
                                            <c:if test="${orderList.orderStatus == 3}">
                                                <td><a href="insertFeedback?accountId=${sessionScope.account.accountId}&productId=${orderList.productId}" class="rating-button">Đánh giá</a></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </tbody>

                                <!-- Hiển thị tổng đơn hàng ở dưới cùng -->
                                <tfoot>

                                    <tr>
                                        <td colspan="5" style="text-align: right; font-weight: bold; font-size: 30px;">
                                            Tổng đơn hàng: <span id="totalAmount">${listOrderDetailsByAccountId[0].totalPrice}</span>
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>          
                        </div>
                        <div class="text-center">
                            <a href="javascript:history.back()" class="btn btn-primary">Quay lại</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
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
            });
        </script>
        <script>
            // Hàm định dạng số theo định dạng VNĐ và bỏ ba số 0 cuối cùng
            function formatCurrency(amount) {
                // Chuyển đổi số thành chuỗi và cắt bỏ dấu phẩy thập phân
                let number = parseFloat(amount);

                // Chia số cho 1000 và làm tròn xuống (bỏ ba số 0 cuối cùng)
                number = Math.floor(number / 1000);

                // Định dạng số với dấu chấm phân cách
                return number.toLocaleString('vi-VN');
            }

            // Lấy giá tiền từ phần tử và định dạng lại
            let totalAmountElement = document.getElementById('totalAmount');
            let totalAmount = totalAmountElement.textContent;
            totalAmountElement.textContent = formatCurrency(totalAmount) + ' VNĐ';
        </script>
    </body>
</html>
