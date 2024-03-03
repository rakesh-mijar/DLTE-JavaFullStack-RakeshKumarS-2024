package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserFileRepository implements UserRepository {
    private String filePath;
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("user");
    private Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private List<User> userList=new ArrayList<>();
    public UserFileRepository(String url){
        filePath=url;
        try{
            FileHandler fileHandler=new FileHandler("user-logs.txt",true);
            SimpleFormatter simpleFormatter=new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeIntoFile(){
        try{
            FileOutputStream fileOutputStream=new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(userList);

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void readFromFile(){
        try{
            FileInputStream fileInputStream=new FileInputStream(filePath);
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            userList=(List<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        readFromFile();
        User object= userList.stream().filter(each->each.getUserName().equals(user.getUserName())).findFirst().orElse(null);
        if(object!=null){
            logger.log(Level.WARNING,user.getUserName()+resourceBundle.getString("user exists"));
            throw new UserException();
        }
        userList.add(user);
        writeIntoFile();
        logger.log(Level.INFO,user.getUserName()+resourceBundle.getString("user.saved"));
        System.out.println(user.getUserName()+resourceBundle.getString("user.saved"));


    }

    @Override
    public User findById(String username) {
        readFromFile();
        User object=userList.stream().filter(each->each.getUserName().equals(username)).findFirst().orElse(null);
        if(object==null){
            logger.log(Level.WARNING,username+resourceBundle.getString("user.notExists"));
            System.out.println(username+" " +resourceBundle.getString("user.notExists"));
            throw new UserException();
        }
        return object;
    }
}
