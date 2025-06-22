<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Transaction" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Fine</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(45deg, #fbc2eb, #a6c1ee, #fbc2eb, #a6c1ee);
            background-size: 400% 400%;
            animation: animatedBackground 12s ease infinite;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 20px;
        }

        @keyframes animatedBackground {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .container {
            background: #ffffffcc;
            backdrop-filter: blur(10px);
            padding: 40px 30px;
            border-radius: 20px;
            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
            max-width: 500px;
            width: 100%;
            position: relative;
            animation: slideIn 1s ease-out;
        }

        @keyframes slideIn {
            from { opacity: 0; transform: translateY(20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .container::before {
            content: "";
            position: absolute;
            top: -60px;
            left: calc(50% - 35px);
            width: 70px;
            height: 70px;
            background: url('https://cdn-icons-png.flaticon.com/512/3248/3248676.png') no-repeat center;
            background-size: cover;
            animation: float 4s ease-in-out infinite;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-10px); }
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            margin-bottom: 20px;
            font-size: 26px;
            color: #2c3e50;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: 600;
            margin-bottom: 6px;
            color: #444;
        }

        input {
            padding: 12px 15px;
            border: none;
            border-radius: 12px;
            background: #f5f7fa;
            box-shadow: inset 3px 3px 6px #d1d9e6, inset -3px -3px 6px #ffffff;
            margin-bottom: 20px;
            font-size: 15px;
            transition: all 0.3s ease;
        }

        input:focus {
            outline: none;
            background: #fff;
            box-shadow: 0 0 0 3px rgba(41, 128, 185, 0.3);
        }

        .btn-submit {
            padding: 14px;
            background: #2980b9;
            color: #fff;
            border: none;
            border-radius: 12px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s ease, transform 0.2s ease;
        }

        .btn-submit:hover {
            background: #216a98;
            transform: scale(1.03);
        }

        .btn-back {
            text-align: center;
            margin-top: 20px;
        }

        .btn-back a {
            text-decoration: none;
            font-weight: bold;
            color: #2980b9;
        }

        .btn-back a:hover {
            text-decoration: underline;
        }

        .message-error {
            text-align: center;
            margin-top: 15px;
            color: #c0392b;
            font-weight: bold;
        }

        @media (max-width: 600px) {
            .container {
                padding: 30px 20px;
            }

            .container::before {
                top: -50px;
                width: 60px;
                height: 60px;
                left: calc(50% - 30px);
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>💰 Add New Fine</h2>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="message-error"><%= error %></div>
    <% } %>

    <form action="FineServlet" method="post">
        <input type="hidden" name="action" value="add" />

        <label for="transactionId">Transaction ID:</label>
        <input type="text" name="transactionId" id="transactionId" required placeholder="e.g. BT123456" />

        <label for="fineAmount">Fine Amount (JOD):</label>
        <input type="number" name="fineAmount" id="fineAmount" step="0.01" min="0" required />

        <button type="submit" class="btn-submit">Add Fine</button>
    </form>

    <div class="btn-back">
        <a href="FineServlet?action=viewAll">← Back to Fines List</a>
    </div>
</div>
</body>
</html>
