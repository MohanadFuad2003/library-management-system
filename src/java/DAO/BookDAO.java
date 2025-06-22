
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Book;

public class BookDAO {
    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    // Insert a new book
    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, isbn, genre, publication_year, availability, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setString(6, book.getAvailability());
            stmt.setString(7, book.getDescription());
            stmt.executeUpdate();
        }
    }
    
    
    public void deleteBookPermanently(int bookId) throws SQLException {
    String sql = "DELETE FROM deleted_books_archive WHERE book_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, bookId);
        stmt.executeUpdate();
    }
}


    // Search books by keyword in title or isbn
    public List<Book> searchBooksByKeyword(String keyword) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(isbn) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword.toLowerCase() + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToBook(rs));
            }
        }
        return list;
    }

    // Get book by ID
    public Book getBookById(int bookId) throws SQLException {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBook(rs);
            }
        }
        return null;
    }

    // Get all books
    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    // Update book
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title=?, author=?, isbn=?, genre=?, publication_year=?, availability=?, description=? WHERE book_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setString(6, book.getAvailability());
            stmt.setString(7, book.getDescription());
            stmt.setInt(8, book.getBookId());
            stmt.executeUpdate();
        }
    }

    // Delete book: archive data then delete permanently
    public void deleteBook(int bookId) throws SQLException {
        // Get book details first
        Book book = getBookById(bookId);
        if (book == null) {
            throw new SQLException("Book not found with id: " + bookId);
        }

        // Insert into deleted_books_archive
        String insertSql = "INSERT INTO deleted_books_archive (book_id, title, author, isbn, genre, publication_year, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setInt(1, book.getBookId());
            insertStmt.setString(2, book.getTitle());
            insertStmt.setString(3, book.getAuthor());
            insertStmt.setString(4, book.getIsbn());
            insertStmt.setString(5, book.getGenre());
            insertStmt.setInt(6, book.getPublicationYear());
            insertStmt.setString(7, book.getDescription());
            insertStmt.executeUpdate();
        }

        // Delete from books table
        String deleteSql = "DELETE FROM books WHERE book_id = ?";
        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, bookId);
            deleteStmt.executeUpdate();
        }
    }

    // Search books by title
    public List<Book> searchBooksByTitle(String title) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + title.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    // Search books by author
    public List<Book> searchBooksByAuthor(String author) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(author) LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + author.toLowerCase() + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    // Get books sorted by publication year descending
    public List<Book> getBooksSortedByYear() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY publication_year DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    // Get book by ISBN
    public Book getBookByISBN(String isbn) throws SQLException {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBook(rs);
            }
        }
        return null;
    }

    // Get deleted books from archive
    public List<Book> getDeletedBooks() throws SQLException {
        List<Book> deletedBooks = new ArrayList<>();
        String sql = "SELECT book_id, title, author, isbn, genre, publication_year, description FROM deleted_books_archive ORDER BY deleted_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getInt("book_id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setIsbn(rs.getString("isbn"));
                book.setGenre(rs.getString("genre"));
                book.setPublicationYear(rs.getInt("publication_year"));
                book.setDescription(rs.getString("description"));
                book.setAvailability("deleted"); // mark as deleted
                deletedBooks.add(book);
            }
        }
        return deletedBooks;
    }

    // Helper: convert ResultSet to Book object
    private Book mapResultSetToBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setIsbn(rs.getString("isbn"));
        book.setGenre(rs.getString("genre"));
        book.setPublicationYear(rs.getInt("publication_year"));
        book.setAvailability(rs.getString("availability"));
        book.setDescription(rs.getString("description"));
        return book;
    }

    // Get books by availability status
    public List<Book> getBooksByAvailability(String status) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(availability) = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToBook(rs));
            }
        }
        return list;
    }

    public List<Book> getBooksSortedByYearAsc() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY publication_year ASC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }

    public List<Book> getBooksSortedByYearDesc() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY publication_year DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(mapResultSetToBook(rs));
            }
        }
        return books;
    }
    
    public void restoreBook(int bookId) throws SQLException {
    // 1. جلب بيانات الكتاب من جدول deleted_books_archive
    String selectSql = "SELECT * FROM deleted_books_archive WHERE book_id = ?";
    Book book = null;
    try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
        selectStmt.setInt(1, bookId);
        ResultSet rs = selectStmt.executeQuery();
        if (rs.next()) {
            book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setIsbn(rs.getString("isbn"));
            book.setGenre(rs.getString("genre"));
            book.setPublicationYear(rs.getInt("publication_year"));
            book.setDescription(rs.getString("description"));
            book.setAvailability("available"); // افتراضاً يعاد كمتاح
        } else {
            throw new SQLException("Book not found in archive with id: " + bookId);
        }
    }

    // 2. إدخال الكتاب في جدول books
    String insertSql = "INSERT INTO books (book_id, title, author, isbn, genre, publication_year, availability, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        insertStmt.setInt(1, book.getBookId());
        insertStmt.setString(2, book.getTitle());
        insertStmt.setString(3, book.getAuthor());
        insertStmt.setString(4, book.getIsbn());
        insertStmt.setString(5, book.getGenre());
        insertStmt.setInt(6, book.getPublicationYear());
        insertStmt.setString(7, book.getAvailability());
        insertStmt.setString(8, book.getDescription());
        insertStmt.executeUpdate();
    }

    // 3. حذف الكتاب من جدول deleted_books_archive
    String deleteSql = "DELETE FROM deleted_books_archive WHERE book_id = ?";
    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
        deleteStmt.setInt(1, bookId);
        deleteStmt.executeUpdate();
    }
}


}
