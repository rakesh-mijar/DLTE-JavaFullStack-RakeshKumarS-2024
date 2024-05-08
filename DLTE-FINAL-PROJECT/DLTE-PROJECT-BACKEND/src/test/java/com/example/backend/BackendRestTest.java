package com.example.backend;

import com.example.backend.rest.services.AccountController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.remotes.AccountRepository;

import com.project.dao.remotes.CustomerRepository;
import com.project.dao.security.MyBankCustomersService;
import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.rmi.ServerException;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BackendRestTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountController accountController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Authentication authentication;


    @Test
    public void testCloseAccountService_Success() throws ServerException {
        String username = "testuser";
        MyBankCustomers customer = new MyBankCustomers();
        customer.setCustomerId(1L);
        customer.setUsername(username);

        Accounts account = new Accounts();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(123456L);
        account.setAccountType("Savings");
        account.setAccountStatus("Active");
        account.setAccountBalance(100.0);
        account.setAccountId(101L);

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(username);
        when(customerRepository.findByUsername(username)).thenReturn(customer);
        when(accountRepository.UpdateAccountService(any(Accounts.class))).thenReturn(account);


        ResponseEntity<Object> responseEntity = accountController.closeAccountService(account);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());

        assertEquals("<200,{accountStatus=Active, accountType=Savings, accountNumber=123456, accountBalance=100.0}Account service closed successfully,[]>",responseEntity.toString());
    }

    @Test
    @WithAnonymousUser
    public void testCloseAccountService_Unauthorized() throws Exception {
        String request = "{\n" +
                "    \"accountNumber\": 2323232323,\n" +
                "    \"accountType\": \"Salary\",\n" +
                "    \"accountStatus\": \"Active\",\n" +
                "    \"accountBalance\": 20000\n" +
                "}";

        mockMvc.perform(put("/accounts/closeAccounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testCloseAccountservice_Success() throws Exception {
        // Mock authentication
        //Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        lenient().when(authentication.getName()).thenReturn("rakesh123");

        // Mock customer
        MyBankCustomers customer = new MyBankCustomers();
        lenient().when(customerRepository.findByUsername("rakesh123")).thenReturn(customer);

        // Mock account
        Accounts account = new Accounts();
        account.setCustomerId(123L); // Set customer ID as needed
        lenient().when(accountRepository.UpdateAccountService(any())).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.put("/accounts/closeAccounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"accountNumber\":1234567890,\"accountType\":\"Savings\",\"accountStatus\":\"Active\",\"accountBalance\":1000.0}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "rakesh")
    public void testRestDepositAvailed() throws Exception {
        String request = "{\n" +
                "    \"accountNumber\": 2323232323,\n" +
                "    \"accountType\": \"Salary\",\n" +
                "    \"accountStatus\": \"Active\",\n" +
                "    \"accountBalance\": 20000\n" +
                "}";
        mockMvc.perform(put("/accounts/closeAccounts").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "rakesh")
    public void testCloseAccountService_InvalidRequestBody2() throws Exception {
        String request = "{\n" +
                "    \"accountNumber\": 2323232323,\n" +
                "    \"accountType\": \"Salary\",\n" +
                "    \"accountStatus\": \"Actve\",\n" +
                "    \"accountBalance\": \n" +
                "}";

        mockMvc.perform(put("/accounts/closeAccounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCloseAccountService_UnauthenticatedUser() throws Exception {
        mockMvc.perform(put("/accounts/closeAccounts"))
                .andExpect(status().isUnauthorized());
    }
}



