<%-- 
    Document   : register
    Created on : Sep 12, 2023, 2:27:44 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <title>4FOODHD</title>
        <style>

            body, html {
                height: 100%;
                margin-top: 50px;
                background-color: #81C408;
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
            }

        </style>
    </head>
    <body>
        <section class="vh-100 bg-image" style="background-color: #81C408;">
            <div class="mask d-flex align-items-center h-100 gradient-custom-3">
                <div class="container h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-5">Đăng ký tài khoản</h2>
                                    <form action="register" method="post" onsubmit="return validateForm()">
                                        <div id="errorMessage" style="color: red;"></div>
                                        <div class="form-outline mb-4">
                                            <small class="form-text" style="color: red;">* Bắt buộc</small>
                                            <input name="email"  type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
                                        </div>

                                        <div class="form-outline mb-4">
                                            <small class="form-text" style="color: red;">* Bắt buộc</small>
                                            <input name="password"  type="password" id="inputPassword" class="form-control" placeholder="Mật khẩu" required="">
                                        </div>

                                        <div class="form-outline mb-4">
                                            <small class="form-text" style="color: red;">* Bắt buộc</small>
                                            <input name="repassword"  type="password" id="inputPassword" class="form-control" placeholder="Nhập lại mật khẩu" required="">
                                        </div>

                                        <div class="form-outline mb-4">
                                            <small class="form-text" style="color: red;">* Bắt buộc</small>
                                            <input name="fullname" type="text" id="inputFullname" class="form-control" placeholder="Họ và tên" required="">
                                        </div>

                                        <div class="form-outline mb-4">
                                            Giới tính  <input type="radio" name="gender" value="Male" checked /> Nam
                                            <input type="radio" name="gender" value="Female" /> Nữ
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input name="phonenumber"  type="text" id="inputPassword" class="form-control" placeholder="Số điện thoại" >
                                        </div>

                                        <div class="form-outline mb-4">
                                            <input name="address"  type="text" id="inputaddress" class="form-control" placeholder="Địa chỉ" >
                                        </div>


                                        <div class="d-flex justify-content-center">
                                            <button type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Đăng ký</button>
                                        </div>

                                        <p class="text-center text-muted mt-5 mb-0">Đã có tài khoản? <a href="Login.jsp"
                                                                                                        ><u>Đăng nhập</u></a></p>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            window.onload = function () {
                var msg = "${msg}";
                if (msg) {
                    document.getElementById("errorMessage").innerHTML = msg;
                }
            }
            function validateForm() {
                var email = document.getElementsByName("email")[0];
                var password = document.getElementsByName("password")[0];
                var repassword = document.getElementsByName("repassword")[0];
                var fullname = document.getElementById("inputFullname");
                var phonenumber = document.getElementsByName("phonenumber")[0];
                var address = document.getElementsByName("address")[0];

                var emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
                if (!email.value.match(emailPattern)) {
                    document.getElementById("errorMessage").innerHTML = "Vui lòng nhập đúng định dạng email";
                    return false;
                }

                if (password.value == "" || password.value.trim().length == 0) {
                    document.getElementById("errorMessage").innerHTML = "Mật khẩu không được để trống";
                    password.value = "";
                    repassword.value = "";
                    return false;
                }

                if (password.value.length < 6) {
                    document.getElementById("errorMessage").innerHTML = "Mật khẩu phải có ít nhất 6 ký tự";
                    return false;
                }

                if (password.value != repassword.value) {
                    document.getElementById("errorMessage").innerHTML = "Mật khẩu và mật khẩu lặp lại phải giống nhau";
                    return false;
                }
                if (fullname.value.trim() == "") {
                    document.getElementById("errorMessage").innerHTML = "Họ và tên không được để trống";
                    return false;
                }

                var namePattern = /^[a-zA-ZÀ-Ỹà-ỹ\s]+$/u;

                // Kiểm tra ký tự đặc biệt hoặc số trong họ và tên
                if (!namePattern.test(fullname.value)) {
                    document.getElementById("errorMessage").innerHTML = "Họ và tên không được chứa ký tự đặc biệt hoặc số";
                    return false;
                }

                var phonePattern = /^\d{10}$/;
                if (!phonePattern.test(phonenumber.value)) {
                    document.getElementById("errorMessage").innerHTML = "Số điện thoại phải có 10 chữ số và không chứa ký tự khác hoặc khoảng trắng";
                    return false;
                }
                if ((address.value == "" && address.value.length != 0) || (address.value.trim().length == 0 && address.value.length != 0)) {
                    document.getElementById("errorMessage").innerHTML = "Vui lòng điền đúng địa chỉ";
                    return false;
                }
            }
        </script>
    </body>
</html>