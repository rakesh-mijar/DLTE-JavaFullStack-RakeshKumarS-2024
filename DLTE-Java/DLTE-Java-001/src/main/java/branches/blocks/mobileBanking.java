package branches.blocks;

import java.util.Scanner;

public class mobileBanking {
    public static void main(String[] args){
        String userName="",emailAddress="",userLanguage="",password="";
        Long accountNumber=0L,mobileNumber=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the user name");
        userName=scanner.nextLine();
        System.out.println("Enter your account number");
        accountNumber=scanner.nextLong();
        System.out.println("Enter the email address");
        emailAddress = scanner.next();
        System.out.println("Enter the mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the password");
        password = scanner.next();
        System.out.println("Select language of your choice to recieve notifications(English/Kannada)");
        userLanguage=scanner.next();
        System.out.println("Dear "+userName+" your mobile banking registeration is successful.You will recieve notifications in "+userLanguage+" For any support,contact us!!");

    }

}
