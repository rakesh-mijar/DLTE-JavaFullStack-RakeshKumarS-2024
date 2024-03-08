package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyBankDatabase<T> implements Activity<T> {
    T[] myObjects;
    public static void main(String[] args) {
        //MyBankDatabase<CreditCard> myBankDataBase = new MyBankDatabase();
        MyBankDatabase<CreditCard> myBankDatabase=new MyBankDatabase<>();

        myBankDatabase.myObjects=new CreditCard[3];//initialize myObjects
        CreditCard creditCard1 = new CreditCard(12345222L, "Mahesh", new Date(2024, 2, 8), 1234, 100000, new Date(2024, 05, 2), new Date(2024, 03, 5), 99887);
        CreditCard creditCard2 = new CreditCard(12536232L, "Suresh", new Date(2024, 7, 18), 4321, 50000, new Date(2024, 10, 20), new Date(2024, 12, 30), 77665);
        CreditCard creditCard3 = new CreditCard(23457522L, "Razi", new Date(2024, 4, 18), 54367, 30000, new Date(2024, 07, 8), new Date(2024, 07, 1), 55443);

        //read operations-1
        System.out.println(myBankDatabase.create(creditCard1));
        System.out.println(myBankDatabase.create(creditCard2));
        System.out.println(myBankDatabase.create(creditCard3));

        //delete
        System.out.println(myBankDatabase.delete(1));

        //view all
        myBankDatabase.viewAll();

        //update
        //System.out.println(myBankDatabase.update(0,creditCard3));

        MyBankDatabase<Transaction> myTransaction=new MyBankDatabase<>();

        myTransaction.myObjects=new Transaction[3];
        Transaction transaction1=new Transaction(new Date(2023,5,29),230000.0,"Sam","Education");
        Transaction transaction2=new Transaction(new Date(2024,7,12),120000.0,"Razi","Emergency");
        Transaction transaction3=new Transaction(new Date(2023,2,17),150000.0,"Prashanth","Bills");
        Transaction transaction4=new Transaction(new Date(2022,4,10),170000.0,"David","Health");

        //read operations-2
        System.out.println(myTransaction.create(transaction1));
        System.out.println(myTransaction.create(transaction2));
        System.out.println(myTransaction.create(transaction3));
        System.out.println(myTransaction.create(transaction4));

    }

    //implementation of methods defined in activity interface
    @Override
    public String create(T objectExample) {
        for (int index=0;index<myObjects.length;index++){
            if (myObjects[index] ==null){
                myObjects[index]= objectExample;
                return "Data Inserted successfully";
            }
        }
        return "Data is not inserted";
    }

    @Override
    public T read(int index) {
        if(index>=0 && index<myObjects.length){
            return myObjects[index];
        }
        return null;
    }

    @Override
    public void update(int index, T object) {
        if(index>=0&&index<=myObjects.length){
            myObjects[index]= object;
            System.out.println(object+" has updated @ "+index);
        }
    }

    @Override
    public String delete(int index) {
        if(index>=0 && index<=myObjects.length && myObjects[index] != null)
        {
            T object= myObjects[index];
            myObjects[index]= null;
            return object+" has deleted";
        }
        return null;
    }

    public void viewAll(){
        System.out.println(Arrays.toString(myObjects));
    }
}

