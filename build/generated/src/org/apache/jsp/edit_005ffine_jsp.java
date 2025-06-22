package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.Fine;

public final class edit_005ffine_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Edit Fine</title>\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
      out.write("            background: #f7f9fc;\n");
      out.write("            margin: 0; padding: 20px;\n");
      out.write("        }\n");
      out.write("        .container {\n");
      out.write("            max-width: 500px;\n");
      out.write("            margin: auto;\n");
      out.write("            background: white;\n");
      out.write("            padding: 25px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("            color: #34495e;\n");
      out.write("        }\n");
      out.write("        form {\n");
      out.write("            display: flex;\n");
      out.write("            flex-direction: column;\n");
      out.write("        }\n");
      out.write("        label {\n");
      out.write("            margin-top: 15px;\n");
      out.write("            font-weight: 600;\n");
      out.write("        }\n");
      out.write("        input, select {\n");
      out.write("            padding: 8px 10px;\n");
      out.write("            margin-top: 5px;\n");
      out.write("            border-radius: 6px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            font-size: 16px;\n");
      out.write("        }\n");
      out.write("        .btn-submit {\n");
      out.write("            margin-top: 25px;\n");
      out.write("            background-color: #2980b9;\n");
      out.write("            color: white;\n");
      out.write("            padding: 12px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        .btn-submit:hover {\n");
      out.write("            background-color: #216a98;\n");
      out.write("        }\n");
      out.write("        .btn-back {\n");
      out.write("            margin-top: 15px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("        .btn-back a {\n");
      out.write("            color: #2980b9;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("        .btn-back a:hover {\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("        .message-error {\n");
      out.write("            margin-top: 15px;\n");
      out.write("            color: #c0392b;\n");
      out.write("            font-weight: bold;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    <script>\n");
      out.write("        function onStatusChange() {\n");
      out.write("            var status = document.getElementById(\"paymentStatus\").value;\n");
      out.write("            var paidDateInput = document.getElementById(\"paidDate\");\n");
      out.write("            if (status === \"paid\") {\n");
      out.write("                paidDateInput.disabled = false;\n");
      out.write("            } else {\n");
      out.write("                paidDateInput.disabled = true;\n");
      out.write("                paidDateInput.value = \"\";\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("        window.onload = function() {\n");
      out.write("            onStatusChange();\n");
      out.write("        };\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>Edit Fine</h2>\n");
      out.write("\n");
      out.write("    ");
 Fine fine = (Fine) request.getAttribute("fine");
       if (fine == null) { 
      out.write("\n");
      out.write("        <div class=\"message-error\">Fine data not found.</div>\n");
      out.write("    ");
 } else { 
      out.write("\n");
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
      out.write("        <input type=\"hidden\" name=\"action\" value=\"edit\" />\n");
      out.write("        <input type=\"hidden\" name=\"fineId\" value=\"");
      out.print( fine.getFineId() );
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"fineAmount\">Fine Amount (JOD):</label>\n");
      out.write("        <input type=\"number\" name=\"fineAmount\" id=\"fineAmount\" step=\"0.01\" min=\"0\" required value=\"");
      out.print( fine.getFineAmount() );
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"paymentStatus\">Payment Status:</label>\n");
      out.write("        <select name=\"paymentStatus\" id=\"paymentStatus\" onchange=\"onStatusChange()\" required>\n");
      out.write("            <option value=\"unpaid\" ");
      out.print( "unpaid".equalsIgnoreCase(fine.getPaymentStatus()) ? "selected" : "" );
      out.write(">Unpaid</option>\n");
      out.write("            <option value=\"paid\" ");
      out.print( "paid".equalsIgnoreCase(fine.getPaymentStatus()) ? "selected" : "" );
      out.write(">Paid</option>\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("        <label for=\"paidDate\">Paid Date:</label>\n");
      out.write("        <input type=\"date\" name=\"paidDate\" id=\"paidDate\" value=\"");
      out.print( fine.getPaidDate() != null ? fine.getPaidDate().toString() : "" );
      out.write("\" disabled />\n");
      out.write("\n");
      out.write("        <button type=\"submit\" class=\"btn-submit\">Update Fine</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <div class=\"btn-back\">\n");
      out.write("        <a href=\"FineServlet?action=viewAll\">← Back to Fines List</a>\n");
      out.write("    </div>\n");
      out.write("    ");
 } 
      out.write("\n");
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
