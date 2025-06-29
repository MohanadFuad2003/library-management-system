package DAO;

import models.Fine;
import models.Transaction;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    private final Connection conn;

    public TransactionDAO(Connection conn) {
        this.conn = conn;
    }

    /* ================================================================
     *                       INSERT NEW TRANSACTION
     * ================================================================ */
    public void insertTransaction(Transaction tx) throws SQLException {
        String sql = "INSERT INTO transactions " +
                     "(transaction_id, user_id, book_id, borrow_date, due_date, fine, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tx.getTransactionId());
            ps.setInt(2, tx.getUserId());
            ps.setInt(3, tx.getBookId());
            ps.setDate(4, tx.getBorrowDate());
            ps.setDate(5, tx.getDueDate());
            ps.setDouble(6, tx.getFine());
            ps.setString(7, tx.getStatus());                // e.g., "borrowed"
            ps.executeUpdate();
        }
    }

    /* ================================================================
     *                    RETURN BOOK & APPLY FINE
     * ================================================================ */
    /** Mark a transaction returned, compute any fine, persist it in both
     *  the transactions table (for history) and the fines table (if >0). */
    public void returnBook(String txId, Date returnDate, double rate) throws SQLException {

        Transaction tx = getTransactionById(txId);
        if (tx == null) throw new SQLException("Transaction not found: " + txId);

        // 1) mark returned
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE transactions SET return_date=?, status='returned' WHERE transaction_id=?")) {
            ps.setDate(1, returnDate);
            ps.setString(2, txId);
            ps.executeUpdate();
        }

        // 2) compute fine
        double fineAmt = 0.0;
        if (returnDate.after(tx.getDueDate())) {
            long daysLate = ChronoUnit.DAYS
                    .between(tx.getDueDate().toLocalDate(), returnDate.toLocalDate());
            fineAmt = daysLate * rate;
        }

        // 3) store fine in transactions row
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE transactions SET fine=? WHERE transaction_id=?")) {
            ps.setDouble(1, fineAmt);
            ps.setString(2, txId);
            ps.executeUpdate();
        }

        // 4) separate record in fines table (only if >0 and not already present)
        if (fineAmt > 0) {
            FineDAO fineDAO = new FineDAO(conn);
            if (!fineDAO.existsFineForTransaction(txId)) {
                Fine f = new Fine();
                f.setTransactionId(txId);
                f.setFineAmount(fineAmt);
                f.setPaymentStatus("unpaid");
                f.setPaidDate(null);
                fineDAO.insertFine(f);
            }
        }
    }

    /* ================================================================
     *            PREVENT FUTURE AUTOMATIC FINE GENERATION
     * ================================================================ */
    public void blockFineForTransaction(String transactionId) {
        String sql = "UPDATE transactions SET fine = 0 WHERE transaction_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, transactionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* ================================================================
     *                           GET BY ID
     * ================================================================ */
    public Transaction getTransactionById(String txId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    /* ================================================================
     *                 GET TRANSACTIONS FOR ONE USER
     * ================================================================ */
    public List<Transaction> getTransactionsByUser(int userId) throws SQLException {
        String sql = "SELECT t.*, b.title AS book_title " +
                     "FROM transactions t JOIN books b ON t.book_id = b.book_id " +
                     "WHERE t.user_id = ? ORDER BY t.borrow_date DESC";
        List<Transaction> list = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Transaction tx = map(rs);
                    tx.setBookTitle(rs.getString("book_title"));
                    list.add(tx);
                }
            }
        }
        return list;
    }

    /* ================================================================
     *                GET TRANSACTIONS BY STATUS
     * ================================================================ */
    public List<Transaction> getAllTransactionsByStatus(String status) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE status = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    /* ================================================================
     *           COUNT ACTIVE BORROWS FOR A GIVEN USER
     * ================================================================ */
    public int getActiveBorrowCountForUser(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM transactions " +
                     "WHERE user_id = ? AND status = 'borrowed'";
        return count(sql, userId);
    }

    /* ================================================================
     *                COUNT BORROWS FOR A SPECIFIC BOOK
     * ================================================================ */
    public int getBorrowCountForBook(int bookId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM transactions WHERE book_id = ?";
        return count(sql, bookId);
    }

    /* ================================================================
     *     COUNT BORROWS OF A BOOK BY PATRONS ONLY
     * ================================================================ */
    public int getBorrowCountForBookByPatrons(int bookId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM transactions t " +
                     "JOIN users u ON t.user_id = u.user_id " +
                     "WHERE t.book_id = ? AND u.role = 'patron'";
        return count(sql, bookId);
    }

    /* ================================================================
     *                    INTERNAL COUNT HELPER
     * ================================================================ */
    private int count(String sql, int param) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, param);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    /* ================================================================
     *             MAP RESULTSET → TRANSACTION OBJECT
     * ================================================================ */
    private Transaction map(ResultSet rs) throws SQLException {
        Transaction tx = new Transaction();
        tx.setTransactionId(rs.getString("transaction_id"));
        tx.setUserId       (rs.getInt   ("user_id"));
        tx.setBookId       (rs.getInt   ("book_id"));
        tx.setBorrowDate   (rs.getDate  ("borrow_date"));
        tx.setDueDate      (rs.getDate  ("due_date"));
        tx.setReturnDate   (rs.getDate  ("return_date"));
        tx.setFine         (rs.getDouble("fine"));
        tx.setStatus       (rs.getString("status"));
        return tx;
    }

    /* ================================================================
     *                 ALIASES FOR LEGACY CALLERS
     * ================================================================ */
    public int getTotalBorrowCountForBookByPatrons(int bookId) throws SQLException {
        return getBorrowCountForBookByPatrons(bookId);
    }

    public int getBorrowCountByBookId(int bookId) throws SQLException {
        return getBorrowCountForBook(bookId);
    }
}
