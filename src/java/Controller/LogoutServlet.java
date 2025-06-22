package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); // false تعني لا تنشئ جلسة جديدة إذا لم تكن موجودة
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
