import java.util.Scanner;

public class DepositAction implements BankAction {
    @Override
    public void execute(Scanner scanner, BankAccount account1, BankAccount account2) {
        System.out.println("Select account to deposit from 1 or 2:");
        int accountChoice = scanner.nextInt();
        System.out.println("Amount to deposit: ");
        double amount = scanner.nextDouble();

        if (accountChoice == 1) {
            account1.deposit(amount); // Deposit into account1
        } else if (accountChoice == 2) {
            account2.deposit(amount); // Deposit into account2
        } else {
            System.out.println("Invalid account selection.");
        }
    }
}
