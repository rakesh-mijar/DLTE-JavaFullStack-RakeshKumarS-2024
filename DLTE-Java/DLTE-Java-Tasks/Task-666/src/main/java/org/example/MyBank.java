package org.example;

import java.io.IOException;
import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> loan=new ArrayList<>();//ArrayList of Loan objects
    void addNewLoan() throws IOException, ClassNotFoundException;
    void checkAvailableLoans();
    void checkClosedLoans();
}
