package com.project.dao.entities;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class Accounts {
    private Long accountId;
    @Range(min = 100000000000L, max = 999999999999L,message = "ERR001")
    @NotNull(message="ERR002")
    @Digits(integer=12,fraction=0,message="ERR001")
    private Long accountNumber;
    private  Long customerId;
    @NotNull(message="ERR002")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "ERR003")
    private String accountType;
    @NotNull(message="ERR002")
    @Pattern(regexp = "^(?i)(active|inactive)$", message = "ERR004")
    private String accountStatus;
    @NotNull(message="ERR002")
    @Digits(integer=10,fraction=0,message="ERR005")
    private  Double accountBalance;

    public Accounts(Long accountId, Long accountNumber, Long customerId, String accountType, String accountStatus, Double accountBalance) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.accountType = accountType;
        this.accountStatus = accountStatus;
        this.accountBalance = accountBalance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Accounts() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
