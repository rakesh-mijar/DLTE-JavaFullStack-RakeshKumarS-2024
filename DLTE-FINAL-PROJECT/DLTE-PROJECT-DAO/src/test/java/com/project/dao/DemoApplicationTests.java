//package com.project.dao;
//
//import com.project.dao.entities.Accounts;
//import com.project.dao.exceptions.CustomerNotFoundException;
//import com.project.dao.services.AccountsServices;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.security.auth.login.AccountException;
//import javax.security.auth.login.AccountNotFoundException;
//import java.rmi.ServerException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class DemoApplicationTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @InjectMocks
//    private AccountsServices accountsServices;
//
//
//    @Test
//    void testfilterStatus() throws ServerException, SQLSyntaxErrorException {
//        List<Accounts> accountList = new ArrayList<>();
//        accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
//        accountList.add(new Accounts(106L, 111111111112L, 1L, "salary", "Active", 25000D));
//        accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
//        accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Active", 400D));
//
//
//        // Stubbing jdbcTemplate.query() method to return the accountList
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(accountList);
//
//        // Stubbing jdbcTemplate.queryForObject() method to return 1
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);
//
//        // Call the method to be tested
//        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);
//
//        // Assertions
//        assertEquals(4, result.size());
//
//    }
//
//    @Test
//    void testfilterStatus2() throws ServerException, SQLSyntaxErrorException {
//        List<Accounts> accountList = new ArrayList<>();
//        accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
//        accountList.add(new Accounts(106L, 111111111112L, 1L, "salary", "Active", 25000D));
//        accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
//        accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Active", 400D));
//
//
//        // Stubbing jdbcTemplate.query() method to return the accountList
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(accountList);
//
//        // Stubbing jdbcTemplate.queryForObject() method to return 1
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);
//
//        // Call the method to be tested
//        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);
//
//        // Assertions
//        assertEquals("Inactive", result.get(0).getAccountStatus());//pass
//    }
//
//    @Test
//    void testfilterStatus3() throws ServerException, SQLSyntaxErrorException {
//        List<Accounts> accountList = new ArrayList<>();
//        accountList.add(new Accounts(105L, 111111111111L, 1L, "savings", "Inactive", 20000D));
//        accountList.add(new Accounts(106L, 111111111112L, 1L, "salary", "Active", 25000D));
//        accountList.add(new Accounts(107L, 222222222222L, 2L, "salary", "Inactive", 4500D));
//        accountList.add(new Accounts(108L, 222222222223L, 2L, "savings", "Active", 400D));
//
//
//        // Stubbing jdbcTemplate.query() method to return the accountList
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(accountList);
//
//        // Stubbing jdbcTemplate.queryForObject() method to return 1
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);
//
//        // Call the method to be tested
//        List<Accounts> result = accountsServices.filterByCustomerStatus(2L);
//
////        System.out.println(result);
//        // Assertions
//        assertEquals("Active", result.get(0).getAccountStatus());//pass
//    }
//
//
//
//
//    //test case to mock AccountNotActive exceptions for the customers
//    @Test
//    void testFilterByCustomerStatusNoActiveAccounts() {
//        // Stubbing jdbcTemplate.query() method to return an empty list
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
//                .thenReturn(new ArrayList<>());
//
//        // Stubbing jdbcTemplate.queryForObject() method to return 1
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);//returning 1 indicating that customer is existing but assert account not found
//
//        // Call the method to be tested and assert that it throws AccountNotFoundException
//        assertThrows(com.project.dao.exceptions.AccountNotFoundException.class, () -> accountsServices.filterByCustomerStatus(1L));
//    }
//
//    //test case to mock customer not found exception
//    @Test
//    void testFilterByCustomerStatusCustomerNotFound() {
//        // Stubbing jdbcTemplate.queryForObject() method to return 0
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(0);//returning 0 indicating that customer is itself not there and assert customer not found exception
//
//        // Call the method to be tested and assert that it throws CustomerNotFoundException
//        assertThrows(CustomerNotFoundException.class, () -> accountsServices.filterByCustomerStatus(1L));
//    }
//
//    //additional test cases for account not found exception
//    //uncomment--->  //return Collections.emptyList();   for running test case testFilterByCustomerStatusNoAccountsFound()
////    @Test
////    void testFilterByCustomerStatusNoAccountsFound() throws ServerException, CustomerNotFoundException, SQLSyntaxErrorException {
////        // Stubbing jdbcTemplate.query() method to return an empty list
////        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountsServices.AccountsMapper.class)))
////                .thenReturn(new ArrayList<>());
////
////        // Stubbing jdbcTemplate.queryForObject() method to return 1
////        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);
////
////        // Call the method to be tested
////        List<Accounts> result = accountsServices.filterByCustomerStatus(1L);
////
////        // Assertions
////        assertNull(result);
////        assertTrue(result.isEmpty());
////    }
//
//}
//
//
