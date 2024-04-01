package org.project2;

import org.project2.Entities.EmployeeAddress;
import org.project2.Entities.EmployeeDetails;

import java.sql.SQLException;

public interface EmployeeMediator {
    EmployeeDetails readEmployeeDetails();
    void displayEmpDetails(EmployeeDetails employeeDetails, EmployeeAddress tempAddress,EmployeeAddress permAddress);
    //Boolean isvalidatePhone(Long number);
    //void searchByPin();
    void writeIntoDatabase(EmployeeDetails employeeDetails) throws SQLException;
    EmployeeAddress readEmpPermAddress();
    EmployeeAddress readEmpTempDetails();
}
