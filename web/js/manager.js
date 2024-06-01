function addToCart(productId) {
    var num = 1; // Số lượng sản phẩm cố định là 1
    var url = "buy?num=" + num + "&id=" + productId; // URL của servlet

    // Tạo một đối tượng XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Thiết lập phương thức và URL của yêu cầu
    xhr.open("POST", url, true);

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 10000) {
            // Nếu yêu cầu thành công, hiển thị thông báo thành công
            alert("Yêu cầu đã được gửi thành công!");
        } else {
            // Nếu yêu cầu không thành công, hiển thị thông báo lỗi
            alert("Đã xảy ra lỗi khi gửi yêu cầu: " + xhr.responseText);
        }
    };

    // Xử lý lỗi khi gửi yêu cầu
    xhr.onerror = function() {
        alert("Đã xảy ra lỗi khi gửi yêu cầu.");
    };

    // Gửi yêu cầu
    xhr.send();
}
