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

@WebServlet("/create/*")
public class CreateAccount extends HttpServlet {
    public UserServices userServices;

    private StorageTarget storageTarget;

    @Override
    public void init() throws ServletException {
        //super.init();
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    //this method is used to create a new user into the accounts table and save it using save() method
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson=new Gson();
        User user=gson.fromJson(req.getReader(), User.class);
        userServices.callSave(user);

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(user.getUsername()+" has added to the records");
    }

    }
