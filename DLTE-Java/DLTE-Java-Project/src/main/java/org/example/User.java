package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userMailId;
    private Long contactInfo;
    private Double initialBalance;

    public User(String userName, String userPassword, String userAddress, String userMailId,    long contactInfo, Double initialBalance) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userMailId = userMailId;
        this.contactInfo = contactInfo;
        this.initialBalance = initialBalance;
    }

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserMailId() {
        return userMailId;
    }

    public void setUserMailId(String userMailId) {
        this.userMailId = userMailId;
    }

    public  long getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(Long contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    @Override
    public String toString() {
        return "user{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userMailId='" + userMailId + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", initialBalance='" + initialBalance + '\'' +
                '}';
    }
}
