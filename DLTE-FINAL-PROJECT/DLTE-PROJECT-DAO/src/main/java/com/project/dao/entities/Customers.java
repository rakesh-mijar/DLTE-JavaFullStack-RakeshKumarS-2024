package com.project.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

public class Customers {
    @NotNull(message="{customer.id.null}")
    private Long customerId;
    @NotNull(message = "{customer.name.null}")
    private String customerName;
    @NotNull(message = "{customer.address.null}")
    private String customerAddress;
    @NotNull(message = "{customer.status.null}")
    private String customerStatus;
    @NotNull(message = "{customer.contact.null}")
    @Digits(integer=10,fraction=0,message="{customer.contact.null}")
    private  Long customerContact;
    @NotNull(message = "{customer.username.null")
    private  String username;
    @NotNull(message = "{customer.pass.null")
    private  String password;

    public Customers(Long customerId, String customerName, String customerAddress, String customerStatus, Long customerContact, String username, String password) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Customers() {
    }
}
