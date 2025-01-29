package com.example.Banking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BankingService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    @Transactional
    public String deposit(String accountNumber, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            return "Deposit successful! New balance: " + account.getBalance();
        } else {
            return "Account not found.";
        }
    }

    @Transactional
    public String withdraw(String accountNumber, double amount) {
        Optional<Account> optionalAccount = accountRepository.findById(accountNumber);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
                return "Withdrawal successful! Remaining balance: " + account.getBalance();
            } else {
                return "Insufficient funds.";
            }
        } else {
            return "Account not found.";
        }
    }

}
