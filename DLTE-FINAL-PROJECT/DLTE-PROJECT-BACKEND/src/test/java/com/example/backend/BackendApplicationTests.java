package com.example.backend;

import com.example.backend.rest.services.AccountController;
import com.example.backend.soap.services.SoapPhase;
import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.exceptions.AccountNotFoundException;

import com.project.dao.remotes.AccountRepository;
import com.project.dao.remotes.CustomerRepository;
import com.project.dao.security.MyBankCustomersService;
import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import services.accounts.FilterByStatusRequest;
import services.accounts.FilterByStatusResponse;
import services.accounts.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BackendApplicationTests {

    @Mock
    private AccountRepository accountsServices;

    @InjectMocks
    private SoapPhase soapPhase;

    @MockBean
    private MyBankCustomersService myBankCustomersService;

    @Mock
    private CustomerRepository customerRepository;
    private Authentication authentication;
    private MyBankCustomers myBankCustomer;

    @BeforeEach
    void setup() {
        authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testFilterByStatus_Successdf() throws ServerException, SQLSyntaxErrorException {
        // Mock authentication
        //Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");

        // Mock SecurityContext
        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        // Mock MyBankCustomers object
        MyBankCustomers myBankCustomer = new MyBankCustomers();
        myBankCustomer.setCustomerId(1L);
        when(myBankCustomersService.findByUsername("user")).thenReturn(myBankCustomer);

        // Mock the accounts list returned by the service
        List<com.project.dao.entities.Accounts> accountList = new ArrayList<>();
        com.project.dao.entities.Accounts account = new com.project.dao.entities.Accounts();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);


        // Mock the service method to return the account list
        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);

        // Create a FilterByStatusRequest object with required data
        FilterByStatusRequest request = new FilterByStatusRequest();

        // Call the service method
        FilterByStatusResponse response = soapPhase.filterByStatus(request);


        System.out.println(response.getAccounts().toString());
        assertNotNull(response);
        ServiceStatus serviceStatus = response.getServiceStatus();
        assertNotNull(serviceStatus);
    }


    @Test
    public void testFilterByStatus_Success() throws AccountNotFoundException, ServerException, SQLSyntaxErrorException {
        //Authentication authentication = mock(Authentication.class);
        //SecurityContext securityContext = mock(SecurityContext.class);
        //SecurityContextHolder.setContext(securityContext);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        MyBankCustomers myBankCustomer = new MyBankCustomers();
        lenient().when(customerRepository.findByUsername("testUser")).thenReturn(myBankCustomer);

        List<com.project.dao.entities.Accounts> accountsList = new ArrayList<>();
        Accounts account = new Accounts();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountsList.add(account);

//        com.project.dao.entities.Accounts account1 = new com.project.dao.entities.Accounts();
        accountsList.add(account);
        lenient().when(accountsServices.filterByCustomerStatus(myBankCustomer.getCustomerId())).thenReturn(accountsList);


        FilterByStatusRequest request = new FilterByStatusRequest();
        FilterByStatusResponse response = soapPhase.filterByStatus(request);

        assertEquals("Null object detected: myBankCustomer or accountsServices", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterByStatus_Succese() throws ServerException, SQLSyntaxErrorException {
        //Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");


        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        MyBankCustomers myBankCustomer = new MyBankCustomers();
        myBankCustomer.setCustomerId(1L);
        when(myBankCustomersService.findByUsername("user")).thenReturn(myBankCustomer);

        List<Accounts> accountList = new ArrayList<>();
        Accounts account = new Accounts();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);


        when(accountsServices.filterByCustomerStatus(1L)).thenReturn(accountList);


        FilterByStatusRequest request = new FilterByStatusRequest();

        FilterByStatusResponse response = soapPhase.filterByStatus(request);

        System.out.println(response.getServiceStatus().getMessage());
        System.out.println(response.getServiceStatus().getStatus());
        assertNotNull(response);
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertEquals("Account details fetched successfuly", response.getServiceStatus().getMessage());
        assertEquals(1, response.getAccounts().size());
        assertEquals(account.getAccountId(), response.getAccounts().get(0).getAccountId());
    }


    @Test
    public void testFilterByStatus_AccountNotFounnd() throws AccountNotFoundException {

       // Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("suresh");
        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        when(myBankCustomersService.findByUsername("suresh")).thenThrow(new AccountNotFoundException("No active accounts to display"));
        FilterByStatusRequest request = new FilterByStatusRequest();

        assertThrows(AccountNotFoundException.class, () -> soapPhase.filterByStatus(request));
    }
}
