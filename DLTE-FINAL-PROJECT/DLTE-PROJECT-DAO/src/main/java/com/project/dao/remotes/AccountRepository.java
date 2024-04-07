package com.project.dao.remotes;

import com.project.dao.entities.Accounts;
import com.project.dao.entities.Customers;
import com.project.dao.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository {
    List<Accounts> filterByCustomerStatus(Long customerId) throws CustomerNotFoundException;
}
