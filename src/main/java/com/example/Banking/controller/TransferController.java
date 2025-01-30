package com.example.Banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankaccounts/api/transfer")
public class TransferController {

    // Endpoint to handle external transfers
    @PostMapping("/external")
    public ResponseEntity<String> createDebitTransfer(@RequestBody TransferRequest transferRequest) {
        // Validate transfer request (this is just a mock validation, replace with your actual logic)
        if (isValidTransfer(transferRequest)) {
            // Simulate transaction logic here (e.g., check if the accounts exist and process the transfer)
            // You would add logic to update balances in your service or repository layer.
            return ResponseEntity.status(HttpStatus.OK).body("Transaction was successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transaction failed");
        }
    }

    // Example validation logic (replace with real checks)
    private boolean isValidTransfer(TransferRequest transferRequest) {
        // For simplicity, we assume the transfer amount is positive and accounts are valid
        return transferRequest.getAmount() > 0 && !transferRequest.getFromAccountNumber().isEmpty() && !transferRequest.getToAccountNumber().isEmpty();
    }

    // Request body for transfer (you can customize this based on your needs)
    public static class TransferRequest {
        private String fromAccountNumber;
        private String toAccountNumber;
        private double amount;

        // Getters and Setters
        public String getFromAccountNumber() {
            return fromAccountNumber;
        }

        public void setFromAccountNumber(String fromAccountNumber) {
            this.fromAccountNumber = fromAccountNumber;
        }

        public String getToAccountNumber() {
            return toAccountNumber;
        }

        public void setToAccountNumber(String toAccountNumber) {
            this.toAccountNumber = toAccountNumber;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
