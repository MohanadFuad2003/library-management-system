package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import models.Transaction;

public final class add_005ffine_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <title>Add New Fine</title>\n");
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
      out.write("            background: linear-gradient(45deg, #fbc2eb, #a6c1ee, #fbc2eb, #a6c1ee);\n");
      out.write("            background-size: 400% 400%;\n");
      out.write("            animation: animatedBackground 12s ease infinite;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            padding: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes animatedBackground {\n");
      out.write("            0% { background-position: 0% 50%; }\n");
      out.write("            50% { background-position: 100% 50%; }\n");
      out.write("            100% { background-position: 0% 50%; }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            background: #ffffffcc;\n");
      out.write("            backdrop-filter: blur(10px);\n");
      out.write("            padding: 40px 30px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);\n");
      out.write("            max-width: 500px;\n");
      out.write("            width: 100%;\n");
      out.write("            position: relative;\n");
      out.write("            animation: slideIn 1s ease-out;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes slideIn {\n");
      out.write("            from { opacity: 0; transform: translateY(20px); }\n");
      out.write("            to { opacity: 1; transform: translateY(0); }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container::before {\n");
      out.write("            content: \"\";\n");
      out.write("            position: absolute;\n");
      out.write("            top: -60px;\n");
      out.write("            left: calc(50% - 35px);\n");
      out.write("            width: 70px;\n");
      out.write("            height: 70px;\n");
      out.write("            background: url('https://cdn-icons-png.flaticon.com/512/3248/3248676.png') no-repeat center;\n");
      out.write("            background-size: cover;\n");
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
      out.write("            margin-top: 30px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            font-size: 26px;\n");
      out.write("            color: #2c3e50;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form {\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction: column;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-weight: 600;\n");
      out.write("            margin-bottom: 6px;\n");
      out.write("            color: #444;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input {\n");
      out.write("            padding: 12px 15px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            background: #f5f7fa;\n");
      out.write("            box-shadow: inset 3px 3px 6px #d1d9e6, inset -3px -3px 6px #ffffff;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            font-size: 15px;\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input:focus {\n");
      out.write("            outline: none;\n");
      out.write("            background: #fff;\n");
      out.write("            box-shadow: 0 0 0 3px rgba(41, 128, 185, 0.3);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-submit {\n");
      out.write("            padding: 14px;\n");
      out.write("            background: #2980b9;\n");
      out.write("            color: #fff;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s ease, transform 0.2s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-submit:hover {\n");
      out.write("            background: #216a98;\n");
      out.write("            transform: scale(1.03);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-back {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-back a {\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #2980b9;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-back a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message-error {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 15px;\n");
      out.write("            color: #c0392b;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (max-width: 600px) {\n");
      out.write("            .container {\n");
      out.write("                padding: 30px 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .container::before {\n");
      out.write("                top: -50px;\n");
      out.write("                width: 60px;\n");
      out.write("                height: 60px;\n");
      out.write("                left: calc(50% - 30px);\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>💰 Add New Fine</h2>\n");
      out.write("\n");
      out.write("    ");
 String error = (String) request.getAttribute("error");
       if (error != null) { 
      out.write("\n");
      out.write("        <div class=\"message-error\">");
      out.print( error );
      out.write("</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <form action=\"FineServlet\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"add\" />\n");
      out.write("\n");
      out.write("        <label for=\"transactionId\">Transaction ID:</label>\n");
      out.write("        <input type=\"text\" name=\"transactionId\" id=\"transactionId\" required placeholder=\"e.g. BT123456\" />\n");
      out.write("\n");
      out.write("        <label for=\"fineAmount\">Fine Amount (JOD):</label>\n");
      out.write("        <input type=\"number\" name=\"fineAmount\" id=\"fineAmount\" step=\"0.01\" min=\"0\" required />\n");
      out.write("\n");
      out.write("        <button type=\"submit\" class=\"btn-submit\">Add Fine</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <div class=\"btn-back\">\n");
      out.write("        <a href=\"FineServlet?action=viewAll\">← Back to Fines List</a>\n");
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
