package Controller;

import DAO.BookDAO;
import DAO.FineDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DBConnection.getConnection()) {
            BookDAO bookDAO = new BookDAO(conn);
            UserDAO userDAO = new UserDAO(conn);
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            FineDAO fineDAO = new FineDAO(conn);

            int totalBooks = bookDAO.getAllBooks().size();
            int totalUsers = userDAO.getAllUsers().size();
            int borrowedBooks = transactionDAO.getAllTransactionsByStatus("borrowed").size();
            double outstandingFines = fineDAO.getUnpaidFines().stream()
                                            .mapToDouble(f -> f.getFineAmount())
                                            .sum();

            request.setAttribute("totalBooks", totalBooks);
            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("borrowedBooks", borrowedBooks);
            request.setAttribute("outstandingFines", outstandingFines);

            request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load dashboard data: " + e.getMessage());
            request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
        }
    }
}
