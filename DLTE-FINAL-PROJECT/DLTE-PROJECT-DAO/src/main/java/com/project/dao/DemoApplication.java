package com.project.dao;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.ServerException;
import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
//       ConfigurableApplicationContext context= SpringApplication.run(DemoApplication.class, args);
//        AccountsServices debitCardServices=context.getBean(AccountsServices.class);
//        System.out.println(debitCardServices.filterByCustomerStatus(3L));
//
//
//        Accounts account = new Accounts();
//        account.setAccountNumber(1235456L);
//        account.setAccountType("Savings");
//        account.setAccountStatus("Active");
//        account.setAccountBalance(20000D);
//        AccountsServices debitCardServices=context.getBean(AccountsServices.class);
//        System.out.println(debitCardServices.UpdateAccountService(account));

    }

}
