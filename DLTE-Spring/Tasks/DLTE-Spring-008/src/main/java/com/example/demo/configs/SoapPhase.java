package com.example.demo.configs;


import com.example.demo.dao.Transactions;
import com.example.demo.dao.TransactionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transactions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url="http://transactions.services";

    @Autowired
    private TransactionsService transactionService;

    @PayloadRoot(namespace = url,localPart="newTransactionRequest")
    @ResponsePayload
    public NewTransactionResponse addNewLoan(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse=new NewTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        services.transactions.Transactions actual=newTransactionRequest.getTransactions();
        Date date=newTransactionRequest.getTransactions().getTransactionDate().toGregorianCalendar().getTime();
        Transactions daoTransaction=new Transactions();
        daoTransaction.setTransactionDate(date);
        BeanUtils.copyProperties(actual,daoTransaction);

        daoTransaction=transactionService.publishNewTransactions(daoTransaction);
        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            BeanUtils.copyProperties(daoTransaction,actual);
            newTransactionResponse.setTransactions(actual);
            serviceStatus.setMessage(actual.getTransactionId()+ " has inserted");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(actual.getTransactionId()+" hasn't inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        return newTransactionResponse;
    }

    @PayloadRoot(namespace=url,localPart = "filterBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterSender(@RequestPayload FilterBySenderRequest filterBySenderRequest){
        FilterBySenderResponse filterBySenderResponse=new FilterBySenderResponse();
        List<services.transactions.Transactions> returnTransactions=new ArrayList<>();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Transactions> received =transactionService.findBySender(filterBySenderRequest.getSender());

        Iterator<Transactions> iterator=received.iterator();
        while(iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction details fetched based on sender name");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransactions().addAll(returnTransactions);

        return filterBySenderResponse;
    }

   @PayloadRoot(namespace=url,localPart = "filterByReceiverRequest")
   @ResponsePayload
    public FilterByReceiverResponse filterReceiver(@RequestPayload FilterByReceiverRequest filterByReceiverRequest){
        FilterByReceiverResponse filterByReceiverResponse=new FilterByReceiverResponse();
        List<services.transactions.Transactions> returnTransactions=new ArrayList<>();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Transactions> received =transactionService.findByReceiver(filterByReceiverRequest.getReceiver());

        Iterator<Transactions> iterator=received.iterator();
        while(iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction details fetched based on sender name");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransactions().addAll(returnTransactions);

        return filterByReceiverResponse;
    }

    @PayloadRoot(namespace = url,localPart = "removeTransactionRequest")
    @ResponsePayload
    public RemoveTransactionResponse closeTransaction(@RequestPayload RemoveTransactionRequest removeTransactionRequest) {
        RemoveTransactionResponse removeTransactionResponse = new RemoveTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Date startDate = removeTransactionRequest.getDate1().toGregorianCalendar().getTime();
        Date endDate = removeTransactionRequest.getDate2().toGregorianCalendar().getTime();
        String result=transactionService.closeTransaction(startDate,endDate);
        if(result.contains("removed")){
            serviceStatus.setStatus("removed the transactions");
        } else{
            serviceStatus.setStatus(" removle is not possible ");
        }
        serviceStatus.setStatus(result);
        removeTransactionResponse.setServiceStatus(serviceStatus);
        return removeTransactionResponse;
    }

    @PayloadRoot(namespace=url,localPart = "filterByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterAmount(@RequestPayload FilterByAmountRequest filterByAmountRequest){
        FilterByAmountResponse filterByAmountResponse=new FilterByAmountResponse();
        List<services.transactions.Transactions> returnTransactions=new ArrayList<>();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Transactions> received =transactionService.findByAmount(filterByAmountRequest.getAmount());

        Iterator<Transactions> iterator=received.iterator();
        while(iterator.hasNext()){
            services.transactions.Transactions currentTransactions=new services.transactions.Transactions();
            BeanUtils.copyProperties(iterator.next(),currentTransactions);
            returnTransactions.add(currentTransactions);
        }

        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction details fetched based on sender name");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransactions().addAll(returnTransactions);

        return filterByAmountResponse;
    }

    @PayloadRoot(namespace = url,localPart = "updateTransactionRemarksRequest")
    @ResponsePayload
    public UpdateTransactionRemarksResponse updateRemarks(@RequestPayload UpdateTransactionRemarksRequest updateTransactionRemarksRequest){
        UpdateTransactionRemarksResponse updateTransactionRemarksResponse=new UpdateTransactionRemarksResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transactions.Transactions transactions=new services.transactions.Transactions();

        Transactions daoTransaction=new Transactions();
        BeanUtils.copyProperties(updateTransactionRemarksRequest.getTransactions(),daoTransaction);

        daoTransaction=transactionService.updateRemarks(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+" remarks has been updated");
        }
        else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+" remarks is not updated ");
        }

        BeanUtils.copyProperties(daoTransaction,transactions);
        updateTransactionRemarksResponse.setServiceStatus(serviceStatus);
        updateTransactionRemarksResponse.setTransactions(transactions);

        return updateTransactionRemarksResponse;
    }
}
