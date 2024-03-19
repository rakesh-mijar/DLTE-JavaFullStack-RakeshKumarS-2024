package org.example;

import java.util.List;

public class TransactionGroup {
    //list of transaction as an attribute with its getter, setter and toString() methods
    List<Transaction> transactions;

    public TransactionGroup(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public TransactionGroup() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TransactionGroup{" +
                "transactions=" + transactions +
                '}';
    }
}
