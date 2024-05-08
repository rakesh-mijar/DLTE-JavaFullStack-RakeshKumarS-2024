package com.project.dao;

import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.remotes.CustomerRepository;
import com.project.dao.security.MyBankCustomersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private MyBankCustomersService myBankCustomersService;

    MyBankCustomers userDetails;
    @BeforeEach
    void setUp(){
        userDetails = new MyBankCustomers();
    }
    @Test
    public void testIsAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(userDetails.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testGetMaxAttempt() {
        int expectedMaxAttempt = 3;
        assertEquals(expectedMaxAttempt, userDetails.getMaxAttempt());
    }

    @Test
    public void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertNull(authorities); // Since the method returns null
    }
    @Test
    void testSigningUp() {
        MyBankCustomers myBankCustomers = new MyBankCustomers();
        myBankCustomers.setCustomerName("Mahesh");
        myBankCustomers.setCustomerAddress("Moodabidri");
        myBankCustomers.setCustomerStatus("Active");
        myBankCustomers.setCustomerContact(1234567890L);
        myBankCustomers.setUsername("mahesh");
        myBankCustomers.setPassword("mahesh");

        lenient().when(customerRepository.signingUp(any(MyBankCustomers.class))).thenReturn(myBankCustomers);

        MyBankCustomers savedCustomer = myBankCustomersService.signingUp(myBankCustomers);
        // Assertions
        assertNotNull(savedCustomer);
        assertEquals("mahesh", savedCustomer.getUsername());
    }

    @Test
    void testFindByUsername() {
        List<MyBankCustomers> customerList = new ArrayList<>();
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("mahesh");
        customerList.add(customer);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenReturn(customerList);

        MyBankCustomers foundCustomer = myBankCustomersService.findByUsername("mahesh");

        assertEquals("mahesh", foundCustomer.getUsername());
    }

    @Test
    void testFindByAccountNumber() {
        List<Accounts> accountList = new ArrayList<>();
        Accounts account = new Accounts();
        account.setCustomerId(1L);
        accountList.add(account);

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(accountList);

        List<Accounts> foundAccounts = myBankCustomersService.findByAccountNumber(1L);

        System.out.println(foundAccounts);
        assertEquals(1, foundAccounts.size());
        assertEquals(1L, foundAccounts.get(0).getCustomerId());
    }

    @Test
    void testFindByAccountNumber2() {
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(Collections.emptyList());

        List<Accounts> foundAccounts = myBankCustomersService.findByAccountNumber(2L);

        assertTrue(foundAccounts.isEmpty());
    }

    @Test
    void testLoadUserByUsername() {
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("mahesh");


        List<MyBankCustomers> customerList = new ArrayList<>();
        customerList.add(customer);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(customerList);

        UserDetails userDetails = myBankCustomersService.loadUserByUsername("mahesh");

        assertNotNull(userDetails);
        assertEquals("mahesh", userDetails.getUsername());
    }

    @Test
    void testUpdateStatus(){
        MyBankCustomers myBankCustomers = new MyBankCustomers();
        myBankCustomers.setUsername("mahesh");
        myBankCustomers.setCustomerStatus("active");

        myBankCustomersService.updateStatus(myBankCustomers);

        verify(jdbcTemplate).update("update mybank_app_customer set customer_status = 'Inactive' where username = ?", "mahesh");
    }

    @Test
    void testUpdateAttempts() {
        MyBankCustomers myBankCustomers = new MyBankCustomers();
        myBankCustomers.setUsername("johndoe");
        myBankCustomers.setAttempts(1);


        myBankCustomersService.updateAttempts(myBankCustomers);

        verify(jdbcTemplate).update("update mybank_app_customer set attempts = ? where username = ?", 1, "johndoe");
       // System.out.println(myBankCustomers.getAttempts());
    }

    @Test

    public void testPasswordMatch() {

        MyBankCustomersService myBankUsersServices = mock(MyBankCustomersService.class);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = "testUser";
        String rawPassword = "password123";
        String encodedPassword =passwordEncoder.encode(rawPassword);
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))
                .thenReturn(customer);
        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);
        String enteredPassword="password123";
        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));

    }
}