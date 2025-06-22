<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.LibraryStatistics" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>📊 Library Reports & Statistics</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap');

        body {
            font-family: 'Inter', sans-serif;
            margin: 0;
            background: linear-gradient(120deg, #f6f8fc, #eaf0f8);
            color: #2c3e50;
        }

        .container {
            max-width: 960px;
            margin: 60px auto;
            padding: 40px 30px;
            background: white;
            border-radius: 18px;
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 35px;
            color: #34495e;
        }

        .back-btn {
            display: inline-block;
            margin-bottom: 25px;
            padding: 10px 20px;
            background: linear-gradient(90deg, #2980b9, #3498db);
            color: #fff;
            text-decoration: none;
            font-weight: 600;
            border-radius: 10px;
            transition: background 0.3s ease;
        }

        .back-btn:hover {
            background: linear-gradient(90deg, #21618c, #2980b9);
        }

        .stats-list {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
        }

        .stat-box {
            background-color: #f4f6fb;
            border-left: 6px solid #2980b9;
            border-radius: 14px;
            padding: 25px 20px;
            text-align: left;
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.05);
            transition: transform 0.2s;
        }

        .stat-box:hover {
            transform: scale(1.02);
        }

        .stat-title {
            font-weight: 600;
            font-size: 16px;
            margin-bottom: 8px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .stat-value {
            font-size: 2.2rem;
            font-weight: 800;
            color: #2c3e50;
        }

        .icon {
            font-size: 20px;
        }

        .green-border { border-left-color: #27ae60; }
        .red-border   { border-left-color: #c0392b; }

        .error {
            color: #c0392b;
            font-weight: 600;
            text-align: center;
            margin: 40px 0;
        }
    </style>
</head>
<body>

<div class="container">
    <%
        User currentUser = (User) session.getAttribute("currentUser");
        String role = currentUser != null ? currentUser.getRole() : "";
        String backUrl = "login.jsp";
        if ("admin".equals(role)) {
            backUrl = "AdminDashboardServlet";
        } else if ("librarian".equals(role)) {
            backUrl = "librarian_dashboard.jsp";
        }
    %>
    <a href="<%= backUrl %>" class="back-btn">← Back to Dashboard</a>
    <h2>📊 Library Reports & Statistics</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="error"><%= error %></div>
    <%
        } else {
            LibraryStatistics stats = (LibraryStatistics) request.getAttribute("stats");
            if (stats != null) {
    %>

    <div class="stats-list">
        <div class="stat-box">
            <div class="stat-title"><span class="icon">📚</span> Total Books</div>
            <div class="stat-value"><%= stats.getTotalBooks() %></div>
        </div>

        <div class="stat-box">
            <div class="stat-title"><span class="icon">🧍‍♂️</span> Active Borrowers</div>
            <div class="stat-value"><%= stats.getActiveBorrowers() %></div>
        </div>

        <div class="stat-box">
            <div class="stat-title"><span class="icon">📘</span> Reserved Books</div>
            <div class="stat-value"><%= stats.getReservedBooks() %></div>
        </div>

        <div class="stat-box green-border">
            <div class="stat-title"><span class="icon">💰</span> Total Fines Collected (JOD)</div>
            <div class="stat-value"><%= String.format("%.2f", stats.getTotalFinesCollected()) %></div>
        </div>

        <div class="stat-box red-border">
            <div class="stat-title"><span class="icon">⚠️</span> Outstanding Fines (JOD)</div>
            <div class="stat-value"><%= String.format("%.2f", stats.getOutstandingFines()) %></div>
        </div>
    </div>

    <%
            } else {
    %>
        <div class="error">📉 No statistics available at the moment.</div>
    <%
            }
        }
    %>
</div>

</body>
</html>
