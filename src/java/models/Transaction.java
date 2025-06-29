
package models;

import java.sql.Date;


public class Transaction {

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", userId=" + userId + ", bookId=" + bookId + ", borrowDate=" + borrowDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + ", fine=" + fine + ", status=" + status + '}';
    }

    public Transaction(String transactionId, int userId, int bookId, Date borrowDate, Date dueDate, Date returnDate, double fine, String status) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }
    
    
    public Transaction() {
        this.transactionId = "No Id";
        this.userId = 0;
        this.bookId = 0;
        this.borrowDate = null;
        this.dueDate = null;
        this.returnDate = null;
        this.fine = 0.0d;
        this.status = "No Status";
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private String bookTitle; // <-- أضف هذا الحقل

public String getBookTitle() {
    return bookTitle;
}

public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
}

    private String transactionId;
    private int userId;
    private int bookId;
    private java.sql.Date borrowDate;
    private java.sql.Date dueDate;
    private java.sql.Date returnDate;
    private double fine;
    private String status; // borrowed, returned
}
