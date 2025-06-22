package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class system_005fconfig_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_if_test.release();
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
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\" />\n");
      out.write("    <title>System Configuration</title>\n");
      out.write("    <style>\n");
      out.write("        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap');\n");
      out.write("\n");
      out.write("        body {\n");
      out.write("            margin: 0;\n");
      out.write("            font-family: 'Inter', sans-serif;\n");
      out.write("            background: linear-gradient(135deg, #e0ecf8, #fdfdfd);\n");
      out.write("            display: flex;\n");
      out.write("            align-items: center;\n");
      out.write("            justify-content: center;\n");
      out.write("            min-height: 100vh;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .container {\n");
      out.write("            width: 100%;\n");
      out.write("            max-width: 600px;\n");
      out.write("            background: white;\n");
      out.write("            padding: 40px 30px;\n");
      out.write("            border-radius: 20px;\n");
      out.write("            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);\n");
      out.write("            animation: slideIn 0.4s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        @keyframes slideIn {\n");
      out.write("            from { transform: translateY(20px); opacity: 0; }\n");
      out.write("            to { transform: translateY(0); opacity: 1; }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        h2 {\n");
      out.write("            text-align: center;\n");
      out.write("            color: #2c3e50;\n");
      out.write("            font-size: 26px;\n");
      out.write("            margin-bottom: 30px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        label {\n");
      out.write("            font-weight: 600;\n");
      out.write("            display: block;\n");
      out.write("            margin-top: 16px;\n");
      out.write("            color: #34495e;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"number\"] {\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 12px;\n");
      out.write("            font-size: 16px;\n");
      out.write("            border: 1px solid #dcdcdc;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            margin-top: 6px;\n");
      out.write("            box-sizing: border-box;\n");
      out.write("            transition: border 0.3s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"number\"]:focus {\n");
      out.write("            border-color: #3498db;\n");
      out.write("            outline: none;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"] {\n");
      out.write("            margin-top: 30px;\n");
      out.write("            width: 100%;\n");
      out.write("            padding: 14px;\n");
      out.write("            font-size: 17px;\n");
      out.write("            font-weight: 700;\n");
      out.write("            color: white;\n");
      out.write("            background: linear-gradient(90deg, #00b4db, #0083b0);\n");
      out.write("            border: none;\n");
      out.write("            border-radius: 12px;\n");
      out.write("            cursor: pointer;\n");
      out.write("            transition: background 0.3s ease, transform 0.2s;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        input[type=\"submit\"]:hover {\n");
      out.write("            background: linear-gradient(90deg, #0083b0, #00b4db);\n");
      out.write("            transform: translateY(-2px);\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .message {\n");
      out.write("            text-align: center;\n");
      out.write("            padding: 12px 16px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            border-radius: 10px;\n");
      out.write("            margin-bottom: 20px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .success {\n");
      out.write("            background-color: #eafaf1;\n");
      out.write("            color: #27ae60;\n");
      out.write("            border: 1px solid #a9e6c7;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .error {\n");
      out.write("            background-color: #fef0f0;\n");
      out.write("            color: #e74c3c;\n");
      out.write("            border: 1px solid #f5b1b1;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-button {\n");
      out.write("            margin-top: 25px;\n");
      out.write("            text-align: center;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-button a {\n");
      out.write("            display: inline-block;\n");
      out.write("            padding: 12px 24px;\n");
      out.write("            font-weight: 600;\n");
      out.write("            font-size: 15px;\n");
      out.write("            text-decoration: none;\n");
      out.write("            color: white;\n");
      out.write("            background: linear-gradient(90deg, #6a11cb, #2575fc);\n");
      out.write("            border-radius: 10px;\n");
      out.write("            transition: background 0.3s ease;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .back-button a:hover {\n");
      out.write("            background: linear-gradient(90deg, #2575fc, #6a11cb);\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div class=\"container\">\n");
      out.write("    <h2>⚙️ System Configuration</h2>\n");
      out.write("\n");
      out.write("    <!-- Success message -->\n");
      out.write("    ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Error message -->\n");
      out.write("    ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("    <form method=\"post\" action=\"SystemConfigServlet\">\n");
      out.write("        <input type=\"hidden\" name=\"configId\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${config.configId}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\n");
      out.write("\n");
      out.write("        <label for=\"fineRate\">📉 Fine Rate Per Overdue Day (JD):</label>\n");
      out.write("        <input type=\"number\" step=\"0.01\" min=\"0\" id=\"fineRate\" name=\"fineRate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${config.fineRate}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"maxBooksBorrowed\">📚 Maximum Books Borrowed:</label>\n");
      out.write("        <input type=\"number\" min=\"1\" id=\"maxBooksBorrowed\" name=\"maxBooksBorrowed\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${config.maxBooksBorrowed}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <label for=\"maxBorrowPeriod\">📆 Maximum Borrow Period (days):</label>\n");
      out.write("        <input type=\"number\" min=\"1\" id=\"maxBorrowPeriod\" name=\"maxBorrowPeriod\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${config.maxBorrowPeriod}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" required />\n");
      out.write("\n");
      out.write("        <input type=\"submit\" value=\"💾 Save Changes\" />\n");
      out.write("    </form>\n");
      out.write("\n");
      out.write("    <div class=\"back-button\">\n");
      out.write("        <a href=\"AdminDashboardServlet\">← Back to Dashboard</a>\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty message}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("        <div class=\"message success\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${message}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</div>\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${not empty error}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("        <div class=\"message error\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
        out.write("</div>\n");
        out.write("    ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }
}
