package implementations;

import Entities.Backend.EmployeeAddress;
import Entities.Backend.EmployeeDetails;
import connections.Database;
import interfaces.EmployeeInterface;
import backend.exceptions.ConnectionFailureException;
import backend.exceptions.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App implements EmployeeInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private static Logger logger= LoggerFactory.getLogger(App.class);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    public App() throws SQLException {
//        Database database = new Database();
//        connection = database.getConnection();
//    }

    public App() throws SQLException {
        try {
            Database database = new Database();
            connection = database.getConnection();
        } catch (backend.exceptions.ConnectionFailureException| SQLException e) {
            //logger.error("Connection to the database failed: " + e.getMessage());
            System.out.println(resourceBundle.getString("no.connect"));
            throw new ConnectionFailureException("Connection to the database failed.");
        }
    }


    public static void main( String[] args ) throws SQLException {
    }

    @Override
    public boolean writeEmployeeDetails(EmployeeDetails employee) throws SQLException {
        if (employee == null) {
//            System.out.println("Employee is Empty");
            System.out.println(resourceBundle.getString("emp.empty"));
            logger.info("Employee table is empty");
            return false;
        }
        try {

            //validating the mobile number
            if(!backend.validation.BackendValidation.isvalidatePhone(employee.getEmpMobileNumber())){
                System.out.println(resourceBundle.getString("employee.contact.invalid"));
                logger.info("employee.contact.invalid");
                return false;
            }

            //validating the pincode of both temporary and permanent address
            if(!backend.validation.BackendValidation.isvalidatePin(employee.getPermAddress().getPincode()) || !backend.validation.BackendValidation.isvalidatePin(employee.getTempAddress().getPincode())){
                System.out.println(resourceBundle.getString("employee.pin.invalid"));
                logger.info("employee.pin.invalid");
                return false;
            }

            //inserting into the employee table
            int employeeID = employee.getEmpId();
            String query = "INSERT INTO employee_table (empId,firstName,middleName,lastName,empMobileNumber) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setLong(5, employee.getEmpMobileNumber());
            int resultSet=preparedStatement.executeUpdate();

            //inserting into the temporary address table
            String query2 = "INSERT INTO temporary_address (empId,street,state,country,pincode) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setString(2, employee.getTempAddress().getStreet());
            preparedStatement.setString(3, employee.getTempAddress().getState());
            preparedStatement.setString(4, employee.getTempAddress().getCountry());
            preparedStatement.setInt(5, employee.getTempAddress().getPincode());
            resultSet=preparedStatement.executeUpdate();

            //inserting into the permanent address table
            String query3 = "INSERT INTO permanent_address (empId,street,state,country,pincode) VALUES (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(query3);
            preparedStatement.setInt(1, employeeID);
            preparedStatement.setString(2, employee.getPermAddress().getStreet());
            preparedStatement.setString(3, employee.getPermAddress().getState());
            preparedStatement.setString(4, employee.getPermAddress().getCountry());
            preparedStatement.setInt(5, employee.getPermAddress().getPincode());
            resultSet=preparedStatement.executeUpdate();

            connection.commit();

            if (resultSet != 0) {
                System.out.println(resourceBundle.getString("db.push.ok"));
                logger.info("Employee details saved successfully!!");
                return true;
            } else {
                System.out.println(resourceBundle.getString("db.push.fail"));
                logger.warn("Employee details failed to insert");
                return false;
            }

        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                logger.warn(resourceBundle.getString("Fail.insert") +" "+ employee + " "+resourceBundle.getString("employee.exists"));
                throw new UserAlreadyExistsException(resourceBundle.getString("Fail.insert") + " " + employee.getEmpId() + " " + resourceBundle.getString("employee.exists"));
            } else {
                return false;
            }
        }finally {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        }
    }

    //method to print all employee details
    @Override
    public List<EmployeeDetails> employeeOutputDetails() throws SQLException {
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        try {
            String query = "SELECT e.empId, e.firstName, e.middleName, e.lastName, e.empMobileNumber, " +
                    "ta.street AS tempStreet, ta.state AS tempState, ta.country AS tempCountry, ta.pincode AS tempPincode, " +
                    "pa.street AS permStreet, pa.state AS permState, pa.country AS permCountry, pa.pincode AS permPincode " +
                    "FROM employee_table e " +
                    "INNER JOIN temporary_address ta ON e.empId = ta.empId " +
                    "INNER JOIN permanent_address pa ON e.empId = pa.empId";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int empId = resultSet.getInt("empId");
                String firstName = resultSet.getString("firstName");
                String middleName = resultSet.getString("middleName");
                String lastName = resultSet.getString("lastName");
                Long empMobileNumber = resultSet.getLong("empMobileNumber");

                String tempStreet = resultSet.getString("tempStreet");
                String tempState = resultSet.getString("tempState");
                String tempCountry = resultSet.getString("tempCountry");
                int tempPincode = resultSet.getInt("tempPincode");
                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);

                String permStreet = resultSet.getString("permStreet");
                String permState = resultSet.getString("permState");
                String permCountry = resultSet.getString("permCountry");
                int permPincode = resultSet.getInt("permPincode");
                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);

                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId, tempAddress, permAddress);
                employeeDetailsList.add(employeeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info(e.toString());
        } finally {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        }
        return employeeDetailsList;
    }

    //method to print the employee details based on the specified pincode..on join of both address tables
    @Override
    public List<EmployeeDetails> findEmployeesByPincode(int pincode) throws SQLException {
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        try {
            String query = "SELECT e.empId, e.firstName, e.middleName, e.lastName, e.empMobileNumber,ta.street AS tempStreet, ta.state AS tempState, ta.country AS tempCountry, ta.pincode AS tempPincode, pa.street AS permStreet, pa.state AS permState, pa.country AS permCountry, pa.pincode AS permPincode FROM employee_table e " +
                    "INNER JOIN temporary_address ta ON e.empId = ta.empId " +
                    "INNER JOIN permanent_address pa ON e.empId = pa.empId " +
                    "WHERE ta.pincode = ? OR pa.pincode = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);
            preparedStatement.setInt(2, pincode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int empId = resultSet.getInt("empId");
                String firstName = resultSet.getString("firstName");
                String middleName = resultSet.getString("middleName");
                String lastName = resultSet.getString("lastName");
                Long empMobileNumber = resultSet.getLong("empMobileNumber");

                String tempStreet = resultSet.getString("tempStreet");
                String tempState = resultSet.getString("tempState");
                String tempCountry = resultSet.getString("tempCountry");
                int tempPincode = resultSet.getInt("tempPincode");
                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);

                String permStreet = resultSet.getString("permStreet");
                String permState = resultSet.getString("permState");
                String permCountry = resultSet.getString("permCountry");
                int permPincode = resultSet.getInt("permPincode");
                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);

                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId, tempAddress, permAddress);
                employeeDetailsList.add(employeeDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
            resultSet.close();
            preparedStatement.close();
        }
        return employeeDetailsList;
    }
}





