package console.application;

import Entitties.Console.data.EmployeeAddress;
import Entitties.Console.data.EmployeeDetails;
import backend.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;


import static entities.validation.ValidateInputs.isvalidatePhone;
import static entities.validation.ValidateInputs.isvalidatePin;

public class App {
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private static Logger logger = LoggerFactory.getLogger(App.class);
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws SQLException {
        //main application loop
        while (true) {
            try {
                //displays the dashboard
                System.out.println(resourceBundle.getString("employee.dashboard"));
                App app = new App();
                //displays the menu
                System.out.println(resourceBundle.getString("employee.menu"));
                int choice = scanner.nextInt();
                String chance;
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        //case for collecting the employee details
                        try {
                            collectEmployeeDetails();
                            logger.info("employee details collected");
                        } catch (backend.exceptions.ConnectionFailureException | SQLException ex) {
                            System.out.println(resourceBundle.getString("no.connection"));
                        }catch (backend.exceptions.UserAlreadyExistsException userEx){
                            System.out.println(resourceBundle.getString("employee.exists")+"\n");
                        }
                        break;




                    case 2:
                        //case for displaying employee details
                         try {
                             interfaces.EmployeeInterface app1 = new implementations.App();

                              List<Entities.Backend.EmployeeDetails> backendEmployees = app1.employeeOutputDetails();
                              List<EmployeeDetails> consoleEmployees = translateEntities(backendEmployees);

                              displayEmployeeDetails(consoleEmployees);
                              logger.info("employee details displayed");

                         } catch (backend.exceptions.ConnectionFailureException | SQLException ex){
                         System.out.println(resourceBundle.getString("no.connection"));
                         }
                        break;


                    case 3:
                        //case for display employee details based on pincode
                        try {
                        // Initialize your App instance
                        interfaces.EmployeeInterface app1 = new implementations.App();

                        System.out.println("Enter the pincode to search for:");
                        int pincode = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        // Invoke the method to find employees by pincode and store in backendEmployees
                        List<Entities.Backend.EmployeeDetails> backendEmployees = app1.findEmployeesByPincode(pincode);

                        //translate backend to console entities
                        List<EmployeeDetails> consoleEmployees = translateEntities(backendEmployees);

                        // Display the results as console entities
                        if (consoleEmployees.isEmpty()) {
                            System.out.println(resourceBundle.getString("no.employee"));
                            logger.info("no.employee");
                        } else {
                            System.out.println("Employees found with the provided pincode:");

                            displayEmployeeDetails(consoleEmployees);
                        }
                    } catch (backend.exceptions.ConnectionFailureException | SQLException ex){
                        System.out.println(resourceBundle.getString("no.connection"));
                    }
                        break;
                    case 4:
                        //case for display the employee details based on emp id
                        try{
                        interfaces.EmployeeInterface app1 = new implementations.App();

                        System.out.println("Enter the employee id to search for:");
                        int empId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        // Invoke the method to find employees by pincode
                        List<Entities.Backend.EmployeeDetails> backendEmployees = app1.findEmployeesById(empId);

                        List<EmployeeDetails> consoleEmployees = translateEntities(backendEmployees);

                        // Display the results
                        if (consoleEmployees.isEmpty()) {
                            System.out.println(resourceBundle.getString("no.employee"));
                            logger.info("no.employee");
                        } else {
                            System.out.println("Employees found with the provided pincode:");

                            displayEmployeeDetails(consoleEmployees);
                        }
                } catch (backend.exceptions.ConnectionFailureException | SQLException ex){
                    System.out.println(resourceBundle.getString("no.connection"));
                }
                break;
                    default:
                        System.exit(0);
                        //return;
                }
            } catch (InputMismatchException ex) {
                logger.error("Invalid input!! Try again");
                System.out.println(resourceBundle.getString("wrong.input"));
                scanner.next();
            }
        }
    }
    //method for collecting the employee details and setting the value of backend entities
    static void collectEmployeeDetails() throws SQLException {
        Entities.Backend.EmployeeDetails employeeDetails = new Entities.Backend.EmployeeDetails();
        try {
            do {
                //Entities.Backend.EmployeeDetails employeeDetails = new Entities.Backend.EmployeeDetails();

                System.out.println(resourceBundle.getString("emp.id"));
                int empId = scanner.nextInt();
                employeeDetails.setEmpId(empId);
                scanner.nextLine();
                System.out.println(resourceBundle.getString("emp.first.name"));
                String firstName = scanner.nextLine();
                employeeDetails.setFirstName(firstName);
                System.out.println(resourceBundle.getString("emp.middle.name"));
                String middleName = scanner.nextLine();
                employeeDetails.setMiddleName(middleName);
                System.out.println(resourceBundle.getString("emp.last.name"));
                String lastName = scanner.nextLine();
                employeeDetails.setLastName(lastName);
                System.out.println(resourceBundle.getString("emp.number"));
                Long empMobileNumber = scanner.nextLong();
                employeeDetails.setEmpMobileNumber(empMobileNumber);
                while (!isvalidatePhone(empMobileNumber)) {
                    System.out.println(resourceBundle.getString("employee.contact.invalid"));
                    empMobileNumber = scanner.nextLong();
                }
                scanner.nextLine();

                Entities.Backend.EmployeeAddress permAddress = new Entities.Backend.EmployeeAddress();

                System.out.println(resourceBundle.getString("perm.address"));
                System.out.println(resourceBundle.getString("house.name"));
                String permStreet = scanner.nextLine();
                permAddress.setStreet(permStreet);
                System.out.println(resourceBundle.getString("state.name"));
                String permState = scanner.nextLine();
                permAddress.setState(permState);
                System.out.println(resourceBundle.getString("country.name"));
                String permCountry = scanner.nextLine();
                permAddress.setCountry(permCountry);
                System.out.println(resourceBundle.getString("pincode"));
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

                System.out.println(resourceBundle.getString("temp.address"));
                System.out.println(resourceBundle.getString("house.name"));
                String tempStreet = scanner.nextLine();
                tempAddress.setStreet(tempStreet);
                System.out.println(resourceBundle.getString("state.name"));
                String tempState = scanner.nextLine();
                tempAddress.setState(tempState);
                System.out.println(resourceBundle.getString("country.name"));
                String tempCountry = scanner.nextLine();
                tempAddress.setCountry(tempCountry);
                System.out.println(resourceBundle.getString("pincode"));
                int tempPincode = scanner.nextInt();
                //tempAddress.setPincode(Integer.parseInt(tempPincode));
                tempAddress.setPincode(tempPincode);
                while (!isvalidatePin(tempPincode)) {
                    System.out.println(resourceBundle.getString("employee.pin.invalid"));
                    //logger.warn("Invalid pin");
                    tempPincode = scanner.nextInt();
                }

                //use of setters instead of constructors
                employeeDetails.setTempAddress(tempAddress);
                employeeDetails.setPermAddress(permAddress);

                implementations.App app1 = new implementations.App();
                app1.writeEmployeeDetails(employeeDetails);

                                boolean success = app1.writeEmployeeDetails(employeeDetails);//implementations.App.writeEmployeeDetails(employeeDetails);
                                if (success) {
                                    System.out.println(resourceBundle.getString("employee.create.success"));
                                } else {
                                    System.out.println(resourceBundle.getString("employee.create.failure"));
                                }

                System.out.println(resourceBundle.getString("app.continue"));
            } while (scanner.nextInt() == 1);
            //Handling validation exception
        }catch(ValidationException valExp){
            System.out.println("Validation Error: " + valExp.getMessage());
            if(valExp.getMessage()==resourceBundle.getString("VAL-001")){
                System.out.println(resourceBundle.getString("emp.number"));
                Long empMobileNumber = scanner.nextLong();
                employeeDetails.setEmpMobileNumber(empMobileNumber);
                Entities.Backend.EmployeeDetails emp=new Entities.Backend.EmployeeDetails();
                emp.setEmpMobileNumber(empMobileNumber);
            }
        }
    }

    //method for translating the backend entities into console entities prior to display in console
    private static List<EmployeeDetails> translateEntities(List<Entities.Backend.EmployeeDetails> backendEmployees){
        List<EmployeeDetails> consoleEmployees = new ArrayList<>();

        for (Entities.Backend.EmployeeDetails backendEmployee : backendEmployees) {
            EmployeeDetails consoleEmployee = new EmployeeDetails();

            consoleEmployee.setEmpId(backendEmployee.getEmpId());
            consoleEmployee.setFirstName(backendEmployee.getFirstName());
            consoleEmployee.setMiddleName(backendEmployee.getMiddleName());
            consoleEmployee.setLastName(backendEmployee.getLastName());
            consoleEmployee.setEmpMobileNumber(backendEmployee.getEmpMobileNumber());

            // Set permanent address if not null
            Entities.Backend.EmployeeAddress backendPermanentAddress = backendEmployee.getPermAddress();
            if (backendPermanentAddress != null) {
                EmployeeAddress consolePermanentAddress = new EmployeeAddress();
                consolePermanentAddress.setStreet(backendPermanentAddress.getStreet());
                consolePermanentAddress.setState(backendPermanentAddress.getState());
                consolePermanentAddress.setCountry(backendPermanentAddress.getCountry());
                consolePermanentAddress.setPincode(backendPermanentAddress.getPincode());
                consoleEmployee.setPermAddress(backendPermanentAddress);
            }

            // Set temporary address if not null
            Entities.Backend.EmployeeAddress backendTemporaryAddress = backendEmployee.getTempAddress();
            if (backendTemporaryAddress != null) {
                EmployeeAddress consoleTemporaryAddress = new EmployeeAddress();
                consoleTemporaryAddress.setStreet(backendTemporaryAddress.getStreet());
                consoleTemporaryAddress.setState(backendTemporaryAddress.getState());
                consoleTemporaryAddress.setCountry(backendTemporaryAddress.getCountry());
                consoleTemporaryAddress.setPincode(backendTemporaryAddress.getPincode());
                consoleEmployee.setTempAddress(backendTemporaryAddress);//setTempAddress(consoleTemporaryAddress);
            }

            // Add console employee to the lists
            consoleEmployees.add(consoleEmployee);
        }
        return consoleEmployees;
    }

    //method for displaying the employee details
    private static void displayEmployeeDetails(List<EmployeeDetails> consoleEmployees){
        int counter = 0;
        for (EmployeeDetails employee : consoleEmployees) {
            System.out.println("\nThe Employee " + (counter + 1) + " details are: ");
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
            System.out.println("Employee ID: " + employee.getEmpId());
            System.out.println("Employee Mobile Number: " + employee.getEmpMobileNumber());
            System.out.println("Permanent Address " + employee.getPermAddress().getStreet() + "," + employee.getPermAddress().getState() + "," + employee.getPermAddress().getCountry() + "," + employee.getPermAddress().getPincode());
            System.out.println("Temporary Details " + employee.getTempAddress().getStreet() + "," + employee.getTempAddress().getState() + "," + employee.getTempAddress().getCountry() + "," + employee.getTempAddress().getPincode() + "\n");
            counter++;
        }
    }

}