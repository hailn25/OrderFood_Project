<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Setting Banner</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .image-preview {
                max-width: 200px;
                max-height: 200px;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header bg-primary text-white">Setting Banner</div>
                        <div class="card-body">
                            <%-- Check for success message --%>
                            <% if (request.getAttribute("message") != null) { %>
                                <div class="alert alert-success" role="alert">
                                    <%= request.getAttribute("message") %>
                                </div>
                            <% } %>

                            <%-- Check for error message --%>
                            <% if (request.getAttribute("error") != null) { %>
                                <div class="alert alert-danger" role="alert">
                                    <%= request.getAttribute("error") %>
                                </div>
                            <% } %>

                            <form action="settingBanner" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                                <div class="form-group">
                                    <label for="sliderTitle">Slider Title</label>
                                    <input type="text" class="form-control" id="sliderTitle" name="sliderTitle" required>
                                    <div class="text-danger" id="sliderTitleError"></div>
                                </div>

                                <div class="form-group">
                                    <label for="backLink">Backlink</label>
                                    <input type="text" class="form-control" id="backLink" name="backLink" required>
                                    <div class="text-danger" id="backLinkError"></div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="createDate">Create Date</label>
                                    <input type="text" class="form-control" id="displayDate" readonly>
                                    <input type="hidden" id="createDate" name="createDate">
                                    <div class="text-danger" id="createDateError"></div>
                                </div>

                                <div class="form-group">
                                    <label for="updateDate">Update Date</label>
                                    <input type="date" class="form-control" id="updateDate" name="updateDate" required>
                                    <div class="text-danger" id="updateDateError"></div>
                                </div>

                                <div class="form-group">
                                    <label for="imageAvatar">Image</label>
                                    <input type="file" class="form-control" id="imageAvatar" name="imageAvatar" accept="image/*" onchange="previewImage(event)" required>
                                    <div class="text-danger" id="imageAvatarError"></div>
                                </div>

                                <div class="form-group text-center">
                                    <img id="imagePreview" class="image-preview" style="display: none;">
                                </div>

                                <div class="form-group text-center">
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            window.onload = function() {
                var createDateInput = document.getElementById('createDate');
                var displayDateInput = document.getElementById('displayDate');
                var currentDate = new Date().toISOString().split('T')[0];
                createDateInput.value = currentDate;
                displayDateInput.value = currentDate;
            };

            function previewImage(event) {
                var input = event.target;
                var reader = new FileReader();
                reader.onload = function() {
                    var dataURL = reader.result;
                    var output = document.getElementById('imagePreview');
                    output.src = dataURL;
                    output.style.display = 'block'; // Display the image preview
                };
                if (input.files && input.files[0]) {
                    reader.readAsDataURL(input.files[0]);
                }
            }

            function validateForm() {
                var isValid = true;

                // Reset errors
                document.getElementById("sliderTitleError").innerHTML = "";
                document.getElementById("backLinkError").innerHTML = "";
                document.getElementById("createDateError").innerHTML = "";
                document.getElementById("updateDateError").innerHTML = "";
                document.getElementById("imageAvatarError").innerHTML = "";

                // Validate sliderTitle
                var sliderTitle = document.getElementById("sliderTitle").value.trim();
                if (sliderTitle === "") {
                    document.getElementById("sliderTitleError").innerHTML = "Slider Title is required.";
                    isValid = false;
                }

                // Validate backLink
                var backLink = document.getElementById("backLink").value.trim();
                if (backLink === "") {
                    document.getElementById("backLinkError").innerHTML = "Backlink is required.";
                    isValid = false;
                }

                // Validate createDate
                var createDate = document.getElementById("createDate").value;
                if (createDate === "") {
                    document.getElementById("createDateError").innerHTML = "Create Date is required.";
                    isValid = false;
                }

                // Validate updateDate
                var updateDate = document.getElementById("updateDate").value;
                if (updateDate === "") {
                    document.getElementById("updateDateError").innerHTML = "Update Date is required.";
                    isValid = false;
                }

                // Validate imageAvatar
                var imageAvatar = document.getElementById("imageAvatar").value.trim();
                if (imageAvatar === "") {
                    document.getElementById("imageAvatarError").innerHTML = "Image URL is required.";
                    isValid = false;
                }

                return isValid;
            }
        </script>
    </body>
</html>
