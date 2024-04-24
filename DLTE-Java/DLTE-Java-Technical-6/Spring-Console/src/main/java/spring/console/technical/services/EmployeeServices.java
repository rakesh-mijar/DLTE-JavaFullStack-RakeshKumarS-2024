package spring.console.technical.services;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.console.technical.repository.EmployeeRepository;
import technical.review.spring.entities.backend.EmployeeAddress;
import technical.review.spring.entities.backend.EmployeeDetails;

import technical.review.spring.exceptions.ValidationException;
import technical.review.spring.interfaces.backend.EmployeeInterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static spring.console.technical.validation.ValidateInputs.isvalidatePhone;
import static spring.console.technical.validation.ValidateInputs.isvalidatePin;

@Service
public class EmployeeServices implements EmployeeRepository {

    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    Logger logger = LoggerFactory.getLogger(EmployeeServices.class);
    Scanner scanner = new Scanner(System.in);


    @Override
    public void collectEmployeeDetails() throws SQLException {
        //method for collecting the employee details and setting the value of backend entities
            EmployeeDetails employeeDetails = new EmployeeDetails();
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


                    EmployeeAddress permAddress = new EmployeeAddress();

                    System.out.println(resourceBundle.getString("perm.address"));
                    System.out.println(resourceBundle.getString("house.name"));
                    String permStreet = scanner.nextLine();
                    permAddress.setStreetName(permStreet);
                    System.out.println(resourceBundle.getString("state.name"));
                    String permState = scanner.nextLine();
                    permAddress.setStateName(permState);
                    System.out.println(resourceBundle.getString("country.name"));
                    String permCountry = scanner.nextLine();
                    permAddress.setCountryName(permCountry);

                    permAddress.setFlag("permanent");
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

                    EmployeeAddress tempAddress = new EmployeeAddress();

                    System.out.println(resourceBundle.getString("temp.address"));
                    System.out.println(resourceBundle.getString("house.name"));
                    String tempStreet = scanner.nextLine();
                    tempAddress.setStreetName(tempStreet);
                    System.out.println(resourceBundle.getString("state.name"));
                    String tempState = scanner.nextLine();
                    tempAddress.setStateName(tempState);
                    System.out.println(resourceBundle.getString("country.name"));
                    String tempCountry = scanner.nextLine();
                    tempAddress.setCountryName(tempCountry);
                    tempAddress.setFlag("temporary");
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

                    //   implementations.App app1 = new implementations.App();

                    try {
                        URL url = new URL(resourceBundle.getString("write.data"));
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json");
                        con.setDoOutput(true);//Sets the HttpURLConnection to allow output (i.e., to send data).....if not given java.net.ProtocolException: cannot write to a URLConnection if doOutput=false - call setDoOutput(true)

                        Gson gson = new Gson();
                        String jsonInputString = gson.toJson(employeeDetails);

                        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"))) {
                            writer.write(jsonInputString);
                        }


                        int responseCode = con.getResponseCode();
                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            System.out.println(resourceBundle.getString("employee.create.success"));
                        } else {
                            // System.out.println("Failed to create employee record. HTTP Error: " + responseCode);
                            System.out.println(resourceBundle.getString("employee.create.failure"));
                        }
                    }catch(IOException e) {
                        System.out.println(e);
                    }



//                app1.writeEmployeeDetails(employeeDetails);

