package com.example.demo.controller;

import com.example.demo.model.UserAccount;
import com.example.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//controller that handles the rest services
@RestController
@RequestMapping("/account") //specifies path of requesting
public class AccountController {
    @Autowired  //field injection: it injects an instance of AccountService into the controller.
    AccountService accountService;

    @PostMapping("/post")
    public UserAccount apiSave(@RequestBody UserAccount userAccount){
        return accountService.callSave(userAccount);
    }

    @PutMapping(value="/",consumes="application/json")
    public UserAccount apiUpdate(@RequestBody UserAccount userAccount)
    {
        return accountService.callSave(userAccount);
    }

    @GetMapping("/gett")
    public List<UserAccount> apiAll(){
        return accountService.callFindAll();
    }
}
