//package com.example.demojdbc.authenticate;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//@Configuration
//public class TransactionSecureConfig {
//
//    @Autowired
//    MyBankUserServices services;
//
//
//    AuthenticationManager authenticationManager;
//
//    // Configuring the PasswordEncoder bean to be used for encoding passwords
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    //method corresponds to provide security by means of authentication username and password should match with the credentials present in the table
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.httpBasic();
//        httpSecurity.formLogin();
//        httpSecurity.csrf().disable();
//
//        //authorizing the request of saving the user details
//        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
//
//        //autorizing the request of getting user details based on the username
//        httpSecurity.authorizeRequests().antMatchers("/profile/users/{username}").permitAll();
//
//        httpSecurity.authorizeRequests().anyRequest().authenticated();
//
//
//        AuthenticationManagerBuilder builder=httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
//
//        builder.userDetailsService(services);
//        authenticationManager=builder.build();
//        httpSecurity.authenticationManager(authenticationManager);
//
//        return httpSecurity.build();
//    }
//
//
//}
