 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Patron Home</title>
<style>
  @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
  body {
    margin: 0;
    font-family: 'Roboto', sans-serif;
    background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    color: #34495e;
  }
  .container {
    background: white;
    padding: 40px 60px;
    border-radius: 20px;
    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1);
    text-align: center;
    max-width: 480px;
    width: 90%;
  }
  h2 {
    font-size: 2.5rem;
    margin-bottom: 20px;
    user-select: none;
  }
  p {
    font-size: 1.2rem;
    font-weight: 500;
    color: #7f8c8d;
  }
  strong {
    color: #2980b9;
    font-weight: 700;
  }
</style>
</head>
<body>
  <div class="container" role="main" aria-label="Patron Home Page">
    <h2>📖 Welcome, Patron</h2>
    <p>You are now logged in as a <strong>Patron</strong>.</p>
  </div>
</body>
</html>
