<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String role = currentUser != null ? currentUser.getRole() : "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');

        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #e0eafc, #cfdef3);
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            background: #ffffff;
            padding: 30px 40px;
            border-radius: 20px;
            box-shadow: 0 12px 28px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            font-size: 28px;
            color: #2c3e50;
        }

        .welcome-msg {
            text-align: center;
            font-size: 18px;
            color: #34495e;
            margin-bottom: 20px;
        }

        .search-form {
            display: flex;
            align-items: center;
            margin-bottom: 25px;
            flex-wrap: wrap;
            gap: 10px;
        }

        .search-group {
            display: flex;
            align-items: center;
            gap: 0;
        }

        .search-input {
            padding: 10px;
            font-size: 15px;
            border: 1px solid #ccc;
            border-top-left-radius: 10px;
            border-bottom-left-radius: 10px;
            border-right: none;
            width: 250px;
        }

        .search-btn {
            padding: 10px 18px;
            font-weight: 600;
            border: none;
            border-top-right-radius: 10px;
            border-bottom-right-radius: 10px;
            color: white;
            background: linear-gradient(90deg, #2980b9, #6dd5fa);
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .search-btn:hover {
            background: linear-gradient(90deg, #6dd5fa, #2980b9);
        }

        .btn {
            padding: 10px 20px;
            font-weight: 600;
            border: none;
            border-radius: 10px;
            color: white;
            background: linear-gradient(90deg, #2980b9, #6dd5fa);
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.2s ease, background 0.3s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
            background: linear-gradient(90deg, #6dd5fa, #2980b9);
        }

        .btn.red {
            background: linear-gradient(90deg, #e74c3c, #ff6b6b);
        }

        .btn.red:hover {
            background: linear-gradient(90deg, #c0392b, #ff5a5a);
        }

        .btn.dashboard {
            background: linear-gradient(90deg, #6a11cb, #2575fc);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 12px 14px;
            border-bottom: 1px solid #f0f0f0;
            text-align: left;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tr:hover {
            background-color: #f8f9fa;
        }

        .action-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .btn.small {
            padding: 6px 12px;
            font-size: 14px;
            border-radius: 8px;
            font-weight: 600;
            text-align: center;
        }

        .edit-btn {
            background: linear-gradient(90deg, #3498db, #6dd5fa);
        }

        .edit-btn:hover {
            background: linear-gradient(90deg, #2980b9, #58c9f3);
        }

        .delete-btn {
            background: linear-gradient(90deg, #e74c3c, #ff7676);
        }

        .delete-btn:hover {
            background: linear-gradient(90deg, #c0392b, #ff5a5a);
        }

        .message-box {
            text-align: center;
            font-weight: bold;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 10px;
        }

        .message-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .message-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .error {
            text-align: center;
            color: red;
            font-weight: 600;
            margin-top: 20px;
        }

        .btn-group {
            text-align: center;
            margin-top: 30px;
        }

        .btn-group a {
            margin: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>👥 Manage Users</h2>

    <% if (!role.isEmpty()) { %>
        <div class="welcome-msg">Welcome, <%= role.substring(0, 1).toUpperCase() + role.substring(1) %>!</div>
    <% } %>

    <% String message = (String) request.getAttribute("message");
       String status = (String) request.getAttribute("status");
       if (message != null) { %>
        <div class="message-box <%= "success".equals(status) ? "message-success" : "message-error" %>">
            <%= message %>
        </div>
    <% } %>

    <% if ("admin".equals(role) || "librarian".equals(role)) { %>
    <form class="search-form" action="UserServlet" method="get">
        <div class="search-group">
            <input class="search-input" type="text" name="keyword" placeholder="🔍 Search by name..." />
            <button class="search-btn" type="submit" name="action" value="search">Search</button>
        </div>
        <% if ("admin".equals(role)) { %>
            <a href="UserServlet?action=viewAll" class="btn">👁 Show All</a>
        <% } %>
    </form>
    <% } %>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <% if ("admin".equals(role) || "librarian".equals(role)) { %>
        <a href="UserServlet?action=showAddForm" class="btn">➕ Add New User</a>
    <% } %>

    <% if ("admin".equals(role)) { %>
        <a href="UserServlet?action=deleted" class="btn red">🗑️ Deleted Users</a>
    <% } %>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Role</th>
            <th>First</th>
            <th>Last</th>
            <th>Email</th>
            <th>DOB</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User u : users) {
                    if ("librarian".equals(role) && !"patron".equals(u.getRole())) continue;
        %>
        <tr>
            <td><%= u.getUserId() %></td>
            <td><%= u.getUsername() %></td>
            <td><%= u.getRole() %></td>
            <td><%= u.getFirstName() %></td>
            <td><%= u.getLastName() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getDob() %></td>
            <td><%= u.getPhoneNumber() %></td>
            <td>
                <div class="action-buttons">
                    <a class="btn small edit-btn" href="UserServlet?action=showEditForm&userId=<%= u.getUserId() %>">✏️ Edit</a>
                    <a class="btn small delete-btn" href="UserServlet?action=delete&userId=<%= u.getUserId() %>"
                       onclick="return confirm('Are you sure you want to delete this user?');">🗑️ Delete</a>
                </div>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="9" style="text-align:center;">No users found.</td></tr>
        <% } %>
        </tbody>
    </table>

    <div class="btn-group">
        <% if ("admin".equals(role)) { %>
            <a href="AdminDashboardServlet" class="btn dashboard">🏠 Admin Dashboard</a>
        <% } else if ("librarian".equals(role)) { %>
            <a href="librarian_dashboard.jsp" class="btn dashboard">🏠 Librarian Dashboard</a>
        <% } else { %>
            <a href="login.jsp" class="btn dashboard">🔐 Back to Login</a>
        <% } %>
    </div>
</div>
</body>
</html>
