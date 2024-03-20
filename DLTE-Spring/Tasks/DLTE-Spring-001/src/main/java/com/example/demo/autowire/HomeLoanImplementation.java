package com.example.demo.autowire;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("homeLoan")
public class HomeLoanImplementation implements LoanInterface {

    @Override
    public List<Loan> findLoan() {
        //creating an arraylist and add them if there is match with the loan type to homeloan
            List<Loan> loans=new ArrayList<>();
            for(Loan eachLoan:loanList){
                if(eachLoan.getLoanType().equalsIgnoreCase("homeLoan")){
                    loans.add(eachLoan);
                }
            }
            return loans;
        }
}
