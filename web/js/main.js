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



   
            
      document.addEventListener('DOMContentLoaded', function () {
    const quantityInputs = document.querySelectorAll('.quantity-input');

    // Function to update quantity and price
    function updateQuantityAndPrice(quantityInput, change) {
        let currentValue = parseInt(quantityInput.value);
        let newValue = currentValue + change;

        if (newValue < 1) {
            newValue = 1;
        }

        quantityInput.value = newValue;
        // Cập nhật giá
        updatePrice(quantityInput, newValue);
        // Cập nhật tổng giá khi thay đổi số lượng
        updateTotal();
    }

    // Function to update price per item
    function updatePrice(quantityInput, quantity) {
        const pricePerItem = parseFloat(quantityInput.dataset.price);
        const totalPriceElement = quantityInput.closest('tr').querySelector('.total-price');
        const totalPrice = quantity * pricePerItem;
        totalPriceElement.innerText = totalPrice.toFixed(2);
    }

    // Function to update total price
    function updateTotal() {
        let total = 0;
        const rows = document.querySelectorAll('tbody > tr');
        rows.forEach(function (row) {
            const priceElement = row.querySelector('.total-price');
            total += parseFloat(priceElement.innerText);
        });

        // Update subtotal and total elements
        const subtotalElement = document.querySelector('.subtotal');
        subtotalElement.innerText = total.toFixed(2);

        const totalElement = document.querySelector('.cart-total');
        totalElement.innerText = total.toFixed(2);
    }

    // Attach event listeners to quantity inputs
    quantityInputs.forEach(function (quantityInput) {
        const btnPlus = quantityInput.closest('.quantity').querySelector('.btn-plus');
        const btnMinus = quantityInput.closest('.quantity').querySelector('.btn-minus');

        // Add event listener for btnPlus
        btnPlus.addEventListener('click', handleClickPlus);

        // Add event listener for btnMinus
        btnMinus.addEventListener('click', handleClickMinus);

        // Update initial price based on current quantity
        updatePrice(quantityInput, parseInt(quantityInput.value));

        // Define handleClickPlus function
        function handleClickPlus() {
            updateQuantityAndPrice(quantityInput, 1);

            // Remove event listener after click
            btnPlus.removeEventListener('click', handleClickPlus);
            setTimeout(function () {
                btnPlus.addEventListener('click', handleClickPlus);
            }, 500); // Re-add after 500ms to prevent rapid clicking issues
        }

        // Define handleClickMinus function
        function handleClickMinus() {
            updateQuantityAndPrice(quantityInput, -1);

            // Remove event listener after click
            btnMinus.removeEventListener('click', handleClickMinus);
            setTimeout(function () {
                btnMinus.addEventListener('click', handleClickMinus);
            }, 500); 
        }
    });
});

        

})(jQuery);

