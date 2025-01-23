import java.util.Scanner;

// idea is to make interface since we learned OOP and t not use switch cases but use interface and classes that override 
public interface BankAction {
    void execute(Scanner scanner, BankAccount account1, BankAccount account2);

}
