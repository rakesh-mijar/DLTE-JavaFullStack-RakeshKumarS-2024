package com.project.dao.entities;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

public class Accounts {
    @NotNull(message="{account.id.null}")
    @Digits(message = "{account.id.digits}", integer = 10, fraction =0 )
    private Long accountId;
    @Range(min = 100000000000L, max = 999999999999L,message = "{account.number.limit}")
    @NotNull(message="{account.number.null}")
    @Digits(integer=12,fraction=0,message="{account.number.limit}")
    private Long accountNumber;
    @NotNull(message="{customer.id.null}")
    @Digits(message = "{customer.id.digits}", integer = 10, fraction =0 )
    private  Long customerId;
    @NotNull(message="{account.type.null}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{account.type.invalid}")
    private String accountType;
    @NotNull(message="{account.status.null}")
    @NotBlank(message = "{account.status.null}")
    @Pattern(regexp = "^(?i)(active|inactive)$", message = "{account.status.invalid}")
    private String accountStatus;
    @NotNull(message="{account.balance.null}")
    @Digits(integer=10,fraction=0,message="{account.balance.limit}")
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
