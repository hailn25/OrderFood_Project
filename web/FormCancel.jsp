<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            success: {
                                DEFAULT: 'hsl(120, 60%, 50%)',
                                foreground: 'hsl(120, 100%, 98%)'
                            },
                            danger: {
                                DEFAULT: 'hsl(0, 84.2%, 60.2%)',
                                foreground: 'hsl(0, 100%, 98%)'
                            },
                        },
                    }
                }
            }
        </script>
        <style type="text/tailwindcss">
            @layer base {
                :root {
                    --background: 210 10% 98%;
                    --foreground: 210 10% 15%;
                    --card: 210 10% 100%;
                    --card-foreground: 210 10% 15%;
                    --popover: 210 10% 100%;
                    --popover-foreground: 210 10% 15%;
                    --primary: 210 90% 50%;
                    --primary-foreground: 210 100% 98%;
                    --secondary: 210 60% 40%;
                    --secondary-foreground: 210 10% 15%;
                    --muted: 210 10% 90%;
                    --muted-foreground: 210 20% 40%;
                    --accent: 210 70% 30%;
                    --accent-foreground: 210 100% 98%;
                    --destructive: 0 84.2% 60.2%;
                    --destructive-foreground: 0 100% 98%;
                    --border: 210 10% 80%;
                    --input: 210 10% 90%;
                    --ring: 210 80% 50%;
                    --radius: 0.5rem;
                }
                .dark {
                    --background: 210 10% 15%;
                    --foreground: 0 0% 100%;
                    --card: 210 10% 15%;
                    --card-foreground: 0 0% 100%;
                    --popover: 210 10% 15%;
                    --popover-foreground: 0 0% 100%;
                    --primary: 0 0% 100%;
                    --primary-foreground: 210 10% 15%;
                    --secondary: 210 20% 20%;
                    --secondary-foreground: 0 0% 100%;
                    --muted: 210 20% 20%;
                    --muted-foreground: 210 20% 80%;
                    --accent: 210 30% 40%;
                    --accent-foreground: 0 0% 100%;
                    --destructive: 0 80% 40%;
                    --destructive-foreground: 0 100% 100%;
                    --border: 210 20% 40%;
                    --input: 210 20% 40%;
                    --ring: 210 80% 60%;
                }
            }
        </style>
    </head>
    <body>
        <div class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
            <div class="bg-card text-card-foreground rounded-lg shadow-lg w-96">
                <form action="cancelOrderForm" method="post">
                    <div class="flex justify-between items-center border-b border-border p-4">
                        <h2 class="text-lg font-semibold">Lý do giao hàng thất bại</h2>
                        <button type="button" class="text-muted-foreground hover:text-foreground focus:outline-none" onclick="history.back()">
                            <img aria-hidden="true" alt="close" src="https://openui.fly.dev/openui/24x24.svg?text=✖" />
                        </button>
                    </div>
                    <div class="p-4">
                        <textarea name="reason" class="w-full h-32 p-2 border border-border rounded-md bg-input text-foreground" placeholder="Enter your reason here..."></textarea>
                    </div>
                    <div class="flex justify-end gap-2 bg-muted p-4 rounded-b-lg">
                        <input type="hidden" name="oid" value="${orderId}" />
                        <button type="button" class="bg-danger text-destructive-foreground hover:bg-danger/80 px-4 py-2 rounded-md transition-colors duration-300 ease-in-out" onclick="history.back()">Hủy</button>
                        <button type="submit" class="bg-success text-success-foreground hover:bg-success/80 px-4 py-2 rounded-md transition-colors duration-300 ease-in-out">Gửi</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>