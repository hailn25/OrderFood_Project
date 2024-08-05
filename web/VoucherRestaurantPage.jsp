<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>4FOODHD</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <script src="https://cdn.tailwindcss.com?plugins=forms,typography"></script>
        <script src="https://unpkg.com/unlazy@0.11.3/dist/unlazy.with-hashing.iife.js" defer init></script>

        <style type="text/tailwindcss">
            @layer base {
                :root {
                    --background: 0 0% 100%;
                    --foreground: 240 10% 3.9%;
                    --card: 0 0% 100%;
                    --card-foreground: 240 10% 3.9%;
                    --popover: 0 0% 100%;
                    --popover-foreground: 240 10% 3.9%;
                    --primary: 240 5.9% 10%;
                    --primary-foreground: 0 0% 98%;
                    --secondary: 240 4.8% 95.9%;
                    --secondary-foreground: 240 5.9% 10%;
                    --muted: 240 4.8% 95.9%;
                    --muted-foreground: 240 3.8% 46.1%;
                    --accent: 240 4.8% 95.9%;
                    --accent-foreground: 240 5.9% 10%;
                    --destructive: 0 84.2% 60.2%;
                    --destructive-foreground: 0 0% 98%;
                    --border: 240 5.9% 90%;
                    --input: 240 5.9% 90%;
                    --ring: 240 5.9% 10%;
                    --radius: 0.5rem;
                }
                .dark {
                    --background: 240 10% 3.9%;
                    --foreground: 0 0% 98%;
                    --card: 240 10% 3.9%;
                    --card-foreground: 0 0% 98%;
                    --popover: 240 10% 3.9%;
                    --popover-foreground: 0 0% 98%;
                    --primary: 0 0% 98%;
                    --primary-foreground: 240 5.9% 10%;
                    --secondary: 240 3.7% 15.9%;
                    --secondary-foreground: 0 0% 98%;
                    --muted: 240 3.7% 15.9%;
                    --muted-foreground: 240 5% 64.9%;
                    --accent: 240 3.7% 15.9%;
                    --accent-foreground: 0 0% 98%;
                    --destructive: 0 62.8% 30.6%;
                    --destructive-foreground: 0 0% 98%;
                    --border: 240 3.7% 15.9%;
                    --input: 240 3.7% 15.9%;
                    --ring: 240 4.9% 83.9%;
                }
                .active_logo {
                    border: 2px solid #007BFF; /* Viền màu xanh để làm nổi bật thẻ */
                    background-color: #E0F7FA; /* Màu nền nhẹ */
                    padding: 0px 10px; /* Tăng khoảng cách nền xung quanh nội dung */
                    border-radius: 10px; /* Tùy chọn: Bo tròn góc để nhìn đẹp hơn */
                }
                .text-center {
                    cursor: pointer;
                }
                .scrollable-list {
                    max-height: 600px; /* Thay đổi giá trị này theo nhu cầu của bạn */
                    overflow-y: auto; /* Cho phép cuộn dọc nếu nội dung vượt quá chiều cao */
                }

                .back-button {
                    display: inline-block; /* Đảm bảo thẻ a có kiểu dáng như một nút */
                    background-color: #007bff; /* Màu nền của nút (tùy chỉnh theo ý bạn) */
                    color: #ffffff; /* Màu chữ của nút */
                    padding: 10px 20px; /* Khoảng cách bên trong nút */
                    border: none; /* Không có đường viền */
                    border-radius: 5px; /* Bo góc cho nút */
                    cursor: pointer; /* Hiển thị con trỏ chuột khi di chuột qua nút */
                    font-size: 16px; /* Kích thước chữ */
                    text-align: center; /* Canh giữa chữ trong nút */
                    text-decoration: none; /* Xóa gạch chân mặc định của thẻ a */
                    margin-top: 30px; /* Khoảng cách trên nút */
                }

                .back-button:hover {
                    background-color: #0056b3; /* Màu nền của nút khi di chuột qua (hover) */
                }
            }
        </style>

        <style>
            .button-margin-top {
                margin-top: 10px;
            }
            .bg-card {
                max-width: 800px; /* Thay đổi giá trị này theo kích thước mong muốn */
                width: 100%; /* Đảm bảo nó chiếm toàn bộ chiều rộng của phần tử chứa */
            }
            .bg-green-500 {
                display: inline-block; /* Đảm bảo chỉ bao quanh nội dung */
                color: #ffffff; /* Màu chữ trắng */
                padding: 2px 8px; /* Khoảng cách bên trong */
                border-radius: 5px; /* Bo góc */
                margin-bottom: 8px; /* Khoảng cách phía dưới */
            }
            .voucher-name {
                text-transform: uppercase;
            }
        </style>

    </head>
    <body>

        <!-- Navbar start -->
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
                            <small class="text-white ms-2">Hello, ${username}</small>
                            <span class="text-white ms-2">|</span>
                            <a href="logout" class="text-white"><small class="text-white ms-2">Đăng xuất</small></a>
                        </c:if> 
                    </div>
                </div>
            </div>

        </div>

        <div style="margin-top: 50px" class="bg-background text-primary-foreground min-h-screen flex items-center justify-center">
            <div class="bg-card ">
                <div class="bg-card shadow-lg rounded-lg p-4 space-y-4">
                    <div class="flex justify-around">
                        <div class="text-center" data-href="loadVoucherFreeship">
                            <img src="img/IconFreeship.png" alt="Voucher Freeship" class="mx-auto mb-2 w-32 h-32 object-cover" />
                            <span class="text-sm text-muted-foreground">VOUCHER FREESHIP</span>
                        </div>
                        <div class="text-center active_logo" data-href="loadVoucherRestaurant">
                            <img src="img/IconVoucher.png" alt="Voucher Shop" class="mx-auto mb-2 w-32 h-32 object-cover" />
                            <span class="text-sm text-muted-foreground">VOUCHER RESTAURANT</span>
                        </div>
                    </div>
                    <div class="grid grid-cols-2 gap-4 p-4 scrollable-list">

                        <c:forEach items="${listVR}" var="o">
                            <div class="bg-card p-4 rounded-lg shadow-md">
                                <div class="flex items-center mb-4">
                                    <img style="width: 100px; height: 100px; border: 2px solid black; border-radius: 8px;" src="img/${o.imageURL}" alt="Không thể tải ảnh" class="w-12 h-12 rounded-full mr-4" />
                                    <span style="color: #45595B"  class="voucher-name text-lg font-bold mb-2">${o.restaurantName}</span>
                                </div>
                                <div class="text-card-foreground">
                                    <h2 class="text-lg font-bold mb-2">${o.description}</h2>
                                    <p class="text-sm text-muted-foreground font-bold">Ngày hết hạn: <fmt:formatDate value="${o.finishDate}" pattern="dd-MM-yyyy" /></p>
                                    <span class="block mt-2 text-xs text-blue-500">Tất cả hình thức thanh toán</span>

                                    <!--<button class="button-margin-top bg-destructive text-destructive-foreground px-4 py-1 rounded-full">Lưu</button>-->
                                    <c:choose>
                                        <c:when test="${o.hasVoucher}">
                                            <!-- Nút "Đã lấy" hiển thị khi người dùng đã có voucher -->
                                            <button class="button-margin-top bg-gray-500 text-primary-foreground px-4 py-2 rounded-lg" disabled>Đã lấy</button>
                                        </c:when>
                                        <c:when test="${o.quantity == 0}">
                                            <!-- Nút "Đã lấy" hiển thị khi người dùng đã có voucher -->
                                            <button class="button-margin-top bg-gray-500 text-primary-foreground px-4 py-2 rounded-lg" disabled>Đã hết</button>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Nút "Lưu" hiển thị khi người dùng chưa có voucher -->
                                            <button class="button-margin-top bg-primary text-primary-foreground px-4 py-2 rounded-lg" onclick="handleVoucherSave('${o.voucherId}')">Lưu</button>
                                        </c:otherwise>

                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>


                    </div>
                </div>
                <a href="home" class="back-button mt-4">Trở lại trang chủ</a>

            </div>
        </div>

        <script>
            function handleVoucherSave(voucherId) {
                // Hiển thị thông báo
                alert("Lấy voucher thành công");

                // Chuyển hướng đến trang nhận voucher
                window.location.href = 'getVoucherRestaurant?voucherId=' + voucherId;
            }
        </script>

        <script>
            document.querySelectorAll('.text-center').forEach(item => {
                item.addEventListener('click', () => {
                    const href = item.getAttribute('data-href');
                    if (href) {
                        window.location.href = href;
                    }
                });
            });
        </script>
    </body>
    <script type="text/javascript">
        window.tailwind.config = {
            darkMode: ['class'],
            theme: {
                extend: {
                    colors: {
                        border: 'hsl(var(--border))',
                        input: 'hsl(var(--input))',
                        ring: 'hsl(var(--ring))',
                        background: 'hsl(var(--background))',
                        foreground: 'hsl(var(--foreground))',
                        primary: {
                            DEFAULT: 'hsl(var(--primary))',
                            foreground: 'hsl(var(--primary-foreground))'
                        },
                        secondary: {
                            DEFAULT: 'hsl(var(--secondary))',
                            foreground: 'hsl(var(--secondary-foreground))'
                        },
                        destructive: {
                            DEFAULT: 'hsl(var(--destructive))',
                            foreground: 'hsl(var(--destructive-foreground))'
                        },
                        muted: {
                            DEFAULT: 'hsl(var(--muted))',
                            foreground: 'hsl(var(--muted-foreground))'
                        },
                        accent: {
                            DEFAULT: 'hsl(var(--accent))',
                            foreground: 'hsl(var(--accent-foreground))'
                        },
                        popover: {
                            DEFAULT: 'hsl(var(--popover))',
                            foreground: 'hsl(var(--popover-foreground))'
                        },
                        card: {
                            DEFAULT: 'hsl(var(--card))',
                            foreground: 'hsl(var(--card-foreground))'
                        },
                    },
                }
            }
        }
    </script>
</html>




