//package com.example.backend;
//
//import com.example.backend.soap.services.SoapPhase;
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.CustomerNotFoundException;
//import com.project.dao.services.AccountsServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import services.accounts.FilterByStatusRequest;
//import services.accounts.FilterByStatusResponse;
//
//import java.rmi.ServerException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class BackendApplicationTests {
//
//	@MockBean
//    private AccountsServices accountsServices;
//
//	@InjectMocks
//    private SoapPhase soapPhase;
//
//	@Test
//    //test case failed
//    public void testfilterByStatus() throws CustomerNotFoundException, ServerException, SQLSyntaxErrorException {
//        List<Accounts> accountList = new ArrayList<>();
//        Accounts account = new Accounts();
//        account.setAccountId(1235456L);
//        account.setCustomerId(1L);
//        account.setAccountType("Savings");
//        account.setAccountNumber(105L);
//        account.setAccountBalance(20000D);
//        account.setAccountStatus("Active");
//        accountList.add(account);
//
//        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);
//
//        FilterByStatusRequest request = new FilterByStatusRequest();
//        request.setCustomerId(1L);
//        FilterByStatusResponse response = soapPhase.filterByStatus(request);
//
//        // checking the response is success or not
//          assertEquals("Successful operation 200", response.getServiceStatus().getStatus());
//
//        //  assertEquals("Account details fetched successfully",response.getServiceStatus().getMessage());//success
//        //assertEquals("Account not fetched",response.getServiceStatus().getMessage());
//    }
//
//    @Test
//    //test case passed
//    public void testfilterByStatus2() throws CustomerNotFoundException, ServerException, SQLSyntaxErrorException {
//        List<Accounts> accountList = new ArrayList<>();
//        Accounts account = new Accounts();
//        account.setAccountId(1235456L);
//        account.setCustomerId(1L);
//        account.setAccountType("Savings");
//        account.setAccountNumber(105L);
//        account.setAccountBalance(20000D);
//        account.setAccountStatus("Active");
//        accountList.add(account);
//
//        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);
//
//        FilterByStatusRequest request = new FilterByStatusRequest();
//        request.setCustomerId(1L);
//        FilterByStatusResponse response = soapPhase.filterByStatus(request);
//
//        // checking the response is success or not
//
//          assertEquals("Account details fetched successfuly",response.getServiceStatus().getMessage());//success
//        //assertEquals("Account not fetched",response.getServiceStatus().getMessage());
//    }
////    //test cae failed
////    @Test
////    public void testfilterByStatus3() throws CustomerNotFoundException, ServerException, SQLSyntaxErrorException {
////        List<Accounts> accountList = new ArrayList<>();
////        Accounts account = new Accounts();
////        account.setAccountId(1235456L);
////        account.setCustomerId(1L);
////        account.setAccountType("Savings");
////        account.setAccountNumber(105L);
////        account.setAccountBalance(20000D);
////        account.setAccountStatus("Active");
////        accountList.add(account);
////
////        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);
////
////        FilterByStatusRequest request = new FilterByStatusRequest();
////        request.setCustomerId(2L);
////        FilterByStatusResponse response = soapPhase.filterByStatus(request);
////
////        // checking the response is success or not
////        assertEquals(accountList.get(0).getCustomerId(),request.getCustomerId());
////    }
//
//}
