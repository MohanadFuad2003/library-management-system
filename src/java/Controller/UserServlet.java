package Controller;

import DAO.UserDAO;
import Util.DBConnection;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;


public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "viewAll";

        HttpSession session = request.getSession();

        // نقل الرسائل من الـ session إلى request (للعرض مرة واحدة فقط)
        String message = (String) session.getAttribute("message");
        String status = (String) session.getAttribute("status");
        if (message != null) {
            request.setAttribute("message", message);
            request.setAttribute("status", status);
            session.removeAttribute("message");
            session.removeAttribute("status");
        }

        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            UserDAO userDAO = new UserDAO(conn);

            switch (action) {
                case "viewAll":
                    List<User> users;
                    if ("admin".equals(currentUser.getRole())) {
                        users = userDAO.getAllUsers();
                    } else if ("librarian".equals(currentUser.getRole())) {
                        users = userDAO.getUsersByRole("patron");
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    request.setAttribute("users", users);
                    request.getRequestDispatcher("user_list.jsp").forward(request, response);
                    break;

                case "showAddForm":
                    if (!"admin".equals(currentUser.getRole()) && !"librarian".equals(currentUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    // السماح للـ librarian بالدخول لصفحة إضافة المستخدم
                    request.getRequestDispatcher("add_user.jsp").forward(request, response);
                    break;

                case "showEditForm":
                    int editUserId = Integer.parseInt(request.getParameter("userId"));
                    User editUser = userDAO.getUserById(editUserId);
                    if (editUser != null) {
                        // اللايبراريين يمكنهم فقط تعديل رواد المكتبة
                        if ("librarian".equals(currentUser.getRole()) && !"patron".equals(editUser.getRole())) {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                            return;
                        }
                        request.setAttribute("user", editUser);
                        request.getRequestDispatcher("edit_user.jsp").forward(request, response);
                    } else {
                        session.setAttribute("message", "User not found.");
                        session.setAttribute("status", "error");
                        response.sendRedirect("UserServlet?action=viewAll");
                    }
                    break;

                case "delete":
                    int deleteUserId = Integer.parseInt(request.getParameter("userId"));
                    User deleteUser = userDAO.getUserById(deleteUserId);
                    if (deleteUser == null) {
                        session.setAttribute("message", "User not found.");
                        session.setAttribute("status", "error");
                        response.sendRedirect("UserServlet?action=viewAll");
                        return;
                    }
                    // اللايبراريين يمكنهم حذف فقط رواد المكتبة
                    if ("librarian".equals(currentUser.getRole()) && !"patron".equals(deleteUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    userDAO.deleteUser(deleteUserId);
                    session.setAttribute("message", "User deleted successfully.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("UserServlet?action=viewAll");
                    break;

                case "search":
                    String keyword = request.getParameter("keyword");
                    List<User> searchResults;
                    if ("admin".equals(currentUser.getRole())) {
                        searchResults = (keyword != null && !keyword.trim().isEmpty())
                                ? userDAO.searchUsersByName(keyword.trim())
                                : userDAO.getAllUsers();
                    } else if ("librarian".equals(currentUser.getRole())) {
                        List<User> patrons = userDAO.getUsersByRole("patron");
                        searchResults = new java.util.ArrayList<>();
                        if (keyword != null && !keyword.trim().isEmpty()) {
                            String kwLower = keyword.trim().toLowerCase();
                            for (User u : patrons) {
                                if (u.getFirstName().toLowerCase().contains(kwLower) || u.getLastName().toLowerCase().contains(kwLower)) {
                                    searchResults.add(u);
                                }
                            }
                        } else {
                            searchResults = patrons;
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    request.setAttribute("users", searchResults);
                    request.getRequestDispatcher("user_list.jsp").forward(request, response);
                    break;

                case "deleted":
                    if (!"admin".equals(currentUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    List<User> deletedUsers = userDAO.getDeletedUsers();
                    request.setAttribute("users", deletedUsers);
                    request.getRequestDispatcher("deleted_user_list.jsp").forward(request, response);
                    break;

                case "restore":
                    if (!"admin".equals(currentUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    int restoreUserId = Integer.parseInt(request.getParameter("userId"));
                    userDAO.restoreUser(restoreUserId);
                    session.setAttribute("message", "User restored successfully.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("UserServlet?action=deleted");
                    break;

                case "deletePermanent":
                    if (!"admin".equals(currentUser.getRole())) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                        return;
                    }
                    int deletePermanentUserId = Integer.parseInt(request.getParameter("userId"));
                    userDAO.deleteUserPermanently(deletePermanentUserId);
                    session.setAttribute("message", "User permanently deleted.");
                    session.setAttribute("status", "success");
                    response.sendRedirect("UserServlet?action=deleted");
                    break;

                default:
                    response.sendRedirect("UserServlet?action=viewAll");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("user_list.jsp").forward(request, response);
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
            UserDAO userDAO = new UserDAO(conn);

            if ("add".equals(action)) {
                if (!"admin".equals(currentUser.getRole()) && !"librarian".equals(currentUser.getRole())) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
                String phoneNumber = request.getParameter("phoneNumber");

                // إذا كان المستخدم الحالي هو لايبراريان، نقوم بتعيين الدور تلقائيًا إلى "patron"
                if ("librarian".equals(currentUser.getRole())) {
                    role = "patron";
                }

                if (userDAO.getUserByUsername(username) != null) {
                    request.setAttribute("error", "Username already exists.");
                    request.getRequestDispatcher("add_user.jsp").forward(request, response);
                    return;
                }

                User newUser = new User();
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setRole(role);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setEmail(email);
                newUser.setDob(dob);
                newUser.setPhoneNumber(phoneNumber);

                userDAO.insertUser(newUser);

                session.setAttribute("message", "User added successfully.");
                session.setAttribute("status", "success");
                response.sendRedirect("UserServlet?action=viewAll");
            }

            else if ("edit".equals(action)) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                User user = userDAO.getUserById(userId);

                if (user == null) {
                    request.setAttribute("error", "User not found.");
                    request.getRequestDispatcher("edit_user.jsp").forward(request, response);
                    return;
                }
                if ("librarian".equals(currentUser.getRole()) && !"patron".equals(user.getRole())) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
                    return;
                }

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
                String phoneNumber = request.getParameter("phoneNumber");

                User existingUserByUsername = userDAO.getUserByUsername(username);
                if (existingUserByUsername != null && existingUserByUsername.getUserId() != userId) {
                    request.setAttribute("error", "Username already exists.");
                    User u = new User();
                    u.setUserId(userId);
                    u.setUsername(username);
                    u.setPassword(password);
                    u.setRole(role);
                    u.setFirstName(firstName);
                    u.setLastName(lastName);
                    u.setEmail(email);
                    u.setDob(dob);
                    u.setPhoneNumber(phoneNumber);

                    request.setAttribute("user", u);
                    request.getRequestDispatcher("edit_user.jsp").forward(request, response);
                    return;
                }

                user.setUsername(username);
                user.setPassword(password);
                user.setRole(role);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setDob(dob);
                user.setPhoneNumber(phoneNumber);

                userDAO.updateUser(user);

                session.setAttribute("message", "User updated successfully.");
                session.setAttribute("status", "success");
                response.sendRedirect("UserServlet?action=viewAll");
            } else {
                response.sendRedirect("UserServlet?action=viewAll");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("user_list.jsp").forward(request, response);
        }
    }
}
