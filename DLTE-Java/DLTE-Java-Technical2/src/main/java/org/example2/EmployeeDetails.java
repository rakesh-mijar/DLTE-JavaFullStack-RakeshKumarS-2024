package org.example2;

import org.example.EmployeeAddress;
import org.example.EmployeeMain;
import org.example.ReadWriteOperations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmployeeDetails implements Serializable {
    //static int count;
    //static ArrayList<EmployeeMain> employee = new ArrayList<>();
    //static ArrayList<EmployeeAddress> empaddress=new ArrayList<>();
    public static void main(String[] args){

        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        ReadWriteOperations readWriteOperations = new ReadWriteOperations();
        EmployeeDetails details=new EmployeeDetails();
        Scanner scanner=new Scanner(System.in);
        int option=0;
        while(true){
            System.out.println(resourceBundle.getString("employee.greet"));
            System.out.println(resourceBundle.getString("employee.menu"));
            try{
                option= scanner.nextInt();
                switch(option){
                    case 1:
                            readWriteOperations.readDetails();
//                        char choice='Y';
//                        do {
//                            readWriteOperations.readDetails();
//                            System.out.println("Do you want to enter another employee details? (Yes/No)");
//                            choice=scanner.next().charAt(0);
//                            scanner.nextLine();
//                        }while(choice=='Y'||choice=='y');
//                        readWriteOperations.writeIntoFile(employee,empaddress);
                        break;

                    case 2:
                        readWriteOperations.displayEmpDetails();
                        break;

                    default:System.exit(0);
                }
            } catch (InputMismatchException ex) {
                logger.log(Level.WARNING,"Invalid input!! Try again");
                scanner.next();
            }

        }
    }
}