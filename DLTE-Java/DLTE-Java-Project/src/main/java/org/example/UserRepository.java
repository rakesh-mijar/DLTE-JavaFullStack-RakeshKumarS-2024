package org.example;
import org.example.User;
public interface UserRepository {
    void save(User user);
    User findById(String username);
}
