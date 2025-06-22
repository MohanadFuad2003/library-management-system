
package models;

import java.sql.Timestamp;


public class Review {

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", userId=" + userId + ", bookId=" + bookId + ", rating=" + rating + ", comment=" + comment + ", reviewDate=" + reviewDate + '}';
    }

    public Review() {
           this.reviewId = 0;
        this.userId = 0;
        this.bookId = 0;
        this.rating = 0;
        this.comment = "Empty";
        this.reviewDate = null;
    }

    public Review(int reviewId, int userId, int bookId, int rating, String comment, Timestamp reviewDate) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }
    private int reviewId;
    private int userId;
    private int bookId;
    private int rating; // 1–5
    private String comment;
    private java.sql.Timestamp reviewDate;
}