//                                boolean success = app1.writeEmployeeDetails(employeeDetails);//implementations.App.writeEmployeeDetails(employeeDetails);
//                                if (success) {
//                                    System.out.println(resourceBundle.getString("employee.create.success"));
//                                } else {
//                                    System.out.println(resourceBundle.getString("employee.create.failure"));
//                                }


                    System.out.println(resourceBundle.getString("app.continue"));
                } while (scanner.nextInt() == 1);
                //Handling validation exception
            }catch(ValidationException valExp){
                System.out.println("Validation Error: " + valExp.getMessage());
                if(valExp.getMessage()==resourceBundle.getString("VAL-001")){
                    System.out.println(resourceBundle.getString("emp.number"));
                    Long empMobileNumber = scanner.nextLong();
                    employeeDetails.setEmpMobileNumber(empMobileNumber);
                    EmployeeDetails emp=new EmployeeDetails();
                    emp.setEmpMobileNumber(empMobileNumber);
                }
            }
    }

    @Override
    public List<spring.console.technical.entities.EmployeeDetails> translateEntities(List<EmployeeDetails> backendEmployees) throws IOException {
        //method for translating the backend entities into console entities prior to display in console
            List<spring.console.technical.entities.EmployeeDetails> consoleEmployees = new ArrayList<>();

            //http://localhost:7001/Technical-RestServices/pincode/?pincode=456789
            //http://localhost:7001/Technical-RestServices/all/
            try {
                for (EmployeeDetails backendEmployee : backendEmployees) {
                    spring.console.technical.entities.EmployeeDetails consoleEmployee = new spring.console.technical.entities.EmployeeDetails();

                    consoleEmployee.setEmpId(backendEmployee.getEmpId());
                    consoleEmployee.setFirstName(backendEmployee.getFirstName());
                    consoleEmployee.setMiddleName(backendEmployee.getMiddleName());
                    consoleEmployee.setLastName(backendEmployee.getLastName());
                    consoleEmployee.setEmpMobileNumber(backendEmployee.getEmpMobileNumber());

                    // Set permanent address if not null
                    EmployeeAddress backendPermanentAddress = backendEmployee.getPermAddress();
                    if (backendPermanentAddress != null) {
                        spring.console.technical.entities.EmployeeAddress consolePermanentAddress = new spring.console.technical.entities.EmployeeAddress();
                        consolePermanentAddress.setStreetName(backendPermanentAddress.getStreetName());
                        consolePermanentAddress.setStateName(backendPermanentAddress.getStateName());
                        consolePermanentAddress.setCountryName(backendPermanentAddress.getCountryName());
                        consolePermanentAddress.setPincode(backendPermanentAddress.getPincode());
                        consolePermanentAddress.setEmpId(backendPermanentAddress.getEmpId());
                        consolePermanentAddress.setFlag(backendPermanentAddress.getFlag());
                        consoleEmployee.setPermAddress(consolePermanentAddress);
                    }

                    // Set temporary address if not null
                    EmployeeAddress backendTemporaryAddress = backendEmployee.getTempAddress();
                    if (backendTemporaryAddress != null) {
                        spring.console.technical.entities.EmployeeAddress consoleTemporaryAddress = new spring.console.technical.entities.EmployeeAddress();
                        consoleTemporaryAddress.setStreetName(backendTemporaryAddress.getStreetName());
                        consoleTemporaryAddress.setStateName(backendTemporaryAddress.getStateName());
                        consoleTemporaryAddress.setCountryName(backendTemporaryAddress.getCountryName());
                        consoleTemporaryAddress.setPincode(backendTemporaryAddress.getPincode());
                        consoleTemporaryAddress.setEmpId(backendTemporaryAddress.getEmpId());
                        consoleTemporaryAddress.setFlag(backendTemporaryAddress.getFlag());
                        consoleEmployee.setTempAddress(consoleTemporaryAddress);
                    }

                    // Add console employee to the lists
                    consoleEmployees.add(consoleEmployee);
                }
            }catch (NullPointerException ex){
                System.out.println("nothing to display");
            }

            return consoleEmployees;
        }


    @Override
    public void displayEmployeeDetails(List<spring.console.technical.entities.EmployeeDetails> consoleEmployees) {

            int counter = 0;
            for (spring.console.technical.entities.EmployeeDetails employee : consoleEmployees) {
                System.out.println("\nThe Employee " + (counter + 1) + " details are: ");
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getMiddleName() + " " + employee.getLastName());
                System.out.println("Employee ID: " + employee.getEmpId());
                System.out.println("Employee Mobile Number: " + employee.getEmpMobileNumber());

                // Print permanent address if available
                spring.console.technical.entities.EmployeeAddress permAddress = employee.getPermAddress();
                if (permAddress != null) {
                    System.out.println("Permanent Address: " + permAddress.getStreetName() + ", " +
                            permAddress.getStateName() + ", " +
                            permAddress.getCountryName() + ", " +
                            permAddress.getPincode());
                } else {
                    System.out.println(resourceBundle.getString("no.data"));
                }

                // Print temporary address if available
                spring.console.technical.entities.EmployeeAddress tempAddress = employee.getTempAddress();
                if (tempAddress != null) {
                    System.out.println("Temporary Address: " + tempAddress.getStreetName() + ", " +
                            tempAddress.getStateName() + ", " +
                            tempAddress.getCountryName() + ", " +
                            tempAddress.getPincode());
                } else {
                    System.out.println(resourceBundle.getString("no.data"));
                }

                System.out.println();
                counter++;
            }
        }

    }
