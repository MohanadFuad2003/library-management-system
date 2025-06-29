package Controller;

import DAO.*;
import Util.DBConnection;
import models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Handles borrowing & returning transactions.
 */
public class TransactionServlet extends HttpServlet {

    /* =============================================================
     *                               GET
     * ============================================================= */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String  action      = Optional.ofNullable(req.getParameter("action")).orElse("");
        HttpSession session = req.getSession();
        User    currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {

            TransactionDAO  txDAO   = new TransactionDAO(conn);
            BookDAO         bookDAO = new BookDAO(conn);
            UserDAO         userDAO = new UserDAO(conn);
            NotificationDAO nDAO    = new NotificationDAO(conn);
            SystemConfigDAO confDAO = new SystemConfigDAO(conn);

            /* ---------- 1) Patron borrowing history ---------- */
            if ("history".equalsIgnoreCase(action)
                    && "patron".equalsIgnoreCase(currentUser.getRole())) {

                req.setAttribute("history",
                        txDAO.getTransactionsByUser(currentUser.getUserId()));
                req.getRequestDispatcher("/borrow_history.jsp").forward(req, resp);
                return;
            }

            /* ---------- 2) Immediate return from list (staff) ---------- */
            if ("return".equalsIgnoreCase(action)
                    && (isAdmin(currentUser) || isLibrarian(currentUser))) {

                String       txId = req.getParameter("transactionId");
                Transaction  tx   = txDAO.getTransactionById(txId);

                if (tx == null) {
                    setSessionMsg(session, "❌ Transaction not found.", "error");
                    resp.sendRedirect("TransactionServlet?action=viewAll");
                    return;
                }

                Date   today    = Date.valueOf(LocalDate.now());
                double fineRate = Optional.ofNullable(confDAO.getCurrentConfig())
                                          .map(SystemConfig::getFineRate).orElse(1.0);

                txDAO.returnBook(txId, today, fineRate);

                Book returned = bookDAO.getBookById(tx.getBookId());
                if (returned != null) {                               // make the book available again
                    returned.setAvailability("available");
                    bookDAO.updateBook(returned);
                }

                long   lateDays = Math.max(0,
                        ChronoUnit.DAYS.between(tx.getDueDate().toLocalDate(), today.toLocalDate()));
                double fineAmt  = lateDays * fineRate;
                String title    = returned != null ? returned.getTitle() : "";

                notifyPatron(nDAO, tx.getUserId(), title, fineAmt);
                notifyStaff (nDAO, userDAO, title, tx.getUserId(), fineAmt);

                setSessionMsg(session, "✅ Book returned successfully!", "success");
                resp.sendRedirect("TransactionServlet?action=viewAll");
                return;
            }

            /* ---------- 3) All currently-borrowed items (staff) ---------- */
            if ("viewAll".equalsIgnoreCase(action)) {

                /* access-control */
                if (!(isAdmin(currentUser) || isLibrarian(currentUser))) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }

                /* fetch & forward */
                req.setAttribute("transactions",
                        txDAO.getAllTransactionsByStatus("borrowed"));
                req.getRequestDispatcher("/transactions_list.jsp").forward(req, resp);
                return;
            }

