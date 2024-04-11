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
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.sql.*;
import java.util.*;


@Service
public class AccountsServices implements AccountRepository {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger= LoggerFactory.getLogger(AccountsServices.class);


    @Override
    // Method to filter accounts by customer status, i.e it checks for the customer status ,if he's active only then accounts active corresponding to the customer is filtered
    public List<Accounts> filterByCustomerStatus(Long customerId) throws CustomerNotFoundException, SQLSyntaxErrorException, ServerException {
        // Check if customer exists
        if(!customerExists(customerId)){
            logger.info(resourceBundle.getString("no.customer.id"));
            throw new CustomerNotFoundException(resourceBundle.getString("no.customer.id"));
        }
        // Query for active accounts related to the customer
        List<Accounts> shortlisted;
        try {
             shortlisted = jdbcTemplate.query("SELECT a.*\n" +
                            "FROM MYBANK_APP_ACCOUNT a\n" +
                            "INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID\n" +
                            "WHERE c.CUSTOMER_ID = ? AND c.CUSTOMER_STATUS = 'Active' AND a.ACCOUNT_STATUS = 'Active'",
                    new Object[]{customerId}, new AccountsMapper());
        }catch (DataAccessException e){
            throw new ServerException(resourceBundle.getString("no.connection.established"));
        }

        if (shortlisted.isEmpty()) {
            logger.info(resourceBundle.getString("no.active.accounts"));
            throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts") + customerId);
        }

        return shortlisted;
    }

    @Override
    public Accounts UpdateAccountService(Accounts accounts) throws ServerException {
        CallableStatementCreator creator = con -> {
            CallableStatement statement = con.prepareCall("{call close_account_service5(?,?,?,?,?,?,?)}");
            statement.setLong(1, accounts.getAccountId()); // returns the account ID
            statement.registerOutParameter(2, Types.NUMERIC); // p_account_number
            statement.registerOutParameter(3, Types.NUMERIC); // p_customer_id
            statement.registerOutParameter(4, Types.VARCHAR); // p_account_type
            statement.registerOutParameter(5, Types.VARCHAR); // p_account_status
            statement.registerOutParameter(6, Types.NUMERIC); // p_account_balance
            statement.registerOutParameter(7, Types.VARCHAR); // p_result
            return statement;
        };

        try {
            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{
                            new SqlParameter(Types.NUMERIC),
                            new SqlOutParameter("p_account_number", Types.NUMERIC),
                            new SqlOutParameter("p_customer_id", Types.NUMERIC),
                            new SqlOutParameter("p_account_type", Types.VARCHAR),
                            new SqlOutParameter("p_account_status", Types.VARCHAR),
                            new SqlOutParameter("p_account_balance", Types.NUMERIC),
                            new SqlOutParameter("p_result", Types.VARCHAR),
                    }
            ));

            String result = returnedExecution.get("p_result").toString();
            if (result.equals("SQLSUCESS")) {
                // Success case
                long accountId = accounts.getAccountId();
                long accountNumber = ((Number) returnedExecution.get("p_account_number")).longValue();
                long customerId = ((Number) returnedExecution.get("p_customer_id")).longValue();
                String accountType = (String) returnedExecution.get("p_account_type");
                String accountStatus = (String) returnedExecution.get("p_account_status");
                double accountBalance = ((Number) returnedExecution.get("p_account_balance")).doubleValue();
                logger.info(resourceBundle.getString("account.close.service"));

                Accounts accounts1=new Accounts();
                accounts1.setAccountId(accountId);
                accounts1.setAccountNumber(accountNumber);
                accounts1.setCustomerId(customerId);
                accounts1.setAccountType(accountType);
                accounts1.setAccountStatus(accountStatus);
                accounts1.setAccountBalance(accountBalance);

                return accounts1;
                //return new Accounts(accountId, accountNumber, customerId, accountType, accountStatus, accountBalance);
            } else if (result.equals("SQLERR-001")) {
                logger.info(resourceBundle.getString("no.active.accounts"));
                throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts"));
            } else if (result.equals("SQLERR-002")) {
                logger.info(resourceBundle.getString("no.customer.id"));
                throw new CustomerNotFoundException(resourceBundle.getString("no.customer.id"));
            } else if (result.equals("SQLERR-003")) {
                logger.info(resourceBundle.getString("inactive.customer"));
                throw new CustomerNotFoundException(resourceBundle.getString("inactive.customer"));
            } else if (result.equals("SQLERR-004")) {
                throw new ServerException(resourceBundle.getString("no.data"));
            }
        } catch (DataAccessException e) {
            throw new ServerException(resourceBundle.getString("no.connection.established")+ e.getMessage());
        }
        return null;
    }


        //method corresponds to closing the account service based on the account id but it is performed only for active customers and close services possible for active accounts
//    @Override
//    public Accounts UpdateAccountService(Accounts accounts) throws ServerException {
//        Accounts updatedAccount = null;
//        String procedureCall = "{call CLOSE_ACCOUNT_SERVICE6(?)}";
//        try {
//            int ack = jdbcTemplate.update(procedureCall, accounts.getAccountId());
//            if (ack != 0) {
//                updatedAccount = jdbcTemplate.queryForObject("SELECT * FROM mybank_app_account WHERE account_id=?",
//                        new Object[]{accounts.getAccountId()},
//                        new AccountsMapper());
//            }
//            //else {
////                throw new AccountNotFoundException("Account not found or already inactive.");
////            }
//        } catch (DataAccessException e) {
//            String errorMessage = e.getMessage();
//            if (errorMessage.contains("-20001")) {
//                throw new AccountNotFoundException(resourceBundle.getString("inactive.account"));
//            } else if (errorMessage.contains("-20002")) {
//                throw new CustomerNotFoundException(resourceBundle.getString("no.customer.id"));
//            } else if (errorMessage.contains("-20003")) {
//                throw new CustomerNotFoundException(resourceBundle.getString("inactive.customer"));
//            } else if (errorMessage.contains("-20004")) {
//                throw new AccountNotFoundException(resourceBundle.getString("no.account.id"));
//            } else {
//                throw new ServerException(resourceBundle.getString("no.connection.established") + errorMessage);
//            }
//        }
//        return updatedAccount;
//
//    }


    // Method to check if customer exists
    public boolean customerExists(Long customerId) {
        try {
            // Query the database to check if the customer exists
            String sql = "SELECT COUNT(*) FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, customerId);
            return count > 0;
        } catch (CustomerNotFoundException e) {
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
