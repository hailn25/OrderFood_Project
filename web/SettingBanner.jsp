<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Setting Banner</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Optional: Custom CSS styles */
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
                        <form action="process_banner.jsp" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="sliderTitle">Slider Title</label>
                                <input type="text" class="form-control" id="sliderTitle" name="sliderTitle" required>
                            </div>

                            <div class="form-group">
                                <label for="arrange">Arrange</label>
                                <input type="number" class="form-control" id="arrange" name="arrange" required>
                            </div>

                            <div class="form-group">
                                <label for="status">Status</label>
                                <select class="form-control" id="status" name="status" required>
                                    <option value="active">Active</option>
                                    <option value="inactive">Inactive</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="backlink">Backlink</label>
                                <input type="text" class="form-control" id="backlink" name="backlink">
                            </div>
                            
                            <div class="form-group">
                                <label for="imageFile">Image Upload (max 2MB)</label>
                                <input type="file" class="form-control-file" id="imageFile" name="imageFile" accept="image/*" required onchange="previewImage(event)">
                                <small id="imageHelpBlock" class="form-text text-muted">
                                    Please upload an image file (max 2MB).
                                </small>
                                <div id="imagePreview" class="mt-2"></div>
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
        function previewImage(event) {
            var reader = new FileReader();
            reader.onload = function(){
                var output = document.getElementById('imagePreview');
                output.innerHTML = '<img src="' + reader.result + '" class="img-fluid img-thumbnail image-preview" />';
            }
            reader.readAsDataURL(event.target.files[0]);
        }
    </script>
</body>
</html>