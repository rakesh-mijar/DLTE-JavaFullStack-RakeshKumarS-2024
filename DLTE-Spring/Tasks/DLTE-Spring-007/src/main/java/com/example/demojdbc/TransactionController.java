package com.example.demojdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//handles all the restful services like GET,POST,..
@RestController
@RequestMapping("/transaction/jdbc")
public class TransactionController {

    //injecting the instance of TransactionServices(automatic dependency injection)
    @Autowired
    TransactionService transactionService;


    Logger logger =LoggerFactory.getLogger(TransactionController.class);

    //save the transaction entry into the table
    //http://localhost:8082/transaction/jdbc/new/transac;
    //give the content in the body as json or xml

    @PostMapping("/new/transac")
    public TransactionNew saved(@RequestBody TransactionNew transaction){
        TransactionNew transactionNew=null;
        try{
            transactionNew=transactionService.apiSave(transaction);
        }
        catch (TransactionException transacException){
            logger.warn(transacException.toString());
        }
        return transactionNew;
    }


    //ex:
//    {
//        "transactionId": 125,
//            "transactionDate": "2023-01-17T00:00:00.000+00:00",
//            "transactionBy": "Venkatesh",
//            "transactionTo": "Raksha",
//            "transactionAmount": 37000.0,
//            "transactionRemarks": "Friend"
//    }

    //this is used to get the list of transaction based on the sender name
     //http://localhost:8082/transaction/jdbc/sender/{input the name};
    @GetMapping("/sender/{name}")
    public List<TransactionNew> gettingSender(@PathVariable("name") String name){
        return transactionService.apiFindBySender(name);
    }

    //this is used to get the list of transaction based on the receiver name
    //http://localhost:8082/transaction/jdbc/reciever/{input the name};
    @GetMapping("/receiver/{name}")
    public List<TransactionNew> gettingReciever(@PathVariable("name") String name){
        return transactionService.apiFindByReciever(name);
    }

    //this is used to get the list of transaction based on the amount of transaction
    //http://localhost:8082/transaction/jdbc/amount/{input the amount};
    @GetMapping("/amount/{amount}")
    public List<TransactionNew> gettingAmount(@PathVariable("amount") Double amount){
        return transactionService.apiFindByAmount(amount);
    }

}
