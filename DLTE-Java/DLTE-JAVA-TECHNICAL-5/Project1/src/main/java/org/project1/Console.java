package org.project1;

import org.project2.Entities.EmployeeAddress;
import org.project2.Entities.EmployeeDetails;
import org.project2.EmployeeMediator;
import org.project2.EmployeeMethods;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        //ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        int option;
        while (true) {
            //System.out.println(resourceBundle.getString("employee.greet"));
            //System.out.println(resourceBundle.getString("employee.menu"));
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Enter the employee id");
                        Long empId = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Enter employee first name");
                        String firstName = scanner.nextLine();
                        System.out.println("Enter employee middle name");
                        String middleName = scanner.nextLine();
                        System.out.println("Enter employee last name");
                        String lastName = scanner.nextLine();
                        System.out.println("Enter the mobile number");
                        Long empMobileNumber = scanner.nextLong();
                        scanner.nextLine();


                        EmployeeAddress permAddress = new EmployeeAddress();
                        System.out.println("Enter the permanent address");
                        System.out.println("Enter the street or locality");
                        String permStreet = scanner.nextLine();
                        permAddress.setStreet(permStreet);
                        System.out.println("Enter the State");
                        String permState = scanner.nextLine();
                        permAddress.setState(permState);
                        System.out.println("Enter the Country");
                        String permCountry = scanner.nextLine();
                        permAddress.setCountry(permCountry);
                        System.out.println("Enter the pincode");
                        Integer permPincode = scanner.nextInt();
                       permAddress.setPincode(permPincode);
                        scanner.nextLine();

                        EmployeeAddress tempAddress = new EmployeeAddress();
                        System.out.println("Enter the temporary address");
                        System.out.println("Enter the street or locality");
                        String tempStreet = scanner.nextLine();
                        tempAddress.setStreet(tempStreet);
                        System.out.println("Enter the State");
                        String tempState = scanner.nextLine();
                        tempAddress.setState(tempState);
                        System.out.println("Enter the Country");
                        String tempCountry = scanner.nextLine();
                        tempAddress.setCountry(tempCountry);
                        System.out.println("Enter the pincode");
                        Integer tempPincode = scanner.nextInt();
                        tempAddress.setPincode(tempPincode);

                        EmployeeDetails employee=new EmployeeDetails(firstName,middleName,lastName,empMobileNumber,empId,permAddress,tempAddress);
                        EmployeeMethods employeeMethods=new EmployeeMethods();
                        try{
                            employeeMethods.writeIntoDatabase(employee);
                        }catch (SQLException sql){
                            System.out.println(sql);
                        }

                        break;
                    case 2:
                      //  mediator.displayEmpDetails();
                        break;
//                    case 3:try {
//                        employeeFileRepo.searchByPin();
//                    }catch (EmployeeException e) {
//                        logger.warn(e.getMessage());
//                    }
                      //  break;
                    default:System.exit(0);
                }
            } catch (InputMismatchException | SQLException e) {
                // logger.log(Level.WARNING,"Invalid input!! Try again");
                //logger.error(e.getMessage());
                scanner.next();
            }
        }
    }
}
