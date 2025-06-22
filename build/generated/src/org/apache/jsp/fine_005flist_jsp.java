package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import models.Fine;
import models.User;
import java.text.SimpleDateFormat;

public final class fine_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\" />\n");
      out.write("<title>Manage Fines - Admin Dashboard</title>\n");
      out.write("<style>\n");
      out.write("  @import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css');\n");
      out.write("\n");
      out.write("  body {\n");
      out.write("    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n");
      out.write("    background: #f0f4f9;\n");
      out.write("    margin: 0; \n");
      out.write("    padding: 25px 20px;\n");
      out.write("    color: #2c3e50;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  .container {\n");
      out.write("    max-width: 1200px;\n");
      out.write("    margin: auto;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  h2 {\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 2.5rem;\n");
      out.write("    color: #34495e;\n");
      out.write("    text-align: center;\n");
      out.write("    margin-bottom: 40px;\n");
      out.write("    letter-spacing: 0.07em;\n");
      out.write("    text-transform: uppercase;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Messages */\n");
      out.write("  .message-box {\n");
      out.write("    margin-bottom: 25px;\n");
      out.write("    padding: 15px 25px;\n");
      out.write("    border-radius: 12px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 16px;\n");
      out.write("    text-align: center;\n");
      out.write("  }\n");
      out.write("  .message-success {\n");
      out.write("    background-color: #d4edda;\n");
      out.write("    color: #155724;\n");
      out.write("    border: 1px solid #c3e6cb;\n");
      out.write("  }\n");
      out.write("  .message-error {\n");
      out.write("    background-color: #f8d7da;\n");
      out.write("    color: #721c24;\n");
      out.write("    border: 1px solid #f5c6cb;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Top Actions */\n");
      out.write("  .top-actions {\n");
      out.write("    text-align: right;\n");
      out.write("    margin-bottom: 30px;\n");
      out.write("  }\n");
      out.write("  .top-actions a.btn-dashboard {\n");
      out.write("    background: linear-gradient(135deg, #536dfe, #304ffe);\n");
      out.write("    color: white;\n");
      out.write("    padding: 12px 28px;\n");
      out.write("    border-radius: 30px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 16px;\n");
      out.write("    text-decoration: none;\n");
      out.write("    box-shadow: 0 6px 15px rgba(48,79,254,0.5);\n");
      out.write("    display: inline-flex;\n");
      out.write("    align-items: center;\n");
      out.write("    gap: 10px;\n");
      out.write("    transition: background 0.3s ease;\n");
      out.write("  }\n");
      out.write("  .top-actions a.btn-dashboard:hover {\n");
      out.write("    background: linear-gradient(135deg, #304ffe, #536dfe);\n");
      out.write("    box-shadow: 0 8px 25px rgba(48,79,254,0.8);\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Search form */\n");
      out.write("  .search-form {\n");
      out.write("    background: white;\n");
      out.write("    padding: 20px 30px;\n");
      out.write("    border-radius: 18px;\n");
      out.write("    display: flex;\n");
      out.write("    gap: 25px;\n");
      out.write("    flex-wrap: wrap;\n");
      out.write("    align-items: center;\n");
      out.write("    box-shadow: 0 4px 20px rgba(0,0,0,0.05);\n");
      out.write("    margin-bottom: 40px;\n");
      out.write("  }\n");
      out.write("  .search-group {\n");
      out.write("    flex: 1 1 180px;\n");
      out.write("    display: flex;\n");
      out.write("    flex-direction: column;\n");
      out.write("  }\n");
      out.write("  .search-group label {\n");
      out.write("    font-weight: 700;\n");
      out.write("    margin-bottom: 6px;\n");
      out.write("    color: #34495e;\n");
      out.write("  }\n");
      out.write("  .search-group input[type=\"text\"],\n");
      out.write("  .search-group select {\n");
      out.write("    padding: 12px 14px;\n");
      out.write("    border-radius: 12px;\n");
      out.write("    border: 1.5px solid #ccc;\n");
      out.write("    font-size: 15px;\n");
      out.write("    transition: border-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("  }\n");
      out.write("  .search-group input[type=\"text\"]:focus,\n");
      out.write("  .search-group select:focus {\n");
      out.write("    outline: none;\n");
      out.write("    border-color: #304ffe;\n");
      out.write("    box-shadow: 0 0 8px #304ffe66;\n");
      out.write("  }\n");
      out.write("  .search-form button {\n");
      out.write("    background: #304ffe;\n");
      out.write("    color: white;\n");
      out.write("    padding: 14px 35px;\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 30px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 16px;\n");
      out.write("    cursor: pointer;\n");
      out.write("    box-shadow: 0 6px 18px rgba(48,79,254,0.5);\n");
      out.write("    transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("  }\n");
      out.write("  .search-form button:hover {\n");
      out.write("    background-color: #2536cc;\n");
      out.write("    box-shadow: 0 8px 25px rgba(37,54,204,0.7);\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Card list */\n");
      out.write("  .fine-list {\n");
      out.write("    display: grid;\n");
      out.write("    grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));\n");
      out.write("    gap: 25px;\n");
      out.write("  }\n");
      out.write("  .fine-card {\n");
      out.write("    background: white;\n");
      out.write("    border-radius: 20px;\n");
      out.write("    padding: 25px 30px;\n");
      out.write("    box-shadow: 0 8px 20px rgba(0,0,0,0.08);\n");
      out.write("    display: flex;\n");
      out.write("    flex-direction: column;\n");
      out.write("    gap: 14px;\n");
      out.write("    position: relative;\n");
      out.write("  }\n");
      out.write("  .fine-id {\n");
      out.write("    position: absolute;\n");
      out.write("    top: 20px;\n");
      out.write("    right: 25px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    color: #7f8c8d;\n");
      out.write("    font-size: 0.85rem;\n");
      out.write("    user-select: none;\n");
      out.write("  }\n");
      out.write("  .fine-row {\n");
      out.write("    display: flex;\n");
      out.write("    justify-content: space-between;\n");
      out.write("    gap: 12px;\n");
      out.write("    flex-wrap: wrap;\n");
      out.write("  }\n");
      out.write("  .fine-row > div {\n");
      out.write("    flex: 1 1 45%;\n");
      out.write("    min-width: 130px;\n");
      out.write("  }\n");
      out.write("  .label {\n");
      out.write("    font-weight: 700;\n");
      out.write("    color: #34495e;\n");
      out.write("    font-size: 0.9rem;\n");
      out.write("    margin-bottom: 4px;\n");
      out.write("    user-select: none;\n");
      out.write("  }\n");
      out.write("  .value {\n");
      out.write("    font-size: 1.05rem;\n");
      out.write("    font-weight: 600;\n");
      out.write("    color: #2c3e50;\n");
      out.write("    word-break: break-word;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Status badge */\n");
      out.write("  .status-badge {\n");
      out.write("    display: inline-flex;\n");
      out.write("    align-items: center;\n");
      out.write("    gap: 6px;\n");
      out.write("    padding: 6px 16px;\n");
      out.write("    border-radius: 30px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 0.95rem;\n");
      out.write("    user-select: none;\n");
      out.write("  }\n");
      out.write("  .status-paid {\n");
      out.write("    background-color: #27ae60;\n");
      out.write("    color: #dff5e1;\n");
      out.write("    box-shadow: 0 2px 8px #1e8449aa;\n");
      out.write("  }\n");
      out.write("  .status-unpaid {\n");
      out.write("    background-color: #e74c3c;\n");
      out.write("    color: #f9d6d5;\n");
      out.write("    box-shadow: 0 2px 8px #a9322699;\n");
      out.write("  }\n");
      out.write("  .status-badge i {\n");
      out.write("    font-size: 1.2rem;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Actions container */\n");
      out.write("  .actions-cell {\n");
      out.write("    margin-top: 20px;\n");
      out.write("    display: flex;\n");
      out.write("    gap: 12px;\n");
      out.write("    flex-wrap: wrap;\n");
      out.write("    justify-content: flex-start;\n");
      out.write("  }\n");
      out.write("  .btn {\n");
      out.write("    display: inline-flex;\n");
      out.write("    align-items: center;\n");
      out.write("    gap: 8px;\n");
      out.write("    padding: 10px 22px;\n");
      out.write("    border-radius: 30px;\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 0.9rem;\n");
      out.write("    border: none;\n");
      out.write("    cursor: pointer;\n");
      out.write("    user-select: none;\n");
      out.write("    transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("    text-decoration: none;\n");
      out.write("    color: white;\n");
      out.write("    white-space: nowrap;\n");
      out.write("  }\n");
      out.write("  .btn:focus-visible {\n");
      out.write("    outline: 3px solid #2980b9;\n");
      out.write("    outline-offset: 3px;\n");
      out.write("  }\n");
      out.write("  .btn-paid {\n");
      out.write("    background-color: #27ae60;\n");
      out.write("    box-shadow: 0 4px 15px #1e8449a6;\n");
      out.write("  }\n");
      out.write("  .btn-paid:hover:not([disabled]) {\n");
      out.write("    background-color: #1e8449;\n");
      out.write("    box-shadow: 0 6px 22px #1e8449d8;\n");
      out.write("  }\n");
      out.write("  .btn-unpaid {\n");
      out.write("    background-color: #e74c3c;\n");
      out.write("    box-shadow: 0 4px 15px #a9322699;\n");
      out.write("  }\n");
      out.write("  .btn-unpaid:hover:not([disabled]) {\n");
      out.write("    background-color: #a93226;\n");
      out.write("    box-shadow: 0 6px 22px #a9322699;\n");
      out.write("  }\n");
      out.write("  .btn-edit {\n");
      out.write("    background-color: #2980b9;\n");
      out.write("    box-shadow: 0 4px 15px #1c5980bb;\n");
      out.write("  }\n");
      out.write("  .btn-edit:hover {\n");
      out.write("    background-color: #1c5980;\n");
      out.write("    box-shadow: 0 6px 22px #1c5980cc;\n");
      out.write("  }\n");
      out.write("  .btn-delete {\n");
      out.write("    background-color: #7f8c8d;\n");
      out.write("    box-shadow: 0 4px 15px #4b545599;\n");
      out.write("  }\n");
      out.write("  .btn-delete:hover {\n");
      out.write("    background-color: #4b5455;\n");
      out.write("    box-shadow: 0 6px 22px #4b545599;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Days overdue badge */\n");
      out.write("  .days-overdue {\n");
      out.write("    font-weight: 700;\n");
      out.write("    font-size: 1rem;\n");
      out.write("    color: #e74c3c;\n");
      out.write("    background: #fbeaea;\n");
      out.write("    border-radius: 20px;\n");
      out.write("    padding: 4px 12px;\n");
      out.write("    user-select: none;\n");
      out.write("    display: inline-block;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* No data */\n");
      out.write("  .no-data {\n");
      out.write("    font-style: italic;\n");
      out.write("    color: #7f8c8d;\n");
      out.write("    text-align: center;\n");
      out.write("    padding: 40px 0;\n");
      out.write("    font-size: 1.2rem;\n");
      out.write("  }\n");
      out.write("\n");
      out.write("  /* Responsive tweaks */\n");
      out.write("  @media (max-width: 700px) {\n");
      out.write("    .fine-row > div {\n");
      out.write("      flex: 1 1 100%;\n");
      out.write("    }\n");
      out.write("    .actions-cell {\n");
      out.write("      justify-content: center;\n");
      out.write("    }\n");
      out.write("  }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("  <h2>Manage Fines</h2>\n");
      out.write("\n");
      out.write("  ");
      out.write("\n");
      out.write("  ");

    String message = (String) session.getAttribute("message");
    String status = (String) session.getAttribute("status");
    if (message != null) {
  
      out.write("\n");
      out.write("    <div class=\"message-box ");
      out.print( "success".equals(status) ? "message-success" : "message-error" );
      out.write("\" role=\"alert\" aria-live=\"assertive\">\n");
      out.write("      ");
      out.print( message );
      out.write("\n");
      out.write("    </div>\n");
      out.write("  ");

    session.removeAttribute("message");
    session.removeAttribute("status");
    }
  
      out.write("\n");
      out.write("\n");
      out.write("  ");
      out.write("\n");
      out.write("  ");

    User currentUser = (User) session.getAttribute("currentUser");
    String role = currentUser != null ? currentUser.getRole() : "";
    String backUrl = "login.jsp";
    if ("admin".equals(role)) {
      backUrl = "AdminDashboardServlet";
    } else if ("librarian".equals(role)) {
      backUrl = "librarian_dashboard.jsp";
    }
  
      out.write("\n");
      out.write("  <div class=\"top-actions\">\n");
      out.write("    <a href=\"");
      out.print( backUrl );
      out.write("\" class=\"btn-dashboard\" aria-label=\"Back to Dashboard\">\n");
      out.write("      <i class=\"fa fa-home\"></i> Dashboard\n");
      out.write("    </a>\n");
      out.write("  </div>\n");
      out.write("\n");
      out.write("  ");
      out.write("\n");
      out.write("  <form method=\"get\" action=\"FineServlet\" class=\"search-form\" role=\"search\" aria-label=\"Search Fines\">\n");
      out.write("    <input type=\"hidden\" name=\"action\" value=\"viewAll\" />\n");
      out.write("    \n");
      out.write("    <div class=\"search-group\">\n");
      out.write("      <label for=\"searchUser\">User</label>\n");
      out.write("      <input type=\"text\" id=\"searchUser\" name=\"searchUser\" \n");
      out.write("             value=\"");
      out.print( request.getParameter("searchUser") != null ? request.getParameter("searchUser") : "" );
      out.write("\" \n");
      out.write("             placeholder=\"First or Last Name\" autocomplete=\"off\" />\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"search-group\">\n");
      out.write("      <label for=\"searchBook\">Book Title</label>\n");
      out.write("      <input type=\"text\" id=\"searchBook\" name=\"searchBook\" \n");
      out.write("             value=\"");
      out.print( request.getParameter("searchBook") != null ? request.getParameter("searchBook") : "" );
      out.write("\" \n");
      out.write("             placeholder=\"Book Title\" autocomplete=\"off\" />\n");
      out.write("    </div>\n");
      out.write("    \n");
      out.write("    <div class=\"search-group\">\n");
      out.write("      <label for=\"searchStatus\">Status</label>\n");
      out.write("      <select id=\"searchStatus\" name=\"searchStatus\" aria-label=\"Filter by payment status\">\n");
      out.write("        <option value=\"\" ");
      out.print( (request.getParameter("searchStatus") == null || request.getParameter("searchStatus").isEmpty()) ? "selected" : "" );
      out.write(">All</option>\n");
      out.write("        <option value=\"paid\" ");
      out.print( "paid".equalsIgnoreCase(request.getParameter("searchStatus")) ? "selected" : "" );
      out.write(">Paid</option>\n");
      out.write("        <option value=\"unpaid\" ");
      out.print( "unpaid".equalsIgnoreCase(request.getParameter("searchStatus")) ? "selected" : "" );
      out.write(">Unpaid</option>\n");
      out.write("      </select>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <button type=\"submit\" aria-label=\"Search fines\"><i class=\"fa fa-magnifying-glass\"></i> Search</button>\n");
      out.write("  </form>\n");
      out.write("\n");
      out.write("  ");
      out.write("\n");
      out.write("  ");

    List<Fine> fines = (List<Fine>) request.getAttribute("fines");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    if (fines != null && !fines.isEmpty()) {
  
      out.write("\n");
      out.write("    <div class=\"fine-list\" role=\"list\" aria-label=\"List of fines\">\n");
      out.write("      ");
 for (Fine fine : fines) { 
           java.util.Date due = fine.getDueDate();
           java.util.Date paid = fine.getPaidDate();
           long daysOverdue = 0;
           java.util.Date today = new java.util.Date();

           if (due != null) {
             if ("paid".equalsIgnoreCase(fine.getPaymentStatus()) && paid != null) {
               daysOverdue = (paid.getTime() - due.getTime()) / (1000 * 60 * 60 * 24);
             } else {
               daysOverdue = (today.getTime() - due.getTime()) / (1000 * 60 * 60 * 24);
             }
             if (daysOverdue < 0) daysOverdue = 0;
           }
      
      out.write("\n");
      out.write("      <article class=\"fine-card\" role=\"listitem\" aria-label=\"Fine ID ");
      out.print( fine.getFineId() );
      out.write("\">\n");
      out.write("        <div class=\"fine-id\">#");
      out.print( fine.getFineId() );
      out.write("</div>\n");
      out.write("\n");
      out.write("        <div class=\"fine-row\">\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">User</div>\n");
      out.write("            <div class=\"value\">");
      out.print( fine.getUserFirstName() );
      out.write(' ');
      out.print( fine.getUserLastName() );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Book</div>\n");
      out.write("            <div class=\"value\" title=\"");
      out.print( fine.getBookTitle() );
      out.write('"');
      out.write('>');
      out.print( fine.getBookTitle().length() > 30 ? fine.getBookTitle().substring(0, 27) + "…" : fine.getBookTitle() );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"fine-row\">\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Amount (JOD)</div>\n");
      out.write("            <div class=\"value\">");
      out.print( String.format("%.2f", fine.getFineAmount()) );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Status</div>\n");
      out.write("            ");
 if ("paid".equalsIgnoreCase(fine.getPaymentStatus())) { 
      out.write("\n");
      out.write("              <span class=\"status-badge status-paid\" aria-label=\"Paid fine\">\n");
      out.write("                <i class=\"fa fa-check-circle\"></i> Paid\n");
      out.write("              </span>\n");
      out.write("            ");
 } else { 
      out.write("\n");
      out.write("              <span class=\"status-badge status-unpaid\" aria-label=\"Unpaid fine\">\n");
      out.write("                <i class=\"fa fa-clock\"></i> Unpaid\n");
      out.write("              </span>\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"fine-row\">\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Borrow Date</div>\n");
      out.write("            <div class=\"value\">");
      out.print( fine.getBorrowDate() != null ? sdf.format(fine.getBorrowDate()) : "—" );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Due Date</div>\n");
      out.write("            <div class=\"value\">");
      out.print( fine.getDueDate() != null ? sdf.format(fine.getDueDate()) : "—" );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"fine-row\">\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Paid Date</div>\n");
      out.write("            <div class=\"value\">");
      out.print( fine.getPaidDate() != null ? sdf.format(fine.getPaidDate()) : "—" );
      out.write("</div>\n");
      out.write("          </div>\n");
      out.write("          <div>\n");
      out.write("            <div class=\"label\">Days Overdue</div>\n");
      out.write("            <div class=\"value\">\n");
      out.write("              ");
 if (daysOverdue > 0) { 
      out.write("\n");
      out.write("                <span class=\"days-overdue\" aria-label=\"");
      out.print( daysOverdue );
      out.write(" days overdue\">");
      out.print( daysOverdue );
      out.write("</span>\n");
      out.write("              ");
 } else { 
      out.write("\n");
      out.write("                0\n");
      out.write("              ");
 } 
      out.write("\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"actions-cell\" role=\"group\" aria-label=\"Actions for fine ID ");
      out.print( fine.getFineId() );
      out.write("\">\n");
      out.write("          ");
 if ("unpaid".equalsIgnoreCase(fine.getPaymentStatus())) { 
      out.write("\n");
      out.write("            <a href=\"FineServlet?action=markPaid&fineId=");
      out.print( fine.getFineId() );
      out.write("\" \n");
      out.write("               class=\"btn btn-paid\" \n");
      out.write("               onclick=\"return confirm('Mark this fine as paid?');\"\n");
      out.write("               aria-label=\"Mark fine ");
      out.print( fine.getFineId() );
      out.write(" as paid\">\n");
      out.write("              <i class=\"fa fa-check\"></i> Mark Paid\n");
      out.write("            </a>\n");
      out.write("          ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("          ");
 if ("admin".equals(role)) { 
      out.write("\n");
      out.write("            <a href=\"FineServlet?action=editForm&fineId=");
      out.print( fine.getFineId() );
      out.write("\" class=\"btn btn-edit\" aria-label=\"Edit fine ");
      out.print( fine.getFineId() );
      out.write("\">\n");
      out.write("              <i class=\"fa fa-pen\"></i> Edit\n");
      out.write("            </a>\n");
      out.write("            <a href=\"FineServlet?action=delete&fineId=");
      out.print( fine.getFineId() );
      out.write("\" \n");
      out.write("               class=\"btn btn-delete\" \n");
      out.write("               onclick=\"return confirm('Are you sure you want to delete this fine?');\"\n");
      out.write("               aria-label=\"Delete fine ");
      out.print( fine.getFineId() );
      out.write("\">\n");
      out.write("              <i class=\"fa fa-trash\"></i> Delete\n");
      out.write("            </a>\n");
      out.write("          ");
 } 
      out.write("\n");
      out.write("        </div>\n");
      out.write("      </article>\n");
      out.write("      ");
 } 
      out.write("\n");
      out.write("    </div>\n");
      out.write("  ");

    } else {
  
      out.write("\n");
      out.write("    <div class=\"no-data\" role=\"alert\" aria-live=\"polite\">No fines found matching the criteria.</div>\n");
      out.write("  ");

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
