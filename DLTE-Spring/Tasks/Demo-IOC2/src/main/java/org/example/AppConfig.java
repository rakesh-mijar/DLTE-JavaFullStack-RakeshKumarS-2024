package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
public class AppConfig {

//    @Autowired
//    Branch bean;


    //@Bean(name="name1")
    @Bean
    public Branch myBean2() {
        Branch bean = new Branch();
        bean.setBranchName("sirsi");
        return bean;
    }
    //@Bean(name="name1")
    public Branch myBean() {
        Branch bean = new Branch();
        bean.setBankName("Baroda");
        bean.setBranchId(121);
        return bean;
    }
}






