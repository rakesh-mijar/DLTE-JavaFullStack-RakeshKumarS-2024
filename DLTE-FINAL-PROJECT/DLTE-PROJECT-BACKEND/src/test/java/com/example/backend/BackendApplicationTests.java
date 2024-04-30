//package com.example.backend;
//
//import com.example.backend.soap.services.SoapPhase;
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.AccountNotFoundException;
//import com.project.dao.exceptions.CustomerNotFoundException;
//import com.project.dao.security.MyBankCustomers;
//import com.project.dao.security.MyBankCustomersService;
//import com.project.dao.services.AccountsServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import services.accounts.FilterByStatusRequest;
//import services.accounts.FilterByStatusResponse;
//
//import javax.servlet.http.HttpServletResponse;
//import java.rmi.ServerException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.mock;
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
//	@MockBean
//    private MyBankCustomersService myBankCustomersService;
//
//    @Test
//    public void testFilterByStatus_Success() throws CustomerNotFoundException, ServerException, SQLSyntaxErrorException {
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.getName()).thenReturn("user"); // Set the authenticated username
//
//        // Mock SecurityContext
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        // Mock MyBankCustomers object
//        MyBankCustomers myBankCustomer = new MyBankCustomers();
//        myBankCustomer.setCustomerId(1L); // Set customer ID
//        when(myBankCustomersService.findByUsername("user")).thenReturn(myBankCustomer);
//
//        // Mock the accounts list returned by the service
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
//        // Mock the service method to return the account list
//        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);
//
//
//        // Call the service method with the new parameter
//        FilterByStatusRequest mockRequest = new FilterByStatusRequest();
//
//
//        // Call the service method
//        FilterByStatusResponse response = soapPhase.filterByStatus(mockRequest);
//
//        // Assertions
//        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
//        assertEquals("Account fetch success", response.getServiceStatus().getMessage());
//        assertEquals(1, response.getAccounts().size());
//        assertEquals(account.getAccountId(), response.getAccounts().get(0).getAccountId());
//    }
//
//    @Test
//    public void testFilterByStatus_CustomerNotFound() throws CustomerNotFoundException {
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.getName()).thenReturn("user"); // Set the authenticated username
//
//        // Mock SecurityContext
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        // Mock MyBankCustomersService to throw CustomerNotFoundException
//        when(myBankCustomersService.findByUsername("user")).thenThrow(new CustomerNotFoundException("Customer not found"));
//
//        FilterByStatusRequest request = new FilterByStatusRequest();
//
//        // Call the service method and assert that CustomerNotFoundException is thrown
//        assertThrows(CustomerNotFoundException.class, () -> soapPhase.filterByStatus(request));
//    }
//
//    @Test
//    public void testFilterByStatus_AccountNotFound() throws CustomerNotFoundException {
//        // Mock authentication
//        Authentication authentication = mock(Authentication.class);
//        when(authentication.getName()).thenReturn("testUser"); // Set the authenticated username
//
//        // Mock SecurityContext
//        SecurityContext securityContext = mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        // Mock MyBankCustomersService to throw CustomerNotFoundException
//        when(myBankCustomersService.findByUsername("testUser")).thenThrow(new AccountNotFoundException("Account not found"));
//        FilterByStatusRequest request=new FilterByStatusRequest();
//        // Call the service method and assert that CustomerNotFoundException is thrown
//        assertFalse(()->{
//            try{
//                soapPhase.filterByStatus(request);
//                return true;
//            }catch (AccountNotFoundException customerException){
//                return false;
//            }
//        });
//        //assertThrows(CustomerNotFoundException.class, () -> soapPhase.filterByStatus());
//    }
//
//
//}
