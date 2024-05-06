package com.project.dao;

import com.project.dao.services.AccountsServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(classes = DemoApplication.class)
class DemoTest {

    @Autowired
    private AccountsServices accountsServices;

    @Test
    public void contextLoads() {
        assertNotNull(accountsServices);
    }
}