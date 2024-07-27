<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>4FOODHD</title>
        <link
            rel="stylesheet"
            href="https://fonts.googleapis.com/css?family=Roboto:400,700"
            />
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <!-- https://fontawesome.com/ -->
        <link rel="stylesheet" href="jquery-ui-datepicker/jquery-ui.min.css" type="text/css" />
        <!-- http://api.jqueryui.com/datepicker/ -->
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <!-- https://getbootstrap.com/ -->
        <link rel="stylesheet" href="css/templatemo-style.css">
        <!--
            Product Admin CSS Template
            https://templatemo.com/tm-524-product-admin
        -->


    </head>

    <body style="background-color: #F6F6F6">
        <div class="container tm-mt-big tm-mb-big">
            <div class="row">
                <div class="col-xl-9 col-lg-10 col-md-12 col-sm-12 mx-auto">
                    <div class="tm-bg-primary-dark tm-block tm-block-h-auto">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="tm-block-title d-inline-block text-uppercase">Chỉnh sửa vai trò và trạng thái</h2>
                            </div>
                        </div>
                        <form action="editAccount" method="post" class="tm-edit-product-form">
                            <div class="row tm-edit-product-row">
                                <div class="col-xl-6 col-lg-6 col-md-12">
                                    <c:if test="${not empty error}">
                                        <div id="error-message" class="alert alert-danger mt-3">${error}</div>
                                    </c:if>
                                    <div>
                                        <input id="accountId" name="accountId" type="hidden" value="${detail.accountId}" class="form-control validate" />
                                        <input id="OldImage" name="OldImage" type="hidden" value="${detail.imageAvatar}" class="form-control validate" />
                                        <input id="oldRoleId" name="oldRoleId" type="hidden" value="${roleId}" class="form-control validate" />
                                        <input id="oldStatus" name="oldStatus" type="hidden" value="${status}" class="form-control validate" />
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="email">Email</label>
                                        <input id="email" name="email" type="text" required value="${detail.email}" class="form-control validate read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Tên người dùng</label>
                                        <input id="name" name="name" type="text" required value="${detail.name}" class="form-control validate read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Giới tính</label>
                                        <input id="gender" name="gender" type="text" required value="${detail.gender ? "Nam" : "Nữ"}" class="form-control validate read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Số điện thoại</label>
                                        <input id="phone" name="phone" type="text" required value="${detail.phone}" class="form-control validate read-only" readonly/>
                                    </div>
                                    <div class="form-group mb-3">
                                        <label for="name">Địa chỉ</label>
                                        <input id="address" name="address" type="text" required value="${detail.address}" class="form-control validate read-only" readonly/>
                                    </div>
                                    <div class="row">
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="createDate">Ngày tạo tài khoản</label>
                                            <input id="createDate" name="createDate" type="text" required value="${detail.createDate}" class="form-control validate read-only" readonly/>
                                        </div>
                                        <div class="form-group mb-3 col-xs-12 col-sm-6">
                                            <label for="lastDateLogin">Ngày đăng nhập cuối</label>
                                            <input id="lastDateLogin" name="lastDateLogin" type="text" required value="${detail.lastDateLogin}" class="form-control validate read-only" readonly/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:if test="${roleId == 2 && status == 1}">
                                            <div class=" form-group mb-3 col-xs-12 col-sm-6">
                                                <label for="role">Vai trò</label>
                                                <select class="custom-select tm-select-accounts select" name="role" required>
                                                    <!--<option value="${o.roleId}" ${o.roleId == roleId ? "selected" : ""}>${o.roleName}</option>-->
                                                    <option value="2" ${2 == roleId ? "selected" : ""}>Khách hàng</option>
                                                    <option value="3" ${3 == roleId ? "selected" : ""}>Shipper</option>
                                                    <option value="4" ${4 == roleId ? "selected" : ""}>Nhà hàng</option>
                                                    <option value="5" ${5 == roleId ? "selected" : ""}>Nhân viên</option>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${roleId == 2 && status == 0}">
                                            <div class=" form-group mb-3 col-xs-12 col-sm-6">
                                                <label for="role">Vai trò</label>
                                                <select class="read-only custom-select tm-select-accounts select" name="role" required>
                                                    <!--<option value="${o.roleId}" ${o.roleId == roleId ? "selected" : ""}>${o.roleName}</option>-->
                                                    <option value="2" ${2 == roleId ? "selected" : ""}>Khách hàng</option>
                                                    <option value="3" ${3 == roleId ? "selected" : ""}>Shipper</option>
                                                    <option value="4" ${4 == roleId ? "selected" : ""}>Nhà hàng</option>
                                                    <option value="5" ${5 == roleId ? "selected" : ""}>Nhân viên</option>
                                                </select>
                                            </div>
                                        </c:if>
                                        <c:if test="${roleId == 3 || roleId == 4 || roleId == 5}">
                                            <div class=" form-group mb-3 col-xs-12 col-sm-6">
                                                <label for="role">Vai trò</label>
                                                <select class="read-only custom-select tm-select-accounts select" name="role" required>
                                                    <!--<option value="${o.roleId}" ${o.roleId == roleId ? "selected" : ""}>${o.roleName}</option>-->
                                                    <option value="2" ${2 == roleId ? "selected" : ""}>Khách hàng</option>
                                                    <option value="3" ${3 == roleId ? "selected" : ""}>Shipper</option>
                                                    <option value="4" ${4 == roleId ? "selected" : ""}>Nhà hàng</option>
                                                    <option value="5" ${5 == roleId ? "selected" : ""}>Nhân viên</option>
                                                </select>
                                            </div>
                                        </c:if>

                                        <c:if test="${status == 1}">
                                            <div class="form-group mb-3 col-xs-12 col-sm-6">
                                                <label for="status">Trạng thái</label>
                                                <select class="custom-select tm-select-accounts select" name="status" required>
                                                    <option value="1" ${status == 1 ? "selected" : ""}>Hoạt động</option>
                                                    <option value="0" ${status == 0 ? "selected" : ""}>Cấm</option>
                                                </select>
                                            </div>
                                        </c:if>

                                        <c:if test="${status == 0}">
                                            <div class="form-group mb-3 col-xs-12 col-sm-6">
                                                <label for="status">Trạng thái</label>
                                                <select class="read-only custom-select tm-select-accounts select" name="status" required>
                                                    <option value="1" ${status == 1 ? "selected" : ""}>Hoạt động</option>
                                                    <option value="0" ${status == 0 ? "selected" : ""}>Cấm</option>
                                                </select>
                                            </div>
                                        </c:if>

                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-12 mx-auto mb-4">
                                    <!-- Khung chứa hình ảnh sản phẩm -->
                                    <div class="tm-product-img-edit mx-auto">
                                        <img style="width: 350px; height: 350px" id="currentImage" src="img/${detail.imageAvatar}" alt="Không thể tải ảnh" class="img-fluid d-block mx-auto" style="color: white">
                                    </div>
                                    <!-- Khung chứa nút chọn ảnh -->
                                    <div class="custom-file mt-3 mb-3">
                                        <input hidden="" id="fileInput" name="image" type="file" style="display:none;" onchange="previewImage(event);" />
                                        <input hidden type="button" class="btn btn-primary btn-block mx-auto text-uppercase" value="Chọn ảnh" onclick="document.getElementById('fileInput').click();" />
                                    </div>
                                </div>

                                <script>
                                    function previewImage(event) {
                                        var input = event.target;
                                        var reader = new FileReader();
                                        reader.onload = function () {
                                            var dataURL = reader.result;
                                            var output = document.getElementById('currentImage');
                                            output.src = dataURL;
                                            output.style.display = 'block'; // Hiển thị ảnh mới
                                        };
                                        if (input.files && input.files[0]) {
                                            reader.readAsDataURL(input.files[0]);
                                        }
                                    }
                                </script>


                                <div class="col-6">
                                    <a href="managerAccount" class="btn btn-primary btn-block text-uppercase">Huỷ bỏ</a>

                                </div>
                                <div class="col-6">
                                    <input type="submit" class="btn btn-primary btn-block text-uppercase" value="Cập nhật ngay" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>




        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- https://jquery.com/download/ -->
        <script src="jquery-ui-datepicker/jquery-ui.min.js"></script>
        <!-- https://jqueryui.com/download/ -->
        <script src="js/bootstrap.min.js"></script>
        <!-- https://getbootstrap.com/ -->
    </body>
</html>



