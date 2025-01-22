import java.util.Scanner;

public class PrintBalanceAction implements BankAction {
    @Override
    public void execute(Scanner scanner, BankAccount account1, BankAccount account2) {
        System.out.println("Select account to print balance from 1 or 2:");
        int accountChoice = scanner.nextInt();

        // Show balance for Account 1
        if (accountChoice == 1) {
            System.out.println("Account 1:");
            account1.printBalance();
        }
        // Show balance for Account 2
        else if (accountChoice == 2) {
            System.out.println("Account 2:");
            account2.printBalance();
        } else {
            System.out.println("Invalid account selection.");
        }
    }
}