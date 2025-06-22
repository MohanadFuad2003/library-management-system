package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.User;

public class UserDAO {
    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, role, first_name, last_name, email, dob, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getEmail());
            if (user.getDob() != null) {
                stmt.setDate(7, user.getDob());
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setString(8, user.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    // دالة جديدة لجلب المستخدمين حسب الدور (role)
    public List<User> getUsersByRole(String role) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapResultSetToUser(rs));
                }
            }
        }
        return users;
    }

    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY user_id";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username=?, password=?, role=?, first_name=?, last_name=?, email=?, dob=?, phone_number=? WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getEmail());
            if (user.getDob() != null) {
                stmt.setDate(7, user.getDob());
            } else {
                stmt.setNull(7, Types.DATE);
            }
            stmt.setString(8, user.getPhoneNumber());
            stmt.setInt(9, user.getUserId());
            stmt.executeUpdate();
        }
    }

    // حذف المستخدم وكل سجلاته المرتبطة بشكل آمن
    public void deleteUser(int userId) throws SQLException {
        try {
            conn.setAutoCommit(false);

            // حذف الإشعارات المرتبطة
            String deleteNotifications = "DELETE FROM notifications WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteNotifications)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // حذف المراجعات المرتبطة
            String deleteReviews = "DELETE FROM reviews WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteReviews)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // حذف المشاركات في الكتب
            String deleteSharedBooks = "DELETE FROM shared_books WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteSharedBooks)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // حذف الغرامات المرتبطة بمعاملات المستخدم
            String deleteFines = "DELETE FROM fines WHERE transaction_id IN (SELECT transaction_id FROM transactions WHERE user_id = ?)";
            try (PreparedStatement stmt = conn.prepareStatement(deleteFines)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // حذف المعاملات المرتبطة
            String deleteTransactions = "DELETE FROM transactions WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteTransactions)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();
            }

            // أرشفة المستخدم في جدول الأرشيف قبل الحذف
            User user = getUserById(userId);
            if (user != null) {
                String insertSql = "INSERT INTO deleted_users_archive (user_id, username, password, role, first_name, last_name, email, dob, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, user.getUserId());
                    insertStmt.setString(2, user.getUsername());
                    insertStmt.setString(3, user.getPassword());
                    insertStmt.setString(4, user.getRole());
                    insertStmt.setString(5, user.getFirstName());
                    insertStmt.setString(6, user.getLastName());
                    insertStmt.setString(7, user.getEmail());
                    if (user.getDob() != null) {
                        insertStmt.setDate(8, user.getDob());
                    } else {
                        insertStmt.setNull(8, Types.DATE);
                    }
                    insertStmt.setString(9, user.getPhoneNumber());
                    insertStmt.executeUpdate();
                }
            }

            // حذف المستخدم نفسه
            String deleteUser = "DELETE FROM users WHERE user_id = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteUser)) {
                deleteStmt.setInt(1, userId);
                deleteStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // حذف نهائي للمستخدم من الأرشيف
    public void deleteUserPermanently(int userId) throws SQLException {
        String sql = "DELETE FROM deleted_users_archive WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }
    }

    // جلب المستخدمين المحذوفين (الأرشيف)
    public List<User> getDeletedUsers() throws SQLException {
        List<User> deletedUsers = new ArrayList<>();
        String sql = "SELECT * FROM deleted_users_archive ORDER BY deleted_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
                deletedUsers.add(user);
            }
        }
        return deletedUsers;
    }

    // استرجاع مستخدم من الأرشيف للجدول الأصلي
    public void restoreUser(int userId) throws SQLException {
        String selectSql = "SELECT * FROM deleted_users_archive WHERE user_id = ?";
        User user = null;
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setInt(1, userId);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dob"));
                user.setPhoneNumber(rs.getString("phone_number"));
            } else {
                throw new SQLException("User not found in archive with id: " + userId);
            }
        }

        String insertSql = "INSERT INTO users (user_id, username, password, role, first_name, last_name, email, dob, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setInt(1, user.getUserId());
            insertStmt.setString(2, user.getUsername());
            insertStmt.setString(3, user.getPassword());
            insertStmt.setString(4, user.getRole());
            insertStmt.setString(5, user.getFirstName());
            insertStmt.setString(6, user.getLastName());
            insertStmt.setString(7, user.getEmail());
            if (user.getDob() != null) {
                insertStmt.setDate(8, user.getDob());
            } else {
                insertStmt.setNull(8, Types.DATE);
            }
            insertStmt.setString(9, user.getPhoneNumber());
            insertStmt.executeUpdate();
        }

        String deleteSql = "DELETE FROM deleted_users_archive WHERE user_id = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, userId);
            deleteStmt.executeUpdate();
        }
    }

    // الحصول على المستخدم حسب اسم المستخدم
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        }
        return null;
    }

    // البحث في المستخدمين بالاسم الأول أو الأخير
    public List<User> searchUsersByName(String keyword) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String wildcard = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, wildcard);
            stmt.setString(2, wildcard);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        }
        return users;
    }

    // تحويل نتيجة الاستعلام إلى كائن مستخدم
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setDob(rs.getDate("dob"));
        user.setPhoneNumber(rs.getString("phone_number"));
        return user;
    }
}
