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
                            <form action="settingBanner" method="post">
                                <div class="form-group">
                                    <label for="sliderTitle">Slider Title</label>
                                    <input type="text" class="form-control" id="sliderTitle" name="sliderTitle" required>
                                </div>

                                <div class="form-group">
                                    <label for="backlink">Backlink</label>
                                    <input type="text" class="form-control" id="backLink" name="backLink" required>
                                </div>

                                <div class="form-group">
                                    <label for="createDate">Create Date</label>
                                    <input type="date" class="form-control" id="createDate" name="createDate" required>
                                </div>

                                <div class="form-group">
                                    <label for="updateDate">Update Date</label>
                                    <input type="date" class="form-control" id="updateDate" name="updateDate" required>
                                </div>

                                <div class="form-group">
                                    <label for="updateDate">ImageURL</label>
                                    <input type="text" class="form-control" id="imageAvatar" name="imageAvatar" required>
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
    </body>
</html>