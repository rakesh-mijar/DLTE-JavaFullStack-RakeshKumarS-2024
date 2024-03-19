package org.example;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserSoapService userSoapService = new UserSoapService();
        Scanner scanner=new Scanner(System.in);
        UserSoap soap = userSoapService.getUserSoapPort();
        System.out.println("1->Create a user\n2->Find By UserName\n3->Find transactions by username");
        System.out.println("Enter your Choice");
        User user=new User();
        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1:
                System.out.println("Enter the Username");
                String username=scanner.nextLine();
                user.setUsername(username);
                System.out.println("Enter your Password");
                String password = scanner.next();
                user.setPassword(password);
                System.out.println("Enter your Address");
                String address = scanner.next();
                user.setAddress(address);
                System.out.println("Enter your email");
                String email = scanner.next();
                user.setEmail(email);
                System.out.println("Enter the initial balance");
                Double initial = scanner.nextDouble();
                user.setBalance(initial);
                System.out.println("Enter your Contact");
                Long contact = scanner.nextLong();
                user.setContact(contact);
                soap.createAccount(user);
                System.out.println("User Created");
                break;
            case 2:
                System.out.println("Enter the username");
                String username2=scanner.nextLine();
                user=soap.readById("username2");
                System.out.println(user.getUsername()+","+user.email+","+user.getBalance());
                break;
            case 3:
                System.out.println("Enter your Username");
                String username3 = scanner.next();
                List<Transaction> transactions = soap.readAllById("username3").getTransactions();
                for (Transaction each: transactions) {
                    System.out.println(each.getTransactionDoneBy()+" "+each.getTransactionType()+" "+each.getTransactionAmount());
                }
                break;

            default:
                System.out.println("Invalid Choice");
                return;
        }
//        String username = "rak@123";
//        List<Transaction> transactions = soap.readAllById(username).getTransactions();
//        for (Transaction each: transactions) {
//            System.out.println(each.getTransactionDoneBy()+" "+each.getTransactionType()+" "+each.getTransactionAmount());
//        }
    }
}


