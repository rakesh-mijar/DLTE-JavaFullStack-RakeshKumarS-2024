package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transactions publishNewTransactions(Transactions transactions){
        int acknowledge = jdbcTemplate.update("insert into mybank_transaction values(?,?,?,?,?,?)",
                new Object[]{transactions.getTransactionId(),transactions.getTransactionDate(),transactions.getTransactionBy(),transactions.getTransactionTo(),transactions.getTransactionAmount(),transactions.getTransactionRemarks()});
        if(acknowledge!=0)
            return transactions;
        else
            return null;
    }

    public List<Transactions> findBySender(String name){
        List<Transactions> filtered=jdbcTemplate.query("select * from mybank_transaction where transaction_by=?",
                new Object[]{name},new TransactionsMapper());
        return filtered;
    }

    public List<Transactions> findByReceiver(String name){
        List<Transactions> filtered=jdbcTemplate.query("select * from mybank_transaction where transaction_to=?",
                new Object[]{name},new TransactionsMapper());
        return filtered;
    }

    public String closeTransaction(Date date1, Date date2){
        int acknowledge = jdbcTemplate.update("delete from mybank_transaction where transaction_date between ? and ?",
                new Object[]{date1,date2});
        if(acknowledge!=0)
            return "Transactions between "+date1+" and "+date2+" closed";
        else
            return "Invalid input";
    }

    public List<Transactions> findByAmount(Double amount){
        List<Transactions> filtered=jdbcTemplate.query("select * from mybank_transaction where transaction_amount=?",
                new Object[]{amount},new TransactionsMapper());
        return filtered;
    }

    public Transactions updateRemarks(Transactions transactions){
        int acknowledge = jdbcTemplate.update("update mybank_transaction set remarks=? where transaction_id=?",
                new Object[]{transactions.getTransactionRemarks(),transactions.getTransactionId()});
        if(acknowledge!=0)
            return transactions;
        else
            return null;
    }

    protected class TransactionsMapper implements RowMapper<Transactions>{

        @Override
        public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
           Transactions transactions=new Transactions();
           transactions.setTransactionId(rs.getLong(1));
           transactions.setTransactionDate(rs.getDate(2));
           transactions.setTransactionBy(rs.getString(3));
           transactions.setTransactionTo(rs.getString(4));
           transactions.setTransactionAmount(rs.getDouble(5));
           transactions.setTransactionRemarks(rs.getString(6));
           return transactions;
        }
    }
}
