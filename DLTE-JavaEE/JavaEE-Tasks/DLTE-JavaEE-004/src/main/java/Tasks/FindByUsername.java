package Tasks;

import com.google.gson.Gson;
import org.example.DatabaseTarget;
import org.example.StorageTarget;
import org.example.User;
import org.example.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findByUsername/")
public class FindByUsername extends HttpServlet {

    private StorageTarget storageTarget;
    public UserServices userServices;

    @Override
    public void init() throws ServletException {
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    //method to get the user details based on the user name from the accounts table
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try{
            User user=userServices.callFindById("pranav@123");
            Gson gson=new Gson();
            String message=gson.toJson(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
