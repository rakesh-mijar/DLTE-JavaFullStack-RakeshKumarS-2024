package com.project.dao.services;

import com.project.dao.entities.Accounts;
import com.project.dao.entities.Customers;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.remotes.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class AccountsServices implements AccountRepository {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger= LoggerFactory.getLogger(AccountsServices.class);


    @Override
    // Method to filter accounts by customer status
    public List<Accounts> filterByCustomerStatus(Long customerId) throws CustomerNotFoundException {
        // Check if customer exists
        if(!customerExists(customerId)){
            logger.info(resourceBundle.getString("no.customer.id"));
            throw new CustomerNotFoundException(resourceBundle.getString("no.customer.id"));

        }
        // Query for active accounts related to the customer
        List<Accounts> shortlisted = jdbcTemplate.query("SELECT a.*\n" +
                        "FROM MYBANK_APP_ACCOUNT a\n" +
                        "INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID\n" +
                        "WHERE c.CUSTOMER_ID = ? AND c.CUSTOMER_STATUS = 'Active' AND a.ACCOUNT_STATUS = 'Active'",
                new Object[]{customerId}, new AccountsMapper());

        if (shortlisted.isEmpty()) {
            logger.info(resourceBundle.getString("no.active.accounts"));
            throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts") + customerId);
        }

        return shortlisted;
    }

    // Method to check if customer exists
    public boolean customerExists(Long customerId) {
        try {
            // Query the database to check if the customer exists
            String sql = "SELECT COUNT(*) FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, customerId);
            return count > 0;
        } catch (DataAccessException e) {
            // Handle any exceptions that may occur during database access
            logger.warn(resourceBundle.getString("no.access"));
            e.printStackTrace();
            return false; // Return false in case of any exception
        }
    }

    


    // Custom RowMapper for Accounts
    public class AccountsMapper implements RowMapper<Accounts>{

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

    public class CustomersMapper implements RowMapper<Customers>{

        @Override
        public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
           Customers customers=new Customers();
           customers.setCustomerId(rs.getLong(1));
           customers.setCustomerName(rs.getString(2));
           customers.setCustomerAddress(rs.getString(3));
           customers.setCustomerStatus(rs.getString(4));
           customers.setCustomerContact(rs.getLong(5));
           customers.setUsername(rs.getString(6));
           customers.setPassword(rs.getString(7));
            return customers;
        }
    }
}
