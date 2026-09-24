public class BankAccount {

    private double balance;

    public BankAccount() {
        balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    protected void increaseBalance(double amount) {
        balance += amount;
    }

    protected boolean decreaseBalance(double amount) {

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }

        return false;
    }

    public void displayAccountType() {
        System.out.println("Bank Account");
    }
}