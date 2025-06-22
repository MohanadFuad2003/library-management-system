package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005fdashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>Admin Dashboard</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Quicksand:wght@500;600&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("        * {\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            font-family: 'Quicksand', sans-serif;\n");
      out.write("            background: linear-gradient(120deg, #fdfbfb, #ebedee);\n");
      out.write("            animation: flowBg 20s linear infinite alternate;\n");
      out.write("            color: #2d3436;\n");
      out.write("            min-height: 100vh;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes flowBg {\n");
      out.write("            0% { background-position: 0% 50%; }\n");
      out.write("            100% { background-position: 100% 50%; }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .header {\n");
      out.write("            background-color: #88d8b0;\n");
      out.write("            color: white;\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 30px;\n");
      out.write("            font-size: 32px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            border-bottom: 4px solid #56c596;\n");
      out.write("            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.05);\n");
      out.write("            letter-spacing: 1px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .dashboard-container {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 30px;\n");
      out.write("            padding: 40px;\n");
      out.write("            max-width: 1200px;\n");
      out.write("            margin: auto;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card {\n");
      out.write("            background: #ffffffdd;\n");
      out.write("            backdrop-filter: blur(8px);\n");
      out.write("            border-radius: 16px;\n");
      out.write("            padding: 30px 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.07);\n");
      out.write("            transition: transform 0.3s ease;\n");
      out.write("            border-left: 6px solid #88d8b0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card:hover {\n");
      out.write("            transform: translateY(-6px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card h3 {\n");
      out.write("            font-size: 20px;\n");
      out.write("            margin-bottom: 10px;\n");
      out.write("            color: #34495e;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .card p {\n");
      out.write("            font-size: 28px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #27ae60;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-container {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 50px;\n");
      out.write("            padding-bottom: 40px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-container a {\n");
      out.write("            display: inline-block;\n");
      out.write("            margin: 12px;\n");
      out.write("            padding: 14px 26px;\n");
      out.write("            background: #56c596;\n");
      out.write("            color: white;\n");
      out.write("            font-weight: bold;\n");
      out.write("            text-decoration: none;\n");
      out.write("            border-radius: 50px;\n");
      out.write("            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);\n");
      out.write("            transition: all 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-container a:hover {\n");
      out.write("            background: #45b08c;\n");
      out.write("            transform: translateY(-3px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-container a.logout {\n");
      out.write("            background: #e74c3c;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-container a.logout:hover {\n");
      out.write("            background: #c0392b;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (max-width: 600px) {\n");
      out.write("            .header {\n");
      out.write("                font-size: 24px;\n");
      out.write("                padding: 20px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            .btn-container a {\n");
      out.write("                display: block;\n");
      out.write("                width: 90%;\n");
      out.write("                margin: 14px auto;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"header\">📚 Library Admin Dashboard</div>\n");
      out.write("\n");
      out.write("<div class=\"dashboard-container\">\n");
      out.write("    <div class=\"card\">\n");
      out.write("        <h3>📘 Total Books</h3>\n");
      out.write("        <p>");
      out.print( request.getAttribute("totalBooks") );
      out.write("</p>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"card\">\n");
      out.write("        <h3>👩‍🏫 Total Users</h3>\n");
      out.write("        <p>");
      out.print( request.getAttribute("totalUsers") );
      out.write("</p>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"card\">\n");
      out.write("        <h3>📖 Borrowed Books</h3>\n");
      out.write("        <p>");
      out.print( request.getAttribute("borrowedBooks") );
      out.write("</p>\n");
      out.write("    </div>\n");
      out.write("    <div class=\"card\">\n");
      out.write("        <h3>💰 Outstanding Fines</h3>\n");
      out.write("        <p>");
      out.print( request.getAttribute("outstandingFines") );
      out.write(" JOD</p>\n");
      out.write("    </div>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<div class=\"btn-container\">\n");
      out.write("    <a href=\"BookServlet?action=viewAll\">📗 Manage Books</a>\n");
      out.write("    <a href=\"UserServlet?action=viewAll\">👨‍👩‍👧 Manage Users</a>\n");
      out.write("    <a href=\"FineServlet?action=viewAll\">💳 Manage Fines</a>\n");
      out.write("    <a href=\"ReportsServlet\">📊 Reports</a>\n");
      out.write("    <a href=\"SystemConfigServlet\">⚙️ Settings</a>\n");
      out.write("    <a href=\"NotificationServlet?action=viewAll\">🔔 Notifications</a>\n");
      out.write("    <a href=\"LogoutServlet\" class=\"logout\">🚪 Logout</a>\n");
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
