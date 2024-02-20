package branches.blocks;

import java.util.Scanner;

public class minimumBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] customerBalance = new double[20];
        for (int i = 0; i < customerBalance.length; i++) {
            System.out.println("enter the balance for customer " + (i + 1));
            customerBalance[i] = scanner.nextDouble();
        }
        minimumBalance.updatedBalances(customerBalance);
        System.out.println("Updated customer balances ");
        for (int i = 0; i < customerBalance.length; i++) {
            System.out.println("enter the balance for customer " + (i + 1)+" "+ customerBalance[i]);
        }

    }
    static void updatedBalances(double[] bal){
        for(int i=0;i<bal.length;i++){
            double currentBalance=bal[i];
            if(currentBalance<10000) {
                if (currentBalance >= 5000 && currentBalance <= 9999) {
                    bal[i] -= currentBalance * 0.3;
                }else if (currentBalance >= 1000 && currentBalance <= 4999) {
                    bal[i] -= currentBalance * 0.5;
                }
            }
        }
    }
}
