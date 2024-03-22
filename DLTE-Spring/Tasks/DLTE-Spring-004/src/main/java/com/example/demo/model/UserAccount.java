package com.example.demo.model;

import org.hibernate.annotations.GeneratorType;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_account")
public class UserAccount implements Serializable {
    //by default takes the name of the properties but can be changed using Column property
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)//,generator = "rakesh")
   //@Column(name="account_number")
    private Long accountNumber;
    private String username;
    private String password;
    private String accounttype;
   // @Column(name="mobile_number")
    private Long mobileNumber;

    public UserAccount(String username, String password, String accounttype, Long accountNumber, Long mobileNumber) {
        this.username = username;
        this.password = password;
        this.accounttype = accounttype;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
    }

    public UserAccount() {
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accounttype='" + accounttype + '\'' +
                ", accountNumber=" + accountNumber +
                ", mobileNumber=" + mobileNumber +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
