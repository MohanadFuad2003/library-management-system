<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Library Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Poppins', sans-serif;
            height: 100vh;
            background: linear-gradient(-45deg, #fceabb, #f8b500, #fceabb, #f8b500);
            background-size: 400% 400%;
            animation: gradientBG 15s ease infinite;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        @keyframes gradientBG {
            0% { background-position: 0% 50%; }
            50% { background-position: 100% 50%; }
            100% { background-position: 0% 50%; }
        }

        .login-wrapper {
            background: #fff;
            padding: 40px 30px;
            border-radius: 20px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            max-width: 430px;
            width: 100%;
            animation: fadeIn 1.2s ease-out;
            position: relative;
            z-index: 10;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(30px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .floating-image {
            position: absolute;
            top: -110px;
            left: calc(50% - 75px);
            width: 150px;
            height: 150px;
            background-image: url('https://cdn-icons-png.flaticon.com/512/29/29302.png');
            background-size: cover;
            background-repeat: no-repeat;
            animation: float 4s ease-in-out infinite;
            z-index: 1;
        }

        @keyframes float {
            0%, 100% { transform: translateY(0); }
            50% { transform: translateY(-15px); }
        }

        h2 {
            text-align: center;
            color: #333;
            margin-top: 60px;
            margin-bottom: 30px;
            font-size: 28px;
            font-weight: 600;
        }

        label {
            font-weight: 600;
            display: block;
            margin-top: 15px;
            margin-bottom: 5px;
            color: #555;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 16px;
            border: none;
            border-radius: 10px;
            background: #f5f5f5;
            transition: all 0.3s ease;
            font-size: 15px;
        }

        input:focus {
            outline: none;
            background: #fff;
            box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.3);
        }

        button {
            width: 100%;
            padding: 12px;
            background: #4CAF50;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            margin-top: 25px;
            position: relative;
            overflow: hidden;
        }

        button::after {
            content: "";
            position: absolute;
            top: 0;
            left: 50%;
            width: 300%;
            height: 100%;
            background: rgba(255, 255, 255, 0.15);
            transform: translateX(-50%) scaleX(0);
            transform-origin: center;
            transition: transform 0.4s ease-out;
        }

        button:hover::after {
            transform: translateX(-50%) scaleX(1);
        }

        .error {
            color: #d32f2f;
            text-align: center;
            font-weight: bold;
            margin-top: 15px;
        }

        .register-link {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }

        .register-link a {
            color: #4CAF50;
            text-decoration: none;
            font-weight: 600;
        }

        .register-link a:hover {
            text-decoration: underline;
        }

        @media (max-width: 500px) {
            .floating-image {
                width: 100px;
                height: 100px;
                top: -80px;
                left: calc(50% - 50px);
            }

            .login-wrapper {
                padding: 30px 20px;
            }

            h2 {
                margin-top: 40px;
                font-size: 24px;
            }
        }
    </style>
</head>
<body>

<div class="floating-image"></div>

<div class="login-wrapper">
    <h2>Welcome Back 📖</h2>

    <form action="LoginServlet" method="post">
        <label for="username">Username</label>
        <input type="text" name="username" id="username" required>

        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>

        <button type="submit">Login</button>
    </form>

    <% String error = (String) request.getAttribute("error");
       if (error != null) {
    %>
    <div class="error"><%= error %></div>
    <% } %>

    <div class="register-link">
        Don't have an account? <a href="register.jsp">Register here</a>
    </div>
</div>

</body>
</html>
