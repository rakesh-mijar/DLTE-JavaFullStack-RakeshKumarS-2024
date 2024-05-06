package com.project.dao.remotes;

import com.project.dao.entities.MyBankCustomers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {
    MyBankCustomers signingUp(MyBankCustomers customer);
    MyBankCustomers findByUsername(String username);
    void updateAttempts(MyBankCustomers customer);
    void updateStatus(MyBankCustomers customer);

}
