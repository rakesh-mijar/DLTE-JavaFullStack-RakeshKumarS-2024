package repository;


import org.springframework.stereotype.Repository;
import technical.review.spring.entities.backend.EmployeeDetails;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface EmployeeRepository {
    void collectEmployeeDetails() throws SQLException;
    List<entities.EmployeeDetails> translateEntities(List<EmployeeDetails> backendEmployees) throws IOException;
    void displayEmployeeDetails(List<entities.EmployeeDetails> consoleEmployees);

}
