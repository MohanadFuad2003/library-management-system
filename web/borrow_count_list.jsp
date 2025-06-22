<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="models.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>📊 Borrow Count for Books</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #a8edea, #fed6e3);
            margin: 0; 
            padding: 40px 20px;
            min-height: 100vh;
            animation: fadeIn 1.2s ease forwards;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px);}
            to { opacity: 1; transform: translateY(0);}
        }

        .container {
            max-width: 900px;
            margin: auto;
            background: rgba(255,255,255,0.9);
            padding: 35px 40px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.12);
            backdrop-filter: blur(10px);
        }

        h2 {
            text-align: center;
            font-weight: 600;
            font-size: 2.4rem;
            color: #34495e;
            margin-bottom: 30px;
            letter-spacing: 1px;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 12px;
        }

        th, td {
            padding: 15px 20px;
            text-align: left;
            font-size: 1rem;
        }

        th {
            background: #74b9ff;
            color: white;
            font-weight: 600;
            border-radius: 12px 12px 0 0;
            letter-spacing: 0.05em;
        }

        td {
            background: #dfe6e9;
            color: #2d3436;
            border-radius: 12px;
            transition: background-color 0.3s ease;
        }

        tr:hover td {
            background-color: #81ecec;
            color: #0984e3;
            font-weight: 600;
            cursor: default;
        }

        .no-data {
            text-align: center;
            font-weight: 600;
            color: #636e72;
            margin: 40px 0;
            font-size: 1.2rem;
        }

        .btn-back {
            display: block;
            width: fit-content;
            margin: 30px auto 0 auto;
            padding: 12px 28px;
            background: #0984e3;
            color: white;
            text-decoration: none;
            font-weight: 600;
            font-size: 1rem;
            border-radius: 30px;
            box-shadow: 0 6px 15px rgba(9, 132, 227, 0.5);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .btn-back:hover {
            background-color: #74b9ff;
            box-shadow: 0 8px 25px rgba(116, 185, 255, 0.7);
        }

        /* Responsive */
        @media (max-width: 600px) {
            th, td {
                padding: 12px 10px;
                font-size: 0.9rem;
            }
            h2 {
                font-size: 2rem;
            }
            .btn-back {
                width: 100%;
                text-align: center;
                padding: 14px 0;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>📊 Number of Times Each Book Was Borrowed</h2>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        Map<Integer, Integer> borrowCounts = (Map<Integer, Integer>) request.getAttribute("borrowCounts");

        if (books != null && !books.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>Book ID</th>
                <th>📖 Title</th>
                <th>✍️ Author</th>
                <th>📈 Borrows</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Book book : books) {
                int count = borrowCounts != null && borrowCounts.containsKey(book.getBookId())
                            ? borrowCounts.get(book.getBookId())
                            : 0;
        %>
            <tr>
                <td><%= book.getBookId() %></td>
                <td><%= book.getTitle() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= count %></td>
            </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
        <p class="no-data">No borrowing data available.</p>
    <% } %>

    <a href="BookServlet?action=viewAll" class="btn-back">← Back to Book List</a>
</div>
</body>
</html>
