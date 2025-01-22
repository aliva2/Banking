//Create a new project "Banking" and create a class "BankAccount"
public class BankAccount {
    //with property "balance" (should be decimal number)
    private double balance;

    //create an empty default constructor for it
    BankAccount() {
        System.out.println("Empty BankAccount");
        this.balance = 0;
    }

    //create a constructor with parameter for setting balance.
    BankAccount(double balance) {
        if (balance < 0 ) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    //method to check if amount is positive
    private boolean isPositiveAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be more than 0.");
            return false;
        } else {
            return true;
        }
    }

    //create a method "deposit" with one parameter "amount" (decimal number)
    // which allows the user to increase the balance.
    public void deposit(double amount) {
        if (isPositiveAmount(amount)) {
            balance += amount;
            System.out.println("Deposited " + amount);
        }
    }

    //create a method "withdraw" with one parameter "amount"(decimal number)
    //which allows the user to decrease the balance.
    public void withdraw(double amount) {
        if (isPositiveAmount(amount)) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew " + amount);
            }
            else {
                System.out.println("Not enough funds.");
            }
        }
    }

    //create a method "printBalance" which displays the current balance to user
    public void printBalance() {
        System.out.println("Balance is " + balance);
    }

    //method for transfer balance from one bank account to another
    public void transferBalance(BankAccount source, BankAccount destination, double amount) {
        if (source.balance < amount) {
            System.out.println("Not enough money in account.");
        } else if (amount <= 0 ) {
            System.out.println("Amount must be more than 0.");
        } else {
            source.balance -= amount;
            destination.balance += amount;
            System.out.println("Transferred " + amount);
        }
    }
}
