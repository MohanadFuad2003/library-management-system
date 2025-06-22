package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class patron_005fhome_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write(" \n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\" />\n");
      out.write("<title>Patron Home</title>\n");
      out.write("<style>\n");
      out.write("  @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');\n");
      out.write("  body {\n");
      out.write("    margin: 0;\n");
      out.write("    font-family: 'Roboto', sans-serif;\n");
      out.write("    background: linear-gradient(135deg, #f5f7fa, #c3cfe2);\n");
      out.write("    display: flex;\n");
      out.write("    justify-content: center;\n");
      out.write("    align-items: center;\n");
      out.write("    height: 100vh;\n");
      out.write("    color: #34495e;\n");
      out.write("  }\n");
      out.write("  .container {\n");
      out.write("    background: white;\n");
      out.write("    padding: 40px 60px;\n");
      out.write("    border-radius: 20px;\n");
      out.write("    box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1);\n");
      out.write("    text-align: center;\n");
      out.write("    max-width: 480px;\n");
      out.write("    width: 90%;\n");
      out.write("  }\n");
      out.write("  h2 {\n");
      out.write("    font-size: 2.5rem;\n");
      out.write("    margin-bottom: 20px;\n");
      out.write("    user-select: none;\n");
      out.write("  }\n");
      out.write("  p {\n");
      out.write("    font-size: 1.2rem;\n");
      out.write("    font-weight: 500;\n");
      out.write("    color: #7f8c8d;\n");
      out.write("  }\n");
      out.write("  strong {\n");
      out.write("    color: #2980b9;\n");
      out.write("    font-weight: 700;\n");
      out.write("  }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("  <div class=\"container\" role=\"main\" aria-label=\"Patron Home Page\">\n");
      out.write("    <h2>📖 Welcome, Patron</h2>\n");
      out.write("    <p>You are now logged in as a <strong>Patron</strong>.</p>\n");
      out.write("  </div>\n");
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
