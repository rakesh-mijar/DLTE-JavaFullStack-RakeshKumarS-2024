package branches.blocks;

import java.util.Scanner;

public class personalLoan
{
    public static void main(String[] args){
        String applicantName="",applicantAddress="",applicantEmail="";
        Long aadhar=0L,mobileNumber=0L,loanAmount=0L,monthlyIncome=0L;
        int loanTenure;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------------Welcome!!--------------");
        System.out.println("Enter your name");
        applicantName=scanner.nextLine();
        System.out.println("Enter your address");
        applicantAddress=scanner.nextLine();
        System.out.println("Enter your mail id");
        applicantEmail=scanner.next();
        System.out.println("Enter the loan amount");
        loanAmount=scanner.nextLong();
        System.out.println("Enter the tenure for loan");
        loanTenure=scanner.nextInt();
        System.out.println("Enter your monthly income");
        monthlyIncome=scanner.nextLong();
        System.out.println("Enter your aadhar number");
        aadhar=scanner.nextLong();
        System.out.println("Enter your mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Dear "+applicantName+" Thanks for showing interest on taking personal loan in MyBank your application has submitted and further details will be mailed to you "+applicantEmail);
    }

}

