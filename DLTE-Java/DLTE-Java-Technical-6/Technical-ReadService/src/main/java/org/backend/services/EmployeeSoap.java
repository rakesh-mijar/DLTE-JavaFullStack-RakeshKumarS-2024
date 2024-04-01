package org.backend.services;

import Entities.Backend.EmployeeDetails;
import connections.Database;
import implementations.App;
import interfaces.EmployeeInterface;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebService
@SOAPBinding(style=SOAPBinding.Style.RPC)
public class EmployeeSoap {
    private EmployeeInterface employeeInterface;;

    public EmployeeSoap() {
        try {
            employeeInterface = new App();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //public EmployeeSoap() throws SQLException {
      //  Database database=new Database();
        //connection=database.getConnection();
       // employeeInterface=new implementations.App();
    //}
    @WebMethod
    public boolean write(@WebParam(name="EmployeeDetails")EmployeeDetails employeeDetails) {
        boolean data=false;
        try{
            data=employeeInterface.writeEmployeeDetails(employeeDetails);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return data;//employeeInterface.writeEmployeeDetails(employeeDetails);
    }



//
//    @WebMethod
//    @WebResult(name="EmployeeGroup")
//    public EmployeeGroup readAllById(@WebParam(name="Integer")Integer empId) throws SQLException {
//        EmployeeGroup employeeGroup=new EmployeeGroup();
//        List<EmployeeDetails> employeeDetailsList=employeeInterface.findEmployeesById(empId);
//        employeeGroup.setEmployeeDetailsList(employeeDetailsList);
//        return employeeGroup;
//    }


//    @WebMethod
//    @WebResult(name="EmployeeGroup")
//    public EmployeeGroup readAllByPincode()

}
