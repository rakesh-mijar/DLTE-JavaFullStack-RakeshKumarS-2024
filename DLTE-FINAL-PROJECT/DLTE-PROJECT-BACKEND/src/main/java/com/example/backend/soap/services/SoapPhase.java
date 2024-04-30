package com.example.backend.soap.services;

import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.exceptions.ServerException;
import com.project.dao.remotes.AccountRepository;
import com.project.dao.security.MyBankCustomers;
import com.project.dao.security.MyBankCustomersService;
import com.project.dao.services.AccountsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.accounts.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Endpoint
@ComponentScan("com.project.dao")
public class SoapPhase {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private final String url="http://accounts.services";

    private Logger logger= LoggerFactory.getLogger(SoapPhase.class);

    //http://localhost:8082/accountsrepo/accounts.wsdl
    @Autowired
    private MyBankCustomersService myBankCustomersService;

    @Autowired
    public AccountRepository accountsServices;

    //lambda expression to filter the account details of customer based on customer id
    @PayloadRoot(namespace = url, localPart = "filterByStatusRequest")
    @ResponsePayload
    public FilterByStatusResponse filterByStatus(@RequestPayload FilterByStatusRequest filterByStatusRequest) throws CustomerNotFoundException {

        FilterByStatusResponse filterByStatusResponse = new FilterByStatusResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        MyBankCustomers myBankCustomer=myBankCustomersService.findByUsername(username);


        try {
            //if(myBankCustomers.getCustomerId()==filterByStatusRequest.getCustomerId()) {
                List<com.project.dao.entities.Accounts> fromDao = accountsServices.filterByCustomerStatus(myBankCustomer.getCustomerId());

                //lambda expression to filter the account details of customer based on customer id
                List<services.accounts.Accounts> actualAccounts = fromDao.stream()
                        .map(account -> {
                            services.accounts.Accounts currentAccount = new services.accounts.Accounts();
                            BeanUtils.copyProperties(account, currentAccount);
                            return currentAccount;
                        })
                        .collect(Collectors.toList());

                logger.info("success.fetch");
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                serviceStatus.setMessage(resourceBundle.getString("account.fetch.success"));
                filterByStatusResponse.setServiceStatus(serviceStatus);
                filterByStatusResponse.getAccounts().addAll(actualAccounts);
           // }
//            else{
//                logger.info("failure.fetch");
//                serviceStatus.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                serviceStatus.setMessage(resourceBundle.getString("authorization.error"));
//                filterByStatusResponse.setServiceStatus(serviceStatus);
//            }
        } catch ( CustomerNotFoundException e) {
            logger.warn("failure.fetch");
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);//204
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }catch ( AccountNotFoundException e) {
            logger.warn("failure.fetch");
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);//404
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }catch (ServerException | java.rmi.ServerException e) {
            logger.warn("failure.fetch");
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        } catch (SQLSyntaxErrorException e) {
            e.printStackTrace();
        }

        return filterByStatusResponse;
    }
    //    List<Account> received = accountService.filterByStatus(filterByStatusRequest.getCustomerId());
//    List<services.account.Account> returnAccount = new ArrayList<>();
//
//for (Account account : received) {
//        services.account.Account currentAccount = new services.account.Account();
//        BeanUtils.copyProperties(account, currentAccount);
//        returnAccount.add(currentAccount);
//        }
}

