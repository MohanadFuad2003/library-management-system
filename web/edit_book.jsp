<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Book" %>
<%
    Book book = (Book) request.getAttribute("book");
    if (book == null) { response.sendRedirect("BookServlet?action=viewAll"); return; }
%>
<!DOCTYPE html>
<html>
<head>
    <title>✏️ Edit Book</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet" />
    <style>
        /* Reset & Base */
        * {
            box-sizing: border-box;
        }
        body {
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);
            margin: 0;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
            min-height: 100vh;
            align-items: center;
        }

        /* Container */
        .container {
            background: #fff;
            border-radius: 20px;
            padding: 40px 50px;
            max-width: 480px;
            width: 100%;
            box-shadow:
                8px 8px 15px rgba(0, 0, 0, 0.1),
                -8px -8px 15px rgba(255, 255, 255, 0.7);
            transition: box-shadow 0.3s ease;
        }
        .container:hover {
            box-shadow:
                12px 12px 25px rgba(0, 0, 0, 0.15),
                -12px -12px 25px rgba(255, 255, 255, 0.9);
        }

        /* Heading */
        h2 {
            text-align: center;
            color: #7b4397;
            font-weight: 700;
            font-size: 2.2rem;
            margin-bottom: 35px;
            letter-spacing: 2px;
            text-shadow: 1px 1px 3px rgba(123, 67, 151, 0.3);
        }

        /* Form Elements */
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: #4a4a4a;
            letter-spacing: 0.03em;
        }
        input[type="text"],
        input[type="number"],
        select,
        textarea {
            width: 100%;
            padding: 14px 18px;
            border-radius: 14px;
            border: none;
            background: #f0f0f3;
            box-shadow:
                inset 6px 6px 8px #d1d9e6,
                inset -6px -6px 8px #ffffff;
            font-size: 1rem;
            color: #555;
            transition: box-shadow 0.3s ease;
            resize: vertical;
            min-height: 48px;
        }
        textarea {
            min-height: 100px;
        }
        input[type="text"]:focus,
        input[type="number"]:focus,
        select:focus,
        textarea:focus {
            outline: none;
            box-shadow:
                inset 3px 3px 6px #b6bfcf,
                inset -3px -3px 6px #ffffff,
                0 0 8px #7b4397;
            color: #3a3a3a;
        }

        /* Button */
        button {
            width: 100%;
            margin-top: 30px;
            padding: 16px 0;
            background: #7b4397;
            color: white;
            font-weight: 700;
            font-size: 1.1rem;
            border: none;
            border-radius: 20px;
            cursor: pointer;
            box-shadow: 3px 3px 8px #5a2d71, -3px -3px 8px #9659b6;
            transition: background-color 0.3s ease, box-shadow 0.3s ease;
        }
        button:hover {
            background: #6a3782;
            box-shadow: 5px 5px 15px #4b245d, -5px -5px 15px #a073ce;
        }

        /* Back Link */
        .back-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            font-weight: 600;
            color: #7b4397;
            text-decoration: none;
            letter-spacing: 0.05em;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #5a2d71;
            text-decoration: underline;
        }

        /* Message */
        .message {
            margin-top: 20px;
            text-align: center;
            font-weight: 700;
            font-size: 1rem;
            letter-spacing: 0.04em;
        }
        .success {
            color: #27ae60;
            text-shadow: 0 0 6px #27ae60;
        }
        .error {
            color: #e74c3c;
            text-shadow: 0 0 6px #e74c3c;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>✏️ Edit Book</h2>

    <form action="BookServlet" method="post" autocomplete="off">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="bookId" value="<%= book.getBookId() %>" />

        <label for="title">Title</label>
        <input type="text" id="title" name="title" value="<%= book.getTitle() %>" required />

        <label for="author">Author</label>
        <input type="text" id="author" name="author" value="<%= book.getAuthor() %>" required />

        <label for="isbn">ISBN</label>
        <input type="text" id="isbn" name="isbn" value="<%= book.getIsbn() %>" required />

        <label for="genre">Genre</label>
        <input type="text" id="genre" name="genre" value="<%= book.getGenre() %>" />

        <label for="publicationYear">Publication Year</label>
        <input type="number" id="publicationYear" name="publicationYear" value="<%= book.getPublicationYear() %>" min="1000" max="9999" />

        <label for="availability">Status</label>
        <select id="availability" name="availability" required>
            <option value="available" <%= "available".equals(book.getAvailability()) ? "selected" : "" %>>Available</option>
            <option value="borrowed"  <%= "borrowed".equals(book.getAvailability()) ? "selected" : "" %>>Borrowed</option>
            <option value="reserved"  <%= "reserved".equals(book.getAvailability()) ? "selected" : "" %>>Reserved</option>
        </select>

        <label for="description">Description</label>
        <textarea id="description" name="description"><%= book.getDescription() %></textarea>

        <button type="submit">Update Book</button>
    </form>

    <%-- Messages --%>
    <%
        String msg  = (String) request.getAttribute("message");
        String stat = (String) request.getAttribute("status");
        if (msg != null) {
    %>
        <div class="message <%= "success".equals(stat) ? "success" : "error" %>"><%= msg %></div>
    <% } %>

    <a href="BookServlet?action=viewAll" class="back-link">← Back to Book List</a>
</div>

</body>
</html>

