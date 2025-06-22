<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(-45deg, #c2e9fb, #a1c4fd, #fbc2eb, #a6c1ee);
            background-size: 400% 400%;
            animation: gradientFlow 12s ease infinite;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 30px;
        }

        @keyframes gradientFlow {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .container {
            background: #ffffffcc;
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 600px;
            animation: slideIn 1s ease-out;
            position: relative;
        }

        @keyframes slideIn {
            from { opacity: 0; transform: translateY(30px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        .container::before {
            content: "";
            position: absolute;
            top: -60px;
            left: calc(50% - 40px);
            width: 80px;
            height: 80px;
            background: url('https://cdn-icons-png.flaticon.com/512/29/29302.png') no-repeat center;
            background-size: contain;
            animation: float 4s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            margin-bottom: 30px;
            color: #2c3e50;
            font-size: 28px;
        }

        label {
            display: block;
            font-weight: 600;
            margin-bottom: 6px;
            color: #333;
        }

        input, select, textarea {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border-radius: 12px;
            border: none;
            background: #f5f7fa;
            box-shadow: inset 3px 3px 6px #d1d9e6, inset -3px -3px 6px #ffffff;
            font-size: 15px;
            transition: all 0.3s ease;
        }

        input:focus, select:focus, textarea:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(39, 174, 96, 0.3);
            background: #ffffff;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        button {
            width: 100%;
            padding: 14px;
            background-color: #27ae60;
            color: white;
            font-weight: bold;
            font-size: 16px;
            border: none;
            border-radius: 12px;
            cursor: pointer;
            transition: background 0.3s ease;
            box-shadow: 0 5px 15px rgba(39,174,96,0.3);
        }

        button:hover {
            background-color: #219150;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            font-weight: bold;
            color: #2980b9;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }

        .message {
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
        }

        .success { color: #27ae60; }
        .error   { color: #c0392b; }

        @media (max-width: 600px) {
            .container {
                padding: 30px 20px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>📚 Add a New Book</h2>

    <form action="BookServlet" method="post">
        <input type="hidden" name="action" value="add">

        <label for="title">Title</label>
        <input type="text" name="title" id="title" required />

        <label for="author">Author</label>
        <input type="text" name="author" id="author" required />

        <label for="isbn">ISBN</label>
        <input type="text" name="isbn" id="isbn" required />

        <label for="genre">Genre</label>
        <input type="text" name="genre" id="genre" />

        <label for="publicationYear">Publication Year</label>
        <input type="number" name="publicationYear" id="publicationYear" />

        <label for="availability">Status</label>
        <select name="availability" id="availability" required>
            <option value="available">Available</option>
            <option value="borrowed">Borrowed</option>
            <option value="reserved">Reserved</option>
        </select>

        <label for="description">Description</label>
        <textarea name="description" id="description"></textarea>

        <button type="submit">Add Book</button>
    </form>

    <%-- Display message --%>
    <%
        String msg = (String) request.getAttribute("message");
        String stat = (String) request.getAttribute("status");
        if (msg != null) {
    %>
        <div class="message <%= "success".equals(stat) ? "success" : "error" %>"><%= msg %></div>
    <% } %>

    <a href="BookServlet?action=viewAll" class="back-link">← Back to Book List</a>
</div>

</body>
</html>
