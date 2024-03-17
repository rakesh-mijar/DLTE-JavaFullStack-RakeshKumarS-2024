package org.example;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class MyTransactions {
    //creating a list of transaction with getters,setters and constructors of the attribute.
    private List<Transaction> transactions;

    public MyTransactions() {
    }

    public MyTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @XmlElement(name="transaction")
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "MyTransactions{" +
                "transactions=" + transactions +
                '}';
    }
}
