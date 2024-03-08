package org.example;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class MainTransactionExecutor {
    public static void main(String[] args){
        TransactionAnalysis transactionAnalysis=new TransactionAnalysis();
        final ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(1); //scheduling tasks for execution-------method creates a service instance that can schedule commands to run after a given delay or to execute periodically. and 1 is number of threads in the pool(execute 1 task at a time)
        final ScheduledFuture scheduledFuture= scheduledExecutorService.scheduleAtFixedRate(transactionAnalysis,1,5,TimeUnit.SECONDS);//1 is the initial delay before first execution,5 is the period between successive execution of tasks both are in seconds...scheduledFuture represents the pending completion of scheduled task
        scheduledExecutorService.schedule(new Runnable() {//execution of tasks for defined delay
            @Override
            public void run() {
                scheduledFuture.cancel(true);//cancels the scheduled task
                scheduledExecutorService.shutdown();//prevents accepting new task and complete existing tasks
            }
        },30,TimeUnit.SECONDS);
   }
}
