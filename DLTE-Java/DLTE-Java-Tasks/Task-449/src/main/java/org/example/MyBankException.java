package org.example;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException {
    public MyBankException(){
        super(ResourceBundle.getBundle("application").getString("exception.credit"));
    }
}
