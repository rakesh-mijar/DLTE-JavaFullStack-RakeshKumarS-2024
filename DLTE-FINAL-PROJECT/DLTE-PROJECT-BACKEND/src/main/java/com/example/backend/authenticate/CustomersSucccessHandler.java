package com.example.backend.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomersSucccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankCustomersService myBankCustomersService;

    Logger logger= LoggerFactory.getLogger(CustomersSucccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankCustomers myBankCustomers= (MyBankCustomers) authentication.getPrincipal();
        if(myBankCustomers.getCustomerStatus()!="Inactive"){
            if(myBankCustomers.getAttempts() >1){
                myBankCustomers.setAttempts(1);
                myBankCustomersService.updateAttempts(myBankCustomers);
            }
            super.setDefaultTargetUrl("http://localhost:8082/accountsrepo/accounts.wsdl");
        }else{
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}