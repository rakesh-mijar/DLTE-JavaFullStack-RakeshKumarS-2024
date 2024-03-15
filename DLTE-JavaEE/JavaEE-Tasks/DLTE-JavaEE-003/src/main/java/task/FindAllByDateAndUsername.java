package task;

import com.google.gson.Gson;


import org.example.DatabaseTarget;
import org.example.StorageTarget;
import org.example.Transaction;
import org.example.UserServices;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet("/rest/date/*")
public class FindAllByDateAndUsername extends HttpServlet {
    private UserServices userServices;
    private ResourceBundle resourceBundle;
    private Logger logger;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(FindAllByDateAndUsername.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //method corresponds to get the userlist based on the user name and date
        String username = req.getParameter("username");
        String date = req.getParameter("date");
        resp.setContentType("application/json");
        try {
            List<Transaction> transactions = userServices.callFindByDateAndUsername(username, Date.valueOf(date));
            Gson gson = new Gson();
            String responseData = gson.toJson(transactions);
            resp.getWriter().println(responseData);
        } catch (IllegalArgumentException illegalException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            System.out.println(illegalException);
        }
        catch (MissingResourceException userException) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}