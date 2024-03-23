package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    //entity class with attributes their name can be changed in the column using column annotation as used in previous example
    @Id
    private Long transactionId;
    private String username;
    private Date dateOfTransaction;
    private Long transactionAmount;
    private String transactionType;
    private String remarks;

    public Transaction(Long transactionId, String username, Date dateOfTransaction, Long transactionAmount, String transactionType, String remarks) {
        this.transactionId = transactionId;
        this.username = username;
        this.dateOfTransaction = dateOfTransaction;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.remarks = remarks;
    }

    public Transaction() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
