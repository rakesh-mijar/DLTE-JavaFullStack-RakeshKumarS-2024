package org.example;

import sun.security.util.Resources;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadWriteOperations implements EmployeeMediator{
    static ArrayList<EmployeeMain> employee=new ArrayList<>();
    static ArrayList<EmployeeAddress> empaddress=new ArrayList<>();
    private static final String FILE_PATH = "C:\\DLTE-JavaFullStack-RakeshKumarS-2024\\DLTE-Java\\DLTE-Java-Technical2\\demo.txt";
    @Override
    public void readDetails() {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Scanner scanner=new Scanner(System.in);
        //for(int index=0;index<count;index++){

        char choice='Y';
        do {
            // try{
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
            while(!isvalidatePhone(empMobileNumber)){
                System.out.println(resourceBundle.getString("employee.contact.invalid"));
                empMobileNumber=scanner.nextLong();
            }
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
            while(!isvalidatePin(pincode)){
                System.out.println(resourceBundle.getString("employee.pin.invalid"));
                pincode=scanner.next();
                address.setPermState(pincode);
            }
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
//        }catch (InputMismatchException ex){
//            System.out.println(resourceBundle.getString("wrong.input"));
//        }
        //writeIntoFile(employee,empaddress);



            //readWriteOperations.readDetails();
            System.out.println("Do you want to enter another employee details? (Yes/No)");
            choice=scanner.next().charAt(0);
            scanner.nextLine();
        }while(choice=='Y'||choice=='y');
        //readWriteOperations.writeIntoFile(employee,empaddress);
        writeIntoFile(employee,empaddress);


    }

    @Override
    public Boolean isvalidatePhone(Long number) {
        String contactString=Long.toString(number);
        String expression="\\d{10}";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(contactString);
        return matcher.matches();

    }

    public Boolean isvalidatePin(String pin) {
        String expression="^\\d{6}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(pin);
        return matcher.matches();
    }

    @Override
    public void displayEmpDetails() {
        ArrayList<EmployeeMain> empDetails=readDetailsFromFile();
        ArrayList<EmployeeAddress> empAddress=readAddressFromFile();
        int index=0;
        for(EmployeeMain each:empDetails){
            EmployeeAddress address=empAddress.get(index);
            System.out.println("Employee Details of "+(index+1));
            System.out.println(each.toString());
            System.out.println("Temporary address "+address.getTempStreet()+","+address.getTempState()+","+address.getTempCountry()+","+address.getPermPincode());
            System.out.println("Permanent address "+address.getPermStreet()+","+address.getPermState()+","+address.getPermCountry()+","+address.getPermPincode());
            System.out.println("\n");
            index++;
        }
    }



    public void writeIntoFile(ArrayList<EmployeeMain> employee, ArrayList<EmployeeAddress> empaddress) {
        ArrayList<EmployeeMain> existingEmployees = readDetailsFromFile();
        ArrayList<EmployeeAddress> existingAddresses = readAddressFromFile();
        existingEmployees.addAll(employee);
        existingAddresses.addAll(empaddress);
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(existingEmployees);
            objectOutputStream.writeObject(existingAddresses);
            System.out.println("Employee details saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }


    public ArrayList<EmployeeMain> readDetailsFromFile(){
        ArrayList<EmployeeMain> employees=new ArrayList<>();
        try{
            FileInputStream inputStream=new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);//reading java objects from an input stream
            employees =(ArrayList<EmployeeMain>) objectInputStream.readObject();
            System.out.println("Employee details read from file successfully!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(employees);
        return employees;
    }

    public ArrayList<EmployeeAddress> readAddressFromFile(){
        ArrayList<EmployeeAddress> employeesAddress=new ArrayList<>();
        try{
            FileInputStream inputStream=new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);//reading java objects from an input stream
            employeesAddress =(ArrayList<EmployeeAddress>) objectInputStream.readObject();
            System.out.println("Employee details read from file successfully!!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(employeesAddress);
        return employeesAddress;
    }



}
