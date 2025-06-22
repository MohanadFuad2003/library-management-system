package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.LibraryStatistics;
import models.User;

public final class reports_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <title>📊 Library Reports & Statistics</title>\n");
      out.write("    <style>\n");
      out.write("        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap');\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            font-family: 'Inter', sans-serif;\n");
      out.write("            margin: 0;\n");
      out.write("            background: linear-gradient(120deg, #f6f8fc, #eaf0f8);\n");
      out.write("            color: #2c3e50;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 960px;\n");
      out.write("            margin: 60px auto;\n");
      out.write("            padding: 40px 30px;\n");
      out.write("            background: white;\n");
      out.write("            border-radius: 18px;\n");
      out.write("            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.08);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 28px;\n");
      out.write("            margin-bottom: 35px;\n");
      out.write("            color: #34495e;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-btn {\n");
      out.write("            display: inline-block;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            background: linear-gradient(90deg, #2980b9, #3498db);\n");
      out.write("            color: #fff;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: 600;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-btn:hover {\n");
      out.write("            background: linear-gradient(90deg, #21618c, #2980b9);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .stats-list {\n");
      out.write("            display: grid;\n");
      out.write("            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));\n");
      out.write("            gap: 25px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .stat-box {\n");
      out.write("            background-color: #f4f6fb;\n");
      out.write("            border-left: 6px solid #2980b9;\n");
      out.write("            border-radius: 14px;\n");
      out.write("            padding: 25px 20px;\n");
      out.write("            text-align: left;\n");
      out.write("            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.05);\n");
      out.write("            transition: transform 0.2s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .stat-box:hover {\n");
      out.write("            transform: scale(1.02);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .stat-title {\n");
      out.write("            font-weight: 600;\n");
      out.write("            font-size: 16px;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            gap: 8px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .stat-value {\n");
      out.write("            font-size: 2.2rem;\n");
      out.write("            font-weight: 800;\n");
      out.write("            color: #2c3e50;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .icon {\n");
      out.write("            font-size: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .green-border { border-left-color: #27ae60; }\n");
      out.write("        .red-border   { border-left-color: #c0392b; }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            color: #c0392b;\n");
      out.write("            font-weight: 600;\n");
      out.write("            text-align: center;\n");
      out.write("            margin: 40px 0;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    ");

        User currentUser = (User) session.getAttribute("currentUser");
        String role = currentUser != null ? currentUser.getRole() : "";
        String backUrl = "login.jsp";
        if ("admin".equals(role)) {
            backUrl = "AdminDashboardServlet";
        } else if ("librarian".equals(role)) {
            backUrl = "librarian_dashboard.jsp";
        }
    
      out.write("\n");
      out.write("    <a href=\"");
      out.print( backUrl );
      out.write("\" class=\"back-btn\">← Back to Dashboard</a>\n");
      out.write("    <h2>📊 Library Reports & Statistics</h2>\n");
      out.write("\n");
      out.write("    ");

        String error = (String) request.getAttribute("error");
        if (error != null) {
    
      out.write("\n");
      out.write("        <div class=\"error\">");
      out.print( error );
      out.write("</div>\n");
      out.write("    ");

        } else {
            LibraryStatistics stats = (LibraryStatistics) request.getAttribute("stats");
            if (stats != null) {
    
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"stats-list\">\n");
      out.write("        <div class=\"stat-box\">\n");
      out.write("            <div class=\"stat-title\"><span class=\"icon\">📚</span> Total Books</div>\n");
      out.write("            <div class=\"stat-value\">");
      out.print( stats.getTotalBooks() );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"stat-box\">\n");
      out.write("            <div class=\"stat-title\"><span class=\"icon\">🧍‍♂️</span> Active Borrowers</div>\n");
      out.write("            <div class=\"stat-value\">");
      out.print( stats.getActiveBorrowers() );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"stat-box\">\n");
      out.write("            <div class=\"stat-title\"><span class=\"icon\">📘</span> Reserved Books</div>\n");
      out.write("            <div class=\"stat-value\">");
      out.print( stats.getReservedBooks() );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"stat-box green-border\">\n");
      out.write("            <div class=\"stat-title\"><span class=\"icon\">💰</span> Total Fines Collected (JOD)</div>\n");
      out.write("            <div class=\"stat-value\">");
      out.print( String.format("%.2f", stats.getTotalFinesCollected()) );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"stat-box red-border\">\n");
      out.write("            <div class=\"stat-title\"><span class=\"icon\">⚠️</span> Outstanding Fines (JOD)</div>\n");
      out.write("            <div class=\"stat-value\">");
      out.print( String.format("%.2f", stats.getOutstandingFines()) );
      out.write("</div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");

            } else {
    
      out.write("\n");
      out.write("        <div class=\"error\">📉 No statistics available at the moment.</div>\n");
      out.write("    ");

            }
        }
    
      out.write("\n");
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
