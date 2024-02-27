package stores.oops;

import java.util.Scanner;
public class mainLoan implements myBank{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        mainLoan obj=new mainLoan();
        while(true){
            System.out.println("Enter your choice: 1->add loans"+"\n"+"2->check available loans "+"\n"+"3->check only closed loans");
            System.out.println("Enter your choice ");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    obj.addNewLoan();
                    break;
                case 2:
                    obj.checkAvailableLoans();
                    break;
                case 3:
                    obj.checkOnlyClosedLoans();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
    static int loanCount;
    @Override
    public void addNewLoan() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the number of loans to be added");
        loanCount=scanner.nextInt();
        for(int base=0;base<loanCount;base++)
        {
            System.out.println("Enter the loan number");
            long number=scanner.nextLong();
            System.out.println("Enter the loan amount");
            double amount=scanner.nextDouble();
            System.out.println("Enter the loan date");
            String date =scanner.next();
            System.out.println("Enter the loan status(open/closed)");
            String status=scanner.next();

            scanner.nextLine();

            System.out.println("Enter the borrower name");
            String name=scanner.nextLine();
            System.out.println("Enter the borrower contact");
            long contact=scanner.nextLong();
            loan[base]=new Loan(number,amount,date,status,name,contact);
        }
    }

    @Override
    public void checkAvailableLoans() {
        for(int base=0;base<loanCount;base++){
            if(loan[base]!=null){
                if(loan[base].getLoanStatus().equalsIgnoreCase("open")){
                    System.out.println(loan[base].toString());
                }
                else {
                    System.out.println("No loans available");
                }
            }
        }

    }


    @Override
    public void checkOnlyClosedLoans() {
        for(int base2=0;base2<loanCount;base2++){
            if(loan[base2]!=null){
                if(loan[base2].getLoanStatus().equalsIgnoreCase("closed")){
                    System.out.println(loan[base2].toString());
                }
            }
        }
        System.out.println("No loans available");
    }
}
