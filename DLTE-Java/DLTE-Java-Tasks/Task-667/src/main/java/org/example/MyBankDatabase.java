package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> creditCardArrayList=new ArrayList<>();
    @Override
    public void create(T object) {
        creditCardArrayList.add(object);
        System.out.println("Inserted into file");
    }
    public void writeToFile() throws IOException {

        FileOutputStream fileOutputStream=new FileOutputStream("CreditCard.txt",true);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditCardArrayList);

        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("CreditCard.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        ArrayList<CreditCard> credit=(ArrayList<CreditCard>) objectInputStream.readObject();
        //int size=creditCardArrayList.size();
        System.out.println(credit);
//        for(int index=0;index<size;index++){
//            if(creditCardArrayList.get(index)!=null){
//                System.out.println(creditCardArrayList.get(index).toString());
//            }
//        }
        objectInputStream.close();
        fileInputStream.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> myBankDatabase=new MyBankDatabase();
        CreditCard creditCard1=new CreditCard(1234567L, "Sam", new Date(2024, 2, 23), 12345, 100000, new Date(2025, 3, 25), new Date(2025, 6, 23), 12345);
        CreditCard creditCard2=new CreditCard(7654321L, "Muzain", new Date(2024, 6, 20), 13345, 250000, new Date(2024, 9, 31), new Date(2024, 1, 13), 554432);
        CreditCard creditCard3=new CreditCard(9874567L, "Joshi", new Date(2024, 10, 3), 98734, 10000, new Date(2024, 7, 20), new Date(2024, 9, 3), 21345);
        myBankDatabase.create(creditCard1);
        myBankDatabase.create(creditCard2);
        myBankDatabase.create(creditCard3);
        myBankDatabase.writeToFile();
        System.out.println("Contents in file");
        myBankDatabase.readFromFile();

    }
}
