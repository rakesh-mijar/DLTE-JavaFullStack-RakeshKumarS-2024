package com.project.dao.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;


public class MyBankCustomers implements UserDetails {
    private Long customerId;
    @NotNull(message = "ERR-001")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "name.invalid")
    private String customerName;
    @NotNull(message = "ERR-001")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "address.invalid")
    private String customerAddress;
    @NotNull(message = "ERR-001")
    @Pattern(regexp = "^(?i)(Active|Inactive)$", message = "customer.status.invalid")
    private String customerStatus;
    @NotNull(message = "ERR-001")
    @Digits(integer = 10, fraction = 0, message = "digits.customerContact")
    private Long customerContact;
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "username.invalid")
    private String username;
    @NotNull(message = "ERR-001")
    @Size(min = 8, message = "password.length.invalid")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "password.invalid")
    private String password;
    private final int maxAttempt=3;
    private Integer attempts;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "MyBankCustomers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxAttempt=" + maxAttempt +
                ", attempts=" + attempts +
                '}';
    }
}

