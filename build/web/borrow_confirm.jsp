<%@page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>Confirm Borrow</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        :root{--primary:#2980b9;--danger:#e74c3c;--shadow:0 15px 35px rgba(0,0,0,.1);--r:20px}
        body{margin:0;font-family:'Roboto',sans-serif;background:linear-gradient(135deg,#f5f7fa,#c3cfe2);display:flex;justify-content:center;align-items:center;min-height:100vh}
        .card{background:#fff;padding:40px 60px;border-radius:var(--r);box-shadow:var(--shadow);max-width:480px;width:90%;text-align:center}
        .btn{display:inline-block;padding:10px 22px;border-radius:8px;text-decoration:none;font-weight:700;margin:12px 8px}
        .btn.primary{background:var(--primary);color:#fff}
        .btn.cancel{background:#ccc;color:#333}
    </style>
</head>
<body>
<div class="card">
    <h2>Confirm Borrow</h2>
    <p><strong>Title:</strong> ${book.title}</p>
    <p><strong>Author:</strong> ${book.author}</p>
    <p><strong>Year:</strong> ${book.publicationYear}</p>
    <p><strong>Status:</strong> ${book.availability}</p>

    <c:choose>
        <c:when test="${book.availability eq 'available'}">
            <!-- ✅ استدعاء TransactionServlet بـ POST -->
            <form action="<c:url value='/TransactionServlet'/>" method="post" style="display:inline;">
                <input type="hidden" name="action" value="borrow"/>
                <input type="hidden" name="bookId" value="${book.bookId}"/>
                <button class="btn primary" type="submit">Borrow</button>
            </form>
        </c:when>
        <c:otherwise>
            <p style="color:var(--danger);font-weight:700">❌ Sorry, this book is not available.</p>
        </c:otherwise>
    </c:choose>

    <a class="btn cancel" href="<c:url value='/BookServlet?action=viewAvailable'/>">Cancel</a>
</div>
</body>
</html>
