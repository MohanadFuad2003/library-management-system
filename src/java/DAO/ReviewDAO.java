
package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Review;

public class ReviewDAO {
    private Connection conn;

    public ReviewDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ 1. إضافة تقييم جديد
    public void insertReview(Review review) throws SQLException {
        String sql = "INSERT INTO reviews (user_id, book_id, rating, comment, review_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getBookId());
            stmt.setInt(3, review.getRating());
            stmt.setString(4, review.getComment());
            stmt.setTimestamp(5, review.getReviewDate());
            stmt.executeUpdate();
        }
    }

    // ✅ 2. عرض كل التقييمات الخاصة بكتاب
    public List<Review> getReviewsByBook(int bookId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE book_id = ? ORDER BY review_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        }
        return reviews;
    }

    // ✅ 3. متوسط تقييم كتاب
    public double getRatingAverageForBook(int bookId) throws SQLException {
        String sql = "SELECT AVG(rating) AS avg_rating FROM reviews WHERE book_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_rating");
            }
        }
        return 0.0;
    }

    // ✅ دالة تحويل نتيجة الاستعلام إلى كائن Review
    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setReviewId(rs.getInt("review_id"));
        review.setUserId(rs.getInt("user_id"));
        review.setBookId(rs.getInt("book_id"));
        review.setRating(rs.getInt("rating"));
        review.setComment(rs.getString("comment"));
        review.setReviewDate(rs.getTimestamp("review_date"));
        return review;
    }
}
