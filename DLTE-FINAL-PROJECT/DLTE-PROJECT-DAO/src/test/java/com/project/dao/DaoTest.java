package com.project.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import java.rmi.ServerException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AccountsServices accountsServices;

    @Test
    void testUpdateAccountService_Success1() throws ServerException {

        Accounts accounts = new Accounts();
        accounts.setAccountNumber(123456789L);
        accounts.setAccountBalance(500.0);
        accounts.setAccountStatus("Active");
        accounts.setAccountType("savings");

        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", "SQLSUCESS");
        returnedExecution.put("p_account_number", 123456789L);
        returnedExecution.put("p_account_type", "savings");
        returnedExecution.put("p_account_status", "Inactive");
        returnedExecution.put("p_account_balance", 5000.0);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);


        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);

        assertEquals("Inactive",updatedAccount.getAccountStatus());
    }


    @Test
    void testUpdateAccountService_S2() throws ServerException{
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(123456789L);
        accounts.setAccountBalance(500.0);
        accounts.setAccountStatus("Active");
        accounts.setAccountType("savings");

        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", "SQLSUCESS");
        returnedExecution.put("p_account_number", 123456789L);
        returnedExecution.put("p_account_type", "savings");
        returnedExecution.put("p_account_status", "Inactive");
        returnedExecution.put("p_account_balance", 5000.0);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);


        Accounts updatedAccount = accountsServices.UpdateAccountService(accounts);



        assertEquals(123456789L, updatedAccount.getAccountNumber());
//        assertEquals(1L, updatedAccount.getCustomerId());
//        assertEquals("savings", updatedAccount.getAccountType());
//        assertEquals(5000.0, updatedAccount.getAccountBalance());
    }



    @Test
    public void testUpdateAccountService_AccountNotFoundException2() throws ServerException {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(123456789L);
        accounts.setCustomerId(54321L);
        accounts.setAccountType("Savings");
        accounts.setAccountStatus("Inactive");
        accounts.setAccountBalance(1000.0);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenReturn(Collections.singletonMap("p_result", "SQLERR-001"));

        com.project.dao.exceptions.AccountNotFoundException exception = assertThrows(com.project.dao.exceptions.AccountNotFoundException.class, () -> {
            accountsServices.UpdateAccountService(accounts);
        });


        assertEquals("Account is already inactive", exception.getMessage());
    }

    @Test
    public void testUpdateAccountService_ServerException_NoData() throws ServerException {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(123456789L);
        accounts.setCustomerId(54321L);
        accounts.setAccountType("Savings");
        accounts.setAccountStatus("Active");
        accounts.setAccountBalance(1000.0);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenReturn(Collections.singletonMap("p_result", "SQLERR-004"));

        ServerException exception = assertThrows(ServerException.class, () -> {
            accountsServices.UpdateAccountService(accounts);
        });

        assertEquals("No Data Found", exception.getMessage());
    }


    @Test
    public void testUpdateAccountService_ServerException_DataAccessException() throws ServerException {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(123456789L);
        accounts.setCustomerId(54321L);
        accounts.setAccountType("Savings");
        accounts.setAccountStatus("Active");
        accounts.setAccountBalance(1000.0);

        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
                .thenThrow(new DataAccessException("Data access exception") {
                });

        ServerException exception = assertThrows(ServerException.class, () -> {
            accountsServices.UpdateAccountService(accounts);
        });

        assertEquals("Database error check the account id again", exception.getMessage());
    }


    @Test
    void testMapRow() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong(1)).thenReturn(105L);
        when(resultSet.getLong(2)).thenReturn(111111111111L);
        when(resultSet.getLong(3)).thenReturn(1L);
        when(resultSet.getString(4)).thenReturn("savings");
        when(resultSet.getString(5)).thenReturn("Active");
        when(resultSet.getDouble(6)).thenReturn(20000D);

        AccountsServices.AccountsMapper accountsMapper = new AccountsServices.AccountsMapper();

        Accounts accounts = accountsMapper.mapRow(resultSet, 1);

        assertEquals(105L, accounts.getAccountId());
        assertEquals(111111111111L, accounts.getAccountNumber());
        assertEquals(1L, accounts.getCustomerId());
        assertEquals("savings", accounts.getAccountType());
        assertEquals("Active", accounts.getAccountStatus());
        assertEquals(20000D, accounts.getAccountBalance());
    }
}

