package org.example;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @BeforeClass
    public static void setUpBeforeClass(){//method will be invoked only once, before starting all the tests.
        System.out.println("Setting up!!");
        LoanMain loanMain=new LoanMain();
    }

    @Before
    public  void setUp(){//method will be invoked before each test
        LoanMain loanMain=new LoanMain();
    }

    @Test
    public void testAddNewLoan() throws IOException, ClassNotFoundException {
        LoanMain loanMain=new LoanMain();

        Loan loan =new Loan(122345L,1000,"2024/03/04","open","Kabir");//example for testing

        loanMain.loan.add(loan);//add the instance into arraylist

        assertEquals(122345L,loanMain.loan.get(0).getLoanNumber()); //Test passed because both expected and actual valus are same
        assertEquals("closed",loanMain.loan.get(0).getLoanStatus());//Test failed because actual loan status is open....check only closed loans
        assertEquals("open",loanMain.loan.get(0).getLoanStatus());//check available loans
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)   //Test failed because no exception found here
    public void testExecution() throws IOException, ClassNotFoundException {
        testAddNewLoan();
    }

    @Test(timeout = 100)//to check whether it completes within the timeout session specified
    public void testAddNewLoan2() throws IOException, ClassNotFoundException {
        LoanMain loanMain = new LoanMain();
        Loan loan = new Loan(122345L, 1000, "2024/03/04", "open", "Kabir");
        loanMain.loan.add(loan);

        assertTrue(loanMain.loan.contains(loan));//check whether the list contains the added instance
    }

    @Test
    public void testNullOrNot(){
        LoanMain loanMain=new LoanMain();
        assertNull(loanMain.loan);//Test fails because it it is not null
        assertNotNull(loanMain.loan);//Test pass because it is not null
    }

}
