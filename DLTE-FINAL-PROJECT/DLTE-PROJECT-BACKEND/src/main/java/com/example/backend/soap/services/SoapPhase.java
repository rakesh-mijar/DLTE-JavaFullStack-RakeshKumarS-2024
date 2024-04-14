package com.example.backend.soap.services;

import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.exceptions.ServerException;
import com.project.dao.remotes.AccountRepository;
import com.project.dao.services.AccountsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private final String url="http://accounts.services";

    private Logger logger= LoggerFactory.getLogger(SoapPhase.class);

    //http://localhost:8082/accountsrepo/accounts.wsdl

    @Autowired
    public AccountRepository accountsServices;

    //lambda expression to filter the account details of customer based on customer id
    @PayloadRoot(namespace = url, localPart = "filterByStatusRequest")
    @ResponsePayload
    public FilterByStatusResponse filterByStatus(@RequestPayload FilterByStatusRequest filterByStatusRequest) throws CustomerNotFoundException {
        FilterByStatusResponse filterByStatusResponse = new FilterByStatusResponse();
        ServiceStatus serviceStatus = new ServiceStatus();


        try {
            List<com.project.dao.entities.Accounts> fromDao = accountsServices.filterByCustomerStatus(filterByStatusRequest.getCustomerId());

            //lambda expression to filter the account details of customer based on customer id
            List<services.accounts.Accounts> actualAccounts = fromDao.stream()
                    .map(account -> {
                        services.accounts.Accounts currentAccount = new services.accounts.Accounts();
                        BeanUtils.copyProperties(account, currentAccount);
                        return currentAccount;
                    })
                    .collect(Collectors.toList());

            logger.info("success.fetch");
            serviceStatus.setStatus(HttpServletResponse.SC_OK );
            serviceStatus.setMessage(resourceBundle.getString("account.fetch.success"));
            filterByStatusResponse.setServiceStatus(serviceStatus);
            filterByStatusResponse.getAccounts().addAll(actualAccounts);

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


//    //method reference to filter the account details of customer based on customer id
//    @PayloadRoot(namespace = url, localPart = "filterByStatusRequest")
//    @ResponsePayload
//    public FilterByStatusResponse filterByStatus(@RequestPayload FilterByStatusRequest filterByStatusRequest) throws CustomerNotFoundException {
//        FilterByStatusResponse filterByStatusResponse = new FilterByStatusResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//
//
//        try {
//            List<com.project.dao.entities.Accounts> fromDao = accountsServices.filterByCustomerStatus(filterByStatusRequest.getCustomerId());
//
//            List<Accounts> actualAccounts = accountsServices.filterByCustomerStatus(filterByStatusRequest.getCustomerId()).stream()
//                    .map(this::convertToAccounts) // Method reference
//                    .collect(Collectors.toList());
//
//            logger.info("success.fetch");
//            serviceStatus.setStatus(HttpServletResponse.SC_OK );
//            serviceStatus.setMessage(resourceBundle.getString("account.fetch.success"));
//            filterByStatusResponse.setServiceStatus(serviceStatus);
//            filterByStatusResponse.getAccounts().addAll(actualAccounts);
//
//        } catch ( CustomerNotFoundException e) {
//            logger.warn("failure.fetch");
//            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);//204
//            serviceStatus.setMessage(e.getMessage());
//            filterByStatusResponse.setServiceStatus(serviceStatus);
//        }catch ( AccountNotFoundException e) {
//            logger.warn("failure.fetch");
//            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);//404
//            serviceStatus.setMessage(e.getMessage());
//            filterByStatusResponse.setServiceStatus(serviceStatus);
//        }catch (ServerException | java.rmi.ServerException e) {
//            logger.warn("failure.fetch");
//            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500
//            serviceStatus.setMessage(e.getMessage());
//            filterByStatusResponse.setServiceStatus(serviceStatus);
//        } catch (SQLSyntaxErrorException e) {
//            e.printStackTrace();
//        }
//
//        return filterByStatusResponse;
//    }
//
//    private Accounts convertToAccounts(com.project.dao.entities.Accounts account) {
//        Accounts currentAccount = new Accounts();
//        BeanUtils.copyProperties(account, currentAccount);
//        return currentAccount;
//    }



    //filter the account details of customer based on customer id without any java 8 features
//    @PayloadRoot(namespace = url, localPart = "filterByIdRequest")
//    @ResponsePayload
//    public FilterByIdResponse filterByIdRequest(@RequestPayload FilterByIdRequest filterByIdRequest) {
//        FilterByIdResponse filterByIdResponse = new FilterByIdResponse();
//        ServiceStatus serviceStatus = new ServiceStatus();
//
//        com.project.dao.entities.Customers fromDao = accountsServices.filterById(filterByIdRequest.getCustomerId());
//
//        if (fromDao != null) {
//            services.accounts.Customers actualCustomer = new services.accounts.Customers();
//            BeanUtils.copyProperties(fromDao, actualCustomer);
//
//            serviceStatus.setStatus("SUCCESS");
//            serviceStatus.setMessage("customers.fetch");
//
//            filterByIdResponse.setServiceStatus(serviceStatus);
//            filterByIdResponse.getCustomers().add(actualCustomer);
//        } else {
//            serviceStatus.setStatus("FAILURE");
//            serviceStatus.setMessage("customer.fetch.null");
//
//            filterByIdResponse.setServiceStatus(serviceStatus);
//        }
//
//        return filterByIdResponse;
//    }
}

