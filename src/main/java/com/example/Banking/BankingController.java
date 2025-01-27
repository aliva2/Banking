package com.example.Banking;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/banking")
public class BankingController {

    private static final Logger logger = LoggerFactory.getLogger(BankingController.class);

    private final List<Account> accounts = new ArrayList<>();

    public BankingController() {
        // Sample accounts
        accounts.add(new Account("11111", "Anita", 500.00));
        accounts.add(new Account("22222", "Janis", 300.00));
        accounts.add(new Account("33333", "Anna", 100.00));
        accounts.add(new Account("44444", "Veronika", 0.00));
        accounts.add(new Account("55555", "Edvards", 400.00));
        logger.info("Sample accounts initialized.");
    }

    @GetMapping("/")
    public String hello() {
        logger.info("Received request to access root.");
        return "Welcome to Anita Bank!";
    }

    // List all accounts
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        logger.info("Received request to list all accounts");
        return accounts;
    }

    // Endpoint to get account info
    @GetMapping("/getAccount/{accountNumber}")
    public Account getAccount(@PathVariable String accountNumber) {
        logger.info("Received request to get account info for account: {}", accountNumber);
        return accounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    // Deposit into an account
    @PostMapping("/deposit/{accountNumber}/{amount}")
    public String deposit(@PathVariable String accountNumber, @PathVariable double amount) {
        logger.info("Received request to deposit {} into account: {}", amount, accountNumber);
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            logger.info("Deposit of {} into account {} was successful.", amount, accountNumber);  // Added success log
            return "Deposit of " + amount + " into account " + accountNumber + " was successful!";
        } else {
            logger.error("Account not found : {}", accountNumber);
            return "Account not found.";
        }
    }


    // Withdraw from an account
    @PostMapping("/withdraw/{accountNumber}/{amount}")
    public String withdraw(@PathVariable String accountNumber, @PathVariable double amount) {
        logger.info("Received request to withdraw {} from account: {}", amount, accountNumber);
        Account account = getAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            logger.info("Withdrawal of {} from account {} was successful.", amount, accountNumber);  // Added success log
            return "Withdrawal of " + amount + " from account " + accountNumber + " was successful!";
        } else {
            logger.error("Account not found: {}", accountNumber);
            return "Account not found.";
        }
    }


    // Transfer money between accounts
    @PostMapping("/transfer")
    public String transfer(@RequestParam String sourceAccountNumber,
                           @RequestParam String destinationAccountNumber,
                           @RequestParam double amount) {
        logger.info("Received request to transfer {} from account {} to account {}", amount, sourceAccountNumber, destinationAccountNumber);
        Account sourceAccount = getAccount(sourceAccountNumber);
        Account destinationAccount = getAccount(destinationAccountNumber);

        if (sourceAccount != null && destinationAccount != null) {
            Account.transfer(sourceAccount, destinationAccount, amount);
            return "Transfer successful! Source Account Balance: " + sourceAccount.getBalance() +
                    ", Destination Account Balance: " + destinationAccount.getBalance();
        } else {
            logger.error("One or both accounts not found. Source: {}, Destination: {}", sourceAccountNumber, destinationAccountNumber);
            return "Account(s) not found.";
        }
    }

    // Save accounts to file
    @PostMapping("/save")
    public String saveAccountsToFile(@RequestParam String filename) {
        logger.info("Received request to save accounts to file: {}", filename);
        Account.saveAccountsToFile(accounts, filename);
        return "Accounts saved to file: " + filename;
    }

    // Load accounts from file
    @PostMapping("/load")
    public String loadAccountsFromFile(@RequestParam String filename) {
        logger.info("Received request to load accounts from file: {}", filename);
        List<Account> loadedAccounts = Account.readAccountsFromFile(filename);
        accounts.clear();
        accounts.addAll(loadedAccounts);
        return "Accounts loaded from file: " + filename;
    }
}
