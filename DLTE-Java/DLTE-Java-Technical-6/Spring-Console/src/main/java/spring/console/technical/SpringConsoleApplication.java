package spring.console.technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import spring.console.technical.services.EmployeeServices;
import technical.review.spring.entities.backend.EmployeeDetails;
import technical.review.spring.exceptions.ConnectionFailureException;
import technical.review.spring.exceptions.UserAlreadyExistsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

@SpringBootApplication
public class SpringConsoleApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringConsoleApplication.class, args);
        EmployeeServices employeeServices = applicationContext.getBean(EmployeeServices.class);

        ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
        Logger logger = LoggerFactory.getLogger(SpringConsoleApplication.class);
        Scanner scanner = new Scanner(System.in);
            //main application loop
            while (true) {
                try {
                    //displays the dashboard
                    System.out.println(resourceBundle.getString("employee.dashboard"));
                    //displays the menu
                    System.out.println(resourceBundle.getString("employee.menu"));
                    int choice = scanner.nextInt();
                    String chance;
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            //case for collecting the employee details
                            try {
                                employeeServices.collectEmployeeDetails();
                                logger.info("employee details collected");
                            } catch (ConnectionFailureException | SQLException ex) {
                                System.out.println(resourceBundle.getString("no.connection"));
                            }catch (UserAlreadyExistsException userEx){
                                System.out.println(resourceBundle.getString("employee.exists")+"\n");
                            }
                            break;

                        case 2:
                            //case for displaying employee details
                            try {

                                List<EmployeeDetails> backendEmployees=null;
                                try{
                                    URL url = new URL(resourceBundle.getString("find.all") );
                                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                                    con.setRequestMethod("GET");
                                    Gson gson = new Gson();
                                    int status = con.getResponseCode();

                                    if (status == HttpURLConnection.HTTP_OK) {
                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                        Type employeeListType = new TypeToken<List<EmployeeDetails>>() {}.getType();
                                        List<EmployeeDetails> employeeList = gson.fromJson(in, employeeListType);

                                        backendEmployees = employeeList;
                                    }
                                    else{
                                        System.out.println(resourceBundle.getString("fail.fetch"));
                                    }
                                }catch(IOException e){
                                    System.out.println(e);
                                }

                                // List<Entities.Backend.EmployeeDetails> backendEmployees = app1.employeeOutputDetails();
                                List<spring.console.technical.entities.EmployeeDetails> consoleEmployees = employeeServices.translateEntities(backendEmployees);


                                employeeServices.displayEmployeeDetails(consoleEmployees);

                            } catch (ConnectionFailureException ex){
                                System.out.println(resourceBundle.getString("no.connection"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;


                        case 3:
                            //case for display employee details based on pincode
                            try {
                                // Initialize your App instanc

                                System.out.println(resourceBundle.getString("enter.pincode"));
                                int pincode = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                List<EmployeeDetails> backendEmployees=null;
                                try{
                                    URL url = new URL(resourceBundle.getString("find.pincode")+pincode);
                                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                                    con.setRequestMethod("GET");
                                    Gson gson = new Gson();
                                    int status = con.getResponseCode();
                                    System.out.println(status);

                                    if (status == HttpURLConnection.HTTP_OK) {
                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                        System.out.println(in);
                                        Type employeeListType = new TypeToken<List<EmployeeDetails>>() {}.getType();
                                        List<EmployeeDetails> employeeList = gson.fromJson(in, employeeListType);

                                        backendEmployees = employeeList;
                                        List<spring.console.technical.entities.EmployeeDetails> consoleEmployees = employeeServices.translateEntities(backendEmployees);

                                        // Display the results as console entities
                                        if (consoleEmployees.isEmpty()) {
                                            System.out.println(resourceBundle.getString("no.employee"));
                                            logger.info("no.employee");
                                        } else {
                                            System.out.println(resourceBundle.getString("emp.pincode"));
                                            employeeServices.displayEmployeeDetails(consoleEmployees);
                                        }
                                    }
                                }catch(IOException e){
                                    System.out.println(e);
                                }catch (NullPointerException ex){
                                    backendEmployees=null;
                                    Gson gson=new Gson();
                                    String responseData = gson.toJson(backendEmployees);
                                    System.out.println(responseData);
                                }

                            } catch (ConnectionFailureException ex) {
                                System.out.println(resourceBundle.getString("no.connection"));
                            }
//                    } catch (NullPointerException ex){
//                            List<Entities.Backend.EmployeeDetails> backendEmployees=null;
//                            Gson gson=new Gson();
//                            String responseData = gson.toJson(backendEmployees);
//                            System.out.println(responseData);
//                        }
                            break;
                        case 4:
//                            //case for display the employee details based on emp id
//                            try {
//
//                                System.out.println("Enter the employee id to search for:");
//                                int empId = scanner.nextInt();
//                                scanner.nextLine(); // Consume newline character
//
//                                // Invoke the method to find employees by pincode
//                                List<EmployeeDetails> backendEmployees = null;
//
//                                try {
//                                    URL url = new URL("http://localhost:8083/employees/findById/" +empId);
//                                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                                    con.setRequestMethod("GET");
//                                    Gson gson = new Gson();
//                                    int status = con.getResponseCode();
//                                    System.out.println(status);
//
//                                    if (status == HttpURLConnection.HTTP_OK) {
//                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                                        System.out.println(in);
//                                        Type employeeListType = new TypeToken<List<EmployeeDetails>>() {}.getType();
//                                        List<EmployeeDetails> employeeList = gson.fromJson(in, employeeListType);
//
//                                        backendEmployees = employeeList;
//                                        List<spring.console.technical.entities.EmployeeDetails> consoleEmployees = employeeServices.translateEntities(backendEmployees);
//
//                                        // Display the results as console entities
//                                        if (consoleEmployees.isEmpty()) {
//                                            System.out.println(resourceBundle.getString("no.employee"));
//                                            logger.info("no.employee");
//                                        } else {
//                                            System.out.println("Employees found with the provided id:");
//
//                                            employeeServices.displayEmployeeDetails(consoleEmployees);
//                                        }
//                                    }
//                                } catch (IOException e) {
//                                    System.out.println(e);
//                                } catch (NullPointerException ex) {
//                                    backendEmployees = null;
//                                    Gson gson = new Gson();
//                                    String responseData = gson.toJson(backendEmployees);
//                                    System.out.println(responseData);
//                                }
//                            }  catch (ConnectionFailureException ex) {
//                        System.out.println(resourceBundle.getString("no.connection"));
//                    }
                            // Case for displaying employee details based on employee ID
                            try {
                                System.out.println("Enter the employee id to search for:");
                                int empId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character

                                // Invoke the method to find employees by ID
                                //EmployeeDetails backendEmployees = null;

                                try {
                                    URL url = new URL("http://localhost:8083/employees/findById/" + empId);
                                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                                    con.setRequestMethod("GET");
                                    Gson gson = new Gson();
                                    int status = con.getResponseCode();
                                    System.out.println(status);

                                    if (status == HttpURLConnection.HTTP_OK) {
                                        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                        Type employeeType = new TypeToken<EmployeeDetails>() {}.getType(); // Type for single EmployeeDetails object
                                        EmployeeDetails employee = gson.fromJson(in, employeeType); // Parse JSON response into EmployeeDetails object

                                        if (employee != null) {
                                            // Display employee details
                                            System.out.println("Employee found with the provided ID:");
                                            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
                                            System.out.println("Employee ID: " + employee.getEmpId());
                                            // Display other details as needed
                                           // employeeServices.displayEmployeeDetails(consoleEmployees);
                                        } else {
                                            System.out.println("No employee found with the provided ID.");
                                        }
                                    }
                                } catch (IOException e) {
                                    System.out.println("Error occurred while fetching employee details: " + e.getMessage());
                                } catch (NullPointerException ex) {
                                    System.out.println("Null pointer exception occurred while processing response.");
                                }
                            } catch (InputMismatchException ex) {
                                logger.error("Invalid input received.");
                                System.out.println("Invalid input received. Please enter a valid employee ID.");
                                scanner.next(); // Clear input buffer
                            } catch (ConnectionFailureException ex) {
                                System.out.println(resourceBundle.getString("no.connection"));
                            }
                            break;
                        default:
                            System.exit(0);
                            //return;
                    }
                } catch (InputMismatchException ex) {
                    logger.error(resourceBundle.getString("wrong.input"));
                    System.out.println(resourceBundle.getString("wrong.input"));
                    scanner.next();
                }
            }
        }
    }

