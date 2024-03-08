package org.example;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends Account {
    private String userName;
    private int upiPin;

    public Gpay(Long accountNumber, double accountBalance, String accountHolder, String userName, int upiPin) {
        super(accountNumber, accountBalance, accountHolder);
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

    public void payBill(String billerName, double billerAmount, String billerType) {
        Scanner scanner = new Scanner(System.in);
        int count=0;
        while(count<5){
            try{
                System.out.println("Enter the upi pin");
                int pin = scanner.nextInt();
                if (pin==getUpiPin()) {//checks if the entered pin is equal to actual upi-pin
                    System.out.println(resourceBundle.getString("payment.ok"));//inform the payment is done
                    logger.log(Level.INFO,"Payment Done");
                    break;
                } else {
                    throw new MyBankException();//if invalid pin entered then raise exception
                }
            }catch (MyBankException exception){//handle the exception
                System.out.println(resourceBundle.getString("payment.not.ok"));//
                logger.log(Level.WARNING,"Incorrect pin entered");
                count++;
                System.out.println();
            }

        }
        if(count==5){
            System.out.println(resourceBundle.getString("account.blocked"));
            logger.log(Level.WARNING,"Maximum Limit exceeded");
        }

    }
}
class Main{

    public static void main(String[] args){
        Gpay account=new Gpay(1345677L,12000,"Mahesh","Surendra",12345);

        Scanner scanner=new Scanner(System.in);
        System.out.println("Pay bills");
        System.out.println("Enter the biller name");
        String name=scanner.nextLine();
        System.out.println("Enter the biller amount");
        int amount1=scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the biller type");
        String type=scanner.nextLine();
        account.payBill(name,amount1,type);
    }
}

