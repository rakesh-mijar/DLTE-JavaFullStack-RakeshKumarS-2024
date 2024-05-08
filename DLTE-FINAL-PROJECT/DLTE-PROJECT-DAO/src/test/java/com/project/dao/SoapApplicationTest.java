package com.project.dao;

import com.project.dao.entities.Accounts;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.springframework.jdbc.core.JdbcTemplate;


import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SoapApplicationTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AccountsServices accountsServices;

    List<Accounts> accountList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        accountList= new ArrayList<>();
        accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
        accountList.add(new Accounts(106L, 111111111112L, 1L, "salary", "Active", 25000D));
        accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
        accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Active", 400D));

    }

    @Test
    void testFilterByCustomerStatus() throws ServerException {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
                .thenAnswer(invoc -> {
//                    Object[] args = invocation.getArguments();
                    Long customerId = 1L;//Long.parseLong(args[0].toString());
                    return accountList.stream()
                            .filter(account -> account.getCustomerId().equals(customerId) && "Active".equals(account.getAccountStatus()))
                            .collect(Collectors.toList());
                });

        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);

        System.out.println(result);
        assertEquals(1, result.size());
        assertEquals("salary", result.get(0).getAccountType());
        assertEquals("Active", result.get(0).getAccountStatus());
    }


    @Test
    void testFilterByCustomerStatus_NoActiveAccounts()  {
        List<Accounts> accountList = new ArrayList<>();
        accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
        accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
        accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Inactive", 400D));


        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
                .thenAnswer(invocation -> {
                    Long customerId = 1L;//(Long) invocation.getArguments()[0];
                    return accountList.stream()
                            .filter(account -> account.getCustomerId().equals(customerId) && "Active".equals(account.getAccountStatus()))
                            .collect(Collectors.toList());
                });


        assertThrows(AccountNotFoundException.class, () -> accountsServices.filterByCustomerStatus(1L));
    }



    @Test
    void testFilterByCustomerStatusNoActiveAccounts() {
        List<Accounts> inactiveAccounts = new ArrayList<>();
        inactiveAccounts.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
        inactiveAccounts.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
                .thenReturn(inactiveAccounts);

        //System.out.println(accountsServices.filterByCustomerStatus(1L));
        assertThrows(AccountNotFoundException.class, () -> accountsServices.filterByCustomerStatus(1L));
    }


}


