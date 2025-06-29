<%@page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    if (session.getAttribute("currentUser") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Books</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .book-card{border-radius:15px;box-shadow:0 4px 8px rgba(0,0,0,.08);transition:transform .2s}
        .book-card:hover{transform:scale(1.02)}
        .search-box{max-width:500px;margin:0 auto 30px}
    </style>
</head>
<body>
<div class="container my-5">

    <!-- نموذج البحث -->
    <form class="search-box input-group mb-4" action="<c:url value='/BookServlet'/>" method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="keyword" class="form-control" placeholder="Search by title or author" value="${param.keyword}">
        <button class="btn btn-primary" type="submit">Search</button>
    </form>

    <h2 class="text-center mb-4">📚 Available Books</h2>
    <div class="row">
        <c:forEach var="book" items="${availableBooks}">
            <div class="col-md-4 mb-4">
                <div class="card book-card p-3 h-100">
                    <h5>${book.title}</h5>
                    <p class="mb-1"><strong>Author:</strong> ${book.author}</p>
                    <p class="mb-1"><strong>Year:</strong> ${book.publicationYear}</p>
                    <p class="mb-3"><strong>Status:</strong> ${book.availability}</p>
                    <a href="<c:url value='/BookServlet'/>?action=confirmBorrow&amp;bookId=${book.bookId}"
                       class="btn btn-success w-100">Borrow</a>
                </div>
            </div>
        </c:forEach>

        <!-- معالجة حالة لا توجد نتائج -->
        <c:if test="${empty availableBooks}">
            <div class="col-12">
                <div class="alert alert-warning text-center">No books found.</div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
