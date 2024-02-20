package branches.blocks;

import java.util.Scanner;

public class debitTransactions {
    public static void main(String[] args){
        //initialize
        int debit=0;
        Long prevTransaction=0L,currentTransaction=0L;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial transactions"); //input the first transaction
        prevTransaction=scanner.nextLong();
        for(int i=2;i<=10;i++){
            System.out.println("Enter the next transaction ");
            currentTransaction  = scanner.nextLong();
            if(prevTransaction > currentTransaction){ //if current transaction is less than previous transaction then debited
                debit++;//increment the count of debit
            }
            prevTransaction=currentTransaction;//re-initialize
        }
        System.out.println("The number of debits during 10 transaction is"+debit);

    }
}
