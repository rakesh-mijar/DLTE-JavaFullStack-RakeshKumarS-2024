package com.example.demo.remotes;

import com.example.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionWithJPA extends JpaRepository<Transaction,Long>{

    //findAllByUserAndType   GetMapping using Native SQL query
    //normal sql query
    @Query(value="select * from transaction where username=:user and transaction_type=:type",nativeQuery = true)
    List<Transaction> lookUpByTransactionNameAndType(String user, String type);

    //findAllByRangeOfTransactionAmount by using HQL
    //here the entity name is given with sttributes and not the table name or column name given
    @Query(value="from Transaction where transactionAmount between :range1 and :range2")
    List<Transaction> lookUpByTransactionRange(Long range1,Long range2);
}
