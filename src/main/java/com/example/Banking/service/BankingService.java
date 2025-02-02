package com.example.Banking.service;

import com.example.Banking.model.Account;
import com.example.Banking.model.Transfer;
import com.example.Banking.repository.AccountRepository;
import com.example.Banking.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BankingService {

    private final AccountRepository accountRepository;
    private final TransferRepository transferRepository;
    @Autowired
    public BankingService(AccountRepository accountRepository, TransferRepository transferRepository) {
        this.accountRepository = accountRepository;
        this.transferRepository = transferRepository;
    }

    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    // Deposit method
    @Transactional
    public String deposit(String accountNumber, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        // Record the deposit transfer
        Transfer transfer = new Transfer();
        transfer.setFromAccountNumber(null);
        transfer.setToAccountNumber(accountNumber);
        transfer.setAmount(amount);
        transfer.setTransferType("deposit");
        transferRepository.save(transfer);

        return "Deposit successful! New balance: " + account.getBalance();
    }


    // Withdraw method
    @Transactional
    public String withdraw(String accountNumber, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transfer transfer = new Transfer();
        transfer.setFromAccountNumber(accountNumber);
        transfer.setToAccountNumber(null);
        transfer.setAmount(amount);
        transfer.setTransferType("withdrawal");
        transferRepository.save(transfer);

        return "Withdrawal successful! Remaining balance: " + account.getBalance();
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

    @Transactional
    public String transfer(String fromAccountNumber, String toAccountNumber, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found.");
        }

        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        // Deduct from sender and add to receiver
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Record the transfer
        Transfer transfer = new Transfer();
        transfer.setFromAccountNumber(fromAccountNumber);
        transfer.setToAccountNumber(toAccountNumber);
        transfer.setAmount(amount);
        transfer.setTransferType("transfer");
        transferRepository.save(transfer);

        return "Transfer successful! New balance for " + fromAccountNumber + ": " + fromAccount.getBalance() + ", New balance for " + toAccountNumber + ": " + toAccount.getBalance();
    }

}
