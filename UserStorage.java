import java.io.*;

public class UserStorage {

    private static final String FILE_NAME = "users.txt";

    // Save a user to the file
    public static void saveUser(User user) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            writer.write(user.getUsername() + "|" 
                    + user.getHashedPassword() + "\n");

        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    // Check if a username already exists
    public static boolean userExists(String username) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length >= 1 &&
                        parts[0].equals(username)) {

                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            // File does not exist yet.
            // This is normal for the first user.
        } catch (IOException e) {
            System.out.println("Error reading users.");
        }

        return false;
    }

    // Load a user using their username
    public static User loadUser(String username) {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length == 2 &&
                        parts[0].equals(username)) {

                    return User.fromStoredData(
                            parts[0],
                            parts[1]
                    );
                }
            }

        } catch (FileNotFoundException e) {
            return null;

        } catch (IOException e) {
            System.out.println("Error loading user.");
        }

        return null;
    }
}