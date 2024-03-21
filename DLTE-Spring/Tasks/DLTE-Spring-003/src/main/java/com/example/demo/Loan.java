package com.example.demo;


import java.util.Date;

public class Loan {
    private long loanNumber;
    private double loanAmount;
    private Date loanDate;
    private String loanType;
    private String borrowerName;

    public Loan(long loanNumber, double loanAmount, Date loanDate, String loanType, String borrowerName) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanType = loanType;
        this.borrowerName = borrowerName;
    }

    public long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate=" + loanDate +
                ", loanType='" + loanType + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}

