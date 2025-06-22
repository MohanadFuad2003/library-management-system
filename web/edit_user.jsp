<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User" %>
<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("UserServlet?action=viewAll");
        return;
    }

    User currentUser = (User) session.getAttribute("currentUser");
    String currentRole = currentUser != null ? currentUser.getRole() : "";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>🧑‍💼 Edit User</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
<style>
  /* Base reset */
  * {
    box-sizing: border-box;
  }
  body {
    margin: 0; padding: 40px 20px;
    background: linear-gradient(135deg, #dbe6f6, #f9faff);
    font-family: 'Poppins', sans-serif;
    display: flex;
    justify-content: center;
    min-height: 100vh;
    align-items: center;
  }
  /* Container */
  .form-container {
    background: #f0f4ff;
    padding: 40px 35px;
    border-radius: 24px;
    max-width: 480px;
    width: 100%;
    box-shadow:
      10px 10px 30px #c1cdfb,
      -10px -10px 30px #ffffff;
    transition: box-shadow 0.3s ease;
  }
  .form-container:hover {
    box-shadow:
      12px 12px 40px #a2b2f5,
      -12px -12px 40px #ffffff;
  }
  /* Heading */
  h2 {
    text-align: center;
    color: #304ffe;
    font-weight: 700;
    font-size: 2.4rem;
    margin-bottom: 35px;
    letter-spacing: 0.08em;
    text-shadow: 1px 1px 3px rgba(48,79,254,0.4);
  }
  /* Error message */
  .error {
    background: #ffd9d9;
    color: #e53935;
    font-weight: 700;
    text-align: center;
    padding: 12px 15px;
    border-radius: 12px;
    margin-bottom: 20px;
    box-shadow: inset 0 0 8px #e5393511;
  }
  /* Labels */
  label {
    display: block;
    font-weight: 600;
    color: #3f51b5;
    margin-top: 20px;
    margin-bottom: 8px;
    letter-spacing: 0.02em;
  }
  /* Inputs & selects with neumorphism */
  input[type="text"],
  input[type="email"],
  input[type="password"],
  input[type="date"],
  input[type="tel"],
  select {
    width: 100%;
    padding: 14px 20px;
    font-size: 1rem;
    color: #3949ab;
    background: #f0f4ff;
    border: none;
    border-radius: 18px;
    box-shadow:
      inset 8px 8px 15px #c3cbf5,
      inset -8px -8px 15px #ffffff;
    transition: box-shadow 0.3s ease, color 0.3s ease;
  }
  /* Disabled inputs styling */
  input[disabled], select[disabled] {
    color: #9fa8da;
    cursor: not-allowed;
    background: #e4e9ff;
    box-shadow: inset 6px 6px 10px #b1b8f7, inset -6px -6px 10px #ffffff;
  }
  /* Focus states */
  input[type="text"]:focus,
  input[type="email"]:focus,
  input[type="password"]:focus,
  input[type="date"]:focus,
  input[type="tel"]:focus,
  select:focus {
    outline: none;
    color: #1a237e;
    box-shadow:
      inset 2px 2px 6px #9aa8ff,
      inset -2px -2px 6px #d8e0ff,
      0 0 10px #304ffeaa;
  }
  /* Button style */
  button {
    margin-top: 40px;
    width: 100%;
    padding: 16px 0;
    background: linear-gradient(135deg, #304ffe, #536dfe);
    border: none;
    border-radius: 30px;
    color: white;
    font-weight: 700;
    font-size: 1.25rem;
    cursor: pointer;
    box-shadow: 0 8px 20px rgba(48,79,254,0.6);
    transition: background 0.3s ease, box-shadow 0.3s ease;
  }
  button:hover {
    background: linear-gradient(135deg, #2838d3, #4554e8);
    box-shadow: 0 12px 28px rgba(40,56,211,0.8);
  }
  /* Back link */
  .back-link {
    display: block;
    text-align: center;
    margin-top: 30px;
    font-weight: 600;
    color: #536dfe;
    text-decoration: none;
    font-size: 1rem;
    transition: color 0.3s ease;
  }
  .back-link:hover {
    color: #2838d3;
    text-decoration: underline;
  }
</style>
</head>
<body>
<div class="form-container">
    <h2>🧑‍💼 Edit User</h2>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>

    <form action="UserServlet" method="post" autocomplete="off">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="userId" value="<%= user.getUserId() %>" />

        <label for="username">Username</label>
        <input type="text" id="username" name="username" value="<%= user.getUsername() %>" required />

        <label for="password">Password</label>
        <input type="password" id="password" name="password" value="<%= user.getPassword() %>" required />

        <label for="role">Role</label>
        <%
            if ("librarian".equals(currentRole)) {
        %>
            <input type="hidden" name="role" value="patron" />
            <input type="text" value="Patron" disabled />
        <%
            } else if ("admin".equals(currentRole)) {
        %>
            <select id="role" name="role" required>
                <option value="admin" <%= "admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
                <option value="librarian" <%= "librarian".equals(user.getRole()) ? "selected" : "" %>>Librarian</option>
                <option value="patron" <%= "patron".equals(user.getRole()) ? "selected" : "" %>>Patron</option>
            </select>
        <%
            } else {
        %>
            <input type="hidden" name="role" value="<%= user.getRole() %>" />
            <input type="text" value="<%= user.getRole().substring(0,1).toUpperCase() + user.getRole().substring(1) %>" disabled />
        <%
            }
        %>

        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required />

        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName" value="<%= user.getLastName() %>" required />

        <label for="email">Email</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required />

        <label for="dob">Date of Birth</label>
        <input type="date" id="dob" name="dob" value="<%= user.getDob() %>" required />

        <label for="phoneNumber">Phone Number</label>
        <input type="tel" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>" required />

        <button type="submit">Update User</button>
    </form>

    <a class="back-link" href="UserServlet?action=viewAll">← Back to Users List</a>
</div>
</body>
</html>
