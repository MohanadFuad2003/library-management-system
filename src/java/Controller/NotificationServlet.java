package Controller;

import DAO.NotificationDAO;
import Util.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import models.Notification;
import models.User;

public class NotificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAll";

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            NotificationDAO notificationDAO = new NotificationDAO(conn);

            switch (action) {
                case "viewAll":
                    // جلب كل الإشعارات (مقروء وغير مقروء)
                    List<Notification> notifications = notificationDAO.getAllNotificationsForUser(currentUser.getUserId());
                    request.setAttribute("notifications", notifications);
                    request.getRequestDispatcher("notifications.jsp").forward(request, response);
                    break;

                case "markAllRead":
                    notificationDAO.markAllAsRead(currentUser.getUserId());
                    session.setAttribute("message", "All notifications marked as read.");
                    response.sendRedirect("NotificationServlet?action=viewAll");
                    break;

                case "delete":
                    int notifId = Integer.parseInt(request.getParameter("notificationId"));
                    notificationDAO.deleteNotification(notifId);
                    session.setAttribute("message", "Notification deleted.");
                    response.sendRedirect("NotificationServlet?action=viewAll");
                    break;

                default:
                    response.sendRedirect("NotificationServlet?action=viewAll");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("notifications.jsp").forward(request, response);
        }
    }
    
    
    
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String action = request.getParameter("action");
    HttpSession session = request.getSession();
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    try (Connection conn = DBConnection.getConnection()) {
        NotificationDAO notificationDAO = new NotificationDAO(conn);

        if ("markRead".equals(action)) {
            int notificationId = Integer.parseInt(request.getParameter("notificationId"));
            // غيّر حالة الإشعار المحدد إلى مقروء
            String sql = "UPDATE notifications SET status = 'read' WHERE notification_id = ? AND user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, notificationId);
                stmt.setInt(2, currentUser.getUserId());
                stmt.executeUpdate();
            }
            session.setAttribute("message", "Notification marked as read.");
            session.setAttribute("status", "success");
            response.sendRedirect("NotificationServlet?action=viewAll");
            return;
        }

        // لأي أكشن غير مدعوم
        response.sendRedirect("NotificationServlet?action=viewAll");
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "Error: " + e.getMessage());
        request.getRequestDispatcher("notifications.jsp").forward(request, response);
    }
}

}
