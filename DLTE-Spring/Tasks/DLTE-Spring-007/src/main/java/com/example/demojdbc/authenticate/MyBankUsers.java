//package com.example.demojdbc.authenticate;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
////user entity class with implementation of userdetails and hence it's methods are implemented here
//public class MyBankUsers implements UserDetails {
//    private String name;
//    private String username;
//    private String password;
//    private String email;
//    private Long contact;
//    private Long aadhar;
//
//    public MyBankUsers() {
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public MyBankUsers(String name, String username, String password, String email, Long contact, Long aadhar) {
//        this.name = name;
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.contact = contact;
//        this.aadhar = aadhar;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Long getContact() {
//        return contact;
//    }
//
//    public void setContact(Long contact) {
//        this.contact = contact;
//    }
//
//    public Long getAadhar() {
//        return aadhar;
//    }
//
//    public void setAadhar(Long aadhar) {
//        this.aadhar = aadhar;
//    }
//
//    @Override
//    public String toString() {
//        return "MyBankUsers{" +
//                "name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", contact=" + contact +
//                ", aadhar=" + aadhar +
//                '}';
//    }
//}
