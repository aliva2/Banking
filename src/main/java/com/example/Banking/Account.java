package com.example.Banking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

    private static final Logger logger = LoggerFactory.getLogger(Account.class);

    @Id
    private String accountNumber;
    private double balance;
    private String accountHolderName;

    // Default constructor (needed for JPA)
    public Account() {}

    // Constructor
    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        if (balance < 0) {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    // Deposit method
    public void deposit(double amount) {
        if (isPositiveAmount(amount)) {
            balance += amount;  // Add the deposit amount to the balance
            logger.info("Deposited {} to account {}. New balance: {}", amount, accountNumber, balance);
        } else {
            logger.error("Deposit failed: Invalid amount {} for account {}", amount, accountNumber);
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (isPositiveAmount(amount)) {
            if (balance >= amount) {
                balance -= amount;
                logger.info("Withdrew {} from account {}. Remaining balance: {}", amount, accountNumber, balance);
            } else {
                logger.error("Insufficient funds for withdrawal from account {}. Current balance: {}", accountNumber, balance);
            }
        }
    }

    // Method to check if the amount is positive
    private boolean isPositiveAmount(double amount) {
        if (amount <= 0) {
            logger.error("Amount must be greater than 0. Invalid amount: {}", amount);
            return false;
        }
        return true;
    }

    // Method to display the current balance
    public void displayBalance() {
        System.out.println("Account Number: " + accountNumber + " | Balance: " + balance);
    }

    // Transfer method
    public static void transfer(Account sourceAccount, Account destinationAccount, double amount) {
        if (sourceAccount.isPositiveAmount(amount)) {
            if (sourceAccount.getBalance() >= amount) {
                sourceAccount.balance -= amount;
                destinationAccount.balance += amount;
                logger.info("Transferred: {} from Account {} to Account {}. Source Balance: {}, Destination Balance: {}",
                        amount, sourceAccount.accountNumber, destinationAccount.accountNumber,
                        sourceAccount.balance, destinationAccount.balance);
            } else {
                logger.error("Transfer failed. Insufficient funds in source account: {}", sourceAccount.accountNumber);
            }
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", accountHolderName='" + accountHolderName + '\'' +
                '}';
    }

    // Save accounts to a file
    public static void saveAccountsToFile(List<Account> accounts, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Account account : accounts) {
                writer.write(account.getAccountNumber() + "," + account.getAccountHolderName() + "," + account.getBalance());
                writer.newLine();
            }
            logger.info("Accounts saved successfully to file: {}", filename);
        } catch (IOException e) {
            logger.error("Error while saving accounts to file: {}", filename, e);
        }
    }

    // Read accounts from a file
    public static List<Account> readAccountsFromFile(String filename) {
        List<Account> accounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] accountData = line.split(",");
                String accountNumber = accountData[0];
                String accountHolderName = accountData[1];
                double balance = Double.parseDouble(accountData[2]);
                accounts.add(new Account(accountNumber, accountHolderName, balance));
            }
            logger.info("Accounts loaded successfully from file: {}", filename);
        } catch (IOException e) {
            logger.error("Error while reading accounts from file: {}", filename, e);
        }
        return accounts;
    }
}
