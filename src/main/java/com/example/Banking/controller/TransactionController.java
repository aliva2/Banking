package com.example.Banking.controller;

import com.example.Banking.model.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @PostMapping("/transaction")
    public String makeTransaction(@RequestBody Transaction transaction) {
        // Simulate transaction processing
        System.out.println("Processing transaction from " + transaction.getSenderName() + " to " + transaction.getReceiverName());
        return "Transaction completed successfully.";
    }
}
