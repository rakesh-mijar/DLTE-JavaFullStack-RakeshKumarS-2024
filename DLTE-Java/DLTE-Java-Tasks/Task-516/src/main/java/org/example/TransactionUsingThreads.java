package org.example;

public class TransactionUsingThreads {
    public static void main(String[] args) throws InterruptedException{
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        //first three threads are analysed using method reference
        Thread firstThread=new Thread(transactionAnalysis::displayAmount,"firstThread");
        firstThread.start();firstThread.join();
        Thread secondThread=new Thread(transactionAnalysis::displayReciepientNames,"secondThread");
        secondThread.start();secondThread.join();
        Thread thirdThread=new Thread(transactionAnalysis::displatTransactionDates,"thirdThread");
        thirdThread.start();thirdThread.join();

        //the remaining 7 threads directly interact with the methods defined in the run method(CLI)
        Thread fourthThread=new Thread(transactionAnalysis,"fourthThread");

        Thread fifthThread=new Thread(transactionAnalysis,"fifthThread");

        Thread sixthThread=new Thread(transactionAnalysis,"sixthThread");

        Thread seventhThread=new Thread(transactionAnalysis,"seventhThread");

        Thread eighthThread=new Thread(transactionAnalysis,"eighthThread");

        Thread ninethThread=new Thread(transactionAnalysis,"ninethThread");

        Thread tenthThread=new Thread(transactionAnalysis,"tenthThread");
        fourthThread.start();
        fifthThread.start();
        sixthThread.start();
        seventhThread.start();
        eighthThread.start();
        ninethThread.start();
        tenthThread.start();
    }

}
