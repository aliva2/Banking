import java.util.Scanner;

public class TransferBalanceAction implements BankAction {
    @Override
    public void execute(Scanner scanner, BankAccount account1, BankAccount account2) {
        System.out.println("Select account to transfer from:");
        System.out.println("1. Account 1");
        System.out.println("2. Account 2");
        int fromAccountChoice = scanner.nextInt();

        System.out.println("Amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (fromAccountChoice == 1) {
            account1.transferBalance(account1, account2, amount);
        } else if (fromAccountChoice == 2) {
            account1.transferBalance(account2, account1, amount); // Transfer from account 2 to 1
        } else {
            System.out.println("Invalid account selection.");
        }
    }
}