//package com.example.backend;
//
//import com.example.backend.rest.services.AccountController;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.AccountNotFoundException;
//import com.project.dao.remotes.AccountRepository;
//import org.junit.jupiter.api.extension.ExtendWith;
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
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.rmi.ServerException;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
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
////    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    //test case passed because status code is as expected i.e 200 OK
//    @Test
//    public void testCloseaccountRepository_Success1() throws ServerException {
//        // Mock service method to return a valid response
//        Accounts updatedAccount = createUpdatedAccount();
//        when(accountRepository.UpdateAccountService(any())).thenReturn(updatedAccount);
//
//        // Send a valid request to the controller
//        ResponseEntity<Object> response = accountController.closeAccountService(updatedAccount);
//        //System.out.println(response.getBody());
//
//        // Assert that the response status is OK and the body matches the returned updated account
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }
//
//    //test case passed because status is actually inactive
//    @Test
//    public void testCloseaccountRepository2() throws ServerException {
//        // Mock service method to return a valid response
//        Accounts updatedAccount = createUpdatedAccount();
//        when(accountRepository.UpdateAccountService(any())).thenReturn(updatedAccount);
//
//        // Send a valid request to the controller
//        ResponseEntity<Object> response = accountController.closeAccountService(updatedAccount);
//        //System.out.println(response.getBody());
//
//
//        // Assert that the account status is set to "Inactive"
//        assertEquals("Inactive", updatedAccount.getAccountStatus());
//    }
//
//
//    //test case failed because expecting a message but actually returned status code and updated account object
//    @Test
//    public void testCloseaccountRepository3() throws ServerException {
//        // Mock service method to return a valid response
//        Accounts updatedAccount = createUpdatedAccount();
//        when(accountRepository.UpdateAccountService(any())).thenReturn(updatedAccount);
//
//        // Send a valid request to the controller
//        ResponseEntity<Object> response = accountController.closeAccountService(updatedAccount);
//        //System.out.println(response.getBody());
//
//        assertEquals("Account Closed Succesfully",response);
//    }
//
//    //test case passed because the expected and actual status code is same i.e 404
//    @Test
//    public void testCloseAccountService_NotFoundException() throws ServerException {
//        // Mock service method to throw an AccountNotFoundException
//        when(accountRepository.UpdateAccountService(any())).thenThrow(new AccountNotFoundException("Account not found"));
//
//        // Send a request to the controller
//        ResponseEntity<Object> response = accountController.closeAccountService(createUpdatedAccount());
//
//        //System.out.println(response.getStatusCode());
//        //System.out.println(response.getBody());
//
//        // Assert that the response status is NOT_FOUND
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//
////        assertEquals("Account not found",response.getBody());
//    }
//
//    //test case failed because response message and the expected statement are not equal
//    @Test
//    public void testCloseAccountService4() throws ServerException {
//        // Mock service method to throw an AccountNotFoundException
//        when(accountRepository.UpdateAccountService(any())).thenThrow(new AccountNotFoundException("Account not found"));
//
//        // Send a request to the controller
//        ResponseEntity<Object> response = accountController.closeAccountService(createUpdatedAccount());
//
//        //System.out.println(response.getStatusCode());
//        System.out.println(response.getBody());
//        // Assert that the response status is NOT_FOUND
//        assertEquals("No account found",response.getBody());
//    }
//
//
//
//    @Test
//    //@WithMockUser(username = "rakesh", password = "rakesh")
//    public void testCloseAccount_Success() throws Exception {
//        // Mocking the behavior of the accountRepository.closeAccount method
//        Accounts closedAccount = createUpdatedAccount();
//        when(accountRepository.UpdateAccountService(any(Accounts.class))).thenReturn(closedAccount);
//
//        // Mocking the path variable and request body
//        Long accountId = 1L;
//        Accounts requestAccount = createUpdatedAccount();
//
//        // Performing the HTTP PUT request
//        mockMvc.perform(put("/accounts/closeAccounts",accountId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(requestAccount)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.accountId").value(closedAccount.getAccountId()))
//                .andExpect(jsonPath("$.accountNumber").value(closedAccount.getAccountNumber()))
//                .andExpect(jsonPath("$.customerId").value(closedAccount.getCustomerId()))
//                .andExpect(jsonPath("$.accountType").value(closedAccount.getAccountType()))
//                .andExpect(jsonPath("$.accountStatus").value(closedAccount.getAccountStatus()))
//                .andExpect(jsonPath("$.accountBalance").value(closedAccount.getAccountBalance()));
//    }
//
//    // Utility method to create a sample updated account object for testing
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
//    // Utility method to convert object to JSON string
//    private String asJsonString(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//
//    @Test
//    //@WithMockUser(username = "rakesh", password = "rakesh")
//    public void testCloseAccount_Success2() throws Exception {
//        // Mocking the behavior of the accountRepository.closeAccount method
//        Accounts closedAccount = createUpdatedAccount();
//        when(accountRepository.UpdateAccountService(any(Accounts.class))).thenReturn(closedAccount);
//
//        // Mocking the request body
//        String request = "{\n" +
//                "  \"accountId\": 105,\n" +
//                "  \"customerId\": 1,\n" +
//                "  \"accountType\": \"Savings\",\n" +
//                "  \"accountNumber\": 1235456,\n" +
//                "  \"accountBalance\": 20000.0,\n" +
//                "  \"accountStatus\": \"Inactive\"\n" +
//                "}";
//
//        // Performing the HTTP PUT request
//        mockMvc.perform(put("/accounts/closeAccounts")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(request))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.accountId").value(closedAccount.getAccountId()))
//                .andExpect(jsonPath("$.accountNumber").value(closedAccount.getAccountNumber()))
//                .andExpect(jsonPath("$.customerId").value(closedAccount.getCustomerId()))
//                .andExpect(jsonPath("$.accountType").value(closedAccount.getAccountType()))
//                .andExpect(jsonPath("$.accountStatus").value(closedAccount.getAccountStatus()))
//                .andExpect(jsonPath("$.accountBalance").value(closedAccount.getAccountBalance()));
//    }
//}