package com.example.Banking.service;

import com.example.Banking.model.Account;
import com.example.Banking.repository.AccountRepository;
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

    // Transfer money between two accounts
    // Merge internal and external transfer logic into separate methods
    @Transactional
    public String transferInternal(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        return transfer(sourceAccountNumber, destinationAccountNumber, amount);
    }

    @Transactional
    public String transferExternal(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        return transfer(sourceAccountNumber, destinationAccountNumber, amount);
    }

    // Common transfer logic for both internal and external transfers
    @Transactional
    public String transfer(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        Optional<Account> sourceOptional = accountRepository.findById(sourceAccountNumber);
        Optional<Account> destinationOptional = accountRepository.findById(destinationAccountNumber);

        if (sourceOptional.isPresent() && destinationOptional.isPresent()) {
            Account sourceAccount = sourceOptional.get();
            Account destinationAccount = destinationOptional.get();

            if (sourceAccount.getBalance() >= amount) {
                // Withdraw from source account
                sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                accountRepository.save(sourceAccount);

                // Deposit to destination account
                destinationAccount.setBalance(destinationAccount.getBalance() + amount);
                accountRepository.save(destinationAccount);

                return "Transfer successful! Source Account Balance: " + sourceAccount.getBalance() +
                        ", Destination Account Balance: " + destinationAccount.getBalance();
            } else {
                return "Insufficient funds in the source account.";
            }
        } else {
            return "One or both accounts not found.";
        }
    }}