            /* ---------- default GET ---------- */
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("message", "❌ Error: " + ex.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    /* =============================================================
     *                               POST
     * ============================================================= */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String       action      = Optional.ofNullable(req.getParameter("action")).orElse("");
        HttpSession  session     = req.getSession();
        User         currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {

            TransactionDAO  txDAO   = new TransactionDAO(conn);
            BookDAO         bookDAO = new BookDAO(conn);
            NotificationDAO nDAO    = new NotificationDAO(conn);
            UserDAO         userDAO = new UserDAO(conn);
            SystemConfigDAO confDAO = new SystemConfigDAO(conn);

            /* ---------- A) PROCESS RETURN (from return_book.jsp) ---------- */
            if ("return".equalsIgnoreCase(action)
                    && (isAdmin(currentUser) || isLibrarian(currentUser))) {

                String txId  = req.getParameter("transactionId");
                Date   rDate = Date.valueOf(req.getParameter("returnDate"));    // yyyy-MM-dd

                Transaction tx = txDAO.getTransactionById(txId);
                if (tx == null) {
                    forwardError(req, resp, "❌ Transaction not found.");
                    return;
                }
                if ("returned".equalsIgnoreCase(tx.getStatus())) {
                    forwardError(req, resp, "❌ Book already returned.");
                    return;
                }

                double fineRate = Optional.ofNullable(confDAO.getCurrentConfig())
                                          .map(SystemConfig::getFineRate).orElse(1.0);

                txDAO.returnBook(txId, rDate, fineRate);

                Book book = bookDAO.getBookById(tx.getBookId());
                if (book != null) {
                    book.setAvailability("available");
                    bookDAO.updateBook(book);
                }

                long   lateDays = Math.max(0,
                        ChronoUnit.DAYS.between(tx.getDueDate().toLocalDate(), rDate.toLocalDate()));
                double fineAmt  = lateDays * fineRate;
                String title    = book != null ? book.getTitle() : "";

                notifyPatron(nDAO, tx.getUserId(), title, fineAmt);
                notifyStaff (nDAO, userDAO, title, tx.getUserId(), fineAmt);

                req.setAttribute("success",
                        "Book returned successfully. Fine: " + fineAmt + " JD");
                req.getRequestDispatcher("/return_book.jsp").forward(req, resp);
                return;
            }

            /* ---------- B) BORROW (patron) ---------- */
            if ("borrow".equalsIgnoreCase(action)) {

                if (!"patron".equalsIgnoreCase(currentUser.getRole())) {
                    forwardError(req, resp, "❌ Only patrons can borrow.");
                    return;
                }

                int  bookId = Integer.parseInt(req.getParameter("bookId"));
                Book book   = bookDAO.getBookById(bookId);

                if (book == null || !"available".equalsIgnoreCase(book.getAvailability())) {
                    forwardError(req, resp, "❌ Book not available.");
                    return;
                }

                if (txDAO.getActiveBorrowCountForUser(currentUser.getUserId()) >= 3) {
                    forwardError(req, resp, "❌ Borrow limit reached (3).");
                    return;
                }

                String txId = generateTxId(txDAO);
                Date   now  = Date.valueOf(LocalDate.now());
                Date   due  = Date.valueOf(LocalDate.now().plusDays(14));

                txDAO.insertTransaction(new Transaction(
                        txId, currentUser.getUserId(), bookId, now, due, null, 0.0, "borrowed"));

                book.setAvailability("borrowed");
                bookDAO.updateBook(book);

                nDAO.insertNotification(new Notification(
                        0, currentUser.getUserId(),
                        "✅ Borrow confirmed! Transaction ID: " + txId,
                        "unread", new Timestamp(System.currentTimeMillis())));

                String staffMsg = "User (" + currentUser.getUserId() + ") borrowed '" + book.getTitle() + "'.";
                notifyAdminsAndLibrarians(nDAO, userDAO, staffMsg);

                session.setAttribute("successMessage", "✅ Borrow confirmed!");
                resp.sendRedirect("patron_dashboard.jsp");
                return;
            }

            /* ---------- default POST ---------- */
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");

        } catch (Exception ex) {
            ex.printStackTrace();
            forwardError(req, resp, "❌ Error: " + ex.getMessage());
        }
    }

    /* =============================================================
     *                         UTILITIES
     * ============================================================= */
    private String generateTxId(TransactionDAO dao) throws Exception {
        Random rnd = new Random();
        String id;
        do {
            id = "BT" + String.format("%06d", rnd.nextInt(1_000_000));
        } while (dao.getTransactionById(id) != null);
        return id;
    }

    /* ---------- tiny helpers ---------- */
    private static boolean isAdmin    (User u) { return "admin"    .equalsIgnoreCase(u.getRole()); }
    private static boolean isLibrarian(User u) { return "librarian".equalsIgnoreCase(u.getRole()); }

    private static void setSessionMsg(HttpSession s, String msg, String status) {
        s.setAttribute("message", msg);
        s.setAttribute("status",  status);
    }

    private static void forwardError(HttpServletRequest r, HttpServletResponse p, String m)
            throws ServletException, IOException {
        r.setAttribute("error", m);
        r.getRequestDispatcher("/return_book.jsp").forward(r, p);
    }

    private static void notifyPatron(NotificationDAO nDAO, int userId,
                                     String bookTitle, double fineAmount) throws Exception {
        nDAO.insertNotification(new Notification(
                0, userId,
                "تم إرجاع الكتاب '" + bookTitle + "' بنجاح."
                        + (fineAmount > 0 ? " غرامة: " + fineAmount + " JD." : ""),
                "unread", new Timestamp(System.currentTimeMillis())));
    }

    private static void notifyStaff(NotificationDAO nDAO, UserDAO userDAO,
                                    String bookTitle, int patronId, double fine) throws Exception {

        String msg = "إرجاع '" + bookTitle + "' من المستخدم (" + patronId + ")"
                   + (fine > 0 ? " | Fine " + fine + " JD" : "");

        notifyAdminsAndLibrarians(nDAO, userDAO, msg);
    }

    private static void notifyAdminsAndLibrarians(NotificationDAO nDAO, UserDAO userDAO,
                                                  String msg) throws Exception {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        for (User u : userDAO.getUsersByRole("admin"))
            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
        for (User u : userDAO.getUsersByRole("librarian"))
            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
    }
}
