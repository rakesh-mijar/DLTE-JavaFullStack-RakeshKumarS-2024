package com.example.backend;


import com.example.backend.mvc.MyBankWebController;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MyBankWebController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyBankCustomersService myBankCustomersService;

    @Test
    @WithMockUser(username = "rakesh")
    void testLanding() throws Exception {
        mockMvc.perform(get("/customer/"))
                .andExpect(status().isOk());
               // .andExpect(view().name("index"));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testHomePage() throws Exception {
        mockMvc.perform(get("/customer/dashboard"))
                .andExpect(status().isOk());
               // .andExpect(view().name("dashboard"));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testView() throws Exception {
        mockMvc.perform(get("/customer/view"))
                .andExpect(status().isOk());
                //.andExpect(view().name("viewAccounts"));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testUpdate() throws Exception {
        mockMvc.perform(get("/customer/update"))
                .andExpect(status().isOk());
                //.andExpect(view().name("updateAccounts"));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testError() throws Exception {
        mockMvc.perform(get("/customer/error"))
                .andExpect(status().isOk());
                //.andExpect(view().name("error"));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testCustomerName_Success() throws Exception {
        String username = "testuser";
        String customerName = "John Doe";

        MyBankCustomers customer = new MyBankCustomers();
        customer.setCustomerName(customerName);

        when(myBankCustomersService.findByUsername(username)).thenReturn(customer);

        mockMvc.perform(get("/customer/name"))
                .andExpect(status().isOk());
                //.andExpect(content().string(customerName));
    }

    @Test
    @WithMockUser(username = "rakesh")
    void testCustomerName_Error() throws Exception {
        String errorMessage = "Error: Customer Name not available";

        when(myBankCustomersService.findByUsername(any())).thenThrow(NullPointerException.class);

        mockMvc.perform(get("/customer/name"))
                .andExpect(status().isOk());
                //.andExpect(content().string(errorMessage));
    }
}