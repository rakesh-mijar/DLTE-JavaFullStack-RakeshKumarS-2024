//package com.project.dao;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.CustomerNotFoundException;
//import com.project.dao.services.AccountsServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.security.auth.login.AccountNotFoundException;
//import java.rmi.ServerException;
//import java.sql.Types;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ResourceBundle;
//
//@ExtendWith(MockitoExtension.class)
//class DaoTest {
//
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private AccountsServices accountsServices;
//
//    //test case failed because it actually returns updated account object but we are expecting null
//    @Test
//    void testUpdateAccountService_Fail1() throws ServerException, AccountNotFoundException {
//        // Create a sample Accounts object for testing
//        Accounts accounts = new Accounts();
//        accounts.setAccountId(1L);
//        accounts.setAccountNumber(123456789L);
//        accounts.setCustomerId(1L);
//        accounts.setAccountBalance(500.0);
//        accounts.setAccountStatus("Active");
//        accounts.setAccountType("savings");
//
//
//        // Mock returned Account fetched from the database
//        Accounts fetchedAccount = new Accounts();
//        fetchedAccount.setAccountId(1L);
//        fetchedAccount.setAccountNumber(123456789L);
//        fetchedAccount.setCustomerId(1L);
//        fetchedAccount.setAccountBalance(500.0);
//        fetchedAccount.setAccountStatus("Active");
//        fetchedAccount.setAccountType("savings");
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        when(jdbcTemplate.queryForObject(
//                eq("SELECT * FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_NUMBER = ?"),
//                any(Object[].class),
//                any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(fetchedAccount);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("p_result", "SQLSUCESS");
//        returnedExecution.put("p_account_number", 123456789L);
//        returnedExecution.put("p_customer_id", 1L);
//        returnedExecution.put("p_account_type", "savings");
//        returnedExecution.put("p_account_status", "Inactive");
//        returnedExecution.put("p_account_balance", 5000.0);
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Call the method to be tested
//        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);
//
//        // Assertions
//        assertNull(updatedAccount);
//    }
//
//
//    //actual status is updated to inactive but we are expecting status as active
//    @Test
//    void testUpdateAccountService_Fail2() throws ServerException, AccountNotFoundException {
//        // Create a sample Accounts object for testing
//        Accounts accounts = new Accounts();
//        accounts.setAccountId(1L);
//        accounts.setAccountNumber(123456789L);
//        accounts.setCustomerId(1L);
//        accounts.setAccountBalance(500.0);
//        accounts.setAccountStatus("Active");
//        accounts.setAccountType("savings");
//
//
//        // Mock returned Account fetched from the database
//        Accounts fetchedAccount = new Accounts();
//        fetchedAccount.setAccountId(1L);
//        fetchedAccount.setAccountNumber(123456789L);
//        fetchedAccount.setCustomerId(1L);
//        fetchedAccount.setAccountBalance(500.0);
//        fetchedAccount.setAccountStatus("Active");
//        fetchedAccount.setAccountType("savings");
//        // Set other necessary fields as needed
//
//        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
//        when(jdbcTemplate.queryForObject(
//                eq("SELECT * FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_NUMBER = ?"),
//                any(Object[].class),
//                any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(fetchedAccount);
//
//        // Mock the returned execution map with success result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("p_result", "SQLSUCESS");
//        returnedExecution.put("p_account_number", 123456789L);
//        returnedExecution.put("p_customer_id", 1L);
//        returnedExecution.put("p_account_type", "savings");
//        returnedExecution.put("p_account_status", "Inactive");
//        returnedExecution.put("p_account_balance", 5000.0);
//
//        // Stub the jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);
//
//        // Call the method to be tested
//        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);
//
//        // Assertions
//        assertEquals("Active", updatedAccount.getAccountStatus());
//
//        //assertEquals(1234589L, updatedAccount.getAccountNumber());
////        assertEquals(1L, updatedAccount.getCustomerId());
////        assertEquals("savings", updatedAccount.getAccountType());
////        assertEquals(5000.0, updatedAccount.getAccountBalance());
//    }
//
//    //test case passed because we are mocking account not found exception
//    @Test
//    public void testUpdateAccountService_AccountNotFoundException() {
//        // Prepare test data
//        Accounts updatedAccount = new Accounts();
//        updatedAccount.setAccountId(12345L);
//        updatedAccount.setAccountNumber(67890L);
//        updatedAccount.setCustomerId(54321L);
//        updatedAccount.setAccountType("Savings");
//        updatedAccount.setAccountStatus("Closed");
//        updatedAccount.setAccountBalance(0.0);
//
//        // Mock the behavior of the JDBC template to simulate AccountNotFoundException
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
//                .thenThrow(com.project.dao.exceptions.AccountNotFoundException.class);
//
//        // Verify that AccountNotFoundException is thrown
//        assertThrows(com.project.dao.exceptions.AccountNotFoundException.class, () -> {
//            accountsServices.UpdateAccountService(updatedAccount);
//        });
//    }
//
//
//    //test case passed because we are mocking customer not found exception i.e it returns error code SQLERR-002
//    @Test
//    void testUpdateAccountService_CustomerNotFoundException() {
//        // Prepare test data
//        Accounts updatedAccount = new Accounts();
//        updatedAccount.setAccountId(12345L);
//        updatedAccount.setAccountNumber(67890L);
//        updatedAccount.setCustomerId(54321L);
//        updatedAccount.setAccountType("Savings");
//        updatedAccount.setAccountStatus("Closed");
//        updatedAccount.setAccountBalance(0.0);
//
//        // Mock the behavior of the stored procedure to simulate CustomerNotFoundException
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("p_result", "SQLERR-002");
//        lenient().when(jdbcTemplate.call(any(), any()))
//                .thenReturn(returnedExecution);
//
//        assertEquals("SQLERR-002", returnedExecution.get("p_result"));
//    }
//
//
//}
//
