package store.oops;

import java.util.Date;
import java.util.Scanner;

public class creditCardAnalysis {


    public static void main(String[] args){
        CreditCard[] myBank = {  //array of objects
                new CreditCard(8765678765678L,"Ramesh",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111),
                new CreditCard(9876543L,"Suresh",new Date(2023,02,25),432,150000,new Date(2028,4,9),new Date(2025,06,26),2345),
                new CreditCard(9876545678L,"Mahesh",new Date(2022,05,23),987,1234567,new Date(2026,5,1),new Date(2026,04,12),5432)
        };
        Scanner scanner=new Scanner(System.in);
        creditCardAnalysis analysis = new creditCardAnalysis();

        System.out.println("Enter the limit"); //Filter based on the given limit
        double limit = scanner.nextDouble();
        analysis.customersOnLimit(myBank,limit);

        System.out.println("Enter the date");//Filter based on the date of bill payment
        int day = scanner.nextInt();
        analysis.customersOnDate(myBank,day);

        System.out.println("Enter the credit card number");//Update specific PIN of customer
        long creditCardNumber = scanner.nextLong();
        analysis.updatePin(myBank,creditCardNumber);

        System.out.println("Enter the day of bill generation");//Update the limit of customers those date of bill generation is 05th
        int date = scanner.nextInt();
        analysis.updateLimit(myBank,date);
    }
    public void customersOnLimit(CreditCard[] customers,double limit){//Filter based on the given limit
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()<=limit){
                System.out.println(each.getCreditCardHolder()+" has limit "+each.getCreditCardLimit()+" lesser than specified limit"+limit);
            }
        }
    }
    public void customersOnDate(CreditCard[] customers,int day){//Filter based on the date of bill payment
        for(CreditCard each:customers){
            if(each.getDateOfBillPayement().getDay() <= day){
                System.out.println(each.getCreditCardHolder()+" paid bill before or on the same day as specified day "+day);
            }
        }
    }
    public void updatePin(CreditCard[] customers, long creditCardNumber){//Update specific PIN of customer
        for(CreditCard each:customers){
            if(each.getCreditCardNumber() == creditCardNumber){
                System.out.println("Enter the updated pin");
                Scanner scanner=new Scanner(System.in);
                long updatedPin=scanner.nextLong();
                each.setCreditCardNumber(updatedPin);
                System.out.println("Updated");
            }
        }
    }
    public void updateLimit(CreditCard[] customers,int date){//Update the limit of customers those date of bill generation is 05th
        for(CreditCard each:customers){
            if(each.getDateOfBillGeneration().getDay() == date){
                System.out.println("enter the limit to be updated");
                Scanner scanner=new Scanner(System.in);
                int updatedLimit=scanner.nextInt();
                each.setCreditCardLimit(updatedLimit);
                System.out.println("Updated");
            }
        }
    }

}
