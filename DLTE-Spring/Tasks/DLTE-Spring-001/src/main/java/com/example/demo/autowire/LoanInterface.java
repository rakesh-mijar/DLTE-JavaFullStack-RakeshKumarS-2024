package com.example.demo.autowire;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface LoanInterface {

    //Loan of objects
    List<Loan> loanList= Stream.of(
            new Loan(1234566667L,10000.0,new Date("12/02/2023"),"HomeLoan","Suresh"),
            new Loan(7654322344L,45000.0,new Date("25/12/2023"),"PersonalLoan","Mahesh"),
            new Loan(9876345632L,87000.0,new Date("06/10/2024"),"HomeLoan","Razi"),
            new Loan(9876523443L,42000.0,new Date("05/03/2023"),"PersonalLoan","David")
    ).collect(Collectors.toList());

    //abstract method to find the loan based on the loan type..returns list of loan
    List<Loan> findLoan();

}

