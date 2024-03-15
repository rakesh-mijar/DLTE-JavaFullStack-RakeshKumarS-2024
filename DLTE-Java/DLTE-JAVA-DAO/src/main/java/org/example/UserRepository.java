package org.example;


import java.sql.Date;
import java.util.List;

public interface UserRepository {
    void save(User user);
    User findById(String username);
    List<User> findAll();
    List<Transaction> findByUserAndDate(String username, Date date);
}
