package technical.spring.services.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import technical.review.spring.entities.backend.EmployeeDetails;
import technical.review.spring.exceptions.*;
import technical.review.spring.interfaces.backend.EmployeeInterface;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("technical.review.spring")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    @Autowired
    private EmployeeInterface employeeInterface;

    @PostMapping("/writeData")
    public ResponseEntity<String> writeEmployeeDetails(@RequestBody EmployeeDetails employee) {
        try {
            String result = employeeInterface.writeEmployeeDetails(employee);
            return ResponseEntity.ok(result);
        } catch (SQLException sqlException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("SQL Error: " + sqlException.getMessage());
        } catch (ConnectionFailureException | ServerException serverException) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Connection Failure: " + serverException.getMessage());
        } catch (InsertionException insertException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Insertion Failure: " + insertException.getMessage());
        } catch (ValidationException  validException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + validException.getMessage());
        }catch (UserAlreadyExistsException userExcception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User Already Exists: " + userExcception.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<EmployeeDetails> employees = employeeInterface.employeeOutputDetails();
            return ResponseEntity.ok(employees);
        } catch (SQLException | EmployeeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (ConnectionFailureException | DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    @GetMapping("/findById/{empId}")
    public ResponseEntity<Object> findEmployeesById(@PathVariable int empId) {
        try {
            EmployeeDetails employees = employeeInterface.findEmployeesById(empId);
            return ResponseEntity.ok(employees);
        } catch (SQLException | EmployeeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no.employee");
        } catch (ConnectionFailureException | DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/findByPincode/{pincode}")
    public ResponseEntity<Object> findEmployeesByPincode(@PathVariable int pincode){
        try{
            List<EmployeeDetails> employeeDetails=employeeInterface.findEmployeesByPincode(pincode);
            return ResponseEntity.ok(employeeDetails);
        }catch (SQLException| EmployeeException ex) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
        } catch (ConnectionFailureException | DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
