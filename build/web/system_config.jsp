<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>System Configuration</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');

        body {
            margin: 0;
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #e0ecf8, #fdfdfd);
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        .container {
            width: 100%;
            max-width: 600px;
            background: white;
            padding: 40px 30px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
            animation: slideIn 0.4s ease;
        }

        @keyframes slideIn {
            from { transform: translateY(20px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        h2 {
            text-align: center;
            color: #2c3e50;
            font-size: 26px;
            margin-bottom: 30px;
        }

        label {
            font-weight: 600;
            display: block;
            margin-top: 16px;
            color: #34495e;
        }

        input[type="number"] {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            border: 1px solid #dcdcdc;
            border-radius: 10px;
            margin-top: 6px;
            box-sizing: border-box;
            transition: border 0.3s;
        }

        input[type="number"]:focus {
            border-color: #3498db;
            outline: none;
        }

        input[type="submit"] {
            margin-top: 30px;
            width: 100%;
            padding: 14px;
            font-size: 17px;
            font-weight: 700;
            color: white;
            background: linear-gradient(90deg, #00b4db, #0083b0);
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: background 0.3s ease, transform 0.2s;
        }

        input[type="submit"]:hover {
            background: linear-gradient(90deg, #0083b0, #00b4db);
            transform: translateY(-2px);
        }

        .message {
            text-align: center;
            padding: 12px 16px;
            font-weight: 600;
            border-radius: 10px;
            margin-bottom: 20px;
        }

        .success {
            background-color: #eafaf1;
            color: #27ae60;
            border: 1px solid #a9e6c7;
        }

        .error {
            background-color: #fef0f0;
            color: #e74c3c;
            border: 1px solid #f5b1b1;
        }

        .back-button {
            margin-top: 25px;
            text-align: center;
        }

        .back-button a {
            display: inline-block;
            padding: 12px 24px;
            font-weight: 600;
            font-size: 15px;
            text-decoration: none;
            color: white;
            background: linear-gradient(90deg, #6a11cb, #2575fc);
            border-radius: 10px;
            transition: background 0.3s ease;
        }

        .back-button a:hover {
            background: linear-gradient(90deg, #2575fc, #6a11cb);
        }
    </style>
</head>
<body>
<div class="container">
    <h2>⚙️ System Configuration</h2>

    <!-- Success message -->
    <c:if test="${not empty message}">
        <div class="message success">${message}</div>
    </c:if>

    <!-- Error message -->
    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>

    <form method="post" action="SystemConfigServlet">
        <input type="hidden" name="configId" value="${config.configId}" />

        <label for="fineRate">📉 Fine Rate Per Overdue Day (JD):</label>
        <input type="number" step="0.01" min="0" id="fineRate" name="fineRate" value="${config.fineRate}" required />

        <label for="maxBooksBorrowed">📚 Maximum Books Borrowed:</label>
        <input type="number" min="1" id="maxBooksBorrowed" name="maxBooksBorrowed" value="${config.maxBooksBorrowed}" required />

        <label for="maxBorrowPeriod">📆 Maximum Borrow Period (days):</label>
        <input type="number" min="1" id="maxBorrowPeriod" name="maxBorrowPeriod" value="${config.maxBorrowPeriod}" required />

        <input type="submit" value="💾 Save Changes" />
    </form>

    <div class="back-button">
        <a href="AdminDashboardServlet">← Back to Dashboard</a>
    </div>
</div>
</body>
</html>
