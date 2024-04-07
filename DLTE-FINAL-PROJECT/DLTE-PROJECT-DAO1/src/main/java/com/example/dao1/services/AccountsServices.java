package com.example.dao1.services;

import com.example.dao1.entities.Accounts;
import com.example.dao1.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
public class AccountsServices implements AccountRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Accounts> filterByStatus(Accounts accounts) {
        List<Accounts> shortlisted=jdbcTemplate.query("SELECT a.*\n" +
                "FROM MYBANK_APP_ACCOUNT a\n" +
                "INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID\n" +
                "WHERE c.CUSTOMER_STATUS = 'Active' AND a.ACCOUNT_STATUS = 'Active'",new AccountsMapper());
        return shortlisted;
    }
    protected class AccountsMapper implements RowMapper<Accounts> {

        @Override
        public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accounts accounts=new Accounts();
            accounts.setAccountId(rs.getLong(1));
            accounts.setAccountNumber(rs.getLong(2));
            accounts.setCustomerId(rs.getLong(3));
            accounts.setAccountType(rs.getString(4));
            accounts.setAccountStatus(rs.getString(5));
            accounts.setAccountBalance(rs.getDouble(6));
            return accounts;
        }
    }
}
