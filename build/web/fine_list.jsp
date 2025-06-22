<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.Fine" %>
<%@ page import="models.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Manage Fines - Admin Dashboard</title>
<style>
  @import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css');

  body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: #f0f4f9;
    margin: 0; 
    padding: 25px 20px;
    color: #2c3e50;
  }

  .container {
    max-width: 1200px;
    margin: auto;
  }

  h2 {
    font-weight: 700;
    font-size: 2.5rem;
    color: #34495e;
    text-align: center;
    margin-bottom: 40px;
    letter-spacing: 0.07em;
    text-transform: uppercase;
  }

  /* Messages */
  .message-box {
    margin-bottom: 25px;
    padding: 15px 25px;
    border-radius: 12px;
    font-weight: 700;
    font-size: 16px;
    text-align: center;
  }
  .message-success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
  }
  .message-error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
  }

  /* Top Actions */
  .top-actions {
    text-align: right;
    margin-bottom: 30px;
  }
  .top-actions a.btn-dashboard {
    background: linear-gradient(135deg, #536dfe, #304ffe);
    color: white;
    padding: 12px 28px;
    border-radius: 30px;
    font-weight: 700;
    font-size: 16px;
    text-decoration: none;
    box-shadow: 0 6px 15px rgba(48,79,254,0.5);
    display: inline-flex;
    align-items: center;
    gap: 10px;
    transition: background 0.3s ease;
  }
  .top-actions a.btn-dashboard:hover {
    background: linear-gradient(135deg, #304ffe, #536dfe);
    box-shadow: 0 8px 25px rgba(48,79,254,0.8);
  }

  /* Search form */
  .search-form {
    background: white;
    padding: 20px 30px;
    border-radius: 18px;
    display: flex;
    gap: 25px;
    flex-wrap: wrap;
    align-items: center;
    box-shadow: 0 4px 20px rgba(0,0,0,0.05);
    margin-bottom: 40px;
  }
  .search-group {
    flex: 1 1 180px;
    display: flex;
    flex-direction: column;
  }
  .search-group label {
    font-weight: 700;
    margin-bottom: 6px;
    color: #34495e;
  }
  .search-group input[type="text"],
  .search-group select {
    padding: 12px 14px;
    border-radius: 12px;
    border: 1.5px solid #ccc;
    font-size: 15px;
    transition: border-color 0.3s ease, box-shadow 0.3s ease;
  }
  .search-group input[type="text"]:focus,
  .search-group select:focus {
    outline: none;
    border-color: #304ffe;
    box-shadow: 0 0 8px #304ffe66;
  }
  .search-form button {
    background: #304ffe;
    color: white;
    padding: 14px 35px;
    border: none;
    border-radius: 30px;
    font-weight: 700;
    font-size: 16px;
    cursor: pointer;
    box-shadow: 0 6px 18px rgba(48,79,254,0.5);
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
  }
  .search-form button:hover {
    background-color: #2536cc;
    box-shadow: 0 8px 25px rgba(37,54,204,0.7);
  }

  /* Card list */
  .fine-list {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
    gap: 25px;
  }
  .fine-card {
    background: white;
    border-radius: 20px;
    padding: 25px 30px;
    box-shadow: 0 8px 20px rgba(0,0,0,0.08);
    display: flex;
    flex-direction: column;
    gap: 14px;
    position: relative;
  }
  .fine-id {
    position: absolute;
    top: 20px;
    right: 25px;
    font-weight: 700;
    color: #7f8c8d;
    font-size: 0.85rem;
    user-select: none;
  }
  .fine-row {
    display: flex;
    justify-content: space-between;
    gap: 12px;
    flex-wrap: wrap;
  }
  .fine-row > div {
    flex: 1 1 45%;
    min-width: 130px;
  }
  .label {
    font-weight: 700;
    color: #34495e;
    font-size: 0.9rem;
    margin-bottom: 4px;
    user-select: none;
  }
  .value {
    font-size: 1.05rem;
    font-weight: 600;
    color: #2c3e50;
    word-break: break-word;
  }

  /* Status badge */
  .status-badge {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    padding: 6px 16px;
    border-radius: 30px;
    font-weight: 700;
    font-size: 0.95rem;
    user-select: none;
  }
  .status-paid {
    background-color: #27ae60;
    color: #dff5e1;
    box-shadow: 0 2px 8px #1e8449aa;
  }
  .status-unpaid {
    background-color: #e74c3c;
    color: #f9d6d5;
    box-shadow: 0 2px 8px #a9322699;
  }
  .status-badge i {
    font-size: 1.2rem;
  }

  /* Actions container */
  .actions-cell {
    margin-top: 20px;
    display: flex;
    gap: 12px;
    flex-wrap: wrap;
    justify-content: flex-start;
  }
  .btn {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 10px 22px;
    border-radius: 30px;
    font-weight: 700;
    font-size: 0.9rem;
    border: none;
    cursor: pointer;
    user-select: none;
    transition: background-color 0.3s ease, box-shadow 0.3s ease;
    text-decoration: none;
    color: white;
    white-space: nowrap;
  }
  .btn:focus-visible {
    outline: 3px solid #2980b9;
    outline-offset: 3px;
  }
  .btn-paid {
    background-color: #27ae60;
    box-shadow: 0 4px 15px #1e8449a6;
  }
  .btn-paid:hover:not([disabled]) {
    background-color: #1e8449;
    box-shadow: 0 6px 22px #1e8449d8;
  }
  .btn-unpaid {
    background-color: #e74c3c;
    box-shadow: 0 4px 15px #a9322699;
  }
  .btn-unpaid:hover:not([disabled]) {
    background-color: #a93226;
    box-shadow: 0 6px 22px #a9322699;
  }
  .btn-edit {
    background-color: #2980b9;
    box-shadow: 0 4px 15px #1c5980bb;
  }
  .btn-edit:hover {
    background-color: #1c5980;
    box-shadow: 0 6px 22px #1c5980cc;
  }
  .btn-delete {
    background-color: #7f8c8d;
    box-shadow: 0 4px 15px #4b545599;
  }
  .btn-delete:hover {
    background-color: #4b5455;
    box-shadow: 0 6px 22px #4b545599;
  }

  /* Days overdue badge */
  .days-overdue {
    font-weight: 700;
    font-size: 1rem;
    color: #e74c3c;
    background: #fbeaea;
    border-radius: 20px;
    padding: 4px 12px;
    user-select: none;
    display: inline-block;
  }

  /* No data */
  .no-data {
    font-style: italic;
    color: #7f8c8d;
    text-align: center;
    padding: 40px 0;
    font-size: 1.2rem;
  }

  /* Responsive tweaks */
  @media (max-width: 700px) {
    .fine-row > div {
      flex: 1 1 100%;
    }
    .actions-cell {
      justify-content: center;
    }
  }
</style>
</head>
<body>
<div class="container">
  <h2>Manage Fines</h2>

  <%-- Messages --%>
  <%
    String message = (String) session.getAttribute("message");
    String status = (String) session.getAttribute("status");
    if (message != null) {
  %>
    <div class="message-box <%= "success".equals(status) ? "message-success" : "message-error" %>" role="alert" aria-live="assertive">
      <%= message %>
    </div>
  <%
    session.removeAttribute("message");
    session.removeAttribute("status");
    }
  %>

  <%-- Back to dashboard --%>
  <%
    User currentUser = (User) session.getAttribute("currentUser");
    String role = currentUser != null ? currentUser.getRole() : "";
    String backUrl = "login.jsp";
    if ("admin".equals(role)) {
      backUrl = "AdminDashboardServlet";
    } else if ("librarian".equals(role)) {
      backUrl = "librarian_dashboard.jsp";
    }
  %>
  <div class="top-actions">
    <a href="<%= backUrl %>" class="btn-dashboard" aria-label="Back to Dashboard">
      <i class="fa fa-home"></i> Dashboard
    </a>
  </div>

  <%-- Search form --%>
  <form method="get" action="FineServlet" class="search-form" role="search" aria-label="Search Fines">
    <input type="hidden" name="action" value="viewAll" />
    
    <div class="search-group">
      <label for="searchUser">User</label>
      <input type="text" id="searchUser" name="searchUser" 
             value="<%= request.getParameter("searchUser") != null ? request.getParameter("searchUser") : "" %>" 
             placeholder="First or Last Name" autocomplete="off" />
    </div>
    
    <div class="search-group">
      <label for="searchBook">Book Title</label>
      <input type="text" id="searchBook" name="searchBook" 
             value="<%= request.getParameter("searchBook") != null ? request.getParameter("searchBook") : "" %>" 
             placeholder="Book Title" autocomplete="off" />
    </div>
    
    <div class="search-group">
      <label for="searchStatus">Status</label>
      <select id="searchStatus" name="searchStatus" aria-label="Filter by payment status">
        <option value="" <%= (request.getParameter("searchStatus") == null || request.getParameter("searchStatus").isEmpty()) ? "selected" : "" %>>All</option>
        <option value="paid" <%= "paid".equalsIgnoreCase(request.getParameter("searchStatus")) ? "selected" : "" %>>Paid</option>
        <option value="unpaid" <%= "unpaid".equalsIgnoreCase(request.getParameter("searchStatus")) ? "selected" : "" %>>Unpaid</option>
      </select>
    </div>

    <button type="submit" aria-label="Search fines"><i class="fa fa-magnifying-glass"></i> Search</button>
  </form>

  <%-- Fine list as cards --%>
  <%
    List<Fine> fines = (List<Fine>) request.getAttribute("fines");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    if (fines != null && !fines.isEmpty()) {
  %>
    <div class="fine-list" role="list" aria-label="List of fines">
      <% for (Fine fine : fines) { 
           java.util.Date due = fine.getDueDate();
           java.util.Date paid = fine.getPaidDate();
           long daysOverdue = 0;
           java.util.Date today = new java.util.Date();

           if (due != null) {
             if ("paid".equalsIgnoreCase(fine.getPaymentStatus()) && paid != null) {
               daysOverdue = (paid.getTime() - due.getTime()) / (1000 * 60 * 60 * 24);
             } else {
               daysOverdue = (today.getTime() - due.getTime()) / (1000 * 60 * 60 * 24);
             }
             if (daysOverdue < 0) daysOverdue = 0;
           }
      %>
      <article class="fine-card" role="listitem" aria-label="Fine ID <%= fine.getFineId() %>">
        <div class="fine-id">#<%= fine.getFineId() %></div>

        <div class="fine-row">
          <div>
            <div class="label">User</div>
            <div class="value"><%= fine.getUserFirstName() %> <%= fine.getUserLastName() %></div>
          </div>
          <div>
            <div class="label">Book</div>
            <div class="value" title="<%= fine.getBookTitle() %>"><%= fine.getBookTitle().length() > 30 ? fine.getBookTitle().substring(0, 27) + "…" : fine.getBookTitle() %></div>
          </div>
        </div>

        <div class="fine-row">
          <div>
            <div class="label">Amount (JOD)</div>
            <div class="value"><%= String.format("%.2f", fine.getFineAmount()) %></div>
          </div>
          <div>
            <div class="label">Status</div>
            <% if ("paid".equalsIgnoreCase(fine.getPaymentStatus())) { %>
              <span class="status-badge status-paid" aria-label="Paid fine">
                <i class="fa fa-check-circle"></i> Paid
              </span>
            <% } else { %>
              <span class="status-badge status-unpaid" aria-label="Unpaid fine">
                <i class="fa fa-clock"></i> Unpaid
              </span>
            <% } %>
          </div>
        </div>

        <div class="fine-row">
          <div>
            <div class="label">Borrow Date</div>
            <div class="value"><%= fine.getBorrowDate() != null ? sdf.format(fine.getBorrowDate()) : "—" %></div>
          </div>
          <div>
            <div class="label">Due Date</div>
            <div class="value"><%= fine.getDueDate() != null ? sdf.format(fine.getDueDate()) : "—" %></div>
          </div>
        </div>

        <div class="fine-row">
          <div>
            <div class="label">Paid Date</div>
            <div class="value"><%= fine.getPaidDate() != null ? sdf.format(fine.getPaidDate()) : "—" %></div>
          </div>
          <div>
            <div class="label">Days Overdue</div>
            <div class="value">
              <% if (daysOverdue > 0) { %>
                <span class="days-overdue" aria-label="<%= daysOverdue %> days overdue"><%= daysOverdue %></span>
              <% } else { %>
                0
              <% } %>
            </div>
          </div>
        </div>

        <div class="actions-cell" role="group" aria-label="Actions for fine ID <%= fine.getFineId() %>">
          <% if ("unpaid".equalsIgnoreCase(fine.getPaymentStatus())) { %>
            <a href="FineServlet?action=markPaid&fineId=<%= fine.getFineId() %>" 
               class="btn btn-paid" 
               onclick="return confirm('Mark this fine as paid?');"
               aria-label="Mark fine <%= fine.getFineId() %> as paid">
              <i class="fa fa-check"></i> Mark Paid
            </a>
          <% } %>

          <% if ("admin".equals(role)) { %>
            <a href="FineServlet?action=editForm&fineId=<%= fine.getFineId() %>" class="btn btn-edit" aria-label="Edit fine <%= fine.getFineId() %>">
              <i class="fa fa-pen"></i> Edit
            </a>
            <a href="FineServlet?action=delete&fineId=<%= fine.getFineId() %>" 
               class="btn btn-delete" 
               onclick="return confirm('Are you sure you want to delete this fine?');"
               aria-label="Delete fine <%= fine.getFineId() %>">
              <i class="fa fa-trash"></i> Delete
            </a>
          <% } %>
        </div>
      </article>
      <% } %>
    </div>
  <%
    } else {
  %>
    <div class="no-data" role="alert" aria-live="polite">No fines found matching the criteria.</div>
  <%
    }
  %>
</div>
</body>
</html>
