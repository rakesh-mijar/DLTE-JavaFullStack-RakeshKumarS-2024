package org.example;

public class ExecuteExecutors {
    public static void main(String[] args) throws InterruptedException {
        MyProvider provider=new MyProvider();
        Thread threadOne=new Thread(provider,"Suresh");
        Thread threadTwo=new Thread(provider,"Prashanth");
        Thread threadThree=new Thread(provider,"Razi");
        Thread threadFour=new Thread(provider,"David");
        threadOne.run();threadOne.join();
        threadTwo.run();threadTwo.join();
        threadThree.run();threadThree.join();
        threadFour.run();threadFour.join();
    }
}
