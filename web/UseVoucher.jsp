<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>4FOODHD</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography"></script>
        <style>
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
        </style>
    </head>
    <body class="bg-light">
        <div class="d-flex align-items-center justify-content-center vh-100 bg-dark bg-opacity-50">
            <div class="card shadow-lg w-75">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h2 class="h5 mb-0">Chọn Voucher</h2>
                </div>
                <div class="card-body">
                    <div class="mb-4">
                        <div class="input-group">
                            <input id="voucher-code" type="text" class="form-control" placeholder="Mã Voucher">
                            <button class="btn btn-outline-secondary">ÁP DỤNG</button>
                        </div>
                    </div>
                    <form action="useVoucher" method="post">
                        <div class="mb-4">
                            <h3 class="h6">Mã Miễn Phí Vận Chuyển</h3>
                            <p class="text-muted">Có thể chọn 1 Voucher</p>
                            <div class="list-group overflow-auto max-h-60">
                                <c:forEach var="l" items="${listF}">
                                    <label class="list-group-item d-flex align-items-center">
                                        <div class="flex-grow-1">
                                            <h4 class="h6 mb-1">${l.voucherName}</h4>
                                            <p class="small text-muted mb-0">${l.description}</p>
                                            <p class="small text-muted">HSD: ${l.finishDate}</p>
                                        </div>
                                        <span class="badge bg-danger ms-3">x 1</span>
                                        <input type="radio" name="voucherFree" value="${l.voucherId}" class="form-check-input ms-3">
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="mb-4">
                            <h3 class="h6">Mã giảm giá đơn hàng</h3>
                            <p class="text-muted">Có thể chọn 1 Voucher</p>
                            <div class="list-group overflow-auto max-h-60">
                                <c:forEach var="lV" items="${listR}">
                                    <label class="list-group-item d-flex align-items-center">
                                        <div class="flex-grow-1">
                                            <h4 class="h6 mb-1">${lV.voucherName}</h4>
                                            <p class="small text-muted mb-0">${lV.description}</p>
                                            <p class="small text-muted">HSD: ${lV.finishDate}</p>
                                        </div>
                                        <span class="badge bg-danger ms-3">x 1</span>
                                        <input type="radio" name="voucherR" value="${lV.voucherId}" class="form-check-input ms-3">
                                    </label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="d-flex justify-content-end">
                            <button class="btn btn-outline-secondary me-2" type="button" onclick="goBack()">Quay lại</button>

                            <button class="btn btn-primary" type="submit">Xác nhận</button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            function goBack() {
                window.history.back();
            }
            function toggleDarkMode() {
                document.body.classList.toggle('dark');
            }
        </script>
    </body>
</html>