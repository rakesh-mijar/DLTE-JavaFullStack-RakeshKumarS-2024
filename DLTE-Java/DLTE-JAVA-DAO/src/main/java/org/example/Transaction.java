package org.example;


//import java.sql.Date;
//
//public class Transaction {
//    private String transactionDoneBy;
//    private String transactionType;
//    private Double transactionAmount;
//    private Date transactionDate;
//
//    public Transaction() {
//    }
//
//    public String getTransactionDoneBy() {
//        return transactionDoneBy;
//    }
//
//    public void setTransactionDoneBy(String transactionDoneBy) {
//        this.transactionDoneBy = transactionDoneBy;
//    }
//
//    public String getTransactionType() {
//        return transactionType;
//    }
//
//    public void setTransactionType(String transactionType) {
//        this.transactionType = transactionType;
//    }
//
//    public Double getTransactionAmount() {
//        return transactionAmount;
//    }
//
//    public void setTransactionAmount(Double transactionAmount) {
//        this.transactionAmount = transactionAmount;
//    }
//
//    public Date getTransactionDate() {
//        return transactionDate;
//    }
//
//    public void setTransactionDate(Date transactionDate) {
//        this.transactionDate = transactionDate;
//    }
//
//    public Transaction(String transactionDoneBy, String transactionType, Double transactionAmount, Date transactionDate) {
//        this.transactionDoneBy = transactionDoneBy;
//        this.transactionType = transactionType;
//        this.transactionAmount = transactionAmount;
//        this.transactionDate = transactionDate;
//    }
//
//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "transactionDoneBy='" + transactionDoneBy + '\'' +
//                ", transactionType='" + transactionType + '\'' +
//                ", transactionAmount=" + transactionAmount +
//                ", transactionDate=" + transactionDate +
//                '}';
//    }
//}



import java.sql.Date;

public class Transaction {
    private String transactionDoneBy;
    private String transactionType;
    private Double transactionAmount;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(String transactionDoneBy, String transactionType, Double transactionAmount, Date transactionDate) {
        this.transactionDoneBy = transactionDoneBy;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionDoneBy() {
        return transactionDoneBy;
    }

    public void setTransactionDoneBy(String transactionDoneBy) {
        this.transactionDoneBy = transactionDoneBy;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDoneBy='" + transactionDoneBy + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
