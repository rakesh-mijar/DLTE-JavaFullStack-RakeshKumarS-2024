package branches.blocks;

import java.util.Scanner;

public class personalLoan
{
    public static void main(String[] args){
        String borrowerName="",borrowerAddress="",borrowerPan="",borrowerEmail="",borrowerIncomeType="";
        Long aadhar=0L,mobileNumber=0L;
        Double loanAmount=0.0;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------------Welcome to My Bank-------------- ");
        System.out.println("Enter your name");
        borrowerName=scanner.nextLine();
        System.out.println("Enter your address");
        borrowerAddress=scanner.nextLine();
        System.out.println("Select Borrower income type(Salaried or self-employed)");
        borrowerIncomeType=scanner.nextLine();
        System.out.println("Enter your mail id");
        borrowerEmail=scanner.next();
        System.out.println("Enter your pan number");
        borrowerPan=scanner.next();
        System.out.println("Enter your aadhar number");
        aadhar=scanner.nextLong();
        System.out.println("Enter your mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the loan amount");
        loanAmount=scanner.nextDouble();


        System.out.println("Dear "+borrowerName+" Thanks for showing interest on taking personal loan in MyBank your application has submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobileNumber);
    }

}

