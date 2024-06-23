<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
            body, html {
                height: 100%;
                margin: 0;
                font-family: Arial, sans-serif;
                background: #81C408;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .card {
                border-radius: 1rem;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                max-width: 600px; /* Increased width */
                width: 100%;
            }
            .form-outline {
                margin-bottom: 1.5rem;
            }
            .btn-block {
                font-size: 1.2rem;
                padding: 0.8rem;
            }
            .card-body {
                padding: 2rem;
            }
            .g-recaptcha {
                margin-bottom: 1rem;
            }
            .small {
                font-size: 0.9rem;
            }
            .text-muted {
                color: #6c757d !important;
            }
            .link-info {
                color: #007bff;
            }
            .card-title {
                font-size: 2rem;
                font-weight: bold;
                margin-bottom: 2rem;
            }
            .form-control {
                border-radius: 0.5rem;
            }
            .btn-primary {
                background-color: #81C408;
                border-color: #81C408;
            }
            .btn-primary:hover {
                background-color: #6ba306;
                border-color: #6ba306;
            }
            .btn-google {
                background-color: #dd4b39;
                border-color: #dd4b39;
            }
            .btn-google:hover {
                background-color: #c23321;
                border-color: #c23321;
            }
        </style>
    </head>
    <body>
        <section class="vh-100 d-flex align-items-center justify-content-center">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="card shadow-2-strong">
                            <div class="card-body text-center">
                                <h3 class="card-title">Đăng nhập</h3>
                                <c:if test="${err!=null}">
                                    <div class="alert alert-danger" role="alert">
                                        ${err}
                                    </div>
                                </c:if>
                                <form id="login_form" action="login" method="post">
                                    <div class="form-outline mb-4">
                                        <input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email" required autofocus value="${email}">
                                    </div>
                                    <div class="form-outline mb-4">
                                        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Mật khẩu" required>
                                    </div>
                                    <p class="small mb-4"><a class="text-muted" href="ForgetPassword.jsp">Quên mật khẩu?</a></p>
                                    <div class="g-recaptcha" data-sitekey="6LdV8_wpAAAAADnLoLkLtmiDn9vmPCVFISEsxn9a"></div>
                                    <div style="color:red" id="error"></div>
                                    <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="checkCaptcha(event)">Đăng nhập</button>
                                    <hr class="my-4">
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Order_Food/logingoogle&response_type=code&client_id=1020017167169-f4va8u548gpbnn9ntap6cggkc4mrmnv4.apps.googleusercontent.com&approval_prompt=force">
                                        <button type="button" class="btn btn-google btn-lg btn-block">Đăng nhập bằng Google</button>
                                    </a>
                                    <hr class="my-4">
                                    <p>Không có tài khoản? <a href="Register.jsp" class="link-info">Đăng ký</a></p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                var registerSuccess = "<c:out value='${registerSuccess}' />";
                if (registerSuccess === "true") {
                    alert("Đăng ký thành công! Vui lòng đăng nhập.");
                    <% session.removeAttribute("registerSuccess"); %>
                }

                var passwordChangeSuccess = "<c:out value='${passwordChangeSuccess}' />";
                if (passwordChangeSuccess === "true") {
                    alert("Mật khẩu của bạn đã được đổi thành công! Vui lòng đăng nhập.");
                    <% session.removeAttribute("passwordChangeSuccess"); %>
                }
            }

            function checkCaptcha(event) {
                event.preventDefault();
                var response = grecaptcha.getResponse();
                var error = document.getElementById("error");
                if (response.length === 0) {
                    error.textContent = "Vui lòng xác nhận bạn không phải là người máy";
                } else {
                    document.getElementById("login_form").submit();
                }
            }
        </script>
    </body>
</html>
