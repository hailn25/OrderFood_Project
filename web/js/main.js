(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(window).width() < 992) {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow');
            } else {
                $('.fixed-top').removeClass('shadow');
            }
        } else {
            if ($(this).scrollTop() > 55) {
                $('.fixed-top').addClass('shadow').css('top', -55);
            } else {
                $('.fixed-top').removeClass('shadow').css('top', 0);
            }
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Testimonial carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });


    // vegetable carousel
    $(".vegetable-carousel").owlCarousel({
        autoplay: false, // Disable autoplay
        smartSpeed: 1500,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 2
            },
            992: {
                items: 3
            },
            1200: {
                items: 4
            }
        }
    });



    // Modal Video
    $(document).ready(function () {
        var $videoSrc;
        $('.btn-play').click(function () {
            $videoSrc = $(this).data("src");
        });
        console.log($videoSrc);

        $('#videoModal').on('shown.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
        })

        $('#videoModal').on('hide.bs.modal', function (e) {
            $("#video").attr('src', $videoSrc);
        })
    });



    // Product Quantity
    document.addEventListener('DOMContentLoaded', function () {
        const quantityInput = document.querySelector('.quantity-input');
        const addToCheckoutForm = document.getElementById('addToCheckout');
        const checkoutQuantityInput = document.getElementById('checkoutQuantity');

        quantityInput.addEventListener('input', function () {
            let newValue = parseInt(quantityInput.value);
            if (isNaN(newValue) || newValue < 1) {
                newValue = 1;
            }
            quantityInput.value = newValue;
            checkoutQuantityInput.value = newValue;
        });

        addToCheckoutForm.addEventListener('submit', function () {
            checkoutQuantityInput.value = quantityInput.value;
        });
    });


    document.addEventListener('DOMContentLoaded', function () {
        const quantityInputs = document.querySelectorAll('.quantity-input');
        const addToCartForm = document.getElementById('addToCartForm');
        const addToCartQuantityInput = document.getElementById('addToCartQuantity');

        quantityInputs.forEach(function (quantityInput) {
            const maxQuantity = parseInt(quantityInput.dataset.maxQuantity);
            const btnPlus = quantityInput.closest('.quantity').querySelector('.btn-plus');
            const btnMinus = quantityInput.closest('.quantity').querySelector('.btn-minus');

            btnPlus.addEventListener('click', function () {
                updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) + 1, maxQuantity);
            });

            btnMinus.addEventListener('click', function () {
                updateQuantityAndPrice(quantityInput, parseInt(quantityInput.value) - 1, maxQuantity);
            });

            quantityInput.addEventListener('input', function () {
                let newValue = parseInt(quantityInput.value);
                if (isNaN(newValue) || newValue < 1) {
                    newValue = 1;
                }
                updateQuantityAndPrice(quantityInput, newValue, maxQuantity);
            });

            // Function to update quantity and price
            function updateQuantityAndPrice(quantityInput, newQuantity, maxQuantity) {
                if (newQuantity < 1) {
                    confirmDelete(event, 'deleteForm' + quantityInput.dataset.productId);
                    return;
                }
                if (newQuantity > maxQuantity) {
                    alert('Số lượng vượt quá số lượng tối đa có sẵn');
                    return;
                }

                quantityInput.value = newQuantity;
                addToCartQuantityInput.value = newQuantity;
                updatePrice(quantityInput, newQuantity);
            }

            function updatePrice(quantityInput, quantity) {
                const pricePerItem = parseFloat(quantityInput.dataset.price);
                // Example of updating total price display based on quantity
                const totalPriceElement = quantityInput.closest('.row').querySelector('.price-total');
                const totalPrice = quantity * pricePerItem * 1000;
                totalPriceElement.innerText = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(totalPrice);
            }

            updatePrice(quantityInput, parseInt(quantityInput.value));
        });

        // Example of submitting form
        addToCartForm.addEventListener('submit', function (event) {
            // You can add additional validation or actions before submitting the form
            // event.preventDefault(); // Uncomment to prevent default form submission for testing
            // Example of fetching data if needed
            const formData = new FormData(addToCartForm);
            fetch(addToCartForm.action, {
                method: 'POST',
                body: formData
            })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.text();
                    })
                    .then(data => {
                        // Handle response data if necessary
                    })
                    .catch(error => {
                        console.error('Có vấn đề xảy ra trong quá trình fetch:', error);
                    });
        });
    });

})(jQuery);

