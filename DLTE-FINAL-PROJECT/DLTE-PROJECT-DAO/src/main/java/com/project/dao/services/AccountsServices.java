package com.project.dao.services;

import com.project.dao.entities.Accounts;
import com.project.dao.entities.Customers;
import com.project.dao.exceptions.AccountNotFoundException;
import com.project.dao.exceptions.CustomerNotFoundException;
import com.project.dao.exceptions.NoDataFoundException;
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
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(AccountsServices.class);


    @Override
    /* Method to filter accounts by customer status, i.e it checks for the customer status,
    if he's active only then accounts active corresponding to the customer is filtered
     */
    public List<Accounts> filterByCustomerStatus(Long customerId) throws CustomerNotFoundException, SQLSyntaxErrorException, ServerException {
        // Check if customer exists
        if (!customerExists(customerId)) {
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
        } catch (DataAccessException e) {
            logger.info(resourceBundle.getString("no.connection.established"));
            throw new ServerException(resourceBundle.getString("no.connection.established"));
        }

        if (shortlisted.isEmpty()) {
            logger.info(resourceBundle.getString("no.active.accounts"));
            throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts") + customerId);
            //return Collections.emptyList();   for running test case testFilterByCustomerStatusNoAccountsFound()
        }

        return shortlisted;
    }

    // Method to check if customer exists
    public boolean customerExists(Long customerId) {
        try {
            // Query the database to check if the customer exists
            String sql = "SELECT COUNT(*) FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ? ";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, customerId);
            return count > 0;//returns true or false based on condition
        } catch (CustomerNotFoundException e) {
            // Handle any exceptions that may occur during database access
            logger.warn(resourceBundle.getString("no.access"));
//            e.printStackTrace();
            return false; // Return false in case of any exception
        }
    }


    /* this method corresponds to update the account services as inactive provided the customer is active and have active accounts
    input: PutMapping-- URL with Profile JSON
    output: Profile Object with updated result
     */
    @Override
    public Accounts UpdateAccountService(Accounts accounts) throws ServerException {
        try {
            //fetch the account details from database
            Accounts fetchedAccount = jdbcTemplate.queryForObject(
                    "SELECT * FROM MYBANK_APP_ACCOUNT WHERE ACCOUNT_NUMBER = ?",
                    new Object[]{accounts.getAccountNumber()},
                    new AccountsMapper());

            // Compare the fetched account details with the provided accounts object
            if (!isSameAccount(accounts, fetchedAccount)) {
                // If account type is incorrect
                if (!Objects.equals(accounts.getAccountType(), fetchedAccount.getAccountType())) {
                    throw new NoDataFoundException(resourceBundle.getString("invalid.type"));
                }
                // If account balance is incorrect
                if (Double.compare(accounts.getAccountBalance(), fetchedAccount.getAccountBalance()) != 0) {
                    throw new NoDataFoundException(resourceBundle.getString("invalid.balance"));
                }
            }

            /*
            CallableStatementCreator is a functional interface by spring
             JDBC which represents a call to a stored procedure in database.
             input : connection object
             output: CallableStatement

            Callablestatement is an interface by JDBC extends the PreparedStatement interface and represents a
            precompiled SQL statement(stored procedures) that can be executed on the database.
             */
            //used lambda expressions
            CallableStatementCreator creator = con -> {
                CallableStatement statement = con.prepareCall("{call close_account_service(?,?,?,?,?,?)}");
                statement.setLong(1, accounts.getAccountNumber()); // setting input parameters--returns the account ID
                statement.registerOutParameter(2, Types.NUMERIC); //registering output parameters  p_account_number
                statement.registerOutParameter(3, Types.VARCHAR); // p_account_type
                statement.registerOutParameter(4, Types.VARCHAR); // p_account_status
                statement.registerOutParameter(5, Types.NUMERIC); // p_account_balance
                statement.registerOutParameter(6, Types.VARCHAR); // p_result
                return statement;
            };

            //try {
            /*
            jdbcTemplate.call(): This method is used to execute a stored procedure or function in the database.
            The result of the execution is stored in the returnedExecution map, where the keys are parameter names (p_account_number),
            and the values are the output parameters returned by the stored procedure.
             */
            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
                    new SqlParameter[]{//parameters of stored procedure
                            new SqlParameter(Types.NUMERIC),
                            new SqlOutParameter("p_customer_id", Types.NUMERIC),
                            new SqlOutParameter("p_account_type", Types.VARCHAR),
                            new SqlOutParameter("p_account_status", Types.VARCHAR),
                            new SqlOutParameter("p_account_balance", Types.NUMERIC),
                            new SqlOutParameter("p_result", Types.VARCHAR),
                    }
            ));

            /*
            based on p_result corresponding output or exceptions are thrown
            on successful exceution updated account object is returned.
            each error code raises corresponding exceptions to be handled in backend.
             */
            String result = returnedExecution.get("p_result").toString();
            if (result.equals("SQLSUCESS")) {
                // Success case
                long accountNumber=accounts.getAccountNumber();
                String accountType = (String) returnedExecution.get("p_account_type");
                String accountStatus = (String) returnedExecution.get("p_account_status");
                double accountBalance = ((Number) returnedExecution.get("p_account_balance")).doubleValue();
                logger.info(resourceBundle.getString("account.close.service"));

                Accounts accounts1 = new Accounts();
                accounts1.setAccountNumber(accountNumber);
                accounts1.setAccountType(accountType);
                accounts1.setAccountStatus(accountStatus);
                accounts1.setAccountBalance(accountBalance);

                return accounts1;
            } else if (result.equals("SQLERR-001")) {
                logger.warn(resourceBundle.getString("no.active.accounts"));
                throw new AccountNotFoundException(resourceBundle.getString("no.active.accounts"));
            } else if (result.equals("SQLERR-002")) {
                logger.warn(resourceBundle.getString("no.customer.id"));
                throw new CustomerNotFoundException(resourceBundle.getString("no.customer.id"));
            } else if (result.equals("SQLERR-003")) {
                logger.warn(resourceBundle.getString("inactive.customer"));
                throw new CustomerNotFoundException(resourceBundle.getString("inactive.customer"));
            } else if (result.equals("SQLERR-004")) {
                logger.warn(resourceBundle.getString("no.data"));
                throw new ServerException(resourceBundle.getString("no.data"));
            }
        } catch (DataAccessException e) {
            logger.warn(resourceBundle.getString("data.error"));
            throw new ServerException(resourceBundle.getString("data.error"));
        }
        return null;
    }

    // Method to compare the fetched account details with the provided accounts object
    private boolean isSameAccount(Accounts providedAccount, Accounts fetchedAccount) {
        // Check if any of the accounts is null
        if (providedAccount == null || fetchedAccount == null) {
            return false;
        }
        // Compare each attribute of the providedAccount with the corresponding attribute of fetchedAccount
        return Objects.equals(providedAccount.getAccountType(), fetchedAccount.getAccountType())
                && Double.compare(providedAccount.getAccountBalance(), fetchedAccount.getAccountBalance()) == 0;
    }


    /**
     *  Custom RowMapper for Accounts
     *  RowMapper interface is used to map rows of a ResultSet to Account objects.
     *  ResultSet contains data retrieved from db, rowNum represents current row number being processed.
     */
    public class AccountsMapper implements RowMapper<Accounts> {

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
