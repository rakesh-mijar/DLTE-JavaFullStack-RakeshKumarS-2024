package org.console;

import org.file.EmployeeFileRepo;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;


public class EmployeeConsole {
    private static final Scanner scanner = new Scanner(System.in);
    private static Logger logger= LoggerFactory.getLogger(EmployeeConsole.class);
    public static void main(String[] args){

        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        //Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


        EmployeeFileRepo employeeFileRepo=new EmployeeFileRepo();

        int option;
        while (true) {
            System.out.println(resourceBundle.getString("employee.greet"));
            System.out.println(resourceBundle.getString("employee.menu"));
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        employeeFileRepo.readEmployeeDetails();
                        break;
                    case 2:
                        employeeFileRepo.displayEmpDetails();
                        break;
                    case 3:try {
                        employeeFileRepo.searchByPin();
                    }catch (EmployeeException e) {
                        logger.warn(e.getMessage());
                    }
                        break;
                    default:System.exit(0);
                }
            } catch (InputMismatchException e) {
               // logger.log(Level.WARNING,"Invalid input!! Try again");
                logger.error(e.getMessage());
                scanner.next();
            }
        }
    }
}


