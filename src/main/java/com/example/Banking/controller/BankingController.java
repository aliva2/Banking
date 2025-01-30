package com.example.Banking.controller;

import com.example.Banking.model.Account;
import com.example.Banking.service.BankingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;



@RestController
@RequestMapping("/api/v1")
public class BankingController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Anita Bank!";
    }

    @Autowired
    private BankingService bankingService;

    private static final Logger logger = LoggerFactory.getLogger(BankingController.class);

    @Operation(summary = "Get account details", description = "Fetch the details of a bank account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @GetMapping("/accounts/{accountNumber}")
    public Optional<Account> getAccount(@PathVariable String accountNumber) {
        return bankingService.getAccount(accountNumber);
    }

    @Operation(summary = "Deposit money into an account", description = "Deposit a certain amount into the specified account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deposit successful"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    @PostMapping("/deposit/{accountNumber}/{amount}")
    public String deposit(@PathVariable String accountNumber, @PathVariable double amount) {
        return bankingService.deposit(accountNumber, amount);
    }

    @Operation(summary = "Withdraw money from an account", description = "Withdraw a certain amount from the specified account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Withdrawal successful"),
            @ApiResponse(responseCode = "404", description = "Account not found"),
            @ApiResponse(responseCode = "400", description = "Insufficient funds")
    })
    @PostMapping("/withdraw/{accountNumber}/{amount}")
    public String withdraw(@PathVariable String accountNumber, @PathVariable double amount) {
        return bankingService.withdraw(accountNumber, amount);
    }

    // Transfer money between accounts
    @Operation(summary = "Transfer money between accounts", description = "Transfer money from one account to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer successful"),
            @ApiResponse(responseCode = "400", description = "Invalid amount or insufficient funds"),
            @ApiResponse(responseCode = "404", description = "One or both accounts not found")
    })
    @PostMapping("/accounts/transfer")
    public String transfer(@RequestParam String sourceAccountNumber,
                           @RequestParam String destinationAccountNumber,
                           @RequestParam double amount,
                           @RequestParam String transferType) {  // Add transferType (internal or external)

        // Call the bankingService.transfer method with account numbers and transfer type
        if ("internal".equalsIgnoreCase(transferType)) {
            return bankingService.transferInternal(sourceAccountNumber, destinationAccountNumber, amount);
        } else if ("external".equalsIgnoreCase(transferType)) {
            return bankingService.transferExternal(sourceAccountNumber, destinationAccountNumber, amount);
        } else {
            return "Invalid transfer type. Please specify either 'internal' or 'external'.";
        }
    }

}
