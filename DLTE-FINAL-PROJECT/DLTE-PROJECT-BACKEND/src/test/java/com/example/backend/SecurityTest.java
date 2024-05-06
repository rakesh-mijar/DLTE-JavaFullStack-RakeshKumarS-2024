package com.example.backend;

import com.example.backend.authenticate.CustomersFailureHandler;
import com.example.backend.authenticate.CustomersSucccessHandler;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.remotes.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SecurityTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomersSucccessHandler successHandler;

    @InjectMocks
    private CustomersFailureHandler failureHandler;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void testOnAuthenticationSuccess_ActiveCustomer() throws Exception {
        Authentication authentication = mock(Authentication.class);
        MyBankCustomers customer = new MyBankCustomers();
        customer.setCustomerStatus("Active");
        customer.setCustomerId(1L);
        customer.setCustomerName("Ram");
        customer.setCustomerAddress("Bangalore");
        customer.setCustomerContact(9898987678L);
        customer.setUsername("mohan");
        customer.setPassword("mohan");
        customer.setAttempts(2);
        when(authentication.getPrincipal()).thenReturn(customer);

//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();

        successHandler.onAuthenticationSuccess(request, response, authentication);

        assertEquals("/customer/dashboard/", response.getRedirectedUrl());
        assert response.getRedirectedUrl().equals("/customer/dashboard/");

    }

    @Test
    public void testOnAuthenticationSuccess_InactiveCustomer() throws Exception {
        Authentication authentication = mock(Authentication.class);
        MyBankCustomers customer = new MyBankCustomers();
        customer.setCustomerStatus("Inactive");
        customer.setCustomerId(1L);
        customer.setCustomerName("Ram");
        customer.setCustomerAddress("Bangalore");
        customer.setCustomerContact(9898987678L);
        customer.setUsername("mohan");
        customer.setPassword("mohan");
        customer.setAttempts(2);
        when(authentication.getPrincipal()).thenReturn(customer);


//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();

        successHandler.onAuthenticationSuccess(request, response, authentication);
        System.out.println("Redirected URL: " + response.getRedirectedUrl());
        assertEquals("/customer/?error=User not exists", response.getRedirectedUrl());

        assert response.getRedirectedUrl().equals("/customer/?error=User not exists");
    }

    ResourceBundle resourceBundle = ResourceBundle.getBundle("accounts");

    @Test
    public void testOnAuthenticationFailure_AttemptsLessThanMax() throws Exception {
        // Mocking behavior
        String username = "testuser";
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername(username);
        customer.setCustomerStatus("Active");
        customer.setAttempts(2); // Less than max attempts
        customer.setCustomerId(1L);
        customer.setCustomerName("Ram");
        customer.setCustomerAddress("Bangalore");
        customer.setCustomerContact(9898987678L);
        customer.setPassword("mohan");

        when(customerRepository.findByUsername(username)).thenReturn(customer);

        // Simulating authentication failure
        int maxAttempts = 3;
        int remainingAttempts = maxAttempts - customer.getAttempts();
        String exceptionMessage = remainingAttempts + " Attempts are taken";
        AuthenticationException exception = new LockedException(exceptionMessage);

        // Calling the method to test
        failureHandler.onAuthenticationFailure(request, response, exception);
        //System.out.println("Redirected URL: " + response.getRedirectedUrl());
        // Verifying behavior
//        verify(customerRepository, times(1)).updateAttempts(customer);
//        verify(response, times(1)).sendRedirect("/customer/?error=User not exists");
        String expectedRedirectUrl = "/customer/?error=org.springframework.security.authentication.LockedException: User not exists";
        assert response.getRedirectedUrl().equals(expectedRedirectUrl);
    }



}
