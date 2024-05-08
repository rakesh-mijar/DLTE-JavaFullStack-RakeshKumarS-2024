package com.project.dao.services;

import com.project.dao.entities.Accounts;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.remotes.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.sql.*;
import java.util.*;


@Service
public class AccountsServices implements AccountRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(AccountsServices.class);


    @Override
    public List<Accounts> filterByCustomerStatus(Long customerId) throws ServerException {
        List<Accounts> activeAccounts = new ArrayList<>();
        try {
            List<Accounts> allAccounts = jdbcTemplate.query("SELECT a.* FROM MYBANK_APP_ACCOUNT a INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID WHERE c.CUSTOMER_ID = ? AND c.CUSTOMER_STATUS = 'Active'", new Object[]{customerId}, new AccountsMapper());

            for (Accounts account : allAccounts) {
                if ("Active".equals(account.getAccountStatus())) {
                    activeAccounts.add(account);
                }
            }
        } catch (DataAccessException e) {
            logger.info(resourceBundle.getString("no.connection.established"));
            throw new ServerException(resourceBundle.getString("no.connection.established"));
        }

        if (activeAccounts.isEmpty()) {
            logger.info(resourceBundle.getString("no.active.accounts"));
            throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts"));
        }

        return activeAccounts;
    }



    @Override
    public Accounts UpdateAccountService(Accounts accounts) throws ServerException {
        try {
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call close_account_service(?,?,?,?,?,?,?)}");
                statement.setLong(1, accounts.getAccountNumber());
                statement.registerOutParameter(2, Types.NUMERIC);
                statement.registerOutParameter(3, Types.NUMERIC);//
                statement.registerOutParameter(4, Types.VARCHAR);
                statement.registerOutParameter(5, Types.VARCHAR);
                statement.registerOutParameter(6, Types.NUMERIC);
                statement.registerOutParameter(7, Types.VARCHAR);
                return statement;
            };


            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{//parameters of stored procedure
                            new SqlParameter(Types.NUMERIC),
                            new SqlOutParameter("p_customer_id", Types.NUMERIC),
                            new SqlOutParameter("p_account_id", Types.NUMERIC),
                            new SqlOutParameter("p_account_type", Types.VARCHAR),
                            new SqlOutParameter("p_account_status", Types.VARCHAR),
                            new SqlOutParameter("p_account_balance", Types.NUMERIC),
                            new SqlOutParameter("p_result", Types.VARCHAR),
                    }
            ));


            String result = returnedExecution.get("p_result").toString();
            if (result.equals("SQLSUCESS")) {
                // Success case
                long accountNumber=accounts.getAccountNumber();
                long customerId = ((Number) returnedExecution.get("p_customer_id")).longValue();
                long accountId = ((Number) returnedExecution.get("p_account_id")).longValue();
                String accountType = (String) returnedExecution.get("p_account_type");
                String accountStatus = (String) returnedExecution.get("p_account_status");
                double accountBalance = ((Number) returnedExecution.get("p_account_balance")).doubleValue();
                logger.info(resourceBundle.getString("account.close.service"));

                Accounts accounts1 = new Accounts();
                accounts1.setCustomerId(customerId);
                accounts1.setAccountId(accountId);
                accounts1.setAccountNumber(accountNumber);
                accounts1.setAccountType(accountType);
                accounts1.setAccountStatus(accountStatus);
                accounts1.setAccountBalance(accountBalance);

                return accounts1;
            } else if (result.equals("SQLERR-001")) {
                logger.warn(resourceBundle.getString("inactive.account"));
                throw new AccountNotFoundException(resourceBundle.getString("inactive.account"));
          } else if (result.equals("SQLERR-004")) {
                logger.warn(resourceBundle.getString("no.data"));
                throw new AccountNotFoundException(resourceBundle.getString("no.data"));
            }else if (result.equals("SQLERR-005")) {
                logger.warn(resourceBundle.getString("error.fetch"));
                throw new ServerException(resourceBundle.getString("error.fetch"));
            }
        } catch (DataAccessException e) {
            logger.warn(resourceBundle.getString("data.error"));
            throw new ServerException(resourceBundle.getString("data.error"));
        }
        return null;
    }

    public static class AccountsMapper implements RowMapper<Accounts> {

        @Override
        public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accounts accounts = new Accounts();
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
