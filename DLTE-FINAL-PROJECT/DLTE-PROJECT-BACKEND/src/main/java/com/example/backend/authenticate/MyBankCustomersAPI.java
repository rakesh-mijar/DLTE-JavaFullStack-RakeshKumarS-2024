package com.example.backend.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MyBankCustomersAPI {
    @Autowired
    MyBankCustomersService customersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankCustomers save(@RequestBody MyBankCustomers myBankCustomers){
        myBankCustomers.setPassword(passwordEncoder.encode(myBankCustomers.getPassword()));
        return customersService.signingUp(myBankCustomers);
    }
}
