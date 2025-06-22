package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import models.Book;

public final class edit_005fbook_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');

    Book book = (Book) request.getAttribute("book");
    if (book == null) { response.sendRedirect("BookServlet?action=viewAll"); return; }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>✏️ Edit Book</title>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap\" rel=\"stylesheet\" />\n");
      out.write("    <style>\n");
      out.write("        /* Reset & Base */\n");
      out.write("        * {\n");
      out.write("            box-sizing: border-box;\n");
      out.write("        }\n");
      out.write("        body {\n");
      out.write("            font-family: 'Inter', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);\n");
      out.write("            margin: 0;\n");
      out.write("            padding: 40px 20px;\n");
      out.write("            display: flex;\n");
      out.write("            justify-content: center;\n");
      out.write("            min-height: 100vh;\n");
      out.write("            align-items: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Container */\n");
      out.write("        .container {\n");
      out.write("            background: #fff;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            padding: 40px 50px;\n");
      out.write("            max-width: 480px;\n");
      out.write("            width: 100%;\n");
      out.write("            box-shadow:\n");
      out.write("                8px 8px 15px rgba(0, 0, 0, 0.1),\n");
      out.write("                -8px -8px 15px rgba(255, 255, 255, 0.7);\n");
      out.write("            transition: box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("        .container:hover {\n");
      out.write("            box-shadow:\n");
      out.write("                12px 12px 25px rgba(0, 0, 0, 0.15),\n");
      out.write("                -12px -12px 25px rgba(255, 255, 255, 0.9);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Heading */\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            color: #7b4397;\n");
      out.write("            font-weight: 700;\n");
      out.write("            font-size: 2.2rem;\n");
      out.write("            margin-bottom: 35px;\n");
      out.write("            letter-spacing: 2px;\n");
      out.write("            text-shadow: 1px 1px 3px rgba(123, 67, 151, 0.3);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Form Elements */\n");
      out.write("        label {\n");
      out.write("            display: block;\n");
      out.write("            margin-bottom: 8px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            color: #4a4a4a;\n");
      out.write("            letter-spacing: 0.03em;\n");
      out.write("        }\n");
      out.write("        input[type=\"text\"],\n");
      out.write("        input[type=\"number\"],\n");
      out.write("        select,\n");
      out.write("        textarea {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 14px 18px;\n");
      out.write("            border-radius: 14px;\n");
      out.write("            border: none;\n");
      out.write("            background: #f0f0f3;\n");
      out.write("            box-shadow:\n");
      out.write("                inset 6px 6px 8px #d1d9e6,\n");
      out.write("                inset -6px -6px 8px #ffffff;\n");
      out.write("            font-size: 1rem;\n");
      out.write("            color: #555;\n");
      out.write("            transition: box-shadow 0.3s ease;\n");
      out.write("            resize: vertical;\n");
      out.write("            min-height: 48px;\n");
      out.write("        }\n");
      out.write("        textarea {\n");
      out.write("            min-height: 100px;\n");
      out.write("        }\n");
      out.write("        input[type=\"text\"]:focus,\n");
      out.write("        input[type=\"number\"]:focus,\n");
      out.write("        select:focus,\n");
      out.write("        textarea:focus {\n");
      out.write("            outline: none;\n");
      out.write("            box-shadow:\n");
      out.write("                inset 3px 3px 6px #b6bfcf,\n");
      out.write("                inset -3px -3px 6px #ffffff,\n");
      out.write("                0 0 8px #7b4397;\n");
      out.write("            color: #3a3a3a;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Button */\n");
      out.write("        button {\n");
      out.write("            width: 100%;\n");
      out.write("            margin-top: 30px;\n");
      out.write("            padding: 16px 0;\n");
      out.write("            background: #7b4397;\n");
      out.write("            color: white;\n");
      out.write("            font-weight: 700;\n");
      out.write("            font-size: 1.1rem;\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            box-shadow: 3px 3px 8px #5a2d71, -3px -3px 8px #9659b6;\n");
      out.write("            transition: background-color 0.3s ease, box-shadow 0.3s ease;\n");
      out.write("        }\n");
      out.write("        button:hover {\n");
      out.write("            background: #6a3782;\n");
      out.write("            box-shadow: 5px 5px 15px #4b245d, -5px -5px 15px #a073ce;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Back Link */\n");
      out.write("        .back-link {\n");
      out.write("            display: block;\n");
      out.write("            text-align: center;\n");
      out.write("            margin-top: 25px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            color: #7b4397;\n");
      out.write("            text-decoration: none;\n");
      out.write("            letter-spacing: 0.05em;\n");
      out.write("            transition: color 0.3s ease;\n");
      out.write("        }\n");
      out.write("        .back-link:hover {\n");
      out.write("            color: #5a2d71;\n");
      out.write("            text-decoration: underline;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        /* Message */\n");
      out.write("        .message {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            text-align: center;\n");
      out.write("            font-weight: 700;\n");
      out.write("            font-size: 1rem;\n");
      out.write("            letter-spacing: 0.04em;\n");
      out.write("        }\n");
      out.write("        .success {\n");
      out.write("            color: #27ae60;\n");
      out.write("            text-shadow: 0 0 6px #27ae60;\n");
      out.write("        }\n");
      out.write("        .error {\n");
      out.write("            color: #e74c3c;\n");
      out.write("            text-shadow: 0 0 6px #e74c3c;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>✏️ Edit Book</h2>\n");
      out.write("\n");
      out.write("    <form action=\"BookServlet\" method=\"post\" autocomplete=\"off\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"edit\" />\n");
      out.write("        <input type=\"hidden\" name=\"bookId\" value=\"");
      out.print( book.getBookId() );
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"title\">Title</label>\n");
      out.write("        <input type=\"text\" id=\"title\" name=\"title\" value=\"");
      out.print( book.getTitle() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"author\">Author</label>\n");
      out.write("        <input type=\"text\" id=\"author\" name=\"author\" value=\"");
      out.print( book.getAuthor() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"isbn\">ISBN</label>\n");
      out.write("        <input type=\"text\" id=\"isbn\" name=\"isbn\" value=\"");
      out.print( book.getIsbn() );
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"genre\">Genre</label>\n");
      out.write("        <input type=\"text\" id=\"genre\" name=\"genre\" value=\"");
      out.print( book.getGenre() );
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"publicationYear\">Publication Year</label>\n");
      out.write("        <input type=\"number\" id=\"publicationYear\" name=\"publicationYear\" value=\"");
      out.print( book.getPublicationYear() );
      out.write("\" min=\"1000\" max=\"9999\" />\n");
      out.write("\n");
      out.write("        <label for=\"availability\">Status</label>\n");
      out.write("        <select id=\"availability\" name=\"availability\" required>\n");
      out.write("            <option value=\"available\" ");
      out.print( "available".equals(book.getAvailability()) ? "selected" : "" );
      out.write(">Available</option>\n");
      out.write("            <option value=\"borrowed\"  ");
      out.print( "borrowed".equals(book.getAvailability()) ? "selected" : "" );
      out.write(">Borrowed</option>\n");
      out.write("            <option value=\"reserved\"  ");
      out.print( "reserved".equals(book.getAvailability()) ? "selected" : "" );
      out.write(">Reserved</option>\n");
      out.write("        </select>\n");
      out.write("\n");
      out.write("        <label for=\"description\">Description</label>\n");
      out.write("        <textarea id=\"description\" name=\"description\">");
      out.print( book.getDescription() );
      out.write("</textarea>\n");
      out.write("\n");
      out.write("        <button type=\"submit\">Update Book</button>\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    ");
      out.write("\n");
      out.write("    ");

        String msg  = (String) request.getAttribute("message");
        String stat = (String) request.getAttribute("status");
        if (msg != null) {
    
      out.write("\n");
      out.write("        <div class=\"message ");
      out.print( "success".equals(stat) ? "success" : "error" );
      out.write('"');
      out.write('>');
      out.print( msg );
      out.write("</div>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("    <a href=\"BookServlet?action=viewAll\" class=\"back-link\">← Back to Book List</a>\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
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
