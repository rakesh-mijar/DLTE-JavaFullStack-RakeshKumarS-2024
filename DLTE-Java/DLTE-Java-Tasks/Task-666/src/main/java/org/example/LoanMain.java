package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanMain implements MyBank{
    @Override
    public void addNewLoan() throws IOException, ClassNotFoundException {//method for adding new loan to the arraylist
        Scanner scanner=new Scanner(System.in);
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
            Loan loans=new Loan(number,amount,date,status,name);
            readFromFile();
            loan.add(loans); //add each instance of loan to the arraylist loan
            writeIntoFile();
             }

    @Override
    public void checkAvailableLoans() {//method to print the loan which is open
        for (Loan each:loan){
            if (each.getLoanStatus().equalsIgnoreCase("open")){
                System.out.println(each.toString());
            }
            else {
                System.out.println("No loans available");
            }
        }
    }

    @Override
    public void checkClosedLoans() {//method to print the loan which is closed
        for (Loan each:loan){
            if(each.getLoanStatus().equalsIgnoreCase("closed")){
                System.out.println(each.toString());
            }
        }
    }



    private void writeIntoFile(){//method foe writing the content into the file
        try{
           FileOutputStream fileOutputStream=new FileOutputStream("Loan.txt",true);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(loan);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFromFile(){//method for reading the content from the file
        try{
            FileInputStream fileInputStream=new FileInputStream("Loan.txt");
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            loan.addAll((ArrayList<Loan>) objectInputStream.readObject());
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {//main metod with CLI gives option for tthe user to execute a particular task
        Scanner scanner = new Scanner(System.in);
        LoanMain loanMain = new LoanMain();
        while (true) {
            System.out.println("Enter your choice: 1->add loans" + "\n" + "2->check available loans " + "\n" + "3->check only closed loans");
            System.out.println("Enter your choice ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try{
                        loanMain.addNewLoan();
                    }catch (IOException exception1){
                        System.out.println(exception1.getMessage());
                    }catch (ClassNotFoundException exception2){
                        System.out.println(exception2.getMessage());
                    }
                    break;
                case 2:
                    loanMain.checkAvailableLoans();
                    break;
                case 3:
                    loanMain.checkClosedLoans();
                    break;
                default:
                    System.exit(0);
            }

        }
    }
}
