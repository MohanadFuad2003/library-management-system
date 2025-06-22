package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Create Account - Library System</title>\n");
      out.write("    <style>\n");
      out.write("        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            margin: 0;\n");
      out.write("            font-family: 'Inter', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #f8f4e5, #f0f0f0);\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            align-items: center;\n");
      out.write("            height: 100vh;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .form-container {\n");
      out.write("            background: #ffffff;\n");
      out.write("            max-width: 520px;\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 40px 32px;\n");
      out.write("            border-radius: 16px;\n");
      out.write("            box-shadow: 0 12px 28px rgba(0, 0, 0, 0.1);\n");
      out.write("            animation: fadeIn 0.5s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from { opacity: 0; transform: translateY(30px); }\n");
      out.write("            to { opacity: 1; transform: translateY(0); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 28px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            color: #3e3e3e;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2::before {\n");
      out.write("            content: \"📚 \";\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-weight: 600;\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 14px;\n");
      out.write("            margin-bottom: 6px;\n");
      out.write("            color: #4a4a4a;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input, select {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            font-size: 15px;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            background: #fcfcfc;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            transition: border 0.3s, box-shadow 0.3s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input:focus, select:focus {\n");
      out.write("            border-color: #2f7b5b;\n");
      out.write("            box-shadow: 0 0 6px rgba(47, 123, 91, 0.3);\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button {\n");
      out.write("            margin-top: 28px;\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 14px;\n");
      out.write("            background: linear-gradient(90deg, #4e944f, #1d5c42);\n");
      out.write("            color: #fff;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s, transform 0.2s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button:hover {\n");
      out.write("            background: linear-gradient(90deg, #1d5c42, #4e944f);\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 10px 16px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            font-weight: 600;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .success {\n");
      out.write("            background-color: #e9f9ef;\n");
      out.write("            border: 1px solid #a2e4bd;\n");
      out.write("            color: #27ae60;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            background-color: #fef0f0;\n");
      out.write("            border: 1px solid #f5b1af;\n");
      out.write("            color: #e74c3c;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .login-link {\n");
      out.write("            margin-top: 25px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .login-link a {\n");
      out.write("            color: #2f7b5b;\n");
      out.write("            font-weight: 600;\n");
      out.write("            text-decoration: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .login-link a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"form-container\">\n");
      out.write("    <h2>Join the Library Universe</h2>\n");
      out.write("\n");
      out.write("    <form action=\"RegisterServlet\" method=\"post\">\n");
      out.write("        <label>👤 Username</label>\n");
      out.write("        <input type=\"text\" name=\"username\" required />\n");
      out.write("\n");
      out.write("        <label>🔒 Password</label>\n");
      out.write("        <input type=\"password\" name=\"password\" required />\n");
      out.write("\n");
      out.write("        <label>🎓 Role</label>\n");
      out.write("        <select name=\"role\" required>\n");
      out.write("            <option value=\"\" disabled selected>Select your role</option>\n");
      out.write("            <option value=\"librarian\">📘 Librarian</option>\n");
      out.write("            <option value=\"patron\">📖 Patron</option>\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("        <label>🧑 First Name</label>\n");
      out.write("        <input type=\"text\" name=\"firstName\" required />\n");
      out.write("\n");
      out.write("        <label>👨‍👩‍👧‍👦 Last Name</label>\n");
      out.write("        <input type=\"text\" name=\"lastName\" required />\n");
      out.write("\n");
      out.write("        <label>📧 Email</label>\n");
      out.write("        <input type=\"email\" name=\"email\" required />\n");
      out.write("\n");
      out.write("        <label>📞 Phone Number</label>\n");
      out.write("        <input type=\"tel\" name=\"phoneNumber\" required />\n");
      out.write("\n");
      out.write("        <label>🎂 Date of Birth</label>\n");
      out.write("        <input type=\"date\" name=\"dob\" required />\n");
      out.write("\n");
      out.write("        <button type=\"submit\">📚 Register Account</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    ");

        String message = (String) request.getAttribute("message");
        String status  = (String) request.getAttribute("status");
        if (message != null) {
    
      out.write("\n");
      out.write("    <div class=\"message ");
      out.print( "success".equals(status) ? "success" : "error" );
      out.write("\">\n");
      out.write("        ");
      out.print( message );
      out.write("\n");
      out.write("    </div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"login-link\">\n");
      out.write("        Already have an account? <a href=\"login.jsp\">Login here</a>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
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
