<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patron Dashboard</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        :root {
            --primary: #4a90e2;
            --accent: #6fb1fc;
            --bg: #eaf1f8;
            --glass: rgba(255, 255, 255, 0.8);
            --shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Roboto', sans-serif;
            background: var(--bg);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .wrapper {
            padding: 40px 20px;
            max-width: 1000px;
            margin: 0 auto;
            text-align: center;
        }

        .flash-success {
            background-color: #d4edda;
            color: #155724;
            padding: 12px;
            border-radius: 8px;
            margin-bottom: 30px;
            border: 1px solid #c3e6cb;
            font-weight: bold;
        }

        .dashboard-banner {
            background: linear-gradient(135deg, var(--primary), var(--accent));
            color: white;
            padding: 60px 30px;
            border-radius: 20px;
            box-shadow: var(--shadow);
            animation: fadeIn 0.8s ease;
            margin-bottom: 40px;
        }

        .dashboard-banner h2 {
            font-size: 36px;
            margin-bottom: 15px;
        }

        .dashboard-banner p {
            font-size: 18px;
            opacity: 0.9;
        }

        .nav-center {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            justify-items: center;
            margin-top: 30px;
        }

        .nav-center a {
            text-decoration: none;
            color: #333;
            font-weight: 600;
            font-size: 18px;
            padding: 16px 24px;
            background: white;
            border-radius: 12px;
            box-shadow: var(--shadow);
            transition: all 0.3s ease-in-out;
            width: 100%;
            max-width: 260px;
            text-align: center;
        }

        .nav-center a:hover {
            background: var(--primary);
            color: white;
            transform: translateY(-3px);
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(10px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        @media (max-width: 600px) {
            .dashboard-banner h2 { font-size: 28px; }
            .dashboard-banner p { font-size: 16px; }
            .nav-center a { font-size: 16px; padding: 14px 20px; }
        }
    </style>
</head>
<body>

<div class="wrapper">

    <c:if test="${not empty sessionScope.successMessage}">
        <div class="flash-success">${sessionScope.successMessage}</div>
        <c:remove var="successMessage" scope="session"/>
    </c:if>

    <div class="dashboard-banner">
        <h2>Welcome to Your Digital Library</h2>
        <p>Discover new books, check your history, manage fines, and get notified – all in one place!</p>
    </div>

    <!-- ✅ Centered Navigation Buttons -->
    <div class="nav-center">
        <a href="<c:url value='/BookServlet?action=viewAvailable'/>">📚 Browse Books</a>
        <a href="<c:url value='/TransactionServlet?action=history'/>">📜 My History</a>
        <a href="<c:url value='/FineServlet?action=myFines'/>">💰 My Fines</a>
        <a href="<c:url value='/NotificationServlet?action=list'/>">🔔 Notifications</a>
        <a href="<c:url value='/LogoutServlet'/>">🚪 Logout</a>
    </div>

</div>

</body>
</html>
