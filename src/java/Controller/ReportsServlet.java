package Controller;

import DAO.LibraryStatisticsDAO;
import Util.DBConnection;
import models.LibraryStatistics;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class ReportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            LibraryStatisticsDAO statsDAO = new LibraryStatisticsDAO(conn);
            LibraryStatistics stats = statsDAO.getCurrentStatistics();

            request.setAttribute("stats", stats);
            request.getRequestDispatcher("reports.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load reports: " + e.getMessage());
            request.getRequestDispatcher("reports.jsp").forward(request, response);
        }
    }
}
