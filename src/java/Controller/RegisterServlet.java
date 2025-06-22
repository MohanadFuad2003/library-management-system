package Controller;

import DAO.NotificationDAO;
import DAO.UserDAO;
import models.User;
import Util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import models.Notification;


public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username    = request.getParameter("username");
        String password    = request.getParameter("password");
        String role        = request.getParameter("role");
        String firstName   = request.getParameter("firstName");
        String lastName    = request.getParameter("lastName");
        String email       = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String dobStr      = request.getParameter("dob");

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            if (userDAO.getUserByUsername(username) != null) {
                request.setAttribute("message", "Username already exists. Please choose another one.");
                request.setAttribute("status", "error");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // إنشاء مستخدم جديد
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRole(role);
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setDob(Date.valueOf(dobStr));

            userDAO.insertUser(newUser);

            // --- إشعار الإداريين واللايبرايريان ---
            NotificationDAO notificationDAO = new NotificationDAO(conn);

            // جلب جميع الإداريين واللايبرايريان
            List<User> admins = userDAO.getUsersByRole("admin");
            List<User> librarians = userDAO.getUsersByRole("librarian");

            String notifMsg = "تم إنشاء حساب مستخدم جديد: "
                    + firstName + " " + lastName + " (" + username + "), الدور: " + role;

            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            for (User admin : admins) {
                notificationDAO.insertNotification(new Notification(
                    0, admin.getUserId(), notifMsg, "unread", now
                ));
            }
            for (User librarian : librarians) {
                notificationDAO.insertNotification(new Notification(
                    0, librarian.getUserId(), notifMsg, "unread", now
                ));
            }
            // --- نهاية منطق الإشعار ---

            request.setAttribute("message", "Your account has been created successfully! ✅");
            request.setAttribute("status", "success");
            request.getRequestDispatcher("register.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "An error occurred: " + e.getMessage());
            request.setAttribute("status", "error");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}

