package org.example;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionAnalysis implements Runnable {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Lock lock=new ReentrantLock();
    //Array of objects for Transaction
    Transaction analysis[]={
            new Transaction(new Date(2024,3,12),1000.0,"Razi","Education"),
            new Transaction(new Date(2023,5,15),2000.0,"Pranav","Health"),
            new Transaction(new Date(2022,8,29),3000.0,"Venkatesh","Friend"),
            new Transaction(new Date(2025,9,15),4000.0,"Nazir","Friend")
    };

    //filter based on range of date specified
    public void filterByDate(Transaction[] analysis,int startDate,int endDate){
        for(Transaction each:analysis){
            if(startDate<=each.getDateOfTransaction().getDate()&&endDate>=each.getDateOfTransaction().getDate()){
                System.out.println("Transactions done within the range are by "+each.getReciept()+" and transaction on "+each.getDateOfTransaction());
            }
        }
    }

    //among the different transaction print the least transaction
    public void filterLeastAmount(Transaction[] analysis){
        Double minAmount=analysis[0].getTransactionAmount();
        for (Transaction each:analysis){
            if(each.getTransactionAmount()<=minAmount){
                minAmount=each.getTransactionAmount();
            }
        }
        System.out.println("Minimum Transaction amount is "+minAmount);
    }

    //among the different transaction print the maximum transaction
    public void filterMaximumAmount(Transaction[] analysis){
        Double maxAmount=analysis[0].getTransactionAmount();
        for(Transaction each:analysis){
            if (each.getTransactionAmount()>=maxAmount){
                maxAmount=each.getTransactionAmount();
            }
        }
        System.out.println("Maximum Transaction amount is "+maxAmount);
    }

    //based on the reciept name print the transaction amount
    public void filterReciept(Transaction[] analysis,String name){
        for (Transaction each:analysis){
            if(each.getReciept().equalsIgnoreCase(name)){
                System.out.println("Amount transfered to "+name+" is "+each.getTransactionAmount());
            }
        }
    }

    //filter the transaction based on the remarks given
    public void filterByRemarks(Transaction[] analysis,String reamrks){
        for(Transaction each:analysis){
            if(each.getRemarks().equalsIgnoreCase(reamrks)){
                System.out.println("Transaction reciepent based on remarks are "+each.getReciept());
            }
        }
    }


    @Override
    public void run() {
        lock.lock();
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        while(true){
            System.out.println(resourceBundle.getString("app.welcome"));
            System.out.println(resourceBundle.getString("app.menu"));
            Scanner scanner=new Scanner(System.in);
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the start date");
                    int startDate=scanner.nextInt();
                    System.out.println("Enter the end date");
                    int endDate=scanner.nextInt();
                    transactionAnalysis.filterByDate(analysis,startDate,endDate);
                    break;
                case 2:transactionAnalysis.filterLeastAmount(analysis);
                    break;
                case 3:transactionAnalysis.filterMaximumAmount(analysis);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("Enter the reciepient name");
                    String reciepientName=scanner.nextLine();
                    transactionAnalysis.filterReciept(analysis,reciepientName);
                    break;
                case 5:
                    System.out.println("Enter the remarks to be filtered");
                    String remarks=scanner.next();
                    transactionAnalysis.filterByRemarks(analysis,remarks);
                    break;
                default:System.exit(0);
            }
            lock.unlock();
        }

    }


}
