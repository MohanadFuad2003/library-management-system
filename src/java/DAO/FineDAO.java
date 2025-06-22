package DAO;

import models.Fine;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;




public class FineDAO {
    private Connection conn;

    public FineDAO(Connection conn) {
        this.conn = conn;
    }

    // إدخال غرامة جديدة
    public void insertFine(Fine fine) throws SQLException {
        String sql = "INSERT INTO fines (transaction_id, fine_amount, payment_status, paid_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fine.getTransactionId());
            stmt.setDouble(2, fine.getFineAmount());
            stmt.setString(3, fine.getPaymentStatus());
            if (fine.getPaidDate() != null) {
                stmt.setDate(4, fine.getPaidDate());
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.executeUpdate();
        }
    }

    // تحديث حالة الدفع وتاريخ الدفع
    public void markFineAsPaid(int fineId, Date paidDate) throws SQLException {
        String sql = "UPDATE fines SET payment_status = 'paid', paid_date = ? WHERE fine_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, paidDate);
            stmt.setInt(2, fineId);
            stmt.executeUpdate();
        }
    }

    // حذف غرامة
    public void deleteFine(int fineId) throws SQLException {
        String sql = "DELETE FROM fines WHERE fine_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fineId);
            stmt.executeUpdate();
        }
    }

    // تعديل بيانات الغرامة
    public void updateFine(Fine fine) throws SQLException {
        String sql = "UPDATE fines SET fine_amount = ?, payment_status = ?, paid_date = ? WHERE fine_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, fine.getFineAmount());
            stmt.setString(2, fine.getPaymentStatus());
            if (fine.getPaidDate() != null) {
                stmt.setDate(3, fine.getPaidDate());
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.setInt(4, fine.getFineId());
            stmt.executeUpdate();
        }
    }

    // جلب غرامة حسب رقم الغرامة (مع تفاصيل المستخدم والكتاب)
    public Fine getFineById(int fineId) throws SQLException {
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, " +
                     "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date " +
                     "FROM fines f " +
                     "JOIN transactions t ON f.transaction_id = t.transaction_id " +
                     "JOIN users u ON t.user_id = u.user_id " +
                     "JOIN books b ON t.book_id = b.book_id " +
                     "WHERE f.fine_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, fineId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fine fine = mapResultSetToFine(rs);
                fine.setUserId(rs.getInt("user_id"));
                fine.setUserFirstName(rs.getString("first_name"));
                fine.setUserLastName(rs.getString("last_name"));
                fine.setBookId(rs.getInt("book_id"));
                fine.setBookTitle(rs.getString("title"));
                fine.setBorrowDate(rs.getDate("borrow_date"));
                fine.setDueDate(rs.getDate("due_date"));
                return fine;
            }
        }
        return null;
    }

    // جلب غرامة حسب transaction_id (مع الحقول الأساسية فقط)
    public Fine getFineByTransactionId(String transactionId) throws SQLException {
        String sql = "SELECT * FROM fines WHERE transaction_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToFine(rs);
            }
        }
        return null;
    }

    // التأكد من وجود غرامة لمعاملة معينة
    public boolean existsFineForTransaction(String transactionId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM fines WHERE transaction_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // جلب كل الغرامات مع بيانات المستخدم والكتاب
    public List<Fine> getAllFinesDetailed() throws SQLException {
        List<Fine> fines = new ArrayList<>();
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, " +
                     "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date " +
                     "FROM fines f " +
                     "JOIN transactions t ON f.transaction_id = t.transaction_id " +
                     "JOIN users u ON t.user_id = u.user_id " +
                     "JOIN books b ON t.book_id = b.book_id " +
                     "ORDER BY f.payment_status, f.fine_id DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fine fine = mapResultSetToFine(rs);
                fine.setUserId(rs.getInt("user_id"));
                fine.setUserFirstName(rs.getString("first_name"));
                fine.setUserLastName(rs.getString("last_name"));
                fine.setBookId(rs.getInt("book_id"));
                fine.setBookTitle(rs.getString("title"));
                fine.setBorrowDate(rs.getDate("borrow_date"));
                fine.setDueDate(rs.getDate("due_date"));
                fines.add(fine);
            }
        }
        return fines;
    }

    // جلب الغرامات غير المدفوعة فقط
    public List<Fine> getUnpaidFines() throws SQLException {
        List<Fine> fines = new ArrayList<>();
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, " +
                     "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date " +
                     "FROM fines f " +
                     "JOIN transactions t ON f.transaction_id = t.transaction_id " +
                     "JOIN users u ON t.user_id = u.user_id " +
                     "JOIN books b ON t.book_id = b.book_id " +
                     "WHERE f.payment_status = 'unpaid' " +
                     "ORDER BY f.fine_id DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fine fine = mapResultSetToFine(rs);
                fine.setUserId(rs.getInt("user_id"));
                fine.setUserFirstName(rs.getString("first_name"));
                fine.setUserLastName(rs.getString("last_name"));
                fine.setBookId(rs.getInt("book_id"));
                fine.setBookTitle(rs.getString("title"));
                fine.setBorrowDate(rs.getDate("borrow_date"));
                fine.setDueDate(rs.getDate("due_date"));
                fines.add(fine);
            }
        }
        return fines;
    }

    // جلب مجموع الغرامات المدفوعة
    public double getTotalFinesCollected() throws SQLException {
        String sql = "SELECT NVL(SUM(fine_amount), 0) AS total FROM fines WHERE payment_status = 'paid'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total");
            }
        }
        return 0.0;
    }

    // جلب الغرامات مع الفلاتر (اسم مستخدم أو عنوان كتاب أو حالة دفع)
    public List<Fine> getFinesWithFilters(String searchUser, String searchBook, String searchStatus) throws SQLException {
        List<Fine> fines = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, " +
            "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date " +
            "FROM fines f " +
            "JOIN transactions t ON f.transaction_id = t.transaction_id " +
            "JOIN users u ON t.user_id = u.user_id " +
            "JOIN books b ON t.book_id = b.book_id " +
            "WHERE 1=1 "
        );
        List<Object> params = new ArrayList<>();

        if (searchUser != null && !searchUser.trim().isEmpty()) {
            sql.append(" AND (LOWER(u.first_name) LIKE ? OR LOWER(u.last_name) LIKE ?) ");
            String likeUser = "%" + searchUser.trim().toLowerCase() + "%";
            params.add(likeUser);
            params.add(likeUser);
        }
        if (searchBook != null && !searchBook.trim().isEmpty()) {
            sql.append(" AND LOWER(b.title) LIKE ? ");
            params.add("%" + searchBook.trim().toLowerCase() + "%");
        }
        if (searchStatus != null && !searchStatus.trim().isEmpty()) {
            sql.append(" AND LOWER(f.payment_status) = ? ");
            params.add(searchStatus.trim().toLowerCase());
        }
        sql.append(" ORDER BY f.fine_id DESC");

        try (PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Fine fine = mapResultSetToFine(rs);
                fine.setUserId(rs.getInt("user_id"));
                fine.setUserFirstName(rs.getString("first_name"));
                fine.setUserLastName(rs.getString("last_name"));
                fine.setBookId(rs.getInt("book_id"));
                fine.setBookTitle(rs.getString("title"));
                fine.setBorrowDate(rs.getDate("borrow_date"));
                fine.setDueDate(rs.getDate("due_date"));
                fines.add(fine);
            }
        }
        return fines;
    }

    // إنشاء غرامات تلقائياً لكل المعاملات المتأخرة (مرجعة متأخرة أو لم تُرجع بعد)
    public void createFinesForOverdueTransactions(double finePerDay) throws SQLException {
        // غرامات للكتب غير المرجعة والمتأخرة
        String sql = "SELECT t.transaction_id, t.due_date FROM transactions t " +
                "WHERE t.due_date < CURRENT_DATE AND t.return_date IS NULL " +
                "AND (t.fine_blocked = 0 OR t.fine_blocked IS NULL) " +
                "AND NOT EXISTS (SELECT 1 FROM fines f WHERE f.transaction_id = t.transaction_id)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String transactionId = rs.getString("transaction_id");
                Date dueDate = rs.getDate("due_date");
                long daysOverdue = ChronoUnit.DAYS.between(dueDate.toLocalDate(), LocalDate.now());
                double amount = daysOverdue * finePerDay;
                if (amount > 0) {
                    String insertSql = "INSERT INTO fines (transaction_id, fine_amount, payment_status) VALUES (?, ?, 'unpaid')";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, transactionId);
                        insertStmt.setDouble(2, amount);
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
        // غرامات للكتب التي تم إرجاعها متأخرة
        String sql2 = "SELECT t.transaction_id, t.due_date, t.return_date FROM transactions t " +
                "WHERE t.return_date IS NOT NULL AND t.return_date > t.due_date " +
                "AND (t.fine_blocked = 0 OR t.fine_blocked IS NULL) " +
                "AND NOT EXISTS (SELECT 1 FROM fines f WHERE f.transaction_id = t.transaction_id)";
        try (PreparedStatement stmt = conn.prepareStatement(sql2)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String transactionId = rs.getString("transaction_id");
                Date dueDate = rs.getDate("due_date");
                Date returnDate = rs.getDate("return_date");
                long daysOverdue = ChronoUnit.DAYS.between(dueDate.toLocalDate(), returnDate.toLocalDate());
                double amount = daysOverdue * finePerDay;
                if (amount > 0) {
                    String insertSql = "INSERT INTO fines (transaction_id, fine_amount, payment_status) VALUES (?, ?, 'unpaid')";
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setString(1, transactionId);
                        insertStmt.setDouble(2, amount);
                        insertStmt.executeUpdate();
                    }
                }
            }
        }
    }

    // دالة مساعدة لتحويل ResultSet إلى Fine
    private Fine mapResultSetToFine(ResultSet rs) throws SQLException {
        Fine fine = new Fine();
        fine.setFineId(rs.getInt("fine_id"));
        fine.setTransactionId(rs.getString("transaction_id"));
        fine.setFineAmount(rs.getDouble("fine_amount"));
        fine.setPaymentStatus(rs.getString("payment_status"));
        fine.setPaidDate(rs.getDate("paid_date"));
        return fine;
    }
}
