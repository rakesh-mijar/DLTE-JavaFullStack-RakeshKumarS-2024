import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/types")
public class PrimitiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Context context=new InitialContext();
            String role = (String)context.lookup("java:/MyPOC");
            resp.getWriter().println(role);


//            Integer intValue = (Integer)context.lookup("java:/MyTask");
//            resp.getWriter().println(intValue);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}