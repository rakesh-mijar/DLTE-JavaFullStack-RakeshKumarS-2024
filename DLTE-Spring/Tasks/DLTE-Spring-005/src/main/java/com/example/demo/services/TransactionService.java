package com.example.demo.services;

import com.example.demo.model.Transaction;
import com.example.demo.remotes.TransactionRepository;
import com.example.demo.remotes.TransactionWithJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    //field injection of the instances defined in the remotes package
    @Autowired
    TransactionWithJPA transactionJPARepository;

    @Autowired
    TransactionRepository transactionRepository;

    //here methods defined in the repository are called...i.e service package
    public Transaction callSave(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> callFindByUserAndType(String user, String type){
        return transactionJPARepository.lookUpByTransactionNameAndType(user,type);
    }

    public List<Transaction> callFindByRange(Long range1,Long range2){
        return  transactionJPARepository.lookUpByTransactionRange(range1,range2);
    }

}
