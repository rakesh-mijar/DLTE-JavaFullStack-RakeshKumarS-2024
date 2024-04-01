package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch Branch1=applicationContext.getBean("branch3", Branch.class);
        System.out.println(Branch1.getIfsCode()+" "+Branch1.getBranchName());
    }
}
