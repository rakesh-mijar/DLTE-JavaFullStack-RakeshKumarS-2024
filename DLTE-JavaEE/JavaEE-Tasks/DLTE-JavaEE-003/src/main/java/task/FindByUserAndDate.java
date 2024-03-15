package task;

import com.google.gson.Gson;
import org.example.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/findUserAndDate")
public class FindByUserAndDate extends HttpServlet {
    private StorageTarget storageTarget;
    private UserServices userServices;

    @Override
    public void init() throws ServletException {
        //super.init();
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
    }

    @Override
    //
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //method corresponds to get the user details based on the username and date specified...from the transaction table
        resp.setContentType("application/json");
        try{
            List<Transaction> user=userServices.callfindByUserAndDate("rak@123",Date.valueOf("2024-03-14"));
            Gson gson=new Gson();
            String message=gson.toJson(user);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
