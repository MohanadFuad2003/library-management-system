<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String currentRole = currentUser != null ? currentUser.getRole() : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New User</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(-45deg, #fbc2eb, #a6c1ee, #fbc2eb, #a6c1ee);
            background-size: 400% 400%;
            animation: gradientFlow 15s ease infinite;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 40px 20px;
        }

        @keyframes gradientFlow {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .form-container {
            background: #ffffffee;
            backdrop-filter: blur(12px);
            padding: 50px 30px;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            position: relative;
            animation: fadeInUp 1s ease-out;
        }

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(30px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        .form-container::before {
            content: "";
            position: absolute;
            top: -70px;
            left: calc(50% - 35px);
            width: 70px;
            height: 70px;
            background: url('https://cdn-icons-png.flaticon.com/512/747/747376.png') no-repeat center;
            background-size: contain;
            animation: float 4s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #2c3e50;
            font-size: 26px;
        }

        label {
            display: block;
            margin-top: 15px;
            font-weight: 600;
            color: #444;
        }

        input, select {
            width: 100%;
            padding: 12px 14px;
            border: none;
            border-radius: 12px;
            background: #f5f7fa;
            box-shadow: inset 2px 2px 6px #d1d9e6, inset -2px -2px 6px #ffffff;
            margin-top: 6px;
            font-size: 15px;
            transition: 0.3s ease;
        }

        input:focus, select:focus {
            background: #fff;
            box-shadow: 0 0 0 3px rgba(39, 174, 96, 0.2);
            outline: none;
        }

        button {
            margin-top: 25px;
            padding: 14px;
            width: 100%;
            background: #27ae60;
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            box-shadow: 0 5px 15px rgba(39, 174, 96, 0.2);
            transition: background 0.3s ease, transform 0.2s ease;
        }

        button:hover {
            background: #219150;
            transform: scale(1.03);
        }

        .error {
            color: #c0392b;
            font-weight: bold;
            text-align: center;
            margin-top: 15px;
        }

        .back-link {
            display: block;
            margin-top: 20px;
            text-align: center;
            font-weight: bold;
            color: #2980b9;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .info-text {
            margin-top: 8px;
            font-style: italic;
            color: #666;
            font-size: 14px;
            text-align: center;
        }

        @media (max-width: 600px) {
            .form-container {
                padding: 35px 20px;
            }

            .form-container::before {
                width: 60px;
                height: 60px;
                top: -60px;
            }
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>➕ Add New User</h2>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="UserServlet" method="post">
        <input type="hidden" name="action" value="add" />

        <label>Username</label>
        <input type="text" name="username" required />

        <label>Password</label>
        <input type="password" name="password" required />

        <% if ("librarian".equals(currentRole)) { %>
            <input type="hidden" name="role" value="patron" />
            <label>Role</label>
            <input type="text" value="Patron" readonly style="background:#f0f0f0; cursor:not-allowed; text-align:center;" />
            <p class="info-text">As a librarian, you can only add users with role "Patron".</p>
        <% } else { %>
            <label>Role</label>
            <select name="role" required>
                <option value="" disabled selected>Select Role</option>
                <option value="admin">Admin</option>
                <option value="librarian">Librarian</option>
                <option value="patron">Patron</option>
            </select>
        <% } %>

        <label>First Name</label>
        <input type="text" name="firstName" required />

        <label>Last Name</label>
        <input type="text" name="lastName" required />

        <label>Email</label>
        <input type="email" name="email" required />

        <label>Date of Birth</label>
        <input type="date" name="dob" required />

        <label>Phone Number</label>
        <input type="tel" name="phoneNumber" required />

        <button type="submit">Add User</button>
    </form>

    <a class="back-link" href="UserServlet?action=viewAll">← Back to Users List</a>
</div>
</body>
</html>
