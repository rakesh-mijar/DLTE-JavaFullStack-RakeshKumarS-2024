package org.file;

import org.middle.EmployeeAddress;
import org.middle.EmployeeMain;
import org.middle.EmployeeMediator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmployeeFileRepo implements EmployeeMediator {
    private static final String filePath = "C:\\DLTE-JavaFullStack-RakeshKumarS-2024\\DLTE-Java\\Review\\details.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger= LoggerFactory.getLogger(EmployeeFileRepo.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");


    @Override
    public void readEmployeeDetails(){
        ArrayList<EmployeeMain> employees = new ArrayList<>();
        ArrayList<EmployeeAddress> addresses = new ArrayList<>();

        //int choice;
        do {
            try {
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
                while (!isvalidatePhone(empMobileNumber)) {
                    System.out.println(resourceBundle.getString("employee.contact.invalid"));
                    empMobileNumber = scanner.nextLong();
                }
                scanner.nextLine();
                EmployeeMain employee = new EmployeeMain(firstName, middleName, lastName, empMobileNumber, empId);

                EmployeeAddress permAddress = new EmployeeAddress();



                System.out.println("Enter the permanent address");

//                System.out.println("Enter the street or locality");
//                String permStreet = scanner.nextLine();
//                permAddress.setStreet(permStreet);
//                System.out.println("Enter the State");
//                String permState = scanner.nextLine();
//                permAddress.setState(permState);
//                System.out.println("Enter the Country");
//                String permCountry = scanner.nextLine();
//                permAddress.setCountry(permCountry);
//                System.out.println("Enter the pincode");
//                String permPincode = scanner.next();
//                permAddress.setPincode(permPincode);
//                while (!Validates.isvalidatePin(permPincode)) {
//                    System.out.println(resourceBundle.getString("employee.pin.invalid"));
//
//                    //logger.warn("Invalid pin");
//                    permPincode = scanner.next();
//                }
//                scanner.nextLine();
//
//                EmployeeAddress tempAddress = new EmployeeAddress();
//                System.out.println("Enter the temporary address");
//                System.out.println("Enter the street or locality");
//                String tempStreet = scanner.nextLine();
//                tempAddress.setStreet(tempStreet);
//                System.out.println("Enter the State");
//                String tempState = scanner.nextLine();
//                tempAddress.setState(tempState);
//                System.out.println("Enter the Country");
//                String tempCountry = scanner.nextLine();
//                tempAddress.setCountry(tempCountry);
//                System.out.println("Enter the pincode");
//                String tempPincode = scanner.next();
//                tempAddress.setPincode(tempPincode);

                employees.add(employee);
//                addresses.add(address);
                addresses.add(tempAddress);
                addresses.add(permAddress);

//                System.out.println("Do you want to enter another employee details? (Yes/No)");
//                choice=scanner.next().charAt(0);
//                scanner.nextLine();

                System.out.println(resourceBundle.getString("app.continue"));

            } catch (Exception e) {
                //System.out.println("Invalid input. Please try again.");
                logger.info("Invalid input. Please try again.");
                scanner.next();
            }
//        }while(choice=='Y'||choice=='y');
        }while (scanner.nextInt() == 1);
        writeEmployeeDetailsToFile(employees, addresses);
    }

    @Override
    public void displayEmpDetails() {
        ArrayList<EmployeeMain> employees = readEmployeesFromFile();
        ArrayList<EmployeeAddress> addresses = readAddressesFromFile();

        int index = 0;
        for (EmployeeMain employee : employees) {
            if(index>=addresses.size()){
                throw new EmployeeException("Address details ar not found for employee "+employee.getEmpId());
            }
            EmployeeAddress address = addresses.get(index);
            System.out.println("Employee Details of " + (index + 1));
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Middle Name: " + employee.getMiddleName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Employee ID: " + employee.getEmpId());
            System.out.println("Mobile Number: " + employee.getEmpMobileNumber());
            System.out.println("Permanent address " + + "," + address.getPermanentState()+ "," + address.getPermanentCountry() + "," + address.getPermanentPincode());
            System.out.println("Temporary address " + address.getTemporaryStreet()+ "," + address.getTemporaryState() + "," + address.getTemporaryCountry() + "," + address.getTemporaryPincode());
            System.out.println("\n");
            index++;
        }
    }

    private void writeEmployeeDetailsToFile(ArrayList<EmployeeMain> employees, ArrayList<EmployeeAddress> addresses) {
        //here before writing into the file we read the exiting contents into the corresponding arraylist
        //then add the new data to the arraylist then write into the file
        ArrayList<EmployeeMain> existingEmployees = readEmployeesFromFile();
        ArrayList<EmployeeAddress> existingAddresses = readAddressesFromFile();
        existingEmployees.addAll(employees);
        existingAddresses.addAll(addresses);
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(existingEmployees);
            objectOutputStream.writeObject(existingAddresses);
           // System.out.println("Employee details saved successfully.");
            logger.info("Employee details saved successfully");
        } catch (IOException e) {
            logger.warn("Error occurred while writing to file");
            //System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    private ArrayList<EmployeeMain> readEmployeesFromFile() {
        //read the baic employee details from the file
        //create a arraylist entity to store the read objects
        ArrayList<EmployeeMain> employees = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees = (ArrayList<EmployeeMain>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.warn("Error occurred while writing to file");
            //System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
        return employees;
    }

    private ArrayList<EmployeeAddress> readAddressesFromFile() {
        //read the address details from the file
        //create a arraylist entity to store the read objects
        ArrayList<EmployeeAddress> addresses = new ArrayList<>();
        try{FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectInputStream.readObject();
            addresses = (ArrayList<EmployeeAddress>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.warn("Error occurred while writing to file");
            //System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
        return addresses;
    }
@Override
    public Boolean isvalidatePhone(Long number) {
        String contactString=Long.toString(number);
        String expression="\\d{10}";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher=pattern.matcher(contactString);
        return matcher.matches();

    }

    @Override
    public void searchByPin() {
            System.out.println("Enter the pincode");
            String pincode = scanner.next();
            ArrayList<EmployeeMain> employeeMains = readEmployeesFromFile();
            ArrayList<EmployeeAddress> employeeAddresses = readAddressesFromFile();
            boolean found = false;
            int size = employeeAddresses.size();
            for (int index = 0; index < size; index++) {
                EmployeeAddress address = employeeAddresses.get(index);
                if (address.getPermanentPincode().equals(pincode) || address.getTemporaryPincode().equals(pincode)) {
                    EmployeeMain employeeMain = employeeMains.get(index);
                    found = true;
                    System.out.println("Details of employee with pincode " + pincode);
                    System.out.println("Employee id " + employeeMain.getEmpId());
                    System.out.println("Employee First Name " + employeeMain.getFirstName());
                    System.out.println("Employee Middle Name " + employeeMain.getMiddleName());
                    System.out.println("Employee Last Name " + employeeMain.getLastName());
                    System.out.println("Employee Mobile Number " + employeeMain.getEmpMobileNumber());
                    System.out.println("Permanent Address " + address.getPermanentStreet() + "," + address.getPermanentState() + "," + address.getPermanentCountry());
                    System.out.println("Temporary Address " + address.getTemporaryStreet() + "," + address.getTemporaryState() + "," + address.getTemporaryCountry());
                    System.out.println("\n");
                }
            }
    }

    @Override
    public void readEmplyoeeAddress() {

    }

}