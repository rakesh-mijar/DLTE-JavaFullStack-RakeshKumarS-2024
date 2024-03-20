package com.example.demo.autowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ExecutingFieldInjection {
    public static void main(String[] args){
        //which is the class that provides the functionality to register Spring beans and manage the application context.
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //This line scans the specified package (com.example.demo.autowire) and its sub-packages for Spring components such as @Component, @Service, @Repository, and @Controller. It tells Spring where to look for the components to register them in the application context.
        context.scan("com.example.demo.autowire");
        //initialize and register all the beans found during the scanning process.
        context.refresh();
        //Since MyBank is annotated with @Service, Spring will create a singleton bean of this class and inject its dependencies using autowiring.
        MyBank myBankService=context.getBean(MyBank.class);
        System.out.println(myBankService.callFindLoans());
    }
}
