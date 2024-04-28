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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/ui/transactions")
public class MvcController {
    @Autowired
    TransactionService transactionService;


    @GetMapping("/new")
    public String show(Model model){
        TransactionNew transactionNew=new TransactionNew();
        model.addAttribute("transactionNew",new TransactionNew());
        return "newTransaction";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transactionNew",new TransactionNew());
        return "index";
    }
    @RequestMapping(value="/new",method = RequestMethod.POST)
    public String newTransaction(@Valid  @ModelAttribute TransactionNew transactionNew, BindingResult bindingResult, Model model){
        model.addAttribute("transactionNew",transactionNew);
        if(!bindingResult.hasErrors()){
                TransactionNew transaction1= transactionService.apiSave(transactionNew);
                model.addAttribute("status","New Transaction has been inserted");
//                model.addAttribute("transactionNew",transaction1);
                return "index";
            }else{
            model.addAttribute("status","Transaction Failed!!");
            return "newTransaction";
        }
    }

    @GetMapping("/search")
    public String searchTransac(Model model){
        TransactionNew transactionNew=new TransactionNew();
        model.addAttribute("transactionNew",new TransactionNew());
        return "filterBy";
    }

    @GetMapping("/tasks")
    public String search(@RequestParam("filterBy") String filterBy,@RequestParam("search") String searchTerm,Model model){
        List<TransactionNew> transactionNewList=null;
        switch (filterBy){
            case "filterBySender":transactionNewList=transactionService.apiFindBySender(searchTerm);
                                    break;
            case "filterByReceiver":transactionNewList=transactionService.apiFindByReciever(searchTerm);
                                    break;
            case "filterByAmount":transactionNewList=transactionService.apiFindByAmount(Double.parseDouble(searchTerm));
                                    break;
        }
        model.addAttribute("transactions",transactionNewList);
        return "filterBy";
    }

    @GetMapping("/previous")
    public String deleteDisplay(Model model){
        model.addAttribute("transactionNew",new TransactionNew());
        return "delete";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("startDate") String start,@RequestParam("endDate") String end, Model model){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        Date startDate,endDate;

        try {
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/transactions/error";
        }
        String delete=transactionService.deleteTransaction(startDate,endDate);
        model.addAttribute("status",delete);
        return "index";
    }
}
