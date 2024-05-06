package com.project.dao.security;


import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import com.project.dao.remotes.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MyBankCustomersService implements CustomerRepository,UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);


    @Override
    public MyBankCustomers signingUp(MyBankCustomers myBankCustomers) {
        // Retrieve the next value from the CUSTOMERID_SEQ1 sequence and used dual since nextval is not specific to any table to be retrieved
        Long nextCustomerId = jdbcTemplate.queryForObject("SELECT CUSTOMERID_SEQ1.NEXTVAL FROM DUAL", Long.class);

        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values(?,?,?,?,?,?,?)", new Object[]{nextCustomerId,myBankCustomers.getCustomerName(),myBankCustomers.getCustomerAddress(),myBankCustomers.getCustomerStatus(),myBankCustomers.getCustomerContact(),myBankCustomers.getUsername(),myBankCustomers.getPassword()});
        return myBankCustomers;
    }

    @Override
    public MyBankCustomers findByUsername(String username) {
        List<MyBankCustomers> customerList = jdbcTemplate.query(
                "SELECT * FROM mybank_app_customer",
                new BeanPropertyRowMapper<>(MyBankCustomers.class));
        return filterByUserName(customerList,username);
    }

    public MyBankCustomers filterByUserName( List<MyBankCustomers> customerList,String username){
        List<MyBankCustomers> filteredCustomers = customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        //filteredCustomers.forEach(System.out::println);
        if (!filteredCustomers.isEmpty()) {
            return filteredCustomers.get(0);
        } else {
            return null;
        }
    }

        @Override
        public void updateAttempts(MyBankCustomers myBankCustomers) {
            jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{myBankCustomers.getAttempts(),myBankCustomers.getUsername()});
            logger.info(resourceBundle.getString("attempts.updated"));
        }


        @Override
        public void updateStatus(MyBankCustomers myBankCustomers) {
            jdbcTemplate.update("update mybank_app_customer set customer_status = 'Inactive' where username = ?",new Object[]{myBankCustomers.getUsername()});
            logger.info(resourceBundle.getString("status.changed"));
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers myBankCustomers=findByUsername(username);
        if(myBankCustomers==null)
            throw new UsernameNotFoundException(username);
        return myBankCustomers;
    }


public List<Accounts> findByAccountNumber(Long customerId) {
    List<Accounts> accountList = jdbcTemplate.query(
            "SELECT * FROM mybank_app_account WHERE customerId = ?",
            new Object[]{customerId},
            new BeanPropertyRowMapper<>(Accounts.class));
    return accountList;
}

}
