package org.example;

import java.io.Serializable;
import java.util.Date;

public class CreditCard implements Serializable {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCVV;
    private Integer creditCardLimit;
    private Date dateOfBillGeneration;
    private Date dateOfBillPayement;
    private Integer CreditCardPin;

    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCVV, Integer creditCardLimit, Date dateOfBillGeneration, Date dateOfBillPayement, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCVV = creditCardCVV;
        this.creditCardLimit = creditCardLimit;
        this.dateOfBillGeneration = dateOfBillGeneration;
        this.dateOfBillPayement = dateOfBillPayement;
        CreditCardPin = creditCardPin;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(Integer creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }

    public Date getDateOfBillGeneration() {
        return dateOfBillGeneration;
    }

    public void setDateOfBillGeneration(Date dateOfBillGeneration) {
        this.dateOfBillGeneration = dateOfBillGeneration;
    }

    public Date getDateOfBillPayement() {
        return dateOfBillPayement;
    }

    public void setDateOfBillPayement(Date dateOfBillPayement) {
        this.dateOfBillPayement = dateOfBillPayement;
    }

    public Integer getCreditCardPin() {
        return CreditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        CreditCardPin = creditCardPin;
    }
}
