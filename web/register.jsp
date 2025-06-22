<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Account - Library System</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');

        body {
            margin: 0;
            font-family: 'Inter', sans-serif;
            background: linear-gradient(135deg, #f8f4e5, #f0f0f0);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: #ffffff;
            max-width: 520px;
            width: 100%;
            padding: 40px 32px;
            border-radius: 16px;
            box-shadow: 0 12px 28px rgba(0, 0, 0, 0.1);
            animation: fadeIn 0.5s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        h2 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 30px;
            color: #3e3e3e;
        }

        h2::before {
            content: "📚 ";
        }

        label {
            font-weight: 600;
            display: block;
            margin-top: 14px;
            margin-bottom: 6px;
            color: #4a4a4a;
        }

        input, select {
            width: 100%;
            padding: 12px;
            font-size: 15px;
            border-radius: 8px;
            border: 1px solid #ccc;
            background: #fcfcfc;
            box-sizing: border-box;
            transition: border 0.3s, box-shadow 0.3s;
        }

        input:focus, select:focus {
            border-color: #2f7b5b;
            box-shadow: 0 0 6px rgba(47, 123, 91, 0.3);
            outline: none;
        }

        button {
            margin-top: 28px;
            width: 100%;
            padding: 14px;
            background: linear-gradient(90deg, #4e944f, #1d5c42);
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            transition: background 0.3s, transform 0.2s;
        }

        button:hover {
            background: linear-gradient(90deg, #1d5c42, #4e944f);
            transform: translateY(-2px);
        }

        .message {
            margin-top: 20px;
            text-align: center;
            padding: 10px 16px;
            border-radius: 10px;
            font-weight: 600;
        }

        .success {
            background-color: #e9f9ef;
            border: 1px solid #a2e4bd;
            color: #27ae60;
        }

        .error {
            background-color: #fef0f0;
            border: 1px solid #f5b1af;
            color: #e74c3c;
        }

        .login-link {
            margin-top: 25px;
            text-align: center;
            font-size: 14px;
        }

        .login-link a {
            color: #2f7b5b;
            font-weight: 600;
            text-decoration: none;
        }

        .login-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Join the Library Universe</h2>

    <form action="RegisterServlet" method="post">
        <label>👤 Username</label>
        <input type="text" name="username" required />

        <label>🔒 Password</label>
        <input type="password" name="password" required />

        <label>🎓 Role</label>
        <select name="role" required>
            <option value="" disabled selected>Select your role</option>
            <option value="librarian">📘 Librarian</option>
            <option value="patron">📖 Patron</option>
        </select>

        <label>🧑 First Name</label>
        <input type="text" name="firstName" required />

        <label>👨‍👩‍👧‍👦 Last Name</label>
        <input type="text" name="lastName" required />

        <label>📧 Email</label>
        <input type="email" name="email" required />

        <label>📞 Phone Number</label>
        <input type="tel" name="phoneNumber" required />

        <label>🎂 Date of Birth</label>
        <input type="date" name="dob" required />

        <button type="submit">📚 Register Account</button>
    </form>

    <%
        String message = (String) request.getAttribute("message");
        String status  = (String) request.getAttribute("status");
        if (message != null) {
    %>
    <div class="message <%= "success".equals(status) ? "success" : "error" %>">
        <%= message %>
    </div>
    <% } %>

    <div class="login-link">
        Already have an account? <a href="login.jsp">Login here</a>
    </div>
</div>

</body>
</html>
