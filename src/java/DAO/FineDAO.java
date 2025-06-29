package DAO;

import models.Fine;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class FineDAO {

    private final Connection conn;

    public FineDAO(Connection conn) {
        this.conn = conn;
    }

    /* ==========================================================
     *                INSERT / UPDATE / DELETE
     * ========================================================== */

    /** إدخال غرامة جديدة */
    public void insertFine(Fine fine) throws SQLException {
        String sql = "INSERT INTO fines (transaction_id, fine_amount, payment_status, paid_date) "
                   + "VALUES (?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fine.getTransactionId());
            ps.setDouble(2, fine.getFineAmount());
            ps.setString(3, fine.getPaymentStatus());
            if (fine.getPaidDate() != null) {
                ps.setDate(4, fine.getPaidDate());
            } else {
                ps.setNull(4, Types.DATE);
            }
            ps.executeUpdate();
        }
    }

    /** وضع الغرامة كمدفوعة */
    public void markFineAsPaid(int fineId, Date paidDate) throws SQLException {
        String sql = "UPDATE fines SET payment_status = 'paid', paid_date = ? WHERE fine_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, paidDate);
            ps.setInt (2, fineId);
            ps.executeUpdate();
        }
    }

    /** حذف غرامة */
    public void deleteFine(int fineId) throws SQLException {
        try (PreparedStatement ps =
                 conn.prepareStatement("DELETE FROM fines WHERE fine_id = ?")) {
            ps.setInt(1, fineId);
            ps.executeUpdate();
        }
    }

    /** تعديل مبلغ أو حالة الغرامة */
    public void updateFine(Fine fine) throws SQLException {
        String sql = "UPDATE fines SET fine_amount = ?, payment_status = ?, paid_date = ? WHERE fine_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, fine.getFineAmount());
            ps.setString(2, fine.getPaymentStatus());
            if (fine.getPaidDate() != null) {
                ps.setDate(3, fine.getPaidDate());
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setInt(4, fine.getFineId());
            ps.executeUpdate();
        }
    }

    /* ==========================================================
     *                           GET
     * ========================================================== */

    /** جلب غرامة (كاملة التفاصيل) حسب ID */
    public Fine getFineById(int fineId) throws SQLException {
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, "
                   + "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date "
                   + "FROM fines f "
                   + "JOIN transactions t ON f.transaction_id = t.transaction_id "
                   + "JOIN users u        ON t.user_id       = u.user_id "
                   + "JOIN books b        ON t.book_id       = b.book_id "
                   + "WHERE f.fine_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, fineId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapWithDetails(rs);
            }
        }
        return null;
    }

    /** جلب غرامة (حقول أساسيّة) عبر Transaction ID */
    public Fine getFineByTransactionId(String txId) throws SQLException {
        try (PreparedStatement ps =
                 conn.prepareStatement("SELECT * FROM fines WHERE transaction_id = ?")) {
            ps.setString(1, txId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? mapBasic(rs) : null;
        }
    }

    /** التحقّق من وجود غرامة لمعاملة معيّنة */
    public boolean existsFineForTransaction(String txId) throws SQLException {
        try (PreparedStatement ps =
                 conn.prepareStatement("SELECT COUNT(*) FROM fines WHERE transaction_id = ?")) {
            ps.setString(1, txId);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    /** جميع الغرامات بالتفاصيل */
    public List<Fine> getAllFinesDetailed() throws SQLException {
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, "
                   + "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date "
                   + "FROM fines f "
                   + "JOIN transactions t ON f.transaction_id = t.transaction_id "
                   + "JOIN users u        ON t.user_id       = u.user_id "
                   + "JOIN books b        ON t.book_id       = b.book_id "
                   + "ORDER BY f.payment_status, f.fine_id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Fine> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapWithDetails(rs));
            }
            return list;
        }
    }

    /** الغرامات غير المدفوعة */
    public List<Fine> getUnpaidFines() throws SQLException {
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, "
                   + "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date "
                   + "FROM fines f "
                   + "JOIN transactions t ON f.transaction_id = t.transaction_id "
                   + "JOIN users u        ON t.user_id       = u.user_id "
                   + "JOIN books b        ON t.book_id       = b.book_id "
                   + "WHERE f.payment_status = 'unpaid' "
                   + "ORDER BY f.fine_id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Fine> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapWithDetails(rs));
            }
            return list;
        }
    }

    /** مجموع الغرامات المدفوعة */
    public double getTotalFinesCollected() throws SQLException {
        try (PreparedStatement ps =
                 conn.prepareStatement("SELECT COALESCE(SUM(fine_amount), 0) "
                                      + "FROM fines WHERE payment_status = 'paid'");
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getDouble(1) : 0.0;
        }
    }

    /** البحث باستخدام فلاتر */
    public List<Fine> getFinesWithFilters(String user, String book, String status) throws SQLException {
        StringBuilder sb = new StringBuilder(
            "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, "
          + "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date "
          + "FROM fines f "
          + "JOIN transactions t ON f.transaction_id = t.transaction_id "
          + "JOIN users u        ON t.user_id       = u.user_id "
          + "JOIN books b        ON t.book_id       = b.book_id "
          + "WHERE 1 = 1 ");
        List<Object> params = new ArrayList<>();

        if (user != null && !user.trim().isEmpty()) {
            sb.append("AND (LOWER(u.first_name) LIKE ? OR LOWER(u.last_name) LIKE ?) ");
            String like = "%" + user.trim().toLowerCase() + "%";
            params.add(like);
            params.add(like);
        }
        if (book != null && !book.trim().isEmpty()) {
            sb.append("AND LOWER(b.title) LIKE ? ");
            params.add("%" + book.trim().toLowerCase() + "%");
        }
        if (status != null && !status.trim().isEmpty()) {
            sb.append("AND LOWER(f.payment_status) = ? ");
            params.add(status.trim().toLowerCase());
        }
        sb.append("ORDER BY f.fine_id DESC");

        try (PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            ResultSet rs = ps.executeQuery();
            List<Fine> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapWithDetails(rs));
            }
            return list;
        }
    }

    /** غرامات مستخدم محدّد */
    public List<Fine> getFinesByUser(int userId) throws SQLException {
        String sql = "SELECT f.fine_id, f.transaction_id, f.fine_amount, f.payment_status, f.paid_date, "
                   + "u.user_id, u.first_name, u.last_name, b.book_id, b.title, t.borrow_date, t.due_date "
                   + "FROM fines f "
                   + "JOIN transactions t ON f.transaction_id = t.transaction_id "
                   + "JOIN users u        ON t.user_id       = u.user_id "
                   + "JOIN books b        ON t.book_id       = b.book_id "
                   + "WHERE u.user_id = ? "
                   + "ORDER BY f.fine_id DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Fine> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapWithDetails(rs));
            }
            return list;
        }
    }

    /* ==========================================================
     *      إنشاء غرامات تلقائيًا للمعاملات المتأخّرة
     * ========================================================== */

    public void createFinesForOverdueTransactions(double ratePerDay) throws SQLException {

        /* أ. معاملات لم تُرجع بعد وقد تجاوزت تاريخ الاستحقاق */
        String q1 = "SELECT transaction_id, due_date "
                   + "FROM transactions "
                   + "WHERE due_date < CURRENT_DATE "
                   + "  AND return_date IS NULL "
                   + "  AND NOT EXISTS (SELECT 1 FROM fines WHERE transaction_id = transactions.transaction_id)";
        try (PreparedStatement ps = conn.prepareStatement(q1);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String txId = rs.getString("transaction_id");
                Date   due  = rs.getDate("due_date");
                long   days = ChronoUnit.DAYS.between(due.toLocalDate(), LocalDate.now());
                double amt  = days * ratePerDay;
                if (amt > 0) {
                    insertAutoFine(txId, amt);
                }
            }
        }

        /* ب. معاملات أُعيدت بعد موعد الاستحقاق */
        String q2 = "SELECT transaction_id, due_date, return_date "
                   + "FROM transactions "
                   + "WHERE return_date IS NOT NULL "
                   + "  AND return_date > due_date "
                   + "  AND NOT EXISTS (SELECT 1 FROM fines WHERE transaction_id = transactions.transaction_id)";
        try (PreparedStatement ps = conn.prepareStatement(q2);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String txId = rs.getString("transaction_id");
                Date   due  = rs.getDate("due_date");
                Date   ret  = rs.getDate("return_date");
                long   days = ChronoUnit.DAYS.between(due.toLocalDate(), ret.toLocalDate());
                double amt  = days * ratePerDay;
                if (amt > 0) {
                    insertAutoFine(txId, amt);
                }
            }
        }
    }

    /* ==========================================================
     *                    PRIVATE  HELPERS
     * ========================================================== */

    private void insertAutoFine(String txId, double amount) throws SQLException {
        try (PreparedStatement ps =
                 conn.prepareStatement("INSERT INTO fines "
                                      + "(transaction_id, fine_amount, payment_status) "
                                      + "VALUES (?, ?, 'unpaid')")) {
            ps.setString(1, txId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
        }
    }

    /** تحويل ResultSet (أساسي) */
    private Fine mapBasic(ResultSet rs) throws SQLException {
        Fine f = new Fine();
        f.setFineId        (rs.getInt   ("fine_id"));
        f.setTransactionId (rs.getString("transaction_id"));
        f.setFineAmount    (rs.getDouble("fine_amount"));
        f.setPaymentStatus (rs.getString("payment_status"));
        f.setPaidDate      (rs.getDate  ("paid_date"));
        return f;
    }

    /** تحويل ResultSet مع جميع التفاصيل */
    private Fine mapWithDetails(ResultSet rs) throws SQLException {
        Fine f = mapBasic(rs);
        f.setUserId        (rs.getInt   ("user_id"));
        f.setUserFirstName (rs.getString("first_name"));
        f.setUserLastName  (rs.getString("last_name"));
        f.setBookId        (rs.getInt   ("book_id"));
        f.setBookTitle     (rs.getString("title"));
        f.setBorrowDate    (rs.getDate  ("borrow_date"));
        f.setDueDate       (rs.getDate  ("due_date"));
        return f;
    }
}
