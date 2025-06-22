<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Notification" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="UTF-8">
    <title>Notifications</title>
    <style>
        body {
            background: #f4f8fb;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
        }
        .container {
            max-width: 720px;
            margin: 40px auto;
            background: #fff;
            padding: 32px 24px;
            border-radius: 14px;
            box-shadow: 0 4px 16px #0002;
        }
        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #2e5c8a;
            letter-spacing: 1px;
        }
        .notif-actions {
            text-align: left;
            margin-bottom: 20px;
        }
        .notif-actions form {
            display: inline;
        }
        .notification-list {
            margin: 0;
            padding: 0;
            list-style: none;
        }
        .notification-row {
            display: flex;
            align-items: flex-start;
            background: #f8fafb;
            border-radius: 10px;
            margin-bottom: 16px;
            padding: 18px 18px 18px 8px;
            box-shadow: 0 2px 6px #0001;
            transition: background 0.2s;
        }
        .notification-row.unread {
            background: #d8e8fd;
        }
        .notif-msg {
            flex: 1;
            font-size: 1.07rem;
            color: #314659;
        }
        .notif-date {
            font-size: 0.93rem;
            color: #777;
            margin-left: 24px;
            min-width: 120px;
            text-align: right;
        }
        .notif-actions-row {
            display: flex;
            gap: 10px;
        }
        .notif-btn {
            padding: 6px 18px;
            border: none;
            border-radius: 20px;
            font-size: 0.95rem;
            cursor: pointer;
            background: #2e5c8a;
            color: #fff;
            margin-left: 5px;
            transition: background 0.15s;
        }
        .notif-btn.mark {
            background: #5eb869;
        }
        .notif-btn.delete {
            background: #e96363;
        }
        .notif-btn.mark:hover { background: #49a65c; }
        .notif-btn.delete:hover { background: #d14848; }
        .notif-btn:active { opacity: 0.7; }

        @media (max-width: 600px) {
            .container { padding: 12px 2vw; }
            .notification-row { flex-direction: column; align-items: stretch; }
            .notif-date { margin-left: 0; margin-top: 6px; text-align: left; }
            .notif-actions-row { margin-top: 8px; }
        }

        .msg-success {
            background: #e7f7ec;
            color: #2e8c52;
            padding: 10px 22px;
            margin-bottom: 18px;
            border-radius: 7px;
            border: 1px solid #bfe6d1;
            font-size: 1rem;
        }
        .msg-error {
            background: #fdeaea;
            color: #c84a4a;
            padding: 10px 22px;
            margin-bottom: 18px;
            border-radius: 7px;
            border: 1px solid #f2c7c7;
            font-size: 1rem;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Notifications</h2>

    <!-- رسائل النجاح أو الخطأ -->
    <%
        String msg = (String) session.getAttribute("message");
        if (msg != null) {
            String status = (String) session.getAttribute("status");
            if ("success".equals(status)) {
    %>
                <div class="msg-success"><%= msg %></div>
    <%
            } else {
    %>
                <div class="msg-error"><%= msg %></div>
    <%
            }
            session.removeAttribute("message");
            session.removeAttribute("status");
        }
    %>

    <div class="notif-actions">
        <!-- زر تعليم كل الإشعارات كمقروءة -->
        <form action="NotificationServlet" method="get" style="display:inline;">
            <input type="hidden" name="action" value="markAllRead"/>
            <button class="notif-btn mark" type="submit">Mark All as Read</button>
        </form>
    </div>

    <ul class="notification-list">
        <%
            List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
            if (notifications == null || notifications.isEmpty()) {
        %>
            <li style="color:#888;text-align:center;padding:30px 0;">No notifications yet.</li>
        <%
            } else {
                for (Notification notif : notifications) {
        %>
            <li class="notification-row <%= "unread".equals(notif.getStatus()) ? "unread" : "" %>">
                <div class="notif-msg"><%= notif.getMessage() %></div>
                <div class="notif-date">
                    <%= notif.getNotificationDate().toString().substring(0,16).replace('T',' ') %>
                </div>
                <div class="notif-actions-row">
                    <% if ("unread".equals(notif.getStatus())) { %>
                        <form action="NotificationServlet" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="markRead"/>
                            <input type="hidden" name="notificationId" value="<%= notif.getNotificationId() %>"/>
                            <button class="notif-btn mark" type="submit">Mark as Read</button>
                        </form>
                    <% } %>
                    <form action="NotificationServlet" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="notificationId" value="<%= notif.getNotificationId() %>"/>
                        <button class="notif-btn delete" type="submit">Delete</button>
                    </form>
                </div>
            </li>
        <%
                }
            }
        %>
    </ul>
</div>
</body>
</html>
