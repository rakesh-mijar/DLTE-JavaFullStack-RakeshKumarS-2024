package com.example.backend.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankCustomersService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(MyBankCustomersService.class);


    public MyBankCustomers signingUp(MyBankCustomers myBankCustomers){

        // Retrieve the next value from the CUSTOMERID_SEQ1 sequence
        Long nextCustomerId = jdbcTemplate.queryForObject("SELECT CUSTOMERID_SEQ1.NEXTVAL FROM DUAL", Long.class);

        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values(?,?,?,?,?,?,?)", new Object[]{nextCustomerId,myBankCustomers.getCustomerName(),myBankCustomers.getCustomerAddress(),myBankCustomers.getCustomerStatus(),myBankCustomers.getCustomerContact(),myBankCustomers.getUsername(),myBankCustomers.getPassword()});
        return myBankCustomers;
    }

    public MyBankCustomers findByUsername(String username){
        MyBankCustomers myBankCustomers=jdbcTemplate.queryForObject("select * from mybank_app_customer where username=?",new Object[]{username},new BeanPropertyRowMapper<>(MyBankCustomers.class));
        return myBankCustomers;
    }

    public void updateAttempts(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{myBankCustomers.getAttempts(),myBankCustomers.getUsername()});
        logger.info("Attempts are Updated");
    }
    public void updateStatus(MyBankCustomers myBankCustomers){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'Inactive' where username = ?",new Object[]{myBankCustomers.getUsername()});
        logger.info("Status has changed");
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomers myBankCustomers=findByUsername(username);
        if(myBankCustomers==null)
            throw new UsernameNotFoundException(username);
        return myBankCustomers;
    }
}
