package com.example.demojdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    //used for executing the sql statement use here
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //method for inserting the details into the table..if failed returns exception
    public TransactionNew apiSave(TransactionNew transaction){
        int ack = jdbcTemplate.update("insert into mybank_transaction values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionBy(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks()
                });
        if(ack!=0)
            return transaction;
        else
            throw new TransactionException("Insertion Failed");
    }

    //select all transaction with specified sender name
    public List<TransactionNew> apiFindBySender(String name){
        return jdbcTemplate.query("select * from mybank_transaction where transaction_by=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(TransactionNew.class));
    }

    //select all transaction with specified receiver name
    public List<TransactionNew> apiFindByReciever(String name){
        return jdbcTemplate.query("select * from mybank_transaction where transaction_to=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(TransactionNew.class));
    }

    //select all transaction with specified amount of transaction
    public List<TransactionNew> apiFindByAmount(Double amount){
        return jdbcTemplate.query("select * from mybank_transaction where transaction_amount=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(TransactionNew.class));
    }

    public String deleteTransaction(Date startDate, Date endDate){
        int acknowledge= jdbcTemplate.update("delete from mybank_transaction where transaction_date between ? and ?",
                new Object[]{startDate,endDate}
        );
        if(acknowledge!=0)
            return "Transaction deleted";
        else
            return "Failed to delete transaction";
    }

    /*RowMapper interface. It is used to map rows from the database result set
     to TransactionNew objects. The mapRow method defines how each row in the result
     set should be mapped to a TransactionNew object.
     */
    protected class TransactiondMapper implements RowMapper<TransactionNew>{
        @Override
        public TransactionNew mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionNew transaction=new TransactionNew();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionBy(rs.getString("transaction_by"));
            transaction.setTransactionTo(rs.getString("transaction_to"));
            transaction.setTransactionAmount(rs.getDouble(5));
            transaction.setTransactionRemarks(rs.getString(6));
            return transaction;
        }
    }
}
