package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.InflaterInputStream;

public class MyBankDatabase<T> implements Activity<T> {
    ArrayList<T> creditCardArrayList=new ArrayList<>();
    @Override
    public void create(T object) throws IOException {//implementation of method create in Activity interface
        creditCardArrayList.add(object);
        System.out.println("Inserted into file");
    }
    private void writeToFile() throws IOException {//this is used to write the contents into file with appending mode
        //File file=new File("CreditCard.txt");
        FileOutputStream fileOutputStream=new FileOutputStream("CreditCard.txt",true);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(creditCardArrayList);

        objectOutputStream.close();
        fileOutputStream.close();
    }
    private void readFromFile() throws IOException, ClassNotFoundException {//this is used to read the contents from te file
        FileInputStream fileInputStream=new FileInputStream("CreditCard.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        creditCardArrayList= (ArrayList<T>)objectInputStream.readObject();
        System.out.println(creditCardArrayList);

        objectInputStream.close();
        fileInputStream.close();
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> myBankDatabase=new MyBankDatabase<>();

        CreditCard creditCard1=new CreditCard(1234567L, "Sam", new Date(2024, 2, 23), 12345, 100000, new Date(2025, 3, 25), new Date(2025, 6, 23), 12345);
        CreditCard creditCard2=new CreditCard(7654321L, "Muzain", new Date(2024, 6, 20), 13345, 250000, new Date(2024, 9, 31), new Date(2024, 1, 13), 554432);
        CreditCard creditCard3=new CreditCard(9874567L, "Joshi", new Date(2024, 10, 3), 98734, 10000, new Date(2024, 7, 20), new Date(2024, 9, 3), 21345);
        //add the three instances of Creditcard into the file
        myBankDatabase.create(creditCard1);
        myBankDatabase.create(creditCard2);
        myBankDatabase.create(creditCard3);

        //write the content into the file "creditcard.txt"
        myBankDatabase.writeToFile();

        //print the contents of file
        System.out.println("Contents in file");
        myBankDatabase.readFromFile();

    }
}




