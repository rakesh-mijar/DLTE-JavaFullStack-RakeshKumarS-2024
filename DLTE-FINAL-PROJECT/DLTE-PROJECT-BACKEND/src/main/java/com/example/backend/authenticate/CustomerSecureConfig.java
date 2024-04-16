package com.example.backend.authenticate;

import com.project.dao.security.MyBankCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class CustomerSecureConfig{

    @Autowired
    MyBankCustomersService myBankCustomersService;

    AuthenticationManager authenticationManager;

    @Autowired
    CustomersFailureHandler customersFailureHandler;

    @Autowired
    CustomersSucccessHandler customersSucccessHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();
        httpSecurity.formLogin().usernameParameter("username").failureHandler(customersFailureHandler).successHandler(customersSucccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
//        httpSecurity.authorizeRequests().antMatchers("/accounts/closeAccounts").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();

        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(myBankCustomersService);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }

}