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
    <title>Return Book</title>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            background: linear-gradient(135deg, #a1c4fd, #c2e9fb);
            font-family: 'Montserrat', sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 60px 20px;
        }

        h1 {
            font-size: 2.5rem;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        .form-container {
            background: white;
            padding: 40px 30px;
            border-radius: 16px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            max-width: 600px;
            width: 100%;
        }

        label {
            display: block;
            margin: 18px 0 6px;
            font-weight: 600;
            color: #34495e;
        }

        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 12px 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 1rem;
            outline: none;
        }

        button {
            margin-top: 30px;
            padding: 14px 30px;
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            color: white;
            font-size: 1.1rem;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: 600;
            transition: all 0.3s ease;
            width: 100%;
        }

        button:hover {
            background: linear-gradient(135deg, #27ae60, #1e8449);
            transform: scale(1.03);
        }

        .message {
            margin-top: 20px;
            padding: 12px 20px;
            border-radius: 10px;
            font-weight: bold;
            text-align: center;
        }

        .message.success {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .message.error {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
    </style>
</head>
<body>

<h1>📘 Return a Borrowed Book</h1>

<div class="form-container">

    <form method="post" action="TransactionServlet">
        <input type="hidden" name="action" value="return" />

        <label for="transactionId">Transaction ID</label>
        <input type="text" id="transactionId" name="transactionId" required placeholder="e.g., BT123456" />

        <label for="returnDate">Return Date</label>
        <input type="date" id="returnDate" name="returnDate" required value="<%= java.time.LocalDate.now() %>" />

        <button type="submit">📦 Process Return</button>
    </form>

    <c:if test="${not empty success}">
        <div class="message success">${success}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>

</div>

</body>
</html>

