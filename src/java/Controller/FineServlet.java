package Controller;

import DAO.FineDAO;
import DAO.NotificationDAO;
import DAO.SystemConfigDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Util.DBConnection;
import models.Fine;
import models.SystemConfig;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import models.Notification;
import models.User;


public class FineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAll";

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            FineDAO fineDAO = new FineDAO(conn);
            SystemConfigDAO configDAO = new SystemConfigDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            UserDAO userDAO = new UserDAO(conn); // لجللب جميع المستخدمين للصلاحيات

            switch (action) {
                case "viewAll":
                    double fineRatePerDay = 1.0;
                    try {
                        SystemConfig config = configDAO.getCurrentConfig();
                        if (config != null) fineRatePerDay = config.getFineRate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    fineDAO.createFinesForOverdueTransactions(fineRatePerDay);

                    String searchUser = request.getParameter("searchUser");
                    String searchBook = request.getParameter("searchBook");
                    String searchStatus = request.getParameter("searchStatus");

                    List<Fine> fines;
                    if ("admin".equals(currentUser.getRole()) || "librarian".equals(currentUser.getRole())) {
                        fines = fineDAO.getFinesWithFilters(searchUser, searchBook, searchStatus);
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    request.setAttribute("searchUser", searchUser);
                    request.setAttribute("searchBook", searchBook);
                    request.setAttribute("searchStatus", searchStatus);
                    request.setAttribute("fines", fines);
                    request.getRequestDispatcher("fine_list.jsp").forward(request, response);
                    break;

                case "markPaid":
                    if (!("admin".equals(currentUser.getRole()) || "librarian".equals(currentUser.getRole()))) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    int fineIdToMark = Integer.parseInt(request.getParameter("fineId"));
                    fineDAO.markFineAsPaid(fineIdToMark, Date.valueOf(LocalDate.now()));

                    Fine paidFine = fineDAO.getFineById(fineIdToMark);
                    if (paidFine != null) {
                        String message = "تم دفع الغرامة عن استعارتك لكتاب '" + paidFine.getBookTitle() + "' (معرّف الغرامة: " + fineIdToMark + "). شكراً لك!";
                        Timestamp now = new Timestamp(System.currentTimeMillis());

                        // 1. إشعار الباترون المعني
                        notificationDAO.insertNotification(new Notification(
                            0,
                            paidFine.getUserId(),
                            message,
                            "unread",
                            now
                        ));

                        // 2. إشعار جميع الإداريين
                        List<User> admins = userDAO.getUsersByRole("admin");
                        List<User> librarians = userDAO.getUsersByRole("librarian");
                        for (User admin : admins) {
                            notificationDAO.insertNotification(new Notification(0, admin.getUserId(), message, "unread", now));
                        }
                        for (User librarian : librarians) {
                            notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), message, "unread", now));
                        }
                    }

                    session.setAttribute("message", "Fine marked as paid successfully and notification sent.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("FineServlet?action=viewAll");
                    break;

                case "delete":
                    if (!"admin".equals(currentUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    int deleteFineId = Integer.parseInt(request.getParameter("fineId"));
                    Fine fineToDelete = fineDAO.getFineById(deleteFineId);
                    if (fineToDelete != null) {
                        String transactionId = fineToDelete.getTransactionId();
                        fineDAO.deleteFine(deleteFineId);
                        TransactionDAO transactionDAO = new TransactionDAO(conn);
                        transactionDAO.blockFineForTransaction(transactionId);

                        String message = "تم حذف الغرامة عن استعارتك لكتاب '" + fineToDelete.getBookTitle() + "'.";
                        Timestamp now = new Timestamp(System.currentTimeMillis());

                        // 1. إشعار الباترون
                        notificationDAO.insertNotification(new Notification(
                            0,
                            fineToDelete.getUserId(),
                            message,
                            "unread",
                            now
                        ));
                        // 2. إشعار جميع الإداريين
                        List<User> admins = userDAO.getUsersByRole("admin");
                        List<User> librarians = userDAO.getUsersByRole("librarian");
                        for (User admin : admins) {
                            notificationDAO.insertNotification(new Notification(0, admin.getUserId(), message, "unread", now));
                        }
                        for (User librarian : librarians) {
                            notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), message, "unread", now));
                        }

                        session.setAttribute("message", "Fine deleted successfully and notifications sent to all.");
                        session.setAttribute("status", "success");
                    } else {
                        session.setAttribute("message", "Fine not found.");
                        session.setAttribute("status", "error");
                    }
                    response.sendRedirect("FineServlet?action=viewAll");
                    break;

                case "showAddForm":
                    session.setAttribute("message", "Manual addition of fines is disabled.");
                    session.setAttribute("status", "error");
                    response.sendRedirect("FineServlet?action=viewAll");
                    break;

                case "editForm":
                    if (!("admin".equals(currentUser.getRole()) || "librarian".equals(currentUser.getRole()))) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }

                    int editFineId = Integer.parseInt(request.getParameter("fineId"));
                    Fine fine = fineDAO.getFineById(editFineId);
                    if (fine != null) {
                        request.setAttribute("fine", fine);
                        request.getRequestDispatcher("edit_fine.jsp").forward(request, response);
                    } else {
                        session.setAttribute("message", "Fine not found.");
                        session.setAttribute("status", "error");
                        response.sendRedirect("FineServlet?action=viewAll");
                    }
                    break;

                default:
                    response.sendRedirect("FineServlet?action=viewAll");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("fine_list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            FineDAO fineDAO = new FineDAO(conn);
            NotificationDAO notificationDAO = new NotificationDAO(conn);
            UserDAO userDAO = new UserDAO(conn);

            if ("add".equals(action)) {
                session.setAttribute("message", "Manual addition of fines is not allowed. Fines are generated automatically.");
                session.setAttribute("status", "error");
                response.sendRedirect("FineServlet?action=viewAll");
                return;
            }
            else if ("edit".equals(action)) {
                if (!("admin".equals(currentUser.getRole()) || "librarian".equals(currentUser.getRole()))) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }

                int fineId = Integer.parseInt(request.getParameter("fineId"));
                double fineAmount = Double.parseDouble(request.getParameter("fineAmount"));
                String paymentStatus = request.getParameter("paymentStatus");
                Date paidDate = null;
                if ("paid".equalsIgnoreCase(paymentStatus)) {
                    paidDate = Date.valueOf(LocalDate.now());
                }

                Fine oldFine = fineDAO.getFineById(fineId); // لجلب البيانات القديمة قبل التعديل

                Fine fine = new Fine();
                fine.setFineId(fineId);
                fine.setFineAmount(fineAmount);
                fine.setPaymentStatus(paymentStatus);
                fine.setPaidDate(paidDate);

                fineDAO.updateFine(fine);

                // إشعار الجميع عند تغيير الحالة إلى paid
                if (oldFine != null && !"paid".equalsIgnoreCase(oldFine.getPaymentStatus()) && "paid".equalsIgnoreCase(paymentStatus)) {
                    String message = "تم تغيير حالة الغرامة لكتاب '" + oldFine.getBookTitle() + "' إلى مدفوعة.";
                    Timestamp now = new Timestamp(System.currentTimeMillis());

                    // 1. الباترون
                    notificationDAO.insertNotification(new Notification(
                        0,
                        oldFine.getUserId(),
                        message,
                        "unread",
                        now
                    ));
                    // 2. الإداريين
                    List<User> admins = userDAO.getUsersByRole("admin");
                    List<User> librarians = userDAO.getUsersByRole("librarian");
                    for (User admin : admins) {
                        notificationDAO.insertNotification(new Notification(0, admin.getUserId(), message, "unread", now));
                    }
                    for (User librarian : librarians) {
                        notificationDAO.insertNotification(new Notification(0, librarian.getUserId(), message, "unread", now));
                    }
                }

                session.setAttribute("message", "Fine updated successfully and notifications sent.");
                session.setAttribute("status", "success");
                response.sendRedirect("FineServlet?action=viewAll");
            }
            else {
                response.sendRedirect("FineServlet?action=viewAll");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("fine_list.jsp").forward(request, response);
        }
    }
}
