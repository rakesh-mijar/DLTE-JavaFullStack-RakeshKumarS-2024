package com.example.demo.services;

import com.example.demo.model.UserAccount;
import com.example.demo.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService { //intermediate between controller and repository

    @Autowired
    AccountRepository accountRepository;

    public UserAccount callSave(UserAccount userAccount){
        return accountRepository.save(userAccount);
    }

//    public UserAccount callUpdate(UserAccount userAccount){
//        return accountRepository.updateAccount(userAccount);
//    }

    public List<UserAccount> callFindAll(){
        return  accountRepository.findAll();
    }
}
