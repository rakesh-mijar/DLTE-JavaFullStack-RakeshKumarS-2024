import Entitties.Console.data.EmployeeDetails;
import com.google.gson.Gson;
import implementations.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create/")
public class CreateEmployeeRecordService extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        interfaces.EmployeeInterface employeeInterface= null;

        try {
            employeeInterface = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Entities.Backend.EmployeeDetails employeeDetails=gson.fromJson(req.getReader(),Entities.Backend.EmployeeDetails.class);
        try{
            employeeInterface.writeEmployeeDetails(employeeDetails);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(" has been added to table");
    }
}
