package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import models.User;

public final class user_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");

    User currentUser = (User) session.getAttribute("currentUser");
    String role = currentUser != null ? currentUser.getRole() : "";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Manage Users</title>\n");
      out.write("    <style>\n");
      out.write("        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            font-family: 'Inter', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #e0eafc, #cfdef3);\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 1100px;\n");
      out.write("            margin: auto;\n");
      out.write("            background: #ffffff;\n");
      out.write("            padding: 30px 40px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 12px 28px rgba(0, 0, 0, 0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            font-size: 28px;\n");
      out.write("            color: #2c3e50;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .welcome-msg {\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 18px;\n");
      out.write("            color: #34495e;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .search-form {\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("            gap: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .search-group {\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            gap: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .search-input {\n");
      out.write("            padding: 10px;\n");
      out.write("            font-size: 15px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-top-left-radius: 10px;\n");
      out.write("            border-bottom-left-radius: 10px;\n");
      out.write("            border-right: none;\n");
      out.write("            width: 250px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .search-btn {\n");
      out.write("            padding: 10px 18px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            border: none;\n");
      out.write("            border-top-right-radius: 10px;\n");
      out.write("            border-bottom-right-radius: 10px;\n");
      out.write("            color: white;\n");
      out.write("            background: linear-gradient(90deg, #2980b9, #6dd5fa);\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .search-btn:hover {\n");
      out.write("            background: linear-gradient(90deg, #6dd5fa, #2980b9);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn {\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            color: white;\n");
      out.write("            background: linear-gradient(90deg, #2980b9, #6dd5fa);\n");
      out.write("            cursor: pointer;\n");
      out.write("            text-decoration: none;\n");
      out.write("            display: inline-block;\n");
      out.write("            transition: transform 0.2s ease, background 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn:hover {\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("            background: linear-gradient(90deg, #6dd5fa, #2980b9);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn.red {\n");
      out.write("            background: linear-gradient(90deg, #e74c3c, #ff6b6b);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn.red:hover {\n");
      out.write("            background: linear-gradient(90deg, #c0392b, #ff5a5a);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn.dashboard {\n");
      out.write("            background: linear-gradient(90deg, #6a11cb, #2575fc);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: collapse;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            overflow: hidden;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th, td {\n");
      out.write("            padding: 12px 14px;\n");
      out.write("            border-bottom: 1px solid #f0f0f0;\n");
      out.write("            text-align: left;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th {\n");
      out.write("            background-color: #2c3e50;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        tr:hover {\n");
      out.write("            background-color: #f8f9fa;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .action-buttons {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 10px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn.small {\n");
      out.write("            padding: 6px 12px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .edit-btn {\n");
      out.write("            background: linear-gradient(90deg, #3498db, #6dd5fa);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .edit-btn:hover {\n");
      out.write("            background: linear-gradient(90deg, #2980b9, #58c9f3);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .delete-btn {\n");
      out.write("            background: linear-gradient(90deg, #e74c3c, #ff7676);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .delete-btn:hover {\n");
      out.write("            background: linear-gradient(90deg, #c0392b, #ff5a5a);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message-box {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: bold;\n");
      out.write("            padding: 12px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message-success {\n");
      out.write("            background-color: #d4edda;\n");
      out.write("            color: #155724;\n");
      out.write("            border: 1px solid #c3e6cb;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message-error {\n");
      out.write("            background-color: #f8d7da;\n");
      out.write("            color: #721c24;\n");
      out.write("            border: 1px solid #f5c6cb;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            text-align: center;\n");
      out.write("            color: red;\n");
      out.write("            font-weight: 600;\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-group {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-group a {\n");
      out.write("            margin: 10px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>👥 Manage Users</h2>\n");
      out.write("\n");
      out.write("    ");
 if (!role.isEmpty()) { 
      out.write("\n");
      out.write("        <div class=\"welcome-msg\">Welcome, ");
      out.print( role.substring(0, 1).toUpperCase() + role.substring(1) );
      out.write("!</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    ");
 String message = (String) request.getAttribute("message");
       String status = (String) request.getAttribute("status");
       if (message != null) { 
      out.write("\n");
      out.write("        <div class=\"message-box ");
      out.print( "success".equals(status) ? "message-success" : "message-error" );
      out.write("\">\n");
      out.write("            ");
      out.print( message );
      out.write("\n");
      out.write("        </div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if ("admin".equals(role) || "librarian".equals(role)) { 
      out.write("\n");
      out.write("    <form class=\"search-form\" action=\"UserServlet\" method=\"get\">\n");
      out.write("        <div class=\"search-group\">\n");
      out.write("            <input class=\"search-input\" type=\"text\" name=\"keyword\" placeholder=\"🔍 Search by name...\" />\n");
      out.write("            <button class=\"search-btn\" type=\"submit\" name=\"action\" value=\"search\">Search</button>\n");
      out.write("        </div>\n");
      out.write("        ");
 if ("admin".equals(role)) { 
      out.write("\n");
      out.write("            <a href=\"UserServlet?action=viewAll\" class=\"btn\">👁 Show All</a>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </form>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    ");
 String error = (String) request.getAttribute("error");
       if (error != null) { 
      out.write("\n");
      out.write("        <div class=\"error\">");
      out.print( error );
      out.write("</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if ("admin".equals(role) || "librarian".equals(role)) { 
      out.write("\n");
      out.write("        <a href=\"UserServlet?action=showAddForm\" class=\"btn\">➕ Add New User</a>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    ");
 if ("admin".equals(role)) { 
      out.write("\n");
      out.write("        <a href=\"UserServlet?action=deleted\" class=\"btn red\">🗑️ Deleted Users</a>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <table>\n");
      out.write("        <thead>\n");
      out.write("        <tr>\n");
      out.write("            <th>ID</th>\n");
      out.write("            <th>Username</th>\n");
      out.write("            <th>Role</th>\n");
      out.write("            <th>First</th>\n");
      out.write("            <th>Last</th>\n");
      out.write("            <th>Email</th>\n");
      out.write("            <th>DOB</th>\n");
      out.write("            <th>Phone</th>\n");
      out.write("            <th>Actions</th>\n");
      out.write("        </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("        ");

            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User u : users) {
                    if ("librarian".equals(role) && !"patron".equals(u.getRole())) continue;
        
      out.write("\n");
      out.write("        <tr>\n");
      out.write("            <td>");
      out.print( u.getUserId() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getUsername() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getRole() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getFirstName() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getLastName() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getEmail() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getDob() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( u.getPhoneNumber() );
      out.write("</td>\n");
      out.write("            <td>\n");
      out.write("                <div class=\"action-buttons\">\n");
      out.write("                    <a class=\"btn small edit-btn\" href=\"UserServlet?action=showEditForm&userId=");
      out.print( u.getUserId() );
      out.write("\">✏️ Edit</a>\n");
      out.write("                    <a class=\"btn small delete-btn\" href=\"UserServlet?action=delete&userId=");
      out.print( u.getUserId() );
      out.write("\"\n");
      out.write("                       onclick=\"return confirm('Are you sure you want to delete this user?');\">🗑️ Delete</a>\n");
      out.write("                </div>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        ");

                }
            } else {
        
      out.write("\n");
      out.write("        <tr><td colspan=\"9\" style=\"text-align:center;\">No users found.</td></tr>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("\n");
      out.write("    <div class=\"btn-group\">\n");
      out.write("        ");
 if ("admin".equals(role)) { 
      out.write("\n");
      out.write("            <a href=\"AdminDashboardServlet\" class=\"btn dashboard\">🏠 Admin Dashboard</a>\n");
      out.write("        ");
 } else if ("librarian".equals(role)) { 
      out.write("\n");
      out.write("            <a href=\"librarian_dashboard.jsp\" class=\"btn dashboard\">🏠 Librarian Dashboard</a>\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("            <a href=\"login.jsp\" class=\"btn dashboard\">🔐 Back to Login</a>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
