package org.example;

import java.util.ArrayList;
import java.util.Date;

public interface MyBank {
    ArrayList<Loan> loan=new ArrayList<>();//arraylist of loan
    void filterByDate(Date startDate, Date endDate);//method to filter the loan details based on the range of date given by user
}
