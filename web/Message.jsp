<%-- 
    Document   : Message
    Created on : Jul 14, 2024, 9:59:50 PM
    Author     : hailt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography"></script>
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
    </head>
    <body>
        <div class="flex h-screen bg-zinc-100 dark:bg-zinc-900">
            <div class="w-1/4 bg-card p-4 border-r border-border">
                <div class="flex items-center justify-between mb-4">
                    <h2 class="text-lg font-bold text-foreground">Đoạn chat</h2>
                </div>
                <input type="text" placeholder="Tìm kiếm trên Messenger" class="w-full p-2 mb-4 border border-border rounded bg-input text-foreground focus:ring-2 focus:ring-primary" />
                <div class="mb-4">
                    <h3 class="text-sm font-semibold text-foreground">Hộp thư</h3>
                    <h3 class="text-sm font-semibold text-foreground">Cộng đồng</h3>
                </div>
                <div class="space-y-2 overflow-y-auto">
                    <div class="flex items-center space-x-2 p-2 rounded hover:bg-muted cursor-pointer">
                        <img src="https://placehold.co/40x40" alt="User avatar" class="w-10 h-10 rounded-full" />
                        <div class="flex-1">
                            <h4 class="text-sm font-semibold text-foreground">Minh Hoàng</h4>
                            <p class="text-xs text-muted-foreground">có code k gửi hình e xem phát · 1 giờ</p>
                        </div>
                        <span class="w-2 h-2 bg-blue-500 rounded-full"></span>
                    </div>
                </div>
            </div>
            <div class="flex-1 flex flex-col">
                <div class="flex items-center justify-between p-4 border-b border-border bg-card">
                    <div class="flex items-center space-x-2">
                        <img src="https://placehold.co/40x40" alt="Group avatar" class="w-10 h-10 rounded-full" />
                        <h3 class="text-lg font-bold text-foreground">Bộ Tộc Cờ TFT</h3>
                    </div>
                    <div class="flex space-x-2">
                        <button class="p-2 rounded-full bg-muted text-muted-foreground">
                            <img aria-hidden="true" alt="info-icon" src="https://openui.fly.dev/openui/24x24.svg?text=ℹ️" />
                        </button>
                    </div>
                </div>
                <div class="flex-1 p-4 overflow-y-auto space-y-4 bg-card">
                    <div class="flex space-x-2">
                        <img src="https://placehold.co/40x40" alt="User avatar" class="w-10 h-10 rounded-full" />
                        <div class="flex-1">
                            <div class="bg-muted p-2 rounded-lg shadow">
                                <p class="text-sm text-foreground">3 tháng đi húp hàu rồi</p>
                            </div>
                            <span class="text-xs text-muted-foreground">08:45</span>
                        </div>
                    </div>
                </div>
                <div class="p-4 border-t border-border bg-card">
                    <input type="text" placeholder="Nhập tin nhắn..." class="w-full p-2 border border-border rounded bg-input text-foreground focus:ring-2 focus:ring-primary" />
                    <i class="far fa-paper-plane"></i>
                </div>

            </div>
        </div>


    </body>
</html>
