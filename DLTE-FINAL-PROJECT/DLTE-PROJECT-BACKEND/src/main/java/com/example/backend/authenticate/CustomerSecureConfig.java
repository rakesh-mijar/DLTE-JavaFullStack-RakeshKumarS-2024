package com.example.backend.authenticate;

import com.project.dao.remotes.CustomerRepository;
import com.project.dao.security.MyBankCustomersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.ResourceBundle;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class CustomerSecureConfig{

    @Autowired
    MyBankCustomersService myBankCustomersService;

    AuthenticationManager authenticationManager;

    @Autowired
    CustomersFailureHandler customersFailureHandler;

    @Autowired
    CustomersSucccessHandler customersSucccessHandler;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    Logger logger= LoggerFactory.getLogger(CustomersFailureHandler.class);

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));

        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();//enables http basic authentication
        //Cross-Origin Resource Sharing (CORS) support

        httpSecurity.authorizeRequests().antMatchers("/customer/").permitAll();
        httpSecurity.logout().permitAll();

        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/css/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/profile/register").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();

        httpSecurity.formLogin().usernameParameter("username").failureHandler(customersFailureHandler).successHandler(customersSucccessHandler);
        httpSecurity.formLogin().loginPage("/customer/").
                usernameParameter("username").
                failureHandler(customersFailureHandler).
                successHandler(customersSucccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.cors();

        httpSecurity.authorizeRequests().antMatchers("/accountsrepo/accounts.wsdl").permitAll();


        httpSecurity.authorizeRequests().anyRequest().authenticated();



        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(myBankCustomersService);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }

}