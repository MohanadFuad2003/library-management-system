
package Controller;

import DAO.BookDAO;
import DAO.FineDAO;
import DAO.NotificationDAO;
import DAO.SystemConfigDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Util.DBConnection;
import models.Fine;
import models.Transaction;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import models.Book;
import models.Notification;
import models.SystemConfig;
import models.User;


public class TransactionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAll";

        try (Connection conn = DBConnection.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            SystemConfigDAO configDAO = new SystemConfigDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            BookDAO bookDAO = new BookDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            if ("return".equals(action)) {
                String transactionId = request.getParameter("transactionId");
                Date returnDate = Date.valueOf(LocalDate.now());

                Transaction tx = transactionDAO.getTransactionById(transactionId);
                if (tx != null) {
                    SystemConfig config = configDAO.getCurrentConfig();
                    double fineRate = (config != null) ? config.getFineRate() : 1.0;

                    transactionDAO.returnBook(transactionId, returnDate, fineRate);

                    // اسم الكتاب
                    String bookTitle = "";
                    Book book = bookDAO.getBookById(tx.getBookId());
                    if (book != null) bookTitle = book.getTitle();

                    // 1. إشعار الباترون
                    String patronMsg = "تم إرجاع الكتاب '" + bookTitle + "' بنجاح.";
                    notificationDAO.insertNotification(
                        new Notification(0, tx.getUserId(), patronMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                    );

                    // هل عليه غرامة؟
                    long daysOverdue = ChronoUnit.DAYS.between(tx.getDueDate().toLocalDate(), returnDate.toLocalDate());
                    daysOverdue = Math.max(daysOverdue, 0);
                    double fineAmount = daysOverdue * fineRate;

                    // 2. إشعار جميع الإداريين واللايبراريان
                    String staffMsg = "قام المستخدم (" + tx.getUserId() + ") بإرجاع الكتاب '" + bookTitle + "'"
                        + (fineAmount > 0 ? " (متأخر - غرامة: " + fineAmount + " دينار)" : ".");
                    List<User> admins = userDAO.getUsersByRole("admin");
                    List<User> librarians = userDAO.getUsersByRole("librarian");

                    for (User admin : admins) {
                        notificationDAO.insertNotification(
                            new Notification(0, admin.getUserId(), staffMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                        );
                    }
                    for (User librarian : librarians) {
                        notificationDAO.insertNotification(
                            new Notification(0, librarian.getUserId(), staffMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                        );
                    }

                    request.setAttribute("message", "✅ Book returned successfully!" + (fineAmount > 0 ? " Fine: " + fineAmount + " JOD." : ""));
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("message", "❌ Transaction not found.");
                    request.setAttribute("status", "error");
                }

                response.sendRedirect("TransactionServlet?action=viewAll");

            } else if ("viewAll".equals(action)) {
                // عرض كل المعاملات إذا أردت
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "❌ Error: " + e.getMessage());
            request.setAttribute("status", "error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        try (Connection conn = DBConnection.getConnection()) {
            TransactionDAO transactionDAO = new TransactionDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            BookDAO bookDAO = new BookDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            if ("borrow".equals(action)) {
                HttpSession session = request.getSession();
                User currentUser = (User) session.getAttribute("currentUser");

                if (currentUser == null) {
                    request.setAttribute("message", "❌ You must be logged in to borrow books.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                if (!"patron".equalsIgnoreCase(currentUser.getRole())) {
                    request.setAttribute("message", "❌ Only patrons can borrow books.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                int bookId = Integer.parseInt(request.getParameter("bookId"));
                int borrowCount = transactionDAO.getTotalBorrowCountForBookByPatrons(bookId);

                if (borrowCount >= 3) {
                    request.setAttribute("message", "❌ This book has already been borrowed 3 times by patrons and cannot be borrowed again.");
                    request.setAttribute("status", "error");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }

                Date borrowDate = Date.valueOf(LocalDate.now());
                Date dueDate = Date.valueOf(LocalDate.now().plusDays(14));

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

                // اسم الكتاب
                String bookTitle = "";
                Book book = bookDAO.getBookById(bookId);
                if (book != null) bookTitle = book.getTitle();

                // 1. إشعار للباترون نفسه
                String patronMsg = "لقد استعرت الكتاب '" + bookTitle + "' بنجاح.";
                notificationDAO.insertNotification(
                    new Notification(0, currentUser.getUserId(), patronMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                );

                // 2. إشعار لجميع الإداريين واللايبرايريان
                String staffMsg = "قام المستخدم (" + currentUser.getUserId() + ") باستعارة الكتاب '" + bookTitle + "'.";
                List<User> admins = userDAO.getUsersByRole("admin");
                List<User> librarians = userDAO.getUsersByRole("librarian");

                for (User admin : admins) {
                    notificationDAO.insertNotification(
                        new Notification(0, admin.getUserId(), staffMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                    );
                }
                for (User librarian : librarians) {
                    notificationDAO.insertNotification(
                        new Notification(0, librarian.getUserId(), staffMsg, "unread", new java.sql.Timestamp(System.currentTimeMillis()))
                    );
                }

                request.setAttribute("message", "✅ Book borrowed successfully!");
                request.setAttribute("status", "success");
                request.getRequestDispatcher("some_confirmation_or_list_page.jsp").forward(request, response);

            } else {
                doGet(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "❌ Error: " + e.getMessage());
            request.setAttribute("status", "error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

