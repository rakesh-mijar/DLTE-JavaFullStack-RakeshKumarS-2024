package com.example.backend;


import com.example.backend.authenticate.MyBankCustomersAPI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.AssertTrue;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(MyBankCustomersAPI.class)
@AutoConfigureMockMvc
@WebMvcTest
class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyBankCustomersService customersService;

    @MockBean
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private MyBankCustomersAPI myBankCustomersAPI;

//    @Test
//    void testSave_SuccessfulRegistration() throws Exception {
//        MyBankCustomers customer = new MyBankCustomers();
//        customer.setUsername("testuser");
//        customer.setPassword("testpassword");
//
//        when(customersService.signingUp(any(MyBankCustomers.class))).thenReturn(customer);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "user")
    void testSave1() throws Exception {

        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("user");
        customer.setPassword("password");

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedpassword");
        when(customersService.signingUp(any(MyBankCustomers.class))).thenReturn(customer);


        mockMvc.perform(post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user\", \"password\": \"encodedpassword\" }"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "rakesh")
    public void testRestDepositAvailed() throws Exception {
        String request = "{\n" +
                "    \"customerName\":\"rakesh\",\n" +
                "    \"customerAddress\":\"as d s\",\n" +
                "    \"customerStatus\":\"active\",\n" +
                "    \"customerContact\":9809876765,\n" +
                "    \"username\":\"kunda\",\n" +
                "    \"password\":\"shreya\"\n" +
                "\n" +
                "}";
        mockMvc.perform(post("/profile/register").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isForbidden());
    }

    @Test
    void testValidationExceptionHandler() throws Exception {
        // Perform the POST request to /profile/register with invalid input
        mockMvc.perform(post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"\", \"password\": \"\" }"))
                .andDo(print()) ;// Print the request and response
                //.andExpect(status().isBadRequest());
    }
    @Mock
    private MethodArgumentNotValidException exception;

    @Test
    void testHandleValidationExceptions() throws Exception {
        // Prepare the exception
        FieldError fieldError = new FieldError("fieldName", "errorMessage", "defaultMessage");
        lenient().when(exception.getBindingResult().getAllErrors()).thenReturn(Arrays.asList(fieldError));
        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new MyBankCustomers())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldName").value("errorMessage"));
    }

}