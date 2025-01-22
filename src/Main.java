//Create a .gitignore file (https://git-scm.com/docs/gitignore )
//Upload Banking  project to your GitHub account, if you do not have one yet, please sign up**.

//for Scanner
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Bank accounts for trying program
        BankAccount account1 = new BankAccount(100.00);
        BankAccount account2 = new BankAccount(200.00);

        // Create instances of each action
        BankAction depositAction = new DepositAction();
        BankAction withdrawAction = new WithdrawAction();
        BankAction printBalanceAction = new PrintBalanceAction();
        BankAction transferBalanceAction = new TransferBalanceAction();
        BankAction exitAction = new ExitAction();

        while(true) {

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Print Balance");
            System.out.println("4. Transfer Balance");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> depositAction.execute(scanner, account1, account2);
                case 2 -> withdrawAction.execute(scanner, account1, account2);
                case 3 -> printBalanceAction.execute(scanner, account1, account2);
                case 4 -> transferBalanceAction.execute(scanner, account1, account2);
                case 5 -> exitAction.execute(scanner, account1, account2);
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}