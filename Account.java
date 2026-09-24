import java.util.ArrayList;

public class Account extends BankAccount {

    private ArrayList<Transaction> transactions;

    public Account() {
        super();
        transactions = new ArrayList<>();
    }

    @Override
    public void displayAccountType() {
        System.out.println("Standard Secure Bank Account");
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }

    public void deposit(double amount) {

        if (amount > 0) {

            increaseBalance(amount);

            transactions.add(
                    new Transaction("Deposit", amount)
            );

            System.out.println("Deposit successful!");

        } else {

            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {

        if (decreaseBalance(amount)) {

            transactions.add(
                    new Transaction("Withdrawal", amount)
            );

            System.out.println("Withdrawal successful!");

        } else {

            System.out.println(
                    "Insufficient funds or invalid amount."
            );
        }
    }

    public void showTransactions() {

        System.out.println(
                "\n===== TRANSACTION HISTORY ====="
        );

        if (transactions.isEmpty()) {

            System.out.println("No transactions yet.");

        } else {

            for (Transaction transaction : transactions) {

                transaction.displayTransaction();
            }
        }
    }
}