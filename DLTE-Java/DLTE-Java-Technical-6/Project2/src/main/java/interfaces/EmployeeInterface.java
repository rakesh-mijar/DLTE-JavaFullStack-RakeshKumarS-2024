package interfaces;

import Entities.Backend.EmployeeDetails;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {

    boolean writeEmployeeDetails(EmployeeDetails  employee) throws SQLException;
    List<EmployeeDetails> employeeOutputDetails() throws SQLException;
    List<EmployeeDetails> findEmployeesByPincode(int pincode) throws SQLException;
}
