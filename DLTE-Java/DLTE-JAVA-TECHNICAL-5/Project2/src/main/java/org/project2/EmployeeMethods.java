package org.project2;

import org.project2.Entities.EmployeeAddress;
import org.project2.Entities.EmployeeDetails;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class EmployeeMethods implements EmployeeMediator {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public EmployeeMethods() throws SQLException {
        Database database = new Database();
        connection = database.getConnection();
    }



    @Override
    public EmployeeDetails readEmployeeDetails() {
        return null;
    }

    @Override
    public void displayEmpDetails(EmployeeDetails employeeDetails, EmployeeAddress tempAddress, EmployeeAddress permAddress) {

    }

    @Override
    public void writeIntoDatabase(EmployeeDetails employeeDetails) throws SQLException {
        if(employeeDetails==null){
            System.out.println("No employee");
            return;
        }
        String query="INSERT INTO employee (empId,firstName,middleName,lastName,empMobileNumber,permStreet,permState,permCountry,permPincode,tempStreet,tempState,tempCountry,tempPincode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.setLong(1,employeeDetails.getEmpId());
        preparedStatement.setString(2,employeeDetails.getFirstName());
        preparedStatement.setString(3,employeeDetails.getMiddleName());
        preparedStatement.setString(4,employeeDetails.getLastName());
        preparedStatement.setLong(5,employeeDetails.getEmpMobileNumber());
        preparedStatement.setString(6,employeeDetails.getPermAddress().getStreet());
        preparedStatement.setString(7,employeeDetails.getPermAddress().getState());
        preparedStatement.setString(8,employeeDetails.getPermAddress().getCountry());
        preparedStatement.setInt(9,employeeDetails.getPermAddress().getPincode());
        preparedStatement.setString(10,employeeDetails.getTempAddress().getStreet());
        preparedStatement.setString(11,employeeDetails.getTempAddress().getState());
        preparedStatement.setString(12,employeeDetails.getTempAddress().getCountry());
        preparedStatement.setInt(13,employeeDetails.getTempAddress().getPincode());
        int resultSet=preparedStatement.executeUpdate();
        if(resultSet!=0){
            System.out.println("done");
        }
        else{
            System.out.println("ERRor");
        }

    }

    @Override
    public EmployeeAddress readEmpPermAddress() {
        return null;
    }

    @Override
    public EmployeeAddress readEmpTempDetails() {
        return null;
    }
}
