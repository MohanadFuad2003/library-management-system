package DAO;

import java.sql.*;
import models.SystemConfig;

public class SystemConfigDAO {
    private Connection conn;

    public SystemConfigDAO(Connection conn) {
        this.conn = conn;
    }

    // جلب الإعدادات الحالية (نفترض وجود سجل واحد فقط)
    public SystemConfig getCurrentConfig() throws SQLException {
        String sql = "SELECT config_id, fine_rate, max_books_borrowed, max_borrow_period FROM system_config WHERE ROWNUM = 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new SystemConfig(
                    rs.getInt("config_id"),
                    rs.getDouble("fine_rate"),
                    rs.getInt("max_books_borrowed"),
                    rs.getInt("max_borrow_period")
                );
            }
        }
        return null;
    }

    // تحديث الإعدادات
    public void updateConfig(SystemConfig config) throws SQLException {
        String sql = "UPDATE system_config SET fine_rate = ?, max_books_borrowed = ?, max_borrow_period = ? WHERE config_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, config.getFineRate());
            stmt.setInt(2, config.getMaxBooksBorrowed());
            stmt.setInt(3, config.getMaxBorrowPeriod());
            stmt.setInt(4, config.getConfigId());
            stmt.executeUpdate();
        }
    }
}
