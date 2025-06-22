<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Quicksand', sans-serif;
            background: linear-gradient(120deg, #fdfbfb, #ebedee);
            animation: flowBg 20s linear infinite alternate;
            color: #2d3436;
            min-height: 100vh;
        }

        @keyframes flowBg {
            0% { background-position: 0% 50%; }
            100% { background-position: 100% 50%; }
        }

        .header {
            background-color: #88d8b0;
            color: white;
            text-align: center;
            padding: 30px;
            font-size: 32px;
            font-weight: bold;
            border-bottom: 4px solid #56c596;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);
            letter-spacing: 1px;
        }

        .dashboard-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 30px;
            padding: 40px;
            max-width: 1200px;
            margin: auto;
        }

        .card {
            background: #ffffffdd;
            backdrop-filter: blur(8px);
            border-radius: 16px;
            padding: 30px 20px;
            text-align: center;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.07);
            transition: transform 0.3s ease;
            border-left: 6px solid #88d8b0;
        }

        .card:hover {
            transform: translateY(-6px);
        }

        .card h3 {
            font-size: 20px;
            margin-bottom: 10px;
            color: #34495e;
        }

        .card p {
            font-size: 28px;
            font-weight: bold;
            color: #27ae60;
        }

        .btn-container {
            text-align: center;
            margin-top: 50px;
            padding-bottom: 40px;
        }

        .btn-container a {
            display: inline-block;
            margin: 12px;
            padding: 14px 26px;
            background: #56c596;
            color: white;
            font-weight: bold;
            text-decoration: none;
            border-radius: 50px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
            transition: all 0.3s ease;
        }

        .btn-container a:hover {
            background: #45b08c;
            transform: translateY(-3px);
        }

        .btn-container a.logout {
            background: #e74c3c;
        }

        .btn-container a.logout:hover {
            background: #c0392b;
        }

        @media (max-width: 600px) {
            .header {
                font-size: 24px;
                padding: 20px;
            }

            .btn-container a {
                display: block;
                width: 90%;
                margin: 14px auto;
            }
        }
    </style>
</head>
<body>

<div class="header">📚 Library Admin Dashboard</div>

<div class="dashboard-container">
    <div class="card">
        <h3>📘 Total Books</h3>
        <p><%= request.getAttribute("totalBooks") %></p>
    </div>
    <div class="card">
        <h3>👩‍🏫 Total Users</h3>
        <p><%= request.getAttribute("totalUsers") %></p>
    </div>
    <div class="card">
        <h3>📖 Borrowed Books</h3>
        <p><%= request.getAttribute("borrowedBooks") %></p>
    </div>
    <div class="card">
        <h3>💰 Outstanding Fines</h3>
        <p><%= request.getAttribute("outstandingFines") %> JOD</p>
    </div>
</div>

<div class="btn-container">
    <a href="BookServlet?action=viewAll">📗 Manage Books</a>
    <a href="UserServlet?action=viewAll">👨‍👩‍👧 Manage Users</a>
    <a href="FineServlet?action=viewAll">💳 Manage Fines</a>
    <a href="ReportsServlet">📊 Reports</a>
    <a href="SystemConfigServlet">⚙️ Settings</a>
    <a href="NotificationServlet?action=viewAll">🔔 Notifications</a>
    <a href="LogoutServlet" class="logout">🚪 Logout</a>
</div>

</body>
</html>
