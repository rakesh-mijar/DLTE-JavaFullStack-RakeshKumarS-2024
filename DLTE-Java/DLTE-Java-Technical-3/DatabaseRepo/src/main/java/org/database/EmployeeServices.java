package org.database;

import org.middle.EmployeeAddress;
import org.middle.EmployeeMain;
import org.middle.EmployeeMediator;

import java.util.ArrayList;

public class EmployeeServices {
    //not actual implementation but in further the methods defined in the middleware is called here so that the xceptions are handeled
    EmployeeMediator employeeMediator;
    public EmployeeServices(StorageTarget storageTarget) {
        employeeMediator=storageTarget.getEmployee();
    }
    public void callRead(EmployeeMain employeeMain, EmployeeAddress employeeAddress){
        employeeMediator.readEmployeeDetails(employeeMain,employeeAddress);
    }
    public ArrayList<EmployeeMain> calldisplayEmpDetails(){
        ArrayList<EmployeeMain> emp= employeeMediator.displayEmpDetails();
        return emp;
    }
}
