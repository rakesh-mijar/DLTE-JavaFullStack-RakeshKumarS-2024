package org.example;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private Double transactionAmount;
    private String reciept;
    private String remarks;

    public Transaction(Date dateOfTransaction, Double transactionAmount, String reciept, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.transactionAmount = transactionAmount;
        this.reciept = reciept;
        this.remarks = remarks;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getReciept() {
        return reciept;
    }

    public void setReciept(String reciept) {
        this.reciept = reciept;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", transactionAmount=" + transactionAmount +
                ", reciept='" + reciept + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
