package org.example;

import sun.security.jgss.GSSCaller;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeDetails{
    static int count;
    static ArrayList<EmployeeMain>  employee = new ArrayList<>();
    static ArrayList<EmployeeAddress> empaddress=new ArrayList<>();
    public static void main(String[] args){

        //EmployeeMain[] employee=null;//reference to the EmployeeMain[] where size is based on the no. of employees
        EmployeeAddress[] empaddress=null;//reference to the EmployeeDetails[] where size is based on the no. of employees
        EmployeeDetails details=new EmployeeDetails();
        Scanner scanner=new Scanner(System.in);
        int option=0;
        while(true){
            System.out.println("Menu");
            System.out.println("1->Read Employee Details");
            System.out.println("2->Display Employee Details");
            System.out.println("3->Quit");
            System.out.println("Select your choice");
            option= scanner.nextInt();
            switch(option){
                case 1://System.out.println("Enter the number of employees");
                       //count=scanner.nextInt();
                       //employee = new EmployeeMain[count];
                       //empaddress = new EmployeeAddress[count];
                       //readDetails(employee,empaddress);
                    EmployeeDetails.readDetails();
                       break;
                case 2://displayEmpDetails(employee,empaddress);
                    EmployeeDetails.displayEmpDetails();
                break;
                case 3:System.exit(0);
            }
        }
    }



    //public static void readDetails(EmployeeMain[] employee,EmployeeAddress[] empaddress){
    public static void readDetails(){
        Scanner scanner=new Scanner(System.in);
        char choice=' ';
       do{
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
                //employee[index]=new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId);
                employee.add(new EmployeeMain(firstName,middleName,lastName,empMobileNumber,empId));

                //taking employee address using setters
                EmployeeAddress address=new EmployeeAddress();
                System.out.println("Enter the permanent address");
                System.out.println("Enter the street or locality");
                String permStreet=scanner.nextLine();
                address.setPermStreet(permStreet);
                System.out.println("Enter the State");
                String permState=scanner.nextLine();
                address.setPermState(permState);
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

                //empaddress[index]=address;
                empaddress.add(address);


                System.out.println("Do you want to enter another employee details? (Yes/No)");
                choice=scanner.next().charAt(0);
                scanner.nextLine();
           }
        }while(choice == 'Y' || choice == 'y');
    }

    //public static void displayEmpDetails(EmployeeMain[] employee,EmployeeAddress[] empaddress) {
    public static void displayEmpDetails(){
        int index=0;
        for(EmployeeMain each:employee){
            //EmployeeAddress address=empaddress[index];
            EmployeeAddress address=empaddress.get(index);
            System.out.println("Employee Details of "+(index+1));
            //printing basic details using toString() method
            System.out.println(each.toString());
            //printing the address using getters and setters
            System.out.println("Temporary address "+address.getTempStreet()+","+address.getTempState()+","+address.getTempCountry()+","+address.getPermPincode());
            System.out.println("Permanent address "+address.getPermStreet()+","+address.getPermState()+","+address.getPermCountry()+","+address.getPermPincode());
            index++;
        }
    }
}
