//package com.example.backend;
//
//import com.example.backend.rest.services.AccountController;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.AccountNotFoundException;
//import com.project.dao.remotes.AccountRepository;
//import com.project.dao.security.MyBankCustomers;
//import com.project.dao.security.MyBankCustomersService;
//import com.project.dao.services.AccountsServices;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
////import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
//
//import java.rmi.ServerException;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
////@SpringBootTest
////@ExtendWith(MockitoExtension.class)
//@WebMvcTest(BackendRestTest.class)
//public class BackendRestTest {
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountController accountController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountsServices accountService;
//
//    @MockBean
//    private MyBankCustomersService myBankCustomersService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//
//    @Test
//    @WithMockUser(username = "testUser", password = "user")
//    public void testCloseAccountService_AuthenticatedUser1() throws Exception {
//        mockMvc.perform(put("/accounts/closeAccounts"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testCloseAccountService_UnauthenticatedUser() throws Exception {
//        mockMvc.perform(put("/accounts/closeAccounts"))
//                .andExpect(status().isUnauthorized());
//    }
//    @Test
//    @WithMockUser(username = "rakesh",password = "rakesh")
//    public void testCloseAccountService_Success1() throws Exception {
//        // Mock authentication
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // Mock the authentication name to return a username
//        Mockito.when(authentication.getName()).thenReturn("testUsername");
//
//        // Prepare sample account data
//        Accounts account = new Accounts();
//        account.setAccountId(105L);
//        account.setCustomerId(1L);
//        account.setAccountType("Savings");
//        account.setAccountNumber(1235456L);
//        account.setAccountBalance(20000D);
//        account.setAccountStatus("Inactive");
//        // Set necessary properties of the account object
//
//        // Mock MyBankCustomersService to return a valid customer
//        MyBankCustomers customers = new MyBankCustomers();
//        // Set necessary properties of the customer object
//        Mockito.when(myBankCustomersService.findByUsername(Mockito.anyString())).thenReturn(customers);
//
//        // Mock account service to return a valid response
//        Accounts updatedAccount = createUpdatedAccount();
//        Mockito.when(accountService.UpdateAccountService(Mockito.any())).thenReturn(updatedAccount);
//
//        // Send a PUT request to the controller
//        mockMvc.perform(put("/closeAccounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(account)))
//                .andExpect(status().isForbidden());
//    }
//    private Accounts createUpdatedAccount() {
//        Accounts account = new Accounts();
//        account.setAccountId(105L);
//        account.setCustomerId(1L);
//        account.setAccountType("Savings");
//        account.setAccountNumber(1235456L);
//        account.setAccountBalance(20000D);
//        account.setAccountStatus("Inactive");
//        return account;
//    }
//
//}
//
//
//
