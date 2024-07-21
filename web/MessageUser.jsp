<%-- 
    Document   : Message
    Created on : Jul 14, 2024, 9:59:50 PM
    Author     : hailt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
       <title>4FOODHD</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
        <!-- https://fonts.google.com/specimen/Roboto -->
        <link rel="stylesheet" href="css/fontawesome.min.css">
        <!-- https://fontawesome.com/ -->
        <script src="https://unpkg.com/unlazy@0.11.3/dist/unlazy.with-hashing.iife.js" defer init></script>
        <script type="text/javascript">
            window.tailwind.config = {
                darkMode: ['class'],
                theme: {
                    extend: {
                        colors: {
                            border: 'hsl(var(--border))',
                            input: 'hsl(var(--input))',
                            ring: 'hsl(var(--ring))',
                            background: 'hsl(var(--background))',
                            foreground: 'hsl(var(--foreground))',
                            primary: {
                                DEFAULT: 'hsl(var(--primary))',
                                foreground: 'hsl(var(--primary-foreground))'
                            },
                            secondary: {
                                DEFAULT: 'hsl(var(--secondary))',
                                foreground: 'hsl(var(--secondary-foreground))'
                            },
                            destructive: {
                                DEFAULT: 'hsl(var(--destructive))',
                                foreground: 'hsl(var(--destructive-foreground))'
                            },
                            muted: {
                                DEFAULT: 'hsl(var(--muted))',
                                foreground: 'hsl(var(--muted-foreground))'
                            },
                            accent: {
                                DEFAULT: 'hsl(var(--accent))',
                                foreground: 'hsl(var(--accent-foreground))'
                            },
                            popover: {
                                DEFAULT: 'hsl(var(--popover))',
                                foreground: 'hsl(var(--popover-foreground))'
                            },
                            card: {
                                DEFAULT: 'hsl(var(--card))',
                                foreground: 'hsl(var(--card-foreground))'
                            },
                        },
                    }
                }
            }
        </script>
        <style type="text/tailwindcss">
            @layer base {
                :root {
                    --background: 0 0% 100%;
                    --foreground: 240 10% 3.9%;
                    --card: 0 0% 100%;
                    --card-foreground: 240 10% 3.9%;
                    --popover: 0 0% 100%;
                    --popover-foreground: 240 10% 3.9%;
                    --primary: 240 5.9% 10%;
                    --primary-foreground: 0 0% 98%;
                    --secondary: 240 4.8% 95.9%;
                    --secondary-foreground: 240 5.9% 10%;
                    --muted: 240 4.8% 95.9%;
                    --muted-foreground: 240 3.8% 46.1%;
                    --accent: 240 4.8% 95.9%;
                    --accent-foreground: 240 5.9% 10%;
                    --destructive: 0 84.2% 60.2%;
                    --destructive-foreground: 0 0% 98%;
                    --border: 240 5.9% 90%;
                    --input: 240 5.9% 90%;
                    --ring: 240 5.9% 10%;
                    --radius: 0.5rem;
                }
                .dark {
                    --background: 240 10% 3.9%;
                    --foreground: 0 0% 98%;
                    --card: 240 10% 3.9%;
                    --card-foreground: 0 0% 98%;
                    --popover: 240 10% 3.9%;
                    --popover-foreground: 0 0% 98%;
                    --primary: 0 0% 98%;
                    --primary-foreground: 240 5.9% 10%;
                    --secondary: 240 3.7% 15.9%;
                    --secondary-foreground: 0 0% 98%;
                    --muted: 240 3.7% 15.9%;
                    --muted-foreground: 240 5% 64.9%;
                    --accent: 240 3.7% 15.9%;
                    --accent-foreground: 0 0% 98%;
                    --destructive: 0 62.8% 30.6%;
                    --destructive-foreground: 0 0% 98%;
                    --border: 240 3.7% 15.9%;
                    --input: 240 3.7% 15.9%;
                    --ring: 240 4.9% 83.9%;
                }
            }
        </style>
        <script type="text/javascript">
            window.onload = function () {
                var error = "<%= (String) request.getAttribute("error") %>";
                if (error !== "") {
                    alert(error);
                }
            };
        </script>
    </head>
    <body>

        <div class="flex h-screen bg-zinc-100 dark:bg-zinc-900">

            <div class="w-1/4 bg-card p-4 border-r border-border">
                <div class="flex items-center justify-between mb-4">
                    <h2 class="text-lg font-bold text-foreground">Đoạn chat</h2>
                </div>
                <form class="flex items-center" action="searchMessageUser" method="POST">
                    <input type="text" name="searchMessage" placeholder="Tìm kiếm tin nhắn" class="w-full p-2 mb-4 border border-border rounded bg-input text-foreground focus:ring-2 focus:ring-primary" />
                    <button type="submit" class="ml-2 p-2 mb-4 border border-border rounded bg-primary text-white focus:ring-2 focus:ring-primary">
                        <i class="fas fa-search"></i>
                    </button>
                </form>
                <!--                <div class="mb-4">
                                    <h3 class="text-sm font-semibold text-foreground">Hộp thư</h3>
                                    <h3 class="text-sm font-semibold text-foreground">Cộng đồng</h3>
                                </div>-->
                <div class="space-y-2 overflow-y-auto">
                    <c:forEach items="${listRestaurantName}" var="r">
                        <a href="messageUser1?userId=${sessionScope.account.accountId}&restaurantId=${r.restaurantId}" class="flex items-center space-x-2 p-2 rounded hover:bg-muted cursor-pointer">
                            <img src="img/${r.restaurantImageURL}" alt="Không thể tải ảnh" class="w-10 h-10 rounded-full" />
                            <div class="flex-1">
                                <h4 class="text-sm font-semibold text-foreground">${r.restaurantName}</h4>
                                <!--                                <p class="text-xs text-muted-foreground">có code k gửi hình e xem phát</p>-->
                            </div>
                            <span class="w-2 h-2 bg-blue-500 rounded-full"></span>
                        </a>
                    </c:forEach>
                </div>

            </div>
            <div class="flex-1 flex flex-col">
                <div class="flex items-center justify-between p-4 border-b border-border bg-card">
                    <div class="flex items-center space-x-2">
                        <!--                        <img src="https://placehold.co/40x40" alt="Group avatar" class="w-10 h-10 rounded-full" />-->
                        <a href="home" class="text-lg font-bold text-foreground">4FOODHD</a>
                    </div>
                    <!--                    <div class="flex space-x-2">
                                            <button class="p-2 rounded-full bg-muted text-muted-foreground">
                                                <img aria-hidden="true" alt="info-icon" src="https://openui.fly.dev/openui/24x24.svg?text=ℹ️" />
                                            </button>
                                        </div>-->
                </div>
                <div class="flex-1 p-4 overflow-y-auto space-y-4 bg-card">
                    <c:choose>
                        <c:when test="${not empty listMessageUser}">
                            <c:forEach items="${listMessageUser}" var="m" varStatus="status">

                                <div class="flex space-x-2 " <c:if test="${status.last}">id="lastMessage"</c:if>>
                                    <img src="img/${m.senderImageAvatar}" alt="Không thể tải ảnh" class="w-10 h-10 rounded-full" />
                                    <div class="flex-1">

                                        <c:if test="${m.senderId == userId}">
                                            <div class="bg-muted p-2 rounded-lg shadow"  style="background: #03A9F4;">
                                                <p class="text-sm text-foreground" style="color: white;">${m.messageContent}</p>
                                            </div>
                                        </c:if>
                                        <c:if test="${m.senderId == restaurantId}">
                                            <div class="bg-muted p-2 rounded-lg shadow">
                                                <p class="text-sm text-foreground">${m.messageContent}</p>
                                            </div>
                                        </c:if>
                                        <span class="text-xs text-muted-foreground">${m.formattedTimestamp}</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div style="display: flex; justify-content: center;">
                                <img src="img/message_blank.jpg" width="100px" height="100px" alt="Không tìm thấy ảnh"/>
                                <h3 style="display: flex; align-items: center;">Hãy gửi tin nhắn cho chúng tôi!</h3>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="p-4 border-t border-border bg-card">
                    <form action="insertMessage" method="POST">
                        <input name="senderId" value="${userId}" hidden/>
                        <input name="receiverId" value="${restaurantId}" hidden/>
                        <input name="messageContent" type="text" required="" placeholder="Nhập tin nhắn..." style="width:95%"  class="w-full p-2 border border-border rounded bg-input text-foreground focus:ring-2 focus:ring-primary" />
                        <button style="margin-left: 10px" >
                            <i class="far fa-paper-plane"></i>
                        </button>
                    </form>
                </div>

            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var lastMessage = document.getElementById("lastMessage");
                if (lastMessage) {
                    lastMessage.scrollIntoView({behavior: "smooth"});
                }
            });
        </script>
</html>


