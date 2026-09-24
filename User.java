public class User {
    private String username;
    private String hashedPassword;

    // Used when creating a new user
    public User(String username, String password) {
        this.username = username;
        this.hashedPassword = PasswordUtil.hashPassword(password);
    }

    // Used when loading an existing user from the file
    private User(String username, String hashedPassword, boolean alreadyHashed) {
        this.username = username;
        this.hashedPassword = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public boolean checkPassword(String password) {
        return PasswordUtil.verifyPassword(password, hashedPassword);
    }

    // Creates a User from data already stored in users.txt
    public static User fromStoredData(String username, String hashedPassword) {
        return new User(username, hashedPassword, true);
    }
}