package org.example;


import java.sql.Date;
import java.util.List;

public interface UserRepository {
    void save(User user);
    User findById(String username);
    List<User> findAll();
    List<Transaction> findAllByDateAndUsername(String username, Date date);
    List<Transaction> findAllUsername(String username);
}
