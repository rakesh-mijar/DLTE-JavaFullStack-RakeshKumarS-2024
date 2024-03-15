package task;

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
import java.util.List;

@WebServlet("/findByUsername/ed")
public class FindByUserName extends HttpServlet {
    private StorageTarget storageTarget;
    private UserServices userServices;

    @Override
    public void init() throws ServletException {
        //super.init();
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        // //method corresponds to get the user details based on the username and date specified...from the users table
        resp.setContentType("application/json");
        try{
            User user=userServices.callFindById("rak@123");
            Gson gson=new Gson();
            String message=gson.toJson(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
