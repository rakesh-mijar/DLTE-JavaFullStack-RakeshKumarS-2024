package org.example;

public class Activity {
    public void checkEligible(String myName, int score){
        if(score>=750){
            System.out.println(myName+" is eligible for loan");
        }else
            System.out.println(myName+" is  not eligible");
    }
    public static void main(String[] args) {
            Activity refer=new Activity();
        // method reference
        Loan loan=refer::checkEligible;//need not implement the interface just refer to it
        loan.applyPersonalLoan("Razak MOhmed S",570);
    }
}

interface Loan{
    void applyPersonalLoan(String name, int cibil);
}