import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccountStorage {

    private static final String ACCOUNT_FILE = "accounts.txt";
    private static final String TRANSACTION_FILE = "transactions.txt";

    public static void saveAccount(String username, Account account) {
        saveBalance(username, account.getBalance());
        saveTransactions(username, account.getTransactions());
    }

    private static void saveBalance(String username, double balance) {

        List<String> lines = new ArrayList<>();

        try {
            File file = new File(ACCOUNT_FILE);

            if (file.exists()) {
                BufferedReader reader =
                        new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split("\\|");

                    if (parts.length >= 1 &&
                            !parts[0].equals(username)) {

                        lines.add(line);
                    }
                }

                reader.close();
            }

            lines.add(username + "|" + balance);

            FileWriter writer = new FileWriter(ACCOUNT_FILE);

            for (String line : lines) {
                writer.write(line + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving account balance.");
        }
    }

    private static void saveTransactions(
            String username,
            ArrayList<Transaction> transactions) {

        List<String> lines = new ArrayList<>();

        try {
            File file = new File(TRANSACTION_FILE);

            if (file.exists()) {

                BufferedReader reader =
                        new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split("\\|");

                    if (parts.length >= 1 &&
                            !parts[0].equals(username)) {

                        lines.add(line);
                    }
                }

                reader.close();
            }

            for (Transaction transaction : transactions) {

                lines.add(
                        username + "|"
                        + transaction.getType() + "|"
                        + transaction.getAmount()
                );
            }

            FileWriter writer = new FileWriter(TRANSACTION_FILE);

            for (String line : lines) {
                writer.write(line + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving transactions.");
        }
    }

    public static Account loadAccount(String username) {

        Account account = new Account();

        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(ACCOUNT_FILE));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length == 2 &&
                        parts[0].equals(username)) {

                    double balance =
                            Double.parseDouble(parts[1]);

                    account.setBalance(balance);
                    break;
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            // Account file does not exist yet.
        } catch (IOException e) {
            System.out.println("Error loading account.");
        }

        try {
            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(TRANSACTION_FILE));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length == 3 &&
                        parts[0].equals(username)) {

                    String type = parts[1];

                    double amount =
                            Double.parseDouble(parts[2]);

                    account.addTransaction(type, amount);
                }
            }

            reader.close();

        } catch (FileNotFoundException e) {
            // Transaction file does not exist yet.
        } catch (IOException e) {
            System.out.println("Error loading transactions.");
        }

        return account;
    }
}