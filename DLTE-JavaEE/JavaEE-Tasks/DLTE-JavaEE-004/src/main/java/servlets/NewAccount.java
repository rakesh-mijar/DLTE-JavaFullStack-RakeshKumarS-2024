package servlets;

import com.google.gson.Gson;
import org.example.DatabaseTarget;
import org.example.StorageTarget;
import org.example.User;
import org.example.UserServices;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet("/create")
public class NewAccount extends HttpServlet {
    public UserServices userServices;

    private ResourceBundle resourceBundle;
    private StorageTarget storageTarget;

    @Override
    public void init(ServletConfig config) throws ServletException {
        storageTarget=new DatabaseTarget();
        userServices=new UserServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String user1=req.getParameter("username");
    String pass1=req.getParameter("password");
    String address=req.getParameter("address");
    String email=req.getParameter("email");
    long contact=Long.parseLong(req.getParameter("contact"));
    double balanace=Double.parseDouble(req.getParameter("balance"));
        //HttpSession session = req.getSession();
        //String username = (String) session.getAttribute("username");
        //String password = (String) session.getAttribute("password");
        User user=new User(user1,pass1,address,email,contact,balanace);
        RequestDispatcher dispatcher=req.getRequestDispatcher("newaccount.jsp");
        userServices.callSave(user);
        req.setAttribute("info", resourceBundle.getString("account.create"));
        dispatcher.forward(req, resp);
    }

}
