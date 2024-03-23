package com.example.demo.remotes;

import com.example.demo.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {
    //need not write anything....but we have used builtin save method but need not be written
}