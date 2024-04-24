//package com.example.demo.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
////one of the output for security task
////{
////        "username": "mahesh",
////        "password": "$2a$10$bN8ZIvgm.UEsxU3tWGSDp.rZtNeQKQQo/dvn6otLyRESEN3yVE/Da",
////        "role": "customer",
////        "address": "bangalore",
////        "contact": 9980342382,
////        "email": "pranav@gmail.com",
////        "enabled": true,
////        "authorities": [
////        {
////        "authority": "customer"
////        }
////        ],
////        "accountNonExpired": true,
////        "accountNonLocked": true,
////        "credentialsNonExpired": true
////        }
//@Service
//public class MyBankUsersService implements UserDetailsService {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public MyBankUsers signingUp(MyBankUsers myBankUsers){
//        int ack=jdbcTemplate.update("insert into users values(?,?,?,?,?,?)", new Object[]{
//                        myBankUsers.getUsername(),myBankUsers.getPassword(),
//                        myBankUsers.getRole(),myBankUsers.getAddress(),
//                        myBankUsers.getContact(),myBankUsers.getEmail()
//                });
//        return myBankUsers;
//    }
//
//    public MyBankUsers findByUsername(String username){
//        MyBankUsers myBankUsers=jdbcTemplate.queryForObject("select * from users where username=?",
//                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
//        return myBankUsers;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MyBankUsers users=findByUsername(username);
//        if(users==null)
//            throw new UsernameNotFoundException(username);
//        return users;
//    }
//}
