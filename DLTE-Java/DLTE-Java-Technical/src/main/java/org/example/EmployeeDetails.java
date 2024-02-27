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
            scanner.nextLine();
            employee[index]=new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId);

            EmployeeAddress address=new EmployeeAddress();
           System.out.println("Enter the permanent address");
           System.out.println("Enter the street or locality");
           String permStreet=scanner.nextLine();
           address.setPermStreet(permStreet);
          System.out.println("Enter the State");
          String permState=scanner.nextLine();
          address.setPermStreet(permState);
          System.out.println("Enter the Country");
            String permCountry=scanner.nextLine();
            address.setPermCountry(permCountry);
            System.out.println("Enter the pincode");
            String pincode=scanner.next();
            address.setPermPincode(pincode);
            scanner.nextLine();

            System.out.println("Enter the temporary address");
            System.out.println("Enter the street or locality");
            String tempStreet=scanner.nextLine();
            address.setTempStreet(tempStreet);
            System.out.println("Enter the State");
            String tempState=scanner.nextLine();
            address.setTempState(tempState);
            System.out.println("Enter the Country");
            String tempCountry=scanner.nextLine();
            address.setTempCountry(tempCountry);
            System.out.println("Enter the pincode");
            String tempPincode=scanner.next();
            address.setTempPincode(tempPincode);

            empaddress[index]=address;
        }
        EmployeeDetails details=new EmployeeDetails();
        details.displayEmpDetails(employee,empaddress);
    }
    public void displayEmpDetails(EmployeeMain[] employee,EmployeeAddress[] empaddress) {
        for(int index=0;index<count;index++){
            System.out.println("Employee Details of "+(index+1));
            System.out.println(employee[index].toString());
            System.out.println("Permanent Address "+empaddress[index].getPermStreet()+","+empaddress[index].getPermState()+","+empaddress[index].getPermCountry()+","+empaddress[index].getPermPincode());
            System.out.println("Temporary Address "+empaddress[index].getTempStreet()+","+empaddress[index].getTempState()+","+empaddress[index].getTempCountry()+","+empaddress[index].getPermPincode());
        }
    }
}
