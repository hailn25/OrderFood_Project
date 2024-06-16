<%-- 
    Document   : Profile
    Created on : May 25, 2024, 10:50:16 PM
    Author     : hailt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Settings - Bootdey.com</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/profile.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item active">
                            <a class="nav-link" href="home" style="background-color: #34ce57">Home <span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>
        <div class="container">
            <div class="row gutters">
                <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                    <div class="card h-100">
                        <div class="card-body" style="background-color: #c4c8c8">
                            <div class="account-settings">
                                <div class="user-profile">
                                    <div class="user-avatar">
                                        <img id="avatar-img" src="img/${account.imageAvatar}" alt="Avatar">
                                        <button class="btn btn-warning" id="edit-avatar" style="margin-top: 10px;">Chỉnh sửa ảnh</button>
                                        <input type="file" id="avatar-input" accept="image/*" style="display:none;">
                                    </div>
                                    <h5 class="user-name">${account.name}</h5>
                                    <div class="user-actions flex-container">
                                        <a href="#" class="btn btn-success" id="screen-profile">Profile</a>
                                        <a href="#" class="btn btn-success" id="edit-profile">Sửa Profile</a>
                                        <a href="#" class="btn btn-success" id="edit-password">Sửa Password</a>
                                        <a href="#" class="btn btn-success" id="voucher">Voucher</a>
                                        <a href="#" class="btn btn-success" id="order-history">Lịch sử đơn hàng</a>
                                        <a href="#" class="btn btn-success" id="order-details">Thông tin đơn hàng</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                    <div class="card h-100" style="background-color: gray;">
                        <div class="card-body">
                            <!-- Hiển Thị Thông Tin Cá Nhân -->
                            <div id="personalInfo" class="content-section">
                                <div class="card">
                                    <div class="card-body">
                                        <h6 class="mb-3 text-primary">Thông Tin Cá Nhân</h6>
                                        <div class="row gutters">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="fullName">Họ và Tên</label>
                                                    <div class="form-control">${account.name}</div>
                                                </div>
                                            </div>  
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="eMail">Email</label>
                                                    <div class="form-control">${account.email}</div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="phone">Số Điện Thoại</label>
                                                    <div class="form-control">${account.phone}</div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="eMail">Gender</label>
                                                    <div class="form-control">${account.gender}</div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="address">Địa Chỉ</label>
                                                    <div class="form-control">${account.address}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div id="editProfile" class="content-section">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Edit Profile</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="fullName">Full Name</label>
                                                    <input type="text" class="form-control" id="fullName" placeholder="Enter full name">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="email">Email Address</label>
                                                    <input type="email" class="form-control" id="email" placeholder="Enter email">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="phone">Phone Number</label>
                                                    <input type="tel" class="form-control" id="phone" placeholder="Enter phone number">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="address">Address</label>
                                                    <input type="text" class="form-control" id="address" placeholder="Enter address">
                                                </div>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <button type="button" class="btn btn-primary">Save Changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Sửa Password -->
                            <div id="changePassword" class="content-section">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Change Password</h6>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="currentPassword">Current Password</label>
                                                    <input type="password" class="form-control" id="currentPassword" placeholder="Enter current password">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="newPassword">New Password</label>
                                                    <input type="password" class="form-control" id="newPassword" placeholder="Enter new password">
                                                </div>
                                            </div>
                                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                                <div class="form-group">
                                                    <label for="confirmPassword">Confirm Password</label>
                                                    <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm new password">
                                                </div>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <button type="button" class="btn btn-primary">Confirm</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Hiển Thị Voucher -->
                            <div id="vouchers" class="content-section">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Vouchers</h6>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <ul id="vouchersList" class="list-group">
                                                    <li class="list-group-item">Voucher #A123 - Discount: 10% - Expiry: 2023-12-31</li>
                                                    <li class="list-group-item">Voucher #B456 - Discount: $20 - Expiry: 2024-01-15</li>
                                                    <!-- Add more voucher items here if needed -->
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Hiển Thị Lịch Sử Mua Hàng -->
                            <div id="purchaseHistory" class="content-section">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Purchase History</h6>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <ul id="purchaseHistoryList" class="list-group">
                                                    <li class="list-group-item">
                                                        Order #1234 - Date: 2023-05-01 - Total: $150.00
                                                        <button class="btn btn-secondary btn-sm float-right review-btn" data-order-id="1234">Review Product</button>
                                                    </li>
                                                    <li class="list-group-item">
                                                        Order #1235 - Date: 2023-06-15 - Total: $85.50
                                                        <button class="btn btn-secondary btn-sm float-right review-btn" data-order-id="1235">Review Product</button>
                                                    </li>
                                                    <!-- Add more purchase history items here if needed -->
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Hidden Review Form -->
                            <div id="reviewModal" class="modal" tabindex="-1" role="dialog">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Submit a Review</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <form id="reviewForm" enctype="multipart/form-data">
                                                <input type="hidden" id="orderId">
                                                <div class="form-group">
                                                    <label for="reviewName">Name</label>
                                                    <input type="text" class="form-control" id="reviewName" placeholder="Enter your name" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="reviewText">Review</label>
                                                    <textarea class="form-control" id="reviewText" rows="3" placeholder="Enter your review" required></textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label for="reviewRating">Rating</label>
                                                    <select class="form-control" id="reviewRating" required>
                                                        <option value="" disabled selected>Select rating</option>
                                                        <option value="1">1 Star</option>
                                                        <option value="2">2 Stars</option>
                                                        <option value="3">3 Stars</option>
                                                        <option value="4">4 Stars</option>
                                                        <option value="5">5 Stars</option>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label for="reviewImages">Upload Images</label>
                                                    <input type="file" class="form-control-file" id="reviewImages" accept="image/*" multiple>
                                                </div>
                                                <div id="imagePreview" class="mt-2"></div>
                                                <button type="submit" class="btn btn-primary">Submit Review</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Hiển thị thông tin đơn hàng -->
                            <div id="orderDetails" class="content-section">
                                <div class="card h-100">
                                    <div class="card-body">
                                        <div class="row gutters">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-primary">Order Details</h6>
                                            </div>
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <ul id="orderDetailsList" class="list-group">
                                                    <li class="list-group-item">
                                                        <strong>Order Number:</strong> 1234 <br>
                                                        <strong>Product Name:</strong> Product A <br>
                                                        <strong>Quantity:</strong> 2 <br>
                                                        <strong>Total Price:</strong> $300.00 <br>
                                                        <strong>Status:</strong> Delivered
                                                    </li>
                                                    <li class="list-group-item">
                                                        <strong>Order Number:</strong> 1235 <br>
                                                        <strong>Product Name:</strong> Product B <br>
                                                        <strong>Quantity:</strong> 1 <br>
                                                        <strong>Total Price:</strong> $85.50 <br>
                                                        <strong>Status:</strong> In Transit
                                                    </li>
                                                    <!-- Add more order details items here if needed -->
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <ul id="reviewList" class="list-group mt-4">
            <!-- Reviews will be appended here -->
        </ul>
        <!-- Custom JavaScript -->
        <script>
            document.querySelectorAll('.review-btn').forEach(button => {
                button.addEventListener('click', function () {
                    const orderId = this.getAttribute('data-order-id');
                    document.getElementById('orderId').value = orderId;
                    document.getElementById('imagePreview').innerHTML = ''; // Clear previous image preview
                    document.getElementById('reviewImages').value = ''; // Clear file input
                    $('#reviewModal').modal('show');
                });
            });

            document.getElementById('reviewImages').addEventListener('change', function () {
                const imagePreview = document.getElementById('imagePreview');
                imagePreview.innerHTML = ''; // Clear previous image preview

                const files = this.files;
                if (files) {
                    Array.from(files).forEach(file => {
                        const reader = new FileReader();
                        reader.onload = function (e) {
                            const img = document.createElement('img');
                            img.src = e.target.result;
                            img.alt = 'Review Image';
                            img.className = 'img-thumbnail mt-2';
                            img.style.maxWidth = '200px';
                            imagePreview.appendChild(img);
                        }
                        reader.readAsDataURL(file);
                    });
                }
            });

            document.getElementById('reviewForm').addEventListener('submit', function (event) {
                event.preventDefault();

                // Get the form values
                const orderId = document.getElementById('orderId').value;
                const name = document.getElementById('reviewName').value;
                const review = document.getElementById('reviewText').value;
                const rating = document.getElementById('reviewRating').value;
                const imageFiles = document.getElementById('reviewImages').files;

                // Create a new list item for the review
                const newReview = document.createElement('li');
                newReview.className = 'list-group-item';
                newReview.innerHTML = '<strong>' + name + '</strong> - Order #' + orderId + ' - ' + rating + ' Stars<br>' + review;

                // Check if images were uploaded and preview them
                if (imageFiles.length > 0) {
                    Array.from(imageFiles).forEach(file => {
                        const img = document.createElement('img');
                        img.src = URL.createObjectURL(file);
                        img.alt = 'Review Image';
                        img.className = 'img-thumbnail mt-2';
                        img.style.maxWidth = '200px';
                        newReview.appendChild(img);
                    });
                }

                // Append the new review to the review list
                document.getElementById('reviewList').appendChild(newReview);

                // Clear the form
                document.getElementById('reviewForm').reset();

                // Close the modal
                $('#reviewModal').modal('hide');
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/profile.js"></script>
    </body>
</html>
