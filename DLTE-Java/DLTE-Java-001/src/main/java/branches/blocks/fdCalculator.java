package branches.blocks;

import java.util.Scanner;

public class fdCalculator {
    public static void main(String[] args)
    {
        double principalAmount=0.0,interestRate=0.0;
        int tenureYears=0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the principle amount");
        principalAmount=scanner.nextDouble();
        System.out.println("enter the interest rate (in years)");
        interestRate=scanner.nextDouble();
        System.out.println("Enter the tenure in years");
        tenureYears=scanner.nextInt();
        System.out.println("Total Interest Rate is "+(principalAmount*interestRate*tenureYears)/100);
        System.out.println("Maturity amount is "+(principalAmount+(principalAmount*interestRate*tenureYears)/100));
    }
}
