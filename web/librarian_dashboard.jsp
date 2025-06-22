<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Librarian Dashboard</title>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

    body {
        font-family: 'Poppins', sans-serif;
        background: linear-gradient(135deg, #c3ecf9 0%, #93e3f9 100%);
        margin: 0;
        padding: 0;
        color: #2c3e50;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .header {
        width: 100%;
        background: linear-gradient(90deg, #34495e 0%, #2980b9 100%);
        color: white;
        padding: 25px 0;
        font-size: 2.4rem;
        font-weight: 600;
        text-align: center;
        box-shadow: 0 5px 15px rgba(0,0,0,0.15);
        letter-spacing: 0.05em;
        user-select: none;
    }

    .dashboard-container {
        max-width: 1080px;
        width: 90%;
        margin: 40px auto 60px;
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
        gap: 30px;
    }

    .card {
        background: #ffffffcc;
        border-radius: 18px;
        padding: 30px 20px;
        text-align: center;
        box-shadow: 0 8px 30px rgba(41, 128, 185, 0.15);
        cursor: pointer;
        position: relative;
        overflow: hidden;
        transition: all 0.35s cubic-bezier(0.4, 0, 0.2, 1);
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 15px;
    }

    .card:hover {
        transform: translateY(-10px) scale(1.05);
        box-shadow: 0 12px 40px rgba(41, 128, 185, 0.35);
        background: #2980b9;
        color: white;
    }

    .card h3 {
        font-weight: 700;
        font-size: 1.25rem;
        margin: 0;
        transition: color 0.35s ease;
    }

    .card p {
        font-size: 3.6rem;
        margin: 0;
        user-select: none;
        transition: color 0.35s ease;
        line-height: 1;
    }

    .card:hover h3,
    .card:hover p {
        color: #f0f8ff;
    }

    /* Gradient icon backgrounds */
    .card[data-icon="books"] p {
        background: linear-gradient(135deg, #3498db, #2980b9);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .card[data-icon="patrons"] p {
        background: linear-gradient(135deg, #9b59b6, #8e44ad);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .card[data-icon="borrow"] p {
        background: linear-gradient(135deg, #e67e22, #d35400);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .card[data-icon="fines"] p {
        background: linear-gradient(135deg, #27ae60, #2ecc71);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .card[data-icon="notifications"] p {
        background: linear-gradient(135deg, #e74c3c, #c0392b);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }
    .card[data-icon="reports"] p {
        background: linear-gradient(135deg, #16a085, #1abc9c);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    /* Creative Logout Button */
    .btn-logout {
        margin: 0 auto 50px;
        padding: 14px 36px;
        font-size: 1.1rem;
        font-weight: 600;
        background: linear-gradient(135deg, #e74c3c, #c0392b);
        color: white;
        border: none;
        border-radius: 40px;
        cursor: pointer;
        box-shadow: 0 6px 15px rgba(231, 76, 60, 0.4);
        transition: all 0.3s ease;
        display: flex;
        align-items: center;
        gap: 12px;
        user-select: none;
        text-decoration: none;
        justify-content: center;
        width: 180px;
    }

    .btn-logout:hover {
        background: linear-gradient(135deg, #c0392b, #992d22);
        box-shadow: 0 8px 25px rgba(192, 57, 43, 0.6);
        transform: scale(1.05);
    }

    .btn-logout svg {
        width: 20px;
        height: 20px;
        fill: white;
        animation: swing 2s ease-in-out infinite;
    }

    @keyframes swing {
        0%, 100% { transform: rotate(0deg); }
        50% { transform: rotate(15deg); }
    }

    /* Responsive tweaks */
    @media (max-width: 600px) {
        .dashboard-container {
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            width: 95%;
        }
    }
    @media (max-width: 400px) {
        .dashboard-container {
            grid-template-columns: 1fr;
        }
    }
</style>
</head>
<body>

<div class="header" role="banner" aria-label="Librarian Dashboard Header">
    📚 Librarian Dashboard
</div>

<div class="dashboard-container" role="main" aria-label="Dashboard navigation cards">

    <div class="card" data-icon="books" tabindex="0" role="button" aria-label="Manage Books" onclick="location.href='BookServlet?action=viewAll'">
        <h3>Manage Books</h3>
        <p>📚</p>
    </div>

    <div class="card" data-icon="patrons" tabindex="0" role="button" aria-label="Manage Patrons" onclick="location.href='UserServlet?action=viewAllPatrons'">
        <h3>Manage Patrons</h3>
        <p>👥</p>
    </div>

    <div class="card" data-icon="borrow" tabindex="0" role="button" aria-label="Borrow and Return" onclick="location.href='TransactionServlet?action=viewAll'">
        <h3>Borrow & Return</h3>
        <p>🔄</p>
    </div>

    <div class="card" data-icon="fines" tabindex="0" role="button" aria-label="Manage Fines" onclick="location.href='FineServlet?action=viewAll'">
        <h3>Manage Fines</h3>
        <p>💰</p>
    </div>

    <div class="card" data-icon="notifications" tabindex="0" role="button" aria-label="Notifications" onclick="location.href='NotificationServlet?action=viewAll'">
        <h3>Notifications</h3>
        <p>🔔</p>
    </div>

    <div class="card" data-icon="reports" tabindex="0" role="button" aria-label="Reports" onclick="location.href='ReportsServlet'">
        <h3>Reports</h3>
        <p>📊</p>
    </div>

</div>

<a href="LogoutServlet" class="btn-logout" aria-label="Logout">
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" aria-hidden="true" focusable="false">
        <path d="M16 13v-2H7V8l-5 4 5 4v-3zM20 3h-8a2 2 0 0 0-2 2v4h2V5h8v14h-8v-4h-2v4a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V5a2 2 0 0 0-2-2z"/>
    </svg>
    Logout
</a>

<script>
    // Accessibility: allow Enter key to trigger card clicks
    document.querySelectorAll('.card').forEach(card => {
        card.addEventListener('keydown', e => {
            if(e.key === 'Enter' || e.key === ' ') {
                e.preventDefault();
                card.click();
            }
        });
    });
</script>

</body>
</html>
