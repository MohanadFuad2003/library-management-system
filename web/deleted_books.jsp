<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Book" %>
<!DOCTYPE html>
<html>
<head>
    <title>🗃️ Deleted Books Archive</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #fceabb, #f8b500);
            margin: 0; padding: 40px 20px;
            min-height: 100vh;
            animation: fadeIn 1s ease forwards;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(20px);}
            to {opacity: 1; transform: translateY(0);}
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background: rgba(255,255,255,0.95);
            border-radius: 25px;
            padding: 35px 45px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.12);
            backdrop-filter: blur(12px);
        }

        h2 {
            text-align: center;
            font-weight: 700;
            font-size: 2.6rem;
            color: #d35400;
            margin-bottom: 40px;
            letter-spacing: 1.5px;
            text-shadow: 0 2px 6px rgba(211, 84, 0, 0.3);
        }

        .book-card {
            display: flex;
            flex-direction: column;
            background: #fff8e1;
            border-left: 6px solid #f39c12;
            margin-bottom: 20px;
            padding: 18px 24px;
            border-radius: 20px;
            box-shadow: 0 8px 15px rgba(243, 156, 18, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .book-card:hover {
            transform: scale(1.02);
            box-shadow: 0 15px 25px rgba(243, 156, 18, 0.4);
        }

        .book-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 12px;
        }

        .book-title {
            font-size: 1.4rem;
            font-weight: 700;
            color: #e67e22;
        }

        .book-author {
            font-style: italic;
            font-size: 1rem;
            color: #8e44ad;
        }

        .book-details {
            font-size: 0.95rem;
            color: #2d3436;
            margin-bottom: 15px;
            line-height: 1.4;
        }

        .book-description {
            font-size: 0.9rem;
            color: #636e72;
            margin-bottom: 18px;
            font-style: italic;
        }

        .actions {
            display: flex;
            gap: 24px;
        }

        .actions a {
            background: #27ae60;
            color: white;
            padding: 10px 22px;
            border-radius: 40px;
            text-decoration: none;
            font-weight: 600;
            box-shadow: 0 6px 15px rgba(39, 174, 96, 0.3);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
            display: inline-flex;
            align-items: center;
            gap: 8px;
            font-size: 0.95rem;
        }

        .actions a.delete-perm {
            background: #e74c3c;
            box-shadow: 0 6px 15px rgba(231, 76, 60, 0.3);
        }

        .actions a:hover {
            filter: brightness(1.1);
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
        }

        .btn-back {
            display: block;
            max-width: 180px;
            margin: 40px auto 0 auto;
            padding: 14px 0;
            text-align: center;
            background: #34495e;
            color: white;
            font-weight: 700;
            border-radius: 30px;
            text-decoration: none;
            box-shadow: 0 8px 20px rgba(52, 73, 94, 0.4);
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }

        .btn-back:hover {
            background-color: #2c3e50;
            box-shadow: 0 12px 35px rgba(44, 62, 80, 0.6);
        }

        .no-books {
            text-align: center;
            font-weight: 700;
            color: #b2bec3;
            font-size: 1.3rem;
            margin-top: 60px;
            font-style: italic;
            letter-spacing: 0.04em;
        }

        /* Responsive */
        @media (max-width: 700px) {
            .book-card {
                padding: 15px 18px;
            }
            .book-header {
                flex-direction: column;
                align-items: flex-start;
                gap: 6px;
            }
            .actions {
                flex-direction: column;
                gap: 12px;
            }
            .actions a {
                justify-content: center;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>🗃️ Deleted Books Archive</h2>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
            for (Book book : books) {
    %>
    <div class="book-card" title="Book ID: <%= book.getBookId() %>">
        <div class="book-header">
            <div class="book-title"><%= book.getTitle() %></div>
            <div class="book-author">by <%= book.getAuthor() %></div>
        </div>
        <div class="book-details">
            <strong>ISBN:</strong> <%= book.getIsbn() %> &nbsp;&bull;&nbsp;
            <strong>Genre:</strong> <%= book.getGenre() %> &nbsp;&bull;&nbsp;
            <strong>Year:</strong> <%= book.getPublicationYear() %>
        </div>
        <div class="book-description"><%= book.getDescription() != null && !book.getDescription().trim().isEmpty() 
            ? book.getDescription() 
            : "No description available." %></div>

        <div class="actions">
            <a href="BookServlet?action=restore&bookId=<%= book.getBookId() %>" 
               onclick="return confirm('Are you sure you want to restore this book?');" 
               title="Restore this book">
               ♻️ Restore
            </a>
            <a href="BookServlet?action=deletePermanent&bookId=<%= book.getBookId() %>" 
               class="delete-perm" 
               onclick="return confirm('Are you sure you want to permanently delete this book? This action cannot be undone.');" 
               title="Permanently delete this book">
               🗑️ Delete Permanently
            </a>
        </div>
    </div>

    <%      }
        } else { %>
        <p class="no-books">No deleted books found.</p>
    <% } %>

    <a href="BookServlet?action=viewAll" class="btn-back">← Back to Book List</a>
</div>

</body>
</html>
