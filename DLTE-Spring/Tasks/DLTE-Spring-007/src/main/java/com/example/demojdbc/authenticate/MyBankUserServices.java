//package com.example.demojdbc.authenticate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//
////service class with methods corresponding to signing up i.e insert details into the user details table
//@Service
//public class MyBankUserServices implements UserDetailsService {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    //signup method
//    public MyBankUsers signingUp(MyBankUsers myBankUsers){
//        int ack = jdbcTemplate.update("insert into mybank_users2 values(?,?,?,?,?,?)",new Object[]{
//                myBankUsers.getName(),myBankUsers.getUsername(),
//                myBankUsers.getPassword(),myBankUsers.getAadhar(),
//                myBankUsers.getContact(),myBankUsers.getEmail()
//        });
//        return myBankUsers;
//    }
//
//    //get the user details based on username
//    public MyBankUsers findByUsername(String username){
//        MyBankUsers myBankUsers=jdbcTemplate.queryForObject("select * from mybank_users2 where username=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
//        return myBankUsers;
//    }
//
//    //method overriden from the implemented interface UserDetailsService
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyBankUsers users=findByUsername(username);
//        if(users==null)
//            throw new UsernameNotFoundException(username);
//        return users;
//    }
//}
