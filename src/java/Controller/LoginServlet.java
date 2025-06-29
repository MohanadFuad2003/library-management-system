package Controller;

import models.User;
import DAO.UserDAO;
import Util.DBConnection;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            // ✅ جلب المستخدم حسب اسم المستخدم
            User user = userDAO.getUserByUsername(username);

            if (user == null) {
                // ❌ اسم المستخدم غير موجود
                request.setAttribute("error", "Username not found.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            if (!user.getPassword().equals(password)) {
                // ❌ كلمة المرور غير صحيحة
                request.setAttribute("error", "Incorrect password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            // ✅ تم التحقق - تسجيل الدخول ناجح
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);

            // ✅ توجيه حسب الدور
            switch (user.getRole()) {
                case "admin":
                    response.sendRedirect("AdminDashboardServlet");
                    break;
                case "librarian":
                    response.sendRedirect("librarian_dashboard.jsp");
                    break;
                case "patron":
                    response.sendRedirect("patron_dashboard.jsp");
                    break;
                default:
                    request.setAttribute("error", "Unknown role.");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // ❌ خطأ في الاتصال أو الاستعلام
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
