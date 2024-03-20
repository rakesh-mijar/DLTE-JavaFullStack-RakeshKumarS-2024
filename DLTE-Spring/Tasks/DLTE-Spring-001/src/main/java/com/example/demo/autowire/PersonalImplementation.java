package com.example.demo.autowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("personalLoan")
public class PersonalImplementation implements LoanInterface {
    @Override
    public List<Loan> findLoan() {
        List<Loan> personalLoans = new ArrayList<>();
        for (Loan eachLoan:loanList){
            if(eachLoan.getLoanType().equalsIgnoreCase("personalLoan")){
                personalLoans.add(eachLoan);
            }
        }
        return personalLoans;
    }
}
