package org.middle;

import java.util.ArrayList;

public interface EmployeeMediator {
    void readEmployeeDetails(EmployeeMain employeeMain,EmployeeAddress tempAddress,EmployeeAddress permAddress);
    ArrayList<EmployeeMain> displayEmpDetails();
    Boolean isvalidatePhone(Long number);
    Boolean isvalidatePin(String pin);
    void searchByPin();
}
