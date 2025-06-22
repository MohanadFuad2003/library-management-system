package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Library Login</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            font-family: 'Poppins', sans-serif;\n");
      out.write("            height: 100vh;\n");
      out.write("            background: linear-gradient(-45deg, #fceabb, #f8b500, #fceabb, #f8b500);\n");
      out.write("            background-size: 400% 400%;\n");
      out.write("            animation: gradientBG 15s ease infinite;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            overflow: hidden;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes gradientBG {\n");
      out.write("            0% { background-position: 0% 50%; }\n");
      out.write("            50% { background-position: 100% 50%; }\n");
      out.write("            100% { background-position: 0% 50%; }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .login-wrapper {\n");
      out.write("            background: #fff;\n");
      out.write("            padding: 40px 30px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);\n");
      out.write("            max-width: 430px;\n");
      out.write("            width: 100%;\n");
      out.write("            animation: fadeIn 1.2s ease-out;\n");
      out.write("            position: relative;\n");
      out.write("            z-index: 10;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from { opacity: 0; transform: translateY(30px); }\n");
      out.write("            to { opacity: 1; transform: translateY(0); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .floating-image {\n");
      out.write("            position: absolute;\n");
      out.write("            top: -110px;\n");
      out.write("            left: calc(50% - 75px);\n");
      out.write("            width: 150px;\n");
      out.write("            height: 150px;\n");
      out.write("            background-image: url('https://cdn-icons-png.flaticon.com/512/29/29302.png');\n");
      out.write("            background-size: cover;\n");
      out.write("            background-repeat: no-repeat;\n");
      out.write("            animation: float 4s ease-in-out infinite;\n");
      out.write("            z-index: 1;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes float {\n");
      out.write("            0%, 100% { transform: translateY(0); }\n");
      out.write("            50% { transform: translateY(-15px); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            color: #333;\n");
      out.write("            margin-top: 60px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            font-size: 28px;\n");
      out.write("            font-weight: 600;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-weight: 600;\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 15px;\n");
      out.write("            margin-bottom: 5px;\n");
      out.write("            color: #555;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"text\"],\n");
      out.write("        input[type=\"password\"] {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px 16px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            background: #f5f5f5;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("            font-size: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input:focus {\n");
      out.write("            outline: none;\n");
      out.write("            background: #fff;\n");
      out.write("            box-shadow: 0 0 0 3px rgba(76, 175, 80, 0.3);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            background: #4CAF50;\n");
      out.write("            color: white;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            margin-top: 25px;\n");
      out.write("            position: relative;\n");
      out.write("            overflow: hidden;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button::after {\n");
      out.write("            content: \"\";\n");
      out.write("            position: absolute;\n");
      out.write("            top: 0;\n");
      out.write("            left: 50%;\n");
      out.write("            width: 300%;\n");
      out.write("            height: 100%;\n");
      out.write("            background: rgba(255, 255, 255, 0.15);\n");
      out.write("            transform: translateX(-50%) scaleX(0);\n");
      out.write("            transform-origin: center;\n");
      out.write("            transition: transform 0.4s ease-out;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button:hover::after {\n");
      out.write("            transform: translateX(-50%) scaleX(1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            color: #d32f2f;\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: bold;\n");
      out.write("            margin-top: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .register-link {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 20px;\n");
      out.write("            font-size: 14px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .register-link a {\n");
      out.write("            color: #4CAF50;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: 600;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .register-link a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (max-width: 500px) {\n");
      out.write("            .floating-image {\n");
      out.write("                width: 100px;\n");
      out.write("                height: 100px;\n");
      out.write("                top: -80px;\n");
      out.write("                left: calc(50% - 50px);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .login-wrapper {\n");
      out.write("                padding: 30px 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            h2 {\n");
      out.write("                margin-top: 40px;\n");
      out.write("                font-size: 24px;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"floating-image\"></div>\n");
      out.write("\n");
      out.write("<div class=\"login-wrapper\">\n");
      out.write("    <h2>Welcome Back 📖</h2>\n");
      out.write("\n");
      out.write("    <form action=\"LoginServlet\" method=\"post\">\n");
      out.write("        <label for=\"username\">Username</label>\n");
      out.write("        <input type=\"text\" name=\"username\" id=\"username\" required>\n");
      out.write("\n");
      out.write("        <label for=\"password\">Password</label>\n");
      out.write("        <input type=\"password\" name=\"password\" id=\"password\" required>\n");
      out.write("\n");
      out.write("        <button type=\"submit\">Login</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    ");
 String error = (String) request.getAttribute("error");
       if (error != null) {
    
      out.write("\n");
      out.write("    <div class=\"error\">");
      out.print( error );
      out.write("</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"register-link\">\n");
      out.write("        Don't have an account? <a href=\"register.jsp\">Register here</a>\n");
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
