package org.example;

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class Account {
    static ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    static Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private Long accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account(Long accountNumber, double accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
