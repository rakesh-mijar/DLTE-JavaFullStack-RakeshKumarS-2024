package com.project.dao.remotes;

import com.project.dao.entities.Accounts;
import org.springframework.stereotype.Repository;

import java.rmi.ServerException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface AccountRepository {
    List<Accounts> filterByCustomerStatus(Long customerId) throws  SQLSyntaxErrorException, ServerException;
    Accounts UpdateAccountService(Accounts accounts) throws ServerException;
}
