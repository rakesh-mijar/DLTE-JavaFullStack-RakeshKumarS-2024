package org.database;

import org.middle.EmployeeAddress;
import org.middle.EmployeeMain;
import org.middle.EmployeeMediator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EmployeeDatabase implements EmployeeMediator {
    private Connection connection;
    //actual implementation of the methods defined in the interface.
    //private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    //private Logger logger = LoggerFactory.getLogger(UserDatabaseRepository.class);
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public EmployeeDatabase(Connection connection) {
        this.connection=connection;
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("employee-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }

    }

    @Override
    public void readEmployeeDetails(EmployeeMain employee, EmployeeAddress address) {
        try{
            String query = "INSERT INTO employee_details (emp_id, first_name, middle_name, last_name, mobile_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, employee.getEmpId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getMiddleName());
            preparedStatement.setString(4, employee.getLastName());
            preparedStatement.setLong(5, employee.getEmpMobileNumber());
            preparedStatement.executeUpdate();

            query = "INSERT INTO employee_address (emp_id, permanent_street, permanent_state, permanent_country, permanent_pincode, temporary_street, temporary_state, temporary_country, temporary_pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, employee.getEmpId());
            preparedStatement.setString(2, address.getPermanentStreet());
            preparedStatement.setString(3, address.getPermanentState());
            preparedStatement.setString(4, address.getPermanentCountry());
            preparedStatement.setString(5, address.getPermanentPincode());
            preparedStatement.setString(6, address.getTemporaryStreet());
            preparedStatement.setString(7, address.getTemporaryState());
            preparedStatement.setString(8, address.getTemporaryCountry());
            preparedStatement.setString(9, address.getTemporaryPincode());
            preparedStatement.executeUpdate();

            logger.log(Level.INFO, "employee.record.inserted");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "employee.record.insert.error" + e.getMessage());
        }
    }


    @Override
    public void readEmployeeDetails(EmployeeMain employeeMain, EmployeeAddress tempAddress, EmployeeAddress permAddress) {
        try{
            String query = "INSERT INTO employee_details (emp_id, first_name, middle_name, last_name, mobile_number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, employeeMain.getEmpId());
            preparedStatement.setString(2, employeeMain.getFirstName());
            preparedStatement.setString(3, employeeMain.getMiddleName());
            preparedStatement.setString(4, employeeMain.getLastName());
            preparedStatement.setLong(5, employeeMain.getEmpMobileNumber());
            preparedStatement.executeUpdate();

            query = "INSERT INTO employee_permAddress (emp_id, permanent_street, permanent_state, permanent_country, permanent_pincode) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, employeeMain.getEmpId());
            preparedStatement.setString(2, permAddress.getStreet());
            preparedStatement.setString(3, permAddress.getState());
            preparedStatement.setString(4, permAddress.getCountry());
            preparedStatement.setString(5, permAddress.getPincode());

            query = "INSERT INTO employee_tempAddress(emp_id,temporary_street,temporary_state,temporary_country,temporary_pincode) VALUES(?,?,?,?,?)";
            preparedStatement.setString(1, employeeMain.getEmpId());
            preparedStatement.setString(2, permAddress.getStreet());
            preparedStatement.setString(3, permAddress.getState());
            preparedStatement.setString(4, address.getTemporaryPincode());
            preparedStatement.executeUpdate();

        }

    @Override
    public ArrayList<EmployeeMain> displayEmpDetails() {
        ArrayList<EmployeeMain> employees = new ArrayList<>();
        try {
            String query = "SELECT * FROM employee_details";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long empId = resultSet.getLong("emp_id");
                String firstName = resultSet.getString("first_name");
                String middleName = resultSet.getString("middle_name");
                String lastName = resultSet.getString("last_name");
                long empMobileNumber = resultSet.getLong("mobile_number");
                employees.add(new EmployeeMain(firstName, middleName, lastName, empMobileNumber, empId));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "employee.retrieve.error" + e.getMessage());
        }
        return employees;

    }

    @Override
    public Boolean isvalidatePhone(Long aLong) {
        return null;
    }

    @Override
    public Boolean isvalidatePin(String s) {
        return null;
    }

    @Override
    public void searchByPin() {

    }
}
