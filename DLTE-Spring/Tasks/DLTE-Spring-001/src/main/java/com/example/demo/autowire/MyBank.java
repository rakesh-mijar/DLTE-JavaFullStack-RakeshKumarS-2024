package com.example.demo.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

//This annotation is used to indicate that the MyBank class is a service component in the Spring application context. It allows Spring to automatically detect and register MyBank as a bean.
@Service
public class MyBank {

    @Autowired
    @Qualifier("homeLoan")//when multiple beans are present to specify uniquely Qualifier is used.
    private LoanInterface loanInterface;

//    @Qualifier("homeLoan")
//    private LoanInterface loanInterface2;

    public List<Loan> callFindLoans(){
        return loanInterface.findLoan();
    }

}
