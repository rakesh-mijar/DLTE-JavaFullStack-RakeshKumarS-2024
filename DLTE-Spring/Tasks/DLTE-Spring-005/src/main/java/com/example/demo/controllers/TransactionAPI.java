package com.example.demo.controllers;

import com.example.demo.model.Transaction;
import com.example.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionAPI {
    
    //injecting the instance of tranaction services to call the methods defined
    @Autowired
    TransactionService transactionService;

    //New Transaction of POST mapping as XML request
    //Request body corresponds to send the value to be inserted in the table
    //input is given as xml format

    @PostMapping(value = "/",consumes="application/xml")
    public Transaction apiCreate(@RequestBody Transaction transaction){
        return transactionService.callSave(transaction);
    }

//    <transaction>
//   <transactionId>101</transactionId>
//   <transactionAmount>25000</transactionAmount>
//   <dateOfTransaction>2024-09-03</dateOfTransaction>
//   <remarks>Friend</remarks>
//   <transactionType>withdraw</transactionType>
//   <username>Pranav</username>
//   </transaction>


    //findAllByUserAndType   GetMapping using Native SQL query
    //we specifiy the name of user and type as pathvaribale to be retrieved
    @GetMapping("/findUserAndType/{user}/{type}")
    public List<Transaction> apiUserAndType(@PathVariable("user") String user,@PathVariable("type") String type){
        return transactionService.callFindByUserAndType(user,type);
    }


    //findAllByRangeOfTransactionAmount by using HQL
    //we specify the two ranges of amount as pathvaribale in the url...action performed is GET.

    @GetMapping("/findByRange/{range1}/{range2}")
    public List<Transaction> apiRangeAmount(@PathVariable("range1") Long range1,@PathVariable("range2") Long range2){
        return transactionService.callFindByRange(range1,range2);
    }

}
