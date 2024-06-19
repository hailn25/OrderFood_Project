<%-- 
    Document   : Feedback
    Created on : Jun 19, 2024, 5:08:55 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form Feedback</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header bg-primary text-white">Phản hồi</div>
                    <div class="card-body">
                        <form id="feedbackForm" action="process_feedback.php" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="accountName">Account Name</label>
                                <input type="text" class="form-control" id="accountName" name="accountName" required>
                            </div>

                            <div class="form-group">
                                <label for="productId">Product ID</label>
                                <input type="text" class="form-control" id="productId" name="productId" required>
                            </div>

                            <div class="form-group">
                                <label for="feedback">Phản hồi</label>
                                <textarea class="form-control" id="feedback" name="feedback" rows="5" required></textarea>
                            </div>

                            <div class="form-group">
                                <label for="rateStar">Đánh giá sao</label>
                                <input type="number" class="form-control" id="rateStar" name="rateStar" min="1" max="5" required>
                            </div>

                            <div class="form-group">
                                <label for="date">Ngày</label>
                                <input type="date" class="form-control" id="date" name="date" required>
                            </div>

                            <div class="form-group">
                                <label for="imageFile">Chọn ảnh (tối đa 2MB)</label>
                                <input type="file" class="form-control-file" id="imageFile" name="imageFile" accept="image/*" onchange="previewImage(event)">
                                <small id="imageHelpBlock" class="form-text text-muted">
                                    File ảnh của bạn không được vượt quá 2MB.
                                </small>
                                <div id="imagePreview" class="mt-2"></div>
                            </div>

                            <div class="form-group text-center">
                                <button type="submit" class="btn btn-primary">Gửi phản hồi</button>
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
                                    function previewImage(event) {
                                        var reader = new FileReader();
                                        reader.onload = function () {
                                            var output = document.getElementById('imagePreview');
                                            output.innerHTML = '<img src="' + reader.result + '" class="img-fluid img-thumbnail" style="max-width:200px; max-height:200px;" />';
                                        }
                                        reader.readAsDataURL(event.target.files[0]);
                                    }
    </script>
</body>
</html>
