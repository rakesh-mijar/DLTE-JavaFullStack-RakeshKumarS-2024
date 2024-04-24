//package com.example.demojdbc.authenticate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/profile")
//public class MyBankUsersAPI {
//    @Autowired
//    MyBankUserServices myBankUserServices;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    //method corresponds to add the user details into the table with encryprion of password
//    @PostMapping("/register")
//    public MyBankUsers save(@RequestBody MyBankUsers myBankUsers){
//        myBankUsers.setPassword(passwordEncoder.encode(myBankUsers.getPassword()));
//        return myBankUserServices.signingUp(myBankUsers);
//    }
//    //method correponds to get the user details based on the specified username
//    @GetMapping("/users/{username}")
//    public MyBankUsers getUserByUsername(@PathVariable String username) {
//        return myBankUserServices.findByUsername(username);
//    }
//}
