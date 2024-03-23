package com.example.demojdbc;

public class TransactionException extends RuntimeException {
    public TransactionException(){
        super("No such Transaction available");
    }
    public TransactionException(String info){
        super("No such Transaction available "+info);
    }
}
