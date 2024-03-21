package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/collection")
public class ControllerTask {
    //list of loan objects.
    List<Loan> loanList= Stream.of(
            new Loan(1234566667L,10000.0,new Date("12/02/2023"),"HomeLoan","Suresh"),
            new Loan(7654322344L,45000.0,new Date("25/12/2023"),"PersonalLoan","Mahesh"),
            new Loan(9876345632L,87000.0,new Date("06/10/2024"),"HomeLoan","Razi"),
            new Loan(987652344*3L,42000.0,new Date("05/03/2023"),"PersonalLoan","David")
    ).collect(Collectors.toList());

    //GetMapping: to retrieve all the loan list items
    @GetMapping("/")
    public List<Loan> readEverything(){
        return loanList;
    }

    //GetMapping: index as pathvariable and display loan in that specified index
    @GetMapping("/{index}")
    public Loan readByIndex(@PathVariable("index") int index){
        return loanList.get(index);
    }

    //PostMapping: new loan into the List
    @PostMapping("/")
    public String createNewLoan(@RequestBody Loan object){
        loanList.add(object);
        return object+" has added";
    }
}
