import java.util.Scanner;

public class ExitAction implements BankAction {
    @Override
    public void execute(Scanner scanner, BankAccount account1, BankAccount account2) {
        System.out.println("Exiting.");
        System.exit(0);
    }
}