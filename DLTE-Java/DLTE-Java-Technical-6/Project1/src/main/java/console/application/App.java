package console.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;


import static entities.validation.ValidateInputs.isvalidatePhone;
import static entities.validation.ValidateInputs.isvalidatePin;

public class App {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    //static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static Logger logger = LoggerFactory.getLogger(App.class);
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {

        while (true) {
            try {
                System.out.println(resourceBundle.getString("employee.dashboard"));
                App app = new App();
                System.out.println(resourceBundle.getString("employee.menu"));
                int choice = scanner.nextInt();
                String chance;
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        try {
                            collectEmployeeDetails();
                        } catch (backend.exceptions.ConnectionFailureException | SQLException ex) {
                            System.out.println(resourceBundle.getString("no.connection"));
                        }catch (backend.exceptions.UserAlreadyExistsException userEx){
                            System.out.println(resourceBundle.getString("employee.exists")+"\n");
                        }
                        break;
                    case 2:
//                        Implementations.App app1 = new Implementations.App();
////                        List<Entitties.Console.data.EmployeeDetails>;
//                        List<Entities.Backend.data.EmployeeDetails> employeeList = app1.employeeOutputDetails();
//                        int counter = 0;
//                        for (Entities.Backend.data.EmployeeDetails employee : employeeList) {
//                            System.out.println("\nThe Employee " + (counter + 1) + " details are: ");
//                            System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
//                            System.out.println("Employee ID: " + employee.getEmpId());
//                            System.out.println("Employee Mobile Number: " + employee.getEmpMobileNumber());
//                            System.out.println("Permanent Address " + employee.getPermAddress().getStreet()+","+employee.getPermAddress().getState()+","+employee.getPermAddress().getCountry()+","+employee.getPermAddress().getPincode());
//                            System.out.println("Temporary Details "+employee.getTempAddress().getStreet()+","+employee.getTempAddress().getState()+","+employee.getTempAddress().getCountry()+","+employee.getTempAddress().getPincode());
//                            counter++;
//                        }
                         try {
                             interfaces.EmployeeInterface app1 = new implementations.App();

                             //Implementations.App app1 = new Implementations.App();
                              List<Entitties.Console.data.EmployeeDetails> consoleEmployees = new ArrayList<>();
                              List<Entities.Backend.EmployeeDetails> backendEmployees = app1.employeeOutputDetails();


                            for (Entities.Backend.EmployeeDetails backendEmployee : backendEmployees) {
                                Entitties.Console.data.EmployeeDetails consoleEmployee = new Entitties.Console.data.EmployeeDetails();

                                consoleEmployee.setEmpId(backendEmployee.getEmpId());
                                consoleEmployee.setFirstName(backendEmployee.getFirstName());
                                consoleEmployee.setMiddleName(backendEmployee.getMiddleName());
                                consoleEmployee.setLastName(backendEmployee.getLastName());
                                consoleEmployee.setEmpMobileNumber(backendEmployee.getEmpMobileNumber());

                                // Set permanent address if not null
                                Entities.Backend.EmployeeAddress backendPermanentAddress = backendEmployee.getPermAddress();
                                if (backendPermanentAddress != null) {
                                    Entitties.Console.data.EmployeeAddress consolePermanentAddress = new Entitties.Console.data.EmployeeAddress();
                                    consolePermanentAddress.setStreet(backendPermanentAddress.getStreet());
                                    consolePermanentAddress.setState(backendPermanentAddress.getState());
                                    consolePermanentAddress.setCountry(backendPermanentAddress.getCountry());
                                    consolePermanentAddress.setPincode(backendPermanentAddress.getPincode());
                                    consoleEmployee.setPermAddress(backendPermanentAddress);
                                }

                            // Set temporary address if not null
                            Entities.Backend.EmployeeAddress backendTemporaryAddress = backendEmployee.getTempAddress();
                            if (backendTemporaryAddress != null) {
                                Entitties.Console.data.EmployeeAddress consoleTemporaryAddress = new Entitties.Console.data.EmployeeAddress();
                                consoleTemporaryAddress.setStreet(backendTemporaryAddress.getStreet());
                                consoleTemporaryAddress.setState(backendTemporaryAddress.getState());
                                consoleTemporaryAddress.setCountry(backendTemporaryAddress.getCountry());
                                consoleTemporaryAddress.setPincode(backendTemporaryAddress.getPincode());
                                consoleEmployee.setTempAddress(backendTemporaryAddress);//setTempAddress(consoleTemporaryAddress);
                            }

                            // Add console employee to the lists
                            consoleEmployees.add(consoleEmployee);
                        }


                        int counter = 0;
                        for (Entitties.Console.data.EmployeeDetails employee : consoleEmployees) {
                            System.out.println("\nThe Employee " + (counter + 1) + " details are: ");
                            System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
                            System.out.println("Employee ID: " + employee.getEmpId());
                            System.out.println("Employee Mobile Number: " + employee.getEmpMobileNumber());
                            System.out.println("Permanent Address " + employee.getPermAddress().getStreet() + "," + employee.getPermAddress().getState() + "," + employee.getPermAddress().getCountry() + "," + employee.getPermAddress().getPincode());
                            System.out.println("Temporary Details " + employee.getTempAddress().getStreet() + "," + employee.getTempAddress().getState() + "," + employee.getTempAddress().getCountry() + "," + employee.getTempAddress().getPincode() + "\n");
                            counter++;
                        }

                         } catch (backend.exceptions.ConnectionFailureException | SQLException ex){
                         System.out.println(resourceBundle.getString("no.connection"));
                         }
                        break;


                    case 3: try {
                        // Initialize your App instance
                        interfaces.EmployeeInterface app1 = new implementations.App();

                        //implementations.App app1= new implementations.App();

                        System.out.println("Enter the pincode to search for:");
                        int pincode = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        // Invoke the method to find employees by pincode
                        List<Entities.Backend.EmployeeDetails> employees = app1.findEmployeesByPincode(pincode);

                        // Display the results
                        if (employees.isEmpty()) {
                            System.out.println(resourceBundle.getString("no.employee"));
                            logger.info("no.employee");
                        } else {
                            System.out.println("Employees found with the provided pincode:");
                            for (Entities.Backend.EmployeeDetails employee : employees) {
                                System.out.println("Employee ID: " + employee.getEmpId());
                                System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
                                System.out.println("Mobile Number: " + employee.getEmpMobileNumber());
                                System.out.println("Permanent Address " + employee.getPermAddress().getStreet() + "," + employee.getPermAddress().getState() + "," + employee.getPermAddress().getCountry() + "," + employee.getPermAddress().getPincode());
                                System.out.println("Temporary Details " + employee.getTempAddress().getStreet() + "," + employee.getTempAddress().getState() + "," + employee.getTempAddress().getCountry() + "," + employee.getTempAddress().getPincode() + "\n");
                            }
                        }
                    } catch (backend.exceptions.ConnectionFailureException | SQLException ex){
                        System.out.println(resourceBundle.getString("no.connection"));
                    }
                        break;
                    default:
                        System.exit(0);
                        return;
                }


            } catch (InputMismatchException ex) {
                //logger.error("Invalid input!! Try again");
                System.out.println(resourceBundle.getString("wrong.input"));
                scanner.next();
            }
        }
    }
    static void collectEmployeeDetails() throws SQLException {
        do {
            Entities.Backend.EmployeeDetails employeeDetails = new Entities.Backend.EmployeeDetails();

            System.out.println("Enter the employee id");
            int empId = scanner.nextInt();
            employeeDetails.setEmpId(empId);
            scanner.nextLine();
            System.out.println("Enter employee first name");
            String firstName = scanner.nextLine();
            employeeDetails.setFirstName(firstName);
            System.out.println("Enter employee middle name");
            String middleName = scanner.nextLine();
            employeeDetails.setMiddleName(middleName);
            System.out.println("Enter employee last name");
            String lastName = scanner.nextLine();
            employeeDetails.setLastName(lastName);
            System.out.println("Enter the mobile number");
            Long empMobileNumber = scanner.nextLong();
            employeeDetails.setEmpMobileNumber(empMobileNumber);
            while (!isvalidatePhone(empMobileNumber)) {
                System.out.println(resourceBundle.getString("employee.contact.invalid"));
                empMobileNumber = scanner.nextLong();
            }
            scanner.nextLine();

            Entities.Backend.EmployeeAddress permAddress = new Entities.Backend.EmployeeAddress();

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
            int permPincode = scanner.nextInt();
            //permAddress.setPincode(Integer.parseInt(permPincode));
            permAddress.setPincode(permPincode);
            while (!isvalidatePin(permPincode)) {
                System.out.println(resourceBundle.getString("employee.pin.invalid"));
                //logger.warn("Invalid pin");
                permPincode = scanner.nextInt();
            }
            scanner.nextLine();

            Entities.Backend.EmployeeAddress tempAddress = new Entities.Backend.EmployeeAddress();

//                            EmployeeAddress tempAddress = new EmployeeAddress();
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
            int tempPincode = scanner.nextInt();
            //tempAddress.setPincode(Integer.parseInt(tempPincode));
            tempAddress.setPincode(tempPincode);
            while (!isvalidatePin(tempPincode)) {
                System.out.println(resourceBundle.getString("employee.pin.invalid"));
                //logger.warn("Invalid pin");
                tempPincode = scanner.nextInt();
            }

            employeeDetails.setTempAddress(tempAddress);
            employeeDetails.setPermAddress(permAddress);

            implementations.App app1 = new implementations.App();
            app1.writeEmployeeDetails(employeeDetails);

//                                boolean success = app1.writeEmployeeDetails(employeeDetails);//implementations.App.writeEmployeeDetails(employeeDetails);
//                                if (success) {
//                                    System.out.println(resourceBundle.getString("employee.create.success"));
//                                } else {
//                                    System.out.println(resourceBundle.getString("employee.create.failure"));
//                                }

            System.out.println(resourceBundle.getString("app.continue"));
        } while (scanner.nextInt() == 1);
    }

}