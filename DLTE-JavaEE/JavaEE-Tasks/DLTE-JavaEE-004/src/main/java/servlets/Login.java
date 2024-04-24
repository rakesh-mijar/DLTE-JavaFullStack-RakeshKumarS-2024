package servlets;

import oracle.jdbc.driver.OracleDriver;
import org.example.DatabaseTarget;
import org.example.StorageTarget;
import org.example.UserServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ResourceBundle;

@WebServlet("/login")
public class Login extends HttpServlet {
    public UserServices userServices;
    public ResourceBundle resourceBundle;
    public StorageTarget storageTarget;

    @Override
    public void init() throws ServletException {
       storageTarget = new DatabaseTarget();
        userServices = new UserServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("users");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Driver driver = new OracleDriver();
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(resourceBundle.getString("db.url"), resourceBundle.getString("db.user"), resourceBundle.getString("db.pass"));
            String query = "select * from mybank_users where username=? and password=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                HttpSession session=req.getSession();
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                resp.sendRedirect("dashboard.jsp");
            }
            else{
//                resp.sendRedirect("index.jsp");
                resp.sendRedirect("index.jsp?error=1");
            }
        } catch (SQLException | IOException sqlException) {
            resp.sendRedirect("index.jsp?error=1");
            System.out.println(sqlException);
        }
    }
}
