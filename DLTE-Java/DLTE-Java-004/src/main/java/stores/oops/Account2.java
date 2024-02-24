package stores.oops;

public class Account2 {
    private Long accountNumber;
    private double accountBalance;
    private String accountHolder;

    public Account2(Long accountNumber, double accountBalance, String accountHolder) {
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
