import Entities.Backend.EmployeeDetails;
import com.google.gson.Gson;
import implementations.App;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/all/")
public class EmployeeDetailsReadService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        interfaces.EmployeeInterface employeeInterface= null;

        try {
            employeeInterface = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json");
        List<EmployeeDetails> employeeDetails= null;
        try {
            employeeDetails = employeeInterface.employeeOutputDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson= new Gson();
        String responseData = gson.toJson(employeeDetails);
        resp.getWriter().println(responseData);
        resp.setStatus(HttpServletResponse.SC_OK);

//       try {
//            employeeInterface = new App();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.setContentType("application/json");
//        List<EmployeeDetails> employeeDetailsList= null;
//        try {
//            employeeDetailsList = employeeInterface.employeeOutputDetails();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }catch (NullPointerException ex){
//             employeeDetailsList=new ArrayList<>();
//            Gson gson=new Gson();
//            String responseData = gson.toJson(employeeDetailsList);
//            resp.getWriter().println(responseData);
//        }
//        //Gson gson=new Gson();
//        String responseData = gson.toJson(employeeDetailsList);
//        resp.getWriter().println(responseData);
//        resp.setStatus(HttpServletResponse.SC_OK);
//        //resp.getWriter().println("Details displayed");
//
    }
}
