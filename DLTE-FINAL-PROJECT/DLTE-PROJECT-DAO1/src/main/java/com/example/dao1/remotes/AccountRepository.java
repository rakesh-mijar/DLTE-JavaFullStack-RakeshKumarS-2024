package com.example.dao1.remotes;

import com.example.dao1.entities.Accounts;

import java.util.List;

public interface AccountRepository {
    List<Accounts> filterByStatus(Accounts accounts);
}
