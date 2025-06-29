<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Borrowed Transactions</title>
    <style>
        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #3498db;
            color: white;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">📚 Currently Borrowed Transactions</h2>

    <c:if test="${empty transactions}">
        <p style="text-align: center; color: gray;">No borrowed books found.</p>
    </c:if>

    <c:if test="${not empty transactions}">
   <table>
    <thead>
        <tr>
            <th>Transaction ID</th>
            <th>Book ID</th>
            <th>User ID</th>
            <th>Borrow Date</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Action</th> <!-- New column -->
        </tr>
    </thead>
    <tbody>
        <c:forEach var="tx" items="${transactions}">
            <tr>
                <td>${tx.transactionId}</td>
                <td>${tx.bookId}</td>
                <td>${tx.userId}</td>
                <td>${tx.borrowDate}</td>
                <td>${tx.dueDate}</td>
                <td>${tx.status}</td>
                <td>
                    <form action="return_book.jsp" method="get">
                        <input type="hidden" name="transactionId" value="${tx.transactionId}" />
                        <button type="submit">Return</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

    </c:if>
</body>
</html>
