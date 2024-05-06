package com.project.dao;
import static org.junit.jupiter.api.Assertions.*;

import com.project.dao.entities.Accounts;
import com.project.dao.entities.MyBankCustomers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.ResourceBundle;
import java.util.Set;

@SpringBootTest
public class ValidationTest {

    @Autowired
    private LocalValidatorFactoryBean validatorFactoryBean;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("accounts");
    @Test
    public void testAccountValidation3() {
        Accounts account = new Accounts();
        account.setAccountId(1L);
        account.setCustomerId(1L);
        account.setAccountType("123");
        account.setAccountNumber(123456721L);
        account.setAccountStatus("Activ");
        account.setAccountBalance(-1000D);


        Set<ConstraintViolation<Accounts>> accounts = validatorFactoryBean.validate(account);

        if (!accounts.isEmpty()) {
            for (ConstraintViolation<Accounts> violation : accounts) {
                String property = violation.getPropertyPath().toString();
                String messageKey = violation.getMessage();
                String actualMessage = resourceBundle.getString(messageKey);

                System.out.println("Property: " + property);
                System.out.println("Message: " + actualMessage);


                switch (property) {
                    case "accountNumber":
                        assertEquals("Account number should be 12 digits", actualMessage, "Incorrect message for accountNumber validation");
                        break;
                    case "accountType":
                        assertEquals("Invalid account type. Only alphabetic characters are allowed.", actualMessage, "Incorrect message for accountType validation");
                        break;
                    case "accountStatus":
                        assertEquals("Invalid account status. It must be either 'active' or 'inactive'.", actualMessage, "Incorrect message for accountStatus validation");
                        break;
                    case "accountBalance":
                        assertEquals("The account balance is limited to 10 digits", actualMessage, "Incorrect message for accountBalance validation");
                        break;
                    default:
                        fail("Unexpected violation for property: " + property);
                }
            }
        }
    }
    @Test
    public void testMyBankCustomersValidation(){
        MyBankCustomers myBankCustomers=new MyBankCustomers();
        myBankCustomers.setCustomerId(1L);
        myBankCustomers.setCustomerName("Maadeva");
        myBankCustomers.setCustomerAddress("kengeri, bangalore");
        myBankCustomers.setCustomerStatus("Active");
        myBankCustomers.setCustomerContact(12345678909L);
        myBankCustomers.setUsername("maadeva(123");
        myBankCustomers.setPassword("ma@12390");


        Set<ConstraintViolation<MyBankCustomers>> violations=validatorFactoryBean.validate(myBankCustomers);

        if(!violations.isEmpty()){
            for (ConstraintViolation<MyBankCustomers> violation : violations) {
                String property = violation.getPropertyPath().toString();
                String messageKey = violation.getMessage();
                String  message=resourceBundle.getString(messageKey);
                System.out.println(message);
                System.out.println("Property: " + property);
                System.out.println("Message: " + message);

                // Using switch statement to handle different property violations
                switch (property) {
                    case "customerName":
                        assertEquals("name is not valid", message, "Incorrect message for customerName validation");
                        break;
                    case "customerAddress":
                        assertEquals("enter valid address", message, "Incorrect message for customerAddress validation");
                        break;
                    case "customerStatus":
                        assertEquals("Customer status is invalid", message, "Incorrect message for customerStatus validation");
                        break;
                    case "customerContact":
                        assertEquals("Enter 10 digits only", message, "Incorrect message for customerContact validation");
                        break;
                    case "username":
                        assertEquals("enter valid username", message, "Incorrect message for username validation");
                        break;
                    case "password":
                        assertEquals("Password is invalid", message, "Incorrect message for password validation");
                        break;
                    default:
                        fail("Unexpected violation for property: " + property);
                }
            }
        }
    }

}
