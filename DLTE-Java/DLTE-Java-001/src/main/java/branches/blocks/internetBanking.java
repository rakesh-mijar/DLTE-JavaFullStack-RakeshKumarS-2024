package branches.blocks;

import java.util.Scanner;

public class internetBanking {
    public static void main(String[] args){
        String customerName,customerAddress,branchName,ifscCode;
        Long aadhar=0L,mobileNumber=0L,accountNumber=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("------------Internet Banking-------");
        System.out.println("Enter your name");
        customerName=scanner.nextLine();
        System.out.println("Enter your address");
        customerAddress=scanner.nextLine();
        System.out.println("Enter your branch name");
        branchName=scanner.nextLine();
        System.out.println("Enter your IFSC code");
        ifscCode=scanner.nextLine();
        System.out.println("Enter your account number");
        accountNumber=scanner.nextLong();
        System.out.println("Enter your mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter your aadhar number");
        aadhar=scanner.nextLong();
        System.out.println("Thank you "+customerName+" for choosing internet banking we'll contact u soon through "+mobileNumber);

    }
}
