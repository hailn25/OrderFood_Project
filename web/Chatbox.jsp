<%-- 
    Document   : Chatbox
    Created on : Jul 2, 2024, 9:53:17 PM
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/chatbox.css" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="chatbox-wrapper">
            <div class="chatbox-toggle">
                <i class="fas fa-comment-dots"></i>
            </div>
            <div class="chatbox-message-wrapper">
                <div class="chatbox-message-header">
                    <div class="chatbox-message-profile">
                        <img src="img/staff1.png"
                             alt="" class="chatbox-message-image">
                        <div>
                            <h4 class="chatbox-message-name">Lê Thị Thuỳ Dương</h4>
                            <p class="chatbox-message-status">online</p>
                        </div>
                    </div>
                    <div class="chatbox-message-dropdown">
                        <i class="fas fa-ellipsis-h chatbox-message-dropdown-toggle"></i>
                        <ul class="chatbox-message-dropdown-menu">
                            <li>
                                <a href="#">Search</a>
                            </li>
                            <li>
                                <a href="#">Report</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="chatbox-message-content">
                    <h4 class="chatbox-message-no-message">Hãy gửi tin nhắn cho tôi nào!</h4>
                    <!-- <div class="chatbox-message-item sent">
                                            <span class="chatbox-message-item-text">
                                                    Lorem, ipsum, dolor sit amet consectetur adipisicing elit. Quod, fugiat?
                                            </span>
                                            <span class="chatbox-message-item-time">08:30</span>
                                    </div>
                                    <div class="chatbox-message-item received">
                                            <span class="chatbox-message-item-text">
                                                    Lorem, ipsum, dolor sit amet consectetur adipisicing elit. Quod, fugiat?
                                            </span>
                                            <span class="chatbox-message-item-time">08:30</span>
                                    </div> -->
                </div>
                <div class="chatbox-message-bottom">
                    <form action="#" class="chatbox-message-form">
                        <textarea rows="1" placeholder="Type message..." class="chatbox-message-input"></textarea>
                        <button type="submit" class="chatbox-message-submit"><i class="fas fa-paper-plane"></i></button>
                    </form>
                </div>
            </div>
        </div>


        <script src="js/chatbox.js"></script>
    </body>
</html>
