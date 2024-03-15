package org.example;


import java.util.ResourceBundle;

public class UserException extends RuntimeException{
        public UserException(){
            super(ResourceBundle.getBundle("database").getString("user.exception"));
        }
        public UserException(String additionalInfo){
            super(ResourceBundle.getBundle("database").getString("user.exception")+" "+additionalInfo);
        }

}
