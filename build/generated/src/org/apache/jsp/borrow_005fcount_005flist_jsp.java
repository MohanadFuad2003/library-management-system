package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.Map;
import models.Book;

public final class borrow_005fcount_005flist_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>📊 Borrow Count for Books</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <style>\n");
      out.write("        body {\n");
      out.write("            font-family: 'Poppins', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #a8edea, #fed6e3);\n");
      out.write("            margin: 0; \n");
      out.write("            padding: 40px 20px;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            animation: fadeIn 1.2s ease forwards;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes fadeIn {\n");
      out.write("            from { opacity: 0; transform: translateY(20px);}\n");
      out.write("            to { opacity: 1; transform: translateY(0);}\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            max-width: 900px;\n");
      out.write("            margin: auto;\n");
      out.write("            background: rgba(255,255,255,0.9);\n");
      out.write("            padding: 35px 40px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 15px 40px rgba(0,0,0,0.12);\n");
      out.write("            backdrop-filter: blur(10px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: 600;\n");
      out.write("            font-size: 2.4rem;\n");
      out.write("            color: #34495e;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("            letter-spacing: 1px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        table {\n");
      out.write("            width: 100%;\n");
      out.write("            border-collapse: separate;\n");
      out.write("            border-spacing: 0 12px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th, td {\n");
      out.write("            padding: 15px 20px;\n");
      out.write("            text-align: left;\n");
      out.write("            font-size: 1rem;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        th {\n");
      out.write("            background: #74b9ff;\n");
      out.write("            color: white;\n");
      out.write("            font-weight: 600;\n");
      out.write("            border-radius: 12px 12px 0 0;\n");
      out.write("            letter-spacing: 0.05em;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        td {\n");
      out.write("            background: #dfe6e9;\n");
      out.write("            color: #2d3436;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            transition: background-color 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        tr:hover td {\n");
      out.write("            background-color: #81ecec;\n");
      out.write("            color: #0984e3;\n");
      out.write("            font-weight: 600;\n");
      out.write("            cursor: default;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .no-data {\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: 600;\n");
      out.write("            color: #636e72;\n");
      out.write("            margin: 40px 0;\n");
      out.write("            font-size: 1.2rem;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-back {\n");
      out.write("            display: block;\n");
      out.write("            width: fit-content;\n");
      out.write("            margin: 30px auto 0 auto;\n");
      out.write("            padding: 12px 28px;\n");
      out.write("            background: #0984e3;\n");
      out.write("            color: white;\n");
      out.write("            text-decoration: none;\n");
      out.write("            font-weight: 600;\n");
      out.write("            font-size: 1rem;\n");
      out.write("            border-radius: 30px;\n");
      out.write("            box-shadow: 0 6px 15px rgba(9, 132, 227, 0.5);\n");
      out.write("            transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .btn-back:hover {\n");
      out.write("            background-color: #74b9ff;\n");
      out.write("            box-shadow: 0 8px 25px rgba(116, 185, 255, 0.7);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Responsive */\n");
      out.write("        @media (max-width: 600px) {\n");
      out.write("            th, td {\n");
      out.write("                padding: 12px 10px;\n");
      out.write("                font-size: 0.9rem;\n");
      out.write("            }\n");
      out.write("            h2 {\n");
      out.write("                font-size: 2rem;\n");
      out.write("            }\n");
      out.write("            .btn-back {\n");
      out.write("                width: 100%;\n");
      out.write("                text-align: center;\n");
      out.write("                padding: 14px 0;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>📊 Number of Times Each Book Was Borrowed</h2>\n");
      out.write("\n");
      out.write("    ");

        List<Book> books = (List<Book>) request.getAttribute("books");
        Map<Integer, Integer> borrowCounts = (Map<Integer, Integer>) request.getAttribute("borrowCounts");

        if (books != null && !books.isEmpty()) {
    
      out.write("\n");
      out.write("    <table>\n");
      out.write("        <thead>\n");
      out.write("            <tr>\n");
      out.write("                <th>Book ID</th>\n");
      out.write("                <th>📖 Title</th>\n");
      out.write("                <th>✍️ Author</th>\n");
      out.write("                <th>📈 Borrows</th>\n");
      out.write("            </tr>\n");
      out.write("        </thead>\n");
      out.write("        <tbody>\n");
      out.write("        ");

            for (Book book : books) {
                int count = borrowCounts != null && borrowCounts.containsKey(book.getBookId())
                            ? borrowCounts.get(book.getBookId())
                            : 0;
        
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print( book.getBookId() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getTitle() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( book.getAuthor() );
      out.write("</td>\n");
      out.write("                <td>");
      out.print( count );
      out.write("</td>\n");
      out.write("            </tr>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        </tbody>\n");
      out.write("    </table>\n");
      out.write("    ");
 } else { 
      out.write("\n");
      out.write("        <p class=\"no-data\">No borrowing data available.</p>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"BookServlet?action=viewAll\" class=\"btn-back\">← Back to Book List</a>\n");
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
