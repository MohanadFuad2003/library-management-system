
package models;

import java.sql.Timestamp;

public class SharedBook {

    public SharedBook(int sharedId, int userId, int bookId, Timestamp sharedDate) {
        this.sharedId = sharedId;
        this.userId = userId;
        this.bookId = bookId;
        this.sharedDate = sharedDate;
    }
    
      public SharedBook () {
        this.sharedId = 0;
        this.userId = 0;
        this.bookId = 0;
        this.sharedDate = null;
    }


    @Override
    public String toString() {
        return "SharedBook{" + "sharedId=" + sharedId + ", userId=" + userId + ", bookId=" + bookId + ", sharedDate=" + sharedDate + '}';
    }

    public int getSharedId() {
        return sharedId;
    }

    public void setSharedId(int sharedId) {
        this.sharedId = sharedId;
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

    public Timestamp getSharedDate() {
        return sharedDate;
    }

    public void setSharedDate(Timestamp sharedDate) {
        this.sharedDate = sharedDate;
    }
    private int sharedId;
    private int userId;
    private int bookId;
    private java.sql.Timestamp sharedDate;
}
