//import Entities.Backend.EmployeeDetails;
//import com.google.gson.Gson;
//import implementations.App;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet("/pincode/")
//public class GetByPincodeService extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        interfaces.EmployeeInterface employeeInterface= null;
//        String pincode=req.getParameter("pincode");
//        int pincode1=Integer.parseInt(pincode);
//        try {
//            employeeInterface = new App();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.setContentType("application/json");
//        List<EmployeeDetails> employeeDetailsList= null;
//        try {
//            employeeDetailsList = employeeInterface.findEmployeesByPincode(pincode1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        Gson gson=new Gson();
//        String responseData = gson.toJson(employeeDetailsList);
//        resp.getWriter().println(responseData);
//        resp.setStatus(HttpServletResponse.SC_OK);
//
//
//
//    }
//}

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
import java.util.List;

@WebServlet("/pincode/")
public class GetByPincodeService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        interfaces.EmployeeInterface employeeInterface = null;
        String pincode = req.getParameter("pincode");
        if (pincode == null) {
            resp.getWriter().println("Pincode parameter is missing.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int pincode1 = Integer.parseInt(pincode);
        try {
            employeeInterface = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.setContentType("application/json");
        List<EmployeeDetails> employeeDetailsList = null;
        try {
            employeeDetailsList = employeeInterface.findEmployeesByPincode(pincode1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        if (employeeDetailsList == null) {
//            resp.getWriter().println("No employee details found for pincode: " + pincode1);
//            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }

        Gson gson = new Gson();
        String responseData = gson.toJson(employeeDetailsList);
        resp.getWriter().println(responseData);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
