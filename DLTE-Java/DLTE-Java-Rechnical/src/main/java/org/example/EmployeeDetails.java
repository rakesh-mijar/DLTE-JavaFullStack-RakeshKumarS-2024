package org.example;

import java.util.Scanner;

public class EmployeeDetails{
    static int count;
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number of employees");
        count=scanner.nextInt();
        EmployeeMain[] employee=new EmployeeMain[count];
        EmployeeAddress[] empaddress = new EmployeeAddress[count];
        for(int index=0;index<count;index++){
            System.out.println("Enter the employee id");
            Long empId=scanner.nextLong();
            scanner.nextLine();
            System.out.println("Enter employee first name");
            String firstName=scanner.nextLine();
            System.out.println("Enter employee middle name");
            String middleName=scanner.nextLine();
            System.out.println("Enter employee last name");
            String lastName=scanner.nextLine();
            System.out.println("Enter the mobile number");
            Long empMobileNumber=scanner.nextLong();

//            System.out.println("Enter the permanent address");
//            System.out.println("Enter the street or locality");
//            String permAdd1=scanner.nextLine();
//            System.out.println("Enter the State");
//            String permAdd2=scanner.nextLine();
//            System.out.println("Enter the Country");
//            String permAdd3=scanner.nextLine();
//            System.out.println("Enter the pincode");
//            Long pincode=scanner.nextLong();
//
//            System.out.println("Enter the Temporary address");
//            System.out.println("Enter the street or locality");
//            String tempAdd1=scanner.nextLine();
//            System.out.println("Enter the State");
//            String tempAdd2=scanner.nextLine();
//            System.out.println("Enter the Country");
//            String tempAdd3=scanner.nextLine();
//            System.out.println("Enter the pincode");
//            Long pincode2=scanner.nextLong();
            employee[index]=new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId);
        }
        EmployeeDetails details=new EmployeeDetails();
        details.displayEmpDetails(employee);
    }
    public void displayEmpDetails(EmployeeMain[] employee) {
        for(EmployeeMain each:employee){
            System.out.println(each.toString());
        }
    }
}
