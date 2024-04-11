package com.project.dao;

import com.project.dao.services.AccountsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
//        ConfigurableApplicationContext context= SpringApplication.run(DemoApplication.class, args);
//
//        AccountsServices debitCardServices=context.getBean(AccountsServices.class);
//        System.out.println(debitCardServices.UpdateAccountService(103L));

    }

}
