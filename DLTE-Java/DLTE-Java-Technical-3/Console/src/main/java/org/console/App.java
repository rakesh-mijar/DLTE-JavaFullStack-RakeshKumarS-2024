package org.console;

import org.database.DatabaseTarget;
import org.database.EmployeeServices;
import org.database.StorageTarget;
import org.database.UserException;
import org.middle.*;

import java.util.ArrayList;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws UserException {


        StorageTarget storageTarget=new DatabaseTarget();
        EmployeeServices employeeServices=new EmployeeServices(storageTarget);

        //-------to add the basic details------------
        //EmployeeMain employeeMain=new EmployeeMain("Mahesh","E","Hadagi",1234567L,12345L);
        EmployeeMain employeeMain=new EmployeeMain("Jaganath","P","Bhat",98765456L,98765L);

        //--------to add the address details--------------
        EmployeeAddress employeeAddress=new EmployeeAddress("Nalluru road","Karnataka","India","579803","Jnanasangama","kerala","India","875324");

        //        employeeAddress.setPermanentStreet("sanna cicle");
//        employeeAddress.setPermanentState("Karnataka");
//        employeeAddress.setPermanentCountry("India");
//        employeeAddress.setPermanentPincode("574223");
//        employeeAddress.setTemporaryStreet("Dodda circle");
//        employeeAddress.setPermanentState("kerala");
//        employeeAddress.setPermanentCountry("India");
//        employeeAddress.setPermanentPincode("5765324");

        //-------------method to read the data into db------------
        employeeServices.callRead(employeeMain,employeeAddress);

        //method to display the employee details from the db
//        ArrayList<EmployeeMain> employeeMains=employeeServices.calldisplayEmpDetails();
//        int index = 0;
//        for(EmployeeMain emp:employeeMains){
//            //System.out.println(emp);
//            System.out.println("Employee Details of " + (index + 1));
//            System.out.println("First Name: " + emp.getFirstName());
//            System.out.println("Middle Name: " + emp.getMiddleName());
//            System.out.println("Last Name: " + emp.getLastName());
//            System.out.println("Employee ID: " + emp.getEmpId());
//            System.out.println("Mobile Number: " + emp.getEmpMobileNumber());
//        }

//        employeeServices.calldisplayEmpDetails();
    }
}
