package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import models.User;

public final class deleted_005fuser_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>🗑️ Deleted Users Archive</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\" />\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Poppins', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #fceabb, #f8b500);\n");
      out.write("            padding: 40px 20px;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            margin: 0;\n");
      out.write("        }\n");
      out.write("        .container {\n");
      out.write("            max-width: 900px;\n");
      out.write("            margin: auto;\n");
      out.write("            background: rgba(255,255,255,0.95);\n");
      out.write("            border-radius: 25px;\n");
      out.write("            padding: 35px 40px;\n");
      out.write("            box-shadow: 0 20px 40px rgba(0,0,0,0.12);\n");
      out.write("            backdrop-filter: blur(12px);\n");
      out.write("            animation: fadeIn 1s ease forwards;\n");
      out.write("        }\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from {opacity: 0; transform: translateY(20px);}\n");
      out.write("            to {opacity: 1; transform: translateY(0);}\n");
      out.write("        }\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: 700;\n");
      out.write("            font-size: 2.4rem;\n");
      out.write("            color: #d35400;\n");
      out.write("            margin-bottom: 40px;\n");
      out.write("            letter-spacing: 1.5px;\n");
      out.write("            text-shadow: 0 2px 6px rgba(211, 84, 0, 0.3);\n");
      out.write("        }\n");
      out.write("        .user-card {\n");
      out.write("            background: #fff8e1;\n");
      out.write("            border-left: 6px solid #f39c12;\n");
      out.write("            padding: 20px 30px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 8px 15px rgba(243, 156, 18, 0.2);\n");
      out.write("            transition: transform 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("        .user-card:hover {\n");
      out.write("            transform: scale(1.02);\n");
      out.write("            box-shadow: 0 15px 25px rgba(243, 156, 18, 0.4);\n");
      out.write("        }\n");
      out.write("        .user-header {\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: space-between;\n");
      out.write("            align-items: center;\n");
      out.write("            margin-bottom: 12px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("            gap: 10px;\n");
      out.write("        }\n");
      out.write("        .user-name {\n");
      out.write("            font-size: 1.4rem;\n");
      out.write("            font-weight: 700;\n");
      out.write("            color: #e67e22;\n");
      out.write("        }\n");
      out.write("        .user-role {\n");
      out.write("            font-style: italic;\n");
      out.write("            font-weight: 600;\n");
      out.write("            color: #8e44ad;\n");
      out.write("            background: #f0e5ff;\n");
      out.write("            padding: 4px 10px;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            font-size: 1rem;\n");
      out.write("        }\n");
      out.write("        .user-details {\n");
      out.write("            font-size: 1rem;\n");
      out.write("            color: #2d3436;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("            line-height: 1.4;\n");
      out.write("        }\n");
      out.write("        .actions {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 24px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("        }\n");
      out.write("        .actions a {\n");
      out.write("            background: #27ae60;\n");
      out.write("            color: white;\n");
      out.write("            padding: 10px 22px;\n");
      out.write("            border-radius: 40px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: 600;\n");
      out.write("            box-shadow: 0 6px 15px rgba(39, 174, 96, 0.3);\n");
      out.write("            transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("            display: inline-flex;\n");
      out.write("            align-items: center;\n");
      out.write("            gap: 8px;\n");
      out.write("            font-size: 0.95rem;\n");
      out.write("        }\n");
      out.write("        .actions a.delete-perm {\n");
      out.write("            background: #e74c3c;\n");
      out.write("            box-shadow: 0 6px 15px rgba(231, 76, 60, 0.3);\n");
      out.write("        }\n");
      out.write("        .actions a:hover {\n");
      out.write("            filter: brightness(1.1);\n");
      out.write("            box-shadow: 0 10px 25px rgba(0,0,0,0.15);\n");
      out.write("        }\n");
      out.write("        .btn-back {\n");
      out.write("            display: block;\n");
      out.write("            max-width: 180px;\n");
      out.write("            margin: 40px auto 0 auto;\n");
      out.write("            padding: 14px 0;\n");
      out.write("            text-align: center;\n");
      out.write("            background: #34495e;\n");
      out.write("            color: white;\n");
      out.write("            font-weight: 700;\n");
      out.write("            border-radius: 30px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            box-shadow: 0 8px 20px rgba(52, 73, 94, 0.4);\n");
      out.write("            transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("        .btn-back:hover {\n");
      out.write("            background-color: #2c3e50;\n");
      out.write("            box-shadow: 0 12px 35px rgba(44, 62, 80, 0.6);\n");
      out.write("        }\n");
      out.write("        .no-users {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: 700;\n");
      out.write("            color: #b2bec3;\n");
      out.write("            font-size: 1.3rem;\n");
      out.write("            margin-top: 60px;\n");
      out.write("            font-style: italic;\n");
      out.write("            letter-spacing: 0.04em;\n");
      out.write("        }\n");
      out.write("        /* Responsive */\n");
      out.write("        @media (max-width: 700px) {\n");
      out.write("            .user-header {\n");
      out.write("                flex-direction: column;\n");
      out.write("                align-items: flex-start;\n");
      out.write("                gap: 6px;\n");
      out.write("            }\n");
      out.write("            .actions {\n");
      out.write("                flex-direction: column;\n");
      out.write("                gap: 12px;\n");
      out.write("            }\n");
      out.write("            .actions a {\n");
      out.write("                justify-content: center;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>🗑️ Deleted Users Archive</h2>\n");
      out.write("\n");
      out.write("    ");

        List<User> users = (List<User>) request.getAttribute("users");
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
    
      out.write("\n");
      out.write("    <div class=\"user-card\" title=\"User ID: ");
      out.print( user.getUserId() );
      out.write("\">\n");
      out.write("        <div class=\"user-header\">\n");
      out.write("            <div class=\"user-name\">");
      out.print( user.getFirstName() );
      out.write(' ');
      out.print( user.getLastName() );
      out.write("</div>\n");
      out.write("            <div class=\"user-role\">");
      out.print( user.getRole() != null ? user.getRole().toUpperCase() : "N/A" );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"user-details\">\n");
      out.write("            <strong>Username:</strong> ");
      out.print( user.getUsername() );
      out.write(" &nbsp;&bull;&nbsp;\n");
      out.write("            <strong>Email:</strong> ");
      out.print( user.getEmail() );
      out.write(" &nbsp;&bull;&nbsp;\n");
      out.write("            <strong>DOB:</strong> ");
      out.print( user.getDob() != null ? user.getDob() : "N/A" );
      out.write(" &nbsp;&bull;&nbsp;\n");
      out.write("            <strong>Phone:</strong> ");
      out.print( user.getPhoneNumber() );
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <div class=\"actions\">\n");
      out.write("            <a href=\"UserServlet?action=restore&userId=");
      out.print( user.getUserId() );
      out.write("\"\n");
      out.write("               onclick=\"return confirm('Are you sure you want to restore this user?');\"\n");
      out.write("               title=\"Restore this user\">♻️ Restore</a>\n");
      out.write("            <a href=\"UserServlet?action=deletePermanent&userId=");
      out.print( user.getUserId() );
      out.write("\"\n");
      out.write("               class=\"delete-perm\"\n");
      out.write("               onclick=\"return confirm('Are you sure you want to permanently delete this user? This action cannot be undone.');\"\n");
      out.write("               title=\"Permanently delete this user\">🗑️ Delete Permanently</a>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
      }
        } else { 
      out.write("\n");
      out.write("        <p class=\"no-users\">No deleted users found.</p>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"UserServlet?action=viewAll\" class=\"btn-back\">← Back to Users List</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
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
