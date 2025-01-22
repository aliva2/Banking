import java.util.Scanner;

public class WithdrawAction implements BankAction {
    @Override
    public void execute(Scanner scanner, BankAccount account1, BankAccount account2) {
        System.out.println("Select account to withdraw from 1 or 2:");
        int accountChoice = scanner.nextInt();
        System.out.println("Amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (accountChoice == 1) {
            account1.withdraw(amount); // Withdraw from account1
        } else if (accountChoice == 2) {
            account2.withdraw(amount); // Withdraw from account2
        } else {
            System.out.println("Invalid account selection.");
        }
    }
}