package store.oops;

import java.util.Date;
import java.util.Scanner;

public class transactionAnalysis {
    public static void main(String[] args){
        transactionClass[] transaction={
                new transactionClass(new Date(2023,5,29),230000,"Sam","Education"),
                new transactionClass(new Date(2024,7,12),120000,"Razi","Emergency"),
                new transactionClass(new Date(2023,2,17),150000,"Prashanth","Bills"),
                new transactionClass(new Date(2022,4,10),170000,"David","Health")

        };
        Scanner scanner=new Scanner(System.in);
        transactionAnalysis analysis = new transactionAnalysis();

        System.out.println("---Transaction within the range-----"); //print the transaction done within the specified range
        System.out.println("Enter the start day");
        int startDay=scanner.nextInt();
        System.out.println("Enter the end date");
        int endDay=scanner.nextInt();
        analysis.transactionWithinRange(transaction,startDay,endDay);

//        System.out.println("-----Lowest Transaction performed-------");//print the minimum transaction
//        analysis.leastTransaction(transaction);

        System.out.println("-----Highest Transaction Performed------");
        analysis.maxTransaction(transaction);

        System.out.println("---------Filter based on remarks---------");
        System.out.println("enter the remarks(Family, Education, Emergency, Bills, Friend)");
        String remarks=scanner.nextLine();
        analysis.remarksTransaction(transaction,remarks);

        System.out.println("------count of Transaction to the benificiary-----");
        System.out.println("Enter the beneficiary name");
        String benificiaryName=scanner.nextLine();
        analysis.beneficiaryTransaction(transaction,benificiaryName);

        System.out.println("-----------sorting of benifiaries--------");
        analysis.sortingBeneficiary(transaction);

        System.out.println("-----------sorting of transaction amount--------");
        analysis.sortingAmount(transaction);

    }
    public void transactionWithinRange(transactionClass[] transaction,int start,int end){   //print the transaction done within the specified range
        for (transactionClass each:transaction){
            if(each.getDateOfTransaction().getDay()>=start && each.getDateOfTransaction().getDay()<=end){
                System.out.println("Transaction's within the range "+"\n"+each.getDateOfTransaction()+ " "+each.getReciept()+"\n");
            }
        }
    }

    public void leastTransaction(transactionClass[] transaction )//print the minimum transaction
    {
        int min=transaction[0].getTransactionAmount();
        for(int var=1;var<transaction.length;var++){
            if(transaction[var].getTransactionAmount() <= min){
                min=transaction[var].getTransactionAmount();
            }
        }
        System.out.println("Minimum transaction performed is "+min+" by "+transaction[min].getReciept());
    }

    public void maxTransaction(transactionClass[] transaction){//for obtaining the maximum transaction
        int max=transaction[0].getTransactionAmount();
        for (int var=1;var<transaction.length;var++){
            if(transaction[var].getTransactionAmount()>=max){
                max=transaction[var].getTransactionAmount();
            }
        }
        System.out.println("Maximum transaction performed is "+max+" by "+transaction[max].getReciept());
    }

    public void remarksTransaction(transactionClass[] transaction,String remarks){ //filter based on the remarks
        for(transactionClass each:transaction){
            if(each.getRemarks().equalsIgnoreCase(remarks)){
                System.out.println("The remarks are "+each.getRemarks());
            }
        }
    }

    public void beneficiaryTransaction(transactionClass[] transaction,String benificiaryName){ //number of transaction made to particular beneficiary
        int count=0;
        for (transactionClass each:transaction) {
            if (each.getReciept().equalsIgnoreCase(benificiaryName)) {
                count++;
            }
            System.out.println("Total transactions with " + benificiaryName + " is " + count);
        }
    }

    public void sortingBeneficiary(transactionClass[] transaction){//sorting baesd on benificiary name
        transactionClass holdsName=null;
        for(int index=0;index<transaction.length-1;index++){
            for(int nextIndex=index+1;nextIndex<transaction.length;nextIndex++) {
                if (transaction[index].getReciept().compareTo(transaction[nextIndex].getReciept()) < 0) {
                    holdsName = transaction[index];
                    transaction[index] = transaction[nextIndex];
                    transaction[nextIndex] = holdsName;
                }
            }
        }
        for(transactionClass each:transaction){
            System.out.println(each+"\n");
        }
    }

    public void sortingAmount(transactionClass[] transaction){//sorting based on transaction amount
        transactionClass holdsAmount=null;
        for(int index=0;index<transaction.length-1;index++){
            for(int nextIndex=index+1;nextIndex<transaction.length;nextIndex++) {
                if (transaction[index].getTransactionAmount() > (transaction[nextIndex].getTransactionAmount())) {
                    holdsAmount = transaction[index];
                    transaction[index] = transaction[nextIndex];
                    transaction[nextIndex] = holdsAmount;
                }
            }
        }
        for(transactionClass each:transaction){
            System.out.println(each+"\n");
        }
    }
}
