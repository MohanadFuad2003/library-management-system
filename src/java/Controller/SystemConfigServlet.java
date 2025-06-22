package Controller;

import DAO.SystemConfigDAO;
import Util.DBConnection;
import models.SystemConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class SystemConfigServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        models.User currentUser = (models.User) session.getAttribute("currentUser");
        if (currentUser == null || !"admin".equalsIgnoreCase(currentUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin only");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            SystemConfigDAO configDAO = new SystemConfigDAO(conn);
            SystemConfig config = configDAO.getCurrentConfig();

            if (config == null) {
                ServletContext context = getServletContext();
                double fineRate = Double.parseDouble(context.getInitParameter("defaultFineRate"));
                int maxBooksBorrowed = Integer.parseInt(context.getInitParameter("defaultMaxBooksBorrowed"));
                int maxBorrowPeriod = Integer.parseInt(context.getInitParameter("defaultMaxBorrowPeriod"));

                config = new SystemConfig(1, fineRate, maxBooksBorrowed, maxBorrowPeriod);
            }

            request.setAttribute("config", config);
            request.getRequestDispatcher("system_config.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load system config: " + e.getMessage());
            request.getRequestDispatcher("system_config.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        models.User currentUser = (models.User) session.getAttribute("currentUser");
        if (currentUser == null || !"admin".equalsIgnoreCase(currentUser.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Admin only");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            SystemConfigDAO configDAO = new SystemConfigDAO(conn);

            int configId = Integer.parseInt(request.getParameter("configId"));
            double fineRate = Double.parseDouble(request.getParameter("fineRate"));
            int maxBooksBorrowed = Integer.parseInt(request.getParameter("maxBooksBorrowed"));
            int maxBorrowPeriod = Integer.parseInt(request.getParameter("maxBorrowPeriod"));

            if (fineRate < 0 || maxBooksBorrowed < 1 || maxBorrowPeriod < 1) {
                request.setAttribute("error", "Invalid input values.");
                doGet(request, response);
                return;
            }

            SystemConfig config = new SystemConfig(configId, fineRate, maxBooksBorrowed, maxBorrowPeriod);
            configDAO.updateConfig(config);

            request.setAttribute("message", "System configuration updated successfully.");
            request.setAttribute("config", config);
            request.getRequestDispatcher("system_config.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating system config: " + e.getMessage());
            doGet(request, response);
        }
    }
}
