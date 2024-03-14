package org.example;

import org.example.DatabaseTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static UserServices services;
    private static StorageTarget storageTarget;
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private static Scanner scanner=new Scanner(System.in);
    private static User user;
    private static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main( String[] args )
    {
        //storageTarget=new FileStorageTarget();
        storageTarget = new DatabaseTarget();
        services=new UserServices(storageTarget);
        int option=0;
        do{
            System.out.println(resourceBundle.getString("app.greet"));
            System.out.println(resourceBundle.getString("app.menu"));
            option=scanner.nextInt();
            switch (option){
                case 1:loggingIn();
                    if (user!=null) {
                        System.out.println(resourceBundle.getString("app.greet"));
                        //System.out.println("Hello "+user.getUserName());
                        System.out.println(resourceBundle.getString("app.menu2"));
                        return;
                    }
                    break;
                case 2:
                    System.out.println("Enter the account details");
                    User user =new User();
                    System.out.println("Enter the username");
                    user.setUsername(scanner.next());
                    while (!isValidUsername(user.getUsername())) {
                        System.out.println(resourceBundle.getString("app.username.invalid"));
                        user.setUsername(scanner.next());
                    }
                    System.out.println("Enter the password");
                    user.setPassword(scanner.next());
                    while (!isValidPassword(user.getPassword())) { //validating password
//                        System.out.println(resourceBundle.getString("app.password.invalid"));
                        //invalid
                        System.out.println(resourceBundle.getString("app.password.format"));
                        user.setPassword(scanner.next());
                    }

                    System.out.println("Enter the mail id");
                    user.setEmail(scanner.next());
                    while (!isValidEmail(user.getEmail())) { //main-validation
                        System.out.println(resourceBundle.getString("app.mail.invalid"));
                        //invalid
                        user.setEmail(scanner.next());
                    }

                    System.out.println("Enter the contact number");
                    user.setContact(scanner.nextLong());
                    while (!isValidContactNumber(user.getContact())) { //validating contact
                        // if invalid
                        System.out.println(resourceBundle.getString("app.contact.invalid"));
                        user.setContact(scanner.nextLong());
                    }

                    scanner.nextLine();
                    System.out.println("Entrer the Address");
                    user.setAddress(scanner.nextLine());
                    System.out.println("Enter the initial Balance");
                    user.setBalance(scanner.nextDouble());
                    while (user.getBalance()<=0) {
                        logger.warn(resourceBundle.getString("app.balance.invalid"));
                        user.setBalance(scanner.nextDouble());
                    }
                    try{
                        services.callSave(user);
                    }
                    catch(UserException userException){
                        System.out.println(userException);
                    }
                    break;
                default:return;
            }
        }while(true);

    }
    public static void loggingIn(){
        //User current=null;
      User current = null;
        try{
            System.out.println(resourceBundle.getString("app.username"));
            current=services.callFindById(scanner.next());
            //System.out.println(resourceBundle.getString("app.password"));
            //String password=scanner.next();
            int maxAttempts = 5;
            int attempts = 0;
            String validPassword = current.getPassword(); // Replace with your desired valid password
            while (attempts < maxAttempts) {
                System.out.println(resourceBundle.getString("app.password"));
                String password = scanner.nextLine();
                scanner.next();
                try {
                    if (password.equals(validPassword)) {
                        logger.info(resourceBundle.getString("app.pass.ok"));
                        logger.info(resourceBundle.getString("app.log.ok"));
                        App.user=current;
                        break; // Exit the loop
                    } else {
                        logger.info(resourceBundle.getString("app.pass.not.ok"));
                        attempts++;
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                    attempts++;
                }
            }
            if (attempts == maxAttempts) {
                logger.warn(resourceBundle.getString("app.user.blocked"));
            }

        }catch (UserException userException){
            System.out.println(userException);
            App.loggingIn();
        }

//       User current=null;
//        try{
//            System.out.println(resourceBundle.getString("app.username"));
//            current=services.callFindById(scanner.next());
//            System.out.println(resourceBundle.getString("app.password"));
//            String password=scanner.next();
//            if(current.getPassword().equals(password)){
//                App.user=current;
//                System.out.println(resourceBundle.getString("app.log.ok"));
//            }
//            else{
//                throw new UserException("Invalid Password");
//            }
//        }catch (UserException userException){
//            System.out.println(userException);
//            App.loggingIn();
//        }
    }
    //Validation checking - Regex
    public static Boolean isValidEmail(String borrowerEmail) {
        String emailExpression = "^[A-Za-z0-9+_.-]+@[a-zA-Z]{3,}\\.[a-z]{2,}";
        Pattern pattern = Pattern.compile(emailExpression);
        Matcher matcher = pattern.matcher(borrowerEmail);
        return matcher.matches();
    }
    public static Boolean isValidContactNumber(Long contactNumber) {
        String contactString = Long.toString(contactNumber);
        String mobileExpression = "^(?!([0-9])\1{9}$)[0-9]{10}$";
        Pattern pattern = Pattern.compile(mobileExpression);
        Matcher matcher = pattern.matcher(contactString);
        return matcher.matches();
    }
    public static Boolean isValidPassword(String password) {
        String passwordExpression = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(passwordExpression);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static Boolean isValidUsername(String username) {
        String usernameExpression = "^[A-Za-z]\\w{5,29}$";
        Pattern pattern = Pattern.compile(usernameExpression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
