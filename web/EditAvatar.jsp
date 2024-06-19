<%-- 
    Document   : EditAvatar
    Created on : Jun 19, 2024, 11:02:24 PM
    Author     : ADMIN
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Avatar</title>
        <script>
            function previewAvatar(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        document.getElementById('currentAvatar').src = e.target.result;
                    }
                    reader.readAsDataURL(input.files[0]);
                }
            }
        </script>
    </head>
    <body>
        <h2>Change Avatar</h2>

        <%-- Form to upload new avatar --%>
        <form action="editAvatar" method="post" enctype="multipart/form-data">
            Current Avatar: <br>
            <img id="currentAvatar" src="<%= request.getContextPath() %>/img/${account.imageAvatar}" width="100" height="100" alt="Current Avatar"><br><br>

            <!-- Thêm ô nhập accountId -->
            Account ID: <br>
            <input type="text" name="accountId" id="accountId" value="${account.accountId}" required><br><br>

            Select new avatar: <br>
            <input type="file" name="newAvatar" accept="image/*" onchange="previewAvatar(this);"><br><br>

            <input type="submit" value="Upload">
        </form>
    </body>
</html>

