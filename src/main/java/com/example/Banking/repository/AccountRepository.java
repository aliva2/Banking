package com.example.Banking.repository;

import com.example.Banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByAccountNumber(String accountNumber);
}
