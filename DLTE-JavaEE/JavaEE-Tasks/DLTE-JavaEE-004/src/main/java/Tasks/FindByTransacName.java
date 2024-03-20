package Tasks;

import com.google.gson.Gson;
import org.example.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/username/transac")
public class FindByTransacName extends HttpServlet {
    public UserServices userServices;
    private StorageTarget storageTarget;


    @Override
    public void init() throws ServletException {
        //super.init();
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    //this method corresponds to get the details of user transaction based on username from the transaction table
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try{
            List<Transaction> transactions=userServices.callFindByUsername("ram@123");
            Gson gson=new Gson();
            String message=gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
