package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultipleXMLs {
    public static void main(String[] args) throws JAXBException, FileNotFoundException{
        //list of transaction to be converted to xml form.
        List<Transaction> transactions= Stream.of(
                new Transaction(new Date(2024,3,20),12000.0,"Mahesh","Family"),
                new Transaction(new Date(2024,1,28),13000.0,"Suresh","Friend"),
                new Transaction(new Date(2024,9,15),25000.0,"Razi","Bills")
        ).collect(Collectors.toList());

        MyTransactions myTransactions=new MyTransactions(transactions);
        //marshalling java to xml notations
        JAXBContext context=JAXBContext.newInstance(MyTransactions.class);
        Marshaller marshaller=context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(myTransactions,new FileOutputStream("transaction.xml"));
        System.out.println("XML has built");
    }
}
