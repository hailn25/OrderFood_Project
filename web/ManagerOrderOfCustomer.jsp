<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!--cai nay cua ProGear-->
        <title>4FOODHD</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!--day la icon edit, delete-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"> <!--day la table cua bang manager-->
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">
        <style>
            img {
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
            .status-waiting-for-shipper {
                color: orange;
            }
            .status-delivering {
                color: blue;
            }
            .status-delivered-successfully {
                color: green;
            }
            .status-customer-rejected {
                color: red;
            }
            .status-shipper-rejected {
                color: darkred;
            }
            .status-waiting-for-restaurant {
                color: purple;
            }
            .status-restaurant-rejected {
                color: brown;
            }
            .dropdown-menu {
                display: none;
                position: absolute;
                background-color: white;
                box-shadow: 0 8px 16px rgba(0,0,0,0.2);
                z-index: 1;
            }
            .dropdown-menu .dropdown-item {
                padding: 8px 16px;
                display: block;
                color: black;
                text-decoration: none;
            }
            .dropdown-menu .dropdown-item:hover {
                background-color: #ddd;
            }
            .show {
                display: block;
            }
            .status-filter-container {
                position: absolute;
                left: 35px;
                top: 130px; /* Adjust based on your layout */
                padding: 20px;
                background-color: #f8f9fa;
                border: 1px solid #ddd;
                border-radius: 5px;
                width: auto;
            }
            .status-filter label {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }
            .status-filter input[type="checkbox"] {
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                width: 16px;
                height: 16px;
                border: 1px solid #ccc;
                border-radius: 2px;
                outline: none;
                margin-right: 10px;
                cursor: pointer;
                position: relative;
            }
            .status-filter input[type="checkbox"]:checked::before {
                content: '\2713'; /* Checkmark character */
                display: block;
                text-align: center;
                font-size: 14px;
                line-height: 16px;
                color: white;
                background-color: #007bff; /* Change this color to your preference */
                border-radius: 2px;
                width: 100%;
                height: 100%;
            }
        </style>

    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">
        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link" href="HomeOfRestaurant.jsp">
                                    <i class="fas fa-home"></i> Trang chủ
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link" href="revenueRestaurant">
                                    <i class="fas fa-tachometer-alt"></i> Thống kê
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link" href="managerCategory">
                                    <i class="far fa-file-alt"></i> Loại sản phẩm
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">
                            <li class="nav-item">
                                <a class="nav-link " href="managerOpenProduct">
                                    <i class="fas fa-shopping-cart"></i> Sản phẩm đang bán
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <!--                            <li class="nav-item">
                                                            <a class="nav-link" href="managerCloseProduct">
                                                                <i class="fas fa-shopping-cart"></i> Sản phẩm đang ẩn
                                                            </a>
                                                        </li>-->
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerFlashSaleProduct" id="btn-viewListProduct">
                                    <i class="fas fa-bolt"></i> Sản phẩm đang FlashSale
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link active" href="managerOrderOfCustomer_0">
                                    <i class="far fa-file-alt"></i> Quản lý đơn hàng
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="Profile.jsp">
                                    <i class="far fa-user"></i> Tài khoản
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${sessionScope.account.roleId == 4}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="messageRestaurant?accountId=${sessionScope.account.accountId}">
                                    <i class="far fa-comments"></i> Tin nhắn
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




    </head>
<body>

    <div class="status-filter-container">
        <h4>Lọc theo trạng thái:</h4>
        <div class="status-filter">
            <label><input type="checkbox" class="status-checkbox" value="Đang chờ xác nhận của nhà hàng"> Đang chờ xác nhận của nhà hàng</label>
            <label><input type="checkbox" class="status-checkbox" value="Đang chờ xác nhận của shipper"> Đang chờ xác nhận của shipper</label>
            <label><input type="checkbox" class="status-checkbox" value="Đang giao hàng"> Đang giao hàng</label>
            <label><input type="checkbox" class="status-checkbox" value="Giao hàng thành công"> Giao hàng thành công</label>
            <label><input type="checkbox" class="status-checkbox" value="Shipper không nhận đơn"> Shipper không nhận đơn</label>
            <label><input type="checkbox" class="status-checkbox" value="Người mua không nhận hàng"> Người mua không nhận hàng</label>
            <label><input type="checkbox" class="status-checkbox" value="Nhà hàng không nhận đơn"> Nhà hàng không nhận đơn</label>
        </div>
    </div>
    <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Quản lý <b> đơn hàng</b></h2>
                    </div>
                </div>
            </div>
            <table id="orderTable" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Tên người dùng</th>
                        <th>Tổng giá trị</th>
                        <th>Trạng thái</th>
                        <th>Tác vụ</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listO}" var="o">
                        <tr>
                            <td>${o.orderId}</td>
                            <td>${o.name}</td>
                            <td><fmt:formatNumber value="${o.totalMoney}" pattern="###,### VNĐ" /></td>
                            <td class="<c:choose>
                                    <c:when test="${o.orderStatusId == 1}">status-waiting-for-shipper</c:when>
                                    <c:when test="${o.orderStatusId == 2}">status-delivering</c:when>
                                    <c:when test="${o.orderStatusId == 3}">status-delivered-successfully</c:when>
                                    <c:when test="${o.orderStatusId == 4}">status-customer-rejected</c:when>
                                    <c:when test="${o.orderStatusId == 5}">status-shipper-rejected</c:when>
                                    <c:when test="${o.orderStatusId == 6}">status-waiting-for-restaurant</c:when>
                                    <c:when test="${o.orderStatusId == 7}">status-restaurant-rejected</c:when>
                                    <c:otherwise>status-unknown</c:otherwise>
                                </c:choose>">
                                <c:choose>
                                    <c:when test="${o.orderStatusId == 1}">
                                        Đang chờ xác nhận của shipper
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 2}">
                                        Đang giao hàng
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 3}">
                                        Giao hàng thành công
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 4}">
                                        Người mua không nhận hàng
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 5}">
                                        Shipper không nhận đơn
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 6}">
                                        Đang chờ xác nhận của nhà hàng
                                    </c:when>
                                    <c:when test="${o.orderStatusId == 7}">
                                        Nhà hàng không nhận đơn
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <a href="viewOrderByRestaurant?oid=${o.orderId}" class="btn btn-blue" title="Xem chi tiết"><i class="far fa-eye"></i></a>
                                    <c:if test="${o.orderStatusId == 6}">
                                    <a href="confirmOrder?oid=${o.orderId}" class="btn btn-green" title="Xác nhận"><i class="fas fa-check"></i></a>
                                    <a href="cancelOrder?oid=${o.orderId}" onclick="confirmCancel(event)" class="btn btn-red" title="Từ chối"><i class="fas fa-times"></i></a>
                                    </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Link jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- Link DataTables JS -->
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script>
                                        $(document).ready(function () {
                                            var table = $('#orderTable').DataTable({
                                                "pageLength": 10,
                                                "order": [], // Tắt chức năng sắp xếp mặc định
                                                "columnDefs": [
                                                    {"orderable": false, "targets": 0}  // Vô hiệu hóa sắp xếp trên cột "Mã đơn hàng"
                                                ],
                                                "language": {
                                                    "search": "Tìm kiếm:",
                                                    "lengthMenu": "Hiển thị _MENU_ đơn hàng mỗi trang",
                                                    "zeroRecords": "Không tìm thấy đơn hàng",
                                                    "info": "Hiển thị trang _PAGE_ của _PAGES_",
                                                    "infoEmpty": "Không có đơn hàng nào",
                                                    "infoFiltered": "(lọc từ _MAX_ đơn hàng)",
                                                    "paginate": {
                                                        "first": "Đầu tiên",
                                                        "last": "Cuối cùng",
                                                        "next": "Tiếp",
                                                        "previous": "Trước"
                                                    }
                                                }
                                            });

                                            // Lắng nghe sự kiện thay đổi trên các checkbox
                                            $('.status-checkbox').on('change', function () {
                                                var selectedStatuses = [];
                                                $('.status-checkbox:checked').each(function () {
                                                    selectedStatuses.push($(this).val());
                                                });
                                                var searchValue = selectedStatuses.join('|'); // Sử dụng '|' để lọc nhiều giá trị
                                                table.column(3).search(searchValue, true, false).draw();
                                            });
                                        });
    </script>
    <!--cai nay cua ProGear-->
    <script src="js/manager_1.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
    <script>
                                        new DataTable('#example');
    </script>
    <script>
        function formatPrice(price) {
// Chuyển đổi số tiền thành dạng chuỗi và thêm dấu phân cách phần nghìn
            return price.toLocaleString('vi-VN') + ' đ';
        }
        function confirmDelete(event) {
            event.preventDefault();
            var confirmAction = confirm("Bạn có chắc chắn muốn ẩn sản phẩm này không?");
            if (confirmAction) {
                window.location.href = event.target.closest('a').href;
            }
        }
    </script>
</body>
</html>