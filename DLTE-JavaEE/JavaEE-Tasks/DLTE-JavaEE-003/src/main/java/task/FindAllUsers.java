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

@WebServlet("/findEveryone")
public class FindAllUsers extends HttpServlet {
    private UserServices userServices;
    private StorageTarget storageTarget;
    @Override
    public void init() throws ServletException {
        //super.init();
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        //this method corresponds to get all the users from the dao
        resp.setContentType("application/json");
        try{
            List<User> userList=userServices.callFindAll();
            Gson gson=new Gson();

            String users=gson.toJson(userList);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(users);
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
