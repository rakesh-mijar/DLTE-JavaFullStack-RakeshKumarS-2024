//package com.example.demo;
//
//import com.example.demo.configs.SoapPhase;
//import com.example.demo.dao.Transactions;
//import com.example.demo.dao.TransactionsService;
//import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import services.transactions.*;
//
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class ApplicationTests {
//
//    @MockBean
//    private TransactionsService transactionsService;
//    @InjectMocks
//    private SoapPhase soapPhase;
//
//    @Test
//    public void testNewTransactions(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        when(transactionsService.publishNewTransactions(any(Transactions.class))).thenReturn(transaction2);
//
//        NewTransactionRequest newTransactionRequest=new NewTransactionRequest();
//        services.transactions.Transactions transactions=new services.transactions.Transactions();
//        transactions.setTransactionId(1235455L);
//        transactions.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,07,21,0));
//        transactions.setTransactionBy("Raksha");
//        transactions.setTransactionTo("Pranav");
//        transactions.setTransactionAmount(1000.0);
//        transactions.setTransactionRemarks("Medicine");
//        newTransactionRequest.setTransactions(transactions);
//
//        NewTransactionResponse newTransactionResponse=soapPhase.addNewLoan(newTransactionRequest);
//
//        //test case failed because there is a response(transaction2) and not nul
//       // assertNull(newTransactionResponse.getTransactions());
//
//        //test case passed because the expected id and the actual id provided are same
//        //assertEquals(1235455L,newTransactionResponse.getTransactions().getTransactionId());
//
//        //test case failed because actual response status is SUCCESS but here expected FAILURE
//        assertEquals("FAILURE",newTransactionResponse.getServiceStatus());
//    }
//
//    @Test
//    public void  testFindBySender(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        List<Transactions> expectedTransactions= Stream.of(transaction3,transaction1).collect(Collectors.toList());
//
//        when(transactionsService.findBySender(anyString())).thenReturn(expectedTransactions);
//
//        FilterBySenderRequest senderRequest=new FilterBySenderRequest();
//        senderRequest.setSender("Raksha");
//        FilterBySenderResponse senderResponse=soapPhase.filterSender(senderRequest);
//
//        //test case failed beacuse expected value is Surendra but actual value is Razi (index position 0 is transaction 3)
//        assertEquals(transaction1.getTransactionTo(),senderResponse.getTransactions().get(0).getTransactionTo());
//        //System.out.println(transaction1.getTransactionTo());
//        //System.out.println(senderResponse.getTransactions().get(0).getTransactionTo());
//
//        //test case passed beacuse expected value is Surendra and actual value is Surendra (index position 1 is transaction 1)
//        assertEquals(transaction1.getTransactionTo(),senderResponse.getTransactions().get(1).getTransactionTo());
//        //System.out.println(transaction1.getTransactionTo());
//        //System.out.println(senderResponse.getTransactions().get(0).getTransactionTo());
//
//    }
//
//    @Test
//    public void  testFindByReceiver(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        List<Transactions> expectedTransactions= Stream.of(transaction2,transaction3).collect(Collectors.toList());
//
//        when(transactionsService.findByReceiver(anyString())).thenReturn(expectedTransactions);
//
//        FilterByReceiverRequest receiverRequest=new FilterByReceiverRequest();
//        receiverRequest.setReceiver("Pranav");
//        FilterByReceiverResponse receiverResponse=soapPhase.filterReceiver(receiverRequest);
//
//        //test case passed beacuse condition is satisfied Mohan is not equal to  Raksha (index position 0 is transaction 2)
//        assertTrue(transaction1.getTransactionBy()!=receiverResponse.getTransactions().get(0).getTransactionBy());
//        //System.out.println(transaction1.getTransactionBy());
//        //System.out.println(receiverResponse.getTransactions().get(0).getTransactionBy());
//
//        //test case failed beacuse the condition is said to be equal but they are not equal Mohan is not equal Raksha (index position 0 is transaction 2)
//        assertTrue(transaction1.getTransactionBy()==receiverResponse.getTransactions().get(0).getTransactionBy());
//        System.out.println(transaction1.getTransactionBy());
//        System.out.println(receiverResponse.getTransactions().get(0).getTransactionBy());
//
//    }
//
//    @Test
//    public void  testFindByAmount(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        List<Transactions> expectedTransactions= Stream.of(transaction2,transaction1).collect(Collectors.toList());
//
//        when(transactionsService.findByAmount(anyDouble())).thenReturn(expectedTransactions);
//
//        FilterByAmountRequest amountRequest=new FilterByAmountRequest();
//        amountRequest.setAmount(12000.0);
//        FilterByAmountResponse amountResponse=soapPhase.filterAmount(amountRequest);
//
//        //test case passed beacuse the actual value and the expected values are same (i.e both corresponds to transaction1 -Mohan)
//        assertEquals(transaction1.getTransactionBy(),amountResponse.getTransactions().get(1).getTransactionBy());
//        //System.out.println(transaction1.getTransactionBy());
//        //System.out.println(receiverResponse.getTransactions().get(0).getTransactionBy());
//
//        //test case failed beacuse the actual value and the expected values are not same (i.e expected Mohan but actual value is Raksha)
//        assertEquals(transaction1.getTransactionBy(),amountResponse.getTransactions().get(0).getTransactionBy());
//        //System.out.println(transaction1.getTransactionBy());
//        //System.out.println(receiverResponse.getTransactions().get(0).getTransactionBy());
//
//    }
//
//    @Test
//    public void testUpdateTransaction(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        when(transactionsService.updateRemarks(any(Transactions.class))).thenReturn(transaction2);
//
//        UpdateTransactionRemarksRequest remarksRequest=new UpdateTransactionRemarksRequest();
//        services.transactions.Transactions transactions=new services.transactions.Transactions();
//        transactions.setTransactionId(1235455L);
//        transactions.setTransactionDate(XMLGregorianCalendarImpl.createDate(2024,07,21,0));
//        transactions.setTransactionBy("Raksha");
//        transactions.setTransactionTo("Pranav");
//        transactions.setTransactionAmount(1000.0);
//        transactions.setTransactionRemarks("Medicine");
//        remarksRequest.setTransactions(transactions);
//
//        UpdateTransactionRemarksResponse remarksResponse=soapPhase.updateRemarks(remarksRequest);
//
//        //test case passed because the remarks is updated to medicine and hence actual and the expected values are same medicine
//        assertEquals(transaction2.getTransactionId(),remarksResponse.getTransactions().getTransactionId());
//        System.out.println(transaction2.getTransactionRemarks());
//        System.out.println(remarksResponse.getTransactions().getTransactionRemarks());
//
//    }
//
//    @Test
//    public void testRemoveBetweenDate(){
//        Transactions transaction1=new Transactions(2345455L,new Date(2024,03,12),"Mohan","Surendra",12000.0,"Friend");
//        Transactions transaction2=new Transactions(1235455L,new Date(2024,07,21),"Raksha","Pranav",1000.0,"Medicine");
//        Transactions transaction3=new Transactions(2341255L,new Date(2024,11,18),"Neha","Razi",25000.0,"Education");
//
//        List<Transactions> transactions=Stream.of(transaction1,transaction3).collect(Collectors.toList());
//
//        RemoveTransactionRequest removeTransactionRequest=new RemoveTransactionRequest();
//
//        removeTransactionRequest.setDate1(XMLGregorianCalendarImpl.createDate(2024,01,01,0));
//        removeTransactionRequest.setDate2(XMLGregorianCalendarImpl.createDate(2024,05,01,0));
//
//        when(transactionsService.closeTransaction(any(Date.class),any(Date.class))).thenReturn("Transaction removed between the dates specified");
//
//        RemoveTransactionResponse removeTransactionResponse=soapPhase.closeTransaction(removeTransactionRequest);
//
//        //test case failed because the status message expected and actual valus is different
//        assertEquals("Transaction removed ",removeTransactionResponse.getServiceStatus().getStatus());
//        System.out.println(removeTransactionResponse.getServiceStatus().getStatus());
//
//        //test case passed because the status message is same in actual and expected place
//        assertEquals("Transaction removed between the dates specified",removeTransactionResponse.getServiceStatus().getStatus());
//
//    }
//
//
//
//}
