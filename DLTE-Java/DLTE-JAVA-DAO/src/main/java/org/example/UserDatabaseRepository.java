package org.example;



import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;


import java.util.List;

import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class UserDatabaseRepository implements UserRepository {

    private Connection connection;
    private List<User> accountList = new ArrayList<>();
    private List<Transaction> accountList2=new ArrayList<>();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public UserDatabaseRepository(Connection connection) {
        this.connection=connection;
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("user-logs.txt", true);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.addHandler(fileHandler);
        } catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    @Override
    public void save(User user) {
        try {
            String query = "insert into mybank_users values(?,?,?,?,?,?)";
            preparedStatement=connection.prepareStatement(query);

            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3,user.getAddress());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setLong(5,user.getContact());
            preparedStatement.setDouble(6,user.getBalance());

            int result = preparedStatement.executeUpdate();
            if (result!=0) {
                logger.log(Level.INFO, resourceBundle.getString("record.push.ok"));
                System.out.println(resourceBundle.getString("record.push.ok"));
            }
            else {
                logger.log(Level.INFO, resourceBundle.getString("record.push.fail"));
                System.out.println(resourceBundle.getString("record.push.fail"));
            }

        } catch (SQLException sqlException) {
            System.out.println(resourceBundle.getString("account.not.ok"));
        }

    }

    @Override
    public User findById(String username) {
       User user=null;
       try{
           String query="select * from mybank_users where username=?";
           preparedStatement=connection.prepareStatement(query);
           preparedStatement.setString(1,username);
           resultSet=preparedStatement.executeQuery();
           if(resultSet.next()){
               user=new User();
               user.setUsername(resultSet.getString(1));
               user.setPassword(resultSet.getString(2));
               user.setAddress(resultSet.getString(3));
               user.setEmail(resultSet.getString(4));
               user.setContact(resultSet.getLong(5));
               user.setBalance(resultSet.getDouble(6));
           }
           else{
               logger.log(Level.INFO,resourceBundle.getString("user.not.exists"));
               System.out.println(resourceBundle.getString("user.not.exists"));
               throw new UserException();
           }
       }catch (SQLException sqlException){
           System.out.println(sqlException);
       }
        return user;
    }

    @Override
    public List<User> findAll() {
        try{
            String query="select * from mybank_users";
            preparedStatement=connection.prepareStatement(query);
            resultSet=preparedStatement.executeQuery();
            accountList=new ArrayList<>();
            User user=null;
            while(resultSet.next()){
                user=new User();
                user.setUsername(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setAddress(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setContact(resultSet.getLong(5));
                accountList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountList;
    }

    @Override
    public List<Transaction> findByUserAndDate(String username, Date date) {
        List<Transaction> transactionList = new ArrayList<>();
        try {
            String query = "select * from transactions where  transaction_date=?";
            preparedStatement=connection.prepareStatement(query);
            //preparedStatement.setString(1,username);
            preparedStatement.setDate(1, date);
            resultSet = preparedStatement.executeQuery();
            Transaction transaction = null;
            while (resultSet.next()) {
                transaction = new Transaction();
                transaction.setTransactionDoneBy(resultSet.getString(1));
                transaction.setTransactionType(resultSet.getString(2));
                transaction.setTransactionAmount(resultSet.getDouble(3));
                transaction.setTransactionDate(resultSet.getDate(4));
                transactionList.add(transaction);
            }
            if(transactionList.size()==0)
                throw new UserException();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionList;
    }


}
