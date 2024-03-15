package org.example;


import java.sql.Date;
import java.util.List;

public class UserServices {
    UserRepository userRepository;
    public UserServices(StorageTarget storageTarget){
        userRepository=storageTarget.getUserRepository();
    }


    public void callSave(User user){
        try{
            userRepository.save(user);
        }catch (UserException userException){
            throw userException;
        }
    }
    public User callFindById(String username){
        try{
            return userRepository.findById(username);
        }
        catch (UserException userException){
            throw userException;
        }
    }
    public List<User> callFindAll(){
        return userRepository.findAll();
    }

    public List<Transaction> callfindByUserAndDate(String username, Date transactionDate) {
        try {
            return userRepository.findByUserAndDate(username, transactionDate);
        } catch (UserException userException) {
            System.out.println("No such username");
            throw new UserException();
        }
    }
}
