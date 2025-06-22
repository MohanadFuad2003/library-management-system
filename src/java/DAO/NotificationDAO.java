
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Notification;


public class NotificationDAO {
    private Connection conn;

    public NotificationDAO(Connection conn) {
        this.conn = conn;
    }

    // إدراج إشعار جديد (بدون notificationId لأن التسلسل/التريغر يضيفه تلقائيًا)
    public void insertNotification(Notification notification) throws SQLException {
        String sql = "INSERT INTO notifications (user_id, message, status, notification_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getUserId());
            stmt.setString(2, notification.getMessage());
            stmt.setString(3, notification.getStatus());
            stmt.setTimestamp(4, notification.getNotificationDate());
            stmt.executeUpdate();
        }
    }

    // جلب جميع الإشعارات (غير المقروءة) لمستخدم
    public List<Notification> getUnreadNotifications(int userId) throws SQLException {
        List<Notification> list = new ArrayList<>();
        String sql = "SELECT * FROM notifications WHERE user_id = ? AND status = 'unread' ORDER BY notification_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToNotification(rs));
            }
        }
        return list;
    }
    
    // جلب كل الإشعارات للمستخدم (مقروءة وغير مقروءة)
public List<Notification> getAllNotificationsForUser(int userId) throws SQLException {
    List<Notification> list = new ArrayList<>();
    String sql = "SELECT * FROM notifications WHERE user_id = ? ORDER BY notification_date DESC";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(mapResultSetToNotification(rs));
        }
    }
    return list;
}

// حذف إشعار
public void deleteNotification(int notificationId) throws SQLException {
    String sql = "DELETE FROM notifications WHERE notification_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, notificationId);
        stmt.executeUpdate();
    }
}


    // تعيين كل الإشعارات كمقروءة لهذا المستخدم
    public void markAllAsRead(int userId) throws SQLException {
        String sql = "UPDATE notifications SET status = 'read' WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    // دالة الماب: تعتمد فقط البراميتر كونستركتر!
    private Notification mapResultSetToNotification(ResultSet rs) throws SQLException {
        return new Notification(
            rs.getInt("notification_id"),
            rs.getInt("user_id"),
            rs.getString("message"),
            rs.getString("status"),
            rs.getTimestamp("notification_date")
        );
    }
}
