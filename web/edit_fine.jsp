<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Fine" %>
<!DOCTYPE html>
<html>
<head>
    <title>🔧 Edit Fine</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet" />
    <style>
        /* Reset and base */
        * {
            box-sizing: border-box;
        }
        body {
            margin: 0; padding: 40px 20px;
            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);
            font-family: 'Poppins', sans-serif;
            display: flex;
            justify-content: center;
            min-height: 100vh;
            align-items: center;
        }
        /* Container */
        .container {
            background: #f0f3f7;
            padding: 40px 35px;
            border-radius: 24px;
            max-width: 460px;
            width: 100%;
            box-shadow:
                8px 8px 15px #bec8d2,
                -8px -8px 15px #ffffff;
            transition: box-shadow 0.3s ease;
        }
        .container:hover {
            box-shadow:
                10px 10px 20px #a1abc9,
                -10px -10px 20px #ffffff;
        }
        /* Title */
        h2 {
            text-align: center;
            color: #536dfe;
            font-weight: 700;
            font-size: 2.3rem;
            margin-bottom: 35px;
            letter-spacing: 0.08em;
            text-shadow: 1px 1px 3px rgba(83,109,254,0.4);
        }
        /* Form layout */
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            font-weight: 600;
            color: #485563;
            margin-bottom: 6px;
            margin-top: 20px;
            letter-spacing: 0.02em;
        }
        input[type="number"],
        select,
        input[type="date"] {
            padding: 14px 18px;
            border-radius: 18px;
            border: none;
            background: #e0e5ec;
            box-shadow:
                inset 8px 8px 10px #bec8d2,
                inset -8px -8px 10px #ffffff;
            font-size: 1.1rem;
            color: #505050;
            transition: box-shadow 0.3s ease, color 0.3s ease;
        }
        input[type="number"]:focus,
        select:focus,
        input[type="date"]:focus {
            outline: none;
            color: #1a237e;
            box-shadow:
                inset 3px 3px 6px #9ea8c7,
                inset -3px -3px 6px #ffffff,
                0 0 8px #536dfe;
        }
        /* Disabled input style */
        input[disabled] {
            background: #ccd1e4;
            color: #8a8a8a;
            cursor: not-allowed;
            box-shadow: inset 4px 4px 8px #a7accf, inset -4px -4px 8px #e4e9ff;
        }
        /* Button */
        .btn-submit {
            margin-top: 40px;
            padding: 16px 0;
            border: none;
            border-radius: 30px;
            font-weight: 700;
            font-size: 1.25rem;
            background: linear-gradient(135deg, #536dfe, #677eff);
            color: white;
            cursor: pointer;
            box-shadow: 0 8px 15px rgba(83,109,254,0.4);
            transition: background 0.3s ease, box-shadow 0.3s ease;
        }
        .btn-submit:hover {
            background: linear-gradient(135deg, #4554e8, #5a65ed);
            box-shadow: 0 12px 20px rgba(69,84,232,0.6);
        }
        /* Back link */
        .btn-back {
            margin-top: 28px;
            text-align: center;
        }
        .btn-back a {
            font-weight: 600;
            color: #536dfe;
            text-decoration: none;
            font-size: 1rem;
            transition: color 0.3s ease;
        }
        .btn-back a:hover {
            color: #3949ab;
            text-decoration: underline;
        }
        /* Error message */
        .message-error {
            margin-top: 20px;
            color: #e03e2f;
            font-weight: 700;
            text-align: center;
            letter-spacing: 0.02em;
            text-shadow: 0 0 5px #e03e2f88;
        }
    </style>

    <script>
        function onStatusChange() {
            var status = document.getElementById("paymentStatus").value;
            var paidDateInput = document.getElementById("paidDate");
            if (status === "paid") {
                paidDateInput.disabled = false;
            } else {
                paidDateInput.disabled = true;
                paidDateInput.value = "";
            }
        }
        window.onload = function() {
            onStatusChange();
        };
    </script>
</head>
<body>
<div class="container">
    <h2>🔧 Edit Fine</h2>

    <% Fine fine = (Fine) request.getAttribute("fine");
       if (fine == null) { %>
        <div class="message-error">Fine data not found.</div>
    <% } else { %>

    <% String error = (String) request.getAttribute("error");
       if (error != null) { %>
        <div class="message-error"><%= error %></div>
    <% } %>

    <form action="FineServlet" method="post" autocomplete="off">
        <input type="hidden" name="action" value="edit" />
        <input type="hidden" name="fineId" value="<%= fine.getFineId() %>" />

        <label for="fineAmount">Fine Amount (JOD):</label>
        <input type="number" name="fineAmount" id="fineAmount" step="0.01" min="0" required value="<%= fine.getFineAmount() %>" />

        <label for="paymentStatus">Payment Status:</label>
        <select name="paymentStatus" id="paymentStatus" onchange="onStatusChange()" required>
            <option value="unpaid" <%= "unpaid".equalsIgnoreCase(fine.getPaymentStatus()) ? "selected" : "" %>>Unpaid</option>
            <option value="paid" <%= "paid".equalsIgnoreCase(fine.getPaymentStatus()) ? "selected" : "" %>>Paid</option>
        </select>

        <label for="paidDate">Paid Date:</label>
        <input type="date" name="paidDate" id="paidDate" value="<%= fine.getPaidDate() != null ? fine.getPaidDate().toString() : "" %>" disabled />

        <button type="submit" class="btn-submit">Update Fine</button>
    </form>

    <div class="btn-back">
        <a href="FineServlet?action=viewAll">← Back to Fines List</a>
    </div>
    <% } %>
</div>
</body>
</html>
