package com.example.backend;

//
//import com.example.backend.authenticate.MyBankCustomersAPI;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.project.dao.entities.MyBankCustomers;
//import com.project.dao.security.MyBankCustomersService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//
//import javax.validation.constraints.AssertTrue;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.lenient;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(MyBankCustomersAPI.class)
//@AutoConfigureMockMvc
////@WebMvcTest
//class ApiTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MyBankCustomersService customersService;
//
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//
//
//    @InjectMocks
//    private MyBankCustomersAPI myBankCustomersAPI;
//
////    @Test
////    void testSave_SuccessfulRegistration() throws Exception {
////        MyBankCustomers customer = new MyBankCustomers();
////        customer.setUsername("testuser");
////        customer.setPassword("testpassword");
////
////        when(customersService.signingUp(any(MyBankCustomers.class))).thenReturn(customer);
////
////        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }"))
////                .andDo(print())
////                .andExpect(status().isOk());
////    }
//
//    @Test
//    @WithMockUser(username = "user")
//    void testSave1() throws Exception {
//
//        MyBankCustomers customer = new MyBankCustomers();
//        customer.setUsername("user");
//        customer.setPassword("password");
//
//        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedpassword");
//        when(customersService.signingUp(any(MyBankCustomers.class))).thenReturn(customer);
//
//
//        mockMvc.perform(post("/profile/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"username\": \"user\", \"password\": \"encodedpassword\" }"))
//                .andDo(print())
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(username = "rakesh")
//    public void testRestDepositAvailed() throws Exception {
//        String request = "{\n" +
//                "    \"customerName\":\"rakesh\",\n" +
//                "    \"customerAddress\":\"as d s\",\n" +
//                "    \"customerStatus\":\"active\",\n" +
//                "    \"customerContact\":9809876765,\n" +
//                "    \"username\":\"kunda\",\n" +
//                "    \"password\":\"shreya\"\n" +
//                "\n" +
//                "}";
//        mockMvc.perform(post("/profile/register").contentType(MediaType.APPLICATION_JSON).content(request))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    void testValidationExceptionHandler() throws Exception {
//        // Perform the POST request to /profile/register with invalid input
//        mockMvc.perform(post("/profile/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"username\": \"\", \"password\": \"\" }"))
//                .andDo(print()) ;// Print the request and response
//                //.andExpect(status().isBadRequest());
//    }
//
//
//
//}


import com.example.backend.authenticate.CustomersFailureHandler;
import com.example.backend.authenticate.CustomersSucccessHandler;
import com.example.backend.authenticate.MyBankCustomersAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.remotes.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class ApiTest {
    @Mock
    private CustomerRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MyBankCustomersAPI controller;
    @InjectMocks
    private CustomersFailureHandler failureHandler;
    @InjectMocks
    private CustomersSucccessHandler successHandler;

    private MockMvc mockMvc;

    @Test
    public void testSave() throws Exception {
        // Mock customer data
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        // Mock the repository response
        lenient().when(repository.signingUp(ArgumentMatchers.any(MyBankCustomers.class))).thenReturn(customer);

        // Mock the password encoder response
        lenient().when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        // Set up mockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk());
               //.andExpect(MockMvcResultMatchers.content().json(asJsonString(customer)));
    }
    @Test
    public void testSaveProfile() {
        // Mock customer data
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        // Mock repository response
        Mockito.when(repository.signingUp(ArgumentMatchers.any(MyBankCustomers.class))).thenReturn(customer);

        // Mock password encoder response
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        // Perform save operation
        MyBankCustomers savedCustomer = controller.save(customer);

        // Verify that repository method is called with the correct argument
        Mockito.verify(repository).signingUp(customer);

        // Verify that password encoder is called with the correct argument
        Mockito.verify(passwordEncoder).encode("testPassword");

        // Verify the returned customer object
        assertEquals(customer.getUsername(), savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming password was encoded correctly
    }

//
    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void onAuthenticationFailure_ValidCustomer() throws IOException, ServletException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        String username = "testUser";
//        Customer customer = new Customer();
//        customer.setUsername(username);
//        customer.setAttempts(2);
//
//
//        when(repository.findByUserName(username)).thenReturn(customer);
//
//        AuthenticationException exception = new LockedException("Invalid credentials");
//
//        failureHandler.onAuthenticationFailure(request, response, exception);
//
//        verify(repository, times(1)).updateAttempts(any(Customer.class));
//        verify(repository, never()).updateStatus(any(Customer.class));
//        verify(response).sendRedirect("/ui/?error=1 Invalid credentials");
//    }
//
//    @Test
//    void onAuthenticationFailure_MaxAttemptsReached() throws IOException, ServletException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        String username = "testUser";
//        Customer customer = new Customer();
//        customer.setUsername(username);
//        customer.setAttempts(3); // Max attempts reached
//
//
//        when(repository.findByUserName(username)).thenReturn(customer);
//
//        AuthenticationException exception = new LockedException("Invalid credentials");
//
//        failureHandler.onAuthenticationFailure(request, response, exception);
//
//        verify(repository, never()).updateAttempts(any(Customer.class));
//        verify(repository, times(1)).updateStatus(any(Customer.class));
//        verify(response).sendRedirect("/ui/?error=Invalid credentials");
//    }
//
//    @Test
//    void onAuthenticationFailure_NullCustomer() throws IOException, ServletException {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        String username = "testUser";
//
//        when(repository.findByUserName(anyString())).thenThrow(new CustomerException("Customer not found")); // Stubbing to handle any string argument
//
//        AuthenticationException exception = new LockedException("Invalid credentials");
//
//        failureHandler.onAuthenticationFailure(request, response, exception);
//
//        verify(repository, never()).updateAttempts(any(Customer.class));
//        verify(repository, never()).updateStatus(any(Customer.class));
//        verify(response).sendRedirect("/ui/?error=Customer not found");
//    }
//
//
//
//    @Test
//    void onAuthenticationSuccess_CustomerClosed() throws ServletException, IOException {
//        // Mock authentication
//        Customer customer = new Customer();
//        customer.setCustomerStatus("closed");
//        Authentication authentication = new UsernamePasswordAuthenticationToken(customer, null);
//
//        // Mock request and response
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        // Call the method
//        successHandler.onAuthenticationSuccess(request, response, authentication);
//
//
//    }

}