package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.User;

public final class edit_005fuser_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');

    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("UserServlet?action=viewAll");
        return;
    }

    User currentUser = (User) session.getAttribute("currentUser");
    String currentRole = currentUser != null ? currentUser.getRole() : "";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\" />\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n");
      out.write("<title>🧑‍💼 Edit User</title>\n");
      out.write("<link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\" />\n");
      out.write("<style>\n");
      out.write("  /* Base reset */\n");
      out.write("  * {\n");
      out.write("    box-sizing: border-box;\n");
      out.write("  }\n");
      out.write("  body {\n");
      out.write("    margin: 0; padding: 40px 20px;\n");
      out.write("    background: linear-gradient(135deg, #dbe6f6, #f9faff);\n");
      out.write("    font-family: 'Poppins', sans-serif;\n");
      out.write("    display: flex;\n");
      out.write("    justify-content: center;\n");
      out.write("    min-height: 100vh;\n");
      out.write("    align-items: center;\n");
      out.write("  }\n");
      out.write("  /* Container */\n");
      out.write("  .form-container {\n");
      out.write("    background: #f0f4ff;\n");
      out.write("    padding: 40px 35px;\n");
      out.write("    border-radius: 24px;\n");
      out.write("    max-width: 480px;\n");
      out.write("    width: 100%;\n");
      out.write("    box-shadow:\n");
      out.write("      10px 10px 30px #c1cdfb,\n");
      out.write("      -10px -10px 30px #ffffff;\n");
      out.write("    transition: box-shadow 0.3s ease;\n");
      out.write("  }\n");
      out.write("  .form-container:hover {\n");
      out.write("    box-shadow:\n");
      out.write("      12px 12px 40px #a2b2f5,\n");
      out.write("      -12px -12px 40px #ffffff;\n");
      out.write("  }\n");
      out.write("  /* Heading */\n");
      out.write("  h2 {\n");
      out.write("    text-align: center;\n");
      out.write("    color: #304ffe;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 2.4rem;\n");
      out.write("    margin-bottom: 35px;\n");
      out.write("    letter-spacing: 0.08em;\n");
      out.write("    text-shadow: 1px 1px 3px rgba(48,79,254,0.4);\n");
      out.write("  }\n");
      out.write("  /* Error message */\n");
      out.write("  .error {\n");
      out.write("    background: #ffd9d9;\n");
      out.write("    color: #e53935;\n");
      out.write("    font-weight: 700;\n");
      out.write("    text-align: center;\n");
      out.write("    padding: 12px 15px;\n");
      out.write("    border-radius: 12px;\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("    box-shadow: inset 0 0 8px #e5393511;\n");
      out.write("  }\n");
      out.write("  /* Labels */\n");
      out.write("  label {\n");
      out.write("    display: block;\n");
      out.write("    font-weight: 600;\n");
      out.write("    color: #3f51b5;\n");
      out.write("    margin-top: 20px;\n");
      out.write("    margin-bottom: 8px;\n");
      out.write("    letter-spacing: 0.02em;\n");
      out.write("  }\n");
      out.write("  /* Inputs & selects with neumorphism */\n");
      out.write("  input[type=\"text\"],\n");
      out.write("  input[type=\"email\"],\n");
      out.write("  input[type=\"password\"],\n");
      out.write("  input[type=\"date\"],\n");
      out.write("  input[type=\"tel\"],\n");
      out.write("  select {\n");
      out.write("    width: 100%;\n");
      out.write("    padding: 14px 20px;\n");
      out.write("    font-size: 1rem;\n");
      out.write("    color: #3949ab;\n");
      out.write("    background: #f0f4ff;\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 18px;\n");
      out.write("    box-shadow:\n");
      out.write("      inset 8px 8px 15px #c3cbf5,\n");
      out.write("      inset -8px -8px 15px #ffffff;\n");
      out.write("    transition: box-shadow 0.3s ease, color 0.3s ease;\n");
      out.write("  }\n");
      out.write("  /* Disabled inputs styling */\n");
      out.write("  input[disabled], select[disabled] {\n");
      out.write("    color: #9fa8da;\n");
      out.write("    cursor: not-allowed;\n");
      out.write("    background: #e4e9ff;\n");
      out.write("    box-shadow: inset 6px 6px 10px #b1b8f7, inset -6px -6px 10px #ffffff;\n");
      out.write("  }\n");
      out.write("  /* Focus states */\n");
      out.write("  input[type=\"text\"]:focus,\n");
      out.write("  input[type=\"email\"]:focus,\n");
      out.write("  input[type=\"password\"]:focus,\n");
      out.write("  input[type=\"date\"]:focus,\n");
      out.write("  input[type=\"tel\"]:focus,\n");
      out.write("  select:focus {\n");
      out.write("    outline: none;\n");
      out.write("    color: #1a237e;\n");
      out.write("    box-shadow:\n");
      out.write("      inset 2px 2px 6px #9aa8ff,\n");
      out.write("      inset -2px -2px 6px #d8e0ff,\n");
      out.write("      0 0 10px #304ffeaa;\n");
      out.write("  }\n");
      out.write("  /* Button style */\n");
      out.write("  button {\n");
      out.write("    margin-top: 40px;\n");
      out.write("    width: 100%;\n");
      out.write("    padding: 16px 0;\n");
      out.write("    background: linear-gradient(135deg, #304ffe, #536dfe);\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 30px;\n");
      out.write("    color: white;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 1.25rem;\n");
      out.write("    cursor: pointer;\n");
      out.write("    box-shadow: 0 8px 20px rgba(48,79,254,0.6);\n");
      out.write("    transition: background 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("  }\n");
      out.write("  button:hover {\n");
      out.write("    background: linear-gradient(135deg, #2838d3, #4554e8);\n");
      out.write("    box-shadow: 0 12px 28px rgba(40,56,211,0.8);\n");
      out.write("  }\n");
      out.write("  /* Back link */\n");
      out.write("  .back-link {\n");
      out.write("    display: block;\n");
      out.write("    text-align: center;\n");
      out.write("    margin-top: 30px;\n");
      out.write("    font-weight: 600;\n");
      out.write("    color: #536dfe;\n");
      out.write("    text-decoration: none;\n");
      out.write("    font-size: 1rem;\n");
      out.write("    transition: color 0.3s ease;\n");
      out.write("  }\n");
      out.write("  .back-link:hover {\n");
      out.write("    color: #2838d3;\n");
      out.write("    text-decoration: underline;\n");
      out.write("  }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"form-container\">\n");
      out.write("    <h2>🧑‍💼 Edit User</h2>\n");
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
      out.write("    <form action=\"UserServlet\" method=\"post\" autocomplete=\"off\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"edit\" />\n");
      out.write("        <input type=\"hidden\" name=\"userId\" value=\"");
      out.print( user.getUserId() );
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"username\">Username</label>\n");
      out.write("        <input type=\"text\" id=\"username\" name=\"username\" value=\"");
      out.print( user.getUsername() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"password\">Password</label>\n");
      out.write("        <input type=\"password\" id=\"password\" name=\"password\" value=\"");
      out.print( user.getPassword() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"role\">Role</label>\n");
      out.write("        ");

            if ("librarian".equals(currentRole)) {
        
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"role\" value=\"patron\" />\n");
      out.write("            <input type=\"text\" value=\"Patron\" disabled />\n");
      out.write("        ");

            } else if ("admin".equals(currentRole)) {
        
      out.write("\n");
      out.write("            <select id=\"role\" name=\"role\" required>\n");
      out.write("                <option value=\"admin\" ");
      out.print( "admin".equals(user.getRole()) ? "selected" : "" );
      out.write(">Admin</option>\n");
      out.write("                <option value=\"librarian\" ");
      out.print( "librarian".equals(user.getRole()) ? "selected" : "" );
      out.write(">Librarian</option>\n");
      out.write("                <option value=\"patron\" ");
      out.print( "patron".equals(user.getRole()) ? "selected" : "" );
      out.write(">Patron</option>\n");
      out.write("            </select>\n");
      out.write("        ");

            } else {
        
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"role\" value=\"");
      out.print( user.getRole() );
      out.write("\" />\n");
      out.write("            <input type=\"text\" value=\"");
      out.print( user.getRole().substring(0,1).toUpperCase() + user.getRole().substring(1) );
      out.write("\" disabled />\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <label for=\"firstName\">First Name</label>\n");
      out.write("        <input type=\"text\" id=\"firstName\" name=\"firstName\" value=\"");
      out.print( user.getFirstName() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"lastName\">Last Name</label>\n");
      out.write("        <input type=\"text\" id=\"lastName\" name=\"lastName\" value=\"");
      out.print( user.getLastName() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"email\">Email</label>\n");
      out.write("        <input type=\"email\" id=\"email\" name=\"email\" value=\"");
      out.print( user.getEmail() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"dob\">Date of Birth</label>\n");
      out.write("        <input type=\"date\" id=\"dob\" name=\"dob\" value=\"");
      out.print( user.getDob() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"phoneNumber\">Phone Number</label>\n");
      out.write("        <input type=\"tel\" id=\"phoneNumber\" name=\"phoneNumber\" value=\"");
      out.print( user.getPhoneNumber() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <button type=\"submit\">Update User</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <a class=\"back-link\" href=\"UserServlet?action=viewAll\">← Back to Users List</a>\n");
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
