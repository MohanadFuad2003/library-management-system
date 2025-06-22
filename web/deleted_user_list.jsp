<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>🗑️ Deleted Users Archive</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #fceabb, #f8b500);
            padding: 40px 20px;
            min-height: 100vh;
            margin: 0;
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: rgba(255,255,255,0.95);
            border-radius: 25px;
            padding: 35px 40px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.12);
            backdrop-filter: blur(12px);
            animation: fadeIn 1s ease forwards;
        }
        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }
        h2 {
            text-align: center;
            font-weight: 700;
            font-size: 2.4rem;
            color: #d35400;
            margin-bottom: 40px;
            letter-spacing: 1.5px;
            text-shadow: 0 2px 6px rgba(211, 84, 0, 0.3);
        }
        .user-card {
            background: #fff8e1;
            border-left: 6px solid #f39c12;
            padding: 20px 30px;
            margin-bottom: 20px;
            border-radius: 20px;
            box-shadow: 0 8px 15px rgba(243, 156, 18, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .user-card:hover {
            transform: scale(1.02);
            box-shadow: 0 15px 25px rgba(243, 156, 18, 0.4);
        }
        .user-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 12px;
            flex-wrap: wrap;
            gap: 10px;
        }
        .user-name {
            font-size: 1.4rem;
            font-weight: 700;
            color: #e67e22;
        }
        .user-role {
            font-style: italic;
            font-weight: 600;
            color: #8e44ad;
            background: #f0e5ff;
            padding: 4px 10px;
            border-radius: 12px;
            font-size: 1rem;
        }
        .user-details {
            font-size: 1rem;
            color: #2d3436;
            margin-bottom: 15px;
            line-height: 1.4;
        }
        .actions {
            display: flex;
            gap: 24px;
            flex-wrap: wrap;
        }
        .actions a {
            background: #27ae60;
            color: white;
            padding: 10px 22px;
            border-radius: 40px;
            text-decoration: none;
            font-weight: 600;
            box-shadow: 0 6px 15px rgba(39, 174, 96, 0.3);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            font-size: 0.95rem;
        }
        .actions a.delete-perm {
            background: #e74c3c;
            box-shadow: 0 6px 15px rgba(231, 76, 60, 0.3);
        }
        .actions a:hover {
            filter: brightness(1.1);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }
        .btn-back {
            display: block;
            max-width: 180px;
            margin: 40px auto 0 auto;
            padding: 14px 0;
            text-align: center;
            background: #34495e;
            color: white;
            font-weight: 700;
            border-radius: 30px;
            text-decoration: none;
            box-shadow: 0 8px 20px rgba(52, 73, 94, 0.4);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        .btn-back:hover {
            background-color: #2c3e50;
            box-shadow: 0 12px 35px rgba(44, 62, 80, 0.6);
        }
        .no-users {
            text-align: center;
            font-weight: 700;
            color: #b2bec3;
            font-size: 1.3rem;
            margin-top: 60px;
            font-style: italic;
            letter-spacing: 0.04em;
        }
        /* Responsive */
        @media (max-width: 700px) {
            .user-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 6px;
            }
            .actions {
                flex-direction: column;
                gap: 12px;
            }
            .actions a {
                justify-content: center;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>🗑️ Deleted Users Archive</h2>

    <%
        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
    %>
    <div class="user-card" title="User ID: <%= user.getUserId() %>">
        <div class="user-header">
            <div class="user-name"><%= user.getFirstName() %> <%= user.getLastName() %></div>
            <div class="user-role"><%= user.getRole() != null ? user.getRole().toUpperCase() : "N/A" %></div>
        </div>
        <div class="user-details">
            <strong>Username:</strong> <%= user.getUsername() %> &nbsp;&bull;&nbsp;
            <strong>Email:</strong> <%= user.getEmail() %> &nbsp;&bull;&nbsp;
            <strong>DOB:</strong> <%= user.getDob() != null ? user.getDob() : "N/A" %> &nbsp;&bull;&nbsp;
            <strong>Phone:</strong> <%= user.getPhoneNumber() %>
        </div>
        <div class="actions">
            <a href="UserServlet?action=restore&userId=<%= user.getUserId() %>"
               onclick="return confirm('Are you sure you want to restore this user?');"
               title="Restore this user">♻️ Restore</a>
            <a href="UserServlet?action=deletePermanent&userId=<%= user.getUserId() %>"
               class="delete-perm"
               onclick="return confirm('Are you sure you want to permanently delete this user? This action cannot be undone.');"
               title="Permanently delete this user">🗑️ Delete Permanently</a>
        </div>
    </div>

    <%      }
        } else { %>
        <p class="no-users">No deleted users found.</p>
    <% } %>

    <a href="UserServlet?action=viewAll" class="btn-back">← Back to Users List</a>
</div>

</body>
</html>

