package branches.blocks;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class carLoanValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String borrowerName, borrowerAadharCard, borrowerPanNumber, borrowerMobileNo, borrowerEmailAddrs, borrowerIncomeType;
        System.out.println("--------Welcome to my Neww Bank-----------------");
        System.out.println("Enter your name");
        borrowerName=scanner.nextLine();
        Pattern pattern=Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)*$");
        Matcher matcher=pattern.matcher(borrowerName);
        if(matcher.matches()){
            System.out.println("Valid name!!");
        }
        else{
            System.out.println("Invalid name!!");
        }
        System.out.println("Enter your aadhar number");
        borrowerAadharCard=scanner.next();
        pattern=Pattern.compile("\\d{12}");
        matcher=pattern.matcher(borrowerAadharCard);
        if(matcher.matches()){
            System.out.println("Valid aadhar number!!");
        }
        else{
            System.out.println("Invalid aadhar number!!");
        }
        System.out.println("Enter your pan number");
        borrowerPanNumber=scanner.next();
        pattern=Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]");
        matcher=pattern.matcher(borrowerPanNumber);
        if(matcher.matches()){
            System.out.println("Valid pan number!!");
        }
        else{
            System.out.println("Invalid pan number!!");
        }
        System.out.println("Enter your mobile number");
        borrowerMobileNo=scanner.next();
        pattern=Pattern.compile("[0-9]{10}");
        matcher=pattern.matcher(borrowerMobileNo);
        if(matcher.matches()){
            System.out.println("Valid mobile number!!");
        }
        else{
            System.out.println("Invalid mobile number!!");
        }
        System.out.println("Enter your mail address");
        borrowerEmailAddrs=scanner.next();
        pattern=Pattern.compile("[a-zA-Z]{3,}@[a-zA-Z]{3,5}.[a-zA-Z]{3,5}");
        matcher=pattern.matcher(borrowerEmailAddrs);
        if(matcher.matches()){
            System.out.println("Valid email address!!");
        }
        else{
            System.out.println("Invalid email address!!");
        }
        System.out.println("Enter your income type");
        borrowerIncomeType=scanner.next();
        pattern=Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z])*");
        matcher=pattern.matcher(borrowerIncomeType);
        if(matcher.matches()){
            System.out.println("Valid income type!!");
        }
        else{
            System.out.println("Invalid income type!!");
        }
        System.out.println("Dear "+borrowerName+" Thanks for showing interest in car loan in 'MY NEW BANK' .Your application is submitted and further details will be mailed to your "+borrowerEmailAddrs+" or sms to "+borrowerMobileNo);


    }
}

