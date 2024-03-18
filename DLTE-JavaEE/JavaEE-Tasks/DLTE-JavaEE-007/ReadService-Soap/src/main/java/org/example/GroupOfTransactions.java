package org.example;

import java.util.List;

public class GroupOfTransactions {
    List<Transaction> transactions;

    public GroupOfTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public GroupOfTransactions() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "GroupOfTransactions{" +
                "transactions=" + transactions +
                '}';
    }
}
