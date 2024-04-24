package implementations;

import Entities.Backend.EmployeeAddress;
import Entities.Backend.EmployeeDetails;
import backend.exceptions.InsertionException;
import backend.exceptions.ValidationException;
import backend.validation.BackendValidation;
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
    private static Logger logger = LoggerFactory.getLogger(App.class);
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
//    public App() throws SQLException {
//        Database database = new Database();
//        connection = database.getConnection();
//    }

    public App() throws SQLException {
        try {
            Database database = new Database();
            connection = database.getConnection();
        } catch (backend.exceptions.ConnectionFailureException | SQLException e) {
            //logger.error("Connection to the database failed: " + e.getMessage());
            System.out.println(resourceBundle.getString("no.connect"));
            throw new ConnectionFailureException("Connection to the database failed.");
        }
    }


    public static void main(String[] args) throws SQLException {
    }


    @Override
    public boolean writeEmployeeDetails(Entities.Backend.EmployeeDetails employee) throws SQLException {

        if (employee == null) {
            System.out.println(resourceBundle.getString("employee.empty"));
            return false;
        }

        try {
//            //validating the mobile number
//            if(!backend.validation.BackendValidation.isvalidatePhone(employee.getEmpMobileNumber())){
//                System.out.println(resourceBundle.getString("employee.contact.invalid"));
//                logger.info("employee.contact.invalid");
//                return false;
//            }
//
//            //validating the pincode of both temporary and permanent address
//            if(!backend.validation.BackendValidation.isvalidatePin(employee.getPermAddress().getPincode()) || !backend.validation.BackendValidation.isvalidatePin(employee.getTempAddress().getPincode())){
//                System.out.println(resourceBundle.getString("employee.pin.invalid"));
//                logger.info("employee.pin.invalid");
//                return false;
//            }
            //validating the mobile number
            if (!BackendValidation.isvalidatePhone(employee.getEmpMobileNumber())) {
                logger.info("Invalid employee mobile number: " + employee.getEmpMobileNumber());
                throw new ValidationException(resourceBundle.getString("VAL-001"));
            }

            //validating the pincode of both temporary and permanent address
            if (!backend.validation.BackendValidation.isvalidatePin(employee.getPermAddress().getPincode()) || !backend.validation.BackendValidation.isvalidatePin(employee.getTempAddress().getPincode())) {
                System.out.println(resourceBundle.getString("employee.pin.invalid"));
                logger.info("employee.pin.invalid");
                return false;
            }

            // Insert data into employee_input table
            String query = "INSERT INTO employee_details  VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setLong(5, employee.getEmpMobileNumber());
            int resultSet = preparedStatement.executeUpdate();
            //preparedStatement.close();

            // Check if the insert into employee_input was successful
            if (resultSet > 0) {
                // Insert data into permanent_address table
                String query2 = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employee.getEmpId());
                preparedStatement.setString(2, employee.getPermAddress().getStreetName());
                preparedStatement.setString(3, employee.getPermAddress().getStateName());
                preparedStatement.setString(4, employee.getPermAddress().getCountryName());
                preparedStatement.setInt(5, employee.getPermAddress().getPincode());
                preparedStatement.setString(6, employee.getPermAddress().getFlag());
                resultSet = preparedStatement.executeUpdate();
                if (resultSet == 0) {
                    System.out.println(resourceBundle.getString("db.push.fail"));
                    return false;
                }
                //preparedStatement.close();

                String query3 = "INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, employee.getEmpId());
                preparedStatement.setString(2, employee.getTempAddress().getStreetName());
                preparedStatement.setString(3, employee.getTempAddress().getStateName());
                preparedStatement.setString(4, employee.getTempAddress().getCountryName());
                preparedStatement.setInt(5, employee.getTempAddress().getPincode());
                preparedStatement.setString(6, employee.getTempAddress().getFlag());
                resultSet = preparedStatement.executeUpdate();
                System.out.println(resourceBundle.getString("db.push.ok"));
                System.out.println("Inserted successfully");
                return true;
            } else {
                System.out.println("Failed to insert into employee_input table.");
                //throw new InsertionException(resourceBundle.getString("Fail.insert") + " " + employee.getEmpId() + " " + resourceBundle.getString("employee.exists"));
                return false;
            }
        }
