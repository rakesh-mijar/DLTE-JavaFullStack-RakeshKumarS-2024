package com.project.dao.security;


import com.project.dao.entities.Accounts;
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
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);

    /*
    This method corresponds with creating new customer i.e signing up
     */
    public MyBankCustomers signingUp(MyBankCustomers myBankCustomers){
        // Retrieve the next value from the CUSTOMERID_SEQ1 sequence and used dual since nextval is not specific to any table to be retrieved
        Long nextCustomerId = jdbcTemplate.queryForObject("SELECT CUSTOMERID_SEQ1.NEXTVAL FROM DUAL", Long.class);

        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values(?,?,?,?,?,?,?)", new Object[]{nextCustomerId,myBankCustomers.getCustomerName(),myBankCustomers.getCustomerAddress(),myBankCustomers.getCustomerStatus(),myBankCustomers.getCustomerContact(),myBankCustomers.getUsername(),myBankCustomers.getPassword()});
        return myBankCustomers;
    }

    //This method corresponds to fetch all the customers from mybank_app_customer table
    public MyBankCustomers findByUsername(String username) {
        List<MyBankCustomers> customerList = jdbcTemplate.query(
                "SELECT * FROM mybank_app_customer",
                new BeanPropertyRowMapper<>(MyBankCustomers.class));
        return filterByUserName(customerList,username);

    }

    //continuing to above method filters the user based on the specified username among all users retrieved
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



    //to  load a user based on the provided username during the authentication process.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers myBankCustomers=findByUsername(username);
        if(myBankCustomers==null)
            throw new UsernameNotFoundException(username);
        return myBankCustomers;
    }

//
//    public List<Accounts> findByAccountNumber(Long customerId) {
//        List<Accounts> customerList = jdbcTemplate.query(
//                "SELECT * FROM mybank_app_account",
//                new BeanPropertyRowMapper<>(Accounts.class));
//        return filterByAccountNumber(customerList,customerId);
//
//    }
//
//    //continuing to above method filters the user based on the specified username among all users retrieved
//    public List<Accounts> filterByAccountNumber( List<Accounts> customerList,Long customerId){
//        // Filter the list based on the provided username
//        List<Accounts> filteredCustomers = customerList.stream()
//                .filter(customer -> customer.getCustomerId().equals(customerId))
//                .collect(Collectors.toList());
//        //filteredCustomers.forEach(System.out::println);
//        if (!filteredCustomers.isEmpty()) {
//            return filteredCustomers; // Return the first matching customer
//        } else {
//            return null; // Return null if no customer found
//        }
//    }
public List<Accounts> findByAccountNumber(Long customerId) {
    List<Accounts> accountList = jdbcTemplate.query(
            "SELECT * FROM mybank_app_account WHERE customerId = ?",
            new Object[]{customerId},
            new BeanPropertyRowMapper<>(Accounts.class));
    return accountList;
}

}
