
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.SharedBook;

public class SharedBookDAO {
    private Connection conn;

    public SharedBookDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ 1. إضافة مشاركة جديدة
    public void insertSharedBook(SharedBook sharedBook) throws SQLException {
        String sql = "INSERT INTO shared_books (user_id, book_id, shared_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sharedBook.getUserId());
            stmt.setInt(2, sharedBook.getBookId());
            stmt.setTimestamp(3, sharedBook.getSharedDate());
            stmt.executeUpdate();
        }
    }

    // ✅ 2. عرض الكتب التي تمّت مشاركتها *مع* هذا المستخدم (بافتراض مشاركة داخلية)
    public List<SharedBook> getSharedBooksForUser(int userId) throws SQLException {
        List<SharedBook> sharedList = new ArrayList<>();
        String sql = "SELECT * FROM shared_books WHERE book_id IN (SELECT book_id FROM transactions WHERE user_id = ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sharedList.add(mapResultSetToSharedBook(rs));
            }
        }
        return sharedList;
    }

    // ✅ 3. عرض الكتب التي قام هذا المستخدم بمشاركتها
    public List<SharedBook> getSharedByUser(int userId) throws SQLException {
        List<SharedBook> sharedList = new ArrayList<>();
        String sql = "SELECT * FROM shared_books WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                sharedList.add(mapResultSetToSharedBook(rs));
            }
        }
        return sharedList;
    }

    // ✅ Helper لتحويل نتيجة الاستعلام إلى كائن SharedBook
    private SharedBook mapResultSetToSharedBook(ResultSet rs) throws SQLException {
        SharedBook sb = new SharedBook();
        sb.setSharedId(rs.getInt("shared_id"));
        sb.setUserId(rs.getInt("user_id"));
        sb.setBookId(rs.getInt("book_id"));
        sb.setSharedDate(rs.getTimestamp("shared_date"));
        return sb;
    }
}
