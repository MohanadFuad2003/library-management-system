<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<%
    /* حماية الجلسة: إعادة التوجيه إن لم يكن هناك مستخدم */
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Fines</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body       { background-color:#f2f6fa; font-family:'Segoe UI',sans-serif; }
        h2         { color:#2980b9; font-weight:700; }
        .container { margin-block:60px; }
        table      { background:#fff; border-radius:10px; overflow:hidden; }
    </style>
</head>
<body>
<div class="container">

    <h2 class="text-center mb-4">💰 My Fines</h2>

    <!-- ✅ جدول الغرامات -->
    <c:if test="${not empty fines}">
        <table class="table table-striped table-bordered align-middle">
            <thead class="table-primary">
            <tr>
                <th>#</th>
                <th>Book Title</th>
                <th>Amount (JD)</th>
                <th>Status</th>
                <th>Paid Date</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="fine" items="${fines}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${fine.bookTitle}</td>
                    <td><fmt:formatNumber value="${fine.fineAmount}" minFractionDigits="2" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${fine.paymentStatus eq 'paid'}">
                                <span class="badge bg-success">Paid</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Unpaid</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${fine.paidDate != null}">
                                <fmt:formatDate value="${fine.paidDate}" pattern="yyyy-MM-dd" />
                            </c:when>
                            <c:otherwise>—</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- ❌ لا توجد غرامات -->
    <c:if test="${empty fines}">
        <p class="text-center text-muted mt-5">You don't have any fines.</p>
    </c:if>

    <!-- 🔙 زر العودة -->
    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/patron_dashboard.jsp" class="btn btn-secondary">
            ⬅ Back to Dashboard
        </a>
    </div>

</div>
</body>
</html>
