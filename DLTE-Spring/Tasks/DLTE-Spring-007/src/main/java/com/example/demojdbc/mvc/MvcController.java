package com.example.demojdbc.mvc;

import com.example.demojdbc.TransactionException;
import com.example.demojdbc.TransactionNew;
import com.example.demojdbc.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/ui")
public class MvcController {
    @Autowired
    TransactionService transactionService;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(MvcController.class);

    @GetMapping("/newTransaction")
    public String submit(Model model){
        TransactionNew transactionNew=new TransactionNew();
        model.addAttribute("transactionNew",new TransactionNew());
        return "newTransaction.html";
    }
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    @RequestMapping(value="/submit",method = RequestMethod.POST)
    public String newTransaction(TransactionNew transactionNew, BindingResult bindingResult,Model model){
        try{
            if(!bindingResult.hasErrors()){
                transactionNew = transactionService.apiSave(transactionNew);
                model.addAttribute("status",transactionNew.getTransactionId()+" has inserted");
            }
        }catch(TransactionException transactionException){
            logger.warn(transactionException.toString());
            model.addAttribute("error",transactionException.toString());
        }
        return "newTransaction.html";
    }

}
