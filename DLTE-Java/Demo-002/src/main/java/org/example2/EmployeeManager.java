package org.example2;


        import org.example3.EmployeeAddress;
        import org.example3.EmployeeMain;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Scanner;

public class EmployeeManager {
    private static final String FILE_PATH = "C:\\DLTE-JavaFullStack-RakeshKumarS-2024\\DLTE-Java\\DLTE-Java-Technical2\\demo.txt";//"employee_details.txt";
    private static Scanner scanner = new Scanner(System.in);
    public void addEmployee(){
        ArrayList<EmployeeMain> employees = new ArrayList<>();
        ArrayList<EmployeeAddress> addresses = new ArrayList<>();
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

                System.out.println("Do you want to add another employee? (1: Yes, 2: No)");
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.next();
            }
        } while (scanner.nextInt() == 1);
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
            System.out.println("Permanent address " + address.getPermanentStreet() + "," + address.getPermanentState() + "," + address.getPermanentCountry() + "," + address.getPermanentPincode());
            System.out.println("Temporary address " + address.getTemporaryStreet() + "," + address.getTemporaryState() + "," + address.getTemporaryCountry() + "," + address.getTemporaryPincode());
            index++;
        }
    }

    private void writeEmployeeDetailsToFile(ArrayList<EmployeeMain> employees, ArrayList<EmployeeAddress> addresses) {
        ArrayList<EmployeeMain> existingEmployees = readEmployeesFromFile();
        ArrayList<EmployeeAddress> existingAddresses = readAddressesFromFile();
        existingEmployees.addAll(employees);
        existingAddresses.addAll(addresses);
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH,true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(existingEmployees);
            objectOutputStream.writeObject(existingAddresses);
            System.out.println("Employee details saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    private ArrayList<EmployeeMain> readEmployeesFromFile() {
        ArrayList<EmployeeMain> employees = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            employees = (ArrayList<EmployeeMain>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
        return employees;
    }

    private ArrayList<EmployeeAddress> readAddressesFromFile() {
        ArrayList<EmployeeAddress> addresses = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            objectInputStream.readObject();
            addresses = (ArrayList<EmployeeAddress>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while reading from file: " + e.getMessage());
        }
        return addresses;
    }
}