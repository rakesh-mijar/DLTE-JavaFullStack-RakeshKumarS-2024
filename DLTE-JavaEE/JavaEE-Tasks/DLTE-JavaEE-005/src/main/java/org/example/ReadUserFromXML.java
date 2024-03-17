package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ReadUserFromXML
{
    public static void main( String[] args )  {
        try{
        //JAXBContext is an abstract class helpful for marshalling and unmarshalling
        JAXBContext context=JAXBContext.newInstance(MyTransactions.class);
        //createUnmarshaller() is a method from JAXB  class
        Unmarshaller unmarshaller=context.createUnmarshaller();
        //unmarshal is defied from Unmarshaller interface
        MyTransactions myTransactions=(MyTransactions) unmarshaller.unmarshal(new FileInputStream("transaction.xml"));
        List<Transaction> transactions=myTransactions.getTransactions();

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the username");
        String user=scanner.next();

        boolean userFound = false;
        for (Transaction transaction : transactions) {
            if (transaction.getReciept().equalsIgnoreCase(user)) {
                System.out.println(transaction.toString());
                userFound = true;
            }
        }

        if (!userFound) {
            System.out.println("No transactions found for user: " + user);
        }

        } catch (JAXBException e) {

        System.out.println("JAXBException occurred: " + e.getMessage());
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    }

    }
}
