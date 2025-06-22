package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import models.Notification;
import models.User;

public final class notifications_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\" />\n");
      out.write("<title>Notifications</title>\n");
      out.write("<style>\n");
      out.write("    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');\n");
      out.write("\n");
      out.write("    body {\n");
      out.write("        font-family: 'Inter', sans-serif;\n");
      out.write("        background: linear-gradient(135deg, #e0f7fa, #fff);\n");
      out.write("        margin: 0;\n");
      out.write("        padding: 25px;\n");
      out.write("        color: #2c3e50;\n");
      out.write("        min-height: 100vh;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .container {\n");
      out.write("        max-width: 900px;\n");
      out.write("        margin: auto;\n");
      out.write("        background: #fff;\n");
      out.write("        border-radius: 16px;\n");
      out.write("        box-shadow: 0 12px 30px rgba(41, 128, 185, 0.1);\n");
      out.write("        padding: 35px 40px;\n");
      out.write("        display: flex;\n");
      out.write("        flex-direction: column;\n");
      out.write("        gap: 24px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    h2 {\n");
      out.write("        text-align: center;\n");
      out.write("        color: #34495e;\n");
      out.write("        font-weight: 700;\n");
      out.write("        font-size: 2rem;\n");
      out.write("        letter-spacing: 0.05em;\n");
      out.write("        user-select: none;\n");
      out.write("        margin: 0;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Message boxes */\n");
      out.write("    .message-box {\n");
      out.write("        border-radius: 12px;\n");
      out.write("        padding: 16px 24px;\n");
      out.write("        font-weight: 700;\n");
      out.write("        font-size: 1rem;\n");
      out.write("        text-align: center;\n");
      out.write("        user-select: none;\n");
      out.write("        box-shadow: 0 3px 12px rgba(0,0,0,0.05);\n");
      out.write("    }\n");
      out.write("    .message-success {\n");
      out.write("        background-color: #d4edda;\n");
      out.write("        color: #155724;\n");
      out.write("        border: 1px solid #c3e6cb;\n");
      out.write("    }\n");
      out.write("    .message-error {\n");
      out.write("        background-color: #f8d7da;\n");
      out.write("        color: #721c24;\n");
      out.write("        border: 1px solid #f5c6cb;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Mark all button */\n");
      out.write("    form {\n");
      out.write("        display: flex;\n");
      out.write("        justify-content: flex-end;\n");
      out.write("    }\n");
      out.write("    .btn-mark-all {\n");
      out.write("        background: linear-gradient(135deg, #2980b9, #3498db);\n");
      out.write("        border: none;\n");
      out.write("        color: white;\n");
      out.write("        padding: 12px 28px;\n");
      out.write("        border-radius: 30px;\n");
      out.write("        font-weight: 600;\n");
      out.write("        font-size: 1rem;\n");
      out.write("        cursor: pointer;\n");
      out.write("        box-shadow: 0 6px 20px rgba(41, 128, 185, 0.3);\n");
      out.write("        transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        user-select: none;\n");
      out.write("    }\n");
      out.write("    .btn-mark-all:hover,\n");
      out.write("    .btn-mark-all:focus-visible {\n");
      out.write("        background: linear-gradient(135deg, #216a98, #2874a6);\n");
      out.write("        box-shadow: 0 8px 28px rgba(33, 106, 152, 0.5);\n");
      out.write("        outline: none;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Notifications table replaced by cards */\n");
      out.write("    .notifications-list {\n");
      out.write("        display: flex;\n");
      out.write("        flex-direction: column;\n");
      out.write("        gap: 16px;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .notification-card {\n");
      out.write("        background: #f9fbfd;\n");
      out.write("        border-radius: 14px;\n");
      out.write("        box-shadow: 0 4px 14px rgba(41, 128, 185, 0.08);\n");
      out.write("        padding: 18px 28px;\n");
      out.write("        display: grid;\n");
      out.write("        grid-template-columns: 1fr auto auto;\n");
      out.write("        align-items: center;\n");
      out.write("        gap: 24px;\n");
      out.write("        transition: background-color 0.3s ease;\n");
      out.write("    }\n");
      out.write("    .notification-card:hover {\n");
      out.write("        background-color: #e6f0fa;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .notification-message {\n");
      out.write("        font-size: 1.05rem;\n");
      out.write("        color: #34495e;\n");
      out.write("        user-select: text;\n");
      out.write("        word-break: break-word;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .notification-status {\n");
      out.write("        font-weight: 700;\n");
      out.write("        font-size: 0.9rem;\n");
      out.write("        padding: 6px 14px;\n");
      out.write("        border-radius: 18px;\n");
      out.write("        text-align: center;\n");
      out.write("        user-select: none;\n");
      out.write("        text-transform: uppercase;\n");
      out.write("        width: 110px;\n");
      out.write("        box-shadow: 0 2px 8px rgba(0,0,0,0.05);\n");
      out.write("    }\n");
      out.write("    .status-unread {\n");
      out.write("        background-color: #fdecea;\n");
      out.write("        color: #c0392b;\n");
      out.write("        border: 1px solid #f5c6cb;\n");
      out.write("    }\n");
      out.write("    .status-read {\n");
      out.write("        background-color: #e6f4ea;\n");
      out.write("        color: #27ae60;\n");
      out.write("        border: 1px solid #c7e0c6;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    .notification-date {\n");
      out.write("        font-size: 0.9rem;\n");
      out.write("        color: #7f8c8d;\n");
      out.write("        user-select: none;\n");
      out.write("        min-width: 110px;\n");
      out.write("        text-align: right;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Actions */\n");
      out.write("    .actions {\n");
      out.write("        display: flex;\n");
      out.write("        gap: 12px;\n");
      out.write("        justify-content: flex-end;\n");
      out.write("        min-width: 100px;\n");
      out.write("    }\n");
      out.write("    .btn {\n");
      out.write("        border-radius: 10px;\n");
      out.write("        font-weight: 600;\n");
      out.write("        font-size: 0.9rem;\n");
      out.write("        padding: 8px 16px;\n");
      out.write("        cursor: pointer;\n");
      out.write("        border: none;\n");
      out.write("        user-select: none;\n");
      out.write("        box-shadow: 0 3px 10px rgba(0,0,0,0.1);\n");
      out.write("        transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        text-decoration: none;\n");
      out.write("        color: white;\n");
      out.write("        display: inline-flex;\n");
      out.write("        align-items: center;\n");
      out.write("        justify-content: center;\n");
      out.write("        white-space: nowrap;\n");
      out.write("    }\n");
      out.write("    .btn-delete {\n");
      out.write("        background: linear-gradient(135deg, #e74c3c, #c0392b);\n");
      out.write("        box-shadow: 0 6px 18px rgba(231, 76, 60, 0.35);\n");
      out.write("    }\n");
      out.write("    .btn-delete:hover,\n");
      out.write("    .btn-delete:focus-visible {\n");
      out.write("        background: linear-gradient(135deg, #c0392b, #992d22);\n");
      out.write("        box-shadow: 0 8px 26px rgba(192, 57, 43, 0.5);\n");
      out.write("        outline: none;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Back link */\n");
      out.write("    .back-link {\n");
      out.write("        margin-top: 30px;\n");
      out.write("        display: block;\n");
      out.write("        text-align: center;\n");
      out.write("        font-weight: 700;\n");
      out.write("        font-size: 1.1rem;\n");
      out.write("        color: #2980b9;\n");
      out.write("        text-decoration: none;\n");
      out.write("        user-select: none;\n");
      out.write("        transition: color 0.3s ease;\n");
      out.write("    }\n");
      out.write("    .back-link:hover,\n");
      out.write("    .back-link:focus-visible {\n");
      out.write("        text-decoration: underline;\n");
      out.write("        color: #216a98;\n");
      out.write("        outline: none;\n");
      out.write("    }\n");
      out.write("\n");
      out.write("    /* Responsive */\n");
      out.write("    @media (max-width: 640px) {\n");
      out.write("        .notification-card {\n");
      out.write("            grid-template-columns: 1fr auto;\n");
      out.write("            gap: 16px;\n");
      out.write("        }\n");
      out.write("        .notification-date {\n");
      out.write("            text-align: left;\n");
      out.write("            min-width: auto;\n");
      out.write("            order: 3;\n");
      out.write("            font-size: 0.85rem;\n");
      out.write("        }\n");
      out.write("        .actions {\n");
      out.write("            order: 2;\n");
      out.write("            min-width: auto;\n");
      out.write("        }\n");
      out.write("    }\n");
      out.write("</style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\" role=\"main\" aria-label=\"Notifications container\">\n");
      out.write("\n");
      out.write("    <h2>Notifications</h2>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    ");

        String message = (String) session.getAttribute("message");
        if (message != null) {
    
      out.write("\n");
      out.write("        <div class=\"message-box message-success\" role=\"alert\" aria-live=\"polite\">\n");
      out.write("            ");
      out.print( message );
      out.write("\n");
      out.write("        </div>\n");
      out.write("    ");

            session.removeAttribute("message");
        }
        String error = (String) request.getAttribute("error");
        if (error != null) {
    
      out.write("\n");
      out.write("        <div class=\"message-box message-error\" role=\"alert\" aria-live=\"assertive\">\n");
      out.write("            ");
      out.print( error );
      out.write("\n");
      out.write("        </div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <form action=\"NotificationServlet\" method=\"get\" aria-label=\"Mark all notifications as read form\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"markAllRead\" />\n");
      out.write("        <button type=\"submit\" class=\"btn-mark-all\" aria-label=\"Mark all notifications as read\">Mark All as Read</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    ");

        List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
        if (notifications == null || notifications.isEmpty()) {
    
      out.write("\n");
      out.write("        <p style=\"text-align:center; font-style: italic; color: #7f8c8d;\">No notifications to display.</p>\n");
      out.write("    ");

        } else {
    
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"notifications-list\" role=\"list\" aria-label=\"List of notifications\">\n");
      out.write("        ");

            for (Notification notif : notifications) {
                String statusClass = "unread".equalsIgnoreCase(notif.getStatus()) ? "status-unread" : "status-read";
                String displayStatus = notif.getStatus().substring(0,1).toUpperCase() + notif.getStatus().substring(1).toLowerCase();
        
      out.write("\n");
      out.write("        <article class=\"notification-card\" role=\"listitem\" tabindex=\"0\" aria-label=\"Notification: ");
      out.print( notif.getMessage() );
      out.write(" status ");
      out.print( displayStatus );
      out.write("\">\n");
      out.write("            <div class=\"notification-message\">");
      out.print( notif.getMessage() );
      out.write("</div>\n");
      out.write("            <div class=\"notification-status ");
      out.print( statusClass );
      out.write('"');
      out.write('>');
      out.print( displayStatus );
      out.write("</div>\n");
      out.write("            <div class=\"notification-date\" aria-label=\"Date: ");
      out.print( notif.getNotificationDate() != null ? notif.getNotificationDate().toString() : "Unknown" );
      out.write("\">\n");
      out.write("                ");
      out.print( notif.getNotificationDate() != null ? notif.getNotificationDate().toString() : "—" );
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <div class=\"actions\">\n");
      out.write("                <a href=\"NotificationServlet?action=delete&notificationId=");
      out.print( notif.getNotificationId() );
      out.write("\" \n");
      out.write("                   class=\"btn btn-delete\"\n");
      out.write("                   onclick=\"return confirm('Are you sure you want to delete this notification?');\"\n");
      out.write("                   aria-label=\"Delete notification\">\n");
      out.write("                    Delete\n");
      out.write("                </a>\n");
      out.write("            </div>\n");
      out.write("        </article>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
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
      out.write("\" class=\"back-link\" aria-label=\"Back to dashboard\">← Back to Dashboard</a>\n");
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
