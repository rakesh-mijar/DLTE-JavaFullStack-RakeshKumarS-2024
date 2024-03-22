package com.example.demo.remotes;

import com.example.demo.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//here useraccount is the entity class and Long is the type of id her(account number)
@Repository
public interface AccountRepository extends CrudRepository<UserAccount,Long> {
    UserAccount save(UserAccount userAccount);
    //UserAccount updateAccount(UserAccount userAccount);
    List<UserAccount> findAll();
}
