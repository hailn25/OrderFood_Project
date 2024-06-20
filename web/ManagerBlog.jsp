<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!--cai nay cua ProGear-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!--day la icon edit, delete-->
        <link rel="stylesheet" href="https://cdn.datatables.net/2.0.1/css/dataTables.dataTables.css"> <!--day la table cua bang manager-->
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <title>4FOODHD</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />


        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/bootstrap.min_1.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">

        <style>
            img{
                width: 150px;
                height: 150px;
            }
        </style>
    </head>

    <body id="reportsPage" style="background-color: #F6F6F6">

        <nav class="navbar navbar-expand-xl">
            <div class="container h-100">
                <a class="navbar-brand" href="ManagerDashboard.jsp">
                    <c:if test="${not empty sessionScope.account.name}">
                        <h1 class="tm-site-title mb-0">Staff: <br><b>${sessionScope.account.name}</b></h1>
                        </c:if>


                </a>
                <button
                    class="navbar-toggler ml-auto mr-0"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
                    >
                    <i class="fas fa-bars tm-nav-icon"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mx-auto h-100">

                        <c:if test="${sessionScope.account.roleId == 5}">
                            <li class="nav-item">
                                <a class="nav-link" href="ManagerStaff.jsp">
                                    <i class="fas fa-home"></i> Trang chủ
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerBlog">
                                    <i class="far fa-file-alt"></i> Quản lý blog
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.account.roleId == 5}">                          
                            <li class="nav-item">
                                <a class="nav-link" href="managerService">
                                    <i class="fas fa-sliders-h"></i> Dịch vụ 
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <c:if test = "${sessionScope.account == null}"> 
                                <a class="nav-link d-block" href="Login.jsp">
                                    <b>Đăng nhập</b>
                                </a>

                            </c:if> 
                            <c:if test = "${sessionScope.account != null}"> 
                                <a class="nav-link d-block" href="logout">
                                    <b>Đăng xuất</b>
                                </a>
                            </c:if> 
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>Blog</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="managerAddBlog"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Thêm bài viết mới</span></a>
                        </div>
                    </div>
                </div>
                <table id="example" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tiêu đề</th>
                            <th>Ảnh</th>
                            <th>Trạng thái</th>
                            <th>Ngày đăng</th>
                            <th>Tác vụ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listBlog}" var="b">
                            <tr>
                                <td>${b.blogId}</td>
                                <td class="title" data-title="${b.title}"></td>
                                <td>
                                    <img src="img/${b.imageURL}" alt="Không thể tải ảnh">
                                </td>
                                <td style="color: ${b.status ? 'green' : 'red'};">
                                    ${b.status ? "Đang hiển thị" : "Đang ẩn"}
                                </td>
                                <td>${b.createDate}</td>
                                <td>
                                    <a href="editBlog?bid=${b.blogId}"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Chỉnh sửa">&#xE254;</i></a>
                                    <c:if test="${b.status == true}">
                                        <a href="changeStatus?bid=${b.blogId}&statusBlog=${b.status}" class="change" data-toggle="modal">
                                            <i class="material-icons fas fa-eye-slash" data-toggle="tooltip" title="Ẩn"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${b.status == false}">
                                        <a href="changeStatus?bid=${b.blogId}&statusBlog=${b.status}" class="change" data-toggle="modal">
                                            <i class="material-icons fas fa-eye" data-toggle="tooltip" title="Hiển thị"></i>
                                        </a>
                                    </c:if>
                                    <a href="deleteBlog?bid=${b.blogId}" class="delete" data-toggle="modal" onclick="confirmDelete(event)"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>



        <!--cai nay cua ProGear-->
        <script src="js/manager_1.js" type="text/javascript"></script>

        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
        <script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
        <script>
                                        new DataTable('#example');

                                        document.addEventListener("DOMContentLoaded", function () {
                                            const summaries = document.querySelectorAll('.summary');
                                            summaries.forEach(function (summary) {
                                                const fullText = summary.getAttribute('data-summary');
                                                if (fullText.length > 100) {
                                                    summary.textContent = fullText.substring(0, 100) + '...';
                                                } else {
                                                    summary.textContent = fullText;
                                                }
                                            });
                                        });

                                        const titles = document.querySelectorAll('.title');
                                        titles.forEach(function (title) {
                                            const fullText = title.getAttribute('data-title');
                                            const words = fullText.split(' ');
                                            if (words.length > 30) {
                                                title.textContent = words.slice(0, 30).join(' ') + '...';
                                            } else {
                                                title.textContent = fullText;
                                            }
                                        });

                                        function confirmDelete(event) {
                                            event.preventDefault();
                                            var confirmAction = confirm("Bạn có chắc chắn muốn xóa bài viết này không?");
                                            if (confirmAction) {
                                                window.location.href = event.target.closest('a').href;
                                            }
                                        }
        </script>
    </body>
</html>