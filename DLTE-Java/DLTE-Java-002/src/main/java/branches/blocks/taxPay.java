package branches.blocks;

import java.util.Scanner;

public class taxPay {
    public static void main(String[] args){
        Double salaryAmount=0.0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your salary");
        salaryAmount = scanner.nextDouble();
        System.out.println("enter your choice to calculate tax");
        System.out.println("0->old slab"+"\n"+"1->new slab");
        int choice=scanner.nextInt();
        switch(choice){
            case 0://calculation of tax based on old slab
                if(salaryAmount<=250000){
                    System.out.println("No tax to be paid");
                }
                else if(salaryAmount>250000 && salaryAmount<=500000){
                    System.out.println("tax to be paid is "+(salaryAmount*0.05));
                }
                else if(salaryAmount>500000 && salaryAmount<=750000){
                    System.out.println("Tax to be paid is "+(salaryAmount*0.2));
                }
                else if(salaryAmount>750000 && salaryAmount<=1000000){
                    System.out.println("Tax to be paid is "+(salaryAmount*0.2));
                }
                else if(salaryAmount>1000000 && salaryAmount<=1250000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                else if(salaryAmount>1250000 && salaryAmount<=1500000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                else{
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                break;
            case 1://calculation of tax based on new slab
                if(salaryAmount<=250000){
                    System.out.println("No tax to be paid");
                }
                else if(salaryAmount>250000 && salaryAmount<=500000){
                    System.out.println("tax to be paid is "+(salaryAmount*0.05));
                }
                else if(salaryAmount>500000 && salaryAmount<=750000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.2);
                }
                else if(salaryAmount>750000 && salaryAmount<=1000000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.2);
                }
                else if(salaryAmount>1000000 && salaryAmount<=1250000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                else if(salaryAmount>1250000 && salaryAmount<=1500000){
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                else{
                    System.out.println("Tax to be paid is "+salaryAmount*0.3);
                }
                break;
        }
    }
}
