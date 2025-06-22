package DAO;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import models.Fine;
import models.Transaction;


public class TransactionDAO {
    private Connection conn;

    public TransactionDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert transaction
    public void insertTransaction(Transaction tx) throws SQLException {
        String sql = "INSERT INTO transactions (transaction_id, user_id, book_id, borrow_date, due_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tx.getTransactionId());
            stmt.setInt(2, tx.getUserId());
            stmt.setInt(3, tx.getBookId());
            stmt.setDate(4, tx.getBorrowDate());
            stmt.setDate(5, tx.getDueDate());
            stmt.setString(6, tx.getStatus()); // should be "borrowed"
            stmt.executeUpdate();
        }
    }
    
    public int getTotalBorrowCountForBookByPatrons(int bookId) throws SQLException {
    String sql = "SELECT COUNT(*) FROM transactions t JOIN users u ON t.user_id = u.user_id WHERE t.book_id = ? AND u.role = 'patron'";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, bookId);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }
}


    // Block future fine for transaction
    public void blockFineForTransaction(String transactionId) {
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE transactions SET fine_blocked = 1 WHERE transaction_id = ?")) {
            stmt.setString(1, transactionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   // إرجاع كتاب وتوليد غرامة تلقائية عند التأخير
public void returnBook(String transactionId, Date returnDate, double fineRatePerDay) throws SQLException {
    Transaction tx = getTransactionById(transactionId);
    if (tx == null) throw new SQLException("Transaction not found");

    // 1. تحديث حالة الاستعارة
    String sql = "UPDATE transactions SET return_date = ?, status = 'returned' WHERE transaction_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setDate(1, returnDate);
        stmt.setString(2, transactionId);
        stmt.executeUpdate();
    }

    // 2. إذا متأخر، أنشئ الغرامة تلقائيًا إذا لم تكن موجودة
    if (tx.getDueDate() != null && returnDate != null && returnDate.after(tx.getDueDate())) {
        FineDAO fineDAO = new FineDAO(conn);
        boolean fineExists = fineDAO.existsFineForTransaction(transactionId);
        if (!fineExists) {
            long daysLate = ChronoUnit.DAYS.between(
                    tx.getDueDate().toLocalDate(),
                    returnDate.toLocalDate()
            );
            double fineAmount = daysLate * fineRatePerDay;
            if (fineAmount > 0) {
                Fine fine = new Fine();
                fine.setTransactionId(transactionId);
                fine.setFineAmount(fineAmount);
                fine.setPaymentStatus("unpaid");
                fine.setPaidDate(null);
                fineDAO.insertFine(fine);
            }
        }
    }
}


    // Get transaction by ID
    public Transaction getTransactionById(String transactionId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransaction(rs);
            }
        }
        return null;
    }

    // Get all transactions of a user
    public List<Transaction> getTransactionsByUser(int userId) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToTransaction(rs));
            }
        }
        return list;
    }

    // Get all overdue transactions
    public List<Transaction> getOverdueTransactions(Date today) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE due_date < ? AND return_date IS NULL";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, today);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToTransaction(rs));
            }
        }
        return list;
    }

    // Check if a book is currently borrowed
    public Transaction getActiveBorrowForBook(int bookId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE book_id = ? AND status = 'borrowed'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToTransaction(rs);
            }
        }
        return null;
    }

    // ✅ Get all transactions by status (e.g., borrowed)
    public List<Transaction> getAllTransactionsByStatus(String status) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE status = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToTransaction(rs));
            }
        }
        return list;
    }

    // Helper
    private Transaction mapResultSetToTransaction(ResultSet rs) throws SQLException {
        Transaction tx = new Transaction();
        tx.setTransactionId(rs.getString("transaction_id"));
        tx.setUserId(rs.getInt("user_id"));
        tx.setBookId(rs.getInt("book_id"));
        tx.setBorrowDate(rs.getDate("borrow_date"));
        tx.setDueDate(rs.getDate("due_date"));
        tx.setReturnDate(rs.getDate("return_date"));
        tx.setFine(rs.getDouble("fine"));
        tx.setStatus(rs.getString("status"));
        return tx;
    }

    // عدد مرات استعارة كل كتاب
    public int getBorrowCountForBook(int bookId) throws SQLException {
        String sql = "SELECT COUNT(*) AS borrow_count FROM transactions WHERE book_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("borrow_count");
            }
        }
        return 0;
    }

    public int getBorrowCountByBookId(int bookId) throws SQLException {
        return getBorrowCountForBook(bookId);
    }
}
