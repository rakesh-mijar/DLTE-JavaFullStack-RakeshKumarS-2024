package org.example;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    private UserServices userServices;

    private UserSoap soapService;

    @Before
    public void setingUp(){
        soapService=new UserSoap();
        soapService.userServices=userServices;
    }

    @Test
    //test method for account creation
    public void testApproval(){
        User user1=new User("ramesh","ram@321","Mangalore","ram@gmail.com",9876543234L,1000.0);
        User user2=new User("muzain","muz@123","karkala","muz@gmail.com",897856789L,2000.0);
        doNothing().when(userServices).callSave(user1);
        soapService.createAccount(user1);

        //test case failed because mismatch with user parameters been passed
        verify(userServices,times(1)).callSave(user2);

        //test case passed because same user parameters been passed
        verify(userServices,times(1)).callSave(user1);
    }

    @Test
    //testing the readbyid() to obtain the user details based on the username as id
    public void testReadById(){
        String user="razi";
        User user1=new User("ramesh","ram@321","Mangalore","ram@gmail.com",9876543234L,1000.0);
        User user2=new User("muzain","muz@123","karkala","muz@gmail.com",897856789L,2000.0);
        User user3=new User("razi","razi@456","Udupi","razi@gmail.com",8978654564L,3000.0);
        User user4=new User("pranava","prana@123","karkala","prana@gmail.com",897856789L,4000.0);

        //whenever the mentioned user is called then return the expected user i.e user3
        when(userServices.callFindById(user)).thenReturn(user3);

        //call the method findById()
        User usersGroup=soapService.readById(user);

        //test case passed because expected and the actual user is same..i.e razi
        assertEquals(user3,usersGroup);

        //test case failed because expected and the actual user is not same..
        assertNotEquals(user,usersGroup);
    }

    @Test
    //testing the findByTransacId()...i.e to obtain the transaction details based on the transaction done by as id
    public void testReadByTransacId(){
        String user="Suresh";
        Transaction transac1=new Transaction("Mahesh","Health",12000.0,new Date(2024,4,24));
        Transaction transac2=new Transaction("Suresh","Business",15000.0,new Date(2023,2,12));

        //return type is list of transactions
        List<Transaction> expectedList1=Stream.of(transac2,transac1).collect(Collectors.toList());

        when(userServices.callFindByUsername(user)).thenReturn(expectedList1);

        TransactionGroup transaction=soapService.readAllById(user);

        // test case failed because it expected null value but returned the details of Suresh transaction
        assertNull(transaction);

        //test case passed because it is expecting a value and also it recieves the same
        assertNotNull(transaction);
    }


}
