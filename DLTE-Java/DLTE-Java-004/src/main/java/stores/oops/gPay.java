package stores.oops;

import java.util.Scanner;

public class gPay extends debitCard {
    private String userName;
    private int upiPin;

    public gPay(Long accountNumber, double accountBalance, String accountHolder, Long cardNumber, int cardPin, String userName, int upiPin) {
        super(accountNumber, accountBalance, accountHolder, cardNumber, cardPin);
        this.userName = userName;
        this.upiPin = upiPin;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUpiPin() {
        return upiPin;
    }

    public void setUpiPin(int upiPin) {
        this.upiPin = upiPin;
    }

    public void payBill(String billerName,double billerAmount,String billerType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the upi pin");
        int pin = scanner.nextInt();
        if (pin == getUpiPin()) {
            System.out.println("Payment Successfull to " + billerName + " of amount " + billerAmount);

        } else {
            System.out.println("Invalid Pin..Retry Again!!");
        }
    }
}
class Main{
    public static void main(String[] args){
       gPay account=new gPay(1345677L,12000,"Mahesh",12345678909L,12345,"Mahe@1234",9876);

       Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your Choice :"+"\n"+"1->Withdraw money "+"\n"+"2->Pay bills");
        int choice=scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("Enter the amount to be withdrawn");
                double amount=scanner.nextDouble();
                account.withdraw(amount);
                break;
            case 2:
                scanner.nextLine();
                System.out.println("Enter the biller name");
                String name=scanner.nextLine();
                System.out.println("Enter the biller amount");
                int amount1=scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the biller type");
                String type=scanner.nextLine();
                account.payBill(name,amount1,type);
                break;
        }
    }
}
