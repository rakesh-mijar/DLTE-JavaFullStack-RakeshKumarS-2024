package com.example.backend.authenticate;

import com.project.dao.security.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomersSucccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankCustomersService myBankCustomersService;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(CustomersSucccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankCustomers myBankCustomers= (MyBankCustomers) authentication.getPrincipal();
        System.out.println(authentication.getPrincipal());

        if(!myBankCustomers.getCustomerStatus().equals("Inactive")){
            if(myBankCustomers.getAttempts() >1){
                myBankCustomers.setAttempts(1);
                myBankCustomersService.updateAttempts(myBankCustomers);
            }
            super.setDefaultTargetUrl("/customer/dashboard/");
        }else{
            logger.warn(resourceBundle.getString("max.reached"));
            //super.setDefaultTargetUrl("/login");
            super.setDefaultTargetUrl("/customer/?error="+ resourceBundle.getString("no.customer"));

        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
