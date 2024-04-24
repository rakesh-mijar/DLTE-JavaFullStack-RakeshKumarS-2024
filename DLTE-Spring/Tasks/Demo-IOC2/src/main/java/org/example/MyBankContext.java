package org.example;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        //loads the bean definition from the xml file
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        //getBean creates an instance of the bean defined from Branch class(like object instance creation)
        Branch Branch1=applicationContext.getBean("branch3 @1", Branch.class);
        //can access the bean using getter and setter
        System.out.println(Branch1.getIfsCode()+" "+Branch1.getBranchName());
        if (Branch1 != null) {
            System.out.println("Bean created successfully!");
        } else {
            System.out.println("Bean creation failed!");
        }
    }
}
