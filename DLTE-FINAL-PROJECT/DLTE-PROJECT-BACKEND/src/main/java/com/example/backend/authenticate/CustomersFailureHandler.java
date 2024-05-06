package com.example.backend.authenticate;

import com.project.dao.entities.MyBankCustomers;
import com.project.dao.remotes.CustomerRepository;
import com.project.dao.security.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomersFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    @Autowired
    CustomerRepository myBankCustomersService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
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
                   logger.warn(resourceBundle.getString("invalid.credits"));
                   int leftAttempts=4;
                   exception = new LockedException(leftAttempts-myBankCustomers.getAttempts() + " " + resourceBundle.getString("customer.password.attempts"));
                   //exception=new LockedException("Attempts are taken");
                   String err = myBankCustomers.getAttempts()+" "+exception.getMessage();
                   logger.warn(err);
                   super.setDefaultFailureUrl("/customer/?error="+err);
               }
               else{
                   myBankCustomersService.updateStatus(myBankCustomers);
                   exception=new LockedException(resourceBundle.getString("max.reached"));
                   super.setDefaultFailureUrl("/customer/?error="+exception.getMessage());
               }
           }
           else{
               logger.warn(resourceBundle.getString("account.suspended"));
               exception=new LockedException(resourceBundle.getString("max.reached"));
               super.setDefaultFailureUrl("/customer/?error="+exception.getMessage());
           }
       }else{
           logger.warn(resourceBundle.getString("no.customer"));
           exception=new LockedException(resourceBundle.getString("no.customer"));
           super.setDefaultFailureUrl("/customer/?error="+exception);
       }
//        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }
}
