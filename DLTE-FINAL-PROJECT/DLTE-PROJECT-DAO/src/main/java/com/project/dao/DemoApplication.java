package com.project.dao;

import com.project.dao.entities.Accounts;
import com.project.dao.services.AccountsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.rmi.ServerException;
import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) throws ServerException, SQLSyntaxErrorException {

        SpringApplication.run(DemoApplication.class, args);
//        ConfigurableApplicationContext context= SpringApplication.run(DemoApplication.class, args);
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
