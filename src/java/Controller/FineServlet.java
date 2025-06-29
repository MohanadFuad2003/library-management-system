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
import java.util.List;

public class FineServlet extends HttpServlet {

    /* =========================================================
     *                        GET METHOD
     * ========================================================= */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String ctx     = req.getContextPath();
        String action  = req.getParameter("action") == null
                       ? "viewAll"
                       : req.getParameter("action");

        HttpSession session = req.getSession();
        User currentUser    = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(ctx + "/login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {

            FineDAO         fineDAO = new FineDAO(conn);
            SystemConfigDAO confDAO = new SystemConfigDAO(conn);
            NotificationDAO nDAO    = new NotificationDAO(conn);
            UserDAO         userDAO = new UserDAO(conn);
            TransactionDAO  txDAO   = new TransactionDAO(conn);

            switch (action) {

                /* ----------- 1) جميع الغرامات (للموظف) ----------- */
                case "viewAll": {
                    double rate = 1.0;
                    SystemConfig cfg = confDAO.getCurrentConfig();
                    if (cfg != null) rate = cfg.getFineRate();
                    fineDAO.createFinesForOverdueTransactions(rate);

                    if (!("admin".equalsIgnoreCase(currentUser.getRole())
                       || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    String sUser   = req.getParameter("searchUser");
                    String sBook   = req.getParameter("searchBook");
                    String sStatus = req.getParameter("searchStatus");

                    List<Fine> fines = fineDAO.getFinesWithFilters(sUser, sBook, sStatus);

                    req.setAttribute("searchUser",   sUser);
                    req.setAttribute("searchBook",   sBook);
                    req.setAttribute("searchStatus", sStatus);
                    req.setAttribute("fines",        fines);
                    req.getRequestDispatcher("fine_list.jsp").forward(req, resp);
                    break;
                }

                /* ------------- 2) وضع غرامة كمدفوعة ------------- */
                case "markPaid": {
                    if (!("admin".equalsIgnoreCase(currentUser.getRole())
                       || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    int fineId = Integer.parseInt(req.getParameter("fineId"));
                    fineDAO.markFineAsPaid(fineId, Date.valueOf(LocalDate.now()));

                    Fine fine = fineDAO.getFineById(fineId);
                    if (fine != null) {
                        String msg = "تم دفع الغرامة لكتاب '" + fine.getBookTitle()
                                   + "' (معرّف الغرامة " + fineId + "). شكرًا!";
                        Timestamp now = new Timestamp(System.currentTimeMillis());

                        nDAO.insertNotification(new Notification(0, fine.getUserId(), msg, "unread", now));
                        for (User u : userDAO.getUsersByRole("admin"))
                            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
                        for (User u : userDAO.getUsersByRole("librarian"))
                            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
                    }

                    session.setAttribute("message", "Fine marked as paid and notifications sent.");
                    session.setAttribute("status",  "success");
                    resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
                    break;
                }

                /* ---------------- 3) حذف غرامة ---------------- */
                case "delete": {
                    if (!"admin".equalsIgnoreCase(currentUser.getRole())) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    int  id  = Integer.parseInt(req.getParameter("fineId"));
                    Fine fin = fineDAO.getFineById(id);

                    if (fin != null) {
                        fineDAO.deleteFine(id);
                        txDAO.blockFineForTransaction(fin.getTransactionId());

                        String msg = "تم حذف الغرامة الخاصة بكتاب '" + fin.getBookTitle() + "'.";
                        Timestamp now = new Timestamp(System.currentTimeMillis());

                        nDAO.insertNotification(new Notification(0, fin.getUserId(), msg, "unread", now));
                        for (User u : userDAO.getUsersByRole("admin"))
                            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
                        for (User u : userDAO.getUsersByRole("librarian"))
                            nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));

                        session.setAttribute("message", "Fine deleted and notifications sent.");
                        session.setAttribute("status",  "success");
                    } else {
                        session.setAttribute("message", "Fine not found.");
                        session.setAttribute("status",  "error");
                    }
                    resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
                    break;
                }

                /* -------------- 4) نموذج التعديل -------------- */
                case "editForm": {
                    if (!("admin".equalsIgnoreCase(currentUser.getRole())
                       || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                        resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    int  id = Integer.parseInt(req.getParameter("fineId"));
                    Fine f  = fineDAO.getFineById(id);

                    if (f != null) {
                        req.setAttribute("fine", f);
                        req.getRequestDispatcher("edit_fine.jsp").forward(req, resp);
                    } else {
                        session.setAttribute("message", "Fine not found.");
                        session.setAttribute("status",  "error");
                        resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
                    }
                    break;
                }

                /* ------------- 5) غرامات المستعير ------------- */
                case "myFines": {
                  if (!currentUser.getRole().equalsIgnoreCase("patron")) {
    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
    return;
}


                    double rate = 1.0;
                    SystemConfig cfg = confDAO.getCurrentConfig();
                    if (cfg != null) rate = cfg.getFineRate();
                    fineDAO.createFinesForOverdueTransactions(rate);

                    List<Fine> patronFines = fineDAO.getFinesByUser(currentUser.getUserId());

                    req.setAttribute("fines", patronFines);
                    req.getRequestDispatcher("patron_fines.jsp").forward(req, resp);
                    break;
                }

                /* ------------------- fallback ------------------ */
                default:
                    resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", "Error: " + ex.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    /* =========================================================
     *                       POST METHOD
     * ========================================================= */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String ctx    = req.getContextPath();
        String action = req.getParameter("action");

        HttpSession session = req.getSession();
        User currentUser    = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect(ctx + "/login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {

            FineDAO         fineDAO = new FineDAO(conn);
            NotificationDAO nDAO    = new NotificationDAO(conn);
            UserDAO         userDAO = new UserDAO(conn);

            /* ---------- 1) غرامات المستعير (نادرًا ما تكون POST) ---------- */
            if ("myFines".equals(action)) {
                List<Fine> fines = fineDAO.getFinesByUser(currentUser.getUserId());
                req.setAttribute("fines", fines);
                req.getRequestDispatcher("my_fines.jsp").forward(req, resp);
                return;     // مهمّ: لا تواصل بعدها
            }

            /* ---------------- 2) تعديل غرامة ---------------- */
            if ("edit".equals(action)) {

                if (!("admin".equalsIgnoreCase(currentUser.getRole())
                   || "librarian".equalsIgnoreCase(currentUser.getRole()))) {
                    resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }

                int    id   = Integer.parseInt(req.getParameter("fineId"));
                double amt  = Double.parseDouble(req.getParameter("fineAmount"));
                String stat = req.getParameter("paymentStatus");

                Fine oldFine = fineDAO.getFineById(id);
                if (oldFine == null) {
                    session.setAttribute("message", "Fine not found.");
                    session.setAttribute("status",  "error");
                    resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
                    return;
                }

                Fine f = new Fine();
                f.setFineId(id);
                f.setFineAmount(amt);
                f.setPaymentStatus(stat);
                f.setPaidDate("paid".equalsIgnoreCase(stat) ? Date.valueOf(LocalDate.now()) : null);
                fineDAO.updateFine(f);

                if (!"paid".equalsIgnoreCase(oldFine.getPaymentStatus())
                 &&  "paid".equalsIgnoreCase(stat)) {

                    String msg = "تم دفع الغرامة الخاصة بكتاب '" + oldFine.getBookTitle() + "'.";
                    Timestamp now = new Timestamp(System.currentTimeMillis());

                    nDAO.insertNotification(new Notification(0, oldFine.getUserId(), msg, "unread", now));
                    for (User u : userDAO.getUsersByRole("admin"))
                        nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
                    for (User u : userDAO.getUsersByRole("librarian"))
                        nDAO.insertNotification(new Notification(0, u.getUserId(), msg, "unread", now));
                }

                session.setAttribute("message", "Fine updated successfully.");
                session.setAttribute("status",  "success");
                resp.sendRedirect(ctx + "/FineServlet?action=viewAll");
                return;
            }

            /* ---------------- أيّ إجراء آخر ---------------- */
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action");

        } catch (Exception ex) {
            ex.printStackTrace();
            req.setAttribute("error", "Error: " + ex.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
