package org.example;

//using constructors---without spring in java
//public class Demo {
//
//    private String name;
//    private int age;
//
//    // Constructor
//    public Demo(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }
//
//    // Getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//}
//
//// Creating a Person bean
//class Main {
//    public static void main(String[] args) {
//        // Creating a Person bean using constructor
//        Demo person = new Demo("John", 30);
//        System.out.println(person.getAge());
//        System.out.println(person.getName());
//    }
//}


//for configuration examples
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// class Main {
//    public static void main(String[] args) {
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        //Branch myBean = context.getBean(Branch.class);//---without name
//        Branch myBean = (Branch) context.getBean("myBean2");
//        System.out.println(myBean.getBranchName());
//        System.out.println(myBean.getBankName());
//        System.out.println(myBean.getBranchName());
//    }
//}


//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
////for component examples
//class Main {
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        //Branch myBean = (Branch) context.getBean("myBean");
//        Branch myBean = context.getBean(Branch.class);
//        // Print the properties of the bean
//        System.out.println("Id " + myBean.getBranchId());
//        System.out.println("Name: " + myBean.getBankName());
//    }
//}


class Main {
    public static void main(String[] args) {
        // Load the Spring application context from the XML configuration file
        ApplicationContext context = new ClassPathXmlApplicationContext("mybank-branches.xml");

        // Retrieve the beans from the context
        BranchController branchController = context.getBean(BranchController.class);


        String branchInfo = branchController.getBranchInfo();
        System.out.println(branchInfo);
    }
}