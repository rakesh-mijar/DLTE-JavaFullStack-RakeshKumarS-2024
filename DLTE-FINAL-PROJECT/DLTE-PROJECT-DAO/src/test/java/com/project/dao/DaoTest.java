package com.project.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.project.dao.entities.Accounts;
import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.rmi.ServerException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AccountsServices accountsServices;

    @Test
    void testUpdateAccountService_Success1() throws ServerException, AccountNotFoundException {
        // Create a sample Accounts object for testing
        Accounts accounts = new Accounts();
        accounts.setAccountId(1L);
        accounts.setAccountNumber(123456789L);
        accounts.setCustomerId(1L);
        accounts.setAccountBalance(500.0);
        accounts.setAccountStatus("Active");
        accounts.setAccountType("savings");


        // Mock returned Account fetched from the database
        Accounts fetchedAccount = new Accounts();
        fetchedAccount.setAccountId(1L);
        fetchedAccount.setAccountNumber(123456789L);
        fetchedAccount.setCustomerId(1L);
        fetchedAccount.setAccountBalance(500.0);
        fetchedAccount.setAccountStatus("Active");
        fetchedAccount.setAccountType("savings");
        // Set other necessary fields as needed

        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_ID = ?"),
                any(Object[].class),
                any(AccountsServices.AccountsMapper.class)))
                .thenReturn(fetchedAccount);

        // Mock the returned execution map with success result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", "SQLSUCESS");
        returnedExecution.put("p_account_number", 123456789L);
        returnedExecution.put("p_customer_id", 1L);
        returnedExecution.put("p_account_type", "savings");
        returnedExecution.put("p_account_status", "Inactive");
        returnedExecution.put("p_account_balance", 5000.0);

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);

        // Call the method to be tested
        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);

        // Assertions
        assertNull(updatedAccount);
    }


    @Test
    void testUpdateAccountService_Success2() throws ServerException, AccountNotFoundException {
        // Create a sample Accounts object for testing
        Accounts accounts = new Accounts();
        accounts.setAccountId(1L);
        accounts.setAccountNumber(123456789L);
        accounts.setCustomerId(1L);
        accounts.setAccountBalance(500.0);
        accounts.setAccountStatus("Active");
        accounts.setAccountType("savings");


        // Mock returned Account fetched from the database
        Accounts fetchedAccount = new Accounts();
        fetchedAccount.setAccountId(1L);
        fetchedAccount.setAccountNumber(123456789L);
        fetchedAccount.setCustomerId(1L);
        fetchedAccount.setAccountBalance(500.0);
        fetchedAccount.setAccountStatus("Active");
        fetchedAccount.setAccountType("savings");
        // Set other necessary fields as needed

        // Stub the jdbcTemplate.queryForObject() method to return the fetchedAccount
        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_ID = ?"),
                any(Object[].class),
                any(AccountsServices.AccountsMapper.class)))
                .thenReturn(fetchedAccount);

        // Mock the returned execution map with success result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", "SQLSUCESS");
        returnedExecution.put("p_account_number", 123456789L);
        returnedExecution.put("p_customer_id", 1L);
        returnedExecution.put("p_account_type", "savings");
        returnedExecution.put("p_account_status", "Inactive");
        returnedExecution.put("p_account_balance", 5000.0);

        // Stub the jdbcTemplate.call() method to return the mock execution map
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);

        // Call the method to be tested
        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);

        // Assertions
        //assertEquals(1234589L, updatedAccount.getAccountNumber());
////        assertEquals(1L, updatedAccount.getCustomerId());
////        assertEquals("savings", updatedAccount.getAccountType());
        assertEquals("Active", updatedAccount.getAccountStatus());
////        assertEquals(5000.0, updatedAccount.getAccountBalance());
    }




//    //testing the account not found exception
//    @Test
//    void testUpdateAccountService_AccountNotFound() {
//        // Mock returned execution map with error result
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("p_result", "SQLERR-001");
//
//        // Stubbing jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(), anyList())).thenReturn(returnedExecution);
//
//        // Call the method to be tested and assert that it throws AccountNotFoundException
//        assertThrows(com.project.dao.exceptions.AccountNotFoundException.class, () -> accountsServices.UpdateAccountService(new Accounts()));
//    }
//
//    //
//    @Test
//    void testUpdateAccountService_CustomerNotFound() {
//        // Mock returned execution map with error result for customer not found
//        Map<String, Object> returnedExecution = new HashMap<>();
//        returnedExecution.put("p_result", "SQLERR-002");
//
//        //Stubbing jdbcTemplate.call() method to return the mock execution map
//        when(jdbcTemplate.call(any(), anyList())).thenReturn(returnedExecution);
//
//        // Call the method to be tested and assert that it throws CustomerNotFoundException
//        assertThrows(AccountNotFoundException.class, () -> accountsServices.UpdateAccountService(new Accounts()));
//    }
//
//
//    @Test
//    void testUpdateAccountService_ServerError() {
//        // Stubbing jdbcTemplate.call() method to throw DataAccessException
//        when(jdbcTemplate.call(any(), anyList())).thenThrow(DataAccessException.class);
//
//        // Call the method to be tested and assert that it throws ServerException
//        assertThrows(ServerException.class, () -> accountsServices.UpdateAccountService(new Accounts()));
//    }
}
