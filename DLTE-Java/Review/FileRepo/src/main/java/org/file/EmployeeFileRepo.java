package org.file;

import org.middle.EmployeeAddress;
import org.middle.EmployeeMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EmployeeFileRepo {
    private static final String filePath = "C:\\DLTE-JavaFullStack-RakeshKumarS-2024\\DLTE-Java\\Review\\details.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static Logger logger= LoggerFactory.getLogger(EmployeeFileRepo.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
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
                EmployeeAddress address = new EmployeeAddress();
                System.out.println("Enter the permanent address");
                System.out.println("Enter the street or locality");
                String permStreet = scanner.nextLine();
                address.setPermanentStreet(permStreet);
                System.out.println("Enter the State");
                String permState = scanner.nextLine();
                address.setPermanentState(permState);
                System.out.println("Enter the Country");
                String permCountry = scanner.nextLine();
                address.setPermanentCountry(permCountry);
                System.out.println("Enter the pincode");
                String permPincode = scanner.next();
                address.setPermanentPincode(permPincode);
                while (!isvalidatePin(permPincode)) {
                    System.out.println(resourceBundle.getString("employee.pin.invalid"));
                    //logger.warn("Invalid pin");
                    permPincode = scanner.next();
                }
                scanner.nextLine();
                System.out.println("Enter the temporary address");
                System.out.println("Enter the street or locality");
                String tempStreet = scanner.nextLine();
                address.setTemporaryStreet(tempStreet);
                System.out.println("Enter the State");
                String tempState = scanner.nextLine();
                address.setTemporaryState(tempState);
                System.out.println("Enter the Country");
                String tempCountry = scanner.nextLine();
                address.setTemporaryCountry(tempCountry);
                System.out.println("Enter the pincode");
                String tempPincode = scanner.next();
                address.setTemporaryPincode(tempPincode);

                employees.add(employee);
                addresses.add(address);

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

    public void displayEmpDetails() {
        ArrayList<EmployeeMain> employees = readEmployeesFromFile();
        ArrayList<EmployeeAddress> addresses = readAddressesFromFile();

        int index = 0;
        for (EmployeeMain employee : employees) {
            EmployeeAddress address = addresses.get(index);
            System.out.println("Employee Details of " + (index + 1));
            System.out.println("First Name: " + employee.getFirstName());
            System.out.println("Middle Name: " + employee.getMiddleName());
            System.out.println("Last Name: " + employee.getLastName());
            System.out.println("Employee ID: " + employee.getEmpId());
            System.out.println("Mobile Number: " + employee.getEmpMobileNumber());
            System.out.println("Permanent address " + address.getPermanentStreet()+ "," + address.getPermanentState()+ "," + address.getPermanentCountry() + "," + address.getPermanentPincode());
            System.out.println("Temporary address " + address.getTemporaryStreet()+ "," + address.getTemporaryState() + "," + address.getTemporaryCountry() + "," + address.getTemporaryPincode());
            index++;
        }
    }

    private void writeEmployeeDetailsToFile(ArrayList<EmployeeMain> employees, ArrayList<EmployeeAddress> addresses) {
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
        ArrayList<EmployeeAddress> addresses = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            objectInputStream.readObject();
            addresses = (ArrayList<EmployeeAddress>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.warn("Error occurred while writing to file");
            //System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
        return addresses;
    }

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
    public void searchByPin() {
        System.out.println("Enter the pincode");
        String pinCode = scanner.next();
        //List<EmployeeDetails> employeeDetails = dataAnalyzer.employeeByPinCode(pinCode);
        ArrayList<EmployeeAddress> address=
        if(!employeeDetails.isEmpty()){
            System.out.println("Employee with following pincode");
            for(EmployeeDetails employee: employeeDetails) {
                System.out.println("Details of Employee with Employee pincode "+ pinCode);
                System.out.println(resourceBundle.getString("First.name")+employee.getPersonalDetails().getEmployeeFirstName());
                System.out.println(resourceBundle.getString("Middle.name") +employee.getPersonalDetails().getEmployeeMiddleName());
                System.out.println(resourceBundle.getString("Last.name") +employee.getPersonalDetails().getEmployeeLastName());
                System.out.println(resourceBundle.getString("Employee.ID")+employee.getPersonalDetails().getEmployeeId());
                System.out.println(resourceBundle.getString("Email")+employee.getPersonalDetails().getEmployeeEmail());
                System.out.println(resourceBundle.getString("phone.number")+employee.getPersonalDetails().getEmployeeMobileNumber());
                System.out.println();
                System.out.println("Temporary Address Details");
                System.out.println(resourceBundle.getString("temporary.house")+employee.getAddressDetails().getTemporaryHouseNumber());
                System.out.println(resourceBundle.getString("temporary.street")+employee.getAddressDetails().getTemporaryStreetAddress());
                System.out.println(resourceBundle.getString("temporary.city")+employee.getAddressDetails().getTemporaryCityName());
                System.out.println(resourceBundle.getString("temporary.state")+employee.getAddressDetails().getTemporaryState());
                System.out.println(resourceBundle.getString("temporary.pin")+employee.getAddressDetails().getTemporaryAddressPin());
                System.out.println();
                System.out.println("Permanent Address Details");
                System.out.println(resourceBundle.getString("permanent.house")+employee.getAddressDetails().getPermanentHouseNumber());
                System.out.println(resourceBundle.getString("permanent.street")+employee.getAddressDetails().getPermanentStreetAddress());
                System.out.println(resourceBundle.getString("permanent.city")+employee.getAddressDetails().getPermanentCityName());
                System.out.println(resourceBundle.getString("permanent.state")+employee.getAddressDetails().getPermanentState());
                System.out.println(resourceBundle.getString("permanent.pin")+employee.getAddressDetails().getPermanentAddressPin());
                System.out.println("-------------------------------------------------------------------------------------------------------");

            }
        }
        else
            System.out.println("Employee with pin code is not found");

    }

}