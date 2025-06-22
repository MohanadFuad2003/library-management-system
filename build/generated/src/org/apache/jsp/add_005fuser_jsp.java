package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.User;

public final class add_005fuser_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    User currentUser = (User) session.getAttribute("currentUser");
    String currentRole = currentUser != null ? currentUser.getRole() : "";

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Add New User</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("        * { box-sizing: border-box; margin: 0; padding: 0; }\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            font-family: 'Poppins', sans-serif;\n");
      out.write("            background: linear-gradient(-45deg, #fbc2eb, #a6c1ee, #fbc2eb, #a6c1ee);\n");
      out.write("            background-size: 400% 400%;\n");
      out.write("            animation: gradientFlow 15s ease infinite;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            padding: 40px 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes gradientFlow {\n");
      out.write("            0% { background-position: 0% 50%; }\n");
      out.write("            50% { background-position: 100% 50%; }\n");
      out.write("            100% { background-position: 0% 50%; }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-container {\n");
      out.write("            background: #ffffffee;\n");
      out.write("            backdrop-filter: blur(12px);\n");
      out.write("            padding: 50px 30px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);\n");
      out.write("            width: 100%;\n");
      out.write("            max-width: 500px;\n");
      out.write("            position: relative;\n");
      out.write("            animation: fadeInUp 1s ease-out;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes fadeInUp {\n");
      out.write("            from { opacity: 0; transform: translateY(30px); }\n");
      out.write("            to   { opacity: 1; transform: translateY(0); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-container::before {\n");
      out.write("            content: \"\";\n");
      out.write("            position: absolute;\n");
      out.write("            top: -70px;\n");
      out.write("            left: calc(50% - 35px);\n");
      out.write("            width: 70px;\n");
      out.write("            height: 70px;\n");
      out.write("            background: url('https://cdn-icons-png.flaticon.com/512/747/747376.png') no-repeat center;\n");
      out.write("            background-size: contain;\n");
      out.write("            animation: float 4s ease-in-out infinite;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes float {\n");
      out.write("            0%, 100% { transform: translateY(0); }\n");
      out.write("            50% { transform: translateY(-10px); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            color: #2c3e50;\n");
      out.write("            font-size: 26px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 15px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            color: #444;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input, select {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px 14px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            background: #f5f7fa;\n");
      out.write("            box-shadow: inset 2px 2px 6px #d1d9e6, inset -2px -2px 6px #ffffff;\n");
      out.write("            margin-top: 6px;\n");
      out.write("            font-size: 15px;\n");
      out.write("            transition: 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input:focus, select:focus {\n");
      out.write("            background: #fff;\n");
      out.write("            box-shadow: 0 0 0 3px rgba(39, 174, 96, 0.2);\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button {\n");
      out.write("            margin-top: 25px;\n");
      out.write("            padding: 14px;\n");
      out.write("            width: 100%;\n");
      out.write("            background: #27ae60;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            box-shadow: 0 5px 15px rgba(39, 174, 96, 0.2);\n");
      out.write("            transition: background 0.3s ease, transform 0.2s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button:hover {\n");
      out.write("            background: #219150;\n");
      out.write("            transform: scale(1.03);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            color: #c0392b;\n");
      out.write("            font-weight: bold;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-link {\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #2980b9;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-link:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .info-text {\n");
      out.write("            margin-top: 8px;\n");
      out.write("            font-style: italic;\n");
      out.write("            color: #666;\n");
      out.write("            font-size: 14px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (max-width: 600px) {\n");
      out.write("            .form-container {\n");
      out.write("                padding: 35px 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .form-container::before {\n");
      out.write("                width: 60px;\n");
      out.write("                height: 60px;\n");
      out.write("                top: -60px;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"form-container\">\n");
      out.write("    <h2>➕ Add New User</h2>\n");
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
      out.write("    <form action=\"UserServlet\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"add\" />\n");
      out.write("\n");
      out.write("        <label>Username</label>\n");
      out.write("        <input type=\"text\" name=\"username\" required />\n");
      out.write("\n");
      out.write("        <label>Password</label>\n");
      out.write("        <input type=\"password\" name=\"password\" required />\n");
      out.write("\n");
      out.write("        ");
 if ("librarian".equals(currentRole)) { 
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"role\" value=\"patron\" />\n");
      out.write("            <label>Role</label>\n");
      out.write("            <input type=\"text\" value=\"Patron\" readonly style=\"background:#f0f0f0; cursor:not-allowed; text-align:center;\" />\n");
      out.write("            <p class=\"info-text\">As a librarian, you can only add users with role \"Patron\".</p>\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("            <label>Role</label>\n");
      out.write("            <select name=\"role\" required>\n");
      out.write("                <option value=\"\" disabled selected>Select Role</option>\n");
      out.write("                <option value=\"admin\">Admin</option>\n");
      out.write("                <option value=\"librarian\">Librarian</option>\n");
      out.write("                <option value=\"patron\">Patron</option>\n");
      out.write("            </select>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("        <label>First Name</label>\n");
      out.write("        <input type=\"text\" name=\"firstName\" required />\n");
      out.write("\n");
      out.write("        <label>Last Name</label>\n");
      out.write("        <input type=\"text\" name=\"lastName\" required />\n");
      out.write("\n");
      out.write("        <label>Email</label>\n");
      out.write("        <input type=\"email\" name=\"email\" required />\n");
      out.write("\n");
      out.write("        <label>Date of Birth</label>\n");
      out.write("        <input type=\"date\" name=\"dob\" required />\n");
      out.write("\n");
      out.write("        <label>Phone Number</label>\n");
      out.write("        <input type=\"tel\" name=\"phoneNumber\" required />\n");
      out.write("\n");
      out.write("        <button type=\"submit\">Add User</button>\n");
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
