package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class TransactionAnalysis {
    public static void main(String[] args){
        List<Transaction> transactionArrayList=new ArrayList<>();
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
        Transaction analysis[]={
                new Transaction(new Date(2024,3,12),1000.0,"Razi","Education"),
                new Transaction(new Date(2023,5,15),2000.0,"Pranav","Health"),
                new Transaction(new Date(2022,8,29),3000.0,"Venkatesh","Friend"),
                new Transaction(new Date(2025,9,15),4000.0,"Nazir","Friend")
        };

        //add all the transaction instances into the arraylist
        transactionArrayList.addAll(Arrays.asList(analysis));

        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        while(true){
            System.out.println(resourceBundle.getString("app.welcome"));
            System.out.println(resourceBundle.getString("app.menu"));
            Scanner scanner=new Scanner(System.in);
            int choice=scanner.nextInt();
            switch (choice){
                //filter based on the range of date
                case 1:
                    System.out.println("Enter the start date");
                    int startDate=scanner.nextInt();
                    System.out.println("Enter the end date");
                    int endDate=scanner.nextInt();
                    transactionAnalysis.filterByDate(transactionArrayList,startDate,endDate);
                    break;

                    //filter the least amount in transaction
                case 2:transactionAnalysis.filterLeastAmount(transactionArrayList);
                    break;

                    //filter the maximum amount in transaction
                case 3:transactionAnalysis.filterMaximumAmount(transactionArrayList);
                    break;

                    //sorting based on the property specified and also the order specified
                case 4:
                    System.out.println("enter the property to be sorted(date,amount,sento,remarks)");
                    String input=scanner.next();
                    System.out.println("Enter the order of sorting(ascending,descending)");
                    String inputOrder = scanner.next();
                    Comparator<Transaction> comparator=null;
                    switch (input.toLowerCase()){
                        case "date":comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                        break;
                        case "amount":comparator=Comparator.comparing(Transaction::getTransactionAmount);
                        break;
                        case "sento":comparator=Comparator.comparing(Transaction::getReciept);
                        break;
                        case "remarks":comparator=Comparator.comparing(Transaction::getRemarks);
                        break;
                        default:
                            System.out.println("Invalid property!!!");
                            break;
                    }
                    if(inputOrder.equalsIgnoreCase("descending")) {
                        comparator=comparator.reversed();
                    }
                    List<Transaction> transaction=transactionArrayList.stream().sorted(comparator).collect(Collectors.toList());
                    System.out.println(transaction);
                default:System.exit(0);
            }
        }

    }
    public void filterByDate(List<Transaction> transactionArrayList, int startDate, int endDate){
        List<Transaction> transactions=transactionArrayList.stream().filter(transaction->transaction.getDateOfTransaction().getDate()>=startDate && transaction.getDateOfTransaction().getDate()<=endDate).collect(Collectors.toList());
        System.out.println(transactions);;
    }
    public void filterLeastAmount(List<Transaction> transactionArrayList){
        Transaction minimumTransac = transactionArrayList.stream().min(Comparator.comparingDouble(Transaction::getTransactionAmount)).orElse(null);
        System.out.println(minimumTransac);
    }
    public void filterMaximumAmount(List<Transaction> transactionArrayList){
        Transaction maximumTransac=transactionArrayList.stream().max(Comparator.comparingDouble(Transaction::getTransactionAmount)).orElse(null);
        System.out.println(maximumTransac);
    }

}
