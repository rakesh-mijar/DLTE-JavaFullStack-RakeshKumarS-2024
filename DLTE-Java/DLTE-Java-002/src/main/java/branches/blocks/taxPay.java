package branches.blocks;

import java.util.Scanner;

public class taxPay {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        long salary;
        System.out.println("Enter salary amount");
        salary = sc.nextLong();
        int slabs;
        System.out.println("Enter the slabs(old=0/new=1)");
        slabs=sc.nextInt();
        switch(slabs){
            case 0:if(salary>=0 && salary<=250000){
                System.out.println(salary);
            }
            else if(salary>250000 && salary<=500000){
                salary = (long)(salary*0.005);
                System.out.println(salary);
            }
            else if(salary>500000 && salary<=750000){
                salary = (long)(salary*0.20);
                System.out.println(salary);
            }
            else if(salary>750000 && salary<=1000000){
                salary = (long)(salary*0.20);
                System.out.println(salary);
            }
            else if(salary>1000000 && salary<=1250000){
                salary = (long)(salary*0.30);
                System.out.println(salary);
            }
            else if(salary>1250000 && salary<=1500000){
                salary = (long)(salary*0.30);
                System.out.println(salary);
            }
            break;
            case 1:
        }
    }
}
