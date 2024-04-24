//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.19 at 07:30:52 AM IST 
//


package services.transactions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TransactionDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="transactionDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="transactionBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="transactionRemarks" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionDetails", propOrder = {
    "transactionId",
    "transactionDate",
    "transactionBy",
    "transactionTo",
    "transactionAmount",
    "transactionRemarks"
})
public class TransactionDetails {

    protected long transactionId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar transactionDate;
    @XmlElement(required = true)
    protected String transactionBy;
    @XmlElement(required = true)
    protected String transactionTo;
    protected double transactionAmount;
    @XmlElement(required = true)
    protected String transactionRemarks;

    /**
     * Gets the value of the transactionId property.
     * 
     */
    public long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     */
    public void setTransactionId(long value) {
        this.transactionId = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
    }

    /**
     * Gets the value of the transactionBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionBy() {
        return transactionBy;
    }

    /**
     * Sets the value of the transactionBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionBy(String value) {
        this.transactionBy = value;
    }

    /**
     * Gets the value of the transactionTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTo() {
        return transactionTo;
    }

    /**
     * Sets the value of the transactionTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTo(String value) {
        this.transactionTo = value;
    }

    /**
     * Gets the value of the transactionAmount property.
     * 
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the value of the transactionAmount property.
     * 
     */
    public void setTransactionAmount(double value) {
        this.transactionAmount = value;
    }

    /**
     * Gets the value of the transactionRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    /**
     * Sets the value of the transactionRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionRemarks(String value) {
        this.transactionRemarks = value;
    }

}
