package com.project.dao.entities;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.Digits;

public class Accounts {
    @NotNull(message="{account.id.null}")
    private Long accountId;
    @NotNull(message="{account.number.null}")
    @Digits(integer=12,fraction=0,message="{account.number.null}")
    private Long accountNumber;
    @NotNull(message="{customer.id.null}")
    private  Long customerId;
    @NotNull(message="{account.type.null}")
    private String accountType;
    @NotNull(message="{account.status.null}")
    private String accountStatus;
    @NotNull(message="{account.balance.null}")
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
