package org.example;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import static org.example.MyBank.loan;

public class MainLoan {
    public static void main(String[] args){
        MainLoan main=new MainLoan();
        Scanner scanner=new Scanner(System.in);
        Loan[] loans={
                new Loan(1234566667L,10000.0,new Date("12/02/2023"),"open","Suresh"),
                new Loan(7654322344L,45000.0,new Date("25/12/2023"),"closed","Mahesh"),
                new Loan(9876345632L,87000.0,new Date("06/10/2024"),"open","Razi"),
                new Loan(9876523443L,42000.0,new Date("05/03/2023"),"closed","David")
        };

        loan.addAll(Arrays.asList(loans));//add all the loan instances into arraylist object

        MyBank orderByDate = ((startDate,endDate)->{//lambda expression to filter the date based on the range
            for(Loan each:loan){
                if(each.getLoanDate().after(startDate) && each.getLoanDate().before(endDate))
                    System.out.println(each);
                else
                    System.out.println("No Loan in between specified range of date");
            }
        });

        System.out.println("Enter start date in (dd/mm/yyyy) format");
        Date startDate=new Date(scanner.next());
        System.out.println("Enter end date in (dd/mm/yyyy) format");
        Date endDate=new Date(scanner.next());

        orderByDate.filterByDate(startDate,endDate);//call the method for filtering by range of date

    }


}
