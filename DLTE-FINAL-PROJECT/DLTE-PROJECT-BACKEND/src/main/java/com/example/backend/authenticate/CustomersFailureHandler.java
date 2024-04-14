package com.example.backend.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomersFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    @Autowired
    MyBankCustomersService myBankCustomersService;

    Logger logger= LoggerFactory.getLogger(CustomersFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       String username = request.getParameter("username");
       MyBankCustomers myBankCustomers=myBankCustomersService.findByUsername(username);
       if(myBankCustomers!=null){
           if(!myBankCustomers.getCustomerStatus().equals("Inactive")){
               if(myBankCustomers.getAttempts()<myBankCustomers.getMaxAttempt()){
                   myBankCustomers.setAttempts(myBankCustomers.getAttempts()+1);
                   myBankCustomersService.updateAttempts(myBankCustomers);
                   logger.warn("Invalid credentials and attempts taken");
                   exception=new LockedException("Attempts are taken");
               }
               else{
                   myBankCustomersService.updateStatus(myBankCustomers);
                   exception=new LockedException("Max Attempts reached account is suspended");
               }
           }
           else{
               logger.warn("Account suspended contact admin to redeem");
           }
       }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