//        } catch (SQLException e) {
//            if (e instanceof SQLIntegrityConstraintViolationException) {
//                logger.warn(resourceBundle.getString("Fail.insert") + " " + employee + " " + resourceBundle.getString("employee.exists"));
//                throw new UserAlreadyExistsException(resourceBundle.getString("Fail.insert") + " " + employee.getEmpId() + " " + resourceBundle.getString("employee.exists"));
//            } else {
//                return false;
//            }
        catch (SQLIntegrityConstraintViolationException ex) {
            logger.warn(resourceBundle.getString("Fail.insert") + " " + employee + " " + resourceBundle.getString("employee.exists"));
            throw new UserAlreadyExistsException(resourceBundle.getString("Fail.insert") + " " + employee.getEmpId() + " " + resourceBundle.getString("employee.exists"));
        } finally {
            // Restore auto-commit mode and close resources
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    @Override
    public List<EmployeeDetails> employeeOutputDetails() throws SQLException {

        List<Entities.Backend.EmployeeDetails> employees = new ArrayList<>();
        String query = "SELECT * FROM employee_details";
        String query2 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'permanent'";
        String query3 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'temporary'";

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Entities.Backend.EmployeeDetails employee = new Entities.Backend.EmployeeDetails();
                int employeeId = resultSet.getInt("emp_id");
                employee.setEmpId(employeeId);
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setMiddleName(resultSet.getString("middle_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmpMobileNumber(resultSet.getLong("mobile_number"));

                // Fetch permanent address
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setInt(1, employeeId);
                ResultSet addressResultSet = preparedStatement.executeQuery();
                List<Entities.Backend.EmployeeAddress> permanentAddresses = new ArrayList<>();
                while (addressResultSet.next()) {
                    Entities.Backend.EmployeeAddress permanentAddress = new Entities.Backend.EmployeeAddress();
                    permanentAddress.setEmpId(employeeId);
                    permanentAddress.setStreetName(addressResultSet.getString("street_name"));
                    permanentAddress.setStateName(addressResultSet.getString("state_name"));
                    permanentAddress.setCountryName(addressResultSet.getString("country_name"));
                    permanentAddress.setPincode(addressResultSet.getInt("pin_code"));
                    permanentAddress.setFlag(addressResultSet.getString("flag"));
                    permanentAddresses.add(permanentAddress);
                }
                employee.setPermAddress(permanentAddresses.stream()
                        .filter(address -> address.getFlag().equalsIgnoreCase("permanent"))
                        .findFirst().orElse(null));

                // Fetch temporary address
                preparedStatement = connection.prepareStatement(query3);
                preparedStatement.setInt(1, employeeId);
                addressResultSet = preparedStatement.executeQuery();
                List<Entities.Backend.EmployeeAddress> temporaryAddresses = new ArrayList<>();
                while (addressResultSet.next()) {
                    Entities.Backend.EmployeeAddress temporaryAddress = new Entities.Backend.EmployeeAddress();
                    temporaryAddress.setEmpId(employeeId);
                    temporaryAddress.setStreetName(addressResultSet.getString("street_name"));
                    temporaryAddress.setStateName(addressResultSet.getString("state_name"));
                    temporaryAddress.setCountryName(addressResultSet.getString("country_name"));
                    temporaryAddress.setPincode(addressResultSet.getInt("pin_code"));
                    temporaryAddress.setFlag(addressResultSet.getString("flag"));
                    temporaryAddresses.add(temporaryAddress);
                }

                employee.setTempAddress(temporaryAddresses.stream()
                        .filter(address -> address.getFlag().equalsIgnoreCase("temporary"))
                        .findFirst().orElse(null));

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new ConnectionFailureException("Failed to retrieve employee details: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employees;
    }

    @Override
    public List<EmployeeDetails> findEmployeesByPincode(int pincode) throws SQLException {

        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
                "pa.street_name AS perm_street, pa.state_name AS perm_state, pa.country_name AS perm_country, pa.pin_code AS perm_pincode, " +
                "ta.street_name AS temp_street, ta.state_name AS temp_state, ta.country_name AS temp_country, ta.pin_code AS temp_pincode " +
                "FROM employee_details e " +
                "LEFT JOIN address_details pa ON e.emp_id = pa.emp_id AND pa.flag = 'permanent' " +
                "LEFT JOIN address_details ta ON e.emp_id = ta.emp_id AND ta.flag = 'temporary' " +
                "WHERE pa.pin_code = ? OR ta.pin_code = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);
            preparedStatement.setInt(2, pincode);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int empId = resultSet.getInt("emp_id");
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                Long empMobileNumber = resultSet.getLong("mobile_number");

                // Parse permanent address
                String permStreet = resultSet.getString("perm_street");
                String permState = resultSet.getString("perm_state");
                String permCountry = resultSet.getString("perm_country");
                int permPincode = resultSet.getInt("perm_pincode");
                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);

                // Parse temporary address
                String tempStreet = resultSet.getString("temp_street");
                String tempState = resultSet.getString("temp_state");
                String tempCountry = resultSet.getString("temp_country");
                int tempPincode = resultSet.getInt("temp_pincode");
                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);

                // Create EmployeeDetails object and add to list
                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId);
                employeeDetails.setPermAddress(permAddress);
                employeeDetails.setTempAddress(tempAddress);
                employeeDetailsList.add(employeeDetails);
            }
        } catch (SQLException e) {
            throw new ConnectionFailureException("Failed to retrieve employee details: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeDetailsList;
    }





    //method to print all employee details
//    @Override
//    public List<EmployeeDetails> employeeOutputDetails() throws SQLException {
//        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
//        try {
//
//            String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
//                    "a.street_name AS street, a.state_name AS state, a.country_name AS country, a.pin_code AS pincode, a.flag " +
//                    "FROM employee_details e " +
//                    "INNER JOIN address_details a ON a.emp_id = e.emp_id";
//            preparedStatement = connection.prepareStatement(query);
//            resultSet = preparedStatement.executeQuery();
//
////            while (resultSet.next()) {
////                int empId = resultSet.getInt("empId");
////                String firstName = resultSet.getString("firstName");
////                String middleName = resultSet.getString("middleName");
////                String lastName = resultSet.getString("lastName");
////                Long empMobileNumber = resultSet.getLong("empMobileNumber");
////
////                String tempStreet = resultSet.getString("tempStreet");
////                String tempState = resultSet.getString("tempState");
////                String tempCountry = resultSet.getString("tempCountry");
////                int tempPincode = resultSet.getInt("tempPincode");
////                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);
////
////                String permStreet = resultSet.getString("permStreet");
////                String permState = resultSet.getString("permState");
////                String permCountry = resultSet.getString("permCountry");
////                int permPincode = resultSet.getInt("permPincode");
////                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);
////
////                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId, tempAddress, permAddress);
////                employeeDetailsList.add(employeeDetails);
////            }
//            //EmployeeDetails employeeDetails;
//            while (resultSet.next()) {
//                int empId = resultSet.getInt("emp_id");
//                String firstName = resultSet.getString("first_name");
//                String middleName = resultSet.getString("middle_name");
//                String lastName = resultSet.getString("last_name");
//                Long empMobileNumber = resultSet.getLong("mobile_number");
//
//                EmployeeDetails employeeDetails = new EmployeeDetails(firstName,middleName,lastName,empMobileNumber,empId);
//
//                int empid=resultSet.getInt("emp_id");
//                String street = resultSet.getString("street");
//                String state = resultSet.getString("state");
//                String country = resultSet.getString("country");
//                int pincode = resultSet.getInt("pincode");
//                String flag = resultSet.getString("flag");
//
//                EmployeeAddress address = new EmployeeAddress(street, state, country, pincode);
//
//                if (flag.equalsIgnoreCase("permanent")) {
//                    employeeDetails.setTempAddress(address);
//                } else if (flag.equalsIgnoreCase("temporary")) {
//                    employeeDetails.setPermAddress(address);
//                }
//
//                //EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId);
//                employeeDetailsList.add(employeeDetails);
//            }
//
//
//            //catch (SQLException e) {
//            //e.printStackTrace();
//            //logger.info(e.toString());
//            // }
//        }finally {
//            connection.close();
//            resultSet.close();
//            preparedStatement.close();
//        }
//        return employeeDetailsList;
//    }
//}








//    //method to print the employee details based on the specified pincode..on join of both address tables
//    @Override
//    public List<EmployeeDetails> findEmployeesByPincode(int pincode) throws SQLException {
//        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
//        try {
////            String query = "SELECT e.empId, e.firstName, e.middleName, e.lastName, e.empMobileNumber,ta.street AS tempStreet, ta.state AS tempState, ta.country AS tempCountry, ta.pincode AS tempPincode, pa.street AS permStreet, pa.state AS permState, pa.country AS permCountry, pa.pincode AS permPincode FROM employee_table e " +
////                    "JOIN temporary_address ta ON e.empId = ta.empId " +
////                    "JOIN permanent_address pa ON e.empId = pa.empId " +
////                    "WHERE ta.pincode = ? OR pa.pincode = ?";
//            String query = "SELECT e.empId, e.firstName,e.middleName,e.lastName,e.empMobileNumber, "+
//                    "a.permstreet AS permStreet, a.permstate AS permState,a.permcountry AS permCountry,a.permpincode AS permPincode, "+
//                    "a.tempstreet AS tempStreet, a.tempstate AS tempState,a.tempcountry AS tempCountry,a.temppincode AS tempPincode "+
//                    "FROM employee_table e "+
//                    "INNER JOIN employee_address a ON a.empId = e.empId "+
//                    "WHERE a.temppincode =? OR a.permpincode=?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, pincode);
//            preparedStatement.setInt(2, pincode);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int empId = resultSet.getInt("empId");
//                String firstName = resultSet.getString("firstName");
//                String middleName = resultSet.getString("middleName");
//                String lastName = resultSet.getString("lastName");
//                Long empMobileNumber = resultSet.getLong("empMobileNumber");
//
//                String tempStreet = resultSet.getString("tempStreet");
//                String tempState = resultSet.getString("tempState");
//                String tempCountry = resultSet.getString("tempCountry");
//                int tempPincode = resultSet.getInt("tempPincode");
//                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);
//
//                String permStreet = resultSet.getString("permStreet");
//                String permState = resultSet.getString("permState");
//                String permCountry = resultSet.getString("permCountry");
//                int permPincode = resultSet.getInt("permPincode");
//                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);
//
//                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId, tempAddress, permAddress);
//                employeeDetailsList.add(employeeDetails);
//            }
//        } //catch (SQLException e) {
////            e.printStackTrace();
////        }
//            finally {
//            connection.close();
//            resultSet.close();
//            preparedStatement.close();
//        }
//        return employeeDetailsList;
//    }

    @Override
    public List<EmployeeDetails> findEmployeesById(int empId) throws SQLException {
//        List<EmployeeDetails> employeeDetails = new ArrayList<>();
//        try {
//
//            String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
//                    "pa.street_name AS perm_street, pa.state_name AS perm_state, pa.country_name AS perm_country, pa.pin_code AS perm_pincode, " +
//                    "ta.street_name AS temp_street, ta.state_name AS temp_state, ta.country_name AS temp_country, ta.pin_code AS temp_pincode " +
//                    "FROM employee_details e " +
//                    "LEFT JOIN address_details pa ON e.emp_id = pa.emp_id AND pa.flag = 'permanent' " +
//                    "LEFT JOIN address_details ta ON e.emp_id = ta.emp_id AND ta.flag = 'temporary' " +
//                    "WHERE e.emp_id=?";
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setInt(1, empId);
//            resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                int empId1 = resultSet.getInt("empId");
//                String firstName = resultSet.getString("firstName");
//                String middleName = resultSet.getString("middleName");
//                String lastName = resultSet.getString("lastName");
//                Long empMobileNumber = resultSet.getLong("empMobileNumber");
//
//                String tempStreet = resultSet.getString("tempStreet");
//                String tempState = resultSet.getString("tempState");
//                String tempCountry = resultSet.getString("tempCountry");
//                int tempPincode = resultSet.getInt("tempPincode");
//                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);
//
//                String permStreet = resultSet.getString("permStreet");
//                String permState = resultSet.getString("permState");
//                String permCountry = resultSet.getString("permCountry");
//                int permPincode = resultSet.getInt("permPincode");
//                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);
//
//                EmployeeDetails employeeDetails1 = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId1, tempAddress, permAddress);
//                employeeDetails.add(employeeDetails1);
//            }
//
//        } finally {
//            connection.close();
//            resultSet.close();
//            preparedStatement.close();
//        }
//        return employeeDetails;
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
                "pa.street_name AS perm_street, pa.state_name AS perm_state, pa.country_name AS perm_country, pa.pin_code AS perm_pincode, " +
                "ta.street_name AS temp_street, ta.state_name AS temp_state, ta.country_name AS temp_country, ta.pin_code AS temp_pincode " +
                "FROM employee_details e " +
                "LEFT JOIN address_details pa ON e.emp_id = pa.emp_id AND pa.flag = 'permanent' " +
                "LEFT JOIN address_details ta ON e.emp_id = ta.emp_id AND ta.flag = 'temporary' " +
                "WHERE e.emp_id=?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,empId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int empId1 = resultSet.getInt("emp_id");
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                Long empMobileNumber = resultSet.getLong("mobile_number");

                // Parse permanent address
                String permStreet = resultSet.getString("perm_street");
                String permState = resultSet.getString("perm_state");
                String permCountry = resultSet.getString("perm_country");
                int permPincode = resultSet.getInt("perm_pincode");
                EmployeeAddress permAddress = new EmployeeAddress(permStreet, permState, permCountry, permPincode);

                // Parse temporary address
                String tempStreet = resultSet.getString("temp_street");
                String tempState = resultSet.getString("temp_state");
                String tempCountry = resultSet.getString("temp_country");
                int tempPincode = resultSet.getInt("temp_pincode");
                EmployeeAddress tempAddress = new EmployeeAddress(tempStreet, tempState, tempCountry, tempPincode);

                // Create EmployeeDetails object and add to list
                EmployeeDetails employeeDetails = new EmployeeDetails(firstName, middleName, lastName, empMobileNumber, empId1);
                employeeDetails.setPermAddress(permAddress);
                employeeDetails.setTempAddress(tempAddress);
                employeeDetailsList.add(employeeDetails);
            }
        } catch (SQLException e) {
            throw new ConnectionFailureException("Failed to retrieve employee details: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return employeeDetailsList;
    }
}





