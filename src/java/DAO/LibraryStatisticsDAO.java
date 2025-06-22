package DAO;

import java.sql.*;
import models.LibraryStatistics;

public class LibraryStatisticsDAO {
    private Connection conn;

    public LibraryStatisticsDAO(Connection conn) {
        this.conn = conn;
    }

    // جلب إحصائيات المكتبة
    public LibraryStatistics getCurrentStatistics() throws SQLException {
        LibraryStatistics stats = new LibraryStatistics();

        // إجمالي الكتب
        String totalBooksSql = "SELECT COUNT(*) AS total_books FROM books";
        // عدد المستعيرين النشطين (الذين لديهم كتب مستعارة حالياً)
        String activeBorrowersSql = "SELECT COUNT(DISTINCT user_id) AS active_borrowers FROM transactions WHERE status = 'borrowed'";
        // عدد الكتب المحجوزة (مؤقتا في حالة الحجز متوفرة)
        String reservedBooksSql = "SELECT COUNT(*) AS reserved_books FROM books WHERE availability = 'reserved'";
        // مجموع الغرامات التي تم تحصيلها
        String totalFinesCollectedSql = "SELECT NVL(SUM(fine_amount), 0) AS total_collected FROM fines WHERE payment_status = 'paid'";
        // مجموع الغرامات المستحقة (غير المدفوعة)
        String outstandingFinesSql = "SELECT NVL(SUM(fine_amount), 0) AS outstanding FROM fines WHERE payment_status = 'unpaid'";

        try (Statement stmt = conn.createStatement()) {
            // إجمالي الكتب
            ResultSet rs = stmt.executeQuery(totalBooksSql);
            if (rs.next()) {
                stats.setTotalBooks(rs.getInt("total_books"));
            }
            rs.close();

            // المستعيرين النشطين
            rs = stmt.executeQuery(activeBorrowersSql);
            if (rs.next()) {
                stats.setActiveBorrowers(rs.getInt("active_borrowers"));
            }
            rs.close();

            // الكتب المحجوزة
            rs = stmt.executeQuery(reservedBooksSql);
            if (rs.next()) {
                stats.setReservedBooks(rs.getInt("reserved_books"));
            }
            rs.close();

            // الغرامات المحصلة
            rs = stmt.executeQuery(totalFinesCollectedSql);
            if (rs.next()) {
                stats.setTotalFinesCollected(rs.getDouble("total_collected"));
            }
            rs.close();

            // الغرامات المستحقة
            rs = stmt.executeQuery(outstandingFinesSql);
            if (rs.next()) {
                stats.setOutstandingFines(rs.getDouble("outstanding"));
            }
            rs.close();
        }

        return stats;
    }
}
