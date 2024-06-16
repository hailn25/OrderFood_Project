/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    // Lắng nghe sự kiện click trên nút "Chỉnh sửa ảnh"
    document.getElementById('edit-avatar').addEventListener('click', function () {
        document.getElementById('avatar-input').click(); // Mở hộp thoại chọn ảnh khi nhấp vào "Chỉnh sửa ảnh"
    });

    // Ẩn tất cả các phần trừ phần thông tin cá nhân khi trang được tải
    document.querySelectorAll('.content-section').forEach(function (el) {
        el.style.display = 'none';
    });

    // Hiển thị phần thông tin cá nhân khi trang được tải
    document.getElementById('personalInfo').style.display = 'block';

    document.getElementById('screen-profile').addEventListener('click', function () {
        showSection('personalInfo');
    });

    // Thêm sự kiện cho các nút bên trái
    document.getElementById('edit-profile').addEventListener('click', function () {
        showSection('editProfile');
    });

    document.getElementById('edit-password').addEventListener('click', function () {
        showSection('changePassword');
    });

    document.getElementById('voucher').addEventListener('click', function () {
        showSection('vouchers');
    });

    document.getElementById('order-history').addEventListener('click', function () {
        showSection('purchaseHistory');
    });

    // Thêm sự kiện click cho nút "Thông tin đơn hàng"
    document.getElementById('order-details').addEventListener('click', function () {
        showSection('orderDetails');
    });

    // Hàm hiển thị phần tương ứng khi được nhấp
    function showSection(sectionId) {
        // Ẩn tất cả các phần
        document.querySelectorAll('.content-section').forEach(function (el) {
            el.style.display = 'none';
        });
        // Hiển thị phần được chọn
        document.getElementById(sectionId).style.display = 'block';
    }

    // Xác nhận khi thay đổi mật khẩu
    document.getElementById('change-password-btn').addEventListener('click', function () {
        var oldPassword = document.getElementById('old-password').value;
        var newPassword = document.getElementById('new-password').value;
        var confirmPassword = document.getElementById('confirm-password').value;

        // Kiểm tra mật khẩu cũ và mới
        if (oldPassword === "" || newPassword === "" || confirmPassword === "") {
            alert("Vui lòng nhập đầy đủ thông tin mật khẩu.");
            return;
        }

        if (newPassword !== confirmPassword) {
            alert("Mật khẩu mới và mật khẩu xác nhận không khớp.");
            return;
        }

        // Gửi yêu cầu thay đổi mật khẩu
        // (Tại đây bạn có thể thực hiện gửi dữ liệu thông qua Ajax hoặc các phương thức khác)

        // Hiển thị thông báo thành công
        alert("Thay đổi mật khẩu thành công.");
    });
});
