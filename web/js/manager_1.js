$(document).ready(function () {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    // Select/Deselect checkboxes
    var checkbox = $('table tbody input[type="checkbox"]');
    $("#selectAll").click(function () {
        if (this.checked) {
            checkbox.each(function () {
                this.checked = true;
            });
        } else {
            checkbox.each(function () {
                this.checked = false;
            });
        }
    });
    checkbox.click(function () {
        if (!this.checked) {
            $("#selectAll").prop("checked", false);
        }
    });
});


function goToCategory(event) {
    if (event.target.tagName === 'A') {
        return; // Do nothing if clicked element is an anchor tag
    }

    // Find the closest anchor tag within the clicked list item
    const anchor = event.target.closest('li').querySelector('a');

    if (anchor) {
        const categoryUrl = anchor.getAttribute('href');
        window.location.href = categoryUrl; // Redirect to the category URL
    }
}

function addToCart(productId) {
    var num = 1; // Số lượng sản phẩm
    var url = "buy?num=" + num + "&id=" + productId; // URL của servlet

    // Tạo một đối tượng XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Thiết lập phương thức và URL của yêu cầu
    xhr.open("Post", url, true);

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // Nếu yêu cầu thành công, hiển thị thông báo thành công
            alert("The request has been sent successfully!");
        } else {
            // Nếu yêu cầu không thành công, hiển thị thông báo lỗi
            alert("An error occurred while sending the request!");
        }
    };

    // Xử lý lỗi khi gửi yêu cầu
    xhr.onerror = function () {
        alert("An error occurred while sending the request!");
    };
    // Gửi yêu cầu
    xhr.send();
}

function buyToCart(productId) {
    var num = 1; // Số lượng sản phẩm
    var url = "buy?num=" + num + "&id=" + productId; // URL của servlet

    // Tạo một đối tượng XMLHttpRequest
    var xhr = new XMLHttpRequest();

    // Thiết lập phương thức và URL của yêu cầu
    xhr.open("POST", url, true);

    // Xử lý sự kiện khi yêu cầu hoàn thành
    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            // Nếu yêu cầu thành công, chuyển hướng tới trang cart.jsp
            window.location.href = "Cart.jsp";
        } else {
            // Nếu yêu cầu không thành công, hiển thị thông báo lỗi
            alert("An error occurred while sending the request!");
        }
    };

    // Xử lý lỗi khi gửi yêu cầu
    xhr.onerror = function () {
        alert("An error occurred while sending the request!");
    };

    // Gửi yêu cầu
    xhr.send();
}
