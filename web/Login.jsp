<%-- 
    Document   : login
    Created on : Sep 12, 2023, 1:53:33 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <style>
        body, html {
            height: 100%;
            margin: 0;
        }
        .bg-image {
            background-image: url('path_to_your_image.jpg'); /* Thay đổi đường dẫn hình nền ở đây */
            height: 100%;
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
    </head>
    <body>
        <section class="vh-100" style="background-color: #81C408;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-5">Đăng nhập</h3>
                                <c:if test="${err!=null}">
                                    <font color ="red">  ${err}</font>
                                </c:if>
                                <form action="login" method="post">
                                    <div class="form-outline mb-4">
                                        <input name="email"  type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="" value="${email}">
                                    </div>

                                    <div class="form-outline mb-4">
                                        <input name="password"  type="password" id="inputPassword" class="form-control" placeholder="Mật khẩu" required="">
                                    </div>
                                    <!-- Forgot password -->
                                    <p class="small mb-5 pb-lg-2"><a class="text-muted" href="ForgetPassword.jsp">Quên mật khẩu?</a></p>
                                    <hr class="my-4">

                                    <button class="btn btn-primary btn-lg btn-block" type="submit" style="background-color:#81C408 ">Đăng nhập</button>

                                    <hr class="my-4">
                                </form>
                                
<a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Order_Food/logingoogle&response_type=code&client_id=1020017167169-f4va8u548gpbnn9ntap6cggkc4mrmnv4.apps.googleusercontent.com&approval_prompt=force">
                                    <button class="btn btn-lg btn-block btn-primary" style="background-color: #dd4b39;"                                                                                                         >
                                        Đăng nhập bằng Google</button></a>

                                <hr class="my-4">

                                <p>Không có tài khoản? <a href="Register.jsp" class="link-info">Đăng ký</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
