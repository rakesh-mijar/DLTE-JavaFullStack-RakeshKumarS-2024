package com.example.backend;





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

    private MockMvc mockMvc;


    @Test
    public void testSave() throws Exception {
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        lenient().when(repository.signingUp(ArgumentMatchers.any(MyBankCustomers.class))).thenReturn(customer);

        lenient().when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testSaveProfile() {
        MyBankCustomers customer = new MyBankCustomers();
        customer.setUsername("testUser");
        customer.setPassword("testPassword");

        Mockito.when(repository.signingUp(ArgumentMatchers.any(MyBankCustomers.class))).thenReturn(customer);

        Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("encodedPassword");

        MyBankCustomers savedCustomer = controller.save(customer);

        Mockito.verify(repository).signingUp(customer);

        Mockito.verify(passwordEncoder).encode("testPassword");

        assertEquals(customer.getUsername(), savedCustomer.getUsername());
        assertEquals("encodedPassword", savedCustomer.getPassword()); // Assuming password was encoded correctly
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}