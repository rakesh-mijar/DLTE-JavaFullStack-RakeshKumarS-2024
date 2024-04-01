package org.example;

import java.util.ArrayList;

public interface EmployeeMediator  {
    void readDetails();
    ArrayList<EmployeeMain> readDetailsFromFile();
    ArrayList<EmployeeAddress> readAddressFromFile();
    Boolean isvalidatePhone(Long number);
    void displayEmpDetails();

}
