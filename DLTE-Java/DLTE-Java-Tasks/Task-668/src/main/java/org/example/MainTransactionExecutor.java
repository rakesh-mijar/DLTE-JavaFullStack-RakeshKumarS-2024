package org.example;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class MainTransactionExecutor {
    public static void main(String[] args){
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        final ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(1);
        final ScheduledFuture scheduledFuture= scheduledExecutorService.scheduleAtFixedRate(transactionAnalysis,1,5,TimeUnit.SECONDS);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                scheduledFuture.cancel(true);
                scheduledExecutorService.shutdown();
            }
        },30,TimeUnit.SECONDS);
   }
}
