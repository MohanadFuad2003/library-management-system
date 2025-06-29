package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import DAO.BookDAO;
import DAO.NotificationDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Util.DBConnection;
import models.Book;
import models.Notification;
import models.Transaction;
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

            if ("patron".equalsIgnoreCase(currentUser.getRole())) {

                if ("confirmBorrow".equalsIgnoreCase(action)) {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    Book book = bookDAO.getBookById(bookId);

                    if (book == null) {
                        request.setAttribute("message", "❌ Book not found.");
                        request.setAttribute("status", "error");
                        request.getRequestDispatcher("/available_books.jsp").forward(request, response);
                        return;
                    }

                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/borrow_confirm.jsp").forward(request, response);
                    return;
                }

                List<Book> availableBooks;

                if ("search".equalsIgnoreCase(action)) {
                    String keyword = request.getParameter("keyword");
                    availableBooks = (keyword != null && !keyword.trim().isEmpty())
                            ? bookDAO.searchBooksByKeyword(keyword.trim())
                            : bookDAO.getBooksByAvailability("available");
                } else {
                    availableBooks = bookDAO.getBooksByAvailability("available");
                }

                request.setAttribute("availableBooks", availableBooks);

                Object msg = session.getAttribute("message");
                Object sts = session.getAttribute("status");
                if (msg != null) {
                    request.setAttribute("message", msg);
                    request.setAttribute("status", sts);
                    session.removeAttribute("message");
                    session.removeAttribute("status");
                }

                request.getRequestDispatcher("/available_books.jsp").forward(request, response);
                return;
            }

            if (!("admin".equalsIgnoreCase(currentUser.getRole()) || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                return;
            }

            switch (action != null ? action : "viewAll") {

                case "viewAll": {
                    List<Book> bookList = bookDAO.getAllBooks();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book b : bookList) {
                        borrowCounts.put(b.getBookId(), transactionDAO.getBorrowCountByBookId(b.getBookId()));
                    }
                    request.setAttribute("books", bookList);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("/book_list.jsp").forward(request, response);
                    break;
                }

                case "search": {
                    String kw = request.getParameter("keyword");
                    List<Book> bookList = (kw != null && !kw.trim().isEmpty())
                            ? bookDAO.searchBooksByKeyword(kw.trim())
                            : bookDAO.getAllBooks();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book b : bookList) {
                        borrowCounts.put(b.getBookId(), transactionDAO.getBorrowCountByBookId(b.getBookId()));
                    }
                    request.setAttribute("books", bookList);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("/book_list.jsp").forward(request, response);
                    break;
                }

                case "filter": {
                    String st = request.getParameter("availability");
                    List<Book> bookList = (st == null || st.isEmpty())
                            ? bookDAO.getAllBooks()
                            : bookDAO.getBooksByAvailability(st);
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book b : bookList) {
                        borrowCounts.put(b.getBookId(), transactionDAO.getBorrowCountByBookId(b.getBookId()));
                    }
                    request.setAttribute("books", bookList);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("/book_list.jsp").forward(request, response);
                    break;
                }

                case "sort": {
                    String order = request.getParameter("sortOrder");
                    List<Book> bookList = "asc".equalsIgnoreCase(order)
                            ? bookDAO.getBooksSortedByYearAsc()
                            : bookDAO.getBooksSortedByYearDesc();
                    Map<Integer, Integer> borrowCounts = new HashMap<>();
                    for (Book b : bookList) {
                        borrowCounts.put(b.getBookId(), transactionDAO.getBorrowCountByBookId(b.getBookId()));
                    }
                    request.setAttribute("books", bookList);
                    request.setAttribute("borrowCounts", borrowCounts);
                    request.getRequestDispatcher("/book_list.jsp").forward(request, response);
                    break;
                }

                case "edit": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    Book book = bookDAO.getBookById(bookId);
                    if (book != null) {
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("/edit_book.jsp").forward(request, response);
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
                    notifyAllRoles(notificationDAO, userDAO, "تم حذف الكتاب: '" + book.getTitle() + "'.");
                    session.setAttribute("message", "Book deleted.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("BookServlet?action=viewAll");
                    break;
                }

                case "deleted": {
                    List<Book> del = bookDAO.getDeletedBooks();
                    request.setAttribute("books", del);
                    request.getRequestDispatcher("/deleted_books.jsp").forward(request, response);
                    break;
                }

                case "restore": {
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    bookDAO.restoreBook(bookId);
                    Book res = bookDAO.getBookById(bookId);
                    notifyAllRoles(notificationDAO, userDAO,
                            "تمت استعادة الكتاب: '" + (res != null ? res.getTitle() : "") + "'.");
                    session.setAttribute("message", "Book restored.");
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

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("error", "Error: " + ex.getMessage());
            request.getRequestDispatcher("/book_list.jsp").forward(request, response);
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

            if ("borrow".equalsIgnoreCase(action)) {

                if (!"patron".equalsIgnoreCase(currentUser.getRole())) {
                    request.setAttribute("message", "❌ Only patrons can borrow.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }

                int bookId = Integer.parseInt(request.getParameter("bookId"));
                int activeBorrows = transactionDAO.getActiveBorrowCountForUser(currentUser.getUserId());

                if (activeBorrows >= 3) {
                    session.setAttribute("message", "❌ You can borrow up to 3 books only.");
                    session.setAttribute("status", "error");
                    response.sendRedirect("BookServlet");
                    return;
                }

                Date borrowDate = Date.valueOf(LocalDate.now());
                Date dueDate = Date.valueOf(LocalDate.now().plusDays(14));
                String transactionId = generateTransactionId(transactionDAO);

                Transaction tx = new Transaction();
                tx.setTransactionId(transactionId);
                tx.setUserId(currentUser.getUserId());
                tx.setBookId(bookId);
                tx.setBorrowDate(borrowDate);
                tx.setDueDate(dueDate);
                tx.setFine(0);
                tx.setStatus("borrowed");

                transactionDAO.insertTransaction(tx);

                notificationDAO.insertNotification(new Notification(
                        0,
                        currentUser.getUserId(),
                        "📚 تم استعارة كتاب جديد. Transaction ID: " + transactionId,
                        "unread",
                        new Timestamp(System.currentTimeMillis())
                ));

                session.setAttribute("message", "✅ Book borrowed successfully!");
                session.setAttribute("status", "success");
                response.sendRedirect("BookServlet");
                return;
            }

            // Admin/Librarian POST handling (add/edit)
            // Keep as in previous version...
               
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

        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "❌ Error: " + ex.getMessage());
            request.setAttribute("status", "error");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    private void notifyAllRoles(NotificationDAO nDao, UserDAO uDao, String msg) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (User u : uDao.getUsersByRole("admin")) nDao.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
        for (User u : uDao.getUsersByRole("librarian")) nDao.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
        for (User u : uDao.getUsersByRole("patron")) nDao.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
    }

    private String generateTransactionId(TransactionDAO dao) throws Exception {
        String prefix = "BT";
        Random random = new Random();
        String id;
        do {
            id = prefix + String.format("%06d", random.nextInt(1000000));
        } while (dao.getTransactionById(id) != null);
        return id;
    }
}
