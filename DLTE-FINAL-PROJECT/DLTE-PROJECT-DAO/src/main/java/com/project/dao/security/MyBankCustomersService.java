package com.project.dao.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class MyBankCustomersService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);


    public MyBankCustomers signingUp(MyBankCustomers myBankCustomers){

        // Retrieve the next value from the CUSTOMERID_SEQ1 sequence
        Long nextCustomerId = jdbcTemplate.queryForObject("SELECT CUSTOMERID_SEQ1.NEXTVAL FROM DUAL", Long.class);

        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values(?,?,?,?,?,?,?)", new Object[]{nextCustomerId,myBankCustomers.getCustomerName(),myBankCustomers.getCustomerAddress(),myBankCustomers.getCustomerStatus(),myBankCustomers.getCustomerContact(),myBankCustomers.getUsername(),myBankCustomers.getPassword()});
        return myBankCustomers;
    }

//    public MyBankCustomers findByUsername(String username){
//        MyBankCustomers myBankCustomers=jdbcTemplate.queryForObject("select * from mybank_app_customer where username=?",new Object[]{username},new BeanPropertyRowMapper<>(MyBankCustomers.class));
//        return myBankCustomers;
//    }

    public MyBankCustomers findByUsername(String username) {
        List<MyBankCustomers> customerList = jdbcTemplate.query(
                "SELECT * FROM mybank_app_customer",
                new BeanPropertyRowMapper<>(MyBankCustomers.class));
        return filterByUserName(customerList,username);

    }

    public MyBankCustomers filterByUserName( List<MyBankCustomers> customerList,String username){
        // Filter the list based on the provided username
        List<MyBankCustomers> filteredCustomers = customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        //filteredCustomers.forEach(System.out::println);
        if (!filteredCustomers.isEmpty()) {
            return filteredCustomers.get(0); // Return the first matching customer
        } else {
            return null; // Return null if no customer found
        }
    }

    public void updateAttempts(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{myBankCustomers.getAttempts(),myBankCustomers.getUsername()});
        logger.info(resourceBundle.getString("attempts.updated"));
    }
    public void updateStatus(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'Inactive' where username = ?",new Object[]{myBankCustomers.getUsername()});
        logger.info(resourceBundle.getString("status.changed"));
    }

    public String getAccountOwnerUsername(Long accountId) {
        try {
            // Query to fetch the username of the account owner based on the account ID
            String sql = "SELECT c.USERNAME " +
                    "FROM mybank_app_customer c " +
                    "JOIN mybank_app_account a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                    "WHERE a.ACCOUNT_ID = ?";
            String ownerUsername = jdbcTemplate.queryForObject(sql, new Object[]{accountId}, String.class);
            return ownerUsername;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(resourceBundle.getString("no.account") + accountId);
            return null;
        } catch (DataAccessException e) {
            logger.error(resourceBundle.getString("error.fetch") + accountId, e);
            return null;
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers myBankCustomers=findByUsername(username);
        if(myBankCustomers==null)
            throw new UsernameNotFoundException(username);
        return myBankCustomers;
    }
}
