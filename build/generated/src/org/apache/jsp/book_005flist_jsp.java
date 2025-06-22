package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Map;
import models.Book;
import models.User;

public final class book_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>📚 Library Book List</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Raleway:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Raleway', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #f5f7fa, #c3cfe2);\n");
      out.write("            padding: 40px 20px;\n");
      out.write("            margin: 0;\n");
      out.write("            animation: fadeIn 1s ease-in;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from {opacity: 0;}\n");
      out.write("            to {opacity: 1;}\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 1100px;\n");
      out.write("            margin: 30px auto;\n");
      out.write("            background: rgba(255, 255, 255, 0.9);\n");
      out.write("            backdrop-filter: blur(8px);\n");
      out.write("            border-radius: 20px;\n");
      out.write("            padding: 30px 20px;\n");
      out.write("            box-shadow: 0 12px 30px rgba(0,0,0,0.1);\n");
      out.write("            overflow-x: auto;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            font-size: 32px;\n");
      out.write("            color: #2d3436;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-group {\n");
      out.write("            display: flex;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("            justify-content: center;\n");
      out.write("            gap: 15px;\n");
      out.write("            margin-bottom: 25px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn {\n");
      out.write("            background: linear-gradient(to right, #74ebd5, #acb6e5);\n");
      out.write("            color: #2d3436;\n");
      out.write("            padding: 10px 20px;\n");
      out.write("            border-radius: 25px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            text-decoration: none;\n");
      out.write("            transition: transform 0.2s ease, box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn:hover {\n");
      out.write("            transform: translateY(-3px);\n");
      out.write("            box-shadow: 0 5px 15px rgba(0,0,0,0.1);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        form {\n");
      out.write("            text-align: center;\n");
      out.write("            margin-bottom: 15px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"text\"], select {\n");
      out.write("            padding: 10px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            border: 1px solid #bbb;\n");
      out.write("            width: 220px;\n");
      out.write("            margin: 8px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button[type=\"submit\"] {\n");
      out.write("            background: #0984e3;\n");
      out.write("            color: white;\n");
      out.write("            border: none;\n");
      out.write("            padding: 10px 16px;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            font-weight: bold;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        button[type=\"submit\"]:hover {\n");
      out.write("            background: #74b9ff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: collapse;\n");
      out.write("            margin-top: 30px;\n");
      out.write("            background-color: white;\n");
      out.write("            border-radius: 15px;\n");
      out.write("            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.05);\n");
      out.write("            table-layout: fixed;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th, td {\n");
      out.write("            padding: 12px 15px;\n");
      out.write("            border-bottom: 1px solid #eee;\n");
      out.write("            text-align: left;\n");
      out.write("            font-size: 15px;\n");
      out.write("            word-break: break-word;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th {\n");
      out.write("            background: linear-gradient(to right, #74ebd5, #acb6e5);\n");
      out.write("            color: #2d3436;\n");
      out.write("            font-weight: 600;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        tr:hover {\n");
      out.write("            background-color: #f0f0f0;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .status-available { color: #00b894; font-weight: bold; }\n");
      out.write("        .status-borrowed { color: #e17055; font-weight: bold; }\n");
      out.write("        .status-reserved { color: #d63031; font-weight: bold; }\n");
      out.write("\n");
      out.write("        .action-buttons {\n");
      out.write("            display: flex;\n");
      out.write("            gap: 10px;\n");
      out.write("            flex-wrap: wrap;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-action {\n");
      out.write("            padding: 6px 14px;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 8px;\n");
      out.write("            font-size: 14px;\n");
      out.write("            font-weight: bold;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: 0.3s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-edit {\n");
      out.write("            background-color: #0984e3;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-edit:hover {\n");
      out.write("            background-color: #74b9ff;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-delete {\n");
      out.write("            background-color: #d63031;\n");
      out.write("            color: white;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-delete:hover {\n");
      out.write("            background-color: #e17055;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .no-books {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: bold;\n");
      out.write("            color: #636e72;\n");
      out.write("            padding: 40px 0;\n");
      out.write("            font-size: 18px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @media (max-width: 768px) {\n");
      out.write("            input[type=\"text\"], select {\n");
      out.write("                width: 90%;\n");
      out.write("            }\n");
      out.write("            .btn-group {\n");
      out.write("                flex-direction: column;\n");
      out.write("                gap: 12px;\n");
      out.write("            }\n");
      out.write("            th, td {\n");
      out.write("                font-size: 14px;\n");
      out.write("                padding: 10px 12px;\n");
      out.write("            }\n");
      out.write("            .container {\n");
      out.write("                padding: 20px 15px;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>📚 Explore Our Library Collection</h2>\n");
      out.write("\n");
      out.write("    ");

        User currentUser = (User) session.getAttribute("currentUser");
        String role = currentUser != null ? currentUser.getRole() : "";
        String backUrl = "login.jsp";
        if ("admin".equals(role)) backUrl = "AdminDashboardServlet";
        else if ("librarian".equals(role)) backUrl = "librarian_dashboard.jsp";
    
      out.write("\n");
      out.write("\n");
      out.write("    <div class=\"btn-group\">\n");
      out.write("        <a href=\"add_book.jsp\" class=\"btn\">➕ Add Book</a>\n");
      out.write("        <a href=\"BookServlet?action=borrowCountList\" class=\"btn\">📈 Borrow Stats</a>\n");
      out.write("        <a href=\"");
      out.print( backUrl );
      out.write("\" class=\"btn\">🏠 Back to Dashboard</a>\n");
      out.write("        <a href=\"BookServlet?action=deleted\" class=\"btn\">🗑️ Deleted Books</a>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <!-- Search Form -->\n");
      out.write("    <form action=\"BookServlet\" method=\"get\">\n");
      out.write("        <input type=\"text\" name=\"keyword\" placeholder=\"🔍 Search by Title or ISBN\" />\n");
      out.write("        <button type=\"submit\" name=\"action\" value=\"search\">Search</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <!-- Filter Form -->\n");
      out.write("    <form action=\"BookServlet\" method=\"get\">\n");
      out.write("        <select name=\"availability\">\n");
      out.write("            <option value=\"\">🔃 All Status</option>\n");
      out.write("            <option value=\"available\">✅ Available</option>\n");
      out.write("            <option value=\"borrowed\">📚 Borrowed</option>\n");
      out.write("            <option value=\"reserved\">🔒 Reserved</option>\n");
      out.write("        </select>\n");
      out.write("        <button type=\"submit\" name=\"action\" value=\"filter\">Filter</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <!-- Sort Form -->\n");
      out.write("    <form action=\"BookServlet\" method=\"get\">\n");
      out.write("        <select name=\"sortOrder\">\n");
      out.write("            <option value=\"desc\">🔽 Newest</option>\n");
      out.write("            <option value=\"asc\">🔼 Oldest</option>\n");
      out.write("        </select>\n");
      out.write("        <button type=\"submit\" name=\"action\" value=\"sort\">Sort</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <a href=\"BookServlet?action=viewAll\" class=\"btn\" style=\"background: #dfe6e9; color: #2d3436;\">🔁 Reset</a>\n");
      out.write("\n");
      out.write("    ");

        List<Book> books = (List<Book>) request.getAttribute("books");
        Map<Integer, Integer> borrowCounts = (Map<Integer, Integer>) request.getAttribute("borrowCounts");

        if (books != null && !books.isEmpty()) {
    
      out.write("\n");
      out.write("    <table>\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th style=\"width: 5%;\">ID</th>\n");
      out.write("                <th style=\"width: 18%;\">📖 Title</th>\n");
      out.write("                <th style=\"width: 15%;\">✍️ Author</th>\n");
      out.write("                <th style=\"width: 12%;\">🔢 ISBN</th>\n");
      out.write("                <th style=\"width: 10%;\">📚 Genre</th>\n");
      out.write("                <th style=\"width: 7%;\">📅 Year</th>\n");
      out.write("                <th style=\"width: 8%;\">📌 Status</th>\n");
      out.write("                <th style=\"width: 7%;\">📈 Borrows</th>\n");
      out.write("                <th style=\"width: 18%;\">📝 Description</th>\n");
      out.write("                <th style=\"width: 10%;\">⚙️ Actions</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("        ");

            for (Book book : books) {
                int count = borrowCounts != null && borrowCounts.containsKey(book.getBookId())
                        ? borrowCounts.get(book.getBookId()) : 0;
        
      out.write("\n");
      out.write("        <tr>\n");
      out.write("            <td>");
      out.print( book.getBookId() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getTitle() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getAuthor() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getIsbn() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getGenre() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getPublicationYear() );
      out.write("</td>\n");
      out.write("            <td class=\"status-");
      out.print( book.getAvailability().toLowerCase() );
      out.write('"');
      out.write('>');
      out.print( book.getAvailability() );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( count );
      out.write("</td>\n");
      out.write("            <td>");
      out.print( book.getDescription() );
      out.write("</td>\n");
      out.write("            <td>\n");
      out.write("                <div class=\"action-buttons\">\n");
      out.write("                    <a href=\"BookServlet?action=edit&bookId=");
      out.print( book.getBookId() );
      out.write("\">\n");
      out.write("                        <button class=\"btn-action btn-edit\" type=\"button\">✏️ Edit</button>\n");
      out.write("                    </a>\n");
      out.write("                    <a href=\"BookServlet?action=delete&bookId=");
      out.print( book.getBookId() );
      out.write("\" onclick=\"return confirm('Are you sure you want to delete this book?');\">\n");
      out.write("                        <button class=\"btn-action btn-delete\" type=\"button\">🗑️ Delete</button>\n");
      out.write("                    </a>\n");
      out.write("                </div>\n");
      out.write("            </td>\n");
      out.write("        </tr>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("    ");
 } else { 
      out.write("\n");
      out.write("        <p class=\"no-books\">📭 No books found. Try again or add new books!</p>\n");
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
