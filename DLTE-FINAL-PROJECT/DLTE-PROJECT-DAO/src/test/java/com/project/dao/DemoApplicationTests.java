package com.project.dao;

import com.project.dao.entities.Accounts;
import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AccountsServices accountsServices;


@Test
void testfilterStatus()  {
    List<Accounts> accountList = new ArrayList<>();
    accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
    accountList.add(new Accounts(106L, 111111111112L, 1L, "salary", "Active", 25000D));
    accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
    accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Active", 400D));


    // Stubbing jdbcTemplate.query() method to return the accountList
    when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
            .thenReturn(accountList);

    // Stubbing jdbcTemplate.queryForObject() method to return 1
    when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);

    // Call the method to be tested
    List<Accounts> result = accountsServices.filterByCustomerStatus(1L);

    // Assertions
    assertEquals(4, result.size());

}
    @Test
    void testfilterStatus2(){
        List<Accounts> accountList = new ArrayList<>();
        accountList.add(new Accounts(105L,111111111111L, 1L, "savings",  "Inactive",20000D ));
        accountList.add(new Accounts(106L,111111111112L, 1L, "salary",  "Active",25000D ));
        accountList.add(new Accounts(107L,222222222222L, 2L, "salary",  "Inactive",4500D));
        accountList.add(new Accounts(108L,222222222223L, 2L, "savings",  "Active",400D));


        // Stubbing jdbcTemplate.query() method to return the accountList
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
                .thenReturn(accountList);

        // Stubbing jdbcTemplate.queryForObject() method to return 1
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);

        // Call the method to be tested
        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);

        // Assertions
        assertEquals("Inactive", result.get(0).getAccountStatus());//pass
    }
    @Test
    void testfilterStatus3(){
        List<Accounts> accountList = new ArrayList<>();
        accountList.add(new Accounts(105L,111111111111L, 1L, "savings",  "Inactive",20000D ));
        accountList.add(new Accounts(106L,111111111112L, 1L, "salary",  "Active",25000D ));
        accountList.add(new Accounts(107L,222222222222L, 2L, "salary",  "Inactive",4500D));
        accountList.add(new Accounts(108L,222222222223L, 2L, "savings",  "Active",400D));


        // Stubbing jdbcTemplate.query() method to return the accountList
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
                .thenReturn(accountList);

        // Stubbing jdbcTemplate.queryForObject() method to return 1
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);

        // Call the method to be tested
        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);

        // Assertions
        assertEquals("Inactive", result.get(0).getAccountStatus());//pass
    }

}
