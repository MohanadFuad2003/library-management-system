<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>Borrowing History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-5">
    <h2 class="text-center mb-4">📜 My Borrowing History</h2>

    <table class="table table-striped table-bordered align-middle">
        <thead class="table-primary">
        <tr>
            <th>#</th>
            <th>Transaction ID</th>
            <th>Title</th>
            <th>Borrow Date</th>
            <th>Due Date</th>
            <th>Return Date</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty history}">
                <c:forEach var="tx" items="${history}" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${tx.transactionId}</td>
                        <td>${tx.bookTitle}</td>
                        <td>
                            <c:if test="${tx.borrowDate != null}">
                                <fmt:formatDate value="${tx.borrowDate}" pattern="yyyy-MM-dd"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${tx.dueDate != null}">
                                <fmt:formatDate value="${tx.dueDate}" pattern="yyyy-MM-dd"/>
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${tx.returnDate != null}">
                                    <fmt:formatDate value="${tx.returnDate}" pattern="yyyy-MM-dd"/>
                                </c:when>
                                <c:otherwise>—</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${tx.status}</td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7" class="text-center text-muted">You haven't borrowed any books yet.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>

    <div class="text-center mt-4">
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/BookServlet?action=viewAvailable">🔙 Back to Books</a>
    </div>
</div>
</body>
</html>
