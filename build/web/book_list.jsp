<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="models.Book" %>
<%@ page import="models.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>📚 Library Book List</title>
    <link href="https://fonts.googleapis.com/css2?family=Raleway:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Raleway', sans-serif;
            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
            padding: 40px 20px;
            margin: 0;
            animation: fadeIn 1s ease-in;
        }

        @keyframes fadeIn {
            from {opacity: 0;}
            to {opacity: 1;}
        }

        .container {
            max-width: 1100px;
            margin: 30px auto;
            background: rgba(255, 255, 255, 0.9);
            backdrop-filter: blur(8px);
            border-radius: 20px;
            padding: 30px 20px;
            box-shadow: 0 12px 30px rgba(0,0,0,0.1);
            overflow-x: auto;
        }

        h2 {
            text-align: center;
            font-size: 32px;
            color: #2d3436;
            margin-bottom: 30px;
        }

        .btn-group {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
            margin-bottom: 25px;
        }

        .btn {
            background: linear-gradient(to right, #74ebd5, #acb6e5);
            color: #2d3436;
            padding: 10px 20px;
            border-radius: 25px;
            font-weight: bold;
            text-decoration: none;
            transition: transform 0.2s ease, box-shadow 0.3s ease;
        }

        .btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        form {
            text-align: center;
            margin-bottom: 15px;
        }

        input[type="text"], select {
            padding: 10px;
            border-radius: 10px;
            border: 1px solid #bbb;
            width: 220px;
            margin: 8px;
        }

        button[type="submit"] {
            background: #0984e3;
            color: white;
            border: none;
            padding: 10px 16px;
            border-radius: 10px;
            cursor: pointer;
            font-weight: bold;
        }

        button[type="submit"]:hover {
            background: #74b9ff;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 30px;
            background-color: white;
            border-radius: 15px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);
            table-layout: fixed;
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #eee;
            text-align: left;
            font-size: 15px;
            word-break: break-word;
        }

        th {
            background: linear-gradient(to right, #74ebd5, #acb6e5);
            color: #2d3436;
            font-weight: 600;
        }

        tr:hover {
            background-color: #f0f0f0;
        }

        .status-available { color: #00b894; font-weight: bold; }
        .status-borrowed { color: #e17055; font-weight: bold; }
        .status-reserved { color: #d63031; font-weight: bold; }

        .action-buttons {
            display: flex;
            gap: 10px;
            flex-wrap: wrap;
        }

        .btn-action {
            padding: 6px 14px;
            border: none;
            border-radius: 8px;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
            transition: 0.3s;
        }

        .btn-edit {
            background-color: #0984e3;
            color: white;
        }

        .btn-edit:hover {
            background-color: #74b9ff;
        }

        .btn-delete {
            background-color: #d63031;
            color: white;
        }

        .btn-delete:hover {
            background-color: #e17055;
        }

        .no-books {
            text-align: center;
            font-weight: bold;
            color: #636e72;
            padding: 40px 0;
            font-size: 18px;
        }

        @media (max-width: 768px) {
            input[type="text"], select {
                width: 90%;
            }
            .btn-group {
                flex-direction: column;
                gap: 12px;
            }
            th, td {
                font-size: 14px;
                padding: 10px 12px;
            }
            .container {
                padding: 20px 15px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>📚 Explore Our Library Collection</h2>

    <%
        User currentUser = (User) session.getAttribute("currentUser");
        String role = currentUser != null ? currentUser.getRole() : "";
        String backUrl = "login.jsp";
        if ("admin".equals(role)) backUrl = "AdminDashboardServlet";
        else if ("librarian".equals(role)) backUrl = "librarian_dashboard.jsp";
    %>

    <div class="btn-group">
        <a href="add_book.jsp" class="btn">➕ Add Book</a>
        <a href="BookServlet?action=borrowCountList" class="btn">📈 Borrow Stats</a>
        <a href="<%= backUrl %>" class="btn">🏠 Back to Dashboard</a>
        <a href="BookServlet?action=deleted" class="btn">🗑️ Deleted Books</a>
    </div>

    <!-- Search Form -->
    <form action="BookServlet" method="get">
        <input type="text" name="keyword" placeholder="🔍 Search by Title or ISBN" />
        <button type="submit" name="action" value="search">Search</button>
    </form>

    <!-- Filter Form -->
    <form action="BookServlet" method="get">
        <select name="availability">
            <option value="">🔃 All Status</option>
            <option value="available">✅ Available</option>
            <option value="borrowed">📚 Borrowed</option>
            <option value="reserved">🔒 Reserved</option>
        </select>
        <button type="submit" name="action" value="filter">Filter</button>
    </form>

    <!-- Sort Form -->
    <form action="BookServlet" method="get">
        <select name="sortOrder">
            <option value="desc">🔽 Newest</option>
            <option value="asc">🔼 Oldest</option>
        </select>
        <button type="submit" name="action" value="sort">Sort</button>
    </form>

    <a href="BookServlet?action=viewAll" class="btn" style="background: #dfe6e9; color: #2d3436;">🔁 Reset</a>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        Map<Integer, Integer> borrowCounts = (Map<Integer, Integer>) request.getAttribute("borrowCounts");

        if (books != null && !books.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th style="width: 5%;">ID</th>
                <th style="width: 18%;">📖 Title</th>
                <th style="width: 15%;">✍️ Author</th>
                <th style="width: 12%;">🔢 ISBN</th>
                <th style="width: 10%;">📚 Genre</th>
                <th style="width: 7%;">📅 Year</th>
                <th style="width: 8%;">📌 Status</th>
                <th style="width: 7%;">📈 Borrows</th>
                <th style="width: 18%;">📝 Description</th>
                <th style="width: 10%;">⚙️ Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Book book : books) {
                int count = borrowCounts != null && borrowCounts.containsKey(book.getBookId())
                        ? borrowCounts.get(book.getBookId()) : 0;
        %>
        <tr>
            <td><%= book.getBookId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getGenre() %></td>
            <td><%= book.getPublicationYear() %></td>
            <td class="status-<%= book.getAvailability().toLowerCase() %>"><%= book.getAvailability() %></td>
            <td><%= count %></td>
            <td><%= book.getDescription() %></td>
            <td>
                <div class="action-buttons">
                    <a href="BookServlet?action=edit&bookId=<%= book.getBookId() %>">
                        <button class="btn-action btn-edit" type="button">✏️ Edit</button>
                    </a>
                    <a href="BookServlet?action=delete&bookId=<%= book.getBookId() %>" onclick="return confirm('Are you sure you want to delete this book?');">
                        <button class="btn-action btn-delete" type="button">🗑️ Delete</button>
                    </a>
                </div>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p class="no-books">📭 No books found. Try again or add new books!</p>
    <% } %>
</div>
</body>
</html>
