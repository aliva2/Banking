package com.example.Banking;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankingService {

    private Map<String, Account> accounts = new HashMap<>();

    // Initialize some dummy accounts
    public BankingService() {
        accounts.put("1", new Account("1", "John Doe", 1000.00));
        accounts.put("2", new Account("2", "Jane Smith", 1500.00));
    }

    public Account getAccount(String accountId) throws Exception {
        if (!accounts.containsKey(accountId)) {
            throw new Exception("Account not found");
        }
        return accounts.get(accountId);
    }

    public Account deposit(String accountId, double amount) throws Exception {
        Account account = getAccount(accountId);
        account.deposit(amount);
        return account;
    }
}
