package org.example;

import java.util.ResourceBundle;

public class UserException extends RuntimeException{
    public UserException(){
        super(ResourceBundle.getBundle("user").getString("user.exception"));
    }
    public UserException(String additionalInfo){
        super(ResourceBundle.getBundle("user").getString("user.exception")+" "+additionalInfo);
    }

}
