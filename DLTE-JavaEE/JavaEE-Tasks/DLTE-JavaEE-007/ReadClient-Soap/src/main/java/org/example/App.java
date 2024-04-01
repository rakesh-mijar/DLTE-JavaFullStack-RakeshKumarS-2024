package org.example;

import java.util.List;

public class App {
    public static void main( String[] args )
    {
        UserSoapService userSoapService = new UserSoapService();
        UserSoap soap = userSoapService.getUserSoapPort();
        String username = "ram@123";
        List<Transaction> transactions = soap.readAllByUsername(username).getTransactions();
        for (Transaction each: transactions) {
            System.out.println(each.getTransactionDoneBy()+" "+each.getTransactionType()+" "+each.getTransactionAmount());
        }
    }
}
