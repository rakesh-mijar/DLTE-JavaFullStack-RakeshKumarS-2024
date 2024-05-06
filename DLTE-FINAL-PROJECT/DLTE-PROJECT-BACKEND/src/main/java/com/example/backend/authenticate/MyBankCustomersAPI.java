package com.example.backend.authenticate;

import com.project.dao.entities.MyBankCustomers;

import com.project.dao.security.MyBankCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class MyBankCustomersAPI {
    @Autowired
    MyBankCustomersService customersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankCustomers save(@Valid @RequestBody MyBankCustomers myBankCustomers){
        myBankCustomers.setPassword(passwordEncoder.encode(myBankCustomers.getPassword()));
        return customersService.signingUp(myBankCustomers);
    }
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
