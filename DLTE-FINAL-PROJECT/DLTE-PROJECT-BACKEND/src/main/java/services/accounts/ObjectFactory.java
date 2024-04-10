//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.04.05 at 04:55:42 PM IST 
//


package services.accounts;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.accounts package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.accounts
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FilterByStatusRequest }
     * 
     */
    public FilterByStatusRequest createFilterByStatusRequest() {
        return new FilterByStatusRequest();
    }

    /**
     * Create an instance of {@link FilterByIdResponse }
     * 
     */
    public FilterByIdResponse createFilterByIdResponse() {
        return new FilterByIdResponse();
    }

    /**
     * Create an instance of {@link ServiceStatus }
     * 
     */
    public ServiceStatus createServiceStatus() {
        return new ServiceStatus();
    }

    /**
     * Create an instance of {@link Customers }
     * 
     */
    public Customers createCustomers() {
        return new Customers();
    }

    /**
     * Create an instance of {@link FilterByIdRequest }
     * 
     */
    public FilterByIdRequest createFilterByIdRequest() {
        return new FilterByIdRequest();
    }

    /**
     * Create an instance of {@link FilterByStatusResponse }
     * 
     */
    public FilterByStatusResponse createFilterByStatusResponse() {
        return new FilterByStatusResponse();
    }

    /**
     * Create an instance of {@link Accounts }
     * 
     */
    public Accounts createAccounts() {
        return new Accounts();
    }

}