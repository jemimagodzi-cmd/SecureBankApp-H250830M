import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("  DEAR CUSTOMER, WELCOME TO SECURE BANKING");
        System.out.println("==============================================");

        int mainChoice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            if (scanner.hasNextInt()) {
                mainChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                mainChoice = 0;
            }

            // =========================
            // REGISTER
            // =========================
            if (mainChoice == 1) {

                System.out.println("\n===== REGISTER =====");

                System.out.print("Create username: ");
                String username = scanner.nextLine();

                if (UserStorage.userExists(username)) {
                    System.out.println("\nUsername already exists.");
                    System.out.println("Please choose another username.");
                    continue;
                }

                System.out.print("Create password: ");
                String password = scanner.nextLine();

                User newUser = new User(username, password);

                UserStorage.saveUser(newUser);

                System.out.println("\nRegistration successful!");
                System.out.println(
                        "You can now login using your username and password.");
            }

            // =========================
            // LOGIN
            // =========================
            else if (mainChoice == 2) {

                System.out.println("\n===== LOGIN =====");

                int loginAttempts = 0;
                boolean loginSuccessful = false;

                while (loginAttempts < 3 && !loginSuccessful) {

                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();

                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();

                    User loginUser =
                            UserStorage.loadUser(loginUsername);

                    if (loginUser != null
                            && loginUser.checkPassword(loginPassword)) {

                        loginSuccessful = true;

                        System.out.println("\nLogin successful!");
                        System.out.println(
                                "Welcome, " + loginUsername + "!");

                        // Load the user's saved account
                        Account account =
                                AccountStorage.loadAccount(loginUsername);

                        // =========================
                        // POLYMORPHISM
                        // =========================
                        BankAccount bankAccount = account;
                        bankAccount.displayAccountType();

                        int choice;

                        do {
                            System.out.println(
                                    "\n===== BANKING MENU =====");
                            System.out.println("1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println(
                                    "4. View Transaction History");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");

                            if (scanner.hasNextInt()) {
                                choice = scanner.nextInt();
                            } else {
                                System.out.println(
                                        "Invalid input. Please enter a number.");
                                scanner.next();
                                choice = 0;
                            }

                            switch (choice) {

                                case 1:
                                    System.out.println(
                                            "Your balance is: $"
                                                    + account.getBalance());
                                    break;

                                case 2:
                                    System.out.print(
                                            "Enter deposit amount: $");

                                    if (scanner.hasNextDouble()) {
                                        double depositAmount =
                                                scanner.nextDouble();

                                        account.deposit(depositAmount);

                                        AccountStorage.saveAccount(
                                                loginUsername,
                                                account);
                                    } else {
                                        System.out.println(
                                                "Invalid amount. Please enter a number.");
                                        scanner.next();
                                    }
                                    break;

                                case 3:
                                    System.out.print(
                                            "Enter withdrawal amount: $");

                                    if (scanner.hasNextDouble()) {
                                        double withdrawAmount =
                                                scanner.nextDouble();

                                        account.withdraw(withdrawAmount);

                                        AccountStorage.saveAccount(
                                                loginUsername,
                                                account);
                                    } else {
                                        System.out.println(
                                                "Invalid amount. Please enter a number.");
                                        scanner.next();
                                    }
                                    break;

                                case 4:
                                    account.showTransactions();
                                    break;

                                case 5:
                                    AccountStorage.saveAccount(
                                            loginUsername,
                                            account);

                                    System.out.println(
                                            "Logging out...");
                                    break;

                                default:
                                    System.out.println(
                                            "Invalid option. Please try again.");
                            }

                        } while (choice != 5);

                    } else {

                        loginAttempts++;

                        if (loginAttempts < 3) {
                            System.out.println(
                                    "\nInvalid username or password.");
                            System.out.println(
                                    "Attempts remaining: "
                                            + (3 - loginAttempts));
                        } else {
                            System.out.println(
                                    "\nToo many failed login attempts.");
                            System.out.println(
                                    "Returning to the main menu...");
                        }
                    }
                }
            }

            // =========================
            // EXIT
            // =========================
            else if (mainChoice == 3) {

                System.out.println(
                        "\nThank you for using Secure Banking!");
            }

            else if (mainChoice != 0) {

                System.out.println(
                        "Invalid option. Please try again.");
            }

        } while (mainChoice != 3);

        scanner.close();
    }
}