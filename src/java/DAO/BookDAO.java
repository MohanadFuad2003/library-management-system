package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Book;

public class BookDAO {

    /* ==========================================================
     *                       Constructor
     * ========================================================== */
    private final Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    /* ==========================================================
     *                       CRUD METHODS
     * ========================================================== */

    /** Insert a new Book into books table */
    public void insertBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (title, author, isbn, genre, publication_year, " +
                     "availability, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    /** Update an existing Book */
    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE books SET title=?, author=?, isbn=?, genre=?, " +
                     "publication_year=?, availability=?, description=? WHERE book_id=?";
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

    /** Archive a book then remove it from books table */
    public void deleteBook(int bookId) throws SQLException {
        Book book = getBookById(bookId);
        if (book == null) throw new SQLException("Book not found with id: " + bookId);

        String insert = "INSERT INTO deleted_books_archive (book_id, title, author, isbn, " +
                        "genre, publication_year, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement in = conn.prepareStatement(insert)) {
            in.setInt(1, book.getBookId());
            in.setString(2, book.getTitle());
            in.setString(3, book.getAuthor());
            in.setString(4, book.getIsbn());
            in.setString(5, book.getGenre());
            in.setInt(6, book.getPublicationYear());
            in.setString(7, book.getDescription());
            in.executeUpdate();
        }

        try (PreparedStatement del =
                     conn.prepareStatement("DELETE FROM books WHERE book_id = ?")) {
            del.setInt(1, bookId);
            del.executeUpdate();
        }
    }

    /** Permanently delete a book from archive */
    public void deleteBookPermanently(int bookId) throws SQLException {
        try (PreparedStatement stmt =
                     conn.prepareStatement("DELETE FROM deleted_books_archive WHERE book_id = ?")) {
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
        }
    }

    /** Restore a book from archive back to books table */
    public void restoreBook(int bookId) throws SQLException {
        String select = "SELECT * FROM deleted_books_archive WHERE book_id = ?";
        Book book;
        try (PreparedStatement st = conn.prepareStatement(select)) {
            st.setInt(1, bookId);
            try (ResultSet rs = st.executeQuery()) {
                if (!rs.next())
                    throw new SQLException("Book not found in archive with id: " + bookId);
                book = mapResultSetToBook(rs);
                book.setAvailability("available");
            }
        }

        String insert = "INSERT INTO books (book_id, title, author, isbn, genre, " +
                        "publication_year, availability, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement in = conn.prepareStatement(insert)) {
            in.setInt(1, book.getBookId());
            in.setString(2, book.getTitle());
            in.setString(3, book.getAuthor());
            in.setString(4, book.getIsbn());
            in.setString(5, book.getGenre());
            in.setInt(6, book.getPublicationYear());
            in.setString(7, book.getAvailability());
            in.setString(8, book.getDescription());
            in.executeUpdate();
        }

        try (PreparedStatement del =
                     conn.prepareStatement("DELETE FROM deleted_books_archive WHERE book_id = ?")) {
            del.setInt(1, bookId);
            del.executeUpdate();
        }
    }

    /* ==========================================================
     *                       FETCH METHODS
     * ========================================================== */

    /** Get book by primary key */
    public Book getBookById(int bookId) throws SQLException {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToBook(rs) : null;
            }
        }
    }

    /** Get book by ISBN */
    public Book getBookByISBN(String isbn) throws SQLException {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapResultSetToBook(rs) : null;
            }
        }
    }

    /** Return every row in books table */
    public List<Book> getAllBooks() throws SQLException {
        List<Book> list = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapResultSetToBook(rs));
        }
        return list;
    }

    /** Get books filtered by availability */
    public List<Book> getBooksByAvailability(String status) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(availability)=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.toLowerCase());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToBook(rs));
            }
        }
        return list;
    }

    /** Search by keyword in title, author, or ISBN (case-insensitive) */
    public List<Book> searchBooksByKeyword(String keyword) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(isbn) LIKE ?";
        String pattern = "%" + keyword.toLowerCase() + "%";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            stmt.setString(3, pattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToBook(rs));
            }
        }
        return list;
    }

    /** Search by title only */
    public List<Book> searchBooksByTitle(String title) throws SQLException {
        return searchBySingleColumn("title", title);
    }

    /** Search by author only */
    public List<Book> searchBooksByAuthor(String author) throws SQLException {
        return searchBySingleColumn("author", author);
    }

    /* ==========================================================
     *                     SORTING METHODS
     * ========================================================== */

    /** Books sorted by publication year ASC */
    public List<Book> getBooksSortedByYearAsc() throws SQLException {
        return getBooksSortedByYear(true);
    }

    /** Books sorted by publication year DESC */
    public List<Book> getBooksSortedByYearDesc() throws SQLException {
        return getBooksSortedByYear(false);
    }

    /** Books sorted by publication year (helper) */
    private List<Book> getBooksSortedByYear(boolean asc) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books ORDER BY publication_year " + (asc ? "ASC" : "DESC");
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) list.add(mapResultSetToBook(rs));
        }
        return list;
    }

    /* ==========================================================
     *               DELETED BOOKS (ARCHIVE) METHODS
     * ========================================================== */

    /** Fetch deleted books from archive table */
    public List<Book> getDeletedBooks() throws SQLException {
        List<Book> deleted = new ArrayList<>();
        String sql = "SELECT book_id, title, author, isbn, genre, publication_year, description " +
                     "FROM deleted_books_archive ORDER BY deleted_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Book b = new Book();
                b.setBookId(rs.getInt("book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setIsbn(rs.getString("isbn"));
                b.setGenre(rs.getString("genre"));
                b.setPublicationYear(rs.getInt("publication_year"));
                b.setDescription(rs.getString("description"));
                b.setAvailability("deleted");
                deleted.add(b);
            }
        }
        return deleted;
    }

    /* ==========================================================
     *                     PRIVATE UTILITIES
     * ========================================================== */

    /** Map current ResultSet row to Book object */
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

    /** Generic search by single column (title, author) */
    private List<Book> searchBySingleColumn(String column, String value) throws SQLException {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(" + column + ") LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + value.toLowerCase() + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) list.add(mapResultSetToBook(rs));
            }
        }
        return list;
    }
}
