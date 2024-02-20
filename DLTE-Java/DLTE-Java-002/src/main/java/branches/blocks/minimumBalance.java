package branches.blocks;

import java.util.Scanner;

public class minimumBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] customerBalance = new double[20];//initialize an array for 20 customers
        for (int i = 0; i < customerBalance.length; i++) {//enter the balance amount for 20 customers
            System.out.println("enter the balance for customer " + (i + 1));
            customerBalance[i] = scanner.nextDouble();
        }
        minimumBalance.updatedBalances(customerBalance);//call the update function defined outside the main
        System.out.println("Updated customer balances ");
        for (int i = 0; i < customerBalance.length; i++) {//print the updated value of balance within the main method
            System.out.println("enter the balance for customer " + (i + 1)+" "+ customerBalance[i]);
        }

    }
    static void updatedBalances(double[] bal){ //method to update the customer balance
        for(int i=0;i<bal.length;i++){
            double currentBalance=bal[i];
            if(currentBalance<10000) {
                if (currentBalance >= 5000 && currentBalance <= 9999) {//charges of 3% of their balance between 5000 to 9999
                    bal[i] -= currentBalance * 0.3;
                }else if (currentBalance >= 1000 && currentBalance <= 4999) {//charges 5% of their balance between 1000 to 4999
                    bal[i] -= currentBalance * 0.5;
                }
            }
        }
    }
}
