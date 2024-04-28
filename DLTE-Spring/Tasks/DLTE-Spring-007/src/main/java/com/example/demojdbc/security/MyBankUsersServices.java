package com.example.demojdbc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyBankUsersServices implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public MyBankUsers signingUp(MyBankUsers myBankUsers){
        int ack=jdbcTemplate.update("insert into users values(?,?,?,?,?,?)", new Object[]{
                myBankUsers.getUsername(),myBankUsers.getPassword(),
                myBankUsers.getRole(),myBankUsers.getAddress(),
                myBankUsers.getContact(),myBankUsers.getEmail()
        });
        return myBankUsers;
    }

    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers=jdbcTemplate.queryForObject("select * from users where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers users=findByUsername(username);
        if(users==null)
            throw new UsernameNotFoundException(username);
        return users;
    }
}
