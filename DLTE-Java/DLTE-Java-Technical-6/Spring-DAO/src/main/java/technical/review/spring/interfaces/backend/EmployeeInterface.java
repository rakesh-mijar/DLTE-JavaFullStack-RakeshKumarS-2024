package technical.review.spring.interfaces.backend;

import org.springframework.stereotype.Repository;
import technical.review.spring.entities.backend.EmployeeDetails;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface EmployeeInterface {

    String writeEmployeeDetails(EmployeeDetails employee) throws SQLException, ServerException;
    List<EmployeeDetails> employeeOutputDetails() throws SQLException;
    List<EmployeeDetails> findEmployeesByPincode(int pincode) throws SQLException;
    EmployeeDetails findEmployeesById(int empId) throws SQLException;
}
