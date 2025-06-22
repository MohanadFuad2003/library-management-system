package Controller;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import DAO.BookDAO;
import DAO.NotificationDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import models.Book;
import Util.DBConnection;
import models.Transaction;
import java.sql.Date;
import java.time.LocalDate;
import models.Notification;

import models.User;


public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            BookDAO bookDAO = new BookDAO(conn);
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            if (!("admin".equalsIgnoreCase(currentUser.getRole()) || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }

            switch (action != null ? action : "viewAll") {
                case "viewAll": {
                    List<Book> books = bookDAO.getAllBooks();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book book : books) {
                        int count = transactionDAO.getBorrowCountByBookId(book.getBookId());
                        borrowCounts.put(book.getBookId(), count);
                    }
                    request.setAttribute("books", books);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("book_list.jsp").forward(request, response);
                    break;
                }
                case "borrowCountList": {
                    List<Book> books = bookDAO.getAllBooks();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book book : books) {
                        int count = transactionDAO.getBorrowCountByBookId(book.getBookId());
                        borrowCounts.put(book.getBookId(), count);
                    }
                    request.setAttribute("books", books);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("borrow_count_list.jsp").forward(request, response);
                    break;
                }
                case "search": {
                    String keyword = request.getParameter("keyword");
                    List<Book> books = (keyword != null && !keyword.trim().isEmpty())
                            ? bookDAO.searchBooksByKeyword(keyword.trim())
                            : bookDAO.getAllBooks();

                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book book : books) {
                        int count = transactionDAO.getBorrowCountByBookId(book.getBookId());
                        borrowCounts.put(book.getBookId(), count);
                    }
                    request.setAttribute("books", books);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("book_list.jsp").forward(request, response);
                    break;
                }
                case "filter": {
                    String status = request.getParameter("availability");
                    List<Book> books = (status == null || status.isEmpty())
                            ? bookDAO.getAllBooks()
                            : bookDAO.getBooksByAvailability(status);
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book book : books) {
                        int count = transactionDAO.getBorrowCountByBookId(book.getBookId());
                        borrowCounts.put(book.getBookId(), count);
                    }
                    request.setAttribute("books", books);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("book_list.jsp").forward(request, response);
                    break;
                }
                case "sort": {
                    String order = request.getParameter("sortOrder");
                    List<Book> books = "asc".equalsIgnoreCase(order)
                            ? bookDAO.getBooksSortedByYearAsc()
                            : bookDAO.getBooksSortedByYearDesc();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book book : books) {
                        int count = transactionDAO.getBorrowCountByBookId(book.getBookId());
                        borrowCounts.put(book.getBookId(), count);
                    }
                    request.setAttribute("books", books);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("book_list.jsp").forward(request, response);
                    break;
                }
                case "edit": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    Book book = bookDAO.getBookById(bookId);

                    if (book != null) {
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("edit_book.jsp").forward(request, response);
                    } else {
                        session.setAttribute("message", "❌ Book not found.");
                        session.setAttribute("status", "error");
                        response.sendRedirect("BookServlet?action=viewAll");
                    }
                    break;
                }
                case "delete": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    Book book = bookDAO.getBookById(bookId);
                    if (book == null) {
                        session.setAttribute("message", "❌ Book not found.");
                        session.setAttribute("status", "error");
                        response.sendRedirect("BookServlet?action=viewAll");
                        return;
                    }
                    bookDAO.deleteBook(bookId);

                    // إشعار الجميع بالحذف
                    List<User> admins = userDAO.getUsersByRole("admin");
                    List<User> librarians = userDAO.getUsersByRole("librarian");
                    List<User> patrons = userDAO.getUsersByRole("patron");
                    String notifMsg = "تم حذف الكتاب: '" + book.getTitle() + "' من المكتبة.";
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    for (User admin : admins) {
                        notificationDAO.insertNotification(new Notification(0, admin.getUserId(), notifMsg, "unread", now));
                    }
                    for (User librarian : librarians) {
                        notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), notifMsg, "unread", now));
                    }
                    for (User patron : patrons) {
                        notificationDAO.insertNotification(new Notification(0, patron.getUserId(), notifMsg, "unread", now));
                    }

                    session.setAttribute("message", "Book deleted successfully and notifications sent.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("BookServlet?action=viewAll");
                    break;
                }
                case "deleted": {
                    List<Book> deletedBooks = bookDAO.getDeletedBooks();
                    request.setAttribute("books", deletedBooks);
                    request.getRequestDispatcher("deleted_books.jsp").forward(request, response);
                    break;
                }
                case "restore": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    bookDAO.restoreBook(bookId);

                    // إشعار الجميع باستعادة كتاب
                    Book restoredBook = bookDAO.getBookById(bookId);
                    List<User> admins = userDAO.getUsersByRole("admin");
                    List<User> librarians = userDAO.getUsersByRole("librarian");
                    List<User> patrons = userDAO.getUsersByRole("patron");
                    String notifMsg = "تمت استعادة الكتاب: '" + (restoredBook != null ? restoredBook.getTitle() : "") + "' للمكتبة.";
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    for (User admin : admins) {
                        notificationDAO.insertNotification(new Notification(0, admin.getUserId(), notifMsg, "unread", now));
                    }
                    for (User librarian : librarians) {
                        notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), notifMsg, "unread", now));
                    }
                    for (User patron : patrons) {
                        notificationDAO.insertNotification(new Notification(0, patron.getUserId(), notifMsg, "unread", now));
                    }

                    session.setAttribute("message", "Book restored successfully and notifications sent.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("BookServlet?action=deleted");
                    break;
                }
                case "deletePermanent": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    bookDAO.deleteBookPermanently(bookId);
                    session.setAttribute("message", "Book permanently deleted.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("BookServlet?action=deleted");
                    break;
                }
                default:
                    response.sendRedirect("BookServlet?action=viewAll");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("book_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        try (Connection conn = DBConnection.getConnection()) {
            BookDAO bookDAO = new BookDAO(conn);
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            if (!("admin".equalsIgnoreCase(currentUser.getRole()) || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }

            if ("borrow".equals(action)) {
                if (!"patron".equalsIgnoreCase(currentUser.getRole())) {
                    request.setAttribute("message", "❌ You must be logged in as a patron to borrow books.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                int bookId = Integer.parseInt(request.getParameter("bookId"));
                int borrowCount = transactionDAO.getTotalBorrowCountForBookByPatrons(bookId);
                if (borrowCount >= 3) {
                    request.setAttribute("message", "❌ This book has already been borrowed 3 times and cannot be borrowed now.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("book_list.jsp").forward(request, response);
                    return;
                }

                Date borrowDate = Date.valueOf(java.time.LocalDate.now());
                Date dueDate = Date.valueOf(java.time.LocalDate.now().plusDays(14));

                Transaction newTransaction = new Transaction();
                newTransaction.setTransactionId(java.util.UUID.randomUUID().toString());
                newTransaction.setUserId(currentUser.getUserId());
                newTransaction.setBookId(bookId);
                newTransaction.setBorrowDate(borrowDate);
                newTransaction.setDueDate(dueDate);
                newTransaction.setReturnDate(null);
                newTransaction.setFine(0);
                newTransaction.setStatus("borrowed");

                transactionDAO.insertTransaction(newTransaction);

                // يمكن هنا إرسال إشعار للمستخدم أو الإداريين إذا أردت

                request.setAttribute("message", "✅ Book borrowed successfully!");
                request.setAttribute("status", "success");
                request.getRequestDispatcher("book_list.jsp").forward(request, response);
            }

            else if ("add".equals(action)) {
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("isbn");
                String genre = request.getParameter("genre").trim();
                int year = Integer.parseInt(request.getParameter("publicationYear"));
                String availability = request.getParameter("availability").trim();
                String description = request.getParameter("description");

                if (bookDAO.getBookByISBN(isbn) != null) {
                    request.setAttribute("message", "❌ Book with this ISBN already exists.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("add_book.jsp").forward(request, response);
                    return;
                }

                List<Book> existingBooks = bookDAO.searchBooksByTitle(title);
                if (!existingBooks.isEmpty()) {
                    request.setAttribute("message", "❌ Book with this title already exists.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("add_book.jsp").forward(request, response);
                    return;
                }

                Book book = new Book();
                book.setTitle(title);
                book.setAuthor(author);
                book.setIsbn(isbn);
                book.setGenre(genre);
                book.setPublicationYear(year);
                book.setAvailability(availability);
                book.setDescription(description);

                bookDAO.insertBook(book);

                // إشعار الجميع بالإضافة
                List<User> admins = userDAO.getUsersByRole("admin");
                List<User> librarians = userDAO.getUsersByRole("librarian");
                List<User> patrons = userDAO.getUsersByRole("patron");
                String notifMsg = "تمت إضافة كتاب جديد: '" + title + "' للمكتبة.";
                Timestamp now = new Timestamp(System.currentTimeMillis());
                for (User admin : admins) {
                    notificationDAO.insertNotification(new Notification(0, admin.getUserId(), notifMsg, "unread", now));
                }
                for (User librarian : librarians) {
                    notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), notifMsg, "unread", now));
                }
                for (User patron : patrons) {
                    notificationDAO.insertNotification(new Notification(0, patron.getUserId(), notifMsg, "unread", now));
                }

                request.setAttribute("message", "✅ Book added successfully and notifications sent!");
                request.setAttribute("status", "success");
                request.getRequestDispatcher("add_book.jsp").forward(request, response);
            }

            else if ("edit".equals(action)) {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                String title = request.getParameter("title");
                String author = request.getParameter("author");
                String isbn = request.getParameter("isbn");
                String genre = request.getParameter("genre");
                int year = Integer.parseInt(request.getParameter("publicationYear"));
                String availability = request.getParameter("availability");
                String description = request.getParameter("description");

                Book existingByISBN = bookDAO.getBookByISBN(isbn);
                if (existingByISBN != null && existingByISBN.getBookId() != bookId) {
                    request.setAttribute("message", "❌ Another book with this ISBN already exists.");
                    request.setAttribute("status", "error");
                    Book book = new Book(bookId, title, author, isbn, genre, year, availability, description);
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("edit_book.jsp").forward(request, response);
                    return;
                }

                List<Book> allBooks = bookDAO.getAllBooks();
                for (Book b : allBooks) {
                    if (b.getTitle().equalsIgnoreCase(title) && b.getBookId() != bookId) {
                        request.setAttribute("message", "❌ Another book with this title already exists.");
                        request.setAttribute("status", "error");
                        Book book = new Book(bookId, title, author, isbn, genre, year, availability, description);
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("edit_book.jsp").forward(request, response);
                        return;
                    }
                }

                Book book = new Book(bookId, title, author, isbn, genre, year, availability, description);
                bookDAO.updateBook(book);

                // إشعار الجميع بالتعديل
                List<User> admins = userDAO.getUsersByRole("admin");
                List<User> librarians = userDAO.getUsersByRole("librarian");
                List<User> patrons = userDAO.getUsersByRole("patron");
                String notifMsg = "تم تعديل بيانات الكتاب: '" + title + "'.";
                Timestamp now = new Timestamp(System.currentTimeMillis());
                for (User admin : admins) {
                    notificationDAO.insertNotification(new Notification(0, admin.getUserId(), notifMsg, "unread", now));
                }
                for (User librarian : librarians) {
                    notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), notifMsg, "unread", now));
                }
                for (User patron : patrons) {
                    notificationDAO.insertNotification(new Notification(0, patron.getUserId(), notifMsg, "unread", now));
                }

                session.setAttribute("message", "✅ Book updated successfully and notifications sent!");
                session.setAttribute("status", "success");
                response.sendRedirect("BookServlet?action=viewAll");
            }
            else {
                response.sendRedirect("BookServlet?action=viewAll");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "❌ Failed to process request: " + e.getMessage());
            request.setAttribute("status", "error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
