package stores.oops;

import java.util.Scanner;

public class debitCard extends Account2 {
        public debitCard(Long accountNumber, double accountBalance, String accountHolder, Long cardNumber, int cardPin) {
                super(accountNumber, accountBalance, accountHolder);
                this.cardNumber = cardNumber;
                this.cardPin = cardPin;
        }

        private Long cardNumber;
        private int cardPin;



        public void withdraw(double amount) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the pin");
                int pin = scanner.nextInt();
                if(pin==getCardPin()){
                        if(amount <=getAccountBalance() ){
                                System.out.println("Withdrawl Approved");
                                setAccountBalance(getAccountBalance() - amount);
                                System.out.println("Balance Amount "+getAccountBalance() );
                        }
                        else{
                                System.out.println("Insufficient Balance");
                        }
                }
                else{
                        System.out.println("Incorrect Pin..Try Again!!");
                }
        }

        public Long getCardNumber() {
                return cardNumber;
        }

        public void setCardNumber(Long cardNumber) {
                this.cardNumber = cardNumber;
        }

        public int getCardPin() {
                return cardPin;
        }

        public void setCardPin(int cardPin) {
                this.cardPin = cardPin;
        }

}