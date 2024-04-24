package technical.review.spring.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import technical.review.spring.entities.backend.EmployeeAddress;
import technical.review.spring.entities.backend.EmployeeDetails;
import technical.review.spring.exceptions.*;
import technical.review.spring.interfaces.backend.EmployeeInterface;
import technical.review.spring.validations.BackendValidation;

import java.rmi.ServerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class EmployeeServices implements EmployeeInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private static Logger logger = LoggerFactory.getLogger(EmployeeServices.class);
//    @Override
//    public boolean writeEmployeeDetails(EmployeeDetails employee) throws SQLException {
//        return false;
//    }

    @Override
    public String writeEmployeeDetails(EmployeeDetails employee) throws SQLException, ServerException {
        if (employee == null) {
            System.out.println(resourceBundle.getString("employee.empty"));
            return resourceBundle.getString("employee.empty");
        }

        try {
            // validating the mobile number
            if (!BackendValidation.isvalidatePhone(employee.getEmpMobileNumber())) {
                logger.info("Invalid employee mobile number: " + employee.getEmpMobileNumber());
                throw new ValidationException(resourceBundle.getString("VAL-001"));
            }

            // validating the pincode of both temporary and permanent address
            if (!BackendValidation.isvalidatePin(employee.getPermAddress().getPincode()) || !BackendValidation.isvalidatePin(employee.getTempAddress().getPincode())) {
                logger.info("employee.pin.invalid");
                throw new ValidationException(resourceBundle.getString("VAL-002"));
            }

            // Insert data into employee_input table
            int resultSet = jdbcTemplate.update("INSERT INTO employee_details VALUES (?, ?, ?, ?, ?)",
                    employee.getEmpId(), employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getEmpMobileNumber());

            // Check if the insert into employee_input was successful
            if (resultSet > 0) {
                // Insert data into permanent_address table
                int resultPermAddress = jdbcTemplate.update("INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?)",
                        employee.getEmpId(), employee.getPermAddress().getStreetName(), employee.getPermAddress().getStateName(),
                        employee.getPermAddress().getCountryName(), employee.getPermAddress().getPincode(),"permanent");
                if (resultPermAddress == 0) {
                   logger.error(resourceBundle.getString("db.push.fail"));
                    return resourceBundle.getString("db.push.fail");
                }

                // Insert data into temporary_address table
                int resultTempAddress = jdbcTemplate.update("INSERT INTO address_details VALUES (?, ?, ?, ?, ?, ?)",
                        employee.getEmpId(), employee.getTempAddress().getStreetName(), employee.getTempAddress().getStateName(),
                        employee.getTempAddress().getCountryName(), employee.getTempAddress().getPincode(), "temporary");
                if (resultTempAddress == 0) {
                    logger.error(resourceBundle.getString("db.push.fail"));
                    throw new InsertionException(resourceBundle.getString("db.push.fail"));
                }

                logger.error(resourceBundle.getString("db.push.ok"));
                return resourceBundle.getString("db.push.ok");
            }  else {
                logger.error(resourceBundle.getString("db.push.fail"));
                throw new InsertionException(resourceBundle.getString("db.push.fail"));
            }
        } catch (DataAccessException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("ORA-00001")) {
                logger.warn(resourceBundle.getString("Fail.insert") + " " + employee + " " + resourceBundle.getString("employee.exists"));
                throw new UserAlreadyExistsException(resourceBundle.getString("Fail.insert") + " " + employee.getEmpId() + " " + resourceBundle.getString("employee.exists"));
            } else {
                throw new ServerException( resourceBundle.getString("server.fail") + errorMessage);
            }
        }
    }


    @Override
    public List<EmployeeDetails> employeeOutputDetails() throws SQLException {
        List<EmployeeDetails> employees = new ArrayList<>();
        String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
                "pa.street_name AS perm_street, pa.state_name AS perm_state, pa.country_name AS perm_country, pa.pin_code AS perm_pincode, " +
                "ta.street_name AS temp_street, ta.state_name AS temp_state, ta.country_name AS temp_country, ta.pin_code AS temp_pincode " +
                "FROM employee_details e " +
                "LEFT JOIN address_details pa ON e.emp_id = pa.emp_id AND pa.flag = 'permanent' " +
                "LEFT JOIN address_details ta ON e.emp_id = ta.emp_id AND ta.flag = 'temporary'";

        try {
            List<EmployeeDetails> employeeRows = jdbcTemplate.query(query, new DetailsMapper());
            employees.addAll(employeeRows);
        } catch (DataAccessException e) {
            throw new ConnectionFailureException(resourceBundle.getString("fail.retrieve") + e.getMessage());
        }
        return employees;

//        List<EmployeeDetails> employees = new ArrayList<>();
//        String query = "SELECT * FROM employee_details";
//        String query2 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'permanent'";
//        String query3 = "SELECT * FROM address_details WHERE emp_id = ? AND flag = 'temporary'";
//
//        try {
//            List<Map<String, Object>> employeeRows = jdbcTemplate.queryForList(query);
//            for (Map<String, Object> employeeRow : employeeRows) {
//                EmployeeDetails employee = new EmployeeDetails();
//                int employeeId = ((Number) employeeRow.get("emp_id")).intValue();
//                employee.setEmpId(((Number) employeeRow.get("emp_id")).intValue());
//                employee.setFirstName((String) employeeRow.get("first_name"));
//                employee.setMiddleName((String) employeeRow.get("middle_name"));
//                employee.setLastName((String) employeeRow.get("last_name"));
//                employee.setEmpMobileNumber(((Number) employeeRow.get("mobile_number")).longValue());
//
//
//                // Fetch permanent address
//                List<Map<String, Object>> permanentAddressRows = jdbcTemplate.queryForList(query2, employeeId);
//                if (!permanentAddressRows.isEmpty()) {
//                    Map<String, Object> permanentAddressRow = permanentAddressRows.get(0);
//                    EmployeeAddress permanentAddress = new EmployeeAddress();
//                    permanentAddress.setEmpId(employeeId);
//                    permanentAddress.setStreetName((String) permanentAddressRow.get("street_name"));
//                    permanentAddress.setStateName((String) permanentAddressRow.get("state_name"));
//                    permanentAddress.setCountryName((String) permanentAddressRow.get("country_name"));
//                    permanentAddress.setPincode(((Number) permanentAddressRow.get("pin_code")).intValue());
//                    permanentAddress.setFlag((String) permanentAddressRow.get("flag"));
//                    employee.setPermAddress(permanentAddress);
//                }
//
//                // Fetch temporary address
//                List<Map<String, Object>> temporaryAddressRows = jdbcTemplate.queryForList(query3, employeeId);
//                if (!temporaryAddressRows.isEmpty()) {
//                    Map<String, Object> temporaryAddressRow = temporaryAddressRows.get(0);
//                    EmployeeAddress temporaryAddress = new EmployeeAddress();
//                    temporaryAddress.setEmpId(employeeId);
//                    temporaryAddress.setStreetName((String) temporaryAddressRow.get("street_name"));
//                    temporaryAddress.setStateName((String) temporaryAddressRow.get("state_name"));
//                    temporaryAddress.setCountryName((String) temporaryAddressRow.get("country_name"));
//                    temporaryAddress.setPincode(((Number) temporaryAddressRow.get("pin_code")).intValue());
//                    temporaryAddress.setFlag((String) temporaryAddressRow.get("flag"));
//                    employee.setTempAddress(temporaryAddress);
//                }
//
//                employees.add(employee);
//            }
//        } catch (DataAccessException e) {
//            throw new ConnectionFailureException("Failed to retrieve employee details: " + e.getMessage());
//        }
//        return employees;
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
            List<EmployeeDetails> rows = jdbcTemplate.query(query, new DetailsMapper(), pincode, pincode);
            employeeDetailsList.addAll(rows);

            // Check if the list is empty
            if (employeeDetailsList.isEmpty()) {
                throw new EmployeeException(resourceBundle.getString("no.employee") + pincode);
            }
        } catch (DataAccessException e) {
            throw new ConnectionFailureException(resourceBundle.getString("fail.retrieve") + e.getMessage());
        }
        return employeeDetailsList;

    }



    @Override
    public EmployeeDetails findEmployeesById(int empId) throws SQLException {
        //List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        String query = "SELECT e.emp_id, e.first_name, e.middle_name, e.last_name, e.mobile_number, " +
                "pa.street_name AS perm_street, pa.state_name AS perm_state, pa.country_name AS perm_country, pa.pin_code AS perm_pincode, " +
                "ta.street_name AS temp_street, ta.state_name AS temp_state, ta.country_name AS temp_country, ta.pin_code AS temp_pincode " +
                "FROM employee_details e " +
                "LEFT JOIN address_details pa ON e.emp_id = pa.emp_id AND pa.flag = 'permanent' " +
                "LEFT JOIN address_details ta ON e.emp_id = ta.emp_id AND ta.flag = 'temporary' " +
                "WHERE e.emp_id=?";
        try {
            EmployeeDetails employeeDetailsList = jdbcTemplate.queryForObject(query, new Object[]{empId}, new DetailsMapper());
            if (employeeDetailsList==null) {
                throw new EmployeeException("Employee with ID " + empId + " not found");
            }
            return employeeDetailsList;
        } catch (DataAccessException e) {
            throw new ConnectionFailureException(resourceBundle.getString("no.connect") + e.getMessage());
        }

//        try {
//            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, empId);
//            if (rows.isEmpty()) {
//                throw new EmployeeNotFoundException("Employee with ID " + empId + " not found");
//            }
//            for (Map<String, Object> row : rows) {
//                EmployeeDetails employee = new EmployeeDetails();
//                int empId1 = ((Number) row.get("emp_id")).intValue();
//                employee.setEmpId(empId1);
//                employee.setFirstName((String) row.get("first_name"));
//                employee.setMiddleName((String) row.get("middle_name"));
//                employee.setLastName((String) row.get("last_name"));
//                employee.setEmpMobileNumber(((Number) row.get("mobile_number")).longValue());
//
//                // Parse permanent address
//                if (row.get("perm_street") != null) {
//                    EmployeeAddress permanentAddress = new EmployeeAddress();
//                    permanentAddress.setEmpId(empId1);
//                    permanentAddress.setStreetName((String) row.get("perm_street"));
//                    permanentAddress.setStateName((String) row.get("perm_state"));
//                    permanentAddress.setCountryName((String) row.get("perm_country"));
//                    permanentAddress.setPincode(((Number) row.get("perm_pincode")).intValue());
//                    permanentAddress.setFlag("permanent");
//                    employee.setPermAddress(permanentAddress);
//                }
//
//                // Parse temporary address
//                if (row.get("temp_street") != null) {
//                    EmployeeAddress temporaryAddress = new EmployeeAddress();
//                    temporaryAddress.setEmpId(empId1);
//                    temporaryAddress.setStreetName((String) row.get("temp_street"));
//                    temporaryAddress.setStateName((String) row.get("temp_state"));
//                    temporaryAddress.setCountryName((String) row.get("temp_country"));
//                    temporaryAddress.setPincode(((Number) row.get("temp_pincode")).intValue());
//                    temporaryAddress.setFlag("temporary");
//                    employee.setTempAddress(temporaryAddress);
//                }
//
//                // Add the EmployeeDetails object to the list
//                employeeDetailsList.add(employee);
//            }
//        } catch (DataAccessException e) {
//            throw new ConnectionFailureException("Failed to retrieve employee details: " + e.getMessage());
//        }
//        return employeeDetailsList;

    }

    public class DetailsMapper implements RowMapper<EmployeeDetails> {

        @Override
        public EmployeeDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmpId(rs.getInt("emp_id"));
            employeeDetails.setFirstName(rs.getString("first_name"));
            employeeDetails.setMiddleName(rs.getString("middle_name"));
            employeeDetails.setLastName(rs.getString("last_name"));
            employeeDetails.setEmpMobileNumber(rs.getLong("mobile_number"));

            EmployeeAddress employeePermAddress = new EmployeeAddress();
            employeePermAddress.setEmpId(rs.getInt("emp_id"));
            employeePermAddress.setStreetName(rs.getString("perm_street"));
            employeePermAddress.setStateName(rs.getString("perm_state"));
            employeePermAddress.setCountryName(rs.getString("perm_country"));
            employeePermAddress.setPincode(rs.getInt("perm_pincode"));
            employeePermAddress.setFlag("permanent");

            EmployeeAddress employeeTempAddress = new EmployeeAddress();
            employeeTempAddress.setEmpId(rs.getInt("emp_id"));
            employeeTempAddress.setStreetName(rs.getString("temp_street"));
            employeeTempAddress.setStateName(rs.getString("temp_state"));
            employeeTempAddress.setCountryName(rs.getString("temp_country"));
            employeeTempAddress.setPincode(rs.getInt("temp_pincode"));
            employeeTempAddress.setFlag("temporary");

            employeeDetails.setPermAddress(employeePermAddress);
            employeeDetails.setTempAddress(employeeTempAddress);

            return employeeDetails;
        }
    }
}
