/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


 function addToCart(productId) {
        var xhr = new XMLHttpRequest();
        var url = "addtocart";
        
       
        xhr.open("POST", url, true);
        
      
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 10000) {
               
                alert("Đã thêm vào giỏ hàng thành công!");
            } else {
                // Nếu yêu cầu không thành công, hiển thị thông báo lỗi
                alert("Đã xảy ra lỗi khi gửi yêu cầu: " + xhr.responseText);
            }
        };
        
        
        xhr.onerror = function() {
            alert("Đã xảy ra lỗi khi gửi yêu cầu.");
        };
        
        // Gửi yêu cầu với dữ liệu sản phẩm
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.send("productId=" + productId);
    }