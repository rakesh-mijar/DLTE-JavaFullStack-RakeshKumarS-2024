package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;

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
    public static void main( String[] args )
    {
        storageTarget=new FileStorageTarget();
        services=new UserServices(storageTarget);
        int option=0;
        do{
            System.out.println(resourceBundle.getString("app.greet"));
            System.out.println(resourceBundle.getString("app.menu"));
            option=scanner.nextInt();
            switch (option){
                case 1:loggingIn();
                break;
                case 2:
                    System.out.println("Enter the account details");
                    User user =new User();
                    System.out.println("Enter the username");
                    user.setUserName(scanner.next());
                    System.out.println("Enter the password");
                    user.setUserPassword(scanner.next());
                    System.out.println("Enter the mail id");
                    user.setUserMailId(scanner.next());

                    System.out.println("Enter the contact number");
                    user.setContactInfo(scanner.nextLong());
                    scanner.nextLine();
                    System.out.println("Entrer the Address");
                    user.setUserAddress(scanner.nextLine());
                    System.out.println("Enter the initial Balance");
                    user.setInitialBalance(scanner.nextDouble());
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
        User current=null;
        try{
            System.out.println(resourceBundle.getString("app.username"));
            current=services.callFindById(scanner.next());
            System.out.println(resourceBundle.getString("app.password"));
            String password=scanner.next();
            if(current.getUserPassword().equals(password)){
                App.user=current;
                System.out.println(resourceBundle.getString("app.log.ok"));
            }
            else{
                throw new UserException("Invalid Password");
            }
        }catch (UserException userException){
            System.out.println(userException);
            App.loggingIn();
        }
    }
}
