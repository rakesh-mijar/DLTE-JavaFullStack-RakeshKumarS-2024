package org.example;

import jdk.jfr.consumer.RecordedObject;

import java.util.logging.Level;

public class UserServices {
    UserFileRepository userFileRepository;
    public UserServices(StorageTarget storageTarget){
        userFileRepository=storageTarget.getUserFileRepository();
    }

    public UserServices() {

    }

    public void callSave(User user){
        try{
            userFileRepository.save(user);
        }catch (UserException userException){
            throw userException;
        }
    }
    public User callFindById(String username){
        try{
            return userFileRepository.findById(username);
        }
        catch (UserException userException){
            throw userException;
        }
    }
}
