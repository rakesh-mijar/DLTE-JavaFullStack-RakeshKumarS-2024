package branches.blocks;

import java.util.Scanner;

public class SIPCalculator {
    public static void main(String [] args){
        Scanner scanner=new Scanner(System.in);
        Double monthlyInvestment=0.0,expectedReturns=0.0,principleAmount=0.0,estimatedReturns=0.0,total=0.0,futureValue =0.0,periodicInRate=0.0;
        int periodYears;
        System.out.println("Enter the monthly investment");
        monthlyInvestment = scanner.nextDouble();
        System.out.println("Enter the expected returns is %");
        expectedReturns=scanner.nextDouble();
        System.out.println("Enter the period in years");
        periodYears = scanner.nextInt();
        principleAmount = monthlyInvestment * 12 * periodYears;
        periodicInRate=(expectedReturns/100)/12;
        futureValue = monthlyInvestment*((Math.pow(1+periodicInRate,periodYears*12)-1)/periodicInRate)*(1+periodicInRate);
        estimatedReturns=futureValue-principleAmount;
        System.out.println("Principle Amount "+principleAmount+"\n"+"Estimated Returns "+estimatedReturns+"\n"+"Total Interest "+futureValue);

    }
}
