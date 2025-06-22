package models;

import java.sql.Date;

public class Fine {

    private int fineId;
    private String transactionId;
    private double fineAmount;
    private String paymentStatus; // paid, unpaid
    private Date paidDate;

    // بيانات مرتبطة (للعرض فقط)
    private int userId;
    private String userFirstName;
    private String userLastName;
    private String userEmail;       // جديد
    private String userPhone;       // جديد
    private int bookId;
    private String bookTitle;
    private Date borrowDate;        // جديد
    private Date dueDate;           // جديد

    public Fine() {
        this.fineId = 0;
        this.transactionId = "Empty";
        this.fineAmount = 0.0d;
        this.paymentStatus = "Empty";
        this.paidDate = null;
        this.userId = 0;
        this.userFirstName = "";
        this.userLastName = "";
        this.userEmail = "";
        this.userPhone = "";
        this.bookId = 0;
        this.bookTitle = "";
        this.borrowDate = null;
        this.dueDate = null;
    }

    public Fine(int fineId, String transactionId, double fineAmount, String paymentStatus, Date paidDate) {
        this.fineId = fineId;
        this.transactionId = transactionId;
        this.fineAmount = fineAmount;
        this.paymentStatus = paymentStatus;
        this.paidDate = paidDate;
    }

    // ... جميع Getter و Setter للخصائص ...

    public int getFineId() { return fineId; }
    public void setFineId(int fineId) { this.fineId = fineId; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public double getFineAmount() { return fineAmount; }
    public void setFineAmount(double fineAmount) { this.fineAmount = fineAmount; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public Date getPaidDate() { return paidDate; }
    public void setPaidDate(Date paidDate) { this.paidDate = paidDate; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUserFirstName() { return userFirstName; }
    public void setUserFirstName(String userFirstName) { this.userFirstName = userFirstName; }

    public String getUserLastName() { return userLastName; }
    public void setUserLastName(String userLastName) { this.userLastName = userLastName; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return "Fine{" +
                "fineId=" + fineId +
                ", transactionId='" + transactionId + '\'' +
                ", fineAmount=" + fineAmount +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paidDate=" + paidDate +
                ", userId=" + userId +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
